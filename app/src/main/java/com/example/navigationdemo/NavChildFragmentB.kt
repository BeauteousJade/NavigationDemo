package com.example.navigationdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class NavChildFragmentB : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_child, container, false)
        val textView = view.findViewById<TextView>(R.id.textView)
        textView.text = "我是NavChildFragmentB"
        view.setOnClickListener {
            findNavController().navigate(R.id.action_child_b_to_a_by_popUp)
        }
        return view
    }
}