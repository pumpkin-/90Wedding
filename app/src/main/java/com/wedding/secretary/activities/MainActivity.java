package com.wedding.secretary.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wedding.secretary.R;
import com.wedding.secretary.adapters.HomeViewPagerAdapter;
import com.wedding.secretary.base.BaseFragmentActivity;
import com.wedding.secretary.fragments.MainFragments.CommunityFragment;
import com.wedding.secretary.fragments.MainFragments.HomeFragment;
import com.wedding.secretary.fragments.MainFragments.MineFragment;
import com.wedding.secretary.fragments.MainFragments.ToolsFragment;
import com.wedding.secretary.widgets.MViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 * Created by hmy on 2015/10/27.
 */
public class MainActivity extends BaseFragmentActivity {

    //控件
    @ViewInject(R.id.vp_main) //viewpager
    private MViewPager vp_main;

    @ViewInject(R.id.iv_main_home)  //首页
    private ImageView iv_main_home;
    @ViewInject(R.id.tv_main_home)
    private TextView tv_main_home;
    @ViewInject(R.id.ll_main_home)
    private LinearLayout ll_main_home;

    @ViewInject(R.id.iv_main_community) //社区
    private ImageView iv_main_community;
    @ViewInject(R.id.tv_main_community)
    private TextView tv_main_community;
    @ViewInject(R.id.ll_main_community)
    private LinearLayout ll_main_community;

    @ViewInject(R.id.iv_main_tools) //工具
    private ImageView iv_main_tools;
    @ViewInject(R.id.tv_main_tools)
    private TextView tv_main_tools;
    @ViewInject(R.id.ll_main_tools)
    private LinearLayout ll_main_tools;

    @ViewInject(R.id.iv_main_mine) //我的
    private ImageView iv_main_mine;
    @ViewInject(R.id.tv_main_mine)
    private TextView tv_main_mine;
    @ViewInject(R.id.ll_main_mine)
    private LinearLayout ll_main_mine;

    private List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.fragments_container_main);
        ViewUtils.inject(this);

        initListener();
        initFragment();
        initViewPager();
        initActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search: {
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViewPager() {
        vp_main.setOffscreenPageLimit(2); // 状态表示
        vp_main.setAdapter(new HomeViewPagerAdapter(getSupportFragmentManager(), fragments));
        //默认显示页设置为首页
        vp_main.setCurrentItem(0);
        setPageIndex(0);
        vp_main.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * 页卡切换监听
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int index) {
            switch (index) {
                case 0:
                    setPageIndex(0);
                    getActionBar().setTitle("首页");
                    break;
                case 1:
                    setPageIndex(1);
                    getActionBar().setTitle("社区");
                    break;
                case 2:
                    setPageIndex(2);
                    getActionBar().setTitle("工具");
                    break;
                case 3:
                    setPageIndex(3);
                    getActionBar().setTitle("我");
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

    /**
     * 底部按钮点击状态设置
     *
     * @param index
     */
    private void setPageIndex(int index) {
        //先初始化
        initButtomButtonBg();
        int pink = getResources().getColor(R.color.pink);
        switch (index) {
            case 0:
                tv_main_home.setTextColor(pink);
                iv_main_home.setBackgroundResource(R.mipmap.img_main_home_checked);
                break;
            case 1:
                tv_main_community.setTextColor(pink);
                iv_main_community.setBackgroundResource(R.mipmap.img_main_community_checked);
                break;
            case 2:
                tv_main_tools.setTextColor(pink);
                iv_main_tools.setBackgroundResource(R.mipmap.img_main_tools_checked);
                break;
            case 3:
                tv_main_mine.setTextColor(pink);
                iv_main_mine.setBackgroundResource(R.mipmap.img_main_mine_checked);
                break;
        }
    }

    /**
     * 初始化底部按钮背景
     */
    private void initButtomButtonBg() {
        int black = getResources().getColor(R.color.grey);
        tv_main_home.setTextColor(black);
        iv_main_home.setBackgroundResource(R.mipmap.img_main_home_unchecked);
        tv_main_community.setTextColor(black);
        iv_main_community.setBackgroundResource(R.mipmap.img_main_community_unchecked);
        tv_main_tools.setTextColor(black);
        iv_main_tools.setBackgroundResource(R.mipmap.img_main_tools_unchecked);
        tv_main_mine.setTextColor(black);
        iv_main_mine.setBackgroundResource(R.mipmap.img_main_mine_unchecked);
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        //首页
        HomeFragment homeFragment = new HomeFragment();
        fragments.add(homeFragment);
        //社区
        CommunityFragment communityFragment = new CommunityFragment();
        fragments.add(communityFragment);
        //工具
        ToolsFragment toolsFragment = new ToolsFragment();
        fragments.add(toolsFragment);
        //我的
        MineFragment mineFragment = new MineFragment();
        fragments.add(mineFragment);
    }

    @Override
    public void initListener() {
        ll_main_home.setOnClickListener(this);
        ll_main_community.setOnClickListener(this);
        ll_main_tools.setOnClickListener(this);
        ll_main_mine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //主页
        if (v == ll_main_home) {
            vp_main.setCurrentItem(0);
            setPageIndex(0);
        }
        //社区
        if (v == ll_main_community) {
            vp_main.setCurrentItem(1);
            setPageIndex(1);
        }
        //工具
        if (v == ll_main_tools) {
            vp_main.setCurrentItem(2);
            setPageIndex(2);
        }
        //我的
        if (v == ll_main_mine) {
            vp_main.setCurrentItem(3);
            setPageIndex(3);
        }
    }
}
