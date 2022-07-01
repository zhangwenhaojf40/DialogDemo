package com.hao.libdialog

import android.app.Activity
import android.app.Dialog
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 *  Create by Zwh on 2020/11/26
 *  DESC:
 */
abstract class BaseDialog {
    lateinit var mView:View
    var mDialog: Dialog? = null
    fun createDialog(activity: Activity?,themeResId:Int = R.style.custom_dialog) {
        if (activity == null) return
        mView = View.inflate(activity, getLayoutRes(), null)
        mDialog = CustomDialog.Builder(activity)
            .setView(mView)
            .setWidth(getWidthScale())
            .setThemeResId(themeResId)
            .setHeight(getHeightScale())
            .create()
        mView.findViewById<ImageView>(R.id.iv_close)?.setOnClickListener {
            dismiss()
        }
        mView.findViewById<TextView>(R.id.tv_cancel)?.setOnClickListener {
            dismiss()
        }
        mView.findViewById<TextView>(R.id.tv_sure)?.setOnClickListener {
            dismiss()
        }
        initView(activity)
        if(!activity.isFinishing){
            showDialog()
        }
    }

    abstract fun getLayoutRes(): Int

    abstract fun initView(activity: Activity?)

    open fun getWidthScale() :Float{
        return 0.8f
    }

    open fun getHeightScale() :Float{
        return 0f
    }

    fun dismiss(){
        try {
            mDialog?.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showDialog(){
        try {
            mDialog?.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}