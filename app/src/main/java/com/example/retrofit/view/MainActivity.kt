package com.example.retrofit.view

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.R
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.view.adapter.RecyclerAdapter
import com.example.retrofit.viewmodel.MainViewModel
import com.example.retrofit.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter:RecyclerAdapter by lazy {RecyclerAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, ViewModelFactory()).get(MainViewModel::class.java)
        setUpRecycler()
        checkInternet()

        binding.refresh.setOnRefreshListener { viewModel.getDataViewModel()
            checkInternet()}

        viewModel.data.observe(this, Observer {
            adapter.setList(it)
            layoutAnimation()
            binding.refresh.isRefreshing = false // получает данные и остан. обновление
        })


    }

    private fun checkInternet() {
        if(!viewModel.internet){
            binding.refresh.isRefreshing = false
            Snackbar.make(binding.recycler, "You have not internet", Snackbar.LENGTH_LONG).show()

        }
    }


    private fun layoutAnimation()  {
        val context = binding.recycler.context
        val layoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation)
        binding.recycler.layoutAnimation = layoutAnimationController
        binding.recycler.adapter?.notifyDataSetChanged()
        binding.recycler.scheduleLayoutAnimation()

    }

    private fun setUpRecycler() {
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter
    }
}