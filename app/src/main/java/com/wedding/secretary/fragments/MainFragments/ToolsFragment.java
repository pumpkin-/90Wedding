package com.wedding.secretary.fragments.MainFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wedding.secretary.base.BaseFragment;
import com.wedding.secretary.domain.MResult;
import com.wedding.secretary.networks.domain.HttpParams;

/**
 * 工具
 * Created by hmy on 2015/11/7.
 */
public class ToolsFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        initListener();

        TextView view = new TextView(getActivity());
        view.setText("工具");
        view.setTextSize(40);

        return view;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void enhanceOnResponse(String Tag, String json, MResult result, HttpParams params) {

    }

    @Override
    public void onClick(View v) {

    }
}
