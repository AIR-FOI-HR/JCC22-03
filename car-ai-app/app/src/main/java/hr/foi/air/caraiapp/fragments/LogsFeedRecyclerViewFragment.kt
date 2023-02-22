package hr.foi.air.caraiapp.fragments

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import hr.foi.air.caraiapp.LogsFeedViewModel
import hr.foi.air.caraiapp.R
import hr.foi.air.caraiapp.core.DataPresenter
import hr.foi.air.caraiapp.databinding.FragmentRecyclerViewBinding
import hr.foi.air.caraiapp.recyclerview.LogsFeedRecyclerViewAdapter
import hr.foi.air.database.DatabaseProvider

class LogsFeedRecyclerViewFragment : Fragment(), DataPresenter {
    private lateinit var binding : FragmentRecyclerViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayData()
    }

    private fun displayData() {
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

    override fun getIcon(context: Context): Drawable {
        return AppCompatResources.getDrawable(context, android.R.drawable.ic_media_play)!!
    }

    override fun getName(context: Context): String {
        return context.getString(R.string.logs_recycler_view_presenter)
    }
    override fun getFragment(): Fragment {
        return this
    }
}