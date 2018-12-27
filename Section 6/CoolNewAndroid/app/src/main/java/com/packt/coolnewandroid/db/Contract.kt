package com.packt.coolnewandroid.db

import android.net.Uri
import android.provider.BaseColumns
import com.packt.coolnewandroid.BuildConfig

public class DBContract {

    companion object {
        val CONTENT_AUTHORITY = BuildConfig.APPLICATION_ID + ".provider"
        val BASE_CONTENT_URI = Uri.parse("content://$CONTENT_AUTHORITY")
        private val CONTENT_TYPE_PREFIX = "vnd.android.cursor.dir/vnd.com.packt.coolnewandroid.provider."
        private val CONTENT_TYPE_ITEM_PREFIX = "vnd.android.cursor.item/vnd.com.packt.coolnewandroid.provider."
    }

    class AutoColumns {
        companion object {
            val COLUMN_ID = BaseColumns._ID
            val COLUMN_MAKE = "make"
            val COLUMN_MODEL = "model"
        }
    }


    class Auto : BaseColumns {
        companion object {
            val CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath("auto").build()
            val CONTENT_TYPE = CONTENT_TYPE_PREFIX + "auto"
            val CONTENT_ITEM_TYPE = CONTENT_TYPE_ITEM_PREFIX + "auto"
        }
    }

}