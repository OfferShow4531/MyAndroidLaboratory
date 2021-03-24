package com.example.laba5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val pageList: List<PageItem>, private val findNavController: NavController) : RecyclerView.Adapter<Adapter.PageViewHolder>() {
    var checkedItems = ArrayList<PageItem>()
    class PageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener{
        var imageView: ImageView = itemView.findViewById(R.id.image_view)
        var textView1: TextView = itemView.findViewById(R.id.text_view_1)
        var textView2: TextView = itemView.findViewById(R.id.text_view_2)
        var textView3: TextView = itemView.findViewById(R.id.text_view_3)
        var cbox: CheckBox = itemView.findViewById(R.id.cb)
        var cd: CardView = itemView.findViewById(R.id.push)

        private lateinit var myItemClickListener: ItemCLickListener
        init {
            cbox.setOnClickListener(this)
            cd.setOnClickListener(this)
        }


        fun setItemClickListener(ic: ItemCLickListener) {
            this.myItemClickListener = ic
        }

        override fun onClick(v: View?) {
            if(v != null )
                this.myItemClickListener.onItemClick(v, layoutPosition)
        }




        interface ItemCLickListener {
            fun onItemClick(itemView: View, pos: Int)

        }
        override fun onLongClick(v: View?): Boolean {
            Toast.makeText(cd.context, textView1.text, Toast.LENGTH_SHORT).show()
            return true
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.page_item, parent, false)

        return PageViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        val currentItem = pageList[position]
        holder.imageView.setImageResource(pageList[position].imageResource)
        holder.textView1.text = pageList[position].text1
        holder.textView2.text = pageList[position].text2
        holder.textView3.text = pageList[position].text3
        holder.cbox.isChecked = currentItem.ready




        holder.setItemClickListener(object: PageViewHolder.ItemCLickListener {
            override fun onItemClick(itemView: View, pos: Int) {
                when(itemView.id) {
                    R.id.push -> {
                        findNavController.navigate(
                                R.id.action_recyclerFragment_to_description,
                                bundleOf(
                                        "image" to pageList[pos].imageResource,
                                        "student" to pageList[pos].text1,
                                        "id" to pageList[pos].text2,
                                        "random" to pageList[pos].text3
                                )
                        )
                    }
                    R.id.cb -> {
                        val checking = itemView as CheckBox
                        val currentPosition = pageList[pos]
                        if (checking.isChecked) {
                            currentPosition.ready = true
                            checkedItems.add(currentPosition)
                        } else if(!checking.isChecked) {
                            currentPosition.ready = false
                            checkedItems.remove(currentPosition)
                        }
                    }
                }
            }
        })
    }

    override fun getItemCount() = pageList.size

}