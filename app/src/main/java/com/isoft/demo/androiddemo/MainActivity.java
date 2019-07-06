package com.isoft.demo.androiddemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    MainFragment1 mainFragment1;
    MainFragment2 mainFragment2;
    MainFragment3 mainFragment3;
    MainFragment4 mainFragment4;
    RadioGroup rg;

    public void init() {
        viewPager = (ViewPager) findViewById(R.id.mainViewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        if (mainFragment1 == null)
                            mainFragment1 = new MainFragment1();
                        return mainFragment1;
                    case 1:
                        if (mainFragment2 == null)
                            mainFragment2 = new MainFragment2();
                        return mainFragment2;
                    case 2:
                        if (mainFragment3 == null)
                            mainFragment3 = new MainFragment3();
                        return mainFragment3;
                    case 3:
                        if (mainFragment4 == null)
                            mainFragment4 = new MainFragment4();
                        return mainFragment4;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("航旅纵横");
        toolbar.setSubtitle("首页");
        toolbar.setLogo(R.mipmap.logo);
        toolbar.setBackground(getDrawable(R.mipmap.title_bg));
        setSupportActionBar(toolbar);
        rg = (RadioGroup) findViewById(R.id.bottomHav);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                viewPager.setCurrentItem(checkedId-1);
            }
        });
        init();


       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
