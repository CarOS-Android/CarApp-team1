package com.thoughtworks.car.settings

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SettingsViewModelTest {
    @Test
    fun shouldInitialSettingsWhenCreated() {
        // given
        val viewModel = SettingsViewModel()

        // when
        val item = viewModel.state

        // then
        assertThat(item.label).isEqualTo("This is Settings Fragment")
    }
}