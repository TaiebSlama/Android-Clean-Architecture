package com.slama.cleanarchitecture.helper.base.activity

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.slama.cleanarchitecture.application.appModules
import com.slama.cleanarchitecture.helper.base.viewModel.BaseViewModel
import com.slama.cleanarchitecture.helper.shared.Constant
import com.slama.cleanarchitecture.helper.useCase.UseCase
import org.koin.core.context.loadKoinModules


abstract class BaseActivity<VM : BaseViewModel<*>>(val viewModel: VM, private val layoutId: Int) :
    AppCompatActivity(), LifecycleOwner, DialogInterface.OnDismissListener {

    var bindingView: ViewDataBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.activity = this
        val root = LayoutInflater.from(this).inflate(layoutId, null, false)
        setContentView(root)
        bindingView = DataBindingUtil.bind(root)
        viewModel.setDataBinding(bindingView)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        Log.d("BaseActivityDialog", "BaseActivityDismiss")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode <= 100) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                viewModel.onActivityResult(requestCode, resultCode, data)
            } else if (resultCode == Activity.RESULT_CANCELED) {
                viewModel.onActivityResultCanceled()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
        Log.d("BaseActivityDialog", "onResume Activity")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode <= Constant.AndroidHelper.MAX_CODE_INTENT_FOR_ACTIVITY) {
            if (grantResults.contains(PackageManager.PERMISSION_DENIED)) {
                viewModel.onPermissionsResultCanceled()
            } else {
                viewModel.onPermissionsResult(requestCode, permissions, grantResults)
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return setSupportNavigateUp()
    }

    fun navigateWithIdDestination(idDestination: Int, useCase: UseCase = UseCase.DEFAULT) {
        val navController: NavController = Navigation.findNavController(this, getNavHostId())
        val bundle = Bundle()
        bundle.putSerializable(Constant.Global.NAVIGATION_USE_CASE_KEY, useCase)
        navController.navigate(idDestination, bundle, null)
    }

    abstract fun setSupportNavigateUp(): Boolean

    abstract fun getNavHostId(): Int

    override fun onBackPressed() {
        viewModel.nativeGoBackClicked()
    }

    init {
        loadKoinModules(module = appModules)
    }

}