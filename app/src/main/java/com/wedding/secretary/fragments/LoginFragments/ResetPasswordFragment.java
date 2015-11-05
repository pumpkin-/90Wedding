package com.wedding.secretary.fragments.LoginFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;
import com.wedding.secretary.R;
import com.wedding.secretary.base.BaseFragment;
import com.wedding.secretary.networks.domain.HttpParams;

/**
 * Created by hmy on 2015/10/27.
 */
public class ResetPasswordFragment extends BaseFragment {

    private static String TAG = ResetPasswordFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resetpassword, null);
        ViewUtils.inject(this, view);
        initListener();
        return view;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void enhanceOnResponse(String Tag, String json, HttpParams params) {

    }

    @Override
    public void onClick(View v) {

    }
}
