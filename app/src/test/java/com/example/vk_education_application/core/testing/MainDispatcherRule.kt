@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.vk_education_application.core.testing

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * JUnit-правило, подменяющее [Dispatchers.Main] тестовым диспетчером.
 *
 * Нужно для тестов ViewModel: `viewModelScope` использует `Dispatchers.Main`,
 * которого нет в локальных JVM-тестах.
 *
 * По умолчанию используется [UnconfinedTestDispatcher], поэтому корутины
 * из `viewModelScope` выполняются сразу и не требуют ручного `advanceUntilIdle()`.
 *
 * @param testDispatcher диспетчер, который будет установлен как `Main`.
 */
class MainDispatcherRule(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {

    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}
