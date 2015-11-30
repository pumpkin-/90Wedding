package com.wedding.secretary.widgets;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.lang.reflect.Field;

/**
 * Created by hmy on 2015/11/13.
 */
public class MViewPager extends ViewPager {

    public MViewPager(Context context) {
        super(context);
    }

    public MViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewPagerScroll(this);
    }

    /**
     * 设置ViewPager的滑动速度
     */
    private void initViewPagerScroll(ViewPager viewPager) {
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            ViewPagerScroller scroller = new ViewPagerScroller(viewPager.getContext());
            mScroller.set(viewPager, scroller);
        } catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
    }
}
