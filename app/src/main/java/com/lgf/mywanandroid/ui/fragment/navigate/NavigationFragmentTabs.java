package com.lgf.mywanandroid.ui.fragment.navigate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lgf.mywanandroid.R;
import com.lgf.mywanandroid.lgf.base.fragment.BaseCompatFragment;
import com.lgf.mywanandroid.lgf.utils.StatusBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/9/11 0011.
 * desc :
 */
public class NavigationFragmentTabs extends BaseCompatFragment {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fl_navigate)
    FrameLayout mFlNavigate;
    Unbinder unbinder;

    public static NavigationFragmentTabs newInstance() {
        Bundle args = new Bundle();
        NavigationFragmentTabs fragment = new NavigationFragmentTabs();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_navigate_tabs;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        StatusBarUtils.fixToolbar(mToolbar, getActivity());

        if (findChildFragment(NavigationFragmentList.class) == null) {
            loadRootFragment(R.id.fl_navigate, NavigationFragmentList.newInstance());
        }
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
