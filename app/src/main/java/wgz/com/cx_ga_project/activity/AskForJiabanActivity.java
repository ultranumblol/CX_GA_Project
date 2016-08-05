package wgz.com.cx_ga_project.activity;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.base.BaseActivity;

public class AskForJiabanActivity extends BaseActivity {

    TimePickerView pvTime;
    private int flag=0;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.id_jiaban_starttime)
    TextView mJiabanStarttime;
    @Bind(R.id.id_jiaban_endtime)
    TextView mJiabanEndtime;
    @Bind(R.id.id_jiaban_reason)
    EditText mJiabanReason;
    @Bind(R.id.id_jiaban_commit)
    CardView mJiabanCommit;
    @Bind(R.id.content_ask_for_jiaban)
    LinearLayout rootview;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ask_for_jiaban;
    }

    @Override
    public void initView() {
        toolbar.setTitle("加班申请");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

}
