package com.example.jakewhartongithubrepositories.core.persentation.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.jakewhartongithubrepositories.R


/**
 * clear navigation graph back stack and then navigate to destination
 * */
fun Fragment.clearBackStackAndNavigate(
    @IdRes graphID: Int,
    @IdRes navId: Int,
    args: Bundle? = null,
    inclusive: Boolean = false,
) {


    findNavController().navigate(
        navId, args, NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_right)
            .setPopEnterAnim(R.anim.slide_in_left)
            .setExitAnim(R.anim.slide_out_left)
            .setPopExitAnim(R.anim.slide_out_right)
            .setPopUpTo(graphID, inclusive)
            .build()
    )

}

/**
 * use default navController method to navigate in the app   and attaching default nav options
 */
fun Fragment.navigateWithId(
    @IdRes navId: Int,
    args: Bundle? = null,
    isSrcNested: Boolean? = false
) {
    if (isSrcNested == true) {
        activity?.findNavController(R.id.nav_host_fragment)
            ?.navigate(navId, args, getDefaultNavOptions())
    } else {
        findNavController().navigate(navId, args, getDefaultNavOptions())
    }
}

/**
 * use default navController method to navigate in the app using action and attaching default nav options
 */
fun Fragment.navigateWithAction(
    navOptions: NavDirections,
    isSrcNested: Boolean? = false
) {
    if (isSrcNested == true) {
        activity?.findNavController(R.id.nav_host_fragment)
            ?.navigate(navOptions, getDefaultNavOptions())
    } else {
        findNavController().navigate(navOptions, getDefaultNavOptions())
    }
}


/**
 * use default navController method to pop in the app and inclusive pop is false by default
 */
fun Fragment.popWithIdTo(@IdRes navId: Int, inclusive: Boolean = false) {
    findNavController().popBackStack(navId, inclusive)
}

/**
 * use default navController method to pop to the previous fragment in the stack
 */
fun Fragment.navigateUp() {
    findNavController().navigateUp()
}


/**
 * use navigate With Id method to navigate in the app and attach fading animation
 */
fun Fragment.navigateByIdWithFadeAnim(
    @IdRes navId: Int,
    args: Bundle? = null,
    isSrcNested: Boolean? = false
) {
    if (isSrcNested == true) {
        activity?.findNavController(R.id.nav_host_fragment)
            ?.navigate(navId, args, getFadeAnim())
    } else {
        findNavController().navigate(navId, args, getFadeAnim())
    }

}

/**
 * Fading in and out Animation
 */
private fun getFadeAnim() = navOptions {
    anim {
        enter = R.anim.fade_in
        exit = R.anim.fade_out
        popEnter = R.anim.fade_in
        popExit = R.anim.fade_out
    }
}

/**
 * Method provides default navOptions that will be applied on all of the navigation
 */
private fun getDefaultNavOptions() = navOptions {
    anim {
        enter = R.anim.slide_in_right
        exit = R.anim.slide_out_left
        popEnter = R.anim.slide_in_left
        popExit = R.anim.slide_out_right
    }
}