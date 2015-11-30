package com.wedding.secretary.fragments.LoginFragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wedding.secretary.R;
import com.wedding.secretary.application.App;
import com.wedding.secretary.application.AppData;
import com.wedding.secretary.base.BaseFragment;
import com.wedding.secretary.domain.MResult;
import com.wedding.secretary.domain.User;
import com.wedding.secretary.networks.ApiUtils.UserRequestUtils;
import com.wedding.secretary.networks.VolleyResponseUtils;
import com.wedding.secretary.networks.domain.HttpParams;
import com.wedding.secretary.utils.common.Navigate;
import com.wedding.secretary.utils.string.StringUtils;

/**
 * 设置密码
 * Created by hmy on 2015/11/16.
 */
public class InputPasswordFragment extends BaseFragment {

    private static String TAG = InputPasswordFragment.class.getSimpleName();

    //控件
    @ViewInject(R.id.et_input_password)
    private EditText et_input_password;//输入密码
    @ViewInject(R.id.iv_delete_input_password)
    private ImageView iv_delete_input_password; //删除输入的密码内容
    @ViewInject(R.id.tv_password_next)
    private TextView tv_password_next;//注册

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_password, null);
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
        iv_delete_input_password.setOnClickListener(this);
        tv_password_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == iv_delete_input_password) {
            //删除输入的密码内容
            et_input_password.setText("");

        } else if (v == tv_password_next) {
            //完成
            //提交用户名和密码
            if (et_input_password.getText().toString().length() >= 6) {
                UserRequestUtils.doUserRegisterOrResetPassword(getActivity(), TAG, App.USER.getPhoneNumber(), et_input_password.getText().toString(), this);
            } else {
                Toast.makeText(getActivity(), "请输入6-19位密码", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initComponent() {

        //密码
        et_input_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!StringUtils.isEmpty(s.toString())) {
                    iv_delete_input_password.setVisibility(View.VISIBLE);
                    tv_password_next.setBackgroundResource(R.drawable.sign_in);
                    tv_password_next.setClickable(true);
                } else {
                    iv_delete_input_password.setVisibility(View.INVISIBLE);
                    tv_password_next.setBackgroundResource(R.drawable.sign_in_normal);
                    tv_password_next.setClickable(false);
                }
            }
        });
    }

    @Override
    public void enhanceOnResponse(String Tag, String json, MResult result, HttpParams params) {

        //提交用户名和密码
        if (Tag.equals(AppData.USER_REQ_DOUSERREGISTERORRESETPASSWORD)) {
            User user = VolleyResponseUtils.getObject(json, User.class);
            if (user != null) {
                App.updateAppUser(user);
                Navigate.startMain(this);
            }
        }
    }

}
