package com.dou.gank.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dou.gank.domain.RetrofitService;
import com.dou.gank.domain.enity.NowList;
import com.dou.gank.view.adapter.TypeAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.wu.allen.zhuanlan.R;

import dou.utils.DLog;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mac on 16/6/21.
 */
public class WelfareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare);

        // 获取RecyclerView对象
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // 创建线性布局管理器（默认是垂直方向）
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 为RecyclerView指定布局管理对象
        recyclerView.setLayoutManager(layoutManager);
        // 创建Adapter
        final TypeAdapter typeAdapter = new TypeAdapter();
        // 填充Adapter
        recyclerView.setAdapter(typeAdapter);
        recyclerView.addItemDecoration(new DividerDecoration(0xffff0000, 2));

        RetrofitService.getService().getNomalList("福利", 10, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NowList>() {
                    @Override
                    public void onCompleted() {
                        DLog.d("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        DLog.d(e.getMessage());
                    }

                    @Override
                    public void onNext(NowList nowList) {
                        DLog.d("size  :  " + nowList.getResults().size());
                        typeAdapter.addData(nowList.getResults());
                    }
                });
    }

}
