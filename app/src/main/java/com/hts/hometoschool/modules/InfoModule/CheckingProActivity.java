package com.hts.hometoschool.modules.InfoModule;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.hts.hometoschool.R;
import com.hts.hometoschool.api.Config;
import com.hts.hometoschool.api.NewsApi;
import com.hts.hometoschool.modules.ClassModule.part.ProCheckActivity;
import com.hts.hometoschool.pojo.HTSApp;
import com.hts.hometoschool.pojo.Project;
import com.hts.hometoschool.pojo.ResultHttp;
import com.hts.hometoschool.pojo.Students;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CheckingProActivity extends AppCompatActivity {

    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> mapList;
    private PtrFrameLayout ptrFrameLayout;
    private List<Project> projectList;
    private int currPage=1;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checking_pro_activity);
        setTitle();
        listView = findViewById(R.id.checking_li);
        mapList = new ArrayList<>();
        ptrFrameLayout=findViewById(R.id.checkingPtr);
        final MaterialHeader header = new MaterialHeader(this);
        HTSApp htsApp= (HTSApp)getApplication();
        Students s=htsApp.getStudents();
        final Students students=s;
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);//显示相关工具类，用于获取用户屏幕宽度、高度以及屏幕密度。同时提供了dp和px的转化方法。
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);

        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrameLayout.refreshComplete();
//刷新
                        mapList.clear();
                        getChecking(students.getStuNum(),currPage);
                        simpleAdapter.notifyDataSetChanged();
                    }
                }, 1800);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // 默认实现，根据实际情况做改动
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });


        getChecking(students.getStuNum(),currPage);
        simpleAdapter = new SimpleAdapter(this,mapList, R.layout.class_content_item, new String[]{"pic","type","title","student","hotImg","commentNum"},new int[]{
                R.id.class_item_img,R.id.class_type,R.id.class_title,R.id.student_name,R.id.hotImg,R.id.comment_num
        });
        listView.setAdapter(simpleAdapter);


    }

    public void setTitle() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //给状态栏设置颜色。
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    void getChecking(String stuId, int page) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.checkingPro())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        NewsApi service = retrofit.create(NewsApi.class);
        service.getCheckingPro(stuId,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultHttp<List<Project>>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ResultHttp<List<Project>> listResultHttp) {

                        if (listResultHttp.getCode() == 100 && currPage == 1) {
                            projectList = listResultHttp.getObj();
                            for (Project project : projectList) {
                                Map<String, Object> map = new HashMap<>();
                                if (project.getProType().equals("互联网+")){
                                    map.put("pic",R.mipmap.internet);
                                }
                                else if(project.getProType().equals("创新创业")){
                                    map.put("pic",R.mipmap.innovation);
                                }
                                else if(project.getProType().equals("学科竞赛")){
                                    map.put("pic",R.mipmap.science);
                                }
                                else if(project.getProType().equals("其他项目")){
                                    map.put("pic",R.mipmap.other);
                                }
                                map.put("type",project.getProType());
                                map.put("title",project.getProName());
                                map.put("student","组员："+project.getProTeamer());
                                map.put("hotImg",R.mipmap.calender);
                                map.put("commentNum","2018-05-16");
                                mapList.add(map);
                            }
                            listView.setAdapter(simpleAdapter);
                            simpleAdapter.notifyDataSetChanged();
                        } else if (listResultHttp.getCode() == 100 && currPage > 1) {
                            List<Project> list = listResultHttp.getObj();
                            for (Project project : list) {
                                Map<String, Object> map = new HashMap<>();
                                if (project.getProType()=="互联网+"){
                                    map.put("pic",R.mipmap.internet);
                                }
                                else if(project.getProType()=="创新创业"){
                                    map.put("pic",R.mipmap.innovation);
                                }
                                else if(project.getProType()=="学科竞赛"){
                                    map.put("pic",R.mipmap.science);
                                }
                                else if(project.getProType()=="其他项目"){
                                    map.put("pic",R.mipmap.other);
                                }
                                map.put("type",project.getProType());
                                map.put("title",project.getProName());
                                map.put("student",project.getProTeamer());
                                map.put("hotImg",R.mipmap.calender);
                                map.put("commentNum","2018-05-09");
                                mapList.add(map);
                            }
                            simpleAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}
