package com.example.laba5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_description.*


class Description : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("image")?.let { image_view.setImageResource(it) }
        arguments?.getString("student")?.let { text_view_1.text = it }
        arguments?.getString("id")?.let { text_view_2.text = it }
        arguments?.getString("random")?.let { text_view_3.text = it }
    }

}