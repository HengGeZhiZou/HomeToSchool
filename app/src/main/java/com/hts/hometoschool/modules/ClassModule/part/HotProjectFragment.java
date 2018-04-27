package com.hts.hometoschool.modules.ClassModule.part;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

public class HotProjectFragment extends Fragment {
    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> mapList;
    private PtrFrameLayout ptrFrameLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.class_content_fragment, container, false);
        listView = view.findViewById(R.id.class_content);
        mapList = new ArrayList<>();

        ptrFrameLayout=view.findViewById(R.id.ptr);
        final MaterialHeader header = new MaterialHeader(view.getContext());

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
//                         mapList.clear();
//                        simpleAdapter.notifyDataSetChanged();
                    }
                }, 1800);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // 默认实现，根据实际情况做改动
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });



        simpleAdapter = new SimpleAdapter(view.getContext(),getMapList(), R.layout.class_content_item, new String[]{"pic","type","title","student","hotImg","commentNum"},new int[]{
                R.id.class_item_img,R.id.class_type,R.id.class_title,R.id.student_name,R.id.hotImg,R.id.comment_num
        });
        listView.setAdapter(simpleAdapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if (listView.getCount()!=0&&listView.getLastVisiblePosition()>=listView.getCount()-1){
                    Map<String,Object> map=new HashMap<>();
                    map.put("pic",R.mipmap.internet);
                    map.put("type","互联网+");
                    map.put("title","基于google人脸识别的签到google人脸识别的签到系统");
                    map.put("student","组员:罗友恒 张丽 马云");
                    map.put("hotImg",R.mipmap.chat);
                    map.put("commentNum","12");
                    mapList.add(map);
                    simpleAdapter.notifyDataSetChanged();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(view.getContext(),ClassContentActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private List<Map<String, Object>> getMapList(){
        Map<String,Object> map=new HashMap<>();
        map.put("pic",R.mipmap.internet);
        map.put("type","互联网+");
        map.put("title","基于google人脸识别的签到google人脸识别的签到系统");
        map.put("student","组员:罗友恒 张丽 马云");
        map.put("hotImg",R.mipmap.fire_first);
        map.put("commentNum","30");
        mapList.add(map);
        Map<String,Object> map1=new HashMap<>();
        map1.put("pic",R.mipmap.innovation);
        map1.put("type","创新创业");
        map1.put("title","使用SimpleAdapter来作为ListView适配器的时");
        map1.put("student","组员:罗友恒 张丽 马云");
        map1.put("hotImg",R.mipmap.fire_secend);
        map1.put("commentNum","28");
        mapList.add(map1);
        Map<String,Object> map2=new HashMap<>();
        map2.put("pic",R.mipmap.science);
        map2.put("type","学科竞赛");
        map2.put("title","您好，客户端代码链接openfire报了Failed to fetch RSS feed");
        map2.put("student","组员:范德萨 大苏打");
        map2.put("hotImg",R.mipmap.fire_third);
        map2.put("commentNum","20");
        mapList.add(map2);
        for (int i=0;i<=2;i++){
            Map<String,Object> map3=new HashMap<>();
            map3.put("pic",R.mipmap.internet);
            map3.put("type","互联网+");
            map3.put("title","基于google人脸识别的签到google人脸识别的签到系统");
            map3.put("student","组员:罗友恒 张丽 马云");
            map3.put("hotImg",R.mipmap.chat);
            map3.put("commentNum","18");
            mapList.add(map3);
            Map<String,Object> map4=new HashMap<>();
            map4.put("pic",R.mipmap.other);
            map4.put("type","其他项目");
            map4.put("title","您好，客户端代码链接op本次我们来讲解一下Android应用中三个适配");
            map4.put("student","组员:范德萨 大苏打");
            map4.put("hotImg",R.mipmap.chat);
            map4.put("commentNum","12");
            mapList.add(map4);
        }
        return mapList;
    }

}
