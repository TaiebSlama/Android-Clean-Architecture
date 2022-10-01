package com.slama.cleanarchitecture.features.main

import com.slama.cleanarchitecture.databinding.ActivityMainBinding
import com.slama.cleanarchitecture.helper.base.viewModel.BaseViewModel

/**
 * Created by Slama Taieb (slama.taieb.contact@gmail.com)
 * Date : 10/1/2022
 * []
 */

class MainViewModel : BaseViewModel<ActivityMainBinding>() {
    override fun configDataBinding(bindingView: ActivityMainBinding) {
        bindingView.viewModel = this
    }
}