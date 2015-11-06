package com.wedding.secretary.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wedding.secretary.application.LoadingUtils;
import com.wedding.secretary.networks.VolleyRequestUtils;
import com.wedding.secretary.networks.domain.HttpParams;
import com.wedding.secretary.networks.VolleyResponse;
import com.wedding.secretary.networks.VolleyResponseUtils;
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

    public abstract void initListener();

    public void finish() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    /**
     * 增强response方法
     *
     * @param Tag
     * @param json
     * @param params
     */
    public abstract void enhanceOnResponse(String Tag, String json, HttpParams params);

    @Override
    public void onResponse(JSONObject jsonObject) {
        WeddingLog.w(jsonObject.toString());
        if (jsonObject == null) {
            Toast.makeText(getActivity(), "请求服务器失败", Toast.LENGTH_SHORT).show();
        } else {
            HttpParams httpParams = VolleyResponseUtils.getHttpParams(jsonObject);
            //map中请求页面不为空
            if (!StringUtils.isEmpty(httpParams.reqPageName)) {
                //获取map中请求页面，将链表中请求成功的方法移除
                List<String> reqlist = VolleyRequestUtils.obtainRequestQueueMap().get(httpParams.reqPageName);
                reqlist.remove(httpParams.methodTag);
                if (reqlist.size() <= 0) {
                    LoadingUtils.dissmissLoadingDialog();
                } else {
                    VolleyRequestUtils.obtainRequestQueueMap().put(httpParams.reqPageName, reqlist);
                }
            }
            enhanceOnResponse(VolleyResponseUtils.getTag(jsonObject),
                    VolleyResponseUtils.getHttpData(jsonObject).getJson(),
                    httpParams);
        }
    }
}
