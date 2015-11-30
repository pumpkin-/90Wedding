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
import com.wedding.secretary.application.AppSDKConst;
import com.wedding.secretary.base.BaseFragment;
import com.wedding.secretary.domain.MResult;
import com.wedding.secretary.domain.User;
import com.wedding.secretary.networks.ApiUtils.UserRequestUtils;
import com.wedding.secretary.networks.domain.HttpParams;
import com.wedding.secretary.utils.common.Navigate;
import com.wedding.secretary.utils.string.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * 输入手机号
 * 1注册
 * 2重置密码
 * Created by hmy on 2015/11/16.
 */
public class InputPhoneNumberFragment extends BaseFragment {

    private static String TAG = InputPhoneNumberFragment.class.getSimpleName();

    //控件
    @ViewInject(R.id.tv_agreement)
    private TextView tv_agreement;//用户协议
    @ViewInject(R.id.et__input_phoneNumber)
    private EditText et__input_phoneNumber;//输入手机号
    @ViewInject(R.id.iv_delete_input_phoneNumber)
    private ImageView iv_delete_input_phoneNumber;//删除输入的手机号内容
    @ViewInject(R.id.tv_phoneNumber_next)
    private TextView tv_phoneNumber_next;//注册

    //变量
    private int action = 0;

    //短信验证 -- 该handler在子线程中
    private EventHandler eh = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //设置USER的手机号
                    User user = new User();
                    user.setPhoneNumber(et__input_phoneNumber.getText().toString());
                    App.updateAppUser(user);

                    if ((Boolean) data) {
                        //智能验证
                        //直接跳转到设置密码界面
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Navigate.startSetPasswordFragment(InputPhoneNumberFragment.this, 0);
                            }
                        });
                    } else {
                        //跳转到获取验证码界面
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Navigate.startGetVerifyCodeFragment(InputPhoneNumberFragment.this, 0);
                            }
                        });
                    }
                } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {

                }
            } else if (result == SMSSDK.RESULT_ERROR) {
                ((Throwable) data).printStackTrace();

            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_phonenumber, null);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        action = bundle.getInt("action");

        initListener();
        initComponent();
    }

    @Override
    public void initListener() {
        tv_agreement.setOnClickListener(this);
        iv_delete_input_phoneNumber.setOnClickListener(this);
        tv_phoneNumber_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tv_agreement) {
            //用户协议

        } else if (v == iv_delete_input_phoneNumber) {
            //删除输入的手机号内容
            et__input_phoneNumber.setText("");

        } else if (v == tv_phoneNumber_next) {
            //下一步
            //验证手机号在数据库中是否存在
            if (isMobileNO(et__input_phoneNumber.getText().toString())) {
                UserRequestUtils.doGetUserByUsername(getActivity(), TAG, et__input_phoneNumber.getText().toString(), this);
            } else {
                Toast.makeText(getActivity(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initComponent() {

        SMSSDK.initSDK(getActivity(), AppSDKConst.SMSSDK_APPKEY, AppSDKConst.SMSSDK_APPSECRET);
        SMSSDK.registerEventHandler(eh); //注册短信回调

        //手机号
        et__input_phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!StringUtils.isEmpty(s.toString())) {
                    iv_delete_input_phoneNumber.setVisibility(View.VISIBLE);
                } else {
                    iv_delete_input_phoneNumber.setVisibility(View.INVISIBLE);
                }
                if (isMobileNO(s.toString())) {
                    tv_phoneNumber_next.setBackgroundResource(R.drawable.sign_in);
                    tv_phoneNumber_next.setClickable(true);
                } else {
                    tv_phoneNumber_next.setBackgroundResource(R.drawable.sign_in_normal);
                    tv_phoneNumber_next.setClickable(false);
                }
            }
        });
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
        //注销EventHandler
        SMSSDK.unregisterEventHandler(eh);
    }

    @Override
    public void enhanceOnResponse(String Tag, String json, MResult result, HttpParams params) {
        //注册或重置密码
        if (Tag.equals(AppData.USER_REQ_DOGETUSERBYUSERNAME)) {
            if (action == AppData.REGISTER) {
                //注册
                if (!result.isSuccess()) {
                    SMSSDK.getVerificationCode("86", et__input_phoneNumber.getText().toString());
                } else {
                    Toast.makeText(getActivity(), "该手机号已被注册过", Toast.LENGTH_SHORT).show();
                }
            } else if (action == AppData.RESETPASSWORD) {
                //重置密码
                if (result.isSuccess()) {
                    SMSSDK.getVerificationCode("86", et__input_phoneNumber.getText().toString());
                } else {
                    Toast.makeText(getActivity(), "该手机号尚未注册", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
