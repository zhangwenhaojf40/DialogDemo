package com.hao.libdialog

import android.app.Activity
import android.view.Gravity
import android.view.WindowManager

/**
 *  Create by Zwh on 2021/5/25 .20:29
 *  DESC:
 */
abstract class BaseBottomDialog :BaseDialog() {

    override fun initView(activity: Activity?) {
        activity?.run {
            val window = mDialog?.window
            window?.setGravity(Gravity.BOTTOM)
            window?.setWindowAnimations(R.style.dialogWindowAnim) // 添加动画
            val  lp = window?.attributes; // 获取对话框当前的参数值
            lp?.width = activity.resources?.displayMetrics?.widthPixels?:1080 // 宽度
            lp?.height = WindowManager.LayoutParams.WRAP_CONTENT // 高度
            window?.attributes = lp
            mDialog?.setCanceledOnTouchOutside(true)
        }
    }
    override fun getWidthScale(): Float {
        return 1f
    }
}