package com.wedding.secretary.fragments.LoginFragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.wedding.secretary.application.AppSDKConst;
import com.wedding.secretary.base.BaseFragment;
import com.wedding.secretary.domain.MResult;
import com.wedding.secretary.networks.ApiUtils.UserRequestUtils;
import com.wedding.secretary.networks.VolleyResponseUtils;
import com.wedding.secretary.networks.domain.HttpParams;
import com.wedding.secretary.utils.string.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by hmy on 2015/10/27.
 */
public class RegisterFragment extends BaseFragment {

    private static String TAG = RegisterFragment.class.getSimpleName();

    //控件
    @ViewInject(R.id.et_input_phone_number)
    private EditText et_input_phone_number;//输入手机号
    @ViewInject(R.id.et_input_verify_code)
    private EditText et_input_verify_code;//输入验证码
    @ViewInject(R.id.tv_get_verify_code)
    private TextView tv_get_verify_code;//获取验证码
    @ViewInject(R.id.et_input_password)
    private EditText et_input_password;//输入密码
    @ViewInject(R.id.iv_delete_input_phone_number)
    private ImageView iv_delete_input_phone_number;  //删除输入的手机号内容
    @ViewInject(R.id.iv_delete_input_password)
    private ImageView iv_delete_input_password; //删除输入的密码内容
    @ViewInject(R.id.tv_agreement)
    private TextView tv_agreement;//用户协议
    @ViewInject(R.id.tv_register)
    private TextView tv_register;//注册

    // 变量
    private int countTime = 120; // 验证码超时时间,120s
    private boolean hasPhoneNumber = false;
    private boolean hasVerifyCode = false;
    private boolean hasPassword = false;

    // 文字提示
    private final String TIP_ILLEGAL_PHONE_NUMBER = "请输入正确的手机号";
    private final String TIP_EMPTY_PASSWORD = "密码不能为空";
    private final String TIP_ILLEGAL_PASSWORD = "请输入6-19位密码";
    private final String TIP_EMPTY_VERIFY_CODE = "请输入验证码";
    private final String TIP_GET_VERIFY_CODE = "获取验证码";
    private final String TIP_GET_VERIFY_CODE_SUCCESS = "验证码已发送到您的手机，请注意查收";

    //短信验证 -- 该handler在子线程中
    private EventHandler eh = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    Log.d(TAG, "提交验证码成功 -- " + result + "  " + data.toString());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            UserRequestUtils.doUserRegiste(getActivity(), TAG,
                                    et_input_phone_number.getText().toString(),
                                    et_input_password.getText().toString(), RegisterFragment.this);
                        }
                    });
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    Log.d(TAG, "获取验证码成功 -- " + result + "  " + data.toString());
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                }
            } else {
                if (result == SMSSDK.RESULT_ERROR) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "验证不成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                ((Throwable) data).printStackTrace();
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
                        tv_get_verify_code.setText(countTime + "s");
                        tv_get_verify_code.setClickable(false);
                        handler.sendEmptyMessageDelayed(0, 1 * 1000);
                    } else {
                        countTime = 120;
                        tv_get_verify_code.setClickable(true);
                        tv_get_verify_code.setText(TIP_GET_VERIFY_CODE);
                    }
                }
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, null);
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
        tv_get_verify_code.setOnClickListener(this);
        tv_agreement.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        iv_delete_input_phone_number.setOnClickListener(this);
        iv_delete_input_password.setOnClickListener(this);
    }

    //用户注册成功后跳转至修改个人信息界面
    @Override
    public void enhanceOnResponse(String Tag, String json, HttpParams params) {
        if (Tag.equals(AppData.USER_REQ_DOUSERREGISTE)) {
            MResult result = VolleyResponseUtils.getObject(json, MResult.class);
            if (result.isSuccess()) {
                //获取服务器返回的用户id
                App.USER.setId(Integer.getInteger(result.getReverse1()));
                // 跳转到完善个人信息
                CompleteUserInfoFragment completeUserInfoFragment = new CompleteUserInfoFragment();
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_login, completeUserInfoFragment).
                        addToBackStack(RegisterFragment.class.getSimpleName()).commit();
            } else {
                Toast.makeText(getActivity(), result.getInfo(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == tv_get_verify_code) {
            //获取验证码
            //判断是否为手机号
            if (isMobileNO(et_input_phone_number.getText().toString())) {
                handler.sendEmptyMessage(0);
                SMSSDK.getVerificationCode("86", et_input_phone_number.getText().toString());
            } else {
                Toast.makeText(getActivity(), TIP_ILLEGAL_PHONE_NUMBER, Toast.LENGTH_SHORT).show();
            }
        } else if (v == tv_agreement) {

        } else if (v == tv_register) {
            //注册
            //发送手机号和验证码到服务器
            if (infoIntegrityCheck()) {
                SMSSDK.submitVerificationCode("86", et_input_phone_number.getText().toString(), et_input_verify_code.getText().toString());
            }

        } else if (v == iv_delete_input_phone_number) {
            //删除输入的手机号内容
            et_input_phone_number.setText("");
        } else if (v == iv_delete_input_password) {
            //删除输入的密码内容
            et_input_password.setText("");
        }
    }

    private void initComponent() {

        SMSSDK.initSDK(getActivity(), AppSDKConst.SMSSDK_APPKEY, AppSDKConst.SMSSDK_APPSECRET);
        SMSSDK.registerEventHandler(eh); //注册短信回调

        //设置“获取验证码”
        tv_get_verify_code.setText(TIP_GET_VERIFY_CODE);

        //手机号
        et_input_phone_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!StringUtils.isEmpty(s.toString())) {
                    hasPhoneNumber = true;
                    iv_delete_input_phone_number.setVisibility(View.VISIBLE);
                } else {
                    hasPhoneNumber = false;
                    iv_delete_input_phone_number.setVisibility(View.INVISIBLE);
                }
                if (hasPhoneNumber == true && hasVerifyCode == true && hasPassword == true) {
                    tv_register.setBackgroundResource(R.drawable.sign_in);
                    tv_register.setClickable(true);
                } else {
                    tv_register.setBackgroundResource(R.drawable.sign_in_normal);
                    tv_register.setClickable(false);
                }
            }
        });

        //验证码
        et_input_verify_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!StringUtils.isEmpty(s.toString())) {
                    hasVerifyCode = true;
                } else {
                    hasVerifyCode = false;
                }
                if (hasPhoneNumber == true && hasVerifyCode == true && hasPassword == true) {
                    tv_register.setBackgroundResource(R.drawable.sign_in);
                    tv_register.setClickable(true);
                } else {
                    tv_register.setBackgroundResource(R.drawable.sign_in_normal);
                    tv_register.setClickable(false);
                }
            }
        });

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
                    hasPassword = true;
                    iv_delete_input_password.setVisibility(View.VISIBLE);
                } else {
                    hasPassword = false;
                    iv_delete_input_password.setVisibility(View.INVISIBLE);
                }
                if (hasPhoneNumber == true && hasVerifyCode == true && hasPassword == true) {
                    tv_register.setBackgroundResource(R.drawable.sign_in);
                    tv_register.setClickable(true);
                } else {
                    tv_register.setBackgroundResource(R.drawable.sign_in_normal);
                    tv_register.setClickable(false);
                }
            }
        });
    }

    // 点击注册时检查信息的完整性
    private boolean infoIntegrityCheck() {
        if (isMobileNO(et_input_phone_number.getText().toString())) { // 手机号
            if (et_input_password.getText().length() <= 0) { // 密码
                Toast.makeText(getActivity(), TIP_EMPTY_PASSWORD, Toast.LENGTH_SHORT).show();
                return false;
            } else if (et_input_password.getText().length() < 6 || et_input_password.getText().length() > 16) {
                Toast.makeText(getActivity(), TIP_ILLEGAL_PASSWORD, Toast.LENGTH_SHORT).show();
                return false;
            }
            if (et_input_verify_code.getText().length() <= 0) { // 验证码
                Toast.makeText(getActivity(), TIP_EMPTY_VERIFY_CODE, Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(getActivity(), TIP_ILLEGAL_PHONE_NUMBER, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // 正则表达式验证手机号
    private boolean isMobileNO(String mobile) {
        Pattern p = Pattern.compile("^((1)\\d{10})$");
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //对EventHandler注销验证
        SMSSDK.unregisterEventHandler(eh);
    }
}
