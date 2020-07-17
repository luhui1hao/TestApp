package luhui1hao.xyz.testapp.home.model.impl;

import io.reactivex.Observable;
import luhui1hao.xyz.testapp.common.http.RetrofitManager;
import luhui1hao.xyz.testapp.home.model.IHomeModel;
import luhui1hao.xyz.testapp.home.model.entity.HomeData;
import luhui1hao.xyz.testapp.home.model.service.HomeService;
import retrofit2.adapter.rxjava2.Result;

public class HomeModel implements IHomeModel {
    private HomeService homeService;

    {
        homeService = RetrofitManager.getInstance().createRetrofit().create(HomeService.class);
    }

    /**
     * 获取首页数据
     * @return
     */
    @Override
    public Observable<Result<HomeData>> getHomeData() {
        Observable<Result<HomeData>> observable = homeService.getHomeData();
        return observable;
    }
}
