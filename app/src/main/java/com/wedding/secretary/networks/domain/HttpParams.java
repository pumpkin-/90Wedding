package com.wedding.secretary.networks.domain;

import java.io.Serializable;

/**
 * Created by hmy on 2015/10/30.
 */
public class HttpParams implements Serializable{

    /**
     * 请求来自的frgment或者activity
     */
    public String reqPageName;
    public String methodTag;
    public Integer userId;

    public HttpParams() {
    }

    /**
     * @param reqPageName  当前fragment,activity的simpleName
     * @param methodTag   当前方法的tag
     */
    public HttpParams(String reqPageName, String methodTag) {
        this.reqPageName = reqPageName;
        this.methodTag = methodTag;
    }
}
