package com.example.hiltmvvmflowmoshi.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hiltmvvmflowmoshi.adapters.DogsAdapter
import com.example.hiltmvvmflowmoshi.adapters.LoadingStateAdapter
import com.example.hiltmvvmflowmoshi.databinding.ActivityMainBinding
import com.example.hiltmvvmflowmoshi.ui.main.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var dogsAdapter: DogsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapters()
        lifecycleScope.launchWhenCreated {
            viewModel.getAllDogs().collectLatest { response->
                binding.apply {
                    recyclerView.isVisible = true
                    progress.isVisible = false
                }
                dogsAdapter.submitData(response)
            }

        }
    }

    private fun initAdapters() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = dogsAdapter.withLoadStateHeaderAndFooter(
                header = LoadingStateAdapter{dogsAdapter.retry()},
                footer = LoadingStateAdapter{dogsAdapter.retry()}
            )
        }
    }
}