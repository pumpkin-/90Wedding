package com.wedding.secretary.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.trade.TradeService;
import com.alibaba.sdk.android.trade.callback.TradeProcessCallback;
import com.alibaba.sdk.android.trade.model.TaokeParams;
import com.alibaba.sdk.android.trade.model.TradeResult;
import com.alibaba.sdk.android.trade.page.ItemDetailPage;
import com.wedding.secretary.R;
import com.wedding.secretary.base.BaseFragmentActivity;

/**
 * 婚礼进行时
 * Created by hmy on 2015/11/20.
 */
public class UnderwayActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments_container_underway);

//        Navigate.startUnderway(this, 0);
        View view = View.inflate(this,R.layout.test, null);
        showItemDetailPage(view);

    }

    public void showItemDetailPage(View view){
        TradeService tradeService = AlibabaSDK.getService(TradeService.class);
        ItemDetailPage itemDetailPage = new ItemDetailPage("37196464781", null);
        TaokeParams taokeParams = new TaokeParams(); //若非淘客taokeParams设置为null即可
        taokeParams.pid = "mm_28xxxx4_4xxxx71_151xxxxx5";
        tradeService.show(itemDetailPage, null, UnderwayActivity.this, null, new TradeProcessCallback() {

            @Override
            public void onFailure(int code, String msg) {
                Toast.makeText(UnderwayActivity.this, "失败 " + code + msg,
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPaySuccess(TradeResult tradeResult) {
                Toast.makeText(UnderwayActivity.this, "成功", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    @Override
    public void initListener() {
    }

    @Override
    public void onClick(View v) {
    }

}
