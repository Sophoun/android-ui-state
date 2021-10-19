Deprecated!!!

Use [PAUL instead](https://github.com/Sophoun/paul)

# Android UI State

We create this library to easy update `Activity` or `Fragment` ui state.
And also easy to send message between service and `Activity` or
`Fragment`.

## How to use
### Import dependency
We host this library on `jcenter`. Make sure you have added `jcenter()`
repository to your `build.gradle` root project.
```groovy
allprojects {
    repositories {
        jcenter()
    }
}
```
Then import library dependency inside your `app` module:
```groovy
implementation 'com.github.sophoun:android-ui-state:0.0.1'
```

## Sample
In our library we force user to extend `Activity`, `Fragment`,
`ViewModel` or `Service` class from our class that provided.

If you add `Activity`, `Fragment` or `ViewModel` you need to extend from
class `BaseActivity`, `BaseFragment` or `BaseViewModel` and then set it
up with `ViewModel` that also extended from `BaseViewModel`.
### Example for Activity, Fragment and ViewModel
Activity:
```kotlin
class MyActivity : BaseActivity() {

    private val sampleViewModel by lazy { (application as MyApplication).viewModelFactory.create(SampleViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupWithViewModel(sampleViewModel)
        
        myBtn.setOnClickListener {
            sampleViewModel.getString()
        }
    }
    
    override fun onStateChanged(state: UiState) {
        super.onStateChanged(state)
        // Here is the place where you
        // handle the state changed
        // of [MyActivityState] class
    }
}
```
Fragment:
```kotlin
class MyFragment : BaseFragment() {

    private val sampleViewModel by lazy { (activity?.application as MyApplication).viewModelFactory?.create(SampleViewModel::class.java)!! }
    override fun layout(): Int = R.layout.fragment_first

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupWithViewModel(sampleViewModel)

        btn_open_second.setOnClickListener {
            sampleViewModel.getStringForFragment()
        }
    }

    override fun onStateChanged(state: UiState) {
        super.onStateChanged(state)
        // Here is the place where you
        // handle the state changed
        // of [MyFragmentState] class
    }
}
```

Inside your `ViewModel` class, you just extend from `BaseViewModel` and
then call `setState(uiState)` from wherever you want.

```kotlin
class SampleViewModel : BaseViewModel() {

    fun getString() {
        setState(MyActivityState("Hello world!"))
    }
    
    fun getStringForFragment() {
        setState(MyFragmentState("Hello Fragment!"))
    }
}
```
Inside `onStateChanged` it will be call based on ViewModel lifecycle and
the state class type that you cast to.

### Example for Service
TODO()


# Documentation
[Document references](documentation/android-ui-state/index.md)
