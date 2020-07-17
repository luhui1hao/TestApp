package luhui1hao.xyz.testapp.home.presenter;

import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import luhui1hao.xyz.testapp.common.base.BaseObserver;
import luhui1hao.xyz.testapp.common.base.BasePresenter;
import luhui1hao.xyz.testapp.common.config.TestApplication;
import luhui1hao.xyz.testapp.common.http.exception.ApiException;
import luhui1hao.xyz.testapp.home.model.IHomeModel;
import luhui1hao.xyz.testapp.home.model.entity.HomeData;
import luhui1hao.xyz.testapp.home.model.impl.HomeModel;
import luhui1hao.xyz.testapp.home.view.presenterview.IHomeView;
import retrofit2.adapter.rxjava2.Result;

public class HomePresenter extends BasePresenter {
    private IHomeView mView;
    private IHomeModel mModel;

    public HomePresenter(IHomeView view){
        mView = view;
        mModel = new HomeModel();
    }

    /**
     * 获取首页数据
     */
    public void getHomeData(boolean isLoading) {
        if(isLoading){
            mView.showLoading();
        }
        Observable observable = mModel.getHomeData();
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Result<HomeData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        addDispose(d);
                    }

                    @Override
                    protected void onNewNext(Result<HomeData> result) {
                        HomeData bean = result.response().body();
                        if(bean.getCode() == 200){
                            mView.loadData(bean);
                        }else {
                            Toast.makeText(TestApplication.getContext(), bean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        mView.hideLoading();
                    }
                });
    }

}
