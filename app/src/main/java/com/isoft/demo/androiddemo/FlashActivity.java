package com.isoft.demo.androiddemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Timer;
import java.util.TimerTask;


public class FlashActivity extends AppCompatActivity {
    Handler handler = new Handler();//刷新UI用Handler,倒计时用Timer
    Handler skipHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            skip.setText("跳过" + MaxNum);
        }
    };
    Timer timer;//隔多久
    TimerTask task;//子线程
    int MaxNum = 5;
    TextView skip;
    Runnable runnable;
    private boolean isFirstuse;

    private GoogleApiClient client;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        skip = (TextView) findViewById(R.id.skip);
       // SharedPreferences preferences = getSharedPreferences("isFirstUse", MODE_PRIVATE)
        final SharedPreferences preferences = getSharedPreferences("fd", MODE_PRIVATE);
        isFirstuse = preferences.getBoolean("isFirstUse", true);
        //String fisrt=
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();//消除
                /*Intent intent = new Intent();
                intent.setClass(FlashActivity.this, GuideActivity.class);
                startActivity(intent);
                finish();*/
                SharedPreferences flags = getSharedPreferences("fd", MODE_PRIVATE);
                SharedPreferences.Editor editor = flags.edit();
                Intent intent = new Intent();
                //记录第一次功能自己实现
                if (isFirstuse) {
                    intent.setClass(FlashActivity.this, GuideActivity.class);//shared
                    editor.putBoolean("isFirstUse", false);
                } else {
                    intent.setClass(FlashActivity.this, LoginActivity.class);//shared
                }
                startActivity(intent);
                finish();
                editor.commit();
            }
        });

        if (runnable == null)
            runnable = new Runnable() {
                @Override
                public void run() {
                /*Intent intent = new Intent();
                intent.setClass(FlashActivity.this, GuideActivity.class);
                startActivity(intent);
                finish();*/
                    SharedPreferences flags = getSharedPreferences("fd", MODE_PRIVATE);
                    SharedPreferences.Editor editor = flags.edit();
                    Intent intent = new Intent();
                    //记录第一次功能自己实现
                    if (isFirstuse) {
                        intent.setClass(FlashActivity.this, GuideActivity.class);//shared
                        editor.putBoolean("isFirstUse", false);
                    } else {
                        intent.setClass(FlashActivity.this, LoginActivity.class);//shared
                    }
                    startActivity(intent);
                    finish();
                    editor.commit();
                }
            };

       /*handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences flags = getSharedPreferences("fa", MODE_PRIVATE);
                SharedPreferences.Editor editor = flags.edit();
                Intent intent = new Intent();
                //记录第一次功能自己实现
                if (isFirstuse) {
                    intent.setClass(FlashActivity.this, GuideActivity.class);//shared
                    editor.putBoolean("isFirstUse", false);
                } else {
                    intent.setClass(FlashActivity.this, LoginActivity.class);//shared
                }
                startActivity(intent);
                finish();
                editor.commit();
            }
        }, 5000);*/
        handler.postDelayed(runnable, 5000);
        if (timer == null)//单例模式
            timer = new Timer();
        if (task == null)
            task = new TimerTask() {
                @Override
                public void run() {
                    //System.out.println("Test"+MaxNum--);
                    //MaxNum--;
                    //skip.setText("跳过"+MaxNum);
                    skipHandler.sendEmptyMessage(0);
                    MaxNum--;
                    if (MaxNum == 0)
                        timer.cancel();
                }
            };
        timer.schedule(task, 1000, 1000);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStop() {
        super.onStop();//当前的Activity不再呈现给用户的时候，onStop()被调用，令其属于挂起状态
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Flash Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.isoft.demo.androiddemo/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        handler.removeCallbacks(runnable);//移除线程
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Flash Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.isoft.demo.androiddemo/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }
}
