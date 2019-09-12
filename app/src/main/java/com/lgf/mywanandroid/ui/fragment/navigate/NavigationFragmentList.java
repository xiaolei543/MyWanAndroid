package com.lgf.mywanandroid.ui.fragment.navigate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.lgf.mywanandroid.R;
import com.lgf.mywanandroid.adapter.ItemType;
import com.lgf.mywanandroid.adapter.NavigationLeftAdapter;
import com.lgf.mywanandroid.adapter.NavigationRightAdapter;
import com.lgf.mywanandroid.adapter.RvAdapterUtils;
import com.lgf.mywanandroid.bean.NavigationListBean;
import com.lgf.mywanandroid.contract.navigate.NavigationFragmentListContract;
import com.lgf.mywanandroid.lgf.adapter.SimpleRecyclerAdapter;
import com.lgf.mywanandroid.lgf.base.BasePresenter;
import com.lgf.mywanandroid.lgf.base.fragment.BaseMVPCompatFragment;
import com.lgf.mywanandroid.presenter.navigate.NavigationFragmentListPresenter;
import com.lgf.mywanandroid.ui.widgets.ArticleWebActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/6/9 0009.
 * desc :
 */
public class NavigationFragmentList extends BaseMVPCompatFragment<NavigationFragmentListContract.Presenter> implements NavigationFragmentListContract.View {


    Unbinder unbinder;
    @BindView(R.id.rv_sort_left)
    RecyclerView leftRecyclerView;
    @BindView(R.id.rv_sort_right)
    RecyclerView rightRecyclerView;
    @BindView(R.id.inflate_view)
    LinearLayout mInflateView;

    private List<NavigationListBean>  navigateList=new ArrayList<>();
    private  List<NavigationListBean.ArticlesBean>  concreteList=new ArrayList<>();

    private final Map<Integer, Integer> indexMap = new HashMap<>();

    private NavigationLeftAdapter leftAdapter;

    private NavigationRightAdapter rightAdapter;

    private Gson gson;

    private AppCompatTextView tvLoad;
    /**
     * 提供Fragment实例
     *
     * @return
     */
    public static NavigationFragmentList newInstance() {
        NavigationFragmentList fragment = new NavigationFragmentList();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_navigate_list;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        mPresenter.getNavigationData();
    }

    @Override
    public void showNavigationListData(List<NavigationListBean> navigationListData) {
        gson=new Gson();
        navigateList.clear();
        navigateList.addAll(navigationListData);
        concreteList.clear();

        for(int i=0;i<navigationListData.size();i++){
            NavigationListBean.ArticlesBean articlesBean1=new NavigationListBean.ArticlesBean(true,navigationListData.get(i).getName());
            articlesBean1.position=i;
            articlesBean1.viewType = ItemType.BIG_SORT;
            concreteList.add(articlesBean1);

            for (int j=0;j<navigationListData.get(i).getArticles().size();j++){
                NavigationListBean.ArticlesBean articlesBean2=new NavigationListBean.ArticlesBean(false,navigationListData.get(i).getName());
                articlesBean2.position=-1;
                articlesBean2.viewType=ItemType.SMALL_SORT;
                articlesBean2.setTitle(navigationListData.get(i).getArticles().get(j).getTitle());
                articlesBean2.setLink(navigationListData.get(i).getArticles().get(j).getLink());
                concreteList.add(articlesBean2);
            }
        }

        // 点击左侧需要知道对应右侧的位置，用map先保存起来
        for (int i = 0; i < concreteList.size(); i++) {
            if (concreteList.get(i).position != -1) {
                indexMap.put(concreteList.get(i).position, i);
            }
        }

        // 左列表
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ((SimpleItemAnimator) leftRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        leftAdapter = new NavigationLeftAdapter();
        leftAdapter.setListData(navigateList);
        leftRecyclerView.setAdapter(leftAdapter);
        // 左侧列表的点击事件
        leftAdapter.setOnItemClickListener(new SimpleRecyclerAdapter.OnItemClickListener<NavigationListBean>() {
            @Override
            public void onItemClick(NavigationListBean item, int index) {
                // 左侧选中并滑到中间位置
                leftAdapter.setSelectedPosition(index);
                RvAdapterUtils.moveToMiddle(leftRecyclerView, index);
                // 右侧滑到对应位置
                ((GridLayoutManager)rightRecyclerView.getLayoutManager())
                        .scrollToPositionWithOffset(indexMap.get(index),0);
            }
        });

        // 右列表
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {
                if (concreteList.get(position).viewType == ItemType.BIG_SORT) {
                    return 3;
                } else {
                    return 1;
                }
            }
        });

        rightRecyclerView.setLayoutManager(gridLayoutManager);
        rightAdapter = new NavigationRightAdapter();
        rightAdapter.setListData(concreteList);
        rightRecyclerView.setAdapter(rightAdapter);
        rightAdapter.setOnItemClickListener(new SimpleRecyclerAdapter.OnItemClickListener<NavigationListBean.ArticlesBean>() {
            @Override
            public void onItemClick(NavigationListBean.ArticlesBean item, int index) {
                Bundle bundle = new Bundle();
                bundle.putString("title",item.getDesc());
                bundle.putString("url",item.getLink());
                startNewActivity(ArticleWebActivity.class,bundle);
            }
        });

        //右侧列表的滚动事件
        rightRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //获取右侧列表的第一个可见Item的position
                int topPosition = ((GridLayoutManager) rightRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                // 如果此项对应的是左边的大类的index
                if (concreteList.get(topPosition).position != -1) {
                    RvAdapterUtils.moveToMiddle(leftRecyclerView, concreteList.get(topPosition).position);
                    leftAdapter.setSelectedPosition(concreteList.get(topPosition).position);
                }

            }
        });
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return NavigationFragmentListPresenter.newInstance();
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
