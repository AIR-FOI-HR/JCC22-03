package com.example.caraiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.databinding.DataBindingUtil
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.caraiapp.databinding.ActivityLogsFeedBinding
import com.example.caraiapp.recyclerview.LogsFeedRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogsFeedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        viewModel.fetchLogsFeedByCarId("car_id_1")

        val binding: ActivityLogsFeedBinding
        = DataBindingUtil.setContentView(this, R.layout.activity_logs_feed)

        //val viewModel: LogsFeedViewModel by viewModels { LogsFeedViewModel.Factory }
        //val viewModel : LogsFeedViewModel = ViewModelProvider(this)[LogsFeedViewModel::class.java]


        binding.lifecycleOwner = this


        val logsFeedAdapter = LogsFeedRecyclerViewAdapter()
        binding.recyclerView.adapter = logsFeedAdapter


         /*viewModel.logsFeedLiveData.observe(this){logItems->
             logsFeedAdapter.setItems(logItems)
         }*/
    }

    @Composable
    fun getViewModel(): LogsFeedViewModel {
        return hiltViewModel<LogsFeedViewModel>()
    }
}

