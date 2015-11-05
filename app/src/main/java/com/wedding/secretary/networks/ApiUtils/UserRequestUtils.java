package com.wedding.secretary.networks.ApiUtils;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.wedding.secretary.application.App;
import com.wedding.secretary.domain.User;
import com.wedding.secretary.networks.domain.HttpParams;
import com.wedding.secretary.networks.HttpResponse;
import com.wedding.secretary.networks.HttpUtils;
import com.wedding.secretary.utils.string.StringUtils;

import java.util.Date;

/**
 * Created by hmy on 2015/10/30.
 */
public class UserRequestUtils {

    /**
     * 用户登录
     *
     * @param activity
     * @param reqPageName
     * @param username
     * @param password
     * @param httpResponse
     */
    public static void doUserLogin(Activity activity, String reqPageName, String username, String password, HttpResponse httpResponse) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(StringUtils.getMD5Str(password));

        String json = JSON.toJSONString(user);

        HttpParams httpParams = new HttpParams(reqPageName, App.USER_REQ_DOUSERLOGIN);
        HttpUtils.httpPost(activity, httpParams, json, httpResponse);
    }

    /**
     * 用户注册
     *
     * @param activity
     * @param reqPageName
     * @param phoneNumber
     * @param password
     * @param httpResponse
     */
    public static void doUserRegiste(Activity activity, String reqPageName, String phoneNumber, String password, HttpResponse httpResponse) {

        User user = new User();
        user.setUsername(phoneNumber);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(StringUtils.getMD5Str(password));
        user.setNickName(phoneNumber);
        App.USER = user;

        String json = JSON.toJSONString(user);

        HttpParams httpParams = new HttpParams(reqPageName, App.USER_REQ_DOUSERREGISTE);
        HttpUtils.httpPost(activity, httpParams, json, httpResponse);
    }

    /**
     * 用户信息修改
     *
     * @param activity
     * @param reqPageName
     * @param nickName
     * @param realName
     * @param gender
     * @param age
     * @param marriageDate
     * @param hometown
     * @param signature
     * @param httpResponse
     */
    public static void doUserInfoUpdate(Activity activity, String reqPageName, String nickName, String realName, Integer gender, Integer age, Date marriageDate, String hometown, String signature, HttpResponse httpResponse) {

        User user=App.USER;
        if(user==null){
            user=new User();
        }
        user.setNickName(nickName);
        user.setRealName(realName);
        user.setGender(gender);
        user.setAge(age);
        user.setMarriageDate(marriageDate);
        user.setHometown(hometown);
        user.setSignature(signature);

        String json = JSON.toJSONString(user);

        HttpParams httpParams = new HttpParams(reqPageName, App.USER_REQ_DOUSERINFOUPDATE);
        HttpUtils.httpPost(activity, httpParams, json, httpResponse);
    }
}
