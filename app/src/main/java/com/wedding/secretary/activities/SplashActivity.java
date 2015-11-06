package com.wedding.secretary.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wedding.secretary.R;
import com.wedding.secretary.application.App;
import com.wedding.secretary.application.AppData;
import com.wedding.secretary.application.AppSDKConst;
import com.wedding.secretary.base.BaseActivity;
import com.wedding.secretary.domain.MResult;
import com.wedding.secretary.domain.User;
import com.wedding.secretary.fragments.LoginFragments.CompleteUserInfoFragment;
import com.wedding.secretary.networks.ApiUtils.UserRequestUtils;
import com.wedding.secretary.networks.VolleyResponseUtils;
import com.wedding.secretary.networks.domain.HttpParams;
import com.wedding.secretary.utils.string.StringUtils;

import cn.smssdk.SMSSDK;

/**
 * Created by hmy on 2015/10/27.
 */
public class SplashActivity extends BaseActivity {

    private static String TAG = SplashActivity.class.getSimpleName();

    //控件
    @ViewInject(R.id.btn_login)
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ViewUtils.inject(this);
        initListener();

        //判断用户是否登录过 登录过则获取用户信息
        SharedPreferences sharedPreferences = App.obtainSharedPreferences(this);
        int id = sharedPreferences.getInt("id", 0);
        int login = sharedPreferences.getInt("autoLogin", AppData.AUTOLOGIN_NO);
        if (id != 0 && login == AppData.AUTOLOGIN_YES) {
            //获取用户信息
            UserRequestUtils.doGetUserInfo(this, TAG, id, SplashActivity.this);
        }
    }

    @Override
    protected void initListener() {
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_login) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void enhanceOnResponse(String Tag, String json, HttpParams params) {
        if (Tag.equals(AppData.USER_REQ_DOGETUSERINFO)) {
            User user = VolleyResponseUtils.getObject(json, User.class);
            if (user != null) {
                // 获取用户信息成功则跳转至首页
                App.USER = user;
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "获取用户信息失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
