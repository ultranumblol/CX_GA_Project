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
public class MyApplyJiabanFragment extends Fragment {


    public MyApplyJiabanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_apply_jiaban,null);
        initview(view);

        return view;
    }

    private void initview(View view) {

    }

}
