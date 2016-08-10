package wgz.com.cx_ga_project.adapter;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.util.Map;

import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.bean.JiaBan;

/**
 * Created by wgz on 2016/8/4.
 */
public class JiabanViewholder extends BaseViewHolder<Map<String,Object>> {
    private TextView jiabanID,jiabanState,jiabanDate;
    private ImageView userface;



    public JiabanViewholder(ViewGroup parent) {
        super(parent, R.layout.item_jiaban_apply);
        jiabanID = (TextView) $(R.id.jiaban_ID);
        jiabanDate = (TextView) $(R.id.jiaban_date);
        jiabanState = (TextView) $(R.id.jiaban_state);
        userface = (ImageView) $(R.id.user_face);

    }

    @Override
    public void setData(Map<String,Object> data) {


       jiabanID.setText(data.get("policeid").toString());
        jiabanDate.setText(data.get("applytime").toString());
        jiabanState.setText(data.get("status").toString());
        userface.setImageResource(R.drawable.ic_account_circle_gray_48dp);
    }
}
