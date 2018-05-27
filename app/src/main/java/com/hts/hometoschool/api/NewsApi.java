package com.hts.hometoschool.api;


import com.hts.hometoschool.pojo.News;
import com.hts.hometoschool.pojo.Project;
import com.hts.hometoschool.pojo.ResultHttp;
import com.hts.hometoschool.pojo.Students;
import com.hts.hometoschool.pojo.Teachers;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface NewsApi {

    @GET("getNews/{type}/{currPage}.do")
    Observable<ResultHttp<List<News>>> getBlog(@Path("type") String type, @Path("currPage")int currPage);

    @POST("upload.do")
    Observable<ResultHttp<String>> getProjectList(@Body Project project);

    @GET("getHotPro/{currPage}.do")
    Observable<ResultHttp<List<Project>>> getHotPro(@Path("currPage") int currPage);

    @GET("proPass/{proId}.do")
    Observable<ResultHttp<String>> passPro(@Path("proId") String proId);

    @GET("NoPassPro/{proId}/{rea}.do")
    Observable<ResultHttp<String>> NoPassPro(@Path("proId") String proId,@Path("rea") String rea);

    @GET("getLastPro/{currPage}.do")
    Observable<ResultHttp<List<Project>>> getLastPro(@Path("currPage") int currPage);

    @GET("getCheckingPro/{stuId}/{currPage}.do")
    Observable<ResultHttp<List<Project>>> getCheckingPro(@Path("stuId") String stuId,@Path("currPage") int currPage);

    @GET("getPersonNoPassPro/{stuId}/{currPage}.do")
    Observable<ResultHttp<List<Project>>> getPersonNoPassPro(@Path("stuId") String stuId,@Path("currPage") int currPage);

    @GET("getPersonPassPro/{stuId}/{currPage}.do")
    Observable<ResultHttp<List<Project>>> getPersonPassPro(@Path("stuId") String stuId,@Path("currPage") int currPage);

    @GET("userLogin/{username}/{password}.do")
    Observable<ResultHttp<Students>> stuLogin(@Path("username") String username,@Path("password") String password);

    @GET("userLogin/{username}/{password}.do")
    Observable<ResultHttp<Teachers>> teacLogin(@Path("username") String username, @Path("password") String password);
}
