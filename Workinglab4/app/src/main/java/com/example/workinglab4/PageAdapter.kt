package com.example.workinglab4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.page_item.view.*


class PageAdapter(private val pageList: List<PageItem>) : RecyclerView.Adapter<PageAdapter.PageViewHolder>() {
    var checkedItems = ArrayList<PageItem>()
    class PageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener, View.OnLongClickListener{
        var imageView: ImageView = itemView.image_view
        var textView1: TextView = itemView.text_view_1
        var textView2: TextView = itemView.text_view_2
        var textView3: TextView = itemView.text_view_3
        var cbox: CheckBox = itemView.findViewById(R.id.cb)
        var cd: CardView = itemView.findViewById(R.id.push)

        private lateinit var myItemClickListener: ItemCLickListener
        init { cbox.setOnClickListener(this)
                cd.setOnLongClickListener(this)}


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
            Toast.makeText(cd.context, textView1.text,Toast.LENGTH_SHORT).show()
            return true
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.page_item, parent, false)

        return PageViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        var currentItem = pageList[position]
        holder.imageView.setImageResource(pageList[position].imageResource)
        holder.textView1.text = pageList[position].text1
        holder.textView2.text = pageList[position].text2
        holder.textView3.text = pageList[position].text3
        holder.cbox.isChecked = currentItem.ready




        holder.setItemClickListener(object: PageViewHolder.ItemCLickListener {
            override fun onItemClick(itemView: View, pos: Int) {
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


        })
    }

    override fun getItemCount() = pageList.size


}