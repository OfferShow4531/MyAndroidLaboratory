package com.example.calculatorlabdone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var allRes: TextView? = null
    var resultAfter:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_0.setOnClickListener { setTextField("0") }
        btn_1.setOnClickListener { setTextField("1") }
        btn_2.setOnClickListener { setTextField("2") }
        btn_3.setOnClickListener { setTextField("3") }
        btn_4.setOnClickListener { setTextField("4") }
        btn_5.setOnClickListener { setTextField("5") }
        btn_6.setOnClickListener { setTextField("6") }
        btn_7.setOnClickListener { setTextField("7") }
        btn_8.setOnClickListener { setTextField("8") }
        btn_9.setOnClickListener { setTextField("9") }
        dot_btn.setOnClickListener { setTextField(".") }
        minus_btn.setOnClickListener { setTextField("-") }
        sum_btn.setOnClickListener { setTextField("+") }
        demult_btn.setOnClickListener { setTextField("/") }
        mult_btn.setOnClickListener { setTextField("*") }
        opnprth_btn.setOnClickListener { setTextField("(") }
        clsprth_btn.setOnClickListener { setTextField(")") }


        del_btn.setOnClickListener {
            math_operation.text = ""
            result_text.text = ""

        }

        back_btn.setOnClickListener {
            val mes = math_operation.text.toString()
            if (mes.isNotEmpty())
                math_operation.text = mes.substring(0, mes.length - 1)

            result_text.text = ""

        }
        equal_btn.setOnClickListener {
            try {
                val ex = ExpressionBuilder(math_operation.text.toString()).build()
                val rec = ex.evaluate()
                val longRes = rec.toLong()
                if (rec == longRes.toDouble())
                    result_text.text = longRes.toString()
                else
                    result_text.text = rec.toString()

            } catch (e: Exception) {
                Log.d("Error", "Message: ${e.message}")
            }
        }
        allRes = result_text

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("Instance state", allRes?.text.toString());
        Log.e("Instance state", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        resultAfter = savedInstanceState.getString("Instance state").toString()

        Log.e("Instance state", "onRestoreInstanceState")
        Log.e("Check", resultAfter)

        //output
        allRes?.text = resultAfter
    }


    fun setTextField(str: String) {
        if (result_text.text != "")
            math_operation.text = result_text.text
        result_text.text = ""
        math_operation.append(str)
    }

}



