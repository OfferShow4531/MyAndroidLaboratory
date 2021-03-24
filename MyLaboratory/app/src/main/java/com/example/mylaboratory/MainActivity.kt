package com.example.mylaboratory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var SomeTxt: TextView ? = null
    private var Placs: TextView ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SomeTxt = findViewById(R.id.MyTxt)
        Placs = findViewById(R.id.MyInp)

    }
    fun GiveMeTxt(view: View){
        val MyToast = Toast.makeText(this, Placs?.text, Toast.LENGTH_SHORT)
        MyToast.show()
    }
    fun GetTxt(view: View){
        SomeTxt?.text = Placs?.text
    }

}

fun main() {
    println("Введите номер месяца и год (1- Если високосный, 0- Если нет")
    var (x,y) = readLine()!!.split(' ')
        fun WeekeNow(x:Int,y:Int): Int {

        when{
            x==1 -> println("Вы ввели январь в нём 31 день")
            x==2 -> if(y==0) println("В феврале сейчас 28 дней ") else println("В феврале сейчас 29 дней (Високосный)")
            x==3 -> println("Вы ввели март в нём 31 день")
            x==4 -> println("Вы ввели апрель в нём 30 день")
            x==5 -> println("Вы ввели май в нём 31 день")
            x==6 -> println("Вы ввели июнь в нём 30 день")
            x==7 -> println("Вы ввели июль в нём 31 день")
            x==8 -> println("Вы ввели август в нём 31 день")
            x==9 -> println("Вы ввели сентябрь в нём 30 день")
            x==10 -> println("Вы ввели октябрь в нём 31 день")
            x==11 -> println("Вы ввели ноябрь в нём 30 день")
            x==12 -> println("Вы ввели декабрь в нём 31 день")
        }
            return 0
    }
    WeekeNow(x.toInt(),y.toInt())

}