package hr.foi.air.caraiapp.logsfeed.ui.fragments

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import hr.foi.air.caraiapp.core.DataPresenter
import hr.foi.air.caraiapp.core.getLoggedInUser
import hr.foi.air.caraiapp.logsfeed.R
import hr.foi.air.caraiapp.logsfeed.databinding.FragmentConsoleViewBinding
import hr.foi.air.caraiapp.logsfeed.ui.viewmodels.LogsFeedViewModel
import hr.foi.air.database.DAO
import hr.foi.air.database.FirebaseRepository
import hr.foi.air.database.entities.Car
import hr.foi.air.database.entities.CarOwner
import hr.foi.air.database.entities.LogsFeed

class LogsFeedConsoleViewFragment : Fragment(), DataPresenter {

    private val viewModel: LogsFeedViewModel by viewModels()

    private lateinit var repository: DAO
    private lateinit var binding: FragmentConsoleViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentConsoleViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRepository(FirebaseRepository)
        setData(viewModel.logsFeedLiveData, viewModel.carOwnersLiveData, viewModel.carsLiveData);
        viewModel.fetchCarOwners(activity?.getLoggedInUser().orEmpty(),repository)
    }

    private fun Spinner.setupAdapterAndListener(titles: List<String>) {
        adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_dropdown_item,
            titles,
        )
        setSelection(selectedItemPosition)

        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) =
                viewModel.onCarSelected(parent?.getItemAtPosition(position) as String)

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun getIcon(context: Context): Drawable =
        AppCompatResources.getDrawable(context, android.R.drawable.ic_media_next)!!

    override fun getName(context: Context) = context.getString(R.string.logs_console_view_presenter)

    override fun getFragment(): Fragment = this

    override fun setData(
        logsFeedData: LiveData<List<LogsFeed>>,
        carOwnersLiveData: LiveData<List<CarOwner>>,
        carsLiveData: LiveData<List<Car>>
    ) {
        logsFeedData.observe(viewLifecycleOwner) { logItems ->
            val logText = logItems.map { logItem ->
                getString(R.string.log_template, logItem.time, logItem.action)
            }.joinToString(separator = "\n")

            binding.console.setText(logText)
        }
        carOwnersLiveData.observe(viewLifecycleOwner) { carOwners ->
            viewModel.fetchCars(carOwners, repository)
        }
        carsLiveData.observe(viewLifecycleOwner) { cars : List<Car> ->
            binding.spinner.setupAdapterAndListener(titles = cars.map { it.carName })
        }
    }

    override fun setRepository(repository: DAO) {
        this.repository = repository
    }
}
