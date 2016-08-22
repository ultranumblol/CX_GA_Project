package wgz.com.cx_ga_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jude.easyrecyclerview.EasyRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.adapter.TimelineAdapter;
import wgz.com.cx_ga_project.base.BaseActivity;


public class NewFightActivity extends BaseActivity {
    @Bind(R.id.timeline_rv)
    EasyRecyclerView timelineRv;
    @Bind(R.id.id_fight_upload)
    CardView idFightUpload;
    @Bind(R.id.id_fight_bulu)
    CardView idFightBulu;
    @Bind(R.id.id_fight_talk)
    CardView idFightTalk;
    private TimelineAdapter adapter;
    private ArrayList<String> list = new ArrayList<>();
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.app_bar)
    AppBarLayout appBar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_fight;
    }

    @Override
    public void initView() {
        toolbar.setTitle("接处警作战");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        timelineRv.setLayoutManager(new LinearLayoutManager(this));
        timelineRv.setAdapter(adapter = new TimelineAdapter(this));
        initData();
        adapter.addAll(list);

    }

    private void initData() {
        list.add("核名\n核定你注册公司名称");
        list.add("申请登记\n申请登记,取得营业执照");
        list.add("刻章\n制作并备案公章,财务专用章等");
        list.add("税务报到\n到税务部门填报信息,取得纳税授权一证通");
        list.add("银行开设基本户\n到银行开设公司基本户,取得开户许可证");
        list.add("社保开户\n到社保主管部门确定社保登记证");


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.id_fight_upload, R.id.id_fight_bulu, R.id.id_fight_talk})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_fight_upload:
                startActivity(new Intent(NewFightActivity.this,AddJQActivity.class));
                break;
            case R.id.id_fight_bulu:
                startActivity(new Intent(NewFightActivity.this,AddJQActivity.class));
                break;
            case R.id.id_fight_talk:
                break;
        }
    }
}
