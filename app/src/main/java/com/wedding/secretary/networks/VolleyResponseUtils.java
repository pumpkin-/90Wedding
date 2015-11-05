package com.wedding.secretary.networks;

import com.alibaba.fastjson.JSON;
import com.wedding.secretary.networks.domain.HttpData;
import com.wedding.secretary.networks.domain.HttpParams;

import org.json.JSONObject;

/**
 * Created by hmy on 2015/10/31.
 */
public class VolleyResponseUtils {

    /**
     * onResponse中返回的JsonObject
     * @param jsonObject
     */
    public static String getTag(JSONObject jsonObject) {
        HttpData data = JSON.parseObject(jsonObject.toString(), HttpData.class);
        return data.getHttpParams().methodTag;
    }

    /**
     * 获取onResponse中返回的对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T>T getObject(String json, Class<T> clazz) {
        T data = JSON.parseObject(json, clazz);
        return data;
    }

    /**
     * 获取HttpData
     * @param jsonObject
     * @return
     */
    public static HttpData getHttpData(JSONObject jsonObject) {
        HttpData data = JSON.parseObject(jsonObject.toString(), HttpData.class);
        return data;
    }

    /**
     * 获取onResponse中返回的对象
     * @param jsonObject
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T>T getObject(JSONObject jsonObject, Class<T> clazz) {
        HttpData data = JSON.parseObject(jsonObject.toString(), HttpData.class);
        T t = JSON.parseObject(data.getJson(), clazz);
        return t;
    }

    /**
     * 获取请求参数
     * @param jsonObject
     * @return
     */
    public static HttpParams getHttpParams(JSONObject jsonObject) {
        HttpData data = JSON.parseObject(jsonObject.toString(), HttpData.class);
        return data.getHttpParams();
    }

}
