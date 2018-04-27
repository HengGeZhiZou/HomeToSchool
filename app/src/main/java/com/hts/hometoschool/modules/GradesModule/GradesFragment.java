package com.hts.hometoschool.modules.GradesModule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.hts.hometoschool.R;
import com.hts.hometoschool.modules.ClassModule.ClassFragmentPagerAdapter;
import com.hts.hometoschool.modules.GradesModule.part.ExcellentStudentsFragment;
import com.hts.hometoschool.modules.GradesModule.part.GradesSearchFragment;

import java.util.ArrayList;
import java.util.List;

public class GradesFragment extends Fragment{

    private ViewPager viewPager;
    private List<String> titleList;
    private List<Fragment> fragmentList;
    private PagerSlidingTabStrip pagerSlidingTabStrip;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.grades_fragment,container,false);
        viewPager=view.findViewById(R.id.viewPagerGrades);
        pagerSlidingTabStrip=view.findViewById(R.id.tabGrades);

        titleList=new ArrayList<>();
        fragmentList=new ArrayList<>();

        titleList.add("优秀学生");
        titleList.add("成绩查询");

        fragmentList.add(new ExcellentStudentsFragment());
        fragmentList.add(new GradesSearchFragment());

        ClassFragmentPagerAdapter gradesFragmentAdapter=new ClassFragmentPagerAdapter(getFragmentManager(),fragmentList,titleList);
      viewPager.setAdapter(gradesFragmentAdapter);
        pagerSlidingTabStrip.setIndicatorColor(R.color.news_bar);
        pagerSlidingTabStrip.setIndicatorHeight(10);
        pagerSlidingTabStrip.setShouldExpand(true);
        pagerSlidingTabStrip.setViewPager(viewPager);

        return view;
    }
}
