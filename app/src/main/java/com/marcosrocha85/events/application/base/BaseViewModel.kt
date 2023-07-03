package com.marcosrocha85.events.application.base

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.marcosrocha85.events.application.utils.ViewStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

interface BaseViewModel {
    val context: Context
    val observeOn: Scheduler
    val subscribeOn: Scheduler
    val viewStatus: MutableLiveData<ViewStatus>
    fun onDestroy()
    fun <TScheduler: Any> Observable<TScheduler>.scheduler(): Observable<TScheduler> {
        return this.subscribeOn(subscribeOn)
            .observeOn(observeOn)
    }

    abstract class Factory(override val context: Context) : BaseViewModel {
        override val observeOn: Scheduler = AndroidSchedulers.mainThread()
        override val subscribeOn: Scheduler = Schedulers.io()
        private val compositeDisposable = CompositeDisposable()
        override val viewStatus = MutableLiveData(
            ViewStatus(
                isLoading = true
            )
        )

        open fun <T: Any> buildObserver(
            observer: Observable<T>,
            successHandler: Consumer<T>,
            errorHandler: Consumer<Throwable>
        ) {
            compositeDisposable.add(
                observer
                    .scheduler()
                    .subscribe(successHandler, errorHandler)
            )
        }

        override fun onDestroy() {
            compositeDisposable.clear()
        }
    }
}