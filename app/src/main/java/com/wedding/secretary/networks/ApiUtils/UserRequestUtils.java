package com.wedding.secretary.networks.ApiUtils;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.wedding.secretary.application.App;
import com.wedding.secretary.domain.User;
import com.wedding.secretary.networks.domain.HttpParams;
import com.wedding.secretary.networks.HttpResponse;
import com.wedding.secretary.networks.HttpUtils;
import com.wedding.secretary.utils.string.StringUtils;

/**
 * Created by hmy on 2015/10/30.
 */
public class UserRequestUtils {

    /**
     *用户登录
     * @param activity
     * @param reqPageName
     * @param username
     * @param password
     * @param httpResponse
     */
    public static void doUserLogin(Activity activity,String reqPageName, String username, String password, HttpResponse httpResponse) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(StringUtils.getMD5Str(password));

        String json = JSON.toJSONString(user);

        HttpParams httpParams = new HttpParams(reqPageName, App.USER_REQ_DOUSERLOGIN);
        HttpUtils.httpPost(activity, httpParams, json, httpResponse);
    }

    /**
     * 用户注册
     * @param activity
     * @param reqPageName
     * @param phoneNumber
     * @param password
     * @param httpResponse
     */
    public static void doUserRegiste(Activity activity,String reqPageName,String phoneNumber,String password, HttpResponse httpResponse) {

        User user=new User();
        user.setUsername(phoneNumber);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(StringUtils.getMD5Str(password));
        user.setNickName(phoneNumber);
        String json = JSON.toJSONString(user);
        App.USER = user;

        HttpParams httpParams = new HttpParams(reqPageName, App.USER_REQ_DOUSERREGISTE);
        HttpUtils.httpPost(activity, httpParams, json, httpResponse);
    }

}
