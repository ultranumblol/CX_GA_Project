package wgz.com.cx_ga_project.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by wgz on 2016/8/4.
 */

public class JiabanAdapter extends MyRecyclerArrayAdapter<List<Map<String,Object>>> {
    public JiabanAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new JiabanViewholder(parent);
    }

}
