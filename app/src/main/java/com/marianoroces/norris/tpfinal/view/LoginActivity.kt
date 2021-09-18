package com.marianoroces.norris.tpfinal.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.marianoroces.norris.tpfinal.R
import com.marianoroces.norris.tpfinal.adapter.ViewPagerAdapter
import com.marianoroces.norris.tpfinal.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.lToolbar)

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        binding.lViewPager.adapter = viewPagerAdapter

        TabLayoutMediator(binding.lTabLayout, binding.lViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "LOGIN"
                1 -> tab.text = "REGISTRARSE"
            }
        }.attach()
    }
}