package com.packt.coolnewandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.packt.coolnewandroid.room.CoolNewDatabase
import kotlinx.android.synthetic.main.activity_form_constraint.*

class FormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_constraint)

        val recyclerAdapter = AutomobileRecyclerAdapter(this, lifecycle)
        lifecycle.addObserver(recyclerAdapter!!)

        btn_save.setOnClickListener {
            val make = edit_make.text.toString()
            val model = edit_model.text.toString()
            val automobile = Automobile(make, model)
//            val intent = Intent()
//            intent.putExtra(MainActivity.KEY_AUTOMOBILE, automobile)
//            setResult(Activity.RESULT_OK, intent)

            Thread {
                CoolNewDatabase.getAppDataBase(this@FormActivity)!!.dao().insertAuto(automobile)
            }.start()

            finish()
        }
    }

}
