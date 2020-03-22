package com.lc.basemvp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment<V : IBaseFragmentView, P : BasePresenter<V>> : Fragment(),
    IBaseFragmentView {


    var rootView: View? = null
    private lateinit var mContext: Context
    val mPresenter: P by lazy {
        createPresenter()
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    abstract fun createPresenter(): P

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false)
        }
        (rootView!!.parent as? ViewGroup)?.removeView(rootView)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.attachView(this as V)
        initView()
        initData()
    }


    abstract fun getLayoutId(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }


    override fun getContext(): Context {
        return mContext
    }


}