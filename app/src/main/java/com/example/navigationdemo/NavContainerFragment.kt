package com.example.navigationdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

class NavContainerFragment : Fragment() {

    private lateinit var mViewGroup: ViewGroup
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_container_nav, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewGroup = view.findViewById(R.id.viewGroup)
        addViewWithClickListener("跳转到NavChildFragmentA") {
            findNavController().navigate(R.id.action_to_child_a)
        }
        val newId = View.generateViewId();
        findNavController().currentDestination?.putAction(newId, R.id.fragment_nav_child_b)
        addViewWithClickListener("跳转到NavChildFragmentB") {
            findNavController().navigate(newId)
        }
        addViewWithClickListener("使用deepLink跳转到NavChildFragmentB") {
            findNavController().navigate("https://www.jade.com".toUri())
        }
        addViewWithClickListener("使用SafeArgs跳转到NavChildFragmentB") {
            findNavController().navigate(NavContainerFragmentDirections.actionToChildA())
        }
        addViewWithClickListener("使用deepLink跳转到SecondGraphFragment") {
            findNavController().navigate("http://www.second.com".toUri())
        }
    }


    private fun addViewWithClickListener(text: String, onClickListener: View.OnClickListener) {
        val button = Button(context).apply {
            this.text = text
            isAllCaps = false
            setOnClickListener(onClickListener)
        }
        mViewGroup.addView(
            button,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
    }
}