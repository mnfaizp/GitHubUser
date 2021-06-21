package com.example.githubuser.core.utils

import android.os.Handler
import android.os.Looper
import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

class AppExecutors @VisibleForTesting constructor(
    private val diskIO: Executor
) {

    @Inject
    constructor() : this(
        Executors.newSingleThreadExecutor()
    )

    fun diskIO(): Executor = diskIO

    companion object {
        private const val THREAD_COUNT = 3
    }

}