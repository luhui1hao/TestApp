package luhui1hao.xyz.testapp.homemvvm.viewmodel;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import luhui1hao.xyz.testapp.common.base.BaseObserver;
import luhui1hao.xyz.testapp.common.config.TestApplication;
import luhui1hao.xyz.testapp.common.http.exception.ApiException;
import luhui1hao.xyz.testapp.home.model.IHomeModel;
import luhui1hao.xyz.testapp.home.model.entity.HomeData;
import luhui1hao.xyz.testapp.home.model.impl.HomeModel;
import retrofit2.adapter.rxjava2.Result;

public class HomeViewModel extends ViewModel {
    private IHomeModel mModel = new HomeModel();
    private MutableLiveData<HomeData> homeData = new MutableLiveData<>();

    public LiveData<HomeData> getHomeData(){
        if(homeData == null){
            homeData = new MutableLiveData<>();
        }
        return homeData;
    }

    public void requestHomeData(){
        Observable observable = mModel.getHomeData();
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Result<HomeData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                    }

                    @Override
                    protected void onNewNext(Result<HomeData> result) {
                        HomeData bean = result.response().body();
                        if(bean.getCode() == 200){
                            homeData.setValue(bean);
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
                    }
                });
    }
}
