package wgz.com.cx_ga_project.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.security.InvalidParameterException;

import wgz.com.cx_ga_project.entity.Apply;

import static wgz.com.cx_ga_project.adapter.ApplyAdapter.TYPE_INVALID;
import static wgz.com.cx_ga_project.adapter.ApplyAdapter.TYPE_JIABAN;
import static wgz.com.cx_ga_project.adapter.ApplyAdapter.TYPE_LEAVE;

/**
 * Created by wgz on 2016/8/4.
 */

public class JiabanAdapter extends MyRecyclerArrayAdapter<Apply.Result> {
    public JiabanAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_LEAVE:
                break;
            case TYPE_JIABAN:
                return new JiabanViewholder(parent);
            default:throw new InvalidParameterException();
        }
        return null;
    }

    @Override
    public int getViewType(int position) {
        if (getItem(position).getType().equals("1"))
            return TYPE_LEAVE;
        if (getItem(position).getType().equals("0"))
            return TYPE_JIABAN;
        return TYPE_INVALID;
    }
}
