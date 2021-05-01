package com.example.hiltex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltex.data.Todo
import com.example.hiltex.databinding.ViewHolderBinding
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * Adapter for the task list. Has a reference to the [TasksViewModel] to send actions back to it.
 */
class MemoAdapter(private val viewModel: MainViewModel) :
    ListAdapter<Todo, MemoAdapter.ViewHolder>(TaskDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: ViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: MainViewModel, item: Todo) {

            binding.item = item
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewHolderBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class TaskDiffCallback : DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }
}

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Todo>?) {
    items?.let {
        (listView.adapter as MemoAdapter).submitList(items)
    }
}
