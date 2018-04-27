package com.hts.hometoschool.modules.IndexModule.content;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.hts.hometoschool.R;
import com.hts.hometoschool.api.Config;
import com.hts.hometoschool.api.NewsApi;
import com.hts.hometoschool.modules.IndexModule.ReadNewsActivity;
import com.hts.hometoschool.pojo.News;
import com.hts.hometoschool.pojo.ResultHttp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AcademicNewsFragment extends Fragment {

    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> mapList;
    private List<News> newsList;
    private int currPage = 1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.news_content_fragment,container,false);
        listView=view.findViewById(R.id.news_contents);
        mapList=new ArrayList<>();
        newsList = new ArrayList<>();

        getNews(currPage);
        simpleAdapter=new SimpleAdapter(view.getContext(),mapList,R.layout.news_item,new String[]{"title","date"},new  int[]{R.id.news_title,R.id.news_date});

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(view.getContext(), ReadNewsActivity.class);
                intent.putExtra("news", newsList.get(i));
                startActivity(intent);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if ((listView.getCount() != 0) && (listView.getLastVisiblePosition() >= listView.getCount() - 1)) {
                    currPage++;
                    getNews(currPage);
                }
            }
        });
        return view;


    }

    void getNews(final int currPage) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.getNews())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        NewsApi service = retrofit.create(NewsApi.class);
        service.getBlog("academic", currPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultHttp<List<News>>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ResultHttp<List<News>> listResultHttp) {

                        if (listResultHttp.getCode() == 100 && currPage == 1) {
                            newsList = listResultHttp.getObj();
                            for (News news : newsList) {
                                Map<String, Object> map = new HashMap<>();
                                map.put("title", news.getNewsTitle());
                                map.put("date", news.getNewsDate().substring(6, 11));
                                mapList.add(map);
                            }
                            listView.setAdapter(simpleAdapter);
                        } else if (listResultHttp.getCode() == 100 && currPage > 1) {
                            List<News> list = listResultHttp.getObj();
                            for (News news : list) {
                                newsList.add(news);
                                Map<String, Object> map = new HashMap<>();
                                map.put("title", news.getNewsTitle());
                                map.put("date", news.getNewsDate().substring(6, 11));
                                mapList.add(map);
                            }
                            simpleAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

}
