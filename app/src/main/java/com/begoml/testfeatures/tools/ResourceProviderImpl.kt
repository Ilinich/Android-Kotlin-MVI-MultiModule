package com.begoml.testfeatures.tools

import android.content.Context
import androidx.core.content.ContextCompat
import com.begoml.common_api.providers.ResourceProvider
import com.begoml.tools.extension.emptyString
import com.begoml.tools.extension.getAndroidId
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(private val context: Context) : ResourceProvider {

    override val deviceId
        get() = context.getAndroidId()

    override val packageName: String
        get() = context.packageName

    override fun getString(res: Int) = context.resources.getString(res)

    override fun getString(res: Int, vararg args: Any) = context.resources.getString(res, *args)

    override fun getStringArray(res: Int): List<String> = context.resources.getStringArray(res).asList()

    override fun getColor(res: Int) = ContextCompat.getColor(context, res)
}