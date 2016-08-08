package cn.dou.mvp2.view;

import cn.dou.mvp2.WeatherBean;
import cn.dou.mvp2.base.BasePresenter;
import cn.dou.mvp2.base.BaseView;

/**
 * Created by mac on 16/7/27.
 */
public class TestContract {

    //mvp中的view层回调函数
    public interface View extends BaseView<Presenter> {

        void updateUI(WeatherBean bean);

        void updateFail(Throwable t);

    }

    //mvp中的presenter层的回调函数
    public interface Presenter extends BasePresenter {

        void getHttpData();

    }
}
