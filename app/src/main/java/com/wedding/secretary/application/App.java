package com.wedding.secretary.application;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.wedding.secretary.domain.User;

/**
 * Created by hmy on 2015/10/30.
 */
public class App extends Application {

    /**
     * 用户信息
     */
    public static User USER;

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
    }

    /**
     * 初始化ImageLoader相关配置
     */
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

    public static final String BASE_URL = "http://192.168.0.102:8080/wedding";

    /**
     * 用户请求相关----------------------------------------------------
     */
    public static final String USER_REQ_DOUSERLOGIN = "/user/operation/userLogin.action";
    public static final String USER_REQ_DOUSERREGISTE = "/user/operation/userRegiste.action";


}
