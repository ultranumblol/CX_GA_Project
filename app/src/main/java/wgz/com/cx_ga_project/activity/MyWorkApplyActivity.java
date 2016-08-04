package wgz.com.cx_ga_project.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.base.BaseActivity;

/**
 * 我的申请
 * Created by wgz on 2016/8/4.
 */

public class MyWorkApplyActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tab_myapply)
    TabLayout tabMyapply;
    @Bind(R.id.myapple_vp)
    ViewPager myappleVp;
    @Bind(R.id.content_my_work_apply)
    ConstraintLayout contentMyWorkApply;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_work_apply;
    }

    @Override
    public void initView() {
        toolbar.setTitle("我的申请");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tabMyapply.addTab(tabMyapply.newTab().setText("加班申请"));
        tabMyapply.addTab(tabMyapply.newTab().setText("请假申请"));
        tabMyapply.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        myappleVp.setCurrentItem(0);
                        break;
                    case 1:
                        myappleVp.setCurrentItem(1);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}
