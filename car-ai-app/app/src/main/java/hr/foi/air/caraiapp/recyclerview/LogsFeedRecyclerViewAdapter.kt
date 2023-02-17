package hr.foi.air.caraiapp.recyclerview

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.foi.air.database.entities.Logs

class LogsFeedRecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    private val logFeedItems = mutableListOf<Logs>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LogsFeedItemHolder(parent)
    }

    override fun getItemCount(): Int {
        return logFeedItems.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as LogsFeedItemHolder).onBind(logFeedItems[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(logFeedItems: List<Logs>?){
        this.logFeedItems.clear()
        this.logFeedItems.addAll(logFeedItems ?: emptyList())
        notifyDataSetChanged()
    }
}