package com.sophoun.app.application

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sophoun.app.view.fragment.FirstFragment
import com.sophoun.app.view.fragment.RootHomeFragment
import com.sophoun.app.view.fragment.SecondFragment
import com.sophoun.app.view.viewmodel.SampleViewModel
import com.sophoun.ui.state.BaseViewModel


class MyApplication : Application() {

    var fragmentFactory = object : FragmentFactory() {

        val fragments = HashMap<String, Fragment>().apply {
            put(
                RootHomeFragment::class.java.name,
                RootHomeFragment()
            )
            put(
                FirstFragment::class.java.name,
                FirstFragment()
            )
            put(
                SecondFragment::class.java.name,
                SecondFragment()
            )
        }

        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
            return fragments[className] ?: super.instantiate(classLoader, className)
        }
    }

    val viewModelFactory = object : ViewModelProvider.Factory {
        val viewModels = HashMap<String, ViewModel>().apply {
            put(SampleViewModel::class.java.name, SampleViewModel())
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return viewModels[modelClass.name] as T
        }
    }

    override fun onCreate() {
        super.onCreate()
    }
}