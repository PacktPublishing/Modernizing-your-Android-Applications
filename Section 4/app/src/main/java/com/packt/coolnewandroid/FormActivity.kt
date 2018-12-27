package com.packt.coolnewandroid

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_form_constraint.*

class FormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_constraint)

        btn_save.setOnClickListener {
            val make = edit_make.text.toString()
            val model = edit_model.text.toString()
            val automobile = Automobile(make, model)
            val intent = Intent()
            intent.putExtra(MainActivity.KEY_AUTOMOBILE, automobile)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

}
