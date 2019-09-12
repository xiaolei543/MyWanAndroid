package com.lgf.mywanandroid.ui.fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lgf.mywanandroid.R;
import com.lgf.mywanandroid.bean.RxEventHeadBean;
import com.lgf.mywanandroid.contract.personal.PersonalContract;
import com.lgf.mywanandroid.lgf.base.BasePresenter;
import com.lgf.mywanandroid.lgf.base.fragment.BaseMVPCompatFragment;
import com.lgf.mywanandroid.lgf.rxbus.RxBus;
import com.lgf.mywanandroid.lgf.rxbus.Subscribe;
import com.lgf.mywanandroid.lgf.utils.AppUtils;
import com.lgf.mywanandroid.lgf.utils.FileUtils;
import com.lgf.mywanandroid.lgf.utils.SpUtils;
import com.lgf.mywanandroid.lgf.widgets.head.HeadDialog;
import com.lgf.mywanandroid.presenter.personal.PersonalPresenter;
import com.lgf.mywanandroid.ui.activity.AboutActivity;
import com.lgf.mywanandroid.ui.activity.LoginActivity;
import com.lgf.mywanandroid.ui.activity.SelectHeadActivity;
import com.lgf.mywanandroid.ui.activity.SettingActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2019/6/4 0004.
 * desc :
 */
public class PersonalFragment extends BaseMVPCompatFragment<PersonalContract.Presenter> implements PersonalContract.View {

    @BindView(R.id.personal_head)
    CircleImageView mPersonalHead;
    @BindView(R.id.personal_name)
    TextView mPersonalName;
    @BindView(R.id.personal_about)
    LinearLayout mPersonalAbout;
    @BindView(R.id.personal_Setting)
    LinearLayout mPersonalSetting;
    Unbinder unbinder;


    HeadDialog mHeadDialog;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        mHeadDialog=new HeadDialog(getContext(),R.style.BottomDialog);
        initPopupView();
        RxBus.get().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }

    /**
     * 提供Fragment实例
     *
     * @return
     */
    public static PersonalFragment newInstance() {
        PersonalFragment fragment = new PersonalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPersonalName.setText(SpUtils.getString(mContext,"username","登陆"));
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


    @OnClick({R.id.personal_head, R.id.personal_name, R.id.personal_about, R.id.personal_Setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_head:
                mPresenter.btnHeadClicked();
                break;
            case R.id.personal_name:
                String isLogin=SpUtils.getString(mContext,"username","登陆");
                if(isLogin.equals("登陆"))
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.personal_about:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.personal_Setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
        }
    }

    @Override
    public void initPopupView() {
        //相机
        mHeadDialog.setOnBtn_cameraListener(new HeadDialog.onBtn_cameraListener() {
            @Override
            public void onCamera() {
                mPresenter.btnCameraClicked();
            }
        });
        //图库
        mHeadDialog.setOnBtn_photokListener(new HeadDialog.onBtn_photokListener() {
            @Override
            public void onPhoto() {
                mPresenter.btnPhotoClicked();
            }
        });
        //取消
        mHeadDialog.setOnBtn_cancelListener(new HeadDialog.onBtn_cancelListener() {
            @Override
            public void onCancel() {
                mPresenter.btnCancelClicked();
            }
        });
    }

    @Override
    public void showHead(Bitmap bitmap) {
        mPersonalHead.setImageBitmap(bitmap);
    }


    @Override
    public void showPopupView() {
        mHeadDialog.show();
    }

    @Override
    public void dismissPopupView() {
        mHeadDialog.dismiss();
    }

    @Override
    public boolean popupIsShowing() {
        return false;
    }

    //前往设置头像界面
    @Override
    public void gotoHeadSettingActivity(Uri uri) {
        if (uri==null){
            return;
        }

        Intent intent = new Intent();
        intent.setClass(mActivity, SelectHeadActivity.class);
        intent.setData(uri);
        startActivity(intent);

    }

    @Override
    public void gotoSystemPhoto(int requestCode) {
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media
                .EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), requestCode);
    }

    @Override
    public void gotoSystemCamera(File tempFile, int requestCode) {
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml，下面2种方式都可以
            //            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            //            Uri contentUri = FileProvider.getUriForFile(mActivity, BuildConfig
            // .APPLICATION_ID + "" +
            //                    ".fileProvider", tempFile);
            //            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);

            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, tempFile
                    .getAbsolutePath());
            Uri uri = getContext().getContentResolver().insert(MediaStore.Images
                    .Media.EXTERNAL_CONTENT_URI, contentValues);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, requestCode);
    }

    //得到p实例
    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return PersonalPresenter.newInstance() ;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPresenter.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }



    /**
     * RxBus接收图片Uri
     *
     * @param bean RxEventHeadBean
     */
    @Subscribe(code = 1001)
    public void rxBusEvent(RxEventHeadBean bean) {
        Uri uri = bean.getUri();
        if (uri == null) {
            return;
        }
        String cropImagePath = FileUtils.getRealFilePathFromUri(AppUtils.getContext(), uri);
        Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
        if (bitMap != null)
            mPersonalHead.setImageBitmap(bitMap);
    }

}
