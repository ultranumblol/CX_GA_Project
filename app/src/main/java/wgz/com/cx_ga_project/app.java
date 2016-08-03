package wgz.com.cx_ga_project;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import wgz.com.cx_ga_project.base.APIservice;
import wgz.com.cx_ga_project.exception.LocalFileHandler;
import wgz.datatom.com.utillibrary.util.LogUtil;
import wgz.datatom.com.utillibrary.util.ToastUtil;

/**
 * Created by wgz on 2016/7/26.
 */

public class app extends Application {
    private static app mApp;
    public static final String BASE_URL = "http://192.168.1.88/demojob/";
    public static APIservice apiService;
    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        ToastUtil.isShow =true;

        LogUtil.isDebug=true;

        //配置程序异常退出处理
        //Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();


        apiService = retrofit.create(APIservice.class);

    }
    public static OkHttpClient defaultOkHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();
        return client;
    }
    public static app getApp(){
        return mApp;
    }
}