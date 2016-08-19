package wgz.com.cx_ga_project.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.util.SPUtils;
import wgz.com.cx_ga_project.util.SomeUtil;
import wgz.datatom.com.utillibrary.util.LogUtil;
import wgz.datatom.com.utillibrary.util.StatusBarUtil;

/**
 * 主页
 * Created by wgz on 2016/8/1.
 */
@SuppressWarnings("ResourceType")
public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar_home)
    Toolbar toolbarHome;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.id_colltoollayout)
    CollapsingToolbarLayout idColltoollayout;
    @Bind(R.id.home_rootView)
    CoordinatorLayout homeRootView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.to_workManage)
    CardView mToWorkManage;
    @Bind(R.id.to_jiechujing)
    CardView mToJiechujing;
    @Bind(R.id.rollPagerView)
    RollPagerView rollPagerView;
    @Bind(R.id.id_appbar_home)
    AppBarLayout idAppbarHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);


        rollPagerView.setHintView(new ColorPointHintView(this, Color.WHITE, Color.GRAY));
        rollPagerView.setAdapter(new BannerAdapter());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbarHome, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        drawer.setStatusBarBackground(Color.TRANSPARENT);
        toolbarHome.setTitle("智慧警务APP");
        setSupportActionBar(toolbarHome);
        toolbarHome.setTitleTextColor(Color.WHITE);
        idColltoollayout.setCollapsedTitleTextColor(Color.WHITE);
        idColltoollayout.setExpandedTitleColor(Color.WHITE);
        navView.setNavigationItemSelectedListener(this);
        Resources resource=(Resources)getBaseContext().getResources();
        ColorStateList csl=(ColorStateList)resource.getColorStateList(R.drawable.navigation_menu_item_color);
        navView.setItemTextColor(csl);
        navView.setItemIconTintList(csl);

    }

   /* private ArrayList<Integer> initData() {
        ArrayList<Integer> list = new ArrayList<>();

            list.add(R.drawable.ad1);
            list.add(R.drawable.ad2);
        list.add(R.drawable.welcome);
        return list;
    }*/

    private class BannerAdapter extends StaticPagerAdapter {
        // private List<Integer> list;
        int[] imageId = new int[]{R.drawable.ad1, R.drawable.ad2, R.drawable.ad3};

        public BannerAdapter() {
            int[] src = imageId;
        }

        @Override
        public View getView(ViewGroup container, final int position) {
            ImageView imageView = new ImageView(HomeActivity.this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setImageResource(R.drawable.calendar_bg);
            //加载图片
            Glide.with(HomeActivity.this)
                    .load(imageId[position])
                    .placeholder(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .thumbnail(0.4f)
                    .dontAnimate()
                    .into(imageView);
            //点击事件
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SomeUtil.showSnackBar(homeRootView,"维护中。。。");
                    //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).getUrl())));
                }
            });
            return imageView;
        }

        @Override
        public int getCount() {
            return imageId.length;
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // TODO: 2016/8/3 退出登录功能

            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            return true;
        }
        if (id == android.R.id.home) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.openDrawer(GravityCompat.START);

        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        // TODO: 2016/8/3 个人页面内容待定
        if (id == R.id.nav_mypic) {

            // Handle the camera action
        } else if (id == R.id.nav_changepass) {

        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_updateAPP) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_clearcache) {

        } else if (id == R.id.nav_logout) {
            SPUtils.clear(getApplicationContext());
            startActivity(new Intent(HomeActivity.this,WelcomeActivity.class));


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @OnClick({R.id.fab, R.id.to_workManage, R.id.to_jiechujing})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                // TODO: 2016/8/3 社会信息采集功能

                Snackbar.make(homeRootView, "社会信息采集开发中。。", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.to_workManage:
                startActivity(new Intent(HomeActivity.this, WorkMagActivity.class));

                break;
            case R.id.to_jiechujing:
                // TODO: 2016/8/5 接处警作战功能
                startActivity(new Intent(HomeActivity.this,StartNewFightActivity.class));
                //Snackbar.make(homeRootView, "开发中。。。", Snackbar.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }


}
