package com.wedding.secretary.activities;

import android.app.Activity;
import android.os.Bundle;

import com.wedding.secretary.R;

/**
 * 第一次运行程序时的介绍页面
 * Created by hmy on 2015/10/27.
 */
public class LoadingPagesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadingpages);
    }
}
