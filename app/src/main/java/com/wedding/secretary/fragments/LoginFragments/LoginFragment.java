package com.wedding.secretary.fragments.LoginFragments;

import android.content.SharedPreferences;
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
 * 用户登录
 * Created by hmy on 2015/10/28.
 */
public class LoginFragment extends BaseFragment {

    private static String TAG = LoginFragment.class.getSimpleName();

    //控件
    @ViewInject(R.id.tv_register)
    private TextView tv_register;//注册
    @ViewInject(R.id.tv_login)
    private TextView tv_login;//登录
    @ViewInject(R.id.et_login_username)
    private EditText et_login_username;//账号
    @ViewInject(R.id.et_login_password)
    private EditText et_login_password;//密码
    @ViewInject(R.id.tv_forget_password)
    private TextView tv_forget_password;//忘记密码

    @ViewInject(R.id.iv_login_username)
    private ImageView iv_login_username;//账号图标
    @ViewInject(R.id.iv_login_password)
    private ImageView iv_login_password;//密码图标
    @ViewInject(R.id.iv_delete_login_username)
    private ImageView iv_delete_login_username;//删除输入的账号内容
    @ViewInject(R.id.iv_delete_login_password)
    private ImageView iv_delete_login_password;//删除输入的密码内容

    // 变量
    private boolean hasUsername = false;
    private boolean hasPassword = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_login, null);
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
        tv_register.setOnClickListener(this);
        tv_login.setOnClickListener(this);
        tv_forget_password.setOnClickListener(this);
        iv_delete_login_username.setOnClickListener(this);
        iv_delete_login_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tv_register) {
            //注册
            Navigate.startInputPhoneNumberFragment(this, AppData.REGISTER);

        } else if (v == tv_login) {
            //登录
            UserRequestUtils.doUserLogin(getActivity(), TAG, et_login_username.getText().toString(), et_login_password.getText().toString(), this);

        } else if (v == tv_forget_password) {
            //忘记密码
            Navigate.startInputPhoneNumberFragment(this, AppData.RESETPASSWORD);

        } else if (v == iv_delete_login_username) {
            //删除输入的账号内容
            et_login_username.setText("");

        } else if (v == iv_delete_login_password) {
            //删除输入的密码内容
            et_login_password.setText("");
        }
    }

    private void initComponent() {
        //登录
        et_login_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!StringUtils.isEmpty(s.toString())) {
                    hasUsername = true;
                    iv_delete_login_username.setVisibility(View.VISIBLE);
                    iv_login_username.setImageResource(R.mipmap.login_user_acount);
                } else {
                    hasUsername = false;
                    iv_delete_login_username.setVisibility(View.INVISIBLE);
                    iv_login_username.setImageResource(R.mipmap.login_user_acount_default);
                }
                if (hasUsername == true && hasPassword == true) {
                    tv_login.setBackgroundResource(R.drawable.sign_in);
                    tv_login.setClickable(true);
                } else {
                    tv_login.setBackgroundResource(R.drawable.sign_in_normal);
                    tv_login.setClickable(false);
                }
            }
        });

        // 密码
        et_login_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StringUtils.isEmpty(s.toString())) {
                    iv_login_password.setImageResource(R.mipmap.login_user_password_default);
                    iv_delete_login_password.setVisibility(View.INVISIBLE);
                } else {
                    iv_login_password.setImageResource(R.mipmap.login_user_password);
                    iv_delete_login_password.setVisibility(View.VISIBLE);
                }
                if (!StringUtils.isEmpty(s.toString()) && s.length() >= 6) {
                    hasPassword = true;
                } else {
                    hasPassword = false;
                }
                if (hasUsername == true && hasPassword == true) {
                    tv_login.setBackgroundResource(R.drawable.sign_in);
                    tv_login.setClickable(true);
                } else {
                    tv_login.setBackgroundResource(R.drawable.sign_in_normal);
                    tv_login.setClickable(false);
                }
            }
        });
    }

    @Override
    public void enhanceOnResponse(String Tag, String json, MResult result, HttpParams params) {
        //登录成功
        if (Tag.equals(AppData.USER_REQ_DOUSERLOGIN)) {
            User user = VolleyResponseUtils.getObject(json, User.class);
            if (result.isSuccess() && user != null) {
                //保存到全局USER中
                App.updateAppUser(user);

                //将用户登陆成功的状态保存到SharedPreferences
                //TODO 判断当前用户id是否存在  多用户情况
                SharedPreferences sharedPreferences = App.obtainSharedPreferences(getActivity());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //login —— 0：未登录过  1：登录过
                editor.putInt("id", App.USER.getId());
                editor.putInt("autoLogin", AppData.AUTOLOGIN_YES);
                editor.commit();

                //跳转到首页
                Navigate.startMain(this);

            } else {
                Toast.makeText(getActivity(), result.getInfo(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
