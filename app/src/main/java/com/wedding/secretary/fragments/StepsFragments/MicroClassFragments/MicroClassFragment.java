package com.wedding.secretary.fragments.StepsFragments.MicroClassFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wedding.secretary.R;
import com.wedding.secretary.adapters.ExpandableListViexAdapter;
import com.wedding.secretary.base.BaseFragment;
import com.wedding.secretary.domain.MResult;
import com.wedding.secretary.networks.domain.HttpParams;

/**
 * 婚礼微课堂
 * Created by hmy on 2015/11/18.
 */
public class MicroClassFragment extends BaseFragment {

    private static String TAG = MicroClassFragment.class.getSimpleName();

    //控件
    @ViewInject(R.id.elv_microClass)
    private ExpandableListView elv_microClass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_microclass, null);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();

        initActionBar();
        getActivity().getActionBar().setTitle("婚礼微课堂");
//        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);

        elv_microClass.setAdapter(new ExpandableListViexAdapter(getActivity()));

    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        getActivity().getMenuInflater().inflate(R.menu.menu_fragment, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }

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