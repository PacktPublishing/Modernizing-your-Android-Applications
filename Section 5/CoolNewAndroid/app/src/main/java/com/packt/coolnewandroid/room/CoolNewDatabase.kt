package com.packt.coolnewandroid.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.packt.coolnewandroid.Automobile

@Database(entities = [Automobile::class], version = 1, exportSchema = false)
abstract class CoolNewDatabase : RoomDatabase() {

    abstract fun dao(): CoolNewDao

    companion object {

        private var INSTANCE: CoolNewDatabase ? = null

        fun getAppDataBase(context: Context): CoolNewDatabase? {
            if (INSTANCE == null){
                synchronized(CoolNewDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            CoolNewDatabase::class.java, "coolNewDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}