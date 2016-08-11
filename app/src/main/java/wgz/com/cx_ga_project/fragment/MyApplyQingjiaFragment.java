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
import wgz.com.cx_ga_project.adapter.ApplyAdapter;
import wgz.com.cx_ga_project.adapter.MyRecyclerArrayAdapter;
import wgz.com.cx_ga_project.adapter.QingjiaAdapter;
import wgz.com.cx_ga_project.app;
import wgz.com.cx_ga_project.base.BaseFragment;
import wgz.com.cx_ga_project.bean.JiaBan;
import wgz.com.cx_ga_project.bean.QingJia;
import wgz.com.cx_ga_project.entity.Apply;
import wgz.com.cx_ga_project.util.FastJsonTools;
import wgz.datatom.com.utillibrary.util.LogUtil;

/**
 * 查看请假申请
 *
 */
public class MyApplyQingjiaFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    private ApplyAdapter adapter;
    @Bind(R.id.id_myapply_qingjia_lv)
    EasyRecyclerView mMyapplyQingjiaLv;
    private Handler handler = new Handler();
    final List<Apply.Result> list = new ArrayList<Apply.Result>();
    @Override
    public void initview(View view) {
        mMyapplyQingjiaLv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMyapplyQingjiaLv.setAdapterWithProgress(adapter = new ApplyAdapter(getActivity()));
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

    /**
     * 初始化数据
     */
    private void initData() {

        app.apiService.getBeanData("getOverLeaveStatus")
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
                       return result.getType().equals("1")?true:false;
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
            list.clear();
            adapter.clear();
            initData();
        }
    }, 2000);
}
}
