package com.sophoun.app.view.viewmodel

import com.sophoun.app.view.state.FirstFragmentState
import com.sophoun.ui.state.BaseViewModel

class SampleViewModel : BaseViewModel() {

    fun getString() {
        setState(FirstFragmentState("Hello world!"))
    }
}