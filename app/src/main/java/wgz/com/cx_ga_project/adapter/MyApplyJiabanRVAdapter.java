package wgz.com.cx_ga_project.adapter;

import com.camnter.easyrecyclerview.adapter.EasyRecyclerViewAdapter;
import com.camnter.easyrecyclerview.holder.EasyRecyclerViewHolder;

import wgz.com.cx_ga_project.R;

/**
 * Created by qwerr on 2016/8/4.
 */

public class MyApplyJiabanRVAdapter extends EasyRecyclerViewAdapter {
    @Override
    public int[] getItemLayouts() {
        return new int[]{R.layout.item_jiaban_apply};
    }

    @Override
    public void onBindRecycleViewHolder(EasyRecyclerViewHolder viewHolder, int position) {

    }

    @Override
    public int getRecycleViewItemType(int position) {
        return 0;
    }
}
