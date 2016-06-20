package com.dou.testmvp.presenter;

/**
 * Created by mac on 16/6/13.
 */
public interface Presenter<V> {

    void attachView(V view);

    void detachView();
}
