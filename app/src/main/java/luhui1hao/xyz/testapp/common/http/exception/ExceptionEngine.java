package luhui1hao.xyz.testapp.common.http.exception;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import luhui1hao.xyz.testapp.common.config.TestApplication;
import luhui1hao.xyz.testapp.common.utils.NetUtil;
import retrofit2.HttpException;


/**
 * Created by shaoyang on 2018/1/15.
 * Exception handling Engine
 */

public class ExceptionEngine {
    //http code
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    /**
     * translate exception to ApiException
     * @param e exception
     * @return ApiException object
     */
    public static ApiException handleException(Throwable e){
        if(e instanceof ApiException){
            return (ApiException)e;
        }
        ApiException ex;
        if (e instanceof HttpException){             //HTTP error
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, ERROR.HTTP_ERROR);
            switch(httpException.code()){
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.setDisplayMessage("network error");
                    break;
            }
            return ex;
        }
//        else if (e instanceof ServerException){    // server error
//            ServerException resultException = (ServerException) e;
//            ex = new ApiException(resultException, resultException.getCode());
//            ex.setDisplayMessage(resultException.getMsg());
//            return ex;
//        }
        else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof JsonSyntaxException){
            ex = new ApiException(e, ERROR.PARSE_ERROR);
            ex.setDisplayMessage("数据解析错误");
            return ex;
        }else if(e instanceof ConnectException){
            ex = new ApiException(e, ERROR.NETWORK_ERROR);
            ex.setDisplayMessage("网络连接错误，请检查网络连接是否正常");
            return ex;
        }else if(e instanceof SocketTimeoutException){
            ex = new ApiException(e, ERROR.LOCAL_TIME_OUT);
            ex.setDisplayMessage("请求超时");
            return ex;
        }else if(e instanceof UnknownHostException){
            ex = new ApiException(e, ERROR.UNKNOW_HOST_EXCEPTION);
            ex.setDisplayMessage("无网络，请检查网络连接是否正常");
            return ex;
        }else if(e instanceof IOException){
            ex = new ApiException(e, ERROR.LOCAL_IO_EXCEPTION);
            ex.setDisplayMessage("IO异常:" + e.getMessage());
            return ex;
        }else if(!NetUtil.isConnected(TestApplication.getContext())){
            ex = new ApiException(e, ERROR.NETWORK_ERROR);
            ex.setDisplayMessage("无网络:" + e.getMessage());
            return ex;
        }
        else {
            ex = new ApiException(e, ERROR.UNKNOWN);
            ex.setDisplayMessage("未知错误:"+e.getMessage());
//            ex.setDisplayMessage(e.getMessage());
            return ex;
        }
    }
}
