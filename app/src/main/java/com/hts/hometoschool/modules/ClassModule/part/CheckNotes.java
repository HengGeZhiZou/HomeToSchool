package com.hts.hometoschool.modules.ClassModule.part;

import android.content.Context;
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

import com.hts.hometoschool.R;
import com.hts.hometoschool.api.Config;
import com.hts.hometoschool.api.NewsApi;
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

public class CheckNotes extends AppCompatActivity {


    private Button postRea;
    private EditText inputRea;
    private Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_notes);

        context=this;
        setTitle();
        inputRea=findViewById(R.id.check_note_content);
        postRea=findViewById(R.id.introduce_button);

        final Project project= (Project) getIntent().getSerializableExtra("project");


        postRea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noPass(project.getProId(),inputRea.getText().toString());
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

    void noPass(String proId,String reason) {
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
                .baseUrl(Config.noProPass())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        NewsApi service = retrofit.create(NewsApi.class);
        service.NoPassPro(proId,reason)
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
                            Toast.makeText(context,"提交完成",Toast.LENGTH_SHORT).show();

                        } else if (stringResultHttp.getCode() == 101) {
                            Toast.makeText(context,"提交失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
        ;
    }
}
