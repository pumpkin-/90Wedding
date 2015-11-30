package com.wedding.secretary.fragments.StepsFragments.MicroClassFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;
import com.wedding.secretary.R;
import com.wedding.secretary.base.BaseFragment;
import com.wedding.secretary.domain.MResult;
import com.wedding.secretary.networks.domain.HttpParams;

/**
 * 婚礼微课堂目录
 * Created by hmy on 2015/11/24.
 */
public class MicroClassCatalogFragment extends BaseFragment {

    private static String TAG = MicroClassCatalogFragment.class.getSimpleName();

    //控件

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_microclass_catalog, null);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
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
