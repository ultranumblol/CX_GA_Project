package wgz.com.cx_ga_project.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by wgz on 2016/8/4.
 */

public class JiabanAdapter extends MyRecyclerArrayAdapter<String> {
    public JiabanAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new JiabanViewholder(parent);
    }

}
