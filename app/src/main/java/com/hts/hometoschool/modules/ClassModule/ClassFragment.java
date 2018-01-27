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
import com.hts.hometoschool.modules.ClassModule.part.InnovationFragment;
import com.hts.hometoschool.modules.ClassModule.part.ScienceFragment;
import com.hts.hometoschool.modules.ClassModule.part.SubjectFragment;

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

        titleList.add("创新创业");
        titleList.add("学科竞赛");
        titleList.add("科研成果");


        fragmentList.add(new InnovationFragment());
        fragmentList.add(new SubjectFragment());
        fragmentList.add(new ScienceFragment());


//        pagerTabStrip.setDrawFullUnderline(false);
//        pagerTabStrip.setTabIndicatorColor(R.color.title);
        ClassFragmentPagerAdapter classFragmentPagerAdapter=new ClassFragmentPagerAdapter(getFragmentManager(),fragmentList,titleList);

        viewPager.setAdapter(classFragmentPagerAdapter);
//        pagerSlidingTabStrip.
        pagerSlidingTabStrip.setIndicatorColor(R.color.news_bar);
        pagerSlidingTabStrip.setIndicatorHeight(10);
        pagerSlidingTabStrip.setShouldExpand(true);
        pagerSlidingTabStrip.setViewPager(viewPager);
        return view;
    }
}
