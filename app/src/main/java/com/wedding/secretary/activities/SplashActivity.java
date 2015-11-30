package com.wedding.secretary.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.wedding.secretary.R;
import com.wedding.secretary.application.App;
import com.wedding.secretary.application.AppData;
import com.wedding.secretary.base.BaseActivity;
import com.wedding.secretary.domain.User;
import com.wedding.secretary.networks.ApiUtils.UserRequestUtils;
import com.wedding.secretary.networks.VolleyResponseUtils;
import com.wedding.secretary.networks.domain.HttpParams;
import com.wedding.secretary.utils.common.Navigate;
import com.wedding.secretary.utils.log.WeddingLog;

/**
 * Splash页面（显示广告，判断是否自动登录，获取用户信息）
 * Created by hmy on 2015/10/27.
 */
public class SplashActivity extends BaseActivity {

    private static String TAG = SplashActivity.class.getSimpleName();

    //控件
    @ViewInject(R.id.iv_splash)
    private ImageView iv_splash;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            doAutoLogin();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ViewUtils.inject(this);
        initListener();

        WeddingLog.e("onCreate 执行了");

        //停留3s
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }.start();

        playAnim(iv_splash);
    }

    /**
     * 动画效果
     *
     * @param view
     */
    public void playAnim(final View view) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.4f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.4f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0.8f, 1f, 0.8f)
        );
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                playAnim(view);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        set.setDuration(20 * 1000).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        WeddingLog.e("onResume 执行了");
    }

    @Override
    protected void initListener() {
    }

    /**
     * 服务器响应处理
     *
     * @param Tag
     * @param json
     * @param httpParams
     */
    @Override
    public void enhanceOnResponse(String Tag, String json, HttpParams httpParams) {
        if (Tag.equals(AppData.USER_REQ_DOGETUSERINFO)) {
            User user = VolleyResponseUtils.getObject(json, User.class);
            if (user != null) {
                // 获取用户信息成功则跳转至首页
                App.USER = user;
                Navigate.startMain(this);
            } else {
                Navigate.startMain(this);
            }
            finish();
        }
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 自动登录
     */
    public void doAutoLogin() {
        //判断用户是否登录过，登录过则获取用户信息
        SharedPreferences sharedPreferences = App.obtainSharedPreferences(SplashActivity.this);
        int id = sharedPreferences.getInt("id", 0);
        int login = sharedPreferences.getInt("autoLogin", AppData.AUTOLOGIN_NO);
        if (id != 0 && login == AppData.AUTOLOGIN_YES) {
            //获取用户信息
            UserRequestUtils.doGetUserInfo(SplashActivity.this, TAG, id, SplashActivity.this);
        } else {
            Navigate.startMain(this);
            finish();
        }
    }
}
