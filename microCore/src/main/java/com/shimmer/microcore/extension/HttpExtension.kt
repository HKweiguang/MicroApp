@file:Suppress("CHECKRESULT")

package com.shimmer.microcore.extension

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

inline fun <T> Observable<T>.subscribe3(
    crossinline onNext: T.() -> Unit,
    crossinline onError: (Throwable) -> Unit = {},
    crossinline onComplete: () -> Unit = {},
) {
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            it.onNext()
        }, { error ->
            onError(error)
        }, {
            onComplete()
        })
}
