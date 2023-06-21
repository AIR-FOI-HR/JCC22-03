package hr.foi.air.caraiapp.fragments

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
import hr.foi.air.caraiapp.LogsFeedViewModel
import hr.foi.air.caraiapp.R
import hr.foi.air.caraiapp.core.DataPresenter
import hr.foi.air.caraiapp.databinding.FragmentRecyclerViewBinding
import hr.foi.air.caraiapp.recyclerview.LogsFeedRecyclerViewAdapter

class LogsFeedRecyclerViewFragment : Fragment(), DataPresenter {

    private val viewModel: LogsFeedViewModel by viewModels()
    private val logsFeedAdapter = LogsFeedRecyclerViewAdapter()

    private lateinit var binding: FragmentRecyclerViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupUi()

        viewModel.fetchCarOwners("mmusica")
    }

    private fun setupObservers() {
        viewModel.logsFeedLiveData.observe(viewLifecycleOwner) { logItems ->
            logsFeedAdapter.submitList(logItems)
            binding.recyclerView.scrollToPosition(logsFeedAdapter.itemCount)
        }
        viewModel.carOwnersLiveData.observe(viewLifecycleOwner) { carOwners ->
            viewModel.fetchCars(carOwners)
        }
        viewModel.carsLiveData.observe(viewLifecycleOwner) { cars ->
            binding.spinner.setupAdapterAndListener(titles = cars.map { it.name })
        }
    }

    private fun setupUi() {
        binding.recyclerView.adapter = logsFeedAdapter
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