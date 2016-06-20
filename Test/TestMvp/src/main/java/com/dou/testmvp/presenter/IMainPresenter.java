package com.dou.testmvp.presenter;

import com.dou.testmvp.model.MainModelBean;

/**
 * Created by mac on 16/6/13.
 */
public interface IMainPresenter {
    void loadDataSuccess(MainModelBean mainModelBean);

    void loadDataFailure();
}
