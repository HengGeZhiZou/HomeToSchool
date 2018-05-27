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

public class ExcellentStudentsFragment extends Fragment {
    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> mapList;
    private NiceSpinner majorChoose;
    private NiceSpinner termChoose;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.excellent_student_fragment,container,false);
        listView=view.findViewById(R.id.gradesRank);
        majorChoose=view.findViewById(R.id.majorChoose);
        termChoose=view.findViewById(R.id.termChoose);
        mapList=new ArrayList<>();
        List<String> major=new LinkedList<>(Arrays.asList("计算机科学与技术","电子商务","计算机应用技术","数字媒体技术"));
        List<String> term=new LinkedList<>(Arrays.asList("1","2","3","4","5","6","7","8"));
        majorChoose.attachDataSource(major);
        termChoose.attachDataSource(term);
        simpleAdapter=new SimpleAdapter(view.getContext(),getMapList(),R.layout.rank_item,new String[]{"rank","stupic","name","class","grades"},new int[]{
                R.id.gradesRank,R.id.rank_stu_pic,R.id.rank_stu_name,R.id.rank_class,R.id.rank_grades
        });
        listView.setAdapter(simpleAdapter);
        return view;
    }
    List<Map<String,Object>> getMapList(){
        for (int i=1;i<=10;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("rank",i);
            map.put("stupic",R.mipmap.default_user);
            map.put("name","马云");
            map.put("class","2014级2班");
            map.put("grades",88-i);
            mapList.add(map);
        }
        return mapList;
    }

}
