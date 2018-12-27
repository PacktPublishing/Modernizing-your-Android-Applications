package com.packt.coolnewandroid

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.packt.coolnewandroid.room.CoolNewDatabase


//class AutoViewModel : ViewModel() {
class AutoViewModel(application: Application) : AndroidViewModel(application) {

    //    private val autos = ArrayList<Automobile>()
    private var autos: LiveData<List<Automobile>>? = null

//    fun getAutos(): List<Automobile> {
//        return autos
//    }


    fun getAutos(): LiveData<List<Automobile>> {
        if (autos == null) {
            autos = CoolNewDatabase.getAppDataBase(getApplication())!!.dao().getAllAutosLiveData()
        }
        return autos as LiveData<List<Automobile>>
    }

}