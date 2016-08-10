package wgz.com.cx_ga_project.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.activity.ApprovalDetilActivity;
import wgz.com.cx_ga_project.adapter.MyApprovalAdapter;
import wgz.com.cx_ga_project.adapter.MyRecyclerArrayAdapter;
import wgz.com.cx_ga_project.app;
import wgz.com.cx_ga_project.base.BaseFragment;
import wgz.com.cx_ga_project.util.FastJsonTools;
import wgz.datatom.com.utillibrary.util.LogUtil;

/**
 * Created by wgz on 2016/8/9.
 */

public class MyapprovalHistoryFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.id_myapproval_Lv_his)
    EasyRecyclerView recyclerview;
    private MyApprovalAdapter adapter;
    private Handler handler = new Handler();
    @Override
    public void initview(View view) {
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter( adapter = new MyApprovalAdapter(getActivity()));
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setMore(R.layout.view_more, new MyRecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //adapter.addAll(initData2());
                    }
                }, 2000);
            }
        });
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
        initdata();
       // adapter.addAll(initData());
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
       /* app.apiService.getData("getOverTimeStatus")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, List<Map<String,Object>>>() {
                    @Override
                    public List<Map<String, Object>> call(String s) {
                        return FastJsonTools.getlistmap(s);
                    }
                })
                .subscribe(new Observer<List<Map<String, Object>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Map<String, Object>> maps) {

                    }
                });*/

        Observable<String> jiabanData = app.apiService.getData("getDepOverTimeApply");
        jiabanData.subscribeOn(Schedulers.newThread());
        Observable<String> qingjiaData = app.apiService.getData("getDepLeaveTimeApply");
        qingjiaData.subscribeOn(Schedulers.newThread());
        Observable.merge(jiabanData,qingjiaData)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, List<Map<String,Object>>>() {
                    @Override
                    public List<Map<String, Object>> call(String s) {
                        return FastJsonTools.getlistmap(s);
                    }
                })
                .subscribe(new Observer<List<Map<String, Object>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Map<String, Object>> maps) {
                        for (int i = 0 ; i<maps.size() ; i++){
                            LogUtil.e("mapsHistory"+maps.get(i).toString());




                        }
                    }
                });




    }
    private ArrayList<String> initData2() {
        ArrayList<String> list = new ArrayList<>();

        return list;
    }
    @Override
    public int getLayoutitem() {
        return R.layout.fragment_my_approval_history;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
       /* handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.addAll(initData());
            }
        }, 2000);*/
    }
}
