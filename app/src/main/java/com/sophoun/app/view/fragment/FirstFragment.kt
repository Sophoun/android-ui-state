package com.sophoun.app.view.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.app.R
import com.sophoun.app.application.MyApplication
import com.sophoun.app.view.activity.MainActivity
import com.sophoun.app.view.state.FirstFragmentState
import com.sophoun.app.view.viewmodel.SampleViewModel
import com.sophoun.ui.state.BaseFragment
import com.sophoun.ui.state.state.UiState
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : BaseFragment() {

    private val sampleViewModel by lazy { (activity?.application as MyApplication).viewModelFactory?.create(SampleViewModel::class.java)!! }
    override fun layout(): Int = R.layout.fragment_first

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupWithViewModel(sampleViewModel)

        btn_open_second.setOnClickListener {
            sampleViewModel.getString()
        }
    }

    override fun onStateChanged(state: UiState) {
        super.onStateChanged(state)
        when(state) {
            is FirstFragmentState -> {
                count.text = state.string
            }
        }
    }
}