package wgz.com.cx_ga_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.base.BaseActivity;

/*
* 加班和请假明细
* */
public class JiabanLeaveDetilActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.detil_leave_committime)
    TextView detilLeaveCommittime;
    @Bind(R.id.detil_leave_starttime)
    TextView detilLeaveStarttime;
    @Bind(R.id.detil_leave_endtime)
    TextView detilLeaveEndtime;
    @Bind(R.id.detil_leave_dayscount)
    TextView detilLeaveDayscount;
    @Bind(R.id.detil_leave_type)
    TextView detilLeaveType;
    @Bind(R.id.detil_leave_reason)
    TextView detilLeaveReason;
    @Bind(R.id.detil_jiaban_committime)
    TextView detilJiabanCommittime;
    @Bind(R.id.detil_jiaban_starttime)
    TextView detilJiabanStarttime;
    @Bind(R.id.detil_jiaban_endtime)
    TextView detilJiabanEndtime;
    @Bind(R.id.detil_jiaban_reason)
    TextView detilJiabanReason;
    @Bind(R.id.detil_jiaban_shenherenname)
    TextView detilJiabanShenherenname;
    @Bind(R.id.content_jiaban_leave_detil)
    LinearLayout rootview;
    @Bind(R.id.jiabanLeave_detil_qingjia)
    LinearLayout jiabanLeaveDetilQingjia;
    @Bind(R.id.jiabanLeave_detil_jiaban)
    LinearLayout jiabanLeaveDetilJiaban;
    @Bind(R.id.detil_qingjia_shenherenname)
    TextView detilQingjiaShenherenname;

    @Override
    public int getLayoutId() {
        return R.layout.activity_jiaban_leave_detil;
    }

    @Override
    public void initView() {

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        switch (type){
            case "jiaban":
                toolbar.setTitle("加班明细");
                jiabanLeaveDetilJiaban.setVisibility(View.VISIBLE);
                jiabanLeaveDetilQingjia.setVisibility(View.GONE);
                break;
            case "qingjia":
                toolbar.setTitle("请假明细");
                jiabanLeaveDetilJiaban.setVisibility(View.GONE);
                jiabanLeaveDetilQingjia.setVisibility(View.VISIBLE);
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

}
