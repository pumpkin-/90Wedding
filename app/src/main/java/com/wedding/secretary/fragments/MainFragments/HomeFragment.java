package com.wedding.secretary.fragments.MainFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wedding.secretary.R;
import com.wedding.secretary.base.BaseFragment;
import com.wedding.secretary.domain.MResult;
import com.wedding.secretary.networks.domain.HttpParams;
import com.wedding.secretary.utils.common.Navigate;

/**
 * 首页
 * Created by hmy on 2015/11/7.
 */
public class HomeFragment extends BaseFragment {

    private static String TAG = HomeFragment.class.getSimpleName();

    //控件
    @ViewInject(R.id.iv_home1)
    private ImageView iv_home1;//婚礼微课堂
    @ViewInject(R.id.iv_home2)
    private ImageView iv_home2;//婚前筹备
    @ViewInject(R.id.iv_home3)
    private ImageView iv_home3;//DIY婚礼现场
    @ViewInject(R.id.iv_home4)
    private ImageView iv_home4;//婚礼进行时

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_home, null);
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
        iv_home1.setOnClickListener(this);
        iv_home2.setOnClickListener(this);
        iv_home3.setOnClickListener(this);
        iv_home4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == iv_home1) {
            //婚礼微课堂
            Navigate.startMicroClassActivity(this);

        } else if (v == iv_home2) {
            //婚前筹备
            Navigate.startPreparationActivity(this);

        } else if (v == iv_home3) {
            //DIY婚礼现场
            Navigate.startDIYSceneActivity(this);

        } else if (v == iv_home4) {
            //婚礼进行时
            Navigate.startUnderwayActivity(this);

        }
    }

    @Override
    public void enhanceOnResponse(String Tag, String json, MResult result, HttpParams params) {

    }

}
