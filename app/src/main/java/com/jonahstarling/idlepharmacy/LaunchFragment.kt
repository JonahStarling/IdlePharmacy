package com.jonahstarling.idlepharmacy

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_launch.*

class LaunchFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_launch, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.viewTreeObserver.addOnGlobalLayoutListener(object:
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                animateLaunchScreenOut()
            }
        })
    }

    fun animateLaunchScreenOut() {
        val launchAnimator = ValueAnimator.ofFloat(1.0f, 0.0f)
        launchAnimator.duration = 500L
        launchAnimator.startDelay = 2000L
        launchAnimator.interpolator = AccelerateInterpolator()
        launchAnimator.addUpdateListener {
            pharmacyImage.alpha = launchAnimator.animatedValue as Float
            title.alpha = launchAnimator.animatedValue as Float
            subtitle.alpha = launchAnimator.animatedValue as Float
        }
        launchAnimator.addListener(object: AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                navigateToMainMenuFragment()
            }
        })
        launchAnimator.start()
    }

    fun navigateToMainMenuFragment() {
        (activity as MainActivity).replaceFragment(MainFragment.newInstance(), MainFragment.TAG)
    }

    companion object {
        val TAG = LaunchFragment::class.java.simpleName

        fun newInstance() = LaunchFragment()
    }
}