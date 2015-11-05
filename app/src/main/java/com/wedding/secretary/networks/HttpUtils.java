package com.wedding.secretary.networks;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.wedding.secretary.application.App;
import com.wedding.secretary.application.LoadingUtils;
import com.wedding.secretary.networks.domain.HttpData;
import com.wedding.secretary.networks.domain.HttpParams;
import com.wedding.secretary.utils.string.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by hmy on 2015/10/29.
 * Update by Byron on 2015/11/1.
 */
public class HttpUtils {
    /**
     * volley请求队列
     */
    private static RequestQueue mQueue = null;
    /**
     * 每个页面的请求队列的方法
     */
    private static Map<String,List<String>> requestQueueMap = new HashMap<String, List<String>>();

    /**
     * 获取每个展示页的请求数量
     * @return
     */
    public static Map<String,List<String>> obtainRequestQueueMap() {
        return requestQueueMap;
    }

    public static RequestQueue obtainRequestQueue(Activity activity) {
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(activity);
        }
        return mQueue;
    }
    public static RequestQueue obtainRequestQueue(Context context) {
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(context);
        }
        return mQueue;
    }

    //HTTP的POST请求
    public static void httpPost(final Activity activity,HttpParams httpParams, String json, HttpResponse httpResponse) {
        String url = App.BASE_URL +httpParams.methodTag;

        Map<String, List<String>> requestMap = HttpUtils.obtainRequestQueueMap();
        if(!StringUtils.isEmpty(httpParams.reqPageName)) {
            List<String> reqList = requestMap.get(httpParams.reqPageName);
            //方法重复提交  直接返回
            if(reqList != null && reqList.contains(httpParams.methodTag)) {
                return;
            }
            if(reqList == null ) {
                List<String> reqs = new LinkedList<String>();
                reqs.add(httpParams.methodTag);
                requestMap.put(httpParams.reqPageName, reqs);
            } else {
                List<String> reqs = requestMap.get(httpParams.reqPageName);
                reqs.add(httpParams.methodTag);
                requestMap.put(httpParams.reqPageName, reqs);
            }
            LoadingUtils.showLoadingDialog(activity);
        }


        HttpData httpData = new HttpData();
        httpData.setHttpParams(httpParams);
        Toast.makeText(activity, httpParams.reqPageName, Toast.LENGTH_SHORT).show();
        httpData.setJson(json);

        String jsonString = JSON.toJSONString(httpData);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mQueue = obtainRequestQueue(activity);
        Request request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, httpResponse, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(activity.getClass().getSimpleName(), "response : " + volleyError.toString());
                LoadingUtils.dissmissLoadingDialog();
                Toast.makeText(activity, "error:服务器异常", Toast.LENGTH_SHORT).show();
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(5*1000, 1, 1.0f));
        mQueue.add(request);
        mQueue.start();
    }

    /**
     * 给jsonObject设置请求参数(支持Integer,String,Boolean,Double,Map类型)
     * @param jsonObject
     */
    public static JSONObject putHttpParamsToJSONObject(JSONObject jsonObject, HttpParams httpParams) {
        Field[] fields = httpParams.getClass().getFields();
        for(Field field : fields) {
            try {
                field.setAccessible(true);
                String fieldName = field.getName();
                Class<?> type = field.getType();
                if(type == Integer.class) {
                    jsonObject.put(fieldName,  (Integer)field.get(httpParams));
                }
                if(type == String.class) {
                    jsonObject.put(fieldName,(String)field.get(httpParams));
                }
                if(type == Boolean.class) {
                    jsonObject.put(fieldName,(Boolean)field.get(httpParams));
                }
                if(type == Double.class) {
                    jsonObject.put(fieldName,(Double)field.get(httpParams));
                }
                if(type == Map.class) {
                    com.alibaba.fastjson.JSONObject jsonObj = JSON.parseObject(jsonObject.toString());
                    jsonObj.putAll((Map)field.get(httpParams));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }


}