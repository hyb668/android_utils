package cn.dou.mvp2.base;

/**
 * Created by mac on 16/7/27.
 */
public interface BaseModelCallBack<T> {

    void onResponse(T t);

    void onFailure(Throwable t);
}
