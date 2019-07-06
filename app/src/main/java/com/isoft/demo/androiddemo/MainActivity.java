package com.isoft.demo.androiddemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    MainFragment1 mainFragment1;
    MainFragment2 mainFragment2;
    MainFragment3 mainFragment3;
    MainFragment4 mainFragment4;
    RadioGroup rg;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;

    private GoogleApiClient client;

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
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0)
                    rb1.setChecked(true);
                if (position == 1)
                    rb2.setChecked(true);
                if (position == 2)
                    rb3.setChecked(true);
                if (position == 3)
                    rb4.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

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
        toolbar.setLogo(R.mipmap.logol);
        toolbar.setBackground(getDrawable(R.mipmap.title_bg));
        setSupportActionBar(toolbar);
        rb1 = (RadioButton) findViewById(R.id.home);
        rb2 = (RadioButton) findViewById(R.id.flight);
        rb3 = (RadioButton) findViewById(R.id.city);
        rb4 = (RadioButton) findViewById(R.id.my);
        rg = (RadioGroup) findViewById(R.id.bottomHav);
        //rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        //  @Override
        //public void onCheckedChanged(RadioGroup group, int checkedId) {
        //  viewPager.setCurrentItem(checkedId - 1);
        //}
        //});
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.home)
                    viewPager.setCurrentItem(0);
                if (checkedId == R.id.flight)
                    viewPager.setCurrentItem(1);
                if (checkedId == R.id.city)
                    viewPager.setCurrentItem(2);
                if (checkedId == R.id.my)
                    viewPager.setCurrentItem(3);
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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.isoft.demo.androiddemo/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.isoft.demo.androiddemo/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
