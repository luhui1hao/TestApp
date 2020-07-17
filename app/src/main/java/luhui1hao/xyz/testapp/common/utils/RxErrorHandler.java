package luhui1hao.xyz.testapp.common.utils;

import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.Observable;
import luhui1hao.xyz.testapp.common.http.exception.ApiException;
import luhui1hao.xyz.testapp.common.http.exception.ExceptionEngine;
import luhui1hao.xyz.testapp.common.http.exception.ServerException;
import retrofit2.adapter.rxjava2.Result;


/**
 *
 * @ClassName:      RxErrorHandler
 * @Description:    communication error handler
 * @Author:         Shao Yang
 * @CreateDate:     2019/12/4 17:06
 */
public class RxErrorHandler {
   private static volatile RxErrorHandler defaultInstance;

   public static RxErrorHandler getDefault() {
       if (defaultInstance == null) {
           synchronized (RxErrorHandler.class) {
               if (defaultInstance == null) {
                   defaultInstance = new RxErrorHandler();
               }
           }
       }
       return defaultInstance ;
   }

   /**
    * Unite handle error http result
    * @param result http result
    * @param <T>  Custom object
    * @return Observable object
    */
   public  <T> Observable<T> handleError(Result<?> result) {
       if (result.error() != null) {
           ApiException apiException =  ExceptionEngine.handleException(result.error());
           return Observable.error(apiException);
       } else if (result.response().code() != 200){
           try {
               ServerException serverException = new ServerException();
               serverException.setCode(result.response().code());
               String msg = result.response().errorBody().string();
               Gson gson = new Gson();
               ServerException.ErrorBean errorBean = null;
               try {
                   errorBean = gson.fromJson(msg , ServerException.ErrorBean.class);
               } catch (Exception e){
                   e.printStackTrace();
               }
               ApiException apiException = null;
               if (errorBean == null) {
                   errorBean = new ServerException.ErrorBean();
                   errorBean.setError(msg);
                   errorBean.setMessage(msg);
                   serverException.setErrorBean(errorBean);
                   apiException = new ApiException(serverException , serverException.getCode());
                   apiException.setDisplayMessage(serverException.getErrorBean().getMessage());
               } else {
                   serverException.setErrorBean(errorBean);
                   apiException = new ApiException(serverException , serverException.getCode());
                   apiException.setDisplayMessage(serverException.getErrorBean().getMessage());
               }

               return Observable.error(apiException);
           } catch (IOException e) {
               e.printStackTrace();
               ApiException apiException =  ExceptionEngine.handleException(e);
               return Observable.error(apiException);
           }
       }
       return null;
   }

   /**
    * Unite get api exception
    * @param result http result
    * @return ApiException object
    */
   public  ApiException getApiException(Result<?> result) {
       if (result.error() != null) {
           ApiException apiException =  ExceptionEngine.handleException(result.error());
           return apiException;
       } else if (result.response().code() != 200){
           try {
               ServerException serverException = new ServerException();
               serverException.setCode(result.response().code());
               String msg = result.response().errorBody().string();
               Gson gson = new Gson();
               ServerException.ErrorBean errorBean = null;
               try {
                   errorBean = gson.fromJson(msg , ServerException.ErrorBean.class);
               } catch (Exception e){
                   e.printStackTrace();
               }
               ApiException apiException = null;
               if (errorBean == null) {
                   errorBean = new ServerException.ErrorBean();
                   errorBean.setError(msg);
                   errorBean.setMessage("HTTP请求错误["+serverException.getCode()+"]");
                   serverException.setErrorBean(errorBean);
                   apiException = new ApiException(serverException , serverException.getCode());
                   apiException.setDisplayMessage(serverException.getErrorBean().getMessage());
               } else {
                   serverException.setErrorBean(errorBean);
                   apiException = new ApiException(serverException , serverException.getCode());
                   apiException.setDisplayMessage(serverException.getErrorBean().getMessage());
               }
               return apiException;
           } catch (Exception e) {
               ApiException apiException =  ExceptionEngine.handleException(e);
//                ApiException apiException = new ApiException(e , ERROR.UNKNOWN);
               return apiException;
           }
       }
       return null;
   }
}
