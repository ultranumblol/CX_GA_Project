package wgz.com.cx_ga_project.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.adapter.QingjiaAdapter;
import wgz.com.cx_ga_project.base.BaseFragment;

/**
 * 查看请假申请
 * A simple {@link Fragment} subclass.
 */
public class MyApplyQingjiaFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    private QingjiaAdapter adapter;
    @Bind(R.id.id_myapply_qingjia_lv)
    EasyRecyclerView mMyapplyQingjiaLv;
    private Handler handler = new Handler();
    @Override
    public void initview(View view) {
        mMyapplyQingjiaLv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMyapplyQingjiaLv.setAdapterWithProgress(adapter = new QingjiaAdapter(getActivity()));
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addAll(initData());

                    }
                }, 2000);
            }
        });
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //ToastUtil.showShort(getActivity(),"cilck:"+position);

            }
        });
        mMyapplyQingjiaLv.setRefreshListener(this);
        onRefresh();
    }

    private ArrayList<String> initData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("i");
        }
        return list;
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
                adapter.addAll(initData());
            }
        }, 2000);
    }
}