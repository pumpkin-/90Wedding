package com.wedding.secretary.activities;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.wedding.secretary.R;
import com.wedding.secretary.adapters.ImageChildAdapter;

public class ShowImagesActivity extends Activity {
    private GridView mGridView;
    private List<String> list;
    private ImageChildAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_show_images);

        mGridView = (GridView) findViewById(R.id.gv_show_images);
        list = getIntent().getStringArrayListExtra("data");

        adapter = new ImageChildAdapter(this, list, mGridView);
        mGridView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
      //  Toast.makeText(this, "选中" + adapter.getSelectItems().size() + " item", Toast.LENGTH_LONG).show();
        super.onBackPressed();
    }


}
