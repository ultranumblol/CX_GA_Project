package wgz.com.cx_ga_project.adapter;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import wgz.com.cx_ga_project.R;

/**
 * Created by wgz on 2016/8/4.
 */
public class JiabanViewholder extends BaseViewHolder {
    private TextView jiabanID,jiabanState,jiabanDate;



    public JiabanViewholder(ViewGroup parent) {
        super(parent, R.layout.item_jiaban_apply);
        jiabanID = (TextView) $(R.id.jiaban_ID);
        jiabanDate = (TextView) $(R.id.jiaban_date);
        jiabanState = (TextView) $(R.id.jiaban_state);

    }

    @Override
    public void setData(Object data) {
       jiabanID.setText("警员007");
        jiabanDate.setText("2016/9/11");
        jiabanState.setText("未审核");
    }
}
