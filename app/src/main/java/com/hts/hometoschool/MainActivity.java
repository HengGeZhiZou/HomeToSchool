package com.hts.hometoschool;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hts.hometoschool.modules.ClassModule.ClassFragment;
import com.hts.hometoschool.modules.GradesModule.GradesFragment;
import com.hts.hometoschool.modules.IndexModule.IndexFragment;
import com.hts.hometoschool.modules.InfoModule.InfoFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;

    private RadioButton radioButtonIndex;
    private RadioButton radioButtonClass;
    private RadioButton radioButtonGrades;
    private RadioButton radioButtonInfo;

    private IndexFragment indexFragment;
    private ClassFragment classFragment;
    private GradesFragment gradesFragment;
    private InfoFragment infoFragment;

//    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle();
        bindView();
    }

    private void bindView() {
        radioGroup = findViewById(R.id.menu_bar);
        radioGroup.setOnCheckedChangeListener(this);

//        textView=findViewById(R.id.title_bar);

        radioButtonIndex = findViewById(R.id.btIndex);
        radioButtonClass = findViewById(R.id.btClass);
        radioButtonGrades = findViewById(R.id.btGrades);
        radioButtonInfo = findViewById(R.id.btInfo);

        radioButtonIndex.setChecked(true);
    }


    public void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (indexFragment != null) {
            fragmentTransaction.hide(indexFragment);
        }
        if (classFragment != null) {
            fragmentTransaction.hide(classFragment);
        }
        if (gradesFragment != null) {
            fragmentTransaction.hide(gradesFragment);
        }
        if (infoFragment != null) {
            fragmentTransaction.hide(infoFragment);
        }


    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (i) {
            case R.id.btIndex:
                if (indexFragment==null){
                    indexFragment=new IndexFragment();
                    fragmentTransaction.add(R.id.frame,indexFragment);
                }else {
                    fragmentTransaction.show(indexFragment);
                }
//                textView.setText("成师快讯");
                break;
            case R.id.btClass:
               if (classFragment==null){
                   classFragment=new ClassFragment();
                   fragmentTransaction.add(R.id.frame,classFragment);
               }else {
                   fragmentTransaction.show(classFragment);
               }
//                textView.setText("第二课堂");
                break;
            case R.id.btGrades:
                if (gradesFragment==null){
                    gradesFragment=new GradesFragment();
                    fragmentTransaction.add(R.id.frame,gradesFragment);
                }else {
                    fragmentTransaction.show(gradesFragment);
                }
//                textView.setText("成绩查询");
                break;
            case R.id.btInfo:
                if (infoFragment==null){
                    infoFragment=new InfoFragment();
                     fragmentTransaction.add(R.id.frame,infoFragment);
                }else {
                    fragmentTransaction.show(infoFragment);
                }
//                textView.setText("个人信息");
                break;
        }
        fragmentTransaction.commit();
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
