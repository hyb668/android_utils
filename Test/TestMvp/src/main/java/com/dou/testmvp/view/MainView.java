package com.dou.testmvp.view;

import com.dou.testmvp.model.MainModelBean;

/**
 * Created by mac on 16/6/13.
 */
public interface MainView {
    void showData(MainModelBean mainModelBean);

    void showProgress();

    void hideProgress();
}
