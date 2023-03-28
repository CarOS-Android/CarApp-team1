package com.thoughtworks.car.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.thoughtworks.ark.router.annotation.Scheme
import com.thoughtworks.car.Schemes
import com.thoughtworks.car.ui.theme.Theme
import dagger.hilt.android.AndroidEntryPoint

@Scheme(Schemes.HOME)
@AndroidEntryPoint
class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = ComposeView(requireContext()).apply {
        setContent {
            Theme {
                HomeScreen()
            }
        }
    }
}