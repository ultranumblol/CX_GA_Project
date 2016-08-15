package wgz.com.cx_ga_project.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.base.BaseActivity;

public class FightActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.id_fight_to1)
    CardView idFightTo1;
    @Bind(R.id.id_fight_to2)
    CardView idFightTo2;
    @Bind(R.id.content_fight)
    ConstraintLayout rootView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fight;
    }

    @Override
    public void initView() {
        toolbar.setTitle("接处警作战");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
