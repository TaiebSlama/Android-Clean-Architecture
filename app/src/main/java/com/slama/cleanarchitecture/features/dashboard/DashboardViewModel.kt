package com.slama.cleanarchitecture.features.dashboard

import com.slama.cleanarchitecture.databinding.FragmentDashboardBinding
import com.slama.cleanarchitecture.helper.base.viewModel.BaseViewModel

/**
 * Created by Slama Taieb (slama.taieb.contact@gmail.com)
 * Date : 10/1/2022
 * []
 */

class DashboardViewModel : BaseViewModel<FragmentDashboardBinding>() {
    override fun configDataBinding(bindingView: FragmentDashboardBinding) {
        bindingView.viewModel = this
    }
}