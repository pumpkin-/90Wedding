package com.wedding.secretary.fragments.StepsFragments.MicroClassFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wedding.secretary.R;
import com.wedding.secretary.application.AppData;
import com.wedding.secretary.base.BaseFragment;
import com.wedding.secretary.domain.MResult;
import com.wedding.secretary.networks.domain.HttpParams;

import java.util.HashMap;
import java.util.Map;

/**
 * 婚礼微课堂WebView
 * Created by hmy on 2015/11/30.
 */
public class MicroClassWebView extends BaseFragment {

    private static String TAG = MicroClassWebView.class.getSimpleName();

    //控件
    @ViewInject(R.id.wv_microclass)
    private WebView wv_microclass;
    @ViewInject(R.id.pb_microclass)
    private ProgressBar pb_microclass;

    //变量
    private int action = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_microclass_webview, null);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();

        initActionBar();
        getActivity().getActionBar().setTitle("婚礼微课堂");

        Bundle bundle = getArguments();
        action = bundle.getInt("action");

        //进度条
        wv_microclass.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    pb_microclass.setVisibility(View.INVISIBLE);
                } else {
                    if (View.INVISIBLE == pb_microclass.getVisibility()) {
                        pb_microclass.setVisibility(View.VISIBLE);
                    }

                    pb_microclass.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });

        //启用支持javascript
        WebSettings settings = wv_microclass.getSettings();
        settings.setJavaScriptEnabled(true);

        Map<String, String> map = new HashMap<String, String>();
        map.put("id", action + "");
        wv_microclass.loadUrl(AppData.BASE_URL + AppData.MICROCLASS_REQ_DOARTICLEDETAIL, map);

        //WebView加载web资源
        //wv_microclass.loadUrl("http://baidu.com");

        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        wv_microclass.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void enhanceOnResponse(String Tag, String json, MResult result, HttpParams params) {

    }

}
