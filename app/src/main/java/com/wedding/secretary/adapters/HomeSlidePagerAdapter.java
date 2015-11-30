package com.wedding.secretary.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wedding.secretary.utils.log.WeddingLog;

import java.util.List;

/**
 * 广告页ViewPager的适配器
 * Created by hmy on 2015/11/13.
 */
public class HomeSlidePagerAdapter extends PagerAdapter {
    private List<ImageView> imageViewsList;

    public HomeSlidePagerAdapter() {
    }

    public HomeSlidePagerAdapter(List<ImageView> imageViewsList) {
        this.imageViewsList = imageViewsList;
    }

    @Override
    public int getCount() {
        return imageViewsList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = imageViewsList.get(position);
        ImageLoader.getInstance().displayImage(imageView.getTag() + "", imageView);

        (container).addView(imageView);

        // 单击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeddingLog.d("广告页");
            }
        });
        return imageViewsList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        (container).removeView(imageViewsList.get(position));
    }
}
