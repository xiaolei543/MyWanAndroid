package com.lgf.mywanandroid.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lgf.mywanandroid.R;
import com.lgf.mywanandroid.adapter.HomeFragment_111Adapter;
import com.lgf.mywanandroid.adapter.NetworkImageHolderView;
import com.lgf.mywanandroid.bean.BannerBean;
import com.lgf.mywanandroid.bean.HomeArticleBean;
import com.lgf.mywanandroid.contract.home.HomeFragment_111.HomeFragment_111Contract;
import com.lgf.mywanandroid.lgf.base.BasePresenter;
import com.lgf.mywanandroid.lgf.base.fragment.BaseRecycleFragment;
import com.lgf.mywanandroid.presenter.home.HomeFragment_111.HomeFragment_111Presenter;
import com.lgf.mywanandroid.ui.widgets.ArticleWebActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/6/6 0006.
 * desc :
 */
public class HomeFragment_111 extends BaseRecycleFragment<HomeFragment_111Contract.Presenter> implements HomeFragment_111Contract.View{


    int page = 0; //返回当前页数

    @BindView(R.id.rv_home111)
    RecyclerView mRvHome111;
    @BindView(R.id.srf_main_refresh)
    SmartRefreshLayout mSrfMainRefresh;

    private ConvenientBanner convenientBanner;//顶部广告栏控件
    Unbinder unbinder;


    private HomeFragment_111Adapter mAdapter;//适配器


    public static HomeFragment_111 newInstance() {
        Bundle args = new Bundle();
        HomeFragment_111 fragment = new HomeFragment_111();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_111;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        //初始化一个空list的adapter，网络错误时使用，第一次加载到数据时重新初始化adapter并绑定recycleview
        mAdapter = new HomeFragment_111Adapter(R.layout.item_home_list);

        mRvHome111.setAdapter(mAdapter);
        mRvHome111.setLayoutManager(new LinearLayoutManager(mActivity));

        HomeSrfMainRefresh();//刷新控件
    }

    //第一次更新页面
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadLatestList();
    }


    @Override
    public int Page() {
        return page;
    }

    @Override
    public void setBanner(List<BannerBean> data) {
        initHeader(data);
    }

    //进行判断是否为第一次，或者刷新之后，或者加载更多
    @Override
    public void updateContentList(List<HomeArticleBean.DatasBean> list,boolean rfresh) {
        //showToast(""+list);
        if (mAdapter.getData().size() == 0 ) {
            initRecycleView(list);
            mPresenter.getBanner();//获取头部数据
        } else if(rfresh){
            mAdapter.replaceData(list);
        }else {
            mAdapter.addData(list);
        }

        mSrfMainRefresh.finishRefresh();
        mSrfMainRefresh.finishLoadMore();
    }


    //点击事件后，刷新item
    @Override
    public void itemNotifyChanged(int position) {
        mAdapter.notifyItemChanged(position);
    }

    //显示网络错误
    @Override
    public void showNetworkError() {
        mAdapter.setEmptyView(errorView);
    }

    //显示加载错误
    @Override
    public void showLoadMoreError() {

    }

    //显示没有更多数据
    @Override
    public void showNoMoreData() {
        mAdapter.loadMoreEnd(false);
    }

    //网络异常view被点击时触发
    @Override
    protected void onErrorViewClick(View view) {
        mPresenter.loadLatestList();
    }

    //显示加载中view
    @Override
    protected void showLoading() {
        mAdapter.setEmptyView(loadingView);
    }


    private void initRecycleView(List<HomeArticleBean.DatasBean> list) {
        mAdapter = new HomeFragment_111Adapter(R.layout.item_home_list, list);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPresenter.onItemClick(position, (HomeArticleBean.DatasBean) adapter.getItem(position));
            }
        });
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        mRvHome111.setAdapter(mAdapter);

        //添加recyclerview 头部
        View header = getLayoutInflater().inflate(R.layout.home_header, (ViewGroup) mRvHome111.getParent(), false);
        convenientBanner=header.findViewById(R.id.home_banner);
        mAdapter.addHeaderView(header,0);

    }


    private void initHeader(List<BannerBean> data){
        convenientBanner.startTurning(2000);
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        },data)//设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
        .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
        //轮播图点击事件
        .setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("title",data.get(position).getTitle());
                bundle.putString("url",data.get(position).getUrl());
                startNewActivity(ArticleWebActivity.class,bundle);
            }
        });
    }


    //设置刷新以及加载触发事件
    public void HomeSrfMainRefresh(){
        mSrfMainRefresh.setEnableAutoLoadMore(true);
        mSrfMainRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=0;
                mPresenter.loadRefreshList();
            }
        });

        mSrfMainRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.loadMoreList();
            }
        });
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return HomeFragment_111Presenter.newInstance();
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
