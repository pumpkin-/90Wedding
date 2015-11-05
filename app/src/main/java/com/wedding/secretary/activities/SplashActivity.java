package com.wedding.secretary.activities;

import android.os.Bundle;
import android.view.View;

import com.wedding.secretary.R;
import com.wedding.secretary.application.AppSDKConst;
import com.wedding.secretary.base.BaseActivity;

import cn.smssdk.SMSSDK;

/**
 * Created by hmy on 2015/10/27.
 */
public class SplashActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initSDK();
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    private void initSDK() {
        //SMSSDK的AppKey和AppSecret
//        SMSSDK.initSDK(this, AppSDKConst.SMSSDK_APPKEY, AppSDKConst.SMSSDK_APPSECRET);
    }
}
