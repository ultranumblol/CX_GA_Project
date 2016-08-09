package wgz.com.cx_ga_project.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.adapter.MyApprovalAdapter;
import wgz.com.cx_ga_project.adapter.MyRecyclerArrayAdapter;
import wgz.com.cx_ga_project.base.BaseFragment;

/**
 * Created by wgz on 2016/8/9.
 */

public class MyapprovalFragment extends BaseFragment {
    @Bind(R.id.id_myapproval_lv)
    EasyRecyclerView recyclerview;
    MyApprovalAdapter adapter;
    @Override
    public void initview(View view) {
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter(adapter = new MyApprovalAdapter(getActivity()));
        adapter.setOnItemClickListener(new MyRecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View itemView) {

            }
        });

        adapter.addAll(initData());
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

    @Override
    public int getLayoutitem() {
        return R.layout.fragment_my_approval;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
