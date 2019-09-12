package com.lgf.mywanandroid.ui.widgets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.lgf.mywanandroid.R;
import com.lgf.mywanandroid.lgf.base.activity.BaseCompatActivity;
import com.lgf.mywanandroid.lgf.utils.StatusBarUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.View.VISIBLE;

public class ArticleWebActivity extends BaseCompatActivity {

    @BindView(R.id.iv_share)
    ImageView mIvShare;
    @BindView(R.id.iv_web_back)
    ImageView mIvWebBack;
    @BindView(R.id.tv_title)
    AppCompatTextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.webview)
    WebView mWebview;

    private String mUrl = "";   //链接地址

    private String mTitle="";


    private ProgressBar mProgressBar;//网页加载进度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_article_web);
        StatusBarUtils.fixToolbar(mToolbar, this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mProgressBar = new ProgressBar(this, null,
                android.R.attr.progressBarStyleHorizontal);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 8);
        mProgressBar.setLayoutParams(layoutParams);

        mWebview.addView(mProgressBar);

        initIntent();
        initWebView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_web;
    }


    private void initIntent() {
        Intent intent=getIntent();
        if (intent!=null){
            mTitle=intent.getStringExtra("title");
            mUrl=intent.getStringExtra("url");
        }
    }

    /**
     * 初始化webview并加载URL
     */
    private void initWebView() {

        mWebview.getSettings().setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        mWebview.getSettings().setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
        mWebview.getSettings().setDisplayZoomControls(true); //隐藏原生的缩放控件
        mWebview.getSettings().setBlockNetworkImage(false);//解决图片不显示
        mWebview.getSettings().setLoadsImagesAutomatically(true); //支持自动加载图片
        mWebview.getSettings().setDefaultTextEncodingName("utf-8");//设置编码格式
        mWebview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        // 获取网页加载进度
        mWebview.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mTvTitle.setText(view.getTitle());
                    mProgressBar.setVisibility(View.GONE);
                } else {

                    if (mProgressBar.getVisibility() == View.GONE)
                        mProgressBar.setVisibility(VISIBLE);
                    mProgressBar.setProgress(newProgress);
                    mTvTitle.setText("正在加载....");
                }
            }
        });

        mWebview.getSettings().setJavaScriptEnabled(true);

        mWebview.loadUrl(mUrl);

    }

    @OnClick({R.id.iv_share, R.id.iv_web_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_share:
                Intent textIntent = new Intent(Intent.ACTION_SEND);
                textIntent.setType("text/plain");
                textIntent.putExtra(Intent.EXTRA_TEXT, mUrl);
                startActivity(Intent.createChooser(textIntent, mTitle));
                break;
            case R.id.iv_web_back:
                finish();
                break;
        }
    }
}
