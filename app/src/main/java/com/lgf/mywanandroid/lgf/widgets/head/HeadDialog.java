package com.lgf.mywanandroid.lgf.widgets.head;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.lgf.mywanandroid.R;

/**
 * Created by Administrator on 2019/9/5 0005.
 * desc :
 */
public class HeadDialog extends Dialog {

    private TextView btn_camera , btn_photo, btn_cancel;

    private onBtn_cameraListener mOnBtn_cameraListener;//取消按钮被点击了的监听器
    private onBtn_photokListener mOnBtn_photokListener;//确定按钮被点击了的监听器
    private onBtn_cancelListener mOnBtn_cancelListener;//确定按钮被点击了的监听器

    public HeadDialog(@NonNull Context context) {
        super(context);
    }

    public HeadDialog(Context context, @StyleRes int themeResId) {
        super(context,themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.head_dialog);
        getWindow().setGravity(Gravity.BOTTOM);//设置显示在底部
        WindowManager windowManager=getWindow().getWindowManager();
        Display display= windowManager.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams=getWindow().getAttributes();
        layoutParams.width=display.getWidth();//设置Dialog的宽度为屏幕宽度
        getWindow().setAttributes(layoutParams);
        getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        //初始化界面控件
        initView();
        //初始化界面控件的事件
        initEvent();
    }



    private void initView() {
        btn_camera=findViewById(R.id.btn_camera);
        btn_photo=findViewById(R.id.btn_photo);
        btn_cancel=findViewById(R.id.btn_cancel);
    }

    private void initEvent() {
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnBtn_cameraListener!=null){
                    mOnBtn_cameraListener.onCamera();
                }
            }
        });

        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnBtn_photokListener!=null){
                    mOnBtn_photokListener.onPhoto();
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnBtn_cancelListener!=null){
                    mOnBtn_cancelListener.onCancel();
                }
            }
        });
    }

    //拍照接口
    public interface onBtn_cameraListener{
        public void onCamera();
    }

    //相册选取接口
    public interface onBtn_photokListener{
        public void onPhoto();
    }

    //取消接口
    public interface onBtn_cancelListener{
        public void onCancel();
    }


    /**
     * 设置拍照按钮的显示内容和监听
     */
    public void setOnBtn_cameraListener(onBtn_cameraListener  onBtn_cameraListener){
        this.mOnBtn_cameraListener=onBtn_cameraListener;
    }

    /**
     * 设置相册按钮的显示内容和监听
     */
    public void setOnBtn_photokListener(onBtn_photokListener onBtn_photokListener){
        this.mOnBtn_photokListener=onBtn_photokListener;
    }

    /**
     * 设置取消按钮的显示内容和监听
     */
    public void setOnBtn_cancelListener(onBtn_cancelListener onBtn_cancelListener){
        this.mOnBtn_cancelListener=onBtn_cancelListener;
    }
}
