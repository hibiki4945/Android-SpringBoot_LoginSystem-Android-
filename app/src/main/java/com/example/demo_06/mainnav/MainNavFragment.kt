package com.example.demo_06.mainnav

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.demo_06.R
import com.example.demo_06.base.BaseFragment
import com.example.demo_06.databinding.FragmentMainNavBinding

class MainNavFragment: BaseFragment<FragmentMainNavBinding, ViewModel>(
    FragmentMainNavBinding::inflate,
    viewModelClass = null
){
    override fun initFragment(
        binding: FragmentMainNavBinding,
        viewModel: ViewModel?,
        savedInstanceState: Bundle?
    ) {
        (childFragmentManager.findFragmentById(R.id.main_view_nav) as NavHostFragment).apply {
            binding.bottomNav.setupWithNavController(this.navController)
        }
    }
}