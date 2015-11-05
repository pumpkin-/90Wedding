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
import com.wedding.secretary.base.BaseFragment;
import com.wedding.secretary.domain.MResult;
import com.wedding.secretary.networks.VolleyResponseUtils;
import com.wedding.secretary.networks.domain.HttpData;
import com.wedding.secretary.networks.domain.HttpParams;
import com.wedding.secretary.utils.images.SelectImagesFragment;
import com.wedding.secretary.utils.log.WeddingLog;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hmy on 2015/11/4.
 */
public class CompleteUserInfoFragment extends BaseFragment {

    private static String TAG = CompleteUserInfoFragment.class.getSimpleName();

    //控件
    @ViewInject(R.id.iv_complete_user_avatar)
    private ImageView iv_complete_user_avatar;//头像
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

    private DatePicker dp_date_picker;//日期选择器

    HttpUtils http;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                //选择婚期
                case 0: {
                    Date date = (Date) msg.obj;
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    tv_complete_user_marriageDate.setText(df.format(date));
                }
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_completeuserinfo, null);
        ViewUtils.inject(this, view);
        View view1 = inflater.inflate(R.layout.dialog_date_picker, null);
        ViewUtils.inject(this, view1);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
    }

    @Override
    public void initListener() {
        iv_complete_user_avatar.setOnClickListener(this);
        et_complete_user_gender.setOnClickListener(this);
        et_complete_user_age.setOnClickListener(this);
        tv_complete_user_marriageDate.setOnClickListener(this);
        et_complete_user_hometown.setOnClickListener(this);
    }

    @Override
    public void enhanceOnResponse(String Tag, String json, HttpParams params) {

    }

    @Override
    public void onClick(View v) {
        if (v == iv_complete_user_avatar) {
            //选择头像
            Intent intent = new Intent(getActivity(), SelectImagesFragment.class);
            startActivityForResult(intent, 998);

        } else if (v == tv_complete_user_marriageDate) {
            //TODO 选择结婚日期
            //设置Dialog布局
            View view = View.inflate(getActivity(), R.layout.dialog_date_picker, null);
            dp_date_picker = (DatePicker) view.findViewById(R.id.dp_date_picker);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setView(view);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());

            //初始化为当前日期
            dp_date_picker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Toast.makeText(getActivity(),year+" " +
                            monthOfYear + " " + dayOfMonth, Toast.LENGTH_SHORT).show();
                    Date date = new Date(year - 1900, monthOfYear, dayOfMonth);
                    Message msg = handler.obtainMessage();
                    msg.what = 0;
                    msg.obj = date;
                    handler.sendMessage(msg);
                }
            });

            builder.setPositiveButton("确  定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            Dialog dialog = builder.create();
            dialog.show();

        } else if (v == et_complete_user_gender) {
            //TODO 选择性别

        } else if (v == et_complete_user_hometown) {
            //TODO 选择家乡

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 998 && data != null) {
            Bundle bundle = data.getExtras();
            String path = bundle.getString("avatarPath");
            http = new HttpUtils();

            String uploadHost = App.BASE_URL + App.USER_REQ_DOUPLOADAVATAR;
            RequestParams params = new RequestParams();
            // params.addBodyParameter("msg",imgtxt.getText().toString());
            params.addBodyParameter(path, new File(path));
            params.addBodyParameter("id", App.USER.getId() + "");
            uploadMethod(params, uploadHost);
        }
    }

    public void uploadMethod(final RequestParams params, final String uploadHost) {
        http.send(HttpRequest.HttpMethod.POST, uploadHost, params, new RequestCallBack<String>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                if (isUploading) {
                    // msgTextview.setText("upload: " + current + "/"+ total);
                } else {
                    // msgTextview.setText("reply: " + current + "/"+ total);
                }
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // msgTextview.setText("reply: " + responseInfo.result);
                LogUtils.d(responseInfo.result);
                LogUtils.d(responseInfo.reasonPhrase);

                HttpData httpData = JSON.parseObject(responseInfo.result, HttpData.class);
                MResult mResult = VolleyResponseUtils.getObject(httpData.getJson(), MResult.class);
                if (mResult.isSuccess()) {
                    ImageLoader.getInstance().displayImage(mResult.getReverse1(), iv_complete_user_avatar);
                    //TODO
                }

            }

            @Override
            public void onFailure(HttpException error, String msg) {
            }
        });
    }

}
