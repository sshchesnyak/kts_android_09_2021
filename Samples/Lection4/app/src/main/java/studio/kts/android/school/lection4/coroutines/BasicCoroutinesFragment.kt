package studio.kts.android.school.lection4.coroutines

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.math.BigInteger
import kotlin.random.Random
import kotlin.random.asJavaRandom

class BasicCoroutinesFragment : Fragment() {

    private val fragmentScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        runSuspendWithoutCoroutine()
//        globalScopeExample()
//        fragmentScopeExample()
//        notUsedSuspendExample()
//        suspendWithThreadChangeExample()
//        serialExample()
//        parallelExample()
        changeThreadExample()
    }

    private fun runSuspendWithoutCoroutine() {
        suspend fun testSuspendFun() {}
        //Suspend function 'testSuspendFun' should be called only from a coroutine or another suspend function
//        testSuspendFun()
    }

    private fun globalScopeExample() {
        suspend fun testSuspendFun() {
            Timber.d("start from thread = ${Thread.currentThread().name}")
            delay(1000)
            Timber.d("end from thread = ${Thread.currentThread().name}")
        }

        GlobalScope.launch {
            testSuspendFun()
        }
    }

    private fun fragmentScopeExample() {

        suspend fun testSuspendFun() {
            Timber.d("start from thread = ${Thread.currentThread().name}")
            delay(1000)
            Timber.d("end from thread = ${Thread.currentThread().name}")
        }

        fragmentScope.launch {
            testSuspendFun()
        }
        Timber.d("after launch coroutine from thread = ${Thread.currentThread().name}")
    }

    private fun notUsedSuspendExample() {
        // suspend is not used
        suspend fun calculateNumber(): BigInteger {
            return BigInteger.probablePrime(2000, Random.asJavaRandom())
        }

        fragmentScope.launch {
            Timber.d("start calculate number from thread = ${Thread.currentThread().name}")
            val number = calculateNumber()
            Timber.d("end calculate number from thread = ${Thread.currentThread().name}")
        }
        Timber.d("after launch coroutine from thread = ${Thread.currentThread().name}")
    }

    private fun suspendWithThreadChangeExample() {
        // suspend is used
        suspend fun calculateNumber(): BigInteger {
            Timber.d("inside calculate number from thread = ${Thread.currentThread().name}")
            return withContext(Dispatchers.Default) {
                Timber.d("inside withContext from thread = ${Thread.currentThread().name}")
                BigInteger.probablePrime(2000, Random.asJavaRandom())
            }
        }

        fragmentScope.launch {
            Timber.d("start calculate number from thread = ${Thread.currentThread().name}")
            val number = calculateNumber()
            Timber.d("end calculate number from thread = ${Thread.currentThread().name}")
        }
        Timber.d("after launch coroutine from thread = ${Thread.currentThread().name}")
    }

    private fun serialExample() {

        // suspend is used
        suspend fun calculateNumber(): BigInteger {
            return withContext(Dispatchers.Default) {
                BigInteger.probablePrime(2000, Random.asJavaRandom())
            }
        }

        fragmentScope.launch {
            (0 until 100).forEach {
                Timber.d("launch $it")
                val result = calculateNumber()
                Timber.d("result $it = $result")
            }
        }
    }

    private fun parallelExample() {

        // suspend is used
        suspend fun calculateNumber(): BigInteger {
            return withContext(Dispatchers.Default) {
                BigInteger.probablePrime(2000, Random.asJavaRandom())
            }
        }

        fragmentScope.launch {
            val startedOperations = (0 until 10).map {
                async {
                    Timber.d("launch $it")
                    calculateNumber()
                }
            }

            startedOperations.forEach {
                val result = it.await()
                Timber.d("result = $result")
            }
        }
    }

    private fun changeThreadExample() {
        val fragmentIOScope = CoroutineScope(Dispatchers.IO)
        fragmentIOScope.launch {
            (0..200).forEach {
                Timber.d("start from thread = ${Thread.currentThread().name}")
                delay(100)
                Timber.d("end from thread = ${Thread.currentThread().name}")
            }
        }
    }
}
