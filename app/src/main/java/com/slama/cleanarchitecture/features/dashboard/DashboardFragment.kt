package com.slama.cleanarchitecture.features.dashboard

import com.slama.cleanarchitecture.R
import com.slama.cleanarchitecture.helper.base.fragment.BaseFragment
import com.slama.cleanarchitecture.helper.base.viewModel.BaseViewModel

/**
 * Created by Slama Taieb (slama.taieb.contact@gmail.com)
 * Date : 10/1/2022
 * []
 */

class DashboardFragment : BaseFragment(R.layout.fragment_dashboard) {
    override fun setUpUseCase(): BaseViewModel<*> {
        return DashboardViewModel()
    }

}