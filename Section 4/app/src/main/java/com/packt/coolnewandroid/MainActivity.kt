package com.packt.coolnewandroid

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.packt.coolnewandroid.room.CoolNewDatabase
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private val automobiles = ArrayList<String>()

    private var recyclerAdapter: AutomobileRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        recyclerView.isVisible
//        recyclerView.isGone
//        recyclerView.isInvisible

//        (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(1000)
//        getSystemService<Vibrator>().vibrate(1000)

//        val bundle = Bundle()
//        bundle.putSerializable("auto", Automobile("ee", "df"))
//
//        bundleOf("auto" to Automobile("ee", "df"))


//        recyclerView.postDelayed(Runnable {
//
//        }, 1000)

//        recyclerView.postDelayed(1000, {
//            //do something
//        })
//
//        recyclerView.postDelayed(1000) {
//            //do something
//        }

//        getSystemService<SharedPreferences>()!!.edit {
//            putBoolean("darkMode", true)
//        }
//
//
//        getSystemService<SharedPreferences>()!!.getBoolean("darkMode", false)
//


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(recyclerView: RecyclerView, motionEvent: MotionEvent): Boolean {
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        toast("finger down")
                    }

                    MotionEvent.ACTION_UP -> {
                        toast("finger up")
                    }
                }
                return false
            }

            override fun onTouchEvent(recyclerView: RecyclerView, motionEvent: MotionEvent) {

            }

            override fun onRequestDisallowInterceptTouchEvent(b: Boolean) {

            }
        })
        recyclerAdapter = AutomobileRecyclerAdapter(this)
        recyclerView.adapter = recyclerAdapter

        if (savedInstanceState != null) {
            val arrayList = savedInstanceState.getStringArrayList("auto")
            if (arrayList != null) {
                for (s in arrayList) {
                    val strings = s.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    val automobile = Automobile(strings[0], strings[1])
                    addAutoToList(automobile)
                }
            }
        }

//        val cursor = contentResolver.query(DBContract.Auto.CONTENT_URI, null, null, null, null)
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                val automobile = Automobile(0, cursor.getString(1), cursor.getString(2))
//                addAutoToList(automobile, false)
//            }
//            cursor.close()
//        }

        Thread {
            val autos = CoolNewDatabase.getAppDataBase(this@MainActivity)!!.dao().getAllAutos()
            autos.forEach { addAutoToList(it, false) }
        }.start()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putStringArrayList("auto", automobiles)
        super.onSaveInstanceState(outState)
    }

    fun onClick(view: View) {
        val intent = Intent(this@MainActivity, FormActivity::class.java)
        startActivityForResult(intent, FORM_REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == FORM_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val automobile = data.getSerializableExtra(KEY_AUTOMOBILE) as Automobile
                addAutoToList(automobile)
            }
        }
    }


    private fun addAutoToList(automobile: Automobile, shouldSave: Boolean = true) {
        runOnUiThread {
            automobiles.add(automobile.make + " " + automobile.model)
            recyclerAdapter!!.addAutomobile(automobile)

            if (shouldSave) {
//            val contentValues = contentValuesOf(COLUMN_MAKE to automobile.make, COLUMN_MODEL to automobile.model)
//            contentResolver.insert(DBContract.Auto.CONTENT_URI, contentValues)
                Thread {
                    CoolNewDatabase.getAppDataBase(this@MainActivity)!!.dao().insertAuto(automobile)
                }.start()
            }
        }
    }

    companion object {

        val FORM_REQUEST_CODE = 1
        val KEY_AUTOMOBILE = "key_automobile"
    }
}

fun Context.toast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}