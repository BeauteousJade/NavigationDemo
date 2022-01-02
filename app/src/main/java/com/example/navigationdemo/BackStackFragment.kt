package com.example.navigationdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class BackStackFragment : Fragment() {

    companion object {
        const val KEY_INDEX = "KEY_INDEX"
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_child, container, false)
        val textView = view.findViewById<TextView>(R.id.textView)
        textView.text = "我是BackStackFragment${arguments?.getInt(KEY_INDEX) ?:0}"
        return view
    }
}