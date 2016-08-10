package wgz.com.cx_ga_project.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

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
import wgz.com.cx_ga_project.activity.JiabanLeaveDetilActivity;
import wgz.com.cx_ga_project.adapter.MyRecyclerArrayAdapter;
import wgz.com.cx_ga_project.adapter.QingjiaAdapter;
import wgz.com.cx_ga_project.app;
import wgz.com.cx_ga_project.base.BaseFragment;
import wgz.com.cx_ga_project.bean.JiaBan;
import wgz.com.cx_ga_project.bean.QingJia;
import wgz.com.cx_ga_project.util.FastJsonTools;

/**
 * 查看请假申请
 *
 */
public class MyApplyQingjiaFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    private QingjiaAdapter adapter;
    @Bind(R.id.id_myapply_qingjia_lv)
    EasyRecyclerView mMyapplyQingjiaLv;
    private Handler handler = new Handler();
    List<QingJia> datalist = new ArrayList<>();
    @Override
    public void initview(View view) {
        mMyapplyQingjiaLv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMyapplyQingjiaLv.setAdapterWithProgress(adapter = new QingjiaAdapter(getActivity()));
       /* adapter.setMore(R.layout.view_more, new MyRecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       // adapter.addAll(initData2());

                    }
                }, 2000);
            }
        });*/
        //adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(new MyRecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View itemView) {

                ImageView im_face = (ImageView) itemView.findViewById(R.id.user_face);
                TextView state = (TextView) itemView.findViewById(R.id.jiaban_state);

                ActivityCompat.startActivity(getActivity(),
                        new Intent(getActivity(), JiabanLeaveDetilActivity.class).putExtra("type","qingjia")
                        , ActivityOptionsCompat
                                .makeSceneTransitionAnimation(getActivity(),
                                        im_face, "share_img").toBundle());
            }
        });
        mMyapplyQingjiaLv.setRefreshListener(this);
       initData();
    }



    private void initData() {
        Observable<String> observable = app.apiService.getData("getLeaveTimeStatus");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, List<Map<String, Object>>>() {
                    @Override
                    public List<Map<String, Object>> call(String s) {

                        return FastJsonTools.getlistmap(s);
                    }
                })
                .map(new Func1<List<Map<String,Object>>, List<QingJia>>() {
                    @Override
                    public List<QingJia> call(List<Map<String, Object>> maps) {
                        List<QingJia> list = new ArrayList<QingJia>();
                        for (int i = 0;i<maps.size();i++){
                            QingJia qingJia = new QingJia();
                            qingJia.setPoliceid(maps.get(i).get("policeid").toString());
                            qingJia.setStatus(maps.get(i).get("status").toString());
                            qingJia.setUpper(maps.get(i).get("upper").toString());
                            qingJia.setStart(maps.get(i).get("upper").toString());
                            qingJia.setEnd(maps.get(i).get("upper").toString());
                            qingJia.setApplytime(maps.get(i).get("upper").toString());
                            qingJia.setContent(maps.get(i).get("upper").toString());
                            qingJia.setReasontype(maps.get(i).get("upper").toString());
                            qingJia.setDays(maps.get(i).get("upper").toString());
                            list.add(qingJia);
                        }
                        return list;
                    }
                }).subscribe(new Observer<List<QingJia>>() {
            @Override
            public void onCompleted() {
                adapter.addAll(datalist);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<QingJia> qingJias) {
                datalist = qingJias;
            }
        });



    }
    @Override
    public int getLayoutitem() {
        return R.layout.fragment_my_apply_qingjia;
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
}
