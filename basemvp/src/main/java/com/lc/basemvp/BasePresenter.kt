package com.lc.basemvp

import java.lang.ref.WeakReference

abstract class BasePresenter<V : IBaseView> {
    var mView: V? = null
        get() {
            return if (field == null) {
                throw IllegalStateException("view not attached")
            } else {
                field
            }
        }
    private lateinit var weakReference: WeakReference<V>

    fun attachView(view: V) {
        weakReference = WeakReference(view)
        this.mView = weakReference.get()!!
    }

    fun detachView() {
        weakReference.clear()
        mView = null
    }


}