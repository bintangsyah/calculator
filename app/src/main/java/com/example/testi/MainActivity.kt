package com.example.testi

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

private lateinit var editWidth: EditText
private lateinit var editHeight: EditText
private lateinit var editLength: EditText
private lateinit var btnCalc: Button
private lateinit var tvResult: TextView

class MainActivity : ComponentActivity(), View.OnClickListener {

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editWidth = findViewById(R.id.edt_length)
        editHeight = findViewById(R.id.edt_height)
        editLength = findViewById(R.id.edt_length)
        btnCalc = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        btnCalc.setOnClickListener(this)
        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT) as String
            tvResult.text = result
        }
    }

    @Override
    override fun onClick(v: View?) {
        if (v != null) {
            if (v.getId() == R.id.btn_calculate) {
                val inputLength = editLength.text.toString().trim()
                val inputWidth = editWidth.text.toString().trim()
                val inputHeight = editHeight.text.toString().trim()


                var isEmptyFields = false


                if (inputLength.isEmpty()) {
                    isEmptyFields = true
                    editLength.error = "Field Ini Tidak Boleh Kosong"
                }


                if (inputWidth.isEmpty()) {
                    isEmptyFields = true
                    editWidth.error = "Field Ini Tidak Boleh Kosong"
                }


                if (inputHeight.isEmpty()) {
                    isEmptyFields = true
                    editHeight.error = "Field Ini Tidak Boleh Kosong"
                }


                var length: Double? = toDouble(inputLength)
                var width: Double? = toDouble(inputWidth)
                var height: Double? = toDouble(inputHeight)


                if (!isEmptyFields) {
                    val volume = length!! * width!! * height!!
                    tvResult.text = volume.toString()
                }
            }
        }
    }

    private fun toDouble(str: String): Double? {
        return try {
            str.toDouble()
        } catch (e: NumberFormatException) {
            null
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }
}