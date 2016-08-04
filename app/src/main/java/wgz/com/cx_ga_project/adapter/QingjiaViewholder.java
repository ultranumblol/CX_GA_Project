package wgz.com.cx_ga_project.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import wgz.com.cx_ga_project.R;

/**
 * Created by wgz on 2016/8/4.
 */
public class QingjiaViewholder extends BaseViewHolder {
    private TextView qingjiaID,qingjiaReason,qingjiaDate,qingjiaState;



    public QingjiaViewholder(ViewGroup parent) {
        super(parent, R.layout.item_jiaban_apply);
        qingjiaID = (TextView) $(R.id.qingjia_ID);
        qingjiaReason = (TextView) $(R.id.qingjia_reason);
        qingjiaDate = (TextView) $(R.id.qingjia_date);
        qingjiaState = (TextView) $(R.id.qingjia_state);
    }

    @Override
    public void setData(Object data) {
            qingjiaID.setText("警员007");
        qingjiaReason.setText("生病");
        qingjiaDate.setText("2016/9/12");
        qingjiaState.setText("已审核");
    }
}
