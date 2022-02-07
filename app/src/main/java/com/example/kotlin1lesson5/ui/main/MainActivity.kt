package com.example.kotlin1lesson5.ui.main

import android.content.Intent
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin1lesson5.base.BaseActivity
import com.example.kotlin1lesson5.base.BaseViewModel
import com.example.kotlin1lesson5.databinding.ActivityMainBinding
import com.example.kotlin1lesson5.models.Items
import com.example.kotlin1lesson5.ui.details.DetailsActivity
import com.example.kotlin1lesson5.ui.internet.Connect

class MainActivity : BaseActivity<BaseViewModel, ActivityMainBinding>() {

    private var list = mutableListOf<Items>()
    private val adapterMain: MainAdapter by lazy {
        MainAdapter(list, this::onClick)
    }

    private fun onClick(id: String) {
        Intent(
            this@MainActivity, DetailsActivity::class.java
        ).apply {
            putExtra("key", id)
            startActivity(this)
        }
    }

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.playlists().observe(this) {
            initAdapter(it.items as MutableList<Items>)
        }
    }

    override fun initView() {
        super.initView()
        checkInternet()
        binding.checkInet.btnTryAgain.setOnClickListener {
            checkInternet()
        }
    }

    private fun checkInternet() {
        Connect(this).observe(this, {
            if (it) {
                binding.checkInet.connectInet.isVisible = false
                binding.recyclerView.isVisible = true
            } else {
                binding.checkInet.connectInet.isVisible = true
                binding.recyclerView.isVisible = false
            }
        })
    }

    private fun initAdapter(list: MutableList<Items>) {
        this.list = list
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterMain
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)

    }
}