package com.wedding.secretary.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by hmy on 2015/10/27.
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected abstract void initListener();
}
