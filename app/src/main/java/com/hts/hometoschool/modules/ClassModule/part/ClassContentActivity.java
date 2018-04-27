package com.hts.hometoschool.modules.ClassModule.part;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.hts.hometoschool.R;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassContentActivity extends AppCompatActivity {

    private SimpleAdapter simpleAdapter;
    private SimpleAdapter commentAdapter;
    private ListView listView;
    private List<Map<String, Object>> mapList;
    private ListView comment;
    private List<Map<String, Object>> commentList;
    private ImageView imageGood;
    private ImageView imageComment;
    private ImageView imageCollect;
    private Boolean goodImg = false;
    private Boolean collectImg = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_content_activity);
        init();
        imageGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!goodImg) {
                    imageGood.setImageResource(R.mipmap.good_on);
                    goodImg = true;
                } else {
                    imageGood.setImageResource(R.mipmap.good_off);
                    goodImg = false;
                }
            }
        });
        imageCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!collectImg) {
                   imageCollect.setImageResource(R.mipmap.collect_on);
                    collectImg = true;
                } else {
                    imageCollect.setImageResource(R.mipmap.collect_off);
                    collectImg=false;
                }
            }
        });

        imageComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ClassContentActivity.this,CommentActivity.class);
                startActivity(intent);
            }
        });

        simpleAdapter = new SimpleAdapter(this, getMapList(), R.layout.content_img, new String[]{"pic"}, new int[]{R.id.class_content_img});
        commentAdapter = new SimpleAdapter(this, getCommentList(), R.layout.comment_list, new String[]{"img", "name", "content", "date"}, new int[]{R.id.comment_item_img, R.id.comment_name, R.id.comment_content, R.id.comment_date});
        listView.setAdapter(simpleAdapter);
        comment.setAdapter(commentAdapter);
        setListViewHeightBasedOnChildren(listView);
        setListViewHeightBasedOnChildren(comment);
    }

    public void setTitle() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //给状态栏设置颜色。
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }


    void init() {
        setTitle();
        mapList = new ArrayList<>();
        commentList = new ArrayList<>();
        listView = findViewById(R.id.img_list);
        comment = findViewById(R.id.comment_list);
        imageGood = findViewById(R.id.imgGood);
        imageCollect = findViewById(R.id.imgCollect);
        imageComment = findViewById(R.id.imgComment);
    }

    List<Map<String, Object>> getMapList() {
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("pic", R.mipmap.jz);
            mapList.add(map);
            System.out.println("===============================");
        }
        return mapList;
    }

    List<Map<String, Object>> getCommentList() {
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("img", R.mipmap.default_user);
            map.put("name", "赵四");
            map.put("content", "哇，好帅啊，学习学习");
            map.put("date", "2018-1-31");
            commentList.add(map);
        }
        return commentList;
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

}
