package com.hts.hometoschool.modules.InfoModule;


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
import android.widget.EditText;
import android.widget.Toast;

import com.hts.hometoschool.MainActivity;
import com.hts.hometoschool.R;
import com.hts.hometoschool.api.Config;
import com.hts.hometoschool.api.NewsApi;
import com.hts.hometoschool.pojo.HTSApp;
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

public class loginActivity extends AppCompatActivity {

    private EditText userEd;
    private EditText passWEd;
    private Button loginButton;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        setTitle();
        userEd=findViewById(R.id.username);
        passWEd=findViewById(R.id.password);
        loginButton=findViewById(R.id.login);
        context=this;
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userEd.getText().toString().length()==12||userEd.getText().toString().length()==13){
                    userlogin(userEd.getText().toString(),passWEd.getText().toString());
                }
                else if(userEd.getText().toString().length()==6){
                    teacLogin(userEd.getText().toString(),passWEd.getText().toString());
                }else {
                    Toast.makeText(context,"账号不存在",Toast.LENGTH_SHORT).show();
                }

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

    void userlogin(String username,String password) {
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
                .baseUrl(Config.stuLogin())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        NewsApi service = retrofit.create(NewsApi.class);
        service.stuLogin(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultHttp<Students>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(ResultHttp<Students> stringResultHttp) {
                        if (stringResultHttp.getCode() == 100) {

                            Students students=  stringResultHttp.getObj();
                            HTSApp htsApp= (HTSApp) getApplication();
                            htsApp.setStudents(students);
                            Intent intent=new Intent(context, MainActivity.class);
                            startActivity(intent);
                        } else if (stringResultHttp.getCode() == 101) {
                            Toast.makeText(context,"登录失败",Toast.LENGTH_SHORT).show();
                        }

                    }
                })
        ;
    }
    void teacLogin(String username,String password) {
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
                .baseUrl(Config.teacLogin())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        NewsApi service = retrofit.create(NewsApi.class);
        service.teacLogin(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultHttp<Teachers>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(ResultHttp<Teachers> stringResultHttp) {
                        if (stringResultHttp.getCode() == 102) {
                            Teachers teachers=  stringResultHttp.getObj();
                            HTSApp htsApp= (HTSApp) getApplication();
                            htsApp.setTeachers(teachers);
                            Intent intent=new Intent(context, MainActivity.class);
                            startActivity(intent);
                        } else if (stringResultHttp.getCode() == 101) {
                            Toast.makeText(context,"登录失败",Toast.LENGTH_SHORT).show();
                        }

                    }
                })
        ;
    }
}
