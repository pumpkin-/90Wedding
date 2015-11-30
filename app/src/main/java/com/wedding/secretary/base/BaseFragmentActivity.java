package com.wedding.secretary.base;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.wedding.secretary.R;

/**
 * Created by hmy on 2015/10/27.
 * Update By Byron on 2015/10/31
 */
public abstract class BaseFragmentActivity extends FragmentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //初始化控件监听器
    public abstract void initListener();

    //TODO
//    private LoadingProgressDialog loadingProgressDialog;

    //初始化ActionBar
    public void initActionBar() {
        // 设置ActionBar样式
        android.app.ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(false);
        actionbar.setDisplayShowHomeEnabled(false);
        actionbar.setTitle("首页");
        // 设置actionbar的背景图
        actionbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
    }

}
