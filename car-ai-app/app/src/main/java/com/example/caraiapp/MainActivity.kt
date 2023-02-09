package com.example.caraiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.caraiapp.databinding.ActivityLogsFeedBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding: ActivityLogsFeedBinding = DataBindingUtil.setContentView(this, R.layout.activity_logs_feed)
        binding.carLogText = "First Car Log Text"

    }
}