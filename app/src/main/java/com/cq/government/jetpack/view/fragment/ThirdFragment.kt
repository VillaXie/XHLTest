package com.cq.government.jetpack.view.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.cq.government.jetpack.R
import com.cq.government.jetpack.view.fragment.animation.RotateFragment
import com.cq.government.jetpack.view.fragment.animation.ScaleFragment
import com.cq.government.jetpack.view.fragment.animation.TranslateFragment
import com.cq.government.jetpack.viewmodel.ThirdViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.third_fragment.*
import kotlin.math.log

class ThirdFragment : Fragment() {

    companion object {
        fun newInstance() = ThirdFragment()
    }

    private lateinit var viewModel: ThirdViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.third_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ThirdViewModel::class.java)
        viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = 3

            override fun createFragment(position: Int) = when (position) {
                0 -> ScaleFragment()
                1 -> RotateFragment()
                else -> TranslateFragment()
            }
        }
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = "缩放"
                1 -> tab.text = "旋转"
                else -> tab.text = "移动"
            }
        }.attach()
    }
}
