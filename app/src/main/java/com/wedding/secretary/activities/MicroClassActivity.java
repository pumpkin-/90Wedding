package com.wedding.secretary.activities;

import android.os.Bundle;
import android.view.View;

import com.wedding.secretary.R;
import com.wedding.secretary.base.BaseFragmentActivity;
import com.wedding.secretary.utils.common.Navigate;

/**
 * 婚礼微课堂
 * Created by hmy on 2015/11/20.
 */
public class MicroClassActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments_container_microclass);

        Navigate.startMicroClass(this, 0);
    }

    @Override
    public void initListener() {
    }

    @Override
    public void onClick(View v) {
    }

}
