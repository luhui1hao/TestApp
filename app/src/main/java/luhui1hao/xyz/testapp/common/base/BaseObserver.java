package luhui1hao.xyz.testapp.common.base;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import luhui1hao.xyz.testapp.common.config.TestApplication;
import luhui1hao.xyz.testapp.common.http.exception.ApiException;
import luhui1hao.xyz.testapp.common.http.exception.ExceptionEngine;
import luhui1hao.xyz.testapp.common.utils.RxErrorHandler;
import retrofit2.adapter.rxjava2.Result;

/**
 * @ClassName: BaseObserver
 * @Description:
 * @Author: Shao Yang
 * @CreateDate: 2019/1/30 17:59
 */
public abstract class BaseObserver<T> implements Observer<T> {
    private static Toast mToast;
    public static synchronized void showToast(String text) {
        if (mToast != null)
            mToast.cancel();
        Context context= TestApplication.getContext();
        if (context == null)
            return;
        mToast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        mToast.show();
    }
    /**
     * Response is ok or not http response
     *
     * @param t Abstract response object
     */
    protected abstract void onNewNext(T t);

    /**
     * Handling error
     *
     * @param ex api exception
     */
    protected void onError(ApiException ex) {
        Log.e("BaseObserver","onError",new Exception(ex.getDisplayMessage()));
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(T t) {
        if (!(t instanceof Result<?>)) {
            onNewNext(t);
            return;
        }
        ApiException apiException = RxErrorHandler.getDefault().getApiException((Result<?>) t);
        if (apiException == null) {
            onNewNext(t);
            return;
        }
        onError((Throwable)apiException);
    }

    @Override
    public void onError(Throwable e) {
        ApiException apiException = ExceptionEngine.handleException(e);
        onError(apiException);
    }

    @Override
    public void onComplete() {

    }
}
