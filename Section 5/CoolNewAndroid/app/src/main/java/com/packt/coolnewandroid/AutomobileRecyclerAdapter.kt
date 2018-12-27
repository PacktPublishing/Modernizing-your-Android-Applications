package com.packt.coolnewandroid

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import com.packt.coolnewandroid.room.CoolNewDatabase

class AutomobileRecyclerAdapter(val context: Context, val lifecycle: Lifecycle) : RecyclerView.Adapter<AutomobileRecyclerAdapter.AutomobileViewHolder>(), LifecycleObserver {

    private val automobiles = ArrayList<Automobile>()


    fun addAutomobile(automobile: Automobile) {
//        if (lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)) {
            automobiles.add(automobile)
            notifyDataSetChanged()
//        }
    }


    fun setAutomobiles(automobiles: ArrayList<Automobile>) {
        this.automobiles.clear()
        this.automobiles.addAll(automobiles)
        notifyDataSetChanged()
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun doOnPause() {
        log("onPause->adapter inside ${(context as Activity).localClassName}!!")
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

    }

    override fun getItemCount() = automobiles.size


    class AutomobileViewHolder(val name: TextView) : RecyclerView.ViewHolder(name)

}
