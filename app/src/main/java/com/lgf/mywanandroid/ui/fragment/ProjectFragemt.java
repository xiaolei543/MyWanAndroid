package com.lgf.mywanandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lgf.mywanandroid.R;
import com.lgf.mywanandroid.lgf.base.fragment.BaseCompatFragment;
import com.lgf.mywanandroid.ui.fragment.project.ProjectFragment_Tabs;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/6/4 0004.
 * desc :
 */
public class ProjectFragemt extends BaseCompatFragment {

    Unbinder unbinder;
    @BindView(R.id.fl_project)
    FrameLayout mFlProject;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        if (findChildFragment(ProjectFragment_Tabs.class) == null) {
            loadRootFragment(R.id.fl_project, ProjectFragment_Tabs.newInstance());
        }
    }

    /**
     * 提供Fragment实例
     *
     * @return
     */
    public static ProjectFragemt newInstance() {

        ProjectFragemt fragment = new ProjectFragemt();
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
