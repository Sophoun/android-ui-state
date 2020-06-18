package com.sophoun.app.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.sophoun.app.application.MyApplication
import com.sophoun.app.view.fragment.FirstFragment
import com.sophoun.app.view.fragment.RootHomeFragment
import com.sophoun.app.view.fragment.SecondFragment
import com.sophoun.ui.state.BaseActivity
import kotlin.collections.HashMap

abstract class AppBaseActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        this.fragmentFactory = (application as MyApplication).fragmentFactory
        super.onCreate(savedInstanceState)
    }
}