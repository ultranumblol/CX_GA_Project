package wgz.com.cx_ga_project.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.app;
import wgz.com.cx_ga_project.base.BaseActivity;
import wgz.com.cx_ga_project.util.SomeUtil;
import wgz.datatom.com.utillibrary.util.LogUtil;


/**
 *
 * 添加工作日志
 * Created by wgz on 2016/8/3.
 */
public class AddWorkLogActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.worklog_detail_tv_title)
    EditText worklogText;
    @Bind(R.id.content_add_work_log)
    ConstraintLayout rootview;
    @Bind(R.id.fab_addworklog)
    FloatingActionButton fabAddworklog;
    private String time="";
    private String worklog = "";
    private String id = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_work_log;
    }

    @Override
    public void initView() {
        toolbar.setTitle("添加工作记录");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        time = intent.getStringExtra("time");
        worklog = intent.getStringExtra("worklog");
        id = intent.getStringExtra("id");
        if (!worklog.contains("没有工作记录")){
           worklogText.setText(worklog);
            RxView.clicks(fabAddworklog).throttleFirst(500, TimeUnit.MILLISECONDS)
                    .subscribe(new Action1<Void>() {
                        @Override
                        public void call(Void aVoid) {
                            ChangeWorkLog();
                        }
                    });


        }else {


            RxView.clicks(fabAddworklog).throttleFirst(500, TimeUnit.MILLISECONDS)
                    .subscribe(new Action1<Void>() {
                        @Override
                        public void call(Void aVoid) {
                            UpLoadWorkLog();
                        }
                    });

        }


    }

    private void ChangeWorkLog() {
        app.apiService.changeWorkLog("changeOnceSummary",id,worklogText.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        SomeUtil.showSnackBar(rootview,"提交修改成功！").setCallback(new Snackbar.Callback() {
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {

                                finish();
                            }
                        });

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        LogUtil.e("Xiugairesult:"+s);
                        if (s.contains("200")){
                            onCompleted();
                        }else onError(new Exception(s));
                    }
                });



    }

    private void UpLoadWorkLog() {
        app.apiService.upWorkLog("addSummary","10001",worklogText.getText().toString(),time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                    finish();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        LogUtil.e("result:"+s);
                        if (s.contains("200")){
                            onCompleted();
                        }else onError(new Exception(s));
                    }
                });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (worklogText.getText().toString().isEmpty()) {
                    finish();

                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(AddWorkLogActivity.this);
                    dialog.setTitle("请确认").setMessage("还没有提交记录，确认退出?")
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            }).setNegativeButton("取消", null).show();
                }


        }

        return true;

    }

}
