package com.thoughtworks.car.navi

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class NaviViewModelTest {

    @Test
    fun shouldInitialNaviWhenCreated() {
        // given
        val viewModel = NaviViewModel()

        // when
        val item = viewModel.uiState.value

        // then
        assertThat(item.label).isEqualTo("This is Navi Fragment")
    }
}