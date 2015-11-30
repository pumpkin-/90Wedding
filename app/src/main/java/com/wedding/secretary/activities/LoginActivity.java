package com.wedding.secretary.activities;

import android.os.Bundle;
import android.view.View;

import com.wedding.secretary.R;
import com.wedding.secretary.base.BaseFragmentActivity;
import com.wedding.secretary.utils.common.Navigate;

/**
 * 登录
 * Created by hmy on 2015/10/27.
 */
public class LoginActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments_container_login);

        Navigate.startLogin(this, 0);
    }

    @Override
    public void initListener() {
    }

    @Override
    public void onClick(View v) {
    }

}
