package com.wedding.secretary.activities;

import android.os.Bundle;
import android.view.View;

import com.wedding.secretary.R;
import com.wedding.secretary.base.BaseFragmentActivity;
import com.wedding.secretary.fragments.MainFragments.MainFragment;

/**
 * 主页
 * Created by hmy on 2015/10/27.
 */
public class MainActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments_container_main);

        //首页
        MainFragment mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_container_main, mainFragment).commit();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
