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
        for (int i=0;i<8;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("course","高等数学（一）");
            map.put("score","77");
            mapList.add(map);
        }
        return mapList;
    }
}
