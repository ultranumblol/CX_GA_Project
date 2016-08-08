package wgz.com.cx_ga_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
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
    @Bind(R.id.userPic)
    ImageView userPic;
    @Bind(R.id.userPic_jiaban)
    ImageView userPicJiaban;
    @Bind(R.id.detil_qingjia_state)
    TextView detilQingjiaState;
    @Bind(R.id.detil_jiaban_state)
    TextView detilJiabanState;

    @Override
    public int getLayoutId() {
        return R.layout.activity_jiaban_leave_detil;
    }

    @Override
    public void initView() {

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        switch (type) {
            case "jiaban":
                toolbar.setTitle("加班明细");
                jiabanLeaveDetilJiaban.setVisibility(View.VISIBLE);
                ViewCompat.setTransitionName(userPicJiaban, "share_img");
                ViewCompat.setTransitionName(detilJiabanState, "share_jbtext");
                jiabanLeaveDetilQingjia.setVisibility(View.GONE);
                break;
            case "qingjia":
                toolbar.setTitle("请假明细");
                jiabanLeaveDetilJiaban.setVisibility(View.GONE);
                ViewCompat.setTransitionName(userPic, "share_img");
                ViewCompat.setTransitionName(detilQingjiaState, "share_qjtext");
                jiabanLeaveDetilQingjia.setVisibility(View.VISIBLE);
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
