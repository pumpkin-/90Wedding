package com.wedding.secretary.fragments.MainFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wedding.secretary.R;
import com.wedding.secretary.application.App;
import com.wedding.secretary.base.BaseFragment;
import com.wedding.secretary.fragments.LoginFragments.CompleteUserInfoFragment;
import com.wedding.secretary.networks.domain.HttpParams;

/**
 * Created by hmy on 2015/11/6.
 */
public class MainFragment extends BaseFragment {

    private static String TAG = MainFragment.class.getSimpleName();

    //控件
    @ViewInject(R.id.btn_userInfo_update)
    private Button btn_userInfo_update;

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
    }

    @Override
    public void onClick(View v) {
        if (v == btn_userInfo_update) {
            //跳转至修改个人信息界面
            if (App.USER.getId() != null) {
                CompleteUserInfoFragment completeUserInfoFragment = new CompleteUserInfoFragment();
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_main, completeUserInfoFragment).
                        addToBackStack(MainFragment.class.getSimpleName()).commit();
            } else {
                Toast.makeText(getActivity(), "您尚未登录", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initComponent() {
    }

    @Override
    public void enhanceOnResponse(String Tag, String json, HttpParams params) {

    }

}
