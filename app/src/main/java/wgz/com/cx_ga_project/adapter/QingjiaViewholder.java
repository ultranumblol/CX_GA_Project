package wgz.com.cx_ga_project.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.bean.QingJia;

/**
 * Created by wgz on 2016/8/4.
 */
public class QingjiaViewholder extends BaseViewHolder<QingJia> {
    private TextView qingjiaID,qingjiaReason,qingjiaDate,qingjiaState;
    private ImageView userface;


    public QingjiaViewholder(ViewGroup parent) {
        super(parent, R.layout.item_qingjia_apply);
        qingjiaReason = (TextView) $(R.id.qingjia_reason);
        qingjiaDate = (TextView) $(R.id.qingjia_date);
        qingjiaState = (TextView) $(R.id.qingjia_state);
        userface = (ImageView) $(R.id.user_face);
    }

    @Override
    public void setData(QingJia data) {
        qingjiaReason.setText(data.getContent());
        qingjiaDate.setText(data.getApplytime());
        qingjiaState.setText(data.getStatus());
        userface.setImageResource(R.drawable.ic_account_circle_gray_48dp);
    }
}
