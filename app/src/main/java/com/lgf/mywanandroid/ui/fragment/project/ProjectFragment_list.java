package com.lgf.mywanandroid.ui.fragment.project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lgf.mywanandroid.R;
import com.lgf.mywanandroid.adapter.ProjectFragmentListAdapter;
import com.lgf.mywanandroid.bean.ProjectListBean;
import com.lgf.mywanandroid.contract.project.ProjectFragment_ListContract;
import com.lgf.mywanandroid.lgf.base.BasePresenter;
import com.lgf.mywanandroid.lgf.base.fragment.BaseRecycleFragment;
import com.lgf.mywanandroid.presenter.project.ProjectFragment_ListPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/6/9 0009.
 * desc :
 */
public class ProjectFragment_list extends BaseRecycleFragment<ProjectFragment_ListContract.Presenter> implements ProjectFragment_ListContract.View {

    @BindView(R.id.rv_projectList)
    RecyclerView mRvProjectList;
    @BindView(R.id.srf_projectList_refresh)
    SmartRefreshLayout mSrfProjectListRefresh;

    Unbinder unbinder;

    private int page = 0;

    private int mCid=0;

    private ProjectFragmentListAdapter projectAdapter;

    /**
     * 提供Fragment实例
     *
     * @return
     */
    public static ProjectFragment_list newInstance(int id) {

        ProjectFragment_list fragment = new ProjectFragment_list();
        Bundle args = new Bundle();
        args.putInt("cid", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_project_list;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        mCid = getArguments().getInt("cid");
        //初始化一个空list的adapter，网络错误时使用，第一次加载到数据时重新初始化adapter并绑定recycleview
        projectAdapter = new ProjectFragmentListAdapter(R.layout.item_fragment_project);

        mRvProjectList.setAdapter(projectAdapter);
        mRvProjectList.setLayoutManager(new LinearLayoutManager(mActivity));

        HomeSrfMainRefresh();//刷新控件

    }

    private void HomeSrfMainRefresh() {
        mSrfProjectListRefresh.setEnableAutoLoadMore(true);
        mSrfProjectListRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=0;
                mPresenter.loadRefreshList();
            }
        });

        mSrfProjectListRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.loadMoreList();
            }
        });
    }



    //第一次更新页面
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadLatestList();
    }

    @Override
    public void updateContentList(List<ProjectListBean.DatasBean> list, boolean Refresh) {
        if (projectAdapter.getData().size() == 0 ) {
            initRecycleView(list);
        } else if(Refresh){
            projectAdapter.replaceData(list);
        }else {
            projectAdapter.addData(list);
        }

        mSrfProjectListRefresh.finishRefresh();
        mSrfProjectListRefresh.finishLoadMore();
    }

    private void initRecycleView(List<ProjectListBean.DatasBean> list) {
        projectAdapter = new ProjectFragmentListAdapter(R.layout.item_fragment_project, list);
        projectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPresenter.onItemClick(position, (ProjectListBean.DatasBean) adapter.getItem(position));
            }
        });
        projectAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        mRvProjectList.setAdapter(projectAdapter);
    }

    @Override
    public void itemNotifyChanged(int position) {
        projectAdapter.notifyItemChanged(position);
    }

    @Override
    public void showNetworkError() {
        projectAdapter.setEmptyView(errorView);
    }

    @Override
    public void showLoadMoreError() {

    }

    @Override
    public void showNoMoreData() {
        projectAdapter.loadMoreEnd(false);
    }

    @Override
    protected void onErrorViewClick(View view) {
        mPresenter.loadLatestList();
    }

    @Override
    protected void showLoading() {
        projectAdapter.setEmptyView(loadingView);
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return ProjectFragment_ListPresenter.newInstance();
    }


    @Override
    public int Page() {
        return page;
    }

    @Override
    public int Cid() {
        return mCid;
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
