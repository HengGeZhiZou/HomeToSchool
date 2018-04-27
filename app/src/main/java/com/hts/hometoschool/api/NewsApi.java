package com.hts.hometoschool.api;


import com.hts.hometoschool.pojo.News;
import com.hts.hometoschool.pojo.ResultHttp;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface NewsApi {

    @GET("getNews/{type}/{currPage}.do")
    Observable<ResultHttp<List<News>>> getBlog(@Path("type") String type, @Path("currPage")int currPage);

}
