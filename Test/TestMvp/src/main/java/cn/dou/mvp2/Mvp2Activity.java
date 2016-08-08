package cn.dou.mvp2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.dou.mvp2.presenter.TestPresenter;
import cn.dou.mvp2.view.TestContract;

/**
 * Created by mac on 16/7/27.
 */
public class Mvp2Activity extends AppCompatActivity implements TestContract.View {
    private TestContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new TestPresenter(this);
        mPresenter.getHttpData();
    }

    @Override
    public void updateUI(WeatherBean bean) {

    }

    @Override
    public void updateFail(Throwable t) {

    }
}
