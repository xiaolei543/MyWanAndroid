package com.lgf.mywanandroid.ApiService;

import com.lgf.mywanandroid.bean.BannerBean;
import com.lgf.mywanandroid.bean.HomeArticleBean;
import com.lgf.mywanandroid.bean.JavaBean1;
import com.lgf.mywanandroid.bean.JavaBean2;
import com.lgf.mywanandroid.bean.NavigationListBean;
import com.lgf.mywanandroid.bean.ProjectListBean;
import com.lgf.mywanandroid.bean.ProjectTreeBean;
import com.lgf.mywanandroid.bean.WxKeyBean;
import com.lgf.mywanandroid.bean.WxarticleBean;
import com.lgf.mywanandroid.lgf.network.response.BaseData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2019/5/31 0031.
 * desc :
 */
public interface Api {


    public static String HOST = "https://www.wanandroid.com/";

    //登陆
    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseData<JavaBean1>> login(@Field("username") String username,
                                          @Field("password") String password);

    //获取公众号信息
    @GET("wxarticle/chapters/json")
    Observable<BaseData<List<JavaBean2>>> getWxarticle();

    //获取一个公众号历史信息
    @GET("wxarticle/list/{eg1}/{eg2}/json")
    Observable<BaseData<WxarticleBean>> getWx122333(@Path("eg1") int eg1, @Path("eg2") int eg2);

    //搜索一个公众号的对应key内容
    @GET("wxarticle/list/{eg1}/{eg2}/json")
    Observable<BaseData<WxKeyBean>> getWxKey(@Path("eg1") int eg1, @Path("eg2") int eg2, @Query("k") String key);

    //退出登陆
    @GET("user/logout/json")
    Observable<BaseData<JavaBean1>> logout();

    //首页轮播图
    @GET("banner/json")
    Observable<BaseData<List<BannerBean>>> getBanner();

    //获取首页文章
    @GET("/article/list/{page}/json")
    Observable<BaseData<HomeArticleBean>> getHomeArticle(@Path("page") int page);

    //项目分类
    @GET("project/tree/json")
    Observable<BaseData<List<ProjectTreeBean>>> getProjectTree();

    //项目列表数据
    @GET("project/list/{page}/json")
    Observable<BaseData<ProjectListBean>> getProjectList(@Path("page")int page,@Query("cid") int cid);

    //导航数据
    @GET("navi/json")
    Observable<BaseData<List<NavigationListBean>>> getNavigation();
 }
