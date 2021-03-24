package com.example.lab6done.fragments.add

import android.app.Application
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lab6done.R
import com.example.lab6done.model.Students
import com.example.lab6done.viewmodel.StudentsViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    private lateinit var mStudentsViewModel: StudentsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        view.add_btr.setOnClickListener{
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase(){
        val name = addName.text.toString()
        val age = addAge.text
        val comment = addComment.text.toString()
        val reputation = addReputation.text
        mStudentsViewModel = StudentsViewModel(Application())
        if(inputCheck(name, age, comment, reputation)){
            //create
            val students = Students(0,name,Integer.parseInt(age.toString()),comment,Integer.parseInt(reputation.toString()))

            //AddDATA
            mStudentsViewModel.addStudent(students)
            Toast.makeText(requireContext(),"YOU ADD ME!", Toast.LENGTH_SHORT).show()

            //Navigate
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"ERROR SOMETHING IS GONE WRONG!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name:String, age:Editable, comment:String, reputation:Editable): Boolean{
        return!(TextUtils.isEmpty(name) && TextUtils.isEmpty(comment) && age.isEmpty() && reputation.isEmpty())
    }

}