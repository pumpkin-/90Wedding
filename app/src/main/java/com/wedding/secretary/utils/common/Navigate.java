package com.wedding.secretary.utils.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.wedding.secretary.R;
import com.wedding.secretary.activities.DIYSceneActivity;
import com.wedding.secretary.activities.LoginActivity;
import com.wedding.secretary.activities.MainActivity;
import com.wedding.secretary.activities.MicroClassActivity;
import com.wedding.secretary.activities.PreparationActivity;
import com.wedding.secretary.activities.UnderwayActivity;
import com.wedding.secretary.base.BaseFragment;
import com.wedding.secretary.fragments.LoginFragments.InputPasswordFragment;
import com.wedding.secretary.fragments.LoginFragments.InputPhoneNumberFragment;
import com.wedding.secretary.fragments.LoginFragments.InputVerifyCodeFragment;
import com.wedding.secretary.fragments.LoginFragments.LoginFragment;
import com.wedding.secretary.fragments.LoginFragments.UserUpdateInfoFragment;
import com.wedding.secretary.fragments.StepsFragments.DIYSceneFragments.DIYSceneFragment;
import com.wedding.secretary.fragments.StepsFragments.MicroClassFragments.MicroClassFragment;
import com.wedding.secretary.fragments.StepsFragments.MicroClassFragments.MicroClassWebView;
import com.wedding.secretary.fragments.StepsFragments.PreparationFragments.PreparationFragment;
import com.wedding.secretary.fragments.StepsFragments.UnderwayFragments.UnderwayFragment;

/**
 * Created by hmy on 2015/11/16.
 */
public class Navigate {

    //从Activity置换容器
    public static void startContainer(FragmentActivity fromActivity, BaseFragment toFragment, int id, int action) {
        if (action > 0) {
            Bundle bundle = new Bundle();
            bundle.putInt("action", action);
            toFragment.setArguments(bundle);
        }
        FragmentManager fm = fromActivity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(toFragment.getClass().getSimpleName());
        if (fragment == null) {
            fm.beginTransaction()
                    .setCustomAnimations(R.anim.faded_in, R.anim.faded_out).
                    add(id, toFragment)
                    .commit();
        }
    }

    //从Fragment置换容器
    public static void startContainer(BaseFragment fromFragment, BaseFragment toFragment, int id, int action) {
        if (action > 0) {
            Bundle bundle = new Bundle();
            bundle.putInt("action", action);
            toFragment.setArguments(bundle);
        }
        FragmentManager fm = fromFragment.getActivity().getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(toFragment.getClass().getSimpleName());
        if (fragment == null) {
            fm.beginTransaction()
                    .setCustomAnimations(R.anim.faded_in, R.anim.faded_out).
                    add(id, toFragment, toFragment.getClass().getSimpleName()).
                    addToBackStack(fromFragment.getClass().getSimpleName())
                    .commit();
        }
    }

    //跳转至首页
    public static void startMain(Activity fromActivity) {
        Intent intent = new Intent(fromActivity, MainActivity.class);
        fromActivity.startActivity(intent);
    }

    public static void startMain(BaseFragment fromFragment) {
        Intent intent = new Intent(fromFragment.getActivity(), MainActivity.class);
        fromFragment.startActivity(intent);
    }

    //跳转至婚礼微课堂
    public static void startMicroClass(FragmentActivity fromActivity, int action) {
        MicroClassFragment toFragment = new MicroClassFragment();
        startContainer(fromActivity, toFragment, R.id.fragment_container_microclass, action);
    }

    public static void startMicroClassActivity(BaseFragment fromFragment) {
        Intent intent = new Intent(fromFragment.getActivity(), MicroClassActivity.class);
        fromFragment.startActivity(intent);
    }

    public static void startMicroClassFragment(BaseFragment fromFragment, int action) {
        MicroClassFragment toFragment = new MicroClassFragment();
        startContainer(fromFragment, toFragment, R.id.fragment_container_microclass, action);
    }

    //跳转至婚礼微课堂WebView
    public static void startMicroClassWebView(BaseFragment fromFragment, int action) {
        MicroClassWebView toFragment = new MicroClassWebView();
        startContainer(fromFragment, toFragment, R.id.fragment_container_microclass, action);
    }

    //跳转至婚前筹备
    public static void startPreparation(FragmentActivity fromActivity, int action) {
        PreparationFragment toFragment = new PreparationFragment();
        startContainer(fromActivity, toFragment, R.id.fragment_container_preparation, action);
    }

    public static void startPreparationActivity(BaseFragment fromFragment) {
        Intent intent = new Intent(fromFragment.getActivity(), PreparationActivity.class);
        fromFragment.startActivity(intent);
    }

    public static void startPreparationFragment(BaseFragment fromFragment, int action) {
        PreparationFragment toFragment = new PreparationFragment();
        startContainer(fromFragment, toFragment, R.id.fragment_container_preparation, action);
    }

    //跳转至DIY婚礼现场
    public static void startDIYScene(FragmentActivity fromActivity, int action) {
        DIYSceneFragment toFragment = new DIYSceneFragment();
        startContainer(fromActivity, toFragment, R.id.fragment_container_diyscene, action);
    }

    public static void startDIYSceneActivity(BaseFragment fromFragment) {
        Intent intent = new Intent(fromFragment.getActivity(), DIYSceneActivity.class);
        fromFragment.startActivity(intent);
    }

    public static void startDIYSceneFragment(BaseFragment fromFragment, int action) {
        DIYSceneFragment toFragment = new DIYSceneFragment();
        startContainer(fromFragment, toFragment, R.id.fragment_container_diyscene, action);
    }

    //跳转至婚礼进行时
    public static void startUnderway(FragmentActivity fromActivity, int action) {
        UnderwayFragment toFragment = new UnderwayFragment();
        startContainer(fromActivity, toFragment, R.id.fragment_container_underway, action);
    }

    public static void startUnderwayActivity(BaseFragment fromFragment) {
        Intent intent = new Intent(fromFragment.getActivity(), UnderwayActivity.class);
        fromFragment.startActivity(intent);
    }

    public static void startUnderwayFragment(BaseFragment fromFragment, int action) {
        UnderwayFragment toFragment = new UnderwayFragment();
        startContainer(fromFragment, toFragment, R.id.fragment_container_underway, action);
    }

    //跳转至登录界面
    public static void startLogin(FragmentActivity fromActivity, int action) {
        LoginFragment toFragment = new LoginFragment();
        startContainer(fromActivity, toFragment, R.id.fragment_container_login, action);
    }

    public static void startLoginActivity(BaseFragment fromFragment) {
        Intent intent = new Intent(fromFragment.getActivity(), LoginActivity.class);
        fromFragment.startActivity(intent);
    }

    public static void startLoginFragment(BaseFragment fromFragment, int action) {
        LoginFragment toFragment = new LoginFragment();
        startContainer(fromFragment, toFragment, R.id.fragment_container_login, action);
    }

    //跳转至输入手机号界面
    public static void startInputPhoneNumberFragment(BaseFragment fromFragment, int action) {
        InputPhoneNumberFragment toFragment = new InputPhoneNumberFragment();
        startContainer(fromFragment, toFragment, R.id.fragment_container_login, action);
    }

    //跳转至获取验证码界面
    public static void startGetVerifyCodeFragment(BaseFragment fromFragment, int action) {
        InputVerifyCodeFragment toFragment = new InputVerifyCodeFragment();
        startContainer(fromFragment, toFragment, R.id.fragment_container_login, action);
    }

    //跳转至设置密码界面
    public static void startSetPasswordFragment(BaseFragment fromFragment, int action) {
        InputPasswordFragment toFragment = new InputPasswordFragment();
        startContainer(fromFragment, toFragment, R.id.fragment_container_login, action);
    }

    //跳转至用户修改个人信息界面
    public static void startUserUpdateInfoFragment(BaseFragment fromFragment, int action) {
        UserUpdateInfoFragment toFragment = new UserUpdateInfoFragment();
        startContainer(fromFragment, toFragment, R.id.fragment_container_login, action);
    }

}
