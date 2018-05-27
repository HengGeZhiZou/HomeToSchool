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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.hts.hometoschool.R;
import com.hts.hometoschool.api.Config;
import com.hts.hometoschool.api.NewsApi;
import com.hts.hometoschool.modules.ClassModule.ProjectIntroduceActivity;
import com.hts.hometoschool.pojo.HTSApp;
import com.hts.hometoschool.pojo.News;
import com.hts.hometoschool.pojo.Project;
import com.hts.hometoschool.pojo.ResultHttp;
import com.hts.hometoschool.pojo.Students;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;


public class ProjectApplyFragment extends Fragment {
    private NiceSpinner niceSpinner;
    private RelativeLayout relativeLayout;
    private RelativeLayout relativeImgLayout;
    private Button button;
    private List<Uri> mSelected;
    private Context context;
    private EditText proName;
    private EditText proTeac;
    private EditText proTeamer;
    private String introduce;
    private String proType;

    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.class_project_apply_fragment, container, false);
        context = view.getContext();
        niceSpinner = view.findViewById(R.id.project_type_choose);
        final List<String> dataset = new LinkedList<>(Arrays.asList("互联网+", "创新创业", "学科竞赛", "其他项目"));

        niceSpinner.attachDataSource(dataset);

        niceSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                proType=String.valueOf(dataset.get(i));
            }
        });
        relativeLayout = view.findViewById(R.id.project_introduce_tab);
        relativeImgLayout = view.findViewById(R.id.project_img_tab);
        proName=view.findViewById(R.id.proName);
        proTeac=view.findViewById(R.id.proTeac);
        proTeamer=view.findViewById(R.id.proTeamer);

        button = view.findViewById(R.id.projectApplyButton);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), ProjectIntroduceActivity.class), 1);
            }
        });
        relativeImgLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImgChoose();
            }
        });
        HTSApp htsApp= (HTSApp)getActivity().getApplication();
        Students s=htsApp.getStudents();
        final Students students=s;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Project project = new Project();
//                project.setProId("1500050710040003");
                project.setStuId(students.getStuNum());
                project.setProName(proName.getText().toString());
                project.setProIntroduce(introduce);
                project.setProPic("http://pic8.nipic.com/20100801/387600_002750589396_2.jpg,http://pic2.ooopic.com/13/45/00/33b1OOOPIC3a.jpg");
                project.setProTeac(proTeac.getText().toString());
                project.setProTeamer(proTeamer.getText().toString());
                project.setProType(proType);
                project.setProGood(0);
                getNews(project);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 || resultCode == 2) {

            if (data != null) {
                String text = data.getStringExtra("value");
                this.introduce=text;
                Toast.makeText(context, "添加简介成功", Toast.LENGTH_LONG).show();
                System.out.println("------------------" + text);
            }
        } else if (requestCode == 23 && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            Log.d("Matisse", "mSelected: " + mSelected);
        }


    }

    void ImgChoose() {
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

    void getNews(Project project) {
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
                .baseUrl(Config.sendProject())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        NewsApi service = retrofit.create(NewsApi.class);
        service.getProjectList(project)
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
                            Toast.makeText(context,"上传成功",Toast.LENGTH_SHORT).show();
                        } else if (stringResultHttp.getCode() == 101) {
                            Toast.makeText(context,"上传失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
        ;
    }



}

