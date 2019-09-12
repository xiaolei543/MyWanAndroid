package com.lgf.mywanandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lgf.mywanandroid.R;
import com.lgf.mywanandroid.lgf.adapter.FragmentAdapter;
import com.lgf.mywanandroid.lgf.base.fragment.BaseCompatFragment;
import com.lgf.mywanandroid.ui.fragment.home.HomeFragment_111;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/6/4 0004.
 * desc :添加ViewPage 是为后期首页模块可能回添加更多功能
 */
public class HomeFragment extends BaseCompatFragment {

//    @BindView(R.id.home_banner)
//    ConvenientBanner mHomeBanner;
    @BindView(R.id.vp_fragment)
    ViewPager mVpFragment;
    Unbinder unbinder;


    private ArrayList<Integer> data=new ArrayList<>();

    private List<Fragment> fragments;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

        data.add(R.drawable.about);
        data.add(R.drawable.start);

        fragments=new ArrayList<>();

        fragments.add(HomeFragment_111.newInstance());

        mVpFragment.setAdapter(new FragmentAdapter(getChildFragmentManager(),fragments));


    }


    /**
     * 提供Fragment实例
     *
     * @return
     */
    public static HomeFragment newInstance() {

        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
