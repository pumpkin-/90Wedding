package com.wedding.secretary.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wedding.secretary.R;
import com.wedding.secretary.base.BaseFragmentActivity;

/**
 * 主页
 * Created by hmy on 2015/10/27.
 */
public class MainActivity extends BaseFragmentActivity {

    //控件
    @ViewInject(R.id.btn_login)
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        initListener();
    }

    @Override
    public void initListener() {
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btn_login){
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
    }
}
