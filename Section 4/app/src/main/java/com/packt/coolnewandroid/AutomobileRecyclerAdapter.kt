package com.packt.coolnewandroid

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.packt.coolnewandroid.room.CoolNewDatabase

class AutomobileRecyclerAdapter(val context: Context) : RecyclerView.Adapter<AutomobileRecyclerAdapter.AutomobileViewHolder>() {

    private val automobiles = ArrayList<Automobile>()


    fun addAutomobile(automobile: Automobile) {
        automobiles.add(automobile)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AutomobileViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(android.R.layout.simple_spinner_dropdown_item, viewGroup, false)
        return AutomobileViewHolder(view as TextView)
    }

    override fun onBindViewHolder(automobileViewHolder: AutomobileViewHolder, position: Int) {
        val automobile = automobiles[position]
        automobileViewHolder.name.text = automobile.make + " " + automobile.model

        automobileViewHolder.name.setOnLongClickListener {
            Thread {
                CoolNewDatabase.getAppDataBase(context)!!.dao().deleteAuto(automobile)
                (context as Activity).runOnUiThread {
                    automobiles.remove(automobile)
                    notifyDataSetChanged()
                }
            }.start()
            true
        }

        val car1 = KotlinAuto()
        val car2 = KotlinAuto(make = "Ford")
        val car3 = KotlinAuto(model = "Edge")
        val car4 = KotlinAuto(model = "A4", make = "Audi")
        val car5 = "Audi A7".getCar(12)

        val cars = car1 + car2 + car3 + car4 + (-car5)

    }

    override fun getItemCount() = automobiles.size


    class AutomobileViewHolder(val name: TextView) : RecyclerView.ViewHolder(name)

}
