package com.example.laba5

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_recycler.*
import kotlin.random.Random


class RecyclerFragment : Fragment() {

    private val list: Array<Int> = arrayOf(R.drawable.ic_outline, R.drawable.ic_outline_green, R.drawable.ic_outline_red, R.drawable.ic_outline_yellow, R.drawable.ic_outline)
    private lateinit var adapter: Adapter
    private val listOfAllItems = arrayListOf(
            PageItem(R.drawable.ic_outline, "Student", "1", "15", false),
            PageItem(R.drawable.ic_outline_red, "Student", "2", "18", false),
            PageItem(R.drawable.ic_outline_green, "Student", "3", "22", false),
            PageItem(R.drawable.ic_outline_red, "Student", "4", "31", false),
            PageItem(R.drawable.ic_outline_green, "Student", "5", "13", false),
            PageItem(R.drawable.ic_outline, "Student", "6", "30", false))


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun randomImage(): Int {
        return list[Random.nextInt(0,list.size-1)]
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_add->{
                listOfAllItems.add(PageItem(randomImage(), "Students", "${Random.nextInt(1,10)}","${Random.nextInt(12,30)}",false))
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
                    Toast.makeText(context, "NOT COMMITTED", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = Adapter(listOfAllItems, findNavController())
        val recyclerview: RecyclerView = view.findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = adapter
        recyclerview.setHasFixedSize(true)

        super.onViewCreated(view, savedInstanceState)
    }


}