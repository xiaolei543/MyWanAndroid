package com.lgf.mywanandroid.ui.fragment.project;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lgf.mywanandroid.R;
import com.lgf.mywanandroid.bean.ProjectTreeBean;
import com.lgf.mywanandroid.contract.project.ProjectFragment_TabsContract;
import com.lgf.mywanandroid.lgf.adapter.FragmentAdapter;
import com.lgf.mywanandroid.lgf.base.BasePresenter;
import com.lgf.mywanandroid.lgf.base.fragment.BaseMVPCompatFragment;
import com.lgf.mywanandroid.lgf.network.response.BaseData;
import com.lgf.mywanandroid.lgf.utils.StatusBarUtils;
import com.lgf.mywanandroid.presenter.project.ProjectFragment_TabsPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/6/9 0009.
 * desc :
 */
public class ProjectFragment_Tabs extends BaseMVPCompatFragment<ProjectFragment_TabsContract.Presenter> implements ProjectFragment_TabsContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tl_tabs)
    TabLayout mTlTabs;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.vp_fragment)
    ViewPager mVpFragment;

    Unbinder unbinder;

    private List<Fragment> fragments;

    public static ProjectFragment_Tabs newInstance(){
        Bundle args=new Bundle();
        ProjectFragment_Tabs fragment=new ProjectFragment_Tabs();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_project_tabs;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        StatusBarUtils.fixToolbar(mToolbar, getActivity());
        mPresenter.getProjectTree();
    }

    @Override
    public void onErrot(String msg) {

    }

    @Override
    public void onSuccess(BaseData<List<ProjectTreeBean>> bean1Response) {
        Log.d(TAG, "onSuccess: "+bean1Response.getData().size());
        for (int i=0;i<bean1Response.getData().size();i++){
            mTlTabs.addTab(mTlTabs.newTab().setText(bean1Response.getData().get(i).getName().toString()));

            fragments.add(ProjectFragment_list.newInstance(bean1Response.getData().get(i).getId()));
        }

        mVpFragment.setAdapter(new FragmentAdapter(getChildFragmentManager(),fragments));
        mVpFragment.setCurrentItem(0);
        // .setAdapter后才起作用
        mTlTabs.setupWithViewPager(mVpFragment);
        mTlTabs.setVerticalScrollbarPosition(0);
        //MODE_FIXED标签栏不可滑动，各个标签会平分屏幕的宽度
        mTlTabs.setTabMode(bean1Response.getData().size()<=4 ? TabLayout.MODE_FIXED: TabLayout.MODE_SCROLLABLE);
        mTlTabs.setTabIndicatorFullWidth(false);

        //tlTabs.setupWithViewPager方法内部会remove所有的tabs，这里重新设置一遍tabs的text，否则tabs的text不显示
        for (int j = 0; j < bean1Response.getData().size(); j++) {
            mTlTabs.getTabAt(j).setText(bean1Response.getData().get(j).getName().toString());

        }

    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return ProjectFragment_TabsPresenter.newInstance();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragments = new ArrayList<>();
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
