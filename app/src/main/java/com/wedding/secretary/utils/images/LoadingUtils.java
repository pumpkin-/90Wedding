package com.wedding.secretary.utils.images;

import android.content.Context;
import android.content.DialogInterface;

import com.wedding.secretary.widgets.LoadingProgressDialog;

/**
 * Created by Byron on 2015/10/31.
 */
public class LoadingUtils {

    private static LoadingProgressDialog loadingProgressDialog;

    /**
     * 显示加载框
     *
     * @param msg
     */
    public synchronized static void showLoadingDialog(Context context, String msg) {
        if (loadingProgressDialog == null) {
            loadingProgressDialog = new LoadingProgressDialog(context);
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
    public synchronized static void showLoadingDialog(Context context) {
        if (loadingProgressDialog == null) {
            loadingProgressDialog = new LoadingProgressDialog(context);
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
        if (loadingProgressDialog != null && loadingProgressDialog.isShowing()) {
            loadingProgressDialog.dismiss();
        }
    }

}
