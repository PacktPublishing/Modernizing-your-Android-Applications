package com.packt.coolnewandroid

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.packt.coolnewandroid.room.CoolNewDatabase

class AutoViewModel(application: Application) : AndroidViewModel(application) {

    var autos: LiveData<PagedList<Automobile>>? = null
    var autosDataSource: LiveData<PageKeyedDataSource<Int, Automobile>>? = null


    init {
        val dataSourceFactory = CoolDataSourceFactory(application)
        autosDataSource = dataSourceFactory.autosLiveDataSource

        val config = PagedList.Config.Builder()
                                                .setEnablePlaceholders(false)
                                                .setPageSize(CoolDataSource.PAGE_SIZE)
                                                .build()

        autos = LivePagedListBuilder(dataSourceFactory, config).build()
    }


//    fun getAutos(): LiveData<PagedList<Automobile>> {
//        if (autos == null) {
//            autos = CoolNewDatabase.getAppDataBase(getApplication())!!.dao().getAllAutosLiveData()
//        }
//        return autos as LiveData<List<Automobile>>
//    }


//    private var autos: LiveData<List<Automobile>>? = null
//
//    fun getAutos(): LiveData<List<Automobile>> {
//        if (autos == null) {
//            autos = CoolNewDatabase.getAppDataBase(getApplication())!!.dao().getAllAutosLiveData()
//        }
//        return autos as LiveData<List<Automobile>>
//    }


    private var makes: LiveData<String>? = null

    fun getMakes(): LiveData<String> {
        if (makes == null) {
            makes = CoolNewDatabase.getAppDataBase(getApplication())!!.dao().getAllAutoMakesLiveData()
        }
        return makes as LiveData<String>
    }



}