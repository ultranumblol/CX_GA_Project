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

import butterknife.Bind;
import butterknife.ButterKnife;
import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.activity.JiabanLeaveDetilActivity;
import wgz.com.cx_ga_project.adapter.MyRecyclerArrayAdapter;
import wgz.com.cx_ga_project.adapter.QingjiaAdapter;
import wgz.com.cx_ga_project.base.BaseFragment;

/**
 * 查看请假申请
 *
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
        adapter.setMore(R.layout.view_more, new MyRecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addAll(initData2());

                    }
                }, 2000);
            }
        });
        adapter.setNoMore(R.layout.view_nomore);
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


                /*Intent intent = new Intent();
                intent.putExtra("type","qingjia");
                intent.setClass(getActivity(), JiabanLeaveDetilActivity.class);
                startActivity(intent);*/
            }
        });
        mMyapplyQingjiaLv.setRefreshListener(this);
        onRefresh();
    }

    private ArrayList<String> initData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("i");
        }
        return list;
    }
    private ArrayList<String> initData2() {
        ArrayList<String> list = new ArrayList<>();

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
