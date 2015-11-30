package com.wedding.secretary.fragments.LoginFragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.wedding.secretary.application.AppSDKConst;
import com.wedding.secretary.base.BaseFragment;
import com.wedding.secretary.domain.MResult;
import com.wedding.secretary.networks.domain.HttpParams;
import com.wedding.secretary.utils.common.Navigate;
import com.wedding.secretary.utils.string.StringUtils;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by hmy on 2015/11/16.
 */
public class InputVerifyCodeFragment extends BaseFragment {

    private static String TAG = InputVerifyCodeFragment.class.getSimpleName();

    //控件
    @ViewInject(R.id.et_input_verifyCode)
    private EditText et_input_verifyCode;//输入验证码
    @ViewInject(R.id.tv_get_verifyCode)
    private TextView tv_get_verifyCode;//获取验证码
    @ViewInject(R.id.iv_delete_input_verifyCode)
    private ImageView iv_delete_input_verifyCode;//删除输入的验证码内容
    @ViewInject(R.id.tv_verifyCode_next)
    private TextView tv_verifyCode_next;//下一步

    // 变量
    private int countTime = 60; // 验证码超时时间,60s

    //短信验证 -- 该handler在子线程中
    private EventHandler eh = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {

                } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //验证成功，跳转至设置密码界面
                    Navigate.startSetPasswordFragment(InputVerifyCodeFragment.this, 0);
                }
            } else if (result == SMSSDK.RESULT_ERROR) {
                ((Throwable) data).printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "验证不成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    };

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //获取验证码倒计时
                case 0: {
                    if (countTime > 0) {
                        countTime--;
                        tv_get_verifyCode.setText("重新获取" + countTime + "s");
                        tv_get_verifyCode.setClickable(false);
                        handler.sendEmptyMessageDelayed(0, 1000);
                    } else {
                        countTime = 60;
                        tv_get_verifyCode.setClickable(true);
                        tv_get_verifyCode.setText("获取验证码");
                    }
                }
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_verifycode, null);
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
        tv_get_verifyCode.setOnClickListener(this);
        iv_delete_input_verifyCode.setOnClickListener(this);
        tv_verifyCode_next.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == tv_get_verifyCode) {
            //获取验证码
            SMSSDK.getVerificationCode("86", App.USER.getPhoneNumber());
            handler.sendEmptyMessage(0);
        } else if (v == iv_delete_input_verifyCode) {
            //删除输入的验证码内容
            et_input_verifyCode.setText("");

        } else if (v == tv_verifyCode_next) {
            //下一步
            //提交验证码
            if (!StringUtils.isEmpty(et_input_verifyCode.toString())) {
                SMSSDK.submitVerificationCode("86", App.USER.getPhoneNumber(), et_input_verifyCode.getText().toString());
            }
        }
    }

    private void initComponent() {

        handler.sendEmptyMessage(0);

        SMSSDK.initSDK(getActivity(), AppSDKConst.SMSSDK_APPKEY, AppSDKConst.SMSSDK_APPSECRET);
        SMSSDK.registerEventHandler(eh); //注册短信回调

        //验证码
        et_input_verifyCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!StringUtils.isEmpty(s.toString())) {
                    iv_delete_input_verifyCode.setVisibility(View.VISIBLE);
                    tv_verifyCode_next.setBackgroundResource(R.drawable.sign_in);
                    tv_verifyCode_next.setClickable(true);
                } else {
                    iv_delete_input_verifyCode.setVisibility(View.INVISIBLE);
                    tv_verifyCode_next.setBackgroundResource(R.drawable.sign_in_normal);
                    tv_verifyCode_next.setClickable(false);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //注销EventHandler
        SMSSDK.unregisterEventHandler(eh);
    }

    @Override
    public void enhanceOnResponse(String Tag, String json, MResult result, HttpParams params) {

    }
}
