package com.hao.libdialog;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * Create by Zwh on 2020/6/12
 * DESC:弹窗
 */
public class CustomDialog extends Dialog {
    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;
        /**
        * 弹窗宽度  默认 屏幕宽度*0.8
        * */
        private float widthScale = 0.8f;
        private float heightScale = 0f;
        private View view;
        /**
        * 默认点击外部消失
        * */
        private boolean canceledOnTouchOutside = true;
        /**
         * 返回键是否可以消息
         */
        private boolean canceledOnTouchBack = true;

        /**
        * 弹窗样式
        * */
        private int themeResId = R.style.custom_dialog;
        public Builder(Context context) {
            this.context = context;
        }

        public Builder setView(View view) {
            this.view = view;
            return this;
        }

        /**
         * @param widthScale 宽度占屏系数  0.1~1  默认0.8
         */
        public Builder setWidth(float widthScale){
            this.widthScale = widthScale;
            return this;
        }

        /**
         * @param heightScale 宽度占屏系数  0.1~1  默认0
         */
        public Builder setHeight(float heightScale){
            this.heightScale = heightScale;
            return this;
        }

        /**
         * @param themeResId 弹窗样式
         */
        public Builder setThemeResId(int themeResId){
            this.themeResId = themeResId;
            return this;
        }

        /**
        * 是否点击外部消失
        * */
        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside){
            this.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        /**
        * 点击返回键是否可以关闭
        * */
        public Builder setCanceledOnTouchBack(boolean canceledOnTouchBack){
            this.canceledOnTouchBack = canceledOnTouchBack;
            return this;
        }
        public CustomDialog create() {
            CustomDialog dialog = new CustomDialog(context, themeResId);
            if (view != null) {
                dialog.setContentView(view);
            }
            Window window = dialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams attributes =window.getAttributes();
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                attributes.width = (int) (displayMetrics.widthPixels * widthScale);
                if (heightScale > 0){
                    attributes.height = (int) (displayMetrics.heightPixels * heightScale);
                }
                window.setAttributes(attributes);
            }
            dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
            dialog.setCancelable(canceledOnTouchBack);
            return dialog;
        }
    }
}
