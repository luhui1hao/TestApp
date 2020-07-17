package luhui1hao.xyz.testapp.home.model;

import io.reactivex.Observable;
import luhui1hao.xyz.testapp.common.base.IModel;
import luhui1hao.xyz.testapp.home.model.entity.HomeData;
import retrofit2.adapter.rxjava2.Result;

public interface IHomeModel extends IModel {
    Observable<Result<HomeData>> getHomeData();
}
