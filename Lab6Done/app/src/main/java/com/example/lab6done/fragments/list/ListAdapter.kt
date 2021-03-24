package com.example.lab6done.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6done.R
import com.example.lab6done.model.Students
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var studentList = emptyList<Students>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = studentList[position]
        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.fname_txt.text = currentItem.Name
        holder.itemView.age_txt.text = currentItem.age.toString()
        holder.itemView.comment_txt.text = currentItem.AboutSelf
        holder.itemView.rate_txt.text = currentItem.rating.toString()


        holder.itemView.rowLayout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(students: List<Students>){
        this.studentList = students
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return studentList.size
    }
}