package wgz.com.cx_ga_project.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.activity.JiabanLeaveDetilActivity;

import wgz.com.cx_ga_project.adapter.JiabanAdapter;
import wgz.com.cx_ga_project.adapter.MyRecyclerArrayAdapter;
import wgz.com.cx_ga_project.app;
import wgz.com.cx_ga_project.base.BaseFragment;
import wgz.com.cx_ga_project.bean.JiaBan;
import wgz.com.cx_ga_project.util.FastJsonTools;
import wgz.datatom.com.utillibrary.util.LogUtil;


/**
 * 查看加班申请
 * A simple {@link Fragment} subclass.
 */
public class MyApplyJiabanFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.id_myapply_jiaban_lv2)
    EasyRecyclerView recyclerView;
    JiabanAdapter adapter;
    List<JiaBan> datalist = new ArrayList<>();
    List<Map<String ,Object>> mapdatalist = new ArrayList<>();
    private Handler handler = new Handler();

    @Override
    public void initview(View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapterWithProgress(adapter = new JiabanAdapter(getActivity()));
       /* adapter.setMore(R.layout.view_more, new MyRecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 2000);
            }
        });*/
        //adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(new MyRecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View itemView) {
                //ToastUtil.showShort(getActivity(),"cilck:"+position);
                ImageView im_face = (ImageView) itemView.findViewById(R.id.user_face);
                ActivityCompat.startActivity(getActivity(), new Intent(getActivity(), JiabanLeaveDetilActivity.class).putExtra("type", "jiaban")
                        , ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), im_face, "share_img").toBundle());

            }
        });

        recyclerView.setRefreshListener(this);
        initData();

    }
    @Override
    public int getLayoutitem() {
        return R.layout.fragment_my_apply_jiaban;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                datalist.clear();
                adapter.clear();
               initData();
            }
        }, 2000);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        app.apiService.getData("getOverTimeStatus")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, List<Map<String,Object>>>() {
                    @Override
                    public List<Map<String, Object>> call(String s) {
                        return FastJsonTools.getlistmap(s);
                    }
                }).subscribe(new Observer<List<Map<String, Object>>>() {
            @Override
            public void onCompleted() {
                adapter.addAll(mapdatalist);
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e("error:"+e.toString());
            }

            @Override
            public void onNext(List<Map<String, Object>> maps) {
                LogUtil.e("加班申请列表："+maps.toString());
                    mapdatalist = maps;
            }
        });

    }
}
