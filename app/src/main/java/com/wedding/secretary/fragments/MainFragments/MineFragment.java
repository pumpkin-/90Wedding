package com.wedding.secretary.fragments.MainFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wedding.secretary.R;
import com.wedding.secretary.application.App;
import com.wedding.secretary.base.BaseFragment;
import com.wedding.secretary.domain.MResult;
import com.wedding.secretary.networks.domain.HttpParams;
import com.wedding.secretary.utils.common.Navigate;

/**
 * 我的
 * Created by hmy on 2015/11/6.
 */
public class MineFragment extends BaseFragment {

    private static String TAG = MineFragment.class.getSimpleName();

    //控件
    @ViewInject(R.id.iv_mine_avatar)
    private ImageView iv_mine_avatar;
    @ViewInject(R.id.tv_mine_nickName)
    private TextView tv_mine_nickName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_mine, null);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
        initUserInfo();
    }

    private void initUserInfo() {
        if (App.USER != null) {
            if (App.USER.getAvatar() != null) {
                ImageLoader.getInstance().displayImage(App.USER.getAvatar(), iv_mine_avatar);
            }
            if (App.USER.getNickName() != null) {
                tv_mine_nickName.setText(App.USER.getNickName());
            }
        }
    }

    @Override
    public void initListener() {
        iv_mine_avatar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == iv_mine_avatar) {
            if (App.USER != null && App.USER.getId() != null) {
                //跳转至修改个人信息界面
                Navigate.startUserUpdateInfoFragment(this, 0);

            } else {
                //跳转至登录界面
                Navigate.startLoginActivity(this);
            }
        }
    }

    /**
     * 服务器响应处理
     *
     * @param Tag
     * @param json
     * @param params
     */
    @Override
    public void enhanceOnResponse(String Tag, String json, MResult result, HttpParams params) {

    }

}
