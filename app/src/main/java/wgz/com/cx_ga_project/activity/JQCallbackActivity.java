package wgz.com.cx_ga_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.Toolbar;

import com.lzp.floatingactionbuttonplus.FabTagLayout;
import com.lzp.floatingactionbuttonplus.FloatingActionButtonPlus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.base.BaseActivity;

public class JQCallbackActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.content_jqcallback)
    ConstraintLayout rootview;
    @Bind(R.id.FabPlus)
    FloatingActionButtonPlus FabPlus;

    @Override
    public int getLayoutId() {
        return R.layout.activity_jqcallback;
    }

    @Override
    public void initView() {
        toolbar.setTitle("警情回告");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FabPlus.setOnItemClickListener(new FloatingActionButtonPlus.OnItemClickListener() {
            @Override
            public void onItemClick(FabTagLayout tagView, int position) {
                int id = tagView.getId();
                switch (id){
                    case R.id.fabtag_addsjcar:
                        startActivity(new Intent(JQCallbackActivity.this,UpLoadSJCarActivity.class));

                        break;
                    case R.id.fabtag_addsjPeople:
                        startActivity(new Intent(JQCallbackActivity.this,SJPeopleActivity.class));

                        break;
                    case R.id.fabtag_addsjPhone:
                        startActivity(new Intent(JQCallbackActivity.this,SJPeopleActivity.class));
                        break;
                    case R.id.fabtag_addjqMsg:
                        startActivity(new Intent(JQCallbackActivity.this,AddJQActivity.class));
                        break;
                }
            }
        });

    }



}
