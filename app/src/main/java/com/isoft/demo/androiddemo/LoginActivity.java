package com.isoft.demo.androiddemo;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.net.Uri;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.reflect.Method;


public class LoginActivity extends AppCompatActivity {
    EditText uname;
    EditText upwd;
    CheckBox rememberpwd;
    CheckBox showpwd;
    Button login;
    Button cancel;

    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setActionBar();
        init();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void init() {
        uname = (EditText) findViewById(R.id.uname);
        upwd = (EditText) findViewById(R.id.upwd);
        rememberpwd = (CheckBox) findViewById(R.id.rememberpwd);
        showpwd = (CheckBox) findViewById(R.id.showpwd);
        login = (Button) findViewById(R.id.login);
        cancel = (Button) findViewById(R.id.cancel);
        SharedPreferences loginStaus = getSharedPreferences("loginStaus", MODE_PRIVATE);
        String temp_uname=loginStaus.getString("uname","");//若没有值，存为空，后面的为默认值
        String temp_upwd= loginStaus.getString("upwd","");
        int temp_status=loginStaus.getInt("status",0);
        uname.setText(temp_uname);
        upwd.setText(temp_upwd);
        if(temp_status==1)
            rememberpwd.setChecked(true);
        else
            rememberpwd.setChecked(false);
        showpwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    upwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    upwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_uname = uname.getText().toString();
                String str_upwd = upwd.getText().toString();
                if (str_uname.length() == 0) {
                    Toast.makeText(LoginActivity.this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (str_upwd.length() == 0) {
                    Toast.makeText(LoginActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (str_upwd.length() < 6) {
                    Toast.makeText(LoginActivity.this, "密码长度不能低于6位！", Toast.LENGTH_SHORT).show();
                    return;
                }
                //硬编码
                if (str_uname.equals("admin") && str_upwd.equals("123456")) {
                    //记住密码
                    SharedPreferences loginStaus = getSharedPreferences("loginStaus", MODE_PRIVATE);//共享引用，私有模式
                    SharedPreferences.Editor editor = loginStaus.edit();
                    if (rememberpwd.isChecked()) {
                        //本地存储
                        editor.putString("uname",str_uname);
                        editor.putString("upwd",str_upwd);
                        //editor.putString("status","1");
                        editor.putInt("status",1);
                    }else{
                        editor.clear();
                    }
                    editor.commit();//提交后才能存储上
                    Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(LoginActivity.this, "登录失败，请重试！", Toast.LENGTH_SHORT).show();
                //Toast.makeText(LoginActivity.this,"登录",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setActionBar() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("航班查询系统");
        actionBar.setSubtitle("登录");
        //actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setBackgroundDrawable(getDrawable(R.mipmap.title_bg));
        actionBar.setIcon(R.mipmap.logol);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.login_menu, menu);
        //使用反射机制，默认不显示图标
       if(menu!=null){
           if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
               try {
                   Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible",
                           Boolean.TYPE);
                   method.setAccessible(true);
                   method.invoke(menu, true);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }
        getMenuInflater().inflate(R.menu.login_menu, menu);
    return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //待完成
        //EditText t1=new EditText(this);
        if(item.getItemId()==R.id.menu_exit){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("是否要退出系统？");
            builder.setCancelable(true);
            //builder.setView(t1);
            builder.setIcon(R.mipmap.logo);
            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            });
            builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        }
        if(item.getItemId()==R.id.menu_register){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("是否要注册新用户？");
            builder.setCancelable(true);
            //builder.setView(t1);
            builder.setIcon(R.mipmap.logo);
            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            });
            builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        }
        if(item.getItemId()==android.R.id.home){//系统自有
            Toast.makeText(this,"我是返回按钮",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
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
                "Login Page", // TODO: Define a title for the content shown.
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

