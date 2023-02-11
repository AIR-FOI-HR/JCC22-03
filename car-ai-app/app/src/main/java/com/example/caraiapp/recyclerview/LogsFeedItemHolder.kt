package com.example.caraiapp.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.caraiapp.R
import com.example.caraiapp.databinding.ViewHolderLogsItemBinding
import com.example.database.entities.Logs

class LogsFeedItemHolder(parent: ViewGroup): RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
    .inflate(R.layout.view_holder_logs_item,parent,false))
{
    private val binding = ViewHolderLogsItemBinding.bind(itemView)
    fun onBind(logFeedItem: Logs){

        binding.logTime = logFeedItem.time
        binding.logAction = logFeedItem.action
    }
}