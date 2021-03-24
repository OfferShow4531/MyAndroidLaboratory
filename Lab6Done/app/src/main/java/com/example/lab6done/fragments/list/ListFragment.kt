package com.example.lab6done.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab6done.R
import com.example.lab6done.viewmodel.StudentsViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {

    private lateinit var mStudentsViewModel: StudentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        //RecyclerView
        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        //StudentViewModel
        mStudentsViewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)
        mStudentsViewModel.readData.observe(viewLifecycleOwner, Observer { students->
            adapter.setData(students)
        })

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)

        }
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllStudent()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllStudent() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mStudentsViewModel.deleteAllStudent()
            Toast.makeText(
                requireContext(),
                "success remove all:",
                Toast.LENGTH_SHORT
            ).show()

        }
        builder.setNegativeButton("No"){_,_-> }
        builder.setTitle("Delete all students?")
        builder.setMessage("You sure delete all students?")
        builder.create().show()
    }

}