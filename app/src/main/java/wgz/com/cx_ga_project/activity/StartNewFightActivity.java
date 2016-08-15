package wgz.com.cx_ga_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;
import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.base.BaseActivity;
import wgz.com.cx_ga_project.service.GetGPSService;
import wgz.com.cx_ga_project.util.SomeUtil;

public class StartNewFightActivity extends BaseActivity {


    @Bind(R.id.app_bar_image)
    ImageView appBarImage;
    @Bind(R.id.toolbar_fight)
    Toolbar toolbarFight;
    @Bind(R.id.id_fight_colltoollayout)
    CollapsingToolbarLayout idFightColltoollayout;
    @Bind(R.id.id_appbar_fight)
    AppBarLayout idAppbarFight;
    @Bind(R.id.newfight)
    CardView newfight;
    @Bind(R.id.content_start_new_fight)
    ConstraintLayout rootview;
    @Bind(R.id.startFight)
    FloatingActionButton startFight;

    @Override
    public int getLayoutId() {
        return R.layout.activity_start_new_fight;
    }

    @Override
    public void initView() {
        toolbarFight.setTitle("新警情");
        setSupportActionBar(toolbarFight);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RxView.clicks(startFight).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        gofight();
                    }


                });

    }

    private void gofight() {
        SomeUtil.showSnackBar(rootview, "是否开始作战？").setAction("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/8/15 开始作战
                startActivity(new Intent(StartNewFightActivity.this, FightActivity.class));
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /*@OnClick(R.id.startFight)
    public void onClick() {
    }*/
}
