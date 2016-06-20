package com.dou.testmvp.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dou.testmvp.R;
import com.dou.testmvp.model.MainModelBean;
import com.dou.testmvp.presenter.MainPresenter;
import com.dou.testmvp.view.MainView;

/**
 * Created by mac on 16/6/13.
 */
public class MainActivity extends AppCompatActivity implements MainView {
    private ProgressBar mProgressBar;
    private TextView text;
    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        text = (TextView) findViewById(R.id.text_ling);
        mProgressBar = (ProgressBar) findViewById(R.id.mProgressBar);
        mMainPresenter = new MainPresenter(this);
        //制造延迟效果

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mMainPresenter.loadData();
            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        mMainPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showData(MainModelBean mainModelBean) {
        String showData = getResources().getString(R.string.city) + mainModelBean.getCity()
                + getResources().getString(R.string.wd) + mainModelBean.getWd()
                + getResources().getString(R.string.ws) + mainModelBean.getWs()
                + getResources().getString(R.string.time) + mainModelBean.getTime();
        text.setText(showData);
    }


    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }
}
