package com.example.lab6done.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lab6done.R
import com.example.lab6done.model.Students
import com.example.lab6done.viewmodel.StudentsViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mStudentsViewModel: StudentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)


        mStudentsViewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)

        view.updateName.setText(args.currentStudent.Name)
        view.updateAge.setText(args.currentStudent.age.toString())
        view.updateComment.setText(args.currentStudent.AboutSelf)
        view.updateRate.setText(args.currentStudent.rating.toString())


        view.update_btr.setOnClickListener{
            updateItem()
        }

        //AddMenu
        setHasOptionsMenu(true)


        return view
    }




    private fun updateItem(){
        val name = updateName.text.toString()
        val age = Integer.parseInt(updateAge.text.toString())
        val comment = updateComment.text.toString()
        val rate = Integer.parseInt(updateRate.text.toString())

        if(inputCheck(name,updateAge.text,comment,updateRate.text)){
            //CREATE
            val updaterStudent = Students(args.currentStudent.id,name,age,comment,rate)
            //UPDATE
            Toast.makeText(requireContext(), "SUCCESSFUL!", Toast.LENGTH_SHORT).show()
            mStudentsViewModel.updateStudent(updaterStudent)
            //NAVIGATE FOR UPDATE
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "ERROR! SOMETHING IS GONE WRONG!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name:String, age: Editable, comment:String, reputation: Editable): Boolean{
        return!(TextUtils.isEmpty(name) && TextUtils.isEmpty(comment) && age.isEmpty() && reputation.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteStudent()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteStudent(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mStudentsViewModel.deleteStudent(args.currentStudent)
            Toast.makeText(
                requireContext(),
                "success remove: ${args.currentStudent.Name}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_,_-> }
            builder.setTitle("Delete ${args.currentStudent.Name}?")
            builder.setMessage("You sure delete ${args.currentStudent.Name}?")
            builder.create().show()
        }

}