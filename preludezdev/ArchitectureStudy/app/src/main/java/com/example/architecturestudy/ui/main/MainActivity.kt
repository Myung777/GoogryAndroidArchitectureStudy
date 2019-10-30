package com.example.architecturestudy.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.architecturestudy.R
import com.example.architecturestudy.base.BaseActivity
import com.example.architecturestudy.databinding.ActivityMainBinding
import com.example.architecturestudy.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModel: MainViewModel by viewModel()
    private val vpAdapter = VpAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initViewPager()
        initEvent()
        initCallback()
    }

    private fun initViewModel() {
        binding.viewModel = mainViewModel
    }

    private fun initViewPager() {
        with(binding) {
            viewPager.adapter = vpAdapter
            viewPager.offscreenPageLimit = vpAdapter.count
            tabLayout.setupWithViewPager(viewPager)
        }
    }

    private fun initEvent() {
        binding.viewPager.addOnPageChangeListener(object : AbstractOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                reloadData(position)
            }
        })
    }

    private fun initCallback() {
        mainViewModel.filter.observe(this, Observer {
            for (position in 0 until vpAdapter.count) {
                vpAdapter.getFragment(position)?.sortData(it.first, it.second)
            }
        })

        mainViewModel.isReload.observe(this, Observer {
            reloadData(binding.viewPager.currentItem)
        })
    }

    private fun reloadData(position: Int) {
        vpAdapter.getFragment(position)?.reload(
            mainViewModel.type.value!!,
            mainViewModel.order.value!!
        )
    }
}