package com.packt.coolnewandroid.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class Database(context: Context) : SQLiteOpenHelper(context, "coolnewandroid.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {

        db!!.execSQL("CREATE TABLE autos (" +
                DBContract.AutoColumns.COLUMN_ID + " integer primary key, " +
                DBContract.AutoColumns.COLUMN_MAKE + " text, " +
                DBContract.AutoColumns.COLUMN_MODEL + " text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS autos")
        onCreate(db)
    }


}