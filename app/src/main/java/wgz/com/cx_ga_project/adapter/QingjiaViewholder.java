package wgz.com.cx_ga_project.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.util.Map;

import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.bean.QingJia;

/**
 * Created by wgz on 2016/8/4.
 */
public class QingjiaViewholder extends BaseViewHolder<Map<String,Object>> {
    private TextView qingjiaID,qingjiaReason,qingjiaDate,qingjiaState;
    private ImageView userface;


    public QingjiaViewholder(ViewGroup parent) {
        super(parent, R.layout.item_qingjia_apply);
        qingjiaReason =  $(R.id.qingjia_reason);
        qingjiaDate =  $(R.id.qingjia_date);
        qingjiaState =  $(R.id.qingjia_state);
        userface = $(R.id.user_face);
        qingjiaID = $(R.id.qingjia_name);
    }

    @Override
    public void setData(Map<String,Object> data) {
        qingjiaID.setText(data.get("policeid").toString());
        qingjiaReason.setText(data.get("reasontype").toString());
        qingjiaDate.setText(data.get("applytime").toString());
        qingjiaState.setText(data.get("shstatus").toString());
        userface.setImageResource(R.drawable.ic_account_circle_gray_48dp);
    }
}
