package com.example.caraiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.caraiapp.databinding.ActivityLogsFeedBinding
import com.example.caraiapp.recyclerview.LogsFeedRecyclerViewAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLogsFeedBinding
        = DataBindingUtil.setContentView(this, R.layout.activity_logs_feed)

        val viewModel : LogsFeedViewModel = ViewModelProvider(this)[LogsFeedViewModel::class.java]
        binding.lifecycleOwner = this
        binding.logsViewModel = viewModel

        val logsFeedAdapter = LogsFeedRecyclerViewAdapter()
        binding.recyclerView.adapter = logsFeedAdapter

         viewModel.fetchLogsFeed()
         viewModel.logsFeedLiveData.observe(this){logItems->
             logsFeedAdapter.setItems(logItems)
         }
    }
}