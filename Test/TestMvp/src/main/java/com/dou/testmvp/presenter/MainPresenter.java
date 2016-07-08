package com.dou.testmvp.presenter;

import com.dou.testmvp.model.MainModel;
import com.dou.testmvp.model.MainModelBean;
import com.dou.testmvp.view.MainView;

/**
 * Created by mac on 16/6/13.
 */
public class MainPresenter implements Presenter<MainView>, IMainPresenter {

    private MainView mMainView;
    private MainModel mMainModel;

    public MainPresenter(MainView view) {
        attachView(view);
        mMainModel = new MainModel(this);
    }

    @Override
    public void attachView(MainView view) {
        this.mMainView = view;
    }

    @Override
    public void detachView() {
        this.mMainView = null;
    }

    public void loadData() {
        mMainView.showProgress();
        mMainModel.loadData();
    }


    @Override
    public void loadDataSuccess(MainModelBean mainModelBean) {
        mMainView.showData(mainModelBean);
        mMainView.hideProgress();
    }

    @Override
    public void loadDataFailure() {
        mMainView.hideProgress();
    }
}
