package com.example.workinglab4

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: PageAdapter
    val list: Array<Int> = arrayOf(R.drawable.ic_outline, R.drawable.ic_outline_green, R.drawable.ic_outline_red, R.drawable.ic_outline_yellow, R.drawable.ic_outline)
    private val listOfAllItems = arrayListOf(
        PageItem(R.drawable.ic_outline, "Student", "1", "15", false),
        PageItem(R.drawable.ic_outline_red, "Student", "2", "18", false),
        PageItem(R.drawable.ic_outline_green, "Student", "3", "22", false),
        PageItem(R.drawable.ic_outline_red, "Student", "4", "31", false),
        PageItem(R.drawable.ic_outline_green, "Student", "5", "13", false),
        PageItem(R.drawable.ic_outline, "Student", "6", "30", false))

    private var count:Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = PageAdapter(listOfAllItems)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter

    }


    private fun RandomImage(): Int {
        return list[kotlin.random.Random.nextInt(0,list.size-1)]
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_add->{
                listOfAllItems.add(PageItem(RandomImage(), "Students", "${Random.nextInt(1,10)}","${Random.nextInt(12,30)}",false))
                adapter.notifyDataSetChanged()
                true
            }
            R.id.action_delete->{
                if(adapter.checkedItems.size > 0){
                    listOfAllItems.removeAll(adapter.checkedItems)
                    adapter.notifyDataSetChanged()
                    false
                }
                else{
                    Toast.makeText(this, "NOT COMMITED",Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

}