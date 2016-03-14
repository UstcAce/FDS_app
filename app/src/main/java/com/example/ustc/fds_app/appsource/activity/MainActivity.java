package com.example.ustc.fds_app.appsource.activity;


import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ustc.fds_app.R;
import com.example.ustc.fds_app.appsource.fragment.Rb1Fragment;
import com.example.ustc.fds_app.appsource.fragment.Rb2Fragment;
import com.example.ustc.fds_app.appsource.fragment.Rb3Fragment;
import com.example.ustc.fds_app.appsource.functions.LanguageTag;
import com.example.ustc.fds_app.special.ResideMenu.ResideMenu;
import com.example.ustc.fds_app.special.ResideMenu.ResideMenuInfo;
import com.example.ustc.fds_app.special.ResideMenu.ResideMenuItem;

import java.util.Locale;


public class MainActivity extends FragmentActivity implements
        View.OnClickListener,RadioGroup.OnCheckedChangeListener {

    public ResideMenu resideMenu;

    private ResideMenuItem item1;
    private ResideMenuItem item2;
    private ResideMenuItem item3;

    private ResideMenuInfo info;

    private boolean is_closed = false;
    private long mExitTime;

    private RadioGroup rg;
    private RadioButton rb1,rb2,rb3;

    private Configuration config;
    private DisplayMetrics dm;
    private Resources resources;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resources =getResources();//获得res资源对象
        config = resources.getConfiguration();//获得设置对象
        dm = resources.getDisplayMetrics();



        setUpMenu();

        setListener();
    }

    private void setUpMenu() {


        //语句顺序 做一定的改变
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        resideMenu.setScaleValue(0.6f);
        resideMenu.setDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        rg = (RadioGroup) findViewById(R.id.rg);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);

        rg.setOnCheckedChangeListener(this);
        rb1.setChecked(true);

        findViewById(R.id.btn_en).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                config.locale = Locale.US;
                resources.updateConfiguration(config, dm);
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                MainActivity.this.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
                startActivity(intent);
                LanguageTag.setTag(0);
                System.out.println("转为英文");
            }
        });

        findViewById(R.id.btn_cn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                config.locale = Locale.SIMPLIFIED_CHINESE;
                resources.updateConfiguration(config, dm);
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                MainActivity.this.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
                startActivity(intent);
                LanguageTag.setTag(1);
                System.out.println("转为中文");
            }
        });




        if(LanguageTag.getTag()==0)
        {
            item1 = new ResideMenuItem(this, "item1");
            item2 = new ResideMenuItem(this, "item2");
            item3 = new ResideMenuItem(this, "item3");
        }
        else if(LanguageTag.getTag()==1)
        {
            item1 = new ResideMenuItem(this, "子项1");
            item2 = new ResideMenuItem(this, "子项2");
            item3 = new ResideMenuItem(this, "子项3");
        }


        resideMenu.addMenuItem(item1,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(item2,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(item3,ResideMenu.DIRECTION_LEFT);


        info = new ResideMenuInfo(this, R.drawable.icon_profile, "info1", "info2");//见ResideMenuInfo
        resideMenu.addMenuInfo(info);

    }

    private void setListener() {
        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        item3.setOnClickListener(this);

        info.setOnClickListener(this);


    }


    public void onClickLiftMenu(View v)
    {
        resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (rb1.getId() == checkedId) {
            changeFragment(new Rb1Fragment());
        } else if (rb2.getId() == checkedId) {
            changeFragment(new Rb2Fragment());
        } else if (rb3.getId() == checkedId) {
            changeFragment(new Rb3Fragment());
        }

    }

    @Override
    public void onClick(View v) {
        if (v == item1) {
            Intent intent = new Intent();
            intent.putExtra("item1", "item1");
            intent.setClass(getApplicationContext(), Item1Activity.class);
            startActivity(intent);
        } else if (v == item2) {
            Intent intent = new Intent();
            intent.putExtra("item2", "item2");
            intent.setClass(getApplicationContext(), Item2Activity.class);
            startActivity(intent);
        } else if (v == item3) {
            Intent intent = new Intent();
            intent.putExtra("item3", "item3");
            intent.setClass(getApplicationContext(), Item3Activity.class);
            startActivity(intent);
        } else if (v == info) {
            Intent intent = new Intent();
            intent.putExtra("info", "info");
            intent.setClass(getApplicationContext(), InfoActivity.class);
            startActivity(intent);
        }
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            is_closed = false;
            //leftMenu.setVisibility(View.GONE);
        }

        @Override
        public void closeMenu() {
            is_closed = true;
            //leftMenu.setVisibility(View.VISIBLE);
        }

    };
//改动
    private void changeFragment(android.support.v4.app.Fragment targetFragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 判断菜单是否关闭
            if (is_closed) {
                // 判断两次点击的时间间隔（默认设置为2秒）
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();

                    mExitTime = System.currentTimeMillis();
                } else {
                    finish();
                    System.exit(0);
                    super.onBackPressed();
                }
            } else {
                resideMenu.closeMenu();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
