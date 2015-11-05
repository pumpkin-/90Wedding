package com.wedding.secretary.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wedding.secretary.application.LoadingUtils;
import com.wedding.secretary.networks.HttpUtils;
import com.wedding.secretary.networks.domain.HttpParams;
import com.wedding.secretary.networks.HttpResponse;
import com.wedding.secretary.networks.VolleyResponseUtils;
import com.wedding.secretary.utils.string.StringUtils;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by hmy on 2015/10/27.
 * Upadte by Byron on 2015/10/31
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener, View.OnTouchListener, HttpResponse {

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
        Log.d("JSON", jsonObject.toString());
        if (jsonObject == null) {
            Toast.makeText(getActivity(), "请求服务器失败", Toast.LENGTH_SHORT).show();
        } else {
            HttpParams httpParams = VolleyResponseUtils.getHttpParams(jsonObject);
            Toast.makeText(getActivity(), httpParams.reqPageName, Toast.LENGTH_SHORT).show();
            if (!StringUtils.isEmpty(httpParams.reqPageName)) {
                List<String> reqlist = HttpUtils.obtainRequestQueueMap().get(httpParams.reqPageName);
                reqlist.remove(httpParams.methodTag);
                if (reqlist.size() <= 0) {
                    LoadingUtils.dissmissLoadingDialog();
                } else {
                    HttpUtils.obtainRequestQueueMap().put(httpParams.reqPageName, reqlist);
                }
            }
            enhanceOnResponse(VolleyResponseUtils.getTag(jsonObject),
                    VolleyResponseUtils.getHttpData(jsonObject).getJson(),
                    httpParams);
        }
    }
}
