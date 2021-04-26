package com.example.hiltex

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltex.data.Todo
import com.example.hiltex.databinding.ActivityMainBinding
import com.example.hiltex.databinding.ViewHolderBinding
import com.example.hiltex.generated.callback.OnClickListener

class TodoAdapter(listener : OnItemClick) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    private val mCallback = listener
    private val items = ArrayList<Todo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : TodoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderBinding.inflate(layoutInflater)
        return TodoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setList(todo: List<Todo>) {
        items.clear()
        items.addAll(todo)
    }

    inner class TodoViewHolder(private val binding: ViewHolderBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(item: Todo){
            binding.tvTodo.text = item.title
            binding.ivIcon.setOnClickListener {
                mCallback.deleteTodo(item)
            }
        }
    }

}
interface OnItemClick {
    fun deleteTodo(todo: Todo)
}