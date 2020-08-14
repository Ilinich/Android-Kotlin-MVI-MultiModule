package com.begoml.feature_news_feed_impl.presentation.view

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.begoml.feature_news_feed_impl.R
import com.begoml.feature_news_feed_impl.di.NewsFeedFlowCoordinator
import com.begoml.feature_news_feed_impl.di.component.DaggerNewsFeedComponent
import com.begoml.feature_news_feed_impl.di.component.NewsFeedFlowComponent
import com.begoml.feature_news_feed_impl.navigation.FlowCoordinator
import com.begoml.feature_news_feed_impl.presentation.news.*
import com.begoml.presentation.mvi.initializeViewStateWatcher
import kotlinx.android.synthetic.main.news_feed_fragment.*
import javax.inject.Inject

class NewsFeedFragment : Fragment(R.layout.news_feed_fragment) {

    @Inject
    lateinit var factory: NewsFeedViewModelFactory

    @Inject
    @field:[NewsFeedFlowCoordinator]
    lateinit var flowCoordinator: FlowCoordinator

    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider({ viewModelStore }, factory).get(NewsViewModel::class.java)
    }

    private val newsAdapter by lazy {
        NewsAdapter { newsModel ->
            viewModel.dispatchEvent(Event.NewsModelClick(newsModel))
        }
    }

    private val watcher = initializeViewStateWatcher<ViewState> {

        ViewState::isLoading { isListLoading ->
            progress.visibility(isListLoading)
        }

        ViewState::newsList { newsList ->
            newsAdapter.submitList(newsList)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependencies()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(ItemDecoration())
            adapter = newsAdapter
        }


        viewModel.apply {
            viewState.observe(viewLifecycleOwner, Observer { stateView ->
                stateView?.let {
                    watcher.render(it)
                }
            })

            news.observe(viewLifecycleOwner, Observer { event ->
                event?.let {
                    when (it) {
                        is News.GoToNewsDetails -> {
                            flowCoordinator.goToUserAccount()
                        }
                    }
                }
            })
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.dispatchEvent(Event.SaveInstanceState)

        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        watcher.clear()

        super.onDestroyView()
    }

    private fun injectDependencies() {
        val newsComponent = DaggerNewsFeedComponent.builder()
            .screen(this@NewsFeedFragment)
            .newsFeedFlowComponentProvider(NewsFeedFlowComponent.get())
            .build()

        newsComponent.inject(this)
    }

}

fun View.visibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

class ItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.apply {
            top = parent.context.resources.getDimensionPixelSize(R.dimen.margin_20)
            right = parent.context.resources.getDimensionPixelSize(R.dimen.margin_8)
            left = parent.context.resources.getDimensionPixelSize(R.dimen.margin_8)
        }
    }
}
