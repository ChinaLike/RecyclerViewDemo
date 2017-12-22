package com.like.recyclerviewdemo;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.felipecsl.asymmetricgridview.AsymmetricRecyclerView;
import com.felipecsl.asymmetricgridview.AsymmetricRecyclerViewAdapter;
import com.felipecsl.asymmetricgridview.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private List<ConfigBean> configBeanList = new ArrayList<>();

    private List<UserBean> userBeanList = new ArrayList<>();

    private Gson gson = new Gson();

    private AsymmetricRecyclerView mAsymmetricRecyclerView;

    private final DemoUtils demoUtils = new DemoUtils();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//强制为横屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        mAsymmetricRecyclerView = findViewById(R.id.grid_view);
        initData();

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(demoUtils.moarItems(6),this,configBeanList);
        mAsymmetricRecyclerView.setRequestedColumnCount(3);
//        mAsymmetricRecyclerView.setDebugging(true);
        int width = ScreenUtil.getScreenWidth(this)*1/100;
        int height = ScreenUtil.getScreenHeight(this)*1/100;
        mAsymmetricRecyclerView.setRequestedHorizontalSpacing(width);

        mAsymmetricRecyclerView.addItemDecoration(
                new SpacesItemDecoration(height));
        AsymmetricRecyclerViewAdapter adapter1 = new AsymmetricRecyclerViewAdapter<>(this, mAsymmetricRecyclerView, adapter);
        mAsymmetricRecyclerView.setAdapter(adapter1);
    }


    public String getFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initData() {
        userBeanList = gson.fromJson(getFromAssets("user.json"), new TypeToken<List<UserBean>>() {
        }.getType());
        configBeanList = gson.fromJson(getFromAssets("config.json"), new TypeToken<List<ConfigBean>>() {
        }.getType());
    }

}
