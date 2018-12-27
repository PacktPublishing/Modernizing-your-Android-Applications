package com.packt.coolnewandroid

class KotlinAuto(var make: String = "Ford", val model: String = "Edge") {
    fun getName() = make + model

    operator fun plus(that: KotlinAuto): ArrayList<KotlinAuto> {
        val list = ArrayList<KotlinAuto>()
        list.add(this)
        list.add(that)
        return list
    }

    operator fun unaryMinus(): KotlinAuto {
        return KotlinAuto(this.model, this.make)
    }

//    init {
//        "Dodge Caravan".getCar()
//    }

}

fun String.getCar(): KotlinAuto {
    val names = this.split(" ")
    return KotlinAuto(names[0], names[1])
}