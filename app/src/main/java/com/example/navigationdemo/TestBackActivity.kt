package com.example.navigationdemo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

class TestBackActivity : AppCompatActivity() {

    private var index = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_back_stack)

        findViewById<View>(R.id.add).setOnClickListener {
            index++
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, BackStackFragment().apply {
                    arguments = Bundle().apply {
                        putInt(BackStackFragment.KEY_INDEX, index)
                    }
                }).addToBackStack("index=$index").commit()
        }
        findViewById<View>(R.id.back).setOnClickListener {
            supportFragmentManager.popBackStack(
                "index=$index",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
            index--
        }

    }
}