package com.lgf.mywanandroid.ui.activity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lgf.mywanandroid.R;
import com.lgf.mywanandroid.bean.RxEventHeadBean;
import com.lgf.mywanandroid.lgf.base.activity.BaseCompatActivity;
import com.lgf.mywanandroid.lgf.network.schedulers.BaseSchedulerProvider;
import com.lgf.mywanandroid.lgf.network.schedulers.SchedulerProvider;
import com.lgf.mywanandroid.lgf.rxbus.RxBus;
import com.lgf.mywanandroid.lgf.utils.StatusBarUtils;
import com.lgf.mywanandroid.lgf.widgets.head.ClipViewLayout;
import com.lgf.mywanandroid.presenter.personal.PersonalPresenter;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

public class SelectHeadActivity extends BaseCompatActivity {

    @BindView(R.id.iv_toolbar_left)
    ImageView mIvToolbarLeft;
    @BindView(R.id.tv_title)
    AppCompatTextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.cvl_rect)
    ClipViewLayout mCvlRect;
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;
    @BindView(R.id.tv_ok)
    TextView mTvOk;
    @BindView(R.id.rl_bottom)
    RelativeLayout mRlBottom;

    private BaseSchedulerProvider schedulerProvider= SchedulerProvider.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_select_head);
    }

    @Override
    protected void initData() {
        super.initData();
        RxBus.get().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Logger.e("RxBus.get().unRegister(this)");
        RxBus.get().unRegister(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        StatusBarUtils.fixToolbar(mToolbar, this);//沉浸式状态栏bug 对应进行调整

        mCvlRect.setImageSrc(getIntent().getData());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_head;

    }

    @OnClick({R.id.iv_toolbar_left, R.id.tv_cancel, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_left:
                onBackPressedSupport();
                break;
            case R.id.tv_cancel:
                onBackPressedSupport();
                break;
            case R.id.tv_ok:
                Observable.create(new ObservableOnSubscribe<Uri>() {
                    @Override
                    public void subscribe(ObservableEmitter<Uri> e) throws
                            Exception {
                        e.onNext(generateUri());
                        e.onComplete();
                    }
                }).compose(schedulerProvider.<Uri>applySchedulers())
                        .subscribe(new Consumer<Uri>() {
                            @Override
                            public void accept(Uri uri) throws Exception {
                                RxEventHeadBean rxEventHeadBean = new RxEventHeadBean(uri);
                                RxBus.get().send(1001, rxEventHeadBean);
                                onBackPressedSupport();
                            }
                        });
                break;
        }
    }


    /**
     * 生成Uri
     */
    private Uri generateUri() {
        //调用返回剪切图
        Bitmap zoomedCropBitmap;
        zoomedCropBitmap = mCvlRect.clip();
        Uri mSaveUri = Uri.fromFile(new File(getCacheDir(), PersonalPresenter.HEAD_IMAGE_NAME + ".jpg"));
        if (mSaveUri != null) {
            OutputStream outputStream = null;
            try {
                outputStream = getContentResolver().openOutputStream(mSaveUri);
                if (outputStream != null) {
                    zoomedCropBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                }
            } catch (IOException ex) {
                Log.e("android", "Cannot open file: " + mSaveUri, ex);
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return mSaveUri;
    }
}
