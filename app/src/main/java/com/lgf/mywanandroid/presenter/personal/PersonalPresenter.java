package com.lgf.mywanandroid.presenter.personal;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.lgf.mywanandroid.contract.personal.PersonalContract;
import com.lgf.mywanandroid.lgf.utils.AppUtils;
import com.lgf.mywanandroid.lgf.utils.FileUtils;
import com.lgf.mywanandroid.lgf.utils.MD5Utils;
import com.lgf.mywanandroid.model.personal.PersonalModel;

import java.io.File;

/**
 * Created by Administrator on 2019/6/5 0005.
 * desc :
 */
public class PersonalPresenter extends PersonalContract.Presenter {

    //请求相机
    private static final int REQUEST_CAMERA = 100;
    //请求相册
    private static final int REQUEST_PHOTO = 101;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;

    private File tempFile;

    public static final String HEAD_IMAGE_NAME = "wanAndroid";

    @NonNull
    public static PersonalPresenter newInstance() {
        return new PersonalPresenter();
    }

    @Override
    public void btnHeadClicked() {
        mIView.showPopupView();//显示弹出菜单
    }

    //调用相机
    @Override
    public void btnCameraClicked() {
        //创建拍照存储的图片文件
        tempFile = new File(FileUtils.checkDirPath(Environment.getExternalStorageDirectory()
                .getPath() + "/WanAndroidImage/"), MD5Utils.getMD5(HEAD_IMAGE_NAME) + System
                .currentTimeMillis() + ".jpg");
        //权限判断
        if (ContextCompat.checkSelfPermission(mIView.getActivity(), Manifest.permission
                .WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(mIView.getActivity(), new String[]{Manifest
                    .permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        } else {
            //跳转到调用系统相机
            mIView.gotoSystemCamera(tempFile, REQUEST_CAMERA);
        }
        //if (mIView.popupIsShowing())
            mIView.dismissPopupView();
    }

    //调用相册
    @Override
    public void btnPhotoClicked() {
        //权限判断
        if (ContextCompat.checkSelfPermission(mIView.getActivity(), Manifest.permission
                .READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //申请READ_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(mIView.getActivity(), new String[]{Manifest
                            .permission.READ_EXTERNAL_STORAGE},
                    READ_EXTERNAL_STORAGE_REQUEST_CODE);
        } else {
            //跳转到相册
            mIView.gotoSystemPhoto(REQUEST_PHOTO);
        }
       // if (mIView.popupIsShowing())
            mIView.dismissPopupView();
    }

    //点击取消按钮
    @Override
    public void btnCancelClicked() {
       // if (mIView.popupIsShowing())
            mIView.dismissPopupView();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                if (mIView != null)
                    mIView.gotoSystemCamera(tempFile, REQUEST_CAMERA);
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                if (mIView != null)
                    mIView.gotoSystemPhoto(REQUEST_PHOTO);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAMERA: //调用系统相机返回
                if (resultCode == Activity.RESULT_OK) {
                    mIView.gotoHeadSettingActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PHOTO:  //调用系统相册返回
                if (resultCode == Activity.RESULT_OK) {
                    Uri uri = intent.getData();
                    mIView.gotoHeadSettingActivity(uri);
                }
                break;
        }
    }

    @Override
    public PersonalContract.Model getModel() {
        return PersonalModel.newInstance();
    }

    @Override
    public void onStart() {
        //这里相当于提前做准备动作 可以上服务器提取图片。或者本地提取图片
        Uri headUri = Uri.fromFile(new File(mIView.getActivity().getCacheDir(), HEAD_IMAGE_NAME + ".jpg"));
        if (headUri != null) {
            String cropImagePath = FileUtils.getRealFilePathFromUri(AppUtils.getContext(), headUri);
            Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
            if (bitMap != null)
                mIView.showHead(bitMap);
        }

    }
}
