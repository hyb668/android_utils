package dou.gank.http;

import dou.gank.enity.History;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by mac on 16/7/22.
 */
public interface GankApi {

    //base : http://gank.io/api/
    //http://gank.io/api/day/history
    @GET("day/history")
    Observable<History> getHistory();

    //http://gank.io/api/data/Android/10/1
    @GET("data/{categoty}/{count}/{page}")
    Observable<History> getByCategory(
            @Path("category") String category,
            @Path("count") int count,
            @Path("page") int page
    );
    //http://gank.io/api/day/2015/08/06


}
