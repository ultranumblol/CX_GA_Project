package wgz.com.cx_ga_project.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.activity.ApprovalDetilActivity;
import wgz.com.cx_ga_project.adapter.ApplyAdapter;
import wgz.com.cx_ga_project.adapter.MyApprovalAdapter;
import wgz.com.cx_ga_project.adapter.MyRecyclerArrayAdapter;
import wgz.com.cx_ga_project.app;
import wgz.com.cx_ga_project.base.BaseFragment;
import wgz.com.cx_ga_project.entity.Apply;
import wgz.com.cx_ga_project.util.FastJsonTools;
import wgz.datatom.com.utillibrary.util.LogUtil;

/**
 * Created by wgz on 2016/8/9.
 */

public class MyapprovalFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.id_myapproval_lv)
    EasyRecyclerView recyclerview;
    ApplyAdapter adapter;
    List<Apply.Result> list = new ArrayList<Apply.Result>();
    @Override
    public void initview(View view) {
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter(adapter = new ApplyAdapter(getActivity()));
        recyclerview.setRefreshListener(this);
        adapter.setOnItemClickListener(new MyRecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View itemView) {

                Intent intent = new Intent();
                intent.setClass(getActivity(), ApprovalDetilActivity.class);
                intent.putExtra("type",adapter.getItem(position).toString());
                startActivity(intent);
            }
        });

        //adapter.addAll(initData());
        initdata();
    }

    private ArrayList<String> initData() {
        ArrayList<String> list = new ArrayList<>();
        list.add("qingjia");
        for (int i = 0; i < 4; i++) {
            list.add("jiaban");
        }
        list.add("qingjia");
        return list;
    }


    private void initdata(){
        app.apiService.getBeanData("getDepLeaveOverApply")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Apply, List<Apply.Result>>() {
                    @Override
                    public List<Apply.Result> call(Apply apply) {
                        //LogUtil.e("map_result::"+apply.getResult().toString());
                        return apply.getResult();
                    }
                })
                .flatMap(new Func1<List<Apply.Result>, Observable<Apply.Result>>() {
                    @Override
                    public Observable<Apply.Result> call(List<Apply.Result> results) {
                        //LogUtil.e("flatMap_result::"+results.size());
                        return Observable.from(results);
                    }
                })
                .filter(new Func1<Apply.Result, Boolean>() {
                    @Override
                    public Boolean call(Apply.Result result) {
                        return result.getStatus().equals("0")?true:false;
                    }
                }).
                map(new Func1<Apply.Result, List<Apply.Result>>() {
                    @Override
                    public List<Apply.Result> call(Apply.Result result) {
                        list.add(result);
                        return list;
                    }
                }).subscribe(new Observer<List<Apply.Result>>() {
            @Override
            public void onCompleted() {
                adapter.addAll(list);
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e("error"+e.toString());
            }

            @Override
            public void onNext(List<Apply.Result> results) {
                LogUtil.e("resultCOUNT:"+results.size());
            }
        });



    }
    @Override
    public int getLayoutitem() {
        return R.layout.fragment_my_approval;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        list.clear();
        adapter.clear();
        initdata();
    }
}
