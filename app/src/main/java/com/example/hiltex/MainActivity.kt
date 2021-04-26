package com.example.hiltex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hiltex.data.Todo
import com.example.hiltex.repository.MemoRepository
import com.example.hiltex.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , OnItemClick{
    private val viewModel by viewModels<MainViewModel>()
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TodoAdapter
    @Inject lateinit var memoRepository: MemoRepository
    @Inject lateinit var memoRepository2: MemoRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        initRecyclerView()

        viewModel.getAll().observe(this, Observer{
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun initRecyclerView(){
        binding.tasksList.layoutManager = LinearLayoutManager(this)
        adapter = TodoAdapter(this)
        binding.tasksList.adapter = adapter
    }

    override fun deleteTodo(todo: Todo) {
        lifecycleScope.launch(Dispatchers.IO){
            viewModel.nukeTable()
        }
    }
}