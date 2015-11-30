package com.wedding.secretary.fragments.StepsFragments.PreparationFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wedding.secretary.R;
import com.wedding.secretary.base.BaseFragment;
import com.wedding.secretary.domain.MResult;
import com.wedding.secretary.networks.domain.HttpParams;

/**
 * 婚前筹备
 * Created by hmy on 2015/11/18.
 */
public class PreparationFragment extends BaseFragment {

    private static String TAG = PreparationFragment.class.getSimpleName();

    //控件
    @ViewInject(R.id.tv_register)
    private TextView tv_register;//注册
    @ViewInject(R.id.tv_login)
    private TextView tv_login;//登录

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_preparation, null);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
        getActivity().getActionBar().setTitle("婚前筹备");
        initActionBar();

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void enhanceOnResponse(String Tag, String json, MResult result, HttpParams params) {

    }

}
