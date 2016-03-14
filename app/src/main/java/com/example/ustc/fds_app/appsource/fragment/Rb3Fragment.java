package com.example.ustc.fds_app.appsource.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ustc.fds_app.R;
import com.example.ustc.fds_app.appsource.view.AddPopWindow;

/**
 * Created by ustc on 2016/3/7.
 */
public class Rb3Fragment extends android.support.v4.app.Fragment{

    private View parentView;
    private ImageView iv_add;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        parentView = inflater.inflate(R.layout.rb3fragment,container,false);
        iv_add = (ImageView) parentView.findViewById(R.id.iv_add);

        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPopWindow addPopWindow = new AddPopWindow(getActivity());
                addPopWindow.showPopupWindow(iv_add);
            }
        });

        return parentView;
    }
}
