package com.wedding.secretary.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.wedding.secretary.widgets.LoadingProgressDialog;

/**
 * Created by hmy on 2015/10/27.
 * Update By Byron on 2015/10/31
 *
 */
public abstract class BaseFragmentActivity extends FragmentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public abstract void initListener();

    private LoadingProgressDialog loadingProgressDialog;

}
