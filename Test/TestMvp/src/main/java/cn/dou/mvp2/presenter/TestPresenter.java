package cn.dou.mvp2.presenter;

import cn.dou.mvp2.base.BaseModelCallBack;
import cn.dou.mvp2.model.TestModel;
import cn.dou.mvp2.view.TestContract;

/**
 * Created by mac on 16/7/27.
 */
public class TestPresenter implements TestContract.Presenter {
    private TestModel mModel;

    private TestContract.View mView;

    public TestPresenter(TestContract.View view) {
        mView = view;
//        mView.setPresenter(this);
        mModel = new TestModel();
    }


    /**
     * 异步获取数据
     */
    @Override
    public void getHttpData() {

        mModel.getData(new BaseModelCallBack() {
            //Model获取数据后的回调方法onResponse

            @Override
            public void onResponse(Object o) {

            }

            //Model获取数据后的回调方法onFailure
            @Override
            public void onFailure(Throwable t) {
                mView.updateFail(t);
            }
        });
    }

    /**
     * 默认的方法
     */
    @Override
    public void start() {

    }
}
