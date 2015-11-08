package com.wedding.secretary.fragments.MainFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wedding.secretary.R;
import com.wedding.secretary.activities.LoginActivity;
import com.wedding.secretary.application.App;
import com.wedding.secretary.base.BaseFragment;
import com.wedding.secretary.fragments.LoginFragments.CompleteUserInfoFragment;
import com.wedding.secretary.networks.domain.HttpParams;

/**
 * 我的
 * Created by hmy on 2015/11/6.
 */
public class MineFragment extends BaseFragment {

    private static String TAG = MineFragment.class.getSimpleName();

    //控件
    @ViewInject(R.id.btn_userInfo_update)
    private Button btn_userInfo_update;
    //控件
    @ViewInject(R.id.btn_user_registe)
    private Button btn_user_registe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
        initComponent();
    }

    @Override
    public void initListener() {
        btn_userInfo_update.setOnClickListener(this);
        btn_user_registe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_userInfo_update) {
            //跳转至修改个人信息界面
            if (App.USER != null && App.USER.getId() != null) {
                CompleteUserInfoFragment completeUserInfoFragment = new CompleteUserInfoFragment();
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.faded_in, R.anim.faded_out).
                        replace(R.id.main_fragment_container, completeUserInfoFragment).
                        addToBackStack(MineFragment.class.getSimpleName()).commit();
            } else {
                Toast.makeText(getActivity(), "您尚未登录", Toast.LENGTH_SHORT).show();
            }
        }
        //注册
        if(v == btn_user_registe) {
            Intent intent= new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }

    private void initComponent() {
    }

    @Override
    public void enhanceOnResponse(String Tag, String json, HttpParams params) {

    }

}
