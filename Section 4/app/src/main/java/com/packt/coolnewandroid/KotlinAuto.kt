package com.packt.coolnewandroid

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class KotlinAuto(@NonNull @PrimaryKey val id: Int = 0, var make: String = "Ford", val model: String = "Edge") {
    fun getName() = make + model

    operator fun plus(that: KotlinAuto): ArrayList<KotlinAuto> {
        val list = ArrayList<KotlinAuto>()
        list.add(this)
        list.add(that)
        return list
    }

    operator fun unaryMinus(): KotlinAuto {
        return KotlinAuto(this.id, this.model, this.make)
    }

//    init {
//        "Dodge Caravan".getCar()
//    }

}

fun String.getCar(id: Int): KotlinAuto {
    val names = this.split(" ")
    return KotlinAuto(id, names[0], names[1])
}