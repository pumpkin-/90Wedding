package com.wedding.secretary.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wedding.secretary.utils.images.LoadingUtils;
import com.wedding.secretary.networks.VolleyRequestUtils;
import com.wedding.secretary.networks.VolleyResponse;
import com.wedding.secretary.networks.VolleyResponseUtils;
import com.wedding.secretary.networks.domain.HttpParams;
import com.wedding.secretary.utils.log.WeddingLog;
import com.wedding.secretary.utils.string.StringUtils;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by hmy on 2015/10/27.
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener, VolleyResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //初始化控件监听器
    protected abstract void initListener();

    /**
     * 增强onResponse方法
     *
     * @param Tag
     * @param json
     * @param httpParams
     */
    public abstract void enhanceOnResponse(String Tag, String json, HttpParams httpParams);

    /**
     * 服务器响应
     *
     * @param jsonObject
     */
    @Override
    public void onResponse(JSONObject jsonObject) {
        WeddingLog.e(jsonObject.toString());
        if (jsonObject == null) {
            Toast.makeText(this, "请求服务器失败", Toast.LENGTH_SHORT).show();
        } else {
            //缓冲圈处理
            HttpParams httpParams = VolleyResponseUtils.getHttpParams(jsonObject);
            //若请求页面不为空
            if (!StringUtils.isEmpty(httpParams.reqPageName)) {
                //将当前请求页面的请求方法链表中的当前请求方法移除
                List<String> reqlist = VolleyRequestUtils.obtainRequestQueueMap().get(httpParams.reqPageName);
                reqlist.remove(httpParams.methodTag);
                //若当前请求页面的请求方法链表为空则隐藏缓冲圈
                if (reqlist.size() <= 0) {
                    LoadingUtils.dissmissLoadingDialog();
                } else {
                    VolleyRequestUtils.obtainRequestQueueMap().put(httpParams.reqPageName, reqlist);
                }
            }
            //回调
            enhanceOnResponse(VolleyResponseUtils.getTag(jsonObject),
                    VolleyResponseUtils.getHttpData(jsonObject).getJson(),
                    httpParams);
        }
    }

}
