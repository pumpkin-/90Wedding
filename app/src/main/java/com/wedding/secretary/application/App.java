package com.wedding.secretary.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.callback.InitResultCallback;
import com.alibaba.sdk.android.trade.TradeConfigs;
import com.alibaba.sdk.android.trade.TradeConstants;
import com.alibaba.sdk.android.trade.callback.TradeProcessCallback;
import com.alibaba.sdk.android.trade.model.TradeResult;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.wedding.secretary.domain.User;
import com.wedding.secretary.utils.log.WeddingLog;

/**
 * 程序初始化
 * Created by hmy on 2015/10/30.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initImageLoader();
        initAlibaba();
    }

    private static App app;

    public static App getApp() {
        return app;
    }

    public static Context getAppContext() {
        return getApp().getApplicationContext();
    }

    //用户信息
    public static User USER = new User();

    public static void updateAppUser(User user) {
        if (user.getId() != null) {
            USER.setId(user.getId());
        }
        if (user.getRealName() != null) {
            USER.setRealName(user.getRealName());
        }
        if (user.getUsername() != null) {
            USER.setUsername(user.getUsername());
        }
        if (user.getPassword() != null) {
            USER.setPassword(user.getPassword());
        }
        if (user.getPhoneNumber() != null) {
            USER.setPhoneNumber(user.getPhoneNumber());
        }
        if (user.getNickName() != null) {
            USER.setNickName(user.getNickName());
        }
        if (user.getGender() != null) {
            USER.setGender(user.getGender());
        }
        if (user.getUsername() != null) {
            USER.setUsername(user.getUsername());
        }
        if (user.getMarriageDate() != null) {
            USER.setMarriageDate(user.getMarriageDate());
        }
        if (user.getAge() != null) {
            USER.setAge(user.getAge());
        }
        if (user.getSignature() != null) {
            USER.setSignature(user.getSignature());
        }
        if (user.getAvatar() != null) {
            USER.setAvatar(user.getAvatar());
        }
    }

    //存储用户登录状态
    public static SharedPreferences sharedPreferences = null;

    public synchronized static SharedPreferences obtainSharedPreferences(Activity activity) {
        if (sharedPreferences == null) {
            sharedPreferences = activity.getSharedPreferences("wedding", Activity.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    //初始化ImageLoader相关配置
    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .enableLogging()
                .build();
        ImageLoader.getInstance().init(config);
    }

    //初始化AlibabaSDK
    private void initAlibaba() {
        TradeConfigs.defaultItemDetailWebViewType = TradeConstants.BAICHUAN_H5_VIEW;
        TradeConfigs.defaultTradeProcessCallback = new TradeProcessCallback() {
            @Override
            public void onPaySuccess(TradeResult tradeResult) {
            }

            @Override
            public void onFailure(int code, String msg) {
            }
        };
        TradeConfigs.defaultTaokePid = "mm_*****";
        TradeConfigs.defaultISVCode = "myIsvCode";

        AlibabaSDK.asyncInit(this, new InitResultCallback() {
            @Override
            public void onSuccess() {
                WeddingLog.d("AlibabaSDK初始化成功");
            }

            @Override
            public void onFailure(int code, String message) {
                WeddingLog.d("AlibabaSDK初始化失败");
            }
        });
    }

}
