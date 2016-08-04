package wgz.com.cx_ga_project.adapter;

import android.support.v7.widget.CardView;

import com.camnter.easyrecyclerview.adapter.EasyRecyclerViewAdapter;
import com.camnter.easyrecyclerview.holder.EasyRecyclerViewHolder;

import wgz.com.cx_ga_project.R;

/**
 * Created by qwerr on 2016/8/4.
 */

public class MyApplyQingjiaRVAdapter extends EasyRecyclerViewAdapter {
    @Override
    public int[] getItemLayouts() {
        return new int[]{R.layout.item_qingjia_apply};
    }

    @Override
    public void onBindRecycleViewHolder(EasyRecyclerViewHolder viewHolder, int position) {
        CardView cardView= viewHolder.findViewById(R.id.id_qingjia_item_root);
    }

    @Override
    public int getRecycleViewItemType(int position) {
        return 0;
    }
}
