package com.iav.randomstringgenerator.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.iav.randomstringgenerator.data.model.RandomStringModel
import org.json.JSONObject

@RequiresApi(Build.VERSION_CODES.O)
fun Context.getRandomStringFromProvider(length: Int): RandomStringModel? {
    val uri = Uri.parse("content://com.iav.contestdataprovider/text")
    val bundle = Bundle().apply {
        putInt(ContentResolver.QUERY_ARG_LIMIT, length)
    }

    return try {
        val cursor = contentResolver.query(uri, null, bundle, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val dataIndex = it.getColumnIndex("data")
                if (dataIndex != -1) {
                    val json = it.getString(dataIndex)
                    val obj = JSONObject(json).getJSONObject("randomText")
                    return RandomStringModel(
                        value = obj.getString("value"),
                        length = obj.getInt("length"),
                        created = obj.getString("created")
                    )
                }
            }
        }
        null
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
