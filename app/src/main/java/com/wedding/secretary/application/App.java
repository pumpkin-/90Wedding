package com.wedding.secretary.application;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.wedding.secretary.domain.User;

/**
 * Created by hmy on 2015/10/30.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
    }

    //用户信息
    public static User USER;

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

}
