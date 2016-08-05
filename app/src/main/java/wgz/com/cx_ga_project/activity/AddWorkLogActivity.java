package wgz.com.cx_ga_project.activity;

import android.content.DialogInterface;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.OnClick;
import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.base.BaseActivity;


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
    ConstraintLayout contentAddWorkLog;
    @Bind(R.id.fab_addworklog)
    FloatingActionButton fabAddworklog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_work_log;
    }

    @Override
    public void initView() {
        toolbar.setTitle("添加工作记录");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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


    @OnClick(R.id.fab_addworklog)
    public void onClick() {
        // TODO: 2016/8/3 上传工作日志图片文字等
        Snackbar.make(contentAddWorkLog,"正在提交！",Snackbar.LENGTH_SHORT).show();

    }
}
