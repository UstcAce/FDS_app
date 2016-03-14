package com.example.ustc.fds_app.appsource.activity;

import android.app.Activity;
import android.content.Intent;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.ustc.fds_app.appsource.adapter.ViewPagerAdapter;

import com.example.ustc.fds_app.R;
import com.example.ustc.fds_app.appsource.functions.LanguageTag;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends Activity implements ViewPager.OnPageChangeListener{

    private ViewPager vp;

    private ViewPagerAdapter vpAdapter;

    private List<View> views;

    private Button start_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String able= getResources().getConfiguration().locale.getCountry();
        if (able.equals("CN"))
        {
            LanguageTag.setTag(1);
        }
        setContentView(R.layout.activity_splash);
        initViews();
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.one,null));
        views.add(inflater.inflate(R.layout.two, null));
        views.add(inflater.inflate(R.layout.three,null));

        vpAdapter = new ViewPagerAdapter(this, views);

        vp = (ViewPager) this.findViewById(R.id.viewPager);//改动
        vp.setAdapter(vpAdapter);

        start_btn = (Button) views.get(2).findViewById(R.id.login);

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            // TODO 联网用户登录还没实现
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.overridePendingTransition(R.anim.activity_move_in_start,R.anim.activity_move_out_start);
                finish();
            }
        });


        vp.setOnPageChangeListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
