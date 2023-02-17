package hr.foi.air.caraiapp.recyclerview

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import hr.foi.air.database.entities.Logs

@BindingAdapter("setItems")
fun setItems(recyclerView: RecyclerView, list: List<Logs>?){
    (recyclerView.adapter as LogsFeedRecyclerViewAdapter).setItems(list)
}