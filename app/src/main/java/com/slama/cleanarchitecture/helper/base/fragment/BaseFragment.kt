package com.slama.cleanarchitecture.helper.base.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.slama.cleanarchitecture.helper.base.activity.BaseActivity
import com.slama.cleanarchitecture.helper.base.viewModel.BaseViewModel
import com.slama.cleanarchitecture.helper.shared.Constant
import com.slama.cleanarchitecture.helper.useCase.UseCase
import com.slama.cleanarchitecture.helper.utils.runOnMainThread

abstract class BaseFragment(private val layoutId: Int) : androidx.fragment.app.Fragment() {

    lateinit var viewModel: BaseViewModel<*>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = setUpUseCase()
        if (activity is BaseActivity<*>) {
            viewModel.activity = activity as BaseActivity<*>
            viewModel.fragment = this
        }
        viewModel.onAttachFragment()
    }

    override fun onStart() {
        super.onStart()
        dismissKeyBoard()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(this.layoutId, container, false)
        viewModel.setDataBinding(DataBindingUtil.bind(root))
        return root
    }

    override fun onResume() {
        super.onResume()
        Log.d("BaseActivityDialog", "onResume Fragment")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            viewModel.onActivityResult(requestCode, resultCode, data)
        } else {
            viewModel.onActivityResultCanceled()
        }
    }

    fun navigateWithIdDestination(idDestination: Int, useCase: UseCase = UseCase.DEFAULT) {
        runOnMainThread {
            dismissKeyBoard()
            val navController: NavController =
                Navigation.findNavController(this.requireActivity(), getNavHostId())
            val bundle = Bundle()
            bundle.putSerializable(Constant.Global.NAVIGATION_USE_CASE_KEY, useCase)
            navController.navigate(idDestination, bundle, null)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) = if (grantResults.contains(PackageManager.PERMISSION_DENIED)) {
        viewModel.onPermissionsResultCanceled()
    } else {
        viewModel.onPermissionsResult(requestCode, permissions, grantResults)
    }

    abstract fun setUpUseCase(): BaseViewModel<*>

    open fun getNavHostId(): Int {
        return viewModel.activity!!.getNavHostId()
    }

    private fun dismissKeyBoard() {
        view?.let {
            val imm =
                it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            it.clearFocus()
            requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
    }

}