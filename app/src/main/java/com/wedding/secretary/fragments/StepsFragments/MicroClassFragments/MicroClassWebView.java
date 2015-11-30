package com.wedding.secretary.fragments.StepsFragments.MicroClassFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wedding.secretary.R;
import com.wedding.secretary.base.BaseFragment;
import com.wedding.secretary.domain.MResult;
import com.wedding.secretary.networks.domain.HttpParams;

/**
 * Created by hmy on 2015/11/30.
 */
public class MicroClassWebView extends BaseFragment {

    private static String TAG = MicroClassWebView.class.getSimpleName();

    //控件
    @ViewInject(R.id.wv_microclass)
    private WebView wv_microclass;

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

        //启用支持javascript
        WebSettings settings = wv_microclass.getSettings();
        settings.setJavaScriptEnabled(true);
        wv_microclass.loadUrl("http://192.168.0.106:8080/wedding/index.jsp");
        //WebView加载web资源
        //wv_microclass.loadUrl("http://baidu.com");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        wv_microclass.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
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
