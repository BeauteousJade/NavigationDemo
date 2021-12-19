package com.example.navigationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val hostFragment = NavHostFragment.create(R.navigation.default_graph)
        supportFragmentManager.beginTransaction()
            .add(R.id.nav_host_fragment_container, hostFragment)
            // 只有设置这个属性，NavHostFragment 才能成功拦截系统的back事件。
            .setPrimaryNavigationFragment(hostFragment)
            .commitAllowingStateLoss()
    }
}