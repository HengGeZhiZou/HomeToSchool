package com.hts.hometoschool.modules.GradesModule.part;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.hts.hometoschool.R;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GradesSearchFragment extends Fragment {

    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> mapList;
    private NiceSpinner yearChoose;
    private NiceSpinner termChoose;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.student_search_fragment,container,false);
       listView=view.findViewById(R.id.gradeSearch);
        yearChoose=view.findViewById(R.id.yearChoose);
        termChoose=view.findViewById(R.id.term);
        List<String> yearList=new LinkedList<>(Arrays.asList("2014-2015","2015-2016","2016-2017","2017-2018"));
        List<String> termList=new LinkedList<>(Arrays.asList("1","2"));
        yearChoose.attachDataSource(yearList);
        termChoose.attachDataSource(termList);
        mapList=new ArrayList<>();
        simpleAdapter=new SimpleAdapter(view.getContext(),getMapList(),R.layout.grades_item,new String[]{"course","score"}
        ,new int[]{R.id.course,R.id.score});
        listView.setAdapter(simpleAdapter);
       return view;
    }

    List<Map<String,Object>> getMapList(){

            Map<String,Object> map=new HashMap<>();
            map.put("course","高等数学（一）");
            map.put("score","88");
            mapList.add(map);
        Map<String,Object> map1=new HashMap<>();
        map1.put("course","计算机基础");
        map1.put("score","92");
        mapList.add(map1);
        Map<String,Object> map2=new HashMap<>();
        map2.put("course","大学物理");
        map2.put("score","89");
        mapList.add(map2);
        Map<String,Object> map3=new HashMap<>();
        map3.put("course","马克思基本原理");
        map3.put("score","99");
        mapList.add(map3);
        Map<String,Object> map4=new HashMap<>();
        map4.put("course","大学体育");
        map4.put("score","99");
        mapList.add(map4);
        Map<String,Object> map5=new HashMap<>();
        map5.put("course","数据结构");
        map5.put("score","98");
        mapList.add(map5);

        return mapList;
    }
}
