package com.wedding.secretary.application;

import com.wedding.secretary.domain.User;

/**
 * 全局常量
 * Created by hmy on 2015/11/6.
 */
public class AppData {

    //自动登录
    public static final int AUTOLOGIN_NO = 0;
    public static final int AUTOLOGIN_YES = 1;

    //请求路径
    public static final String BASE_URL = "http://192.168.0.102:8080/wedding";

    /**
     * 用户请求相关----------------------------------------------------
     */
    public static final String USER_REQ_DOUSERLOGIN = "/user/operation/userLogin.action";
    public static final String USER_REQ_DOUSERREGISTE = "/user/operation/userRegiste.action";
    public static final String USER_REQ_DOAVATARUPLOAD = "/file/operation/avatarUpload.action";
    public static final String USER_REQ_DOUPDATEUSERINFO = "/user/operation//updateUserInfo.action";
    public static final String USER_REQ_DOGETUSERINFO = "/user/operation/getUserInfo.action";
}
