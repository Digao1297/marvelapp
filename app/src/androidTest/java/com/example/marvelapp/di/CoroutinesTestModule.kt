package com.example.marvelapp.di

import com.example.core.usecase.base.CoroutinesDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
class AppCoroutinesTestDispatchers(
    private val testDispatchers: TestDispatcher = UnconfinedTestDispatcher(TestCoroutineScheduler())
) : CoroutinesDispatchers {
    override fun default(): CoroutineDispatcher = testDispatchers
    override fun io(): CoroutineDispatcher = testDispatchers
    override fun main(): CoroutineDispatcher = testDispatchers
    override fun unconfined(): CoroutineDispatcher = testDispatchers
}

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesTestModule {

    @Provides
    fun provideTestDispatchers(): CoroutinesDispatchers = AppCoroutinesTestDispatchers()
}