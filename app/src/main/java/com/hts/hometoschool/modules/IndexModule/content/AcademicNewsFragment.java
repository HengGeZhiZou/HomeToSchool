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
import com.hts.hometoschool.modules.IndexModule.ReadNewsActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AcademicNewsFragment extends Fragment {

    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> mapList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.news_content_fragment,container,false);
        listView=view.findViewById(R.id.news_contents);
        mapList=new ArrayList<>();

        simpleAdapter=new SimpleAdapter(view.getContext(),getMapList(),R.layout.news_item,new String[]{"title","date"},new  int[]{R.id.news_title,R.id.news_date});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(view.getContext(), ReadNewsActivity.class);
                startActivity(intent);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if ((listView.getCount()!=0) && (listView.getLastVisiblePosition()>=listView.getCount()-1)){
                    Map<String,Object> map=new HashMap<>();
                    map.put("title","王万民书记到心理学院督导本科叫...");
                    map.put("date","01.25");
                    mapList.add(map);
                    simpleAdapter.notifyDataSetChanged();
                }
            }
        });
        return view;


    }

    List<Map<String,Object>> getMapList(){
        for (int i=0;i<10;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("title","陈宁校长辅导学科建设规划及方案...");
            map.put("date","01.26");
            mapList.add(map);
        }
        return mapList;

    }

}
