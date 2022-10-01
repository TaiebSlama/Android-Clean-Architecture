package com.slama.cleanarchitecture.helper.base.viewModel

import android.annotation.SuppressLint
import android.content.Intent
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.slama.cleanarchitecture.helper.base.activity.BaseActivity
import com.slama.cleanarchitecture.helper.base.fragment.BaseFragment


abstract class BaseViewModel<VDB : ViewDataBinding> :
    LifecycleObserver, ViewModel() {

    private lateinit var viewDataBinding: VDB

    @SuppressLint("StaticFieldLeak")
    var activity: BaseActivity<*>? = null
    var fragment: BaseFragment? = null

    fun setDataBinding(viewDataBinding: ViewDataBinding?) {
        if (viewDataBinding != null) {
            this.viewDataBinding = viewDataBinding as VDB
            configDataBinding(viewDataBinding)
        }
    }


    abstract fun configDataBinding(bindingView: VDB)
    open fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {}
    open fun onActivityResultCanceled() {}
    open fun onPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
    }

    open fun onResume() {}

    open fun nativeGoBackClicked() {}

    open fun onPermissionsResultCanceled() {}

    open fun onAttachFragment() {}

}