package com.hts.hometoschool.modules.ClassModule.part;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.hts.hometoschool.R;
import com.hts.hometoschool.pojo.Project;

public class LastProActivity  extends AppCompatActivity {

    private TextView pro_name;
    private TextView pro_teac;
    private TextView pro_team;
    private TextView pro_content;
    private TextView pro_type;
    private Context con;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lastpro_activity);
        con = this;
        pro_name = findViewById(R.id.lastPro_name);
        pro_teac = findViewById(R.id.lastPro_teac);
        pro_team = findViewById(R.id.lastPro_team);
        pro_type = findViewById(R.id.lastPro_type);
        pro_content = findViewById(R.id.lastPro_content);

        setTitle();
        final Project project = (Project) getIntent().getSerializableExtra("project");
        pro_name.setText(project.getProName());
        pro_teac.setText(project.getProTeac());
        pro_team.setText(project.getProTeamer());
        pro_content.setText(project.getProIntroduce());
        pro_type.setText(project.getProType());

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


