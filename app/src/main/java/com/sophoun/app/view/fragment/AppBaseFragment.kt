package com.sophoun.app.view.fragment

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sophoun.app.application.MyApplication
import com.sophoun.app.view.activity.AppBaseActivity
import com.sophoun.app.view.activity.MainActivity
import com.sophoun.ui.state.BaseFragment

abstract class AppBaseFragment : BaseFragment() {

    val fragmentFactory by lazy { (activity?.application as AppBaseActivity).fragmentFactory }

    override fun onAttach(context: Context) {
        this.viewModelFactory = (activity?.application as MyApplication).viewModelFactory
        super.onAttach(context)
    }

}