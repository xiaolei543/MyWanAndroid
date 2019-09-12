package com.lgf.mywanandroid.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.lgf.mywanandroid.R;
import com.lgf.mywanandroid.lgf.base.activity.BaseCompatActivity;
import com.lgf.mywanandroid.ui.fragment.HomeFragment;
import com.lgf.mywanandroid.ui.fragment.NavigateFragment;
import com.lgf.mywanandroid.ui.fragment.PersonalFragment;
import com.lgf.mywanandroid.ui.fragment.ProjectFragemt;

import butterknife.BindView;

public class MainActivity extends BaseCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.fl_main)
    FrameLayout mFlMain;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;


    // Fragment管理器，和执行器
    private FragmentManager mManager;
    private FragmentTransaction mTransaction;

    private HomeFragment mHomeFragment;

    private PersonalFragment mPersonalFragment;

    private NavigateFragment mNavigateFragment;

    private ProjectFragemt mProjectFragemt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mManager =getSupportFragmentManager();

        // TODO 设置模式
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        // TODO 设置背景色样式
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setBarBackgroundColor(R.color.white);

        initBottomNavigationBar();
    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onTabSelected(int position) {
        //开启事务
        mTransaction = mManager.beginTransaction();
        hideFragment(mTransaction);

        /**
         * fragment 用 add + show + hide 方式
         * 只有第一次切换会创建fragment，再次切换不创建
         *
         * fragment 用 replace 方式
         * 每次切换都会重新创建
         *
         */
        switch (position){
            case 0:   // 首页

                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance();
                    mTransaction.add(R.id.fl_main, mHomeFragment);
                } else {
                    mTransaction.show(mHomeFragment);
                }
                break;
            case 1:    // 项目
                if (mProjectFragemt == null) {
                    mProjectFragemt =  mProjectFragemt.newInstance();
                    mTransaction.add(R.id.fl_main,
                            mProjectFragemt);
                } else {
                    mTransaction.show(mProjectFragemt);
                }
                break;
            case 2:  // 导航
                if (mNavigateFragment == null) {
                    mNavigateFragment = NavigateFragment.newInstance();
                    mTransaction.add(R.id.fl_main,
                            mNavigateFragment);
                } else {
                    mTransaction.show(mNavigateFragment);
                }
                break;
            case 3:  // 我的
                if (mPersonalFragment == null) {
                    mPersonalFragment = mPersonalFragment.newInstance();
                    mTransaction.add(R.id.fl_main,
                            mPersonalFragment);
                } else {
                    mTransaction.show(mPersonalFragment);
                }
                break;
        }
        // 事务提交
        mTransaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }


    private void initBottomNavigationBar() {
        /**
         *  new BottomNavigationItem(R.mipmap.tab_home_pressed,"首页") ,选中的图标，文字
         *  setActiveColorResource：选中的颜色
         *  setInactiveIconResource：未选中的图标
         *  setInActiveColorResource：未选中的颜色
         */

        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_main_home1, "首页").setActiveColorResource(R.color.txt_link_blue).setInactiveIconResource(R.mipmap.ic_main_home2).setInActiveColorResource(R.color.txt_black))
                .addItem(new BottomNavigationItem(R.mipmap.ic_main_project1, "项目").setActiveColorResource(R.color.txt_link_blue).setInactiveIconResource(R.mipmap.ic_main_project2).setInActiveColorResource(R.color.txt_black))
                .addItem(new BottomNavigationItem(R.mipmap.ic_main_navigate1, "导航").setActiveColorResource(R.color.txt_link_blue).setInactiveIconResource(R.mipmap.ic_main_navigate2).setInActiveColorResource(R.color.txt_black))
                .addItem(new BottomNavigationItem(R.mipmap.ic_main_me1, "我的").setActiveColorResource(R.color.txt_link_blue).setInactiveIconResource(R.mipmap.ic_main_me2).setInActiveColorResource(R.color.txt_black))
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(this);
        mBottomNavigationBar.selectTab(0);
    }


    /**
     * 隐藏当前fragment
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction){
        if (mHomeFragment != null){
            transaction.hide(mHomeFragment);
        }

        if (mPersonalFragment != null){
            transaction.hide(mPersonalFragment);
        }

        if (mProjectFragemt!=null){
            transaction.hide(mProjectFragemt);
        }
        if (mNavigateFragment!=null){
            transaction.hide(mNavigateFragment);
        }
    }


}
