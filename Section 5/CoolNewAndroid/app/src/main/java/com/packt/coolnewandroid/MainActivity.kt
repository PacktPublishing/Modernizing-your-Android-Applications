package com.packt.coolnewandroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AutoViewModel

//    private val automobiles = ArrayList<String>()

    private var recyclerAdapter: AutomobileRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        log("onCreate()")

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(recyclerView: RecyclerView, motionEvent: MotionEvent): Boolean {
//                when (motionEvent.action) {
//                    MotionEvent.ACTION_DOWN -> {
//                        toast("finger down")
//                    }
//
//                    MotionEvent.ACTION_UP -> {
//                        toast("finger up")
//                    }
//                }
                return false
            }

            override fun onTouchEvent(recyclerView: RecyclerView, motionEvent: MotionEvent) {

            }

            override fun onRequestDisallowInterceptTouchEvent(b: Boolean) {

            }
        })
        recyclerAdapter = AutomobileRecyclerAdapter(this, lifecycle)
        lifecycle.addObserver(recyclerAdapter!!)
        recyclerView.adapter = recyclerAdapter

//        if (savedInstanceState != null) {
//            val arrayList = savedInstanceState.getStringArrayList("auto")
//            if (arrayList != null) {
//                for (s in arrayList) {
//                    val strings = s.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
//                    val automobile = Automobile(strings[0], strings[1])
//                    addAutoToList(automobile)
//                }
//            }
//        }


//        Thread {
//            val autos = CoolNewDatabase.getAppDataBase(this@MainActivity)!!.dao().getAllAutos()
//            autos.forEach { addAutoToList(it, false) }
//        }.start()


        viewModel = ViewModelProviders.of(this).get(AutoViewModel(application as CoolApplication)::class.java)

        val autosObserver = Observer<List<Automobile>> { autos ->
//            autos.forEach {
//                addAutoToList(it, false)
//            }
            recyclerAdapter!!.setAutomobiles(autos as ArrayList<Automobile>)
        }

        viewModel.getAutos().observe(this, autosObserver)

//        autos.forEach {
//            addAutoToList(it, false)
//        }

    }



//    override fun onPause() {
//        log("onPause()")
//
//        if (recyclerAdapter != null) {
//            recyclerAdapter!!.notifyDataSetChanged()
//        }
//
//        super.onPause()
//    }

//    override fun onStart() {
//        super.onStart()
//        log("onStart()")
//    }
//
//
//    override fun onTrimMemory(level: Int) {
//        super.onTrimMemory(level)
//    }
//
//    override fun onWindowFocusChanged(hasFocus: Boolean) {
//        super.onWindowFocusChanged(hasFocus)
//    }

//    override fun onStop() {
//        log("onStop()")
//        super.onStop()
//    }
//
//
//    override fun onDestroy() {
//        log("onDestroy()")
//        super.onDestroy()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        log("onResume()")
//    }
//
//
//    override fun onPostResume() {
//        super.onPostResume()
//        log("onPostResume()")
//    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        outState.putStringArrayList("auto", automobiles)
//        super.onSaveInstanceState(outState)
//    }

    fun onClick(view: View) {
        val intent = Intent(this@MainActivity, FormActivity::class.java)
//        startActivityForResult(intent, FORM_REQUEST_CODE)
        startActivity(intent)
    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (requestCode == FORM_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            if (data != null) {
//                val automobile = data.getSerializableExtra(KEY_AUTOMOBILE) as Automobile
//                addAutoToList(automobile)
//            }
//        }
//    }


//    private fun addAutoToList(automobile: Automobile, shouldSave: Boolean = true) {
//        runOnUiThread {
////            automobiles.add(automobile.make + " " + automobile.model)
//
//            recyclerAdapter!!.addAutomobile(automobile)
//
////            if (shouldSave) {
////                viewModel.addAuto(automobile)
////            }
//
////            if (shouldSave) {
////                Thread {
////                    CoolNewDatabase.getAppDataBase(this@MainActivity)!!.dao().insertAuto(automobile)
////                }.start()
////            }
//        }
//    }

//    companion object {
//
//        val FORM_REQUEST_CODE = 1
//        val KEY_AUTOMOBILE = "key_automobile"
//    }
}

fun Context.toast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

fun log(string: String) {
    Log.d("MainActivity", string)
}