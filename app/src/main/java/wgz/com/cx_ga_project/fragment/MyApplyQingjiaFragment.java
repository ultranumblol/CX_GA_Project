package wgz.com.cx_ga_project.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wgz.com.cx_ga_project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyApplyQingjiaFragment extends Fragment {


    public MyApplyQingjiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_apply_qingjia,null);
        initview(view);

        return view;
    }

    private void initview(View view) {

    }

}
