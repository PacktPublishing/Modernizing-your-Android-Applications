package com.packt.coolnewandroid.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.packt.coolnewandroid.Automobile

@Dao
interface CoolNewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAuto (automobile: Automobile)

    @Insert
    fun insertMultipleAutos (automobiles: List<Automobile>)

    @Query("SELECT * FROM Automobile")
    fun getAllAutosLiveData (): LiveData<List<Automobile>>

    @Query("SELECT * FROM Automobile")
    fun getAllAutos (): List<Automobile>



    @Query("SELECT * FROM Automobile ORDER BY make ASC LIMIT 10")
    fun getFirstTenAutos (): List<Automobile>

    @Query("SELECT * FROM Automobile ORDER BY make ASC LIMIT :from OFFSET :offset")
    fun getTenAutos (from: Int, offset: Int): List<Automobile>



    @Query("SELECT * FROM Automobile WHERE make = 'Ford'")
    fun getAllFords(): List<Automobile>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAuto (automobile: Automobile)

    @Delete
    fun deleteAuto (automobile: Automobile)



    @Query("SELECT make FROM Automobile LIMIT 1")
    fun getAllAutoMakesLiveData (): LiveData<String>

    @Query("DELETE FROM Automobile")
    fun deleteAll()
}