package com.example.dailystudy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.MyApplication;
import com.example.bean.UserMessage;
import com.example.manager.HttpManger;
import com.example.util.TextViewUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView titlename;
    private Button mf_land;
    private Button mf_register;
    private EditText username;
    private EditText password;
    private SharedPreferences.Editor edit;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land);
        sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        //修改标题名
        titlename = (TextView)findViewById(R.id.titlename);
        titlename.setText("登录");

        mf_land = (Button) findViewById(R.id.mf_land);
        mf_register = (Button) findViewById(R.id.mf_register);
        mf_land.setOnClickListener(this);
        mf_register.setOnClickListener(this);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mf_land:
                Map<String, String> map = new HashMap<>();
                map.put("userName", TextViewUtils.getText(username));
                map.put("password",TextViewUtils.getText(password));
                map.put("dosubmit","1");
                HttpManger.postMethod(false, true, "http://www.meirixue.com/", "api.php?c=login&a=index", map, new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String s = response.body();
                        Gson gson=new Gson();
                        UserMessage userMessage = gson.fromJson(s, UserMessage.class);
                        String user_name = userMessage.getData().getUser_name();
                        String user_sex = userMessage.getData().getUser_sex();
                        String user_middle_log = userMessage.getData().getUser_middle_log();
                        edit = sharedPreferences.edit();
                        edit.putString("name",user_name);
                        edit.putString("sex",user_sex);
                        edit.putString("image",user_middle_log);
                        edit.commit();

                        finish();
                        Toast.makeText(LandActivity.this,"登陆成功" ,Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

                break;
            case R.id.mf_register:
                Intent intent=new Intent(LandActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
