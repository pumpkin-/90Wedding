package com.wedding.secretary.widgets;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.lidroid.xutils.ViewUtils;
import com.wedding.secretary.R;
import com.wedding.secretary.networks.HttpUtils;
import com.wedding.secretary.utils.string.StringUtils;

/**
 * Created by Byron on 2015/10/31.
 */
public class LoadingProgressDialog extends Dialog{

    TextView textView;
    ImageView img;
    Context context;
    public LoadingProgressDialog(Context context) {
        super(context, R.style.loadingDialog);
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.widget_loading);

        img = (ImageView)findViewById(R.id.loding_img);
        textView = (TextView) findViewById(R.id.loading_tv);

        AnimationDrawable animationDrawable = (AnimationDrawable) img.getBackground();
        animationDrawable.start();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            super.dismiss();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    /**
     * 给加载框设置消息
     * @param msg
     */
    public void setMessage(String msg) {
        if(StringUtils.isEmpty(msg)){
            textView.setText("");
            textView.setVisibility(View.GONE);
        } else{
            textView.setText(msg);
            textView.setVisibility(View.VISIBLE);
        }
    }
}
