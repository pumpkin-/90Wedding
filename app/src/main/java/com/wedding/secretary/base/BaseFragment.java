package com.wedding.secretary.base;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wedding.secretary.R;
import com.wedding.secretary.domain.MResult;
import com.wedding.secretary.networks.VolleyRequestUtils;
import com.wedding.secretary.networks.VolleyResponse;
import com.wedding.secretary.networks.VolleyResponseUtils;
import com.wedding.secretary.networks.domain.HttpParams;
import com.wedding.secretary.utils.images.LoadingUtils;
import com.wedding.secretary.utils.log.WeddingLog;
import com.wedding.secretary.utils.string.StringUtils;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by hmy on 2015/10/27.
 * Upadte by Byron on 2015/10/31
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener, VolleyResponse {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    //初始化控件监听器
    public abstract void initListener();

    //初始化ActionBar
    public void initActionBar() {
        // 设置ActionBar样式
        android.app.ActionBar actionbar = getActivity().getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(false);
        actionbar.setDisplayShowHomeEnabled(false);
        // 设置actionbar的背景图
        actionbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
    }

    public void finish() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    /**
     * 增强onResponse方法
     *
     * @param Tag
     * @param json
     * @param result
     * @param params
     */
    public abstract void enhanceOnResponse(String Tag, String json, MResult result, HttpParams params);

    /**
     * 服务器响应
     *
     * @param jsonObject
     */
    @Override
    public void onResponse(JSONObject jsonObject) {
        WeddingLog.w(jsonObject.toString());
        if (jsonObject == null) {
            Toast.makeText(getActivity(), "请求服务器失败", Toast.LENGTH_SHORT).show();
        } else {
            //缓冲圈处理
            HttpParams httpParams = VolleyResponseUtils.getHttpParams(jsonObject);
            MResult mResult = VolleyResponseUtils.getMResult(jsonObject);
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
                    mResult,
                    httpParams);
        }
    }

}
