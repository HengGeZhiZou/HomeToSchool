package com.hts.hometoschool.modules.InfoModule;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hts.hometoschool.R;
import com.hts.hometoschool.api.Config;
import com.hts.hometoschool.api.NewsApi;
import com.hts.hometoschool.pojo.HTSApp;
import com.hts.hometoschool.pojo.Project;
import com.hts.hometoschool.pojo.ResultHttp;
import com.hts.hometoschool.pojo.Students;
import com.hts.hometoschool.pojo.Teachers;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InfoFragment extends Fragment{
    private RelativeLayout infoRelativeLayout;
    private RelativeLayout checkingRelativeLayout;
    private RelativeLayout noPassRelativeLayout;
    private RelativeLayout passRelativeLayout;
    private TextView stuName;
    private TextView stuClass;
    private Button logout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.info_fragment,container,false);
        infoRelativeLayout=view.findViewById(R.id.updateInfo);
        checkingRelativeLayout=view.findViewById(R.id.checking_pro);
        noPassRelativeLayout=view.findViewById(R.id.noPassPro);
        passRelativeLayout=view.findViewById(R.id.passPro);
        stuName=view.findViewById(R.id.stu_name);
        stuClass=view.findViewById(R.id.stu_class);
        logout=view.findViewById(R.id.logout);
        final HTSApp htsApp= (HTSApp) getActivity().getApplication();
        String role=htsApp.checkRole();
        if(role.equals("teacher")){
            Teachers teachers=htsApp.getTeachers();
            stuName.setText(teachers.getTeacName());
            stuClass.setText(teachers.getTeacClass());

//            infoRelativeLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent=new Intent(view.getContext(),UpdateInfoActivity.class);
//                    intent.putExtra("teacher",t);
//                    startActivity(intent);
//                }
//            });
        }
        else if (role.equals("student")){
            Students s=htsApp.getStudents();
            final  Students students=s;
            stuName.setText(s.getStuName());
            stuClass.setText(s.getStuClass());

            infoRelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(view.getContext(),UpdateInfoActivity.class);
                    intent.putExtra("student",students);
                    startActivity(intent);
                }
            });
        }




        checkingRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),CheckingProActivity.class);
                startActivity(intent);
            }
        });

        noPassRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),NoPassProActivity.class);
                startActivity(intent);
            }
        });


        passRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),PassProActivity.class);
                startActivity(intent);
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                htsApp.logout();
                Intent intent=new Intent(view.getContext(),loginActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


}
