package com.hts.hometoschool.modules.ClassModule.part;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.hts.hometoschool.R;
import com.hts.hometoschool.modules.ClassModule.ProjectIntroduceActivity;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class ProjectApplyFragment extends Fragment {
    private NiceSpinner niceSpinner;
    private RelativeLayout relativeLayout;
    private RelativeLayout relativeImgLayout;
    private Button button;
    private  List<Uri> mSelected;
    private Context context;
    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.class_project_apply_fragment,container,false);
        context=view.getContext();
        niceSpinner=view.findViewById(R.id.project_type_choose);
        List<String> dataset = new LinkedList<>(Arrays.asList("互联网+", "创新创业", "学科竞赛", "其他项目"));

        niceSpinner.attachDataSource(dataset);

        relativeLayout=view.findViewById(R.id.project_introduce_tab);
        relativeImgLayout=view.findViewById(R.id.project_img_tab);

        button=view.findViewById(R.id.projectApplyButton);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(),ProjectIntroduceActivity.class),1);
            }
        });
        relativeImgLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImgChoose();
            }
        });

        return  view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1||resultCode==2){

            if (data!=null){
                String text=data.getStringExtra("value");
                Toast.makeText(context,"添加简介成功",Toast.LENGTH_LONG).show();
                System.out.println("------------------"+text);
            }
        }

        else if (requestCode == 23 && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            Log.d("Matisse", "mSelected: " + mSelected);
        }


    }

    void ImgChoose(){
        Matisse.from(ProjectApplyFragment.this)
                .choose(MimeType.ofImage())//照片视频全部显示
                .countable(true)//有序选择图片
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .maxSelectable(9)//最大选择数量为9
                .gridExpectedSize(350) //图片显示表格的大小.getResources().getDimensionPixelSize(R.dimen.grid_expected_size)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)//图像选择和预览活动所需的方向。
                .thumbnailScale(0.85f)//缩放比例
                .theme(R.style.Matisse_HTS)//主题  暗色主题 R.style.Matisse_Dracula
                .imageEngine(new GlideEngine())//加载方式
                .forResult(23);//请求码
    }
}
