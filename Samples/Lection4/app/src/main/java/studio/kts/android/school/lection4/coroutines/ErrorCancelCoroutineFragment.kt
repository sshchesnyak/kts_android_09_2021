package studio.kts.android.school.lection4.coroutines

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import studio.kts.android.school.lection4.R
import studio.kts.android.school.lection4.databinding.FragmentErrorCancelBinding
import timber.log.Timber

class ErrorCancelCoroutineFragment : Fragment(R.layout.fragment_error_cancel) {

    private val binding by viewBinding(FragmentErrorCancelBinding::bind)
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        crashExample()
//        crashWithTryCatchExample()
//        correctTryCatchExample()
//        runCatchingExample()
//        errorHandlerExample()
//        errorCancelsSiblingCoroutines()
//        errorWithSupervisorJobNotCancelsSiblingCoroutines()
//        nonCancellableExample()
//        cancellableExample()
//        cancellableWithDelayExample()
//        cancellableWithYieldExample()
    }

    private fun crashExample() {
        scope.launch {
            error("test exception")
        }
    }

    private fun crashWithTryCatchExample() {
        try {
            scope.launch {
                error("test exception")
            }
        } catch (t: Throwable) {
            Timber.e(t, "catch throwable")
        }
    }

    private fun correctTryCatchExample() {
        scope.launch {
            try {
                error("test exception")
            } catch (t: Throwable) {
                Timber.e(t, "catch throwable")
            }
        }
    }

    private fun runCatchingExample() {
        scope.launch {
            runCatching {
                error("test exception")
                5
            }.onSuccess {
                //handle result
            }.onFailure {
                Timber.e(it, "catch throwable")
            }
        }
    }

    private fun errorHandlerExample() {
        val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Timber.e(throwable, "error from CoroutineExceptionHandler")
        }

        val scopeWithErrorHandling = CoroutineScope(Dispatchers.Main + errorHandler)

        scopeWithErrorHandling.launch {
            error("test exception")
        }
    }

    private fun errorCancelsSiblingCoroutines() {

        val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Timber.e(throwable, "error from CoroutineExceptionHandler")
        }
        val scopeWithErrorHandling = CoroutineScope(Dispatchers.Main + errorHandler)


        scopeWithErrorHandling.launch {
            delay(3000)
            error("test exception")
        }

        scopeWithErrorHandling.launch {
            (0 until 100).forEach {
                delay(500)
                Timber.d("$it")
            }
        }
    }

    private fun errorWithSupervisorJobNotCancelsSiblingCoroutines() {

        val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Timber.e(throwable, "error from CoroutineExceptionHandler")
        }
        val scopeWithErrorHandling = CoroutineScope(SupervisorJob() + Dispatchers.Main + errorHandler)


        scopeWithErrorHandling.launch {
            delay(3000)
            error("test exception")
        }

        scopeWithErrorHandling.launch {
            (0 until 100).forEach {
                delay(500)
                Timber.d("$it")
            }
        }
    }

    private fun nonCancellableExample() {
        scope.launch {
            var i = 0
            withContext(Dispatchers.Default) {
                while (true) {
                    //use thread sleep, not delay
                    Thread.sleep(500)
                    Timber.d("log $i")
                    i++
                }
            }
        }
    }

    private fun cancellableExample() {
        scope.launch {
            withContext(Dispatchers.Default) {
                suspendCancellableCoroutine<Unit> { continuation ->
                    var cancelled = false
                    continuation.invokeOnCancellation {
                        Timber.d("coroutine cancelled")
                        cancelled = true
                    }
                    var i = 0
                    while (true) {
                        if(cancelled) break
                        Thread.sleep(500)
                        Timber.d("log $i")
                        i++
                    }
                }
            }
        }
    }

    private fun cancellableWithDelayExample() {
        scope.launch {
            withContext(Dispatchers.Default) {
                var i = 0
                while (true) {
                    delay(500) // delay support cancelling
                    Timber.d("log $i")
                    i++
                }
            }
        }
    }

    private fun cancellableWithYieldExample() {
        scope.launch {
            withContext(Dispatchers.Default) {
                var i = 0
                while (true) {
                    yield()
                    Thread.sleep(500)
                    Timber.d("log $i")
                    i++
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cancelButton.setOnClickListener {
            scope.coroutineContext.cancelChildren()
        }
    }
}