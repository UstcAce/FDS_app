package com.example.ustc.fds_app.appsource.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.example.ustc.fds_app.R;
import com.example.ustc.fds_app.appsource.view.AddPopWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ustc on 2016/3/3.
 */
public class Rb1Fragment extends android.support.v4.app.Fragment implements RadioGroup.OnCheckedChangeListener {


    private View parentView;
    private RadioGroup rg;
    private RadioButton rb1, rb2;
    private ViewPager vp;

    List<android.support.v4.app.Fragment> list = null;
    private ImageView iv_add;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        parentView = inflater.inflate(R.layout.rb1fragment, container, false);
        rg = (RadioGroup) parentView.findViewById(R.id.radioGroup);
        rb1 = (RadioButton) parentView.findViewById(R.id.title_rb1);
        rb2 = (RadioButton) parentView.findViewById(R.id.title_rb2);
        vp = (ViewPager) parentView.findViewById(R.id.viewpager_rb1);
        iv_add = (ImageView) parentView.findViewById(R.id.iv_add);

        list = new ArrayList<android.support.v4.app.Fragment>();
        Rb1subFragment1 rbsub1 = new Rb1subFragment1();
        Rb1subFragment2 rbsub2 = new Rb1subFragment2();
        list.add(rbsub1);
        list.add(rbsub2);


        ZxzcAdapter zxzc = new ZxzcAdapter(getChildFragmentManager(), list);
        vp.setAdapter(zxzc);
        zxzc.notifyDataSetChanged();

        rg.setOnCheckedChangeListener(this);
        rb1.setChecked(true);

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int arg0) {
                switch (arg0) {
                    case 0:
                        rb1.setChecked(true);
                        break;
                    case 1:
                        rb2.setChecked(true);
                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPopWindow addPopWindow = new AddPopWindow(getActivity());
                addPopWindow.showPopupWindow(iv_add);
            }
        });

        return parentView;
    }



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == rb1.getId()) {
            vp.setCurrentItem(0);
        } else if (checkedId == rb2.getId()) {
            vp.setCurrentItem(1);
        }

    }


    class ZxzcAdapter extends FragmentStatePagerAdapter {


        List<android.support.v4.app.Fragment> list;
        public ZxzcAdapter(FragmentManager fm,List<android.support.v4.app.Fragment> list) {
            super(fm);
            this.list=list;
}
        @Override
        public android.support.v4.app.Fragment getItem(int arg0) {
            return list.get(arg0);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }





}
