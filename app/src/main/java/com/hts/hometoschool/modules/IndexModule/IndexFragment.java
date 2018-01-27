package com.hts.hometoschool.modules.IndexModule;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;


import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.hts.hometoschool.R;
import com.hts.hometoschool.modules.IndexModule.content.AcademicNewsFragment;
import com.hts.hometoschool.modules.IndexModule.content.CSNewsFragment;
import com.hts.hometoschool.modules.IndexModule.content.DepartmentNewsFragment;
import com.hts.hometoschool.modules.IndexModule.content.NoticeNewsFragment;
import com.hts.hometoschool.util.TImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

public class IndexFragment extends Fragment{
    private Banner banner;
    private List<Integer> imgList;

    private PagerTabStrip pagerTabStrip;
    private List<String> titleList;
    private List<Fragment> fragmentList;
    private ViewPager viewPager;
    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.index_fragment,container,false);
       banner=view.findViewById(R.id.bannerImg);
        pagerTabStrip=view.findViewById(R.id.newsTitle);
       viewPager=view.findViewById(R.id.indexView);

        imgList=new ArrayList<>();
        initImg();
        fragmentList=new ArrayList<>();
        titleList=new ArrayList<>();
        fragmentList.add(new CSNewsFragment());
        fragmentList.add(new DepartmentNewsFragment());
        fragmentList.add(new AcademicNewsFragment());
        fragmentList.add(new NoticeNewsFragment());

        titleList.add("成师新闻");
        titleList.add("部门动态");
        titleList.add("学术动态");
        titleList.add("通知公告");


        pagerTabStrip.setDrawFullUnderline(false);
        pagerTabStrip.setTabIndicatorColor(R.color.title);
        NewsFragmentPagerAdapter newsFragmentPagerAdapter=new NewsFragmentPagerAdapter(getFragmentManager(),fragmentList,titleList);

        viewPager.setAdapter(newsFragmentPagerAdapter);


        return view;
    }



   private void initImg(){
        imgList.add(R.mipmap.index_01);
        imgList.add(R.mipmap.index_02);
        imgList.add(R.mipmap.index_03);
        imgList.add(R.mipmap.index_04);
        imgList.add(R.mipmap.index_05);
        banner.setImageLoader(new TImageLoader());
        banner.setBannerAnimation(Transformer.CubeOut);
        banner.setImages(imgList);
        banner.start();
    }
}
