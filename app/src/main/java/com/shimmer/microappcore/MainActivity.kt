package com.shimmer.microappcore

import android.view.LayoutInflater
import android.view.View
import com.shimmer.microappcore.databinding.ActivityMainBinding
import com.shimmer.microcore.core.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun setView(): View {
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        return binding.root
    }

    override fun init() {
        TODO("Not yet implemented")
    }
}
