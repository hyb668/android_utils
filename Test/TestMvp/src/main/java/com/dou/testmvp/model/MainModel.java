package com.dou.testmvp.model;

import com.dou.testmvp.presenter.IMainPresenter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by mac on 16/6/13.
 */
public class MainModel {

    IMainPresenter mIMainPresenter;

    public MainModel(IMainPresenter iMainPresenter) {
        this.mIMainPresenter = iMainPresenter;
    }

    public void loadData() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get("http://www.weather.com.cn/adat/sk/101010100.html", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    MainModelBean mainModelBean = new MainModelBean();
                    JSONObject weatherinfo = response.getJSONObject("weatherinfo");
                    mainModelBean.setCity(weatherinfo.getString("city"));
                    mainModelBean.setWd(weatherinfo.getString("WD"));
                    mainModelBean.setWs(weatherinfo.getString("WS"));
                    mainModelBean.setTime(weatherinfo.getString("time"));
                    mIMainPresenter.loadDataSuccess(mainModelBean);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                mIMainPresenter.loadDataFailure();
            }
        });
    }
}
