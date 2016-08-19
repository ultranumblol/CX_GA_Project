package wgz.com.cx_ga_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
import wgz.com.cx_ga_project.util.SomeUtil;

/**
 * 开始新作战任务
 * Created by wgz on 2016/8/15.
 */
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.jqhistory, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.jq_history) {
            SomeUtil.showSnackBar(rootview,"开发中。。。");
            return true;
        }
        if (id == android.R.id.home) {
          onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.newfight)
    public void onClick() {
        startActivity(new Intent(StartNewFightActivity.this, FightActivity.class));
    }
}
