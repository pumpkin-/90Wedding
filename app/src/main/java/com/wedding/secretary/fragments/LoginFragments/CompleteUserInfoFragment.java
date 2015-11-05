package com.wedding.secretary.fragments.LoginFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

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
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.wedding.secretary.R;
import com.wedding.secretary.base.BaseFragment;
import com.wedding.secretary.domain.MResult;
import com.wedding.secretary.networks.VolleyResponseUtils;
import com.wedding.secretary.networks.domain.HttpData;
import com.wedding.secretary.networks.domain.HttpParams;
import com.wedding.secretary.utils.images.SelectImagesFragment;

import java.io.File;
import java.util.Calendar;

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
    @ViewInject(R.id.et_complete_user_marriageDate)
    private EditText et_complete_user_marriageDate;//结婚日期
    @ViewInject(R.id.et_complete_user_hometown)
    private EditText et_complete_user_hometown;//家乡
    @ViewInject(R.id.et_complete_user_signature)
    private EditText et_complete_user_signature;//个性签名

    @ViewInject(R.id.dp_date_picker)
    private DatePicker dp_date_picker;//日期选择器

    HttpUtils http;

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
    }

    @Override
    public void initListener() {
        iv_complete_user_avatar.setOnClickListener(this);
        et_complete_user_gender.setOnClickListener(this);
        et_complete_user_age.setOnTouchListener(this);
        et_complete_user_marriageDate.setOnTouchListener(this);
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
        } else if (v == et_complete_user_gender) {
            //TODO 选择性别

        } else if (v == et_complete_user_hometown) {
            //TODO 选择家乡

        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (v == et_complete_user_age) {
                //TODO 选择年龄
                final int inType = et_complete_user_age.getInputType();
                et_complete_user_age.setInputType(InputType.TYPE_NULL);
                et_complete_user_age.onTouchEvent(event);
                et_complete_user_age.setInputType(inType);
                et_complete_user_age.setSelection(et_complete_user_age.getText().length());

                StringBuffer sb = datePicker();
                et_complete_user_age.setText(sb);

            } else if (v == et_complete_user_marriageDate) {
                //TODO 选择结婚日期
                final int inType = et_complete_user_marriageDate.getInputType();
                et_complete_user_marriageDate.setInputType(InputType.TYPE_NULL);
                et_complete_user_marriageDate.onTouchEvent(event);
                et_complete_user_marriageDate.setInputType(inType);
                et_complete_user_marriageDate.setSelection(et_complete_user_marriageDate.getText().length());

                StringBuffer sb = datePicker();
                et_complete_user_marriageDate.setText(sb);
            }
        }
        return true;
    }

    //选择日期
    private StringBuffer datePicker() {
        final StringBuffer sb = new StringBuffer();
        sb.append("");

        //设置Dialog布局
        View view = View.inflate(getActivity(), R.layout.dialog_date_picker, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        //初始化为当前日期
        dp_date_picker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null);

        builder.setPositiveButton("确  定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sb.append(String.format("%d-%02d-%02d",
                                dp_date_picker.getYear(),
                                dp_date_picker.getMonth() + 1,
                                dp_date_picker.getDayOfMonth()));
                        dialog.cancel();
                    }
                });
        Dialog dialog = builder.create();
        dialog.show();
        return sb;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = data.getExtras();
        String path = bundle.getString("avatarPath");
        http = new HttpUtils();


        String uploadHost = "http://192.168.0.102:8080/wedding/file/operation/imgsUpload.action";
        RequestParams params = new RequestParams();
        // params.addBodyParameter("msg",imgtxt.getText().toString());
        params.addBodyParameter(path, new File(path));
        params.addBodyParameter("id", "helloworld");
        uploadMethod(params, uploadHost);


    }

    public void uploadMethod(final RequestParams params, final String uploadHost) {
        http.send(HttpRequest.HttpMethod.POST, uploadHost, params, new RequestCallBack<String>() {
            @Override
            public void onStart() {
                // msgTextview.setText("conn...");
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
                // msgTextview.setText(error.getExceptionCode() + ":" + msg);
            }
        });
    }

}
