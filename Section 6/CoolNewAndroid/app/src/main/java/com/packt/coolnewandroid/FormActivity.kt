package com.packt.coolnewandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.packt.coolnewandroid.databinding.ActivityFormConstraintBinding
import com.packt.coolnewandroid.room.CoolNewDatabase
import kotlinx.android.synthetic.main.activity_form_constraint.*

class FormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_form_constraint)

        val binding: ActivityFormConstraintBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_form_constraint)

        binding.auto = Automobile(null, "Marcialago")

//        val auto = Automobile("Lamborghini", "Marcialago")
//        findViewById<TextView>(R.id.edit_make).setHint(auto.make + "(or other make)")
//        findViewById<TextView>(R.id.edit_model).setHint(auto.model)

        btn_save.setOnClickListener {
            val make = edit_make.text.toString()
            val model = edit_model.text.toString()
            val automobile = Automobile(make, model)

            Thread {
                CoolNewDatabase.getAppDataBase(this@FormActivity)!!.dao().insertAuto(automobile)
            }.start()

            finish()
        }
    }

}
