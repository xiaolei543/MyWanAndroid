package com.lgf.mywanandroid.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.widget.TextView;

import com.lgf.mywanandroid.R;
import com.lgf.mywanandroid.lgf.base.activity.BaseCompatActivity;
import com.lgf.mywanandroid.lgf.utils.StringUtils;
import com.lgf.mywanandroid.lgf.utils.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 初始化页面
 *
 * 由于这个页面没有什么交互逻辑故不使用mvp模式
 */


public class GuideActivity extends BaseCompatActivity {

    @BindView(R.id.tv_final)
    TextView mTvFinal;


    private boolean mCancle;//判断是否点击跳过
    private int mTime = 3;//倒计时

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_guide);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        requestPermissions();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void onBackPressedSupport() {
        mCancle = true;
        setIsTransAnim(false);
        finish();
    }

    //权限申请
    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(GuideActivity.this);
        //请求权限全部结果
        rxPermission.request(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (!granted) {
                            ToastUtils.showToast("App未能获取全部需要的相关权限，部分功能可能不能正常使用.");
                        }
                        //不管是否获取全部权限，进入主页面
                        initCountDown();
                    }
                });
    }

    private void initCountDown() {
        Observable.interval(1, TimeUnit.SECONDS)
                .take(3)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return mTime-aLong;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        String s = String.valueOf(aLong);
                        if (mTvFinal != null)
                            mTvFinal.setText(StringUtils.isEmpty(s) ? "" : "跳过-"+s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if (!mCancle) {
                            startActivity(MainActivity.class);
                            finish();
                        }
                    }
                });

    }


    @OnClick(R.id.tv_final)
    public void onViewClicked() {
        mCancle=true;
        startActivity(MainActivity.class);
        finish();
    }
}
