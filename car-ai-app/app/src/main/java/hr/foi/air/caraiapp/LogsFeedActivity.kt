package hr.foi.air.caraiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import hr.foi.air.caraiapp.databinding.ActivityLogsFeedBinding
import hr.foi.air.caraiapp.recyclerview.LogsFeedRecyclerViewAdapter


class LogsFeedActivity : AppCompatActivity() {
//val listLogFeed = listOf<LogFeed>(ListLogFeed(), ....)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLogsFeedBinding
        = DataBindingUtil.setContentView(this, R.layout.activity_logs_feed)

        //val viewModel: LogsFeedViewModel by viewModels { LogsFeedViewModel.Factory }
        //val viewModel : LogsFeedViewModel = ViewModelProvider(this)[LogsFeedViewModel::class.java]
        val databaseProvider = DatabaseProvider()
        val repository = databaseProvider.provideDatabase()
        val viewModel : LogsFeedViewModel = LogsFeedViewModel(repository)

        binding.lifecycleOwner = this
        binding.logsViewModel = viewModel

        val logsFeedAdapter = LogsFeedRecyclerViewAdapter()
        binding.recyclerView.adapter = logsFeedAdapter

         viewModel.fetchLogsFeedByCarId("car_id_1")
         /*viewModel.logsFeedLiveData.observe(this){logItems->
             logsFeedAdapter.setItems(logItems)
         }*/
    }
}

