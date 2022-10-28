package com.example.jakewhartongithubrepositories.core.persentation.screens

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.basemvi.core.persentation.viewmodel.*
import com.example.jakewhartongithubrepositories.core.persentation.viewmodel.Action
import com.example.jakewhartongithubrepositories.core.persentation.viewmodel.BaseResult
import com.example.jakewhartongithubrepositories.core.persentation.viewmodel.ViewEvent
import com.example.jakewhartongithubrepositories.core.persentation.viewmodel.ViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseFragment<STATE : ViewState, EVENT : ViewEvent, ACTION : Action, RESULT : BaseResult>(
    @LayoutRes layoutId: Int,
) : Fragment(layoutId) {

    abstract val viewModel: BaseViewModel<STATE, EVENT, ACTION, RESULT>

    private var onBackPressedCallback: OnBackPressedCallback? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { handleViewState(it) }
            }
        }
    }

    /**
     * abstract function need to be implemented, used to receive new [ViewState] from the view model
     *
     * @param state is the new state of the ui to be handled
     */
    abstract fun handleViewState(state: STATE)

    /**
     * function used to initialize view events listener
     *
     * @param handleViewEvent callback function to receive new [ViewEvent] from the view model
     */
    fun initViewEvents(handleViewEvent: (event: EVENT) -> Unit) {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect { handleViewEvent(it) }
            }
        }
    }

    fun addOnBackPressed(
        isEnabled: Boolean = false,
        onBackPressed: () -> Unit = { requireActivity().finish() },
    ) {
        onBackPressedCallback?.isEnabled = isEnabled

        if (onBackPressedCallback == null) {
            onBackPressedCallback =
                object : OnBackPressedCallback(isEnabled /* disabled by default */) {
                    override fun handleOnBackPressed() {
                        onBackPressed.invoke()
                    }
                }
            requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback!!)
        }
    }
}