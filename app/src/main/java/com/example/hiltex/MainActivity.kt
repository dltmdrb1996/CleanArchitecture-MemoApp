package com.example.hiltex

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.hiltex.databinding.ActivityMainBinding
import com.example.hiltex.repository.MemoRepository
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception
import java.lang.NullPointerException
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    lateinit var binding: ActivityMainBinding
    private lateinit var listAdapter: MemoAdapter
    @Inject lateinit var memoRepository: MemoRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        toastError()
        setupListAdapter()
    }

    private fun setupListAdapter() {
        val viewModel = binding.viewModel
        if (viewModel != null) {
            listAdapter = MemoAdapter(viewModel)
            binding.recyclerView.adapter = listAdapter
        } else {
            Log.e("ViewModel not initialized when attempting to set up adapter.", "err")
        }
    }

    private fun toastError() {
        viewModel.showErrorToast.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, "메모를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        })
    }
}