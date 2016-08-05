package wgz.com.cx_ga_project.activity;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.base.BaseActivity;
import wgz.com.cx_ga_project.bean.AskForLeaveBean;

public class AskForLeaveActivity extends BaseActivity {

    TimePickerView pvTime;
    private int flag=0;
    OptionsPickerView pvOptions;
    private ArrayList<AskForLeaveBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items= new ArrayList<>();



    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.id_leave_type)
    TextView mLeaveType;
    @Bind(R.id.id_leave_starttime)
    TextView mLeaveStarttime;
    @Bind(R.id.id_leave_endtime)
    TextView mLeaveEndtime;
    @Bind(R.id.id_leave_daycount)
    EditText mLeaveDaycount;
    @Bind(R.id.id_leave_reason)
    EditText mLeaveReason;
    @Bind(R.id.id_leave_commit)
    CardView mLeaveCommit;
    @Bind(R.id.content_ask_for_leave)
    LinearLayout rootview;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ask_for_leave;
    }

    @Override
    public void initView() {
        toolbar.setTitle("请销假");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //时间选择器
        pvTime = new TimePickerView(this, TimePickerView.Type.MONTH_DAY_HOUR_MIN);
        //控制时间范围
        Calendar calendar = Calendar.getInstance();
        pvTime.setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR));//要在setTime 之前才有效果
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        //时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                switch (flag){
                    case 1:
                        mLeaveStarttime.setText(getTime(date));
                        break;
                    case 2:
                        mLeaveEndtime.setText(getTime(date));
                }
            }
        });

        //选项选择器
        pvOptions = new OptionsPickerView(this);
        //选项1
        options1Items.add(new AskForLeaveBean(1,"请假类型"));
        //选项2
        ArrayList<String> options2Items_01=new ArrayList<>();
        options2Items_01.add("事假");
        options2Items_01.add("病假");
        options2Items_01.add("调休");
        options2Items_01.add("出差");
        options2Items_01.add("其他");
        options2Items.add(options2Items_01);
        pvOptions.setPicker(options1Items, options2Items, null, true);

        //pvOptions.setTitle("选择假别");
        pvOptions.setCyclic(false, false, false);
        //设置默认选中的项目
        //监听确定选择按钮
        pvOptions.setSelectOptions(0,1,0);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String result = options2Items.get(options1).get(option2);
                mLeaveType.setText(result);



            }
        });



    }
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    @OnClick({R.id.id_leave_type, R.id.id_leave_starttime, R.id.id_leave_endtime, R.id.id_leave_daycount, R.id.id_leave_reason, R.id.id_leave_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_leave_type:
                pvOptions.show();
                break;
            case R.id.id_leave_starttime:
                pvTime.show();
                flag=1;
                break;
            case R.id.id_leave_endtime:
                pvTime.show();
                flag=2;
                break;
            case R.id.id_leave_daycount:
                break;
            case R.id.id_leave_reason:
                break;
            case R.id.id_leave_commit:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(pvOptions.isShowing()||pvTime.isShowing()){
                pvOptions.dismiss();
                pvTime.dismiss();
                return true;
            }
            if(pvTime.isShowing()){
                pvTime.dismiss();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
