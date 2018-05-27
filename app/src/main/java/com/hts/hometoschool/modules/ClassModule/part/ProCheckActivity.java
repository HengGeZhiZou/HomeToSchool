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
import android.widget.Toast;

import com.hts.hometoschool.R;
import com.hts.hometoschool.api.Config;
import com.hts.hometoschool.api.NewsApi;
import com.hts.hometoschool.pojo.News;
import com.hts.hometoschool.pojo.Project;
import com.hts.hometoschool.pojo.ResultHttp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ProCheckActivity extends AppCompatActivity {
    private Button button;
    private Button passB;
    private TextView pro_name;
    private TextView pro_teac;
    private TextView pro_team;
    private TextView pro_content;
    private TextView pro_type;
    private Context con;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_pro_check_activity);
        con=this;
        button=findViewById(R.id.noPass);
        passB=findViewById(R.id.check_pass);
        pro_name=findViewById(R.id.pro_check_name);
        pro_teac=findViewById(R.id.pro_check_teac);
        pro_team=findViewById(R.id.pro_check_teamer);
        pro_type=findViewById(R.id.pro_check_type);
        pro_content=findViewById(R.id.pro_check_cont);
        setTitle();
        final Project project= (Project) getIntent().getSerializableExtra("project");
        pro_name.setText(project.getProName());
        pro_teac.setText(project.getProTeac());
        pro_team.setText(project.getProTeamer());
        pro_content.setText(project.getProIntroduce());
        pro_type.setText(project.getProType());

        passB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passPro(project.getProId());
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),CheckNotes.class);
                intent.putExtra("project",project);
                startActivity(intent);
            }
        });
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

    void passPro(String proId) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
//                .cookieJar(CookieJarImpl())
                .addInterceptor(loggingInterceptor)
//                .addInterceptor(TokenInterceptor())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.proPass())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        NewsApi service = retrofit.create(NewsApi.class);
        service.passPro(proId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultHttp<String>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(ResultHttp<String> stringResultHttp) {
                        if (stringResultHttp.getCode() == 100) {
                            Toast.makeText(con,"审核完成",Toast.LENGTH_SHORT).show();

                        } else if (stringResultHttp.getCode() == 101) {
                            Toast.makeText(con,"审核失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
        ;
    }

}
