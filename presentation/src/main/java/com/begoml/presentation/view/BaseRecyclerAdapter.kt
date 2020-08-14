package com.begoml.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class BaseRecyclerAdapter<T, VH : BaseViewHolder<T>> :
    RecyclerView.Adapter<VH>() {

    var items: List<T> = listOf()

    val isEmpty: Boolean
        get() = items.isEmpty()

    fun getItem(position: Int): T {
        return items[position]
    }

    protected fun inflate(
        @LayoutRes layout: Int, parent: ViewGroup,
        attachToRoot: Boolean = false
    ): View {
        return LayoutInflater.from(parent.context).inflate(layout, parent, attachToRoot)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    abstract fun submitList(newList: List<T>)
}

abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {

    /**
     * Bind data to the item and set listener if needed.
     *
     * @param data object, associated with the item.
     */
    abstract fun bind(data: T)
}