package com.slama.cleanarchitecture.features.main

import androidx.navigation.Navigation
import com.slama.cleanarchitecture.R
import com.slama.cleanarchitecture.helper.base.activity.BaseActivity

class MainActivity : BaseActivity<MainViewModel>(MainViewModel(), R.layout.activity_main) {
    override fun setSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, getNavHostId()).navigateUp()
    }

    override fun getNavHostId(): Int {
        return R.id.activity_main_nav_host_fragment
    }

}