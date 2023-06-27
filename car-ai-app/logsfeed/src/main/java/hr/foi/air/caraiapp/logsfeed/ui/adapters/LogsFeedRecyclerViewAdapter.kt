package hr.foi.air.caraiapp.logsfeed.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hr.foi.air.caraiapp.logsfeed.databinding.ViewHolderLogsItemBinding
import hr.foi.air.database.entities.LogsFeed

private object LogsFeedDiffUtilCallback : DiffUtil.ItemCallback<LogsFeed>() {
    override fun areItemsTheSame(oldItem: LogsFeed, newItem: LogsFeed) = oldItem == newItem
    override fun areContentsTheSame(oldItem: LogsFeed, newItem: LogsFeed) = oldItem == newItem
}

class LogsFeedRecyclerViewAdapter :
    ListAdapter<LogsFeed, LogsFeedRecyclerViewAdapter.ViewHolder>(LogsFeedDiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderLogsItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(logsFeed = getItem(position))

    class ViewHolder(
        private val binding: ViewHolderLogsItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(logsFeed: LogsFeed) {
            binding.apply {
                logTimeText.text = logsFeed.time
                logActionText.text = logsFeed.action
            }
        }
    }
}