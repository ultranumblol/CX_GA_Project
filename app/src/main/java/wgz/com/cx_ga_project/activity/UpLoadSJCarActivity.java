package wgz.com.cx_ga_project.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.base.BaseActivity;

public class UpLoadSJCarActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.content_up_load_sjcar)
    LinearLayout rootview;
    @Bind(R.id.fab_addSJCar)
    FloatingActionButton fab;

    @Override
    public int getLayoutId() {
        return R.layout.activity_up_load_sjcar;
    }

    @Override
    public void initView() {
        toolbar.setTitle("涉警车辆信息采集");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
