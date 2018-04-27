package com.hts.hometoschool.modules.IndexModule;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.hts.hometoschool.R;
import com.hts.hometoschool.pojo.News;

import org.w3c.dom.Text;

public class ReadNewsActivity extends AppCompatActivity {
    private TextView newsTitle;
    private TextView newsType;
    private TextView newsDate;
    private TextView newsContent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_read_activity);
        newsTitle=findViewById(R.id.news_title_index);
        newsType=findViewById(R.id.news_type);
        newsDate=findViewById(R.id.news_date_index);
        newsContent=findViewById(R.id.news_contents_index);
        setTitle();
        News news= (News) getIntent().getSerializableExtra("news");
        newsTitle.setText(news.getNewsTitle());
        newsType.setText(news.getNewsType());
        newsDate.setText(news.getNewsDate());
        newsContent.setText(news.getNewsContent());
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
}
