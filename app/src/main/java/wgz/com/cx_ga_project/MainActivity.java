package wgz.com.cx_ga_project;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.OnClick;
import wgz.com.cx_ga_project.activity.SchedulingActivity;
import wgz.com.cx_ga_project.base.BaseActivity;

public class MainActivity extends BaseActivity {


    @Bind(R.id.app_bar_image)
    ImageView appBarImage;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.id_colltoollayout)
    CollapsingToolbarLayout idColltoollayout;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.id_myscheduling)
    CardView toMyscheduling;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        toolbar.setTitle("警务APP");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        idColltoollayout.setCollapsedTitleTextColor(Color.WHITE);
        idColltoollayout.setExpandedTitleColor(Color.WHITE);
        //StatusBarUtil.setColor(this,R.color.colorPrimaryDark);
        // StatusBarUtil.setTranslucent(this,100);
        afterCreat();

    }

    private void afterCreat() {


    }


    @OnClick(R.id.id_myscheduling)
    public void onClick() {
        startActivity(new Intent(MainActivity.this, SchedulingActivity.class));
    }
}
