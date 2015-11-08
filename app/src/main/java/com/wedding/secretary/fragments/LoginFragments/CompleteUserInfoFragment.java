package com.wedding.secretary.fragments.LoginFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wedding.secretary.R;
import com.wedding.secretary.application.App;
import com.wedding.secretary.application.AppData;
import com.wedding.secretary.base.BaseFragment;
import com.wedding.secretary.domain.MResult;
import com.wedding.secretary.domain.User;
import com.wedding.secretary.networks.ApiUtils.UserRequestUtils;
import com.wedding.secretary.networks.VolleyResponseUtils;
import com.wedding.secretary.networks.domain.HttpData;
import com.wedding.secretary.networks.domain.HttpParams;
import com.wedding.secretary.utils.images.SelectImagesFragment;
import com.wedding.secretary.utils.string.StringUtils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 用户信息
 * Created by hmy on 2015/11/4.
 */
public class CompleteUserInfoFragment extends BaseFragment {

    private static String TAG = CompleteUserInfoFragment.class.getSimpleName();

    //控件
    @ViewInject(R.id.iv_complete_user_avatar)
    private ImageView iv_complete_user_avatar;//头像
    @ViewInject(R.id.pb_img_upload)
    private ProgressBar pb_img_upload; //图像上传加载框
    @ViewInject(R.id.et_complete_user_nickName)
    private EditText et_complete_user_nickName;//昵称
    @ViewInject(R.id.et_complete_user_realName)
    private EditText et_complete_user_realName;//真实姓名
    @ViewInject(R.id.et_complete_user_gender)
    private EditText et_complete_user_gender;//性别
    @ViewInject(R.id.et_complete_user_age)
    private EditText et_complete_user_age;//年龄
    @ViewInject(R.id.et_complete_user_phoneNumber)
    private EditText et_complete_user_phoneNumber;//手机号
    @ViewInject(R.id.tv_complete_user_marriageDate)
    private TextView tv_complete_user_marriageDate;//结婚日期
    @ViewInject(R.id.et_complete_user_hometown)
    private EditText et_complete_user_hometown;//家乡
    @ViewInject(R.id.et_complete_user_signature)
    private EditText et_complete_user_signature;//个性签名
    @ViewInject(R.id.tv_complete_user_save)
    private TextView tv_complete_user_save;//保存

    private DatePicker dp_date_picker;//日期选择器
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//日期格式
    private Date date;//Date日期

    private HttpUtils http;//网络请求

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                //选择结婚日期
                case 0: {
                    Date date = (Date) msg.obj;
                    tv_complete_user_marriageDate.setText(df.format(date));
                }
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_completeuserinfo, null);
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
                ImageLoader.getInstance().displayImage(App.USER.getAvatar(), iv_complete_user_avatar);
            }
            if (App.USER.getNickName() != null) {
                et_complete_user_nickName.setText(App.USER.getNickName());
            }
            if (App.USER.getRealName() != null) {
                et_complete_user_realName.setText(App.USER.getRealName());
            }
            if (App.USER.getGender() != null) {
                if (App.USER.getGender() == 0) {
                    et_complete_user_gender.setText("女");
                } else {
                    et_complete_user_gender.setText("男");
                }
            }
            if (App.USER.getAge() != null) {
                et_complete_user_age.setText(App.USER.getAge().toString());
            }
            if (App.USER.getPhoneNumber() != null) {
                et_complete_user_phoneNumber.setText(App.USER.getPhoneNumber().toString());
            }
            if (App.USER.getMarriageDate() != null) {
                tv_complete_user_marriageDate.setText(df.format(App.USER.getMarriageDate()));
            }
            if (App.USER.getHometown() != null) {
                et_complete_user_hometown.setText(App.USER.getHometown());
            }
            if (App.USER.getSignature() != null) {
                et_complete_user_signature.setText(App.USER.getSignature());
            }
        }
    }

    @Override
    public void initListener() {
        iv_complete_user_avatar.setOnClickListener(this);
        et_complete_user_gender.setOnClickListener(this);
        et_complete_user_age.setOnClickListener(this);
        tv_complete_user_marriageDate.setOnClickListener(this);
        et_complete_user_hometown.setOnClickListener(this);
        tv_complete_user_save.setOnClickListener(this);
    }

    /**
     * 服务器响应处理
     *
     * @param Tag
     * @param json
     * @param params
     */
    @Override
    public void enhanceOnResponse(String Tag, String json, HttpParams params) {
        if (Tag.equals(AppData.USER_REQ_DOUPDATEUSERINFO)) {
            User user = VolleyResponseUtils.getObject(json, User.class);
            if (user != null) {
                App.USER = user;
                initUserInfo();
            } else {
                Toast.makeText(getActivity(), "修改个人信息失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == iv_complete_user_avatar) {
            //选择头像
            Intent intent = new Intent(getActivity(), SelectImagesFragment.class);
            startActivityForResult(intent, 998);

        } else if (v == tv_complete_user_marriageDate) {
            //选择结婚日期
            //设置Dialog布局
            View view = View.inflate(getActivity(), R.layout.dialog_date_picker, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setView(view);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());

            //将日期设置为当前日期
            date = calendar.getTime();

            //将日期选择器初始化为当前日期
            dp_date_picker = (DatePicker) view.findViewById(R.id.dp_date_picker);
            dp_date_picker.setCalendarViewShown(false);
            dp_date_picker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
                        @Override
                        public void onDateChanged(DatePicker view, int year, int month, int dayOfMonth) {
                            date = new Date(year - 1900, month, dayOfMonth);
                        }
                    });

            builder.setPositiveButton("确  定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Message msg = handler.obtainMessage();
                            msg.what = 0;
                            msg.obj = date;
                            handler.sendMessage(msg);
                            dialog.cancel();
                        }
                    });
            Dialog dialog = builder.create();
            dialog.show();

        } else if (v == et_complete_user_gender) {
            //TODO 选择性别

        } else if (v == et_complete_user_hometown) {
            //TODO 选择家乡

        } else if (v == tv_complete_user_save) {
            //TODO 保存
            String gender = et_complete_user_gender.getText().toString();
            String age = et_complete_user_age.getText().toString();
            if (StringUtils.isEmpty(gender)) {
                gender = "0";
            }
            if (StringUtils.isEmpty(age)) {
                age = "0";
            }
            if (App.USER.getId() != null) {
                UserRequestUtils.doUserInfoUpdate(getActivity(), TAG,
                        App.USER.getId(),
                        et_complete_user_nickName.getText().toString(),
                        et_complete_user_realName.getText().toString(),
                        Integer.parseInt(gender),
                        Integer.parseInt(age),
                        date,
                        et_complete_user_hometown.getText().toString(),
                        et_complete_user_signature.getText().toString(),
                        CompleteUserInfoFragment.this);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //上传头像
        if (requestCode == 998 && data != null) {
            Bundle bundle = data.getExtras();
            String path = bundle.getString("avatarPath");
            http = new HttpUtils();

            String uploadHost = AppData.BASE_URL + AppData.USER_REQ_DOAVATARUPLOAD;
            RequestParams requestParams = new RequestParams();
            requestParams.addBodyParameter(path, new File(path));
            requestParams.addBodyParameter("id", App.USER.getId() + "");
            uploadMethod(requestParams, uploadHost);
        }
    }

    /**
     * 上传头像
     *
     * @param requestParams
     * @param uploadHost
     */
    public void uploadMethod(final RequestParams requestParams, final String uploadHost) {
        http.send(HttpRequest.HttpMethod.POST, uploadHost, requestParams, new RequestCallBack<String>() {
            @Override
            public void onStart() {
                pb_img_upload.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                if (isUploading) {

                } else {

                }
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtils.d(responseInfo.result);
                LogUtils.d(responseInfo.reasonPhrase);

                HttpData httpData = JSON.parseObject(responseInfo.result, HttpData.class);
                MResult mResult = VolleyResponseUtils.getObject(httpData.getJson(), MResult.class);
                if (mResult.isSuccess()) {
                    //设置头像
                    ImageLoader.getInstance().displayImage(mResult.getReverse1(), iv_complete_user_avatar);
                }
                pb_img_upload.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
            }
        });
    }

}
