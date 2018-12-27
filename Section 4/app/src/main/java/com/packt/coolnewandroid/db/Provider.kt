package com.packt.coolnewandroid.db

import android.content.ContentProvider
import android.content.ContentResolver
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class Provider : ContentProvider() {


    private val sUriMatcher = buildUriMatcher()

    private val PRODUCT = 100
    private val PRODUCT_ID = 101


    private var mDatabase: SQLiteDatabase? = null
    private var mDbHelper: Database? = null
    private var mContentResolver: ContentResolver? = null


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        mDatabase = mDbHelper!!.getWritableDatabase()

        val table = "autos"

        val id = mDatabase!!.insertWithOnConflict(table, null, values, SQLiteDatabase.CONFLICT_REPLACE)

        val uri2 = if (id == -1L) uri else Uri.withAppendedPath(uri, id.toString())
        mContentResolver!!.notifyChange(uri2, null)
        return uri2
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        mDatabase = mDbHelper!!.writableDatabase

        val table = "autos"

        val cursor = mDatabase!!.query(table, projection, selection, selectionArgs, null, null, sortOrder)
        cursor.setNotificationUri(mContentResolver, uri)
        return cursor
    }

    override fun onCreate(): Boolean {
        val context = context
        if (context != null) {
            mDbHelper = Database(context)
            mContentResolver = context.contentResolver
        }
        return true
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        mDatabase = mDbHelper!!.writableDatabase

        val table = "autos"

        val ui = mDatabase!!.update(table, values, selection, selectionArgs)
        mContentResolver!!.notifyChange(uri, null)
        return ui
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        mDatabase = mDbHelper!!.getWritableDatabase()

        val table = "autos"

        val del = mDatabase!!.delete(table, null, selectionArgs)
        mContentResolver!!.notifyChange(uri, null)
        return del
    }

    override fun getType(uri: Uri) = DBContract.Auto.CONTENT_TYPE

    private fun buildUriMatcher(): UriMatcher {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)

        matcher.addURI(DBContract.CONTENT_AUTHORITY, "auto", PRODUCT)
        matcher.addURI(DBContract.CONTENT_AUTHORITY, "auto/*", PRODUCT_ID)

        return matcher
    }
}