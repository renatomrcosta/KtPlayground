package doublecontext

import kotlinx.coroutines.Deferred
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.jvmErasure


inline fun <T> withCache(cache: String, block: () -> T): T = block().also { println("Ran $cache") }

interface IDataProvider

data class DataProvider(
    val name: String
) : IDataProvider

inline fun <DP : IDataProvider, T> DP.use(block: (DP) -> T): T = try {
    block(this)
} finally {
    this::class.memberProperties
        .filter { it.returnType.jvmErasure == Deferred::class } // Filter directly through erasure tho
        .forEach { (it.call(this) as? Deferred<*>)?.checkAndCancelMember() } // do a safecast and go from there
}

fun <T, Y : Deferred<T>> Y.checkAndCancelMember() {
    if (!this.isActive) {
        this.cancel()
    }
}

object MDCAdapter

inline fun <T> withMDC(vararg context: Pair<String, String?>, block: (MDCAdapter) -> T): T = try {
    context.forEach { ctx -> println("$ctx = Added to MDC") }
    block(MDCAdapter)
} finally {
    println("Cleared MDC context")
}

// ############################

object MDCContext
object CacheContext {
    const val AAAAA: String = "aaaaaa"
}

inline fun <T> withCacheReceiver(cache: String, block: CacheContext.() -> T): T {
    return block(CacheContext).also { println("Ran $cache") }
}

inline fun <T> withMDCReceiver(vararg context: Pair<String, String?>, block: MDCAdapter.() -> T): T = try {
    context.forEach { ctx -> println("$ctx = Added to MDC") }
    block(MDCAdapter)
} finally {
    println("Cleared MDC context")
}

inline fun <DP : IDataProvider, T> DP.useReceiver(block: DP.() -> T): T = try {
    block()
} finally {
    this::class.memberProperties
        .filter { it.returnType.jvmErasure == Deferred::class } // Filter directly through erasure tho
        .forEach { (it.call(this) as? Deferred<*>)?.checkAndCancelMember() } // do a safecast and go from there
}


// ############################
// Kudos here to Duncan Mcgregor, the dude he quoted, arrow and Simon Vergauwen and whoever is https://gist.github.com/carbaj03/4ebd0f8da17c351d4235e1bedd9a36b5
// Error Tracking: https://youtrack.jetbrains.com/issue/KT-54233/Lambda-context-receiver-definitions-can-no-longer-accept-multiple-reified-generic-context-parameters
//

sealed interface TypeWrapper<out A> {
    data object IMPL : TypeWrapper<Nothing>
}


//@OptIn(ExperimentalContracts::class)
//inline fun <reified A, reified B, reified R> with(a: A, b: B, block: context(A, B) (TypeWrapper<B>) -> R): R {
//    contract {
//        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
//    }
//    return block(a, b, TypeWrapper.IMPL)
//}

@OptIn(ExperimentalContracts::class)
inline fun <reified A : MDCAdapter, reified B : IDataProvider, reified R> with(
    a: A,
    b: B,
    block: context(A, B) () -> R
): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block(a, b)
}

@OptIn(ExperimentalContracts::class)
inline fun <reified A : MDCAdapter, reified B : IDataProvider, reified C : CacheContext, reified R> with(
    a: A,
    b: B,
    c: C,
    block: context(A, B, C) () -> R
): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block(a, b, c)
}


inline fun <T1, R> doIt(
    t1: T1,
    crossinline block: () -> R,
) {
    with(t1) t1@{ block() }
}

inline fun <T1, T2, R> doIt(
    t1: T1,
    t2: T2,
    crossinline block: context (T1) T2.() -> R,
) {
    with(t2) t2@{ with(t1) t1@{ block(this@t1, this@t2) } }
}

//inline fun <reified T1, reified T2, reified T3, reified R> doIt(
//    t1: T1,
//    t2: T2,
//    t3: T3,
//    crossinline block: context(T1, T2, T3) () -> R,
//): R = with(t3) t3@{
//    with(t2) t2@{
//        with(t1) t1@{
//            block(this@t1, this@t2, this@t3)
//        }
//    }
//}