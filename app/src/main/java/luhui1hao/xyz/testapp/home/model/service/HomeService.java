package luhui1hao.xyz.testapp.home.model.service;

import io.reactivex.Observable;
import luhui1hao.xyz.testapp.home.model.entity.HomeData;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;

public interface HomeService {
    /**
     * 获取首页数据
     */
    @GET("home")
    Observable<Result<HomeData>> getHomeData();
}
