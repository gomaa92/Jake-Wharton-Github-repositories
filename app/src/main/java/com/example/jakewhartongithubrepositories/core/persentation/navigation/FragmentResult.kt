package com.example.jakewhartongithubrepositories.core.persentation.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

const val NAVIGATION_RESULT = "navigation result"
fun <T> Fragment.getNavigationResult(key: String = NAVIGATION_RESULT) =
    findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)

fun <T> Fragment.setNavigationResult(result: T, key: String = NAVIGATION_RESULT) {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, result)
}