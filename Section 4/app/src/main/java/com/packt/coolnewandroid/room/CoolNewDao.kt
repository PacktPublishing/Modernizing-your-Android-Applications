package com.packt.coolnewandroid.room

import androidx.room.*
import com.packt.coolnewandroid.Automobile

@Dao
interface CoolNewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAuto (automobile: Automobile)

    @Insert
    fun insertMultipleAutos (automobiles: List<Automobile>)

    @Query("SELECT * FROM Automobile")
    fun getAllAutos (): List<Automobile>

    @Query("SELECT * FROM Automobile WHERE make = 'Ford'")
    fun getAllFords(): List<Automobile>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAuto (automobile: Automobile)

    @Delete
    fun deleteAuto (automobile: Automobile)
}