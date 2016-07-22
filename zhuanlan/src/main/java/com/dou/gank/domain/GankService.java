package com.dou.gank.domain;

import com.dou.gank.domain.enity.Day;
import com.dou.gank.domain.enity.HistoryDate;
import com.dou.gank.domain.enity.NowList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by mac on 16/6/20.
 */
public interface GankService {

    /**
     * http://gank.io/api/day/history
     */
    @GET("day/history")
    Observable<HistoryDate> getHistory();

    /**
     * 每日数据 http://gank.io/api/day/2015/08/07
     */
    @GET("day/{date}")
    Observable<Day> getDay(@Path("date") String date);

    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     */
    @GET("data/{type}/{prepage}/{page}")
    Observable<NowList> getNomalList(@Path("type") String type, @Path("prepage") int prepage, @Path("page") int page);


    /**
     * 字段	描述	备注
     url	想要提交的网页地址
     desc	对干货内容的描述	单独的文字描述
     who	提交者 ID	干货提交者的网络 ID
     type	干货类型	可选参数: Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
     debug	当前提交为测试数据	如果想要测试数据是否合法, 请设置 debug 为 true! 可选参数: true | false
     */
    //https://gank.io/api/add2gank post

}
