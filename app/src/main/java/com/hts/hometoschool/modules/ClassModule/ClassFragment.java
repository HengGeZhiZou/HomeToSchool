package com.hts.hometoschool.modules.ClassModule;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.hts.hometoschool.R;
import com.hts.hometoschool.modules.ClassModule.part.HotProjectFragment;
import com.hts.hometoschool.modules.ClassModule.part.LatestFragment;
import com.hts.hometoschool.modules.ClassModule.part.ProjectApplyFragment;
import com.hts.hometoschool.modules.ClassModule.part.ProjectCheckFragment;
import com.hts.hometoschool.pojo.HTSApp;
import com.hts.hometoschool.pojo.Students;

import java.util.ArrayList;
import java.util.List;

public class ClassFragment extends Fragment{
    private ViewPager viewPager;
    private List<String> titleList;
    private List<Fragment> fragmentList;
    private PagerSlidingTabStrip pagerSlidingTabStrip;
//    private PagerTabStrip pagerTabStrip;
    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.class_fragment,container,false);
        viewPager=view.findViewById(R.id.classView);
        pagerSlidingTabStrip=view.findViewById(R.id.tabs);
//        pagerTabStrip=view.findViewById(R.id.class_title);
        titleList=new ArrayList<>();
        fragmentList=new ArrayList<>();
        HTSApp htsApp= (HTSApp) getActivity().getApplication();
        String role=htsApp.checkRole();
        if (role.equals("teacher")){
            titleList.add("项目审核");
            fragmentList.add(new ProjectCheckFragment());
        }else if (role.equals("student")){
            Students s=htsApp.getStudents();
            if (s.getRole().equals("student")){
                titleList.add("项目申报");
                fragmentList.add(new ProjectApplyFragment());
            }
        }




        titleList.add("推荐项目");
        titleList.add("最新成果");

//

        fragmentList.add(new HotProjectFragment());
        fragmentList.add(new LatestFragment());



//        pagerTabStrip.setDrawFullUnderline(false);
//        pagerTabStrip.setTabIndicatorColor(R.color.title);
        ClassFragmentPagerAdapter classFragmentPagerAdapter=new ClassFragmentPagerAdapter(getFragmentManager(),fragmentList,titleList);

        viewPager.setAdapter(classFragmentPagerAdapter);
        viewPager.setCurrentItem(1);
//        pagerSlidingTabStrip.
        pagerSlidingTabStrip.setIndicatorColor(R.color.news_bar);
        pagerSlidingTabStrip.setIndicatorHeight(10);
        pagerSlidingTabStrip.setShouldExpand(true);
        pagerSlidingTabStrip.setViewPager(viewPager);
        return view;
}
}
