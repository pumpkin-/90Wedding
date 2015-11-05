package com.wedding.secretary.application;

import android.app.Activity;
import android.content.DialogInterface;

import com.wedding.secretary.widgets.LoadingProgressDialog;

/**
 * Created by Byron on 2015/10/31.
 */
public class LoadingUtils {

    private static LoadingProgressDialog loadingProgressDialog;

    /**
     * 显示加载框
     * @param msg
     */
    public static void showLoadingDialog(Activity activity, String msg) {
        if(loadingProgressDialog == null) {
            loadingProgressDialog = new LoadingProgressDialog(activity);
            loadingProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {

                }
            });
        }
        loadingProgressDialog.setMessage(msg);
        loadingProgressDialog.show();
    }
    /**
     * 显示加载框
     */
    public static void showLoadingDialog(Activity activity) {
        if(loadingProgressDialog == null) {
            loadingProgressDialog = new LoadingProgressDialog(activity);
            loadingProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {

                }
            });
        }
        loadingProgressDialog.setMessage(null);
        loadingProgressDialog.show();
    }

    /**
     * 关闭加载框
     */
    public static void dissmissLoadingDialog() {
        if(loadingProgressDialog != null && loadingProgressDialog.isShowing()) {
            loadingProgressDialog.dismiss();
        }
    }

}
