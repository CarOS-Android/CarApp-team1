package com.thoughtworks.car.music.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.thoughtworks.ark.router.annotation.Scheme
import com.thoughtworks.car.Schemes
import com.thoughtworks.car.ui.theme.Theme

@Scheme(Schemes.MUSIC)
class MusicFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = ComposeView(requireContext()).apply {
        setContent {
            Theme {
                MusicScreen()
            }
        }
    }
}