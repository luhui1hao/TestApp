package luhui1hao.xyz.testapp.common.http.exception;

import android.text.TextUtils;

/**
 * Created by shaoyang on 2018/1/15.
 * Http communication exception
 */

public class ApiException extends Exception {
    private int code;
    private String displayMessage;

    /**
     * @param throwable exception
     * @param code error code
     */
    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public ApiException(int code,String displayMessage) {
        super(displayMessage);
        this.code = code;
    }

    /**
     * set display message
     * @param displayMessage display message
     */
    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    /**
     * get display message
     * @return display message
     */
    public String getDisplayMessage() {
        if(code==401){
            return "登录信息失效";
        }else if(!TextUtils.isEmpty(displayMessage)){
            return displayMessage;
        }else{
            return "请求失败["+code+"]";
        }
    }

    /**
     * get error code
     * @return error code
     */
    public int getCode() {
        return code;
    }
    @Override
    public String getMessage() {
        return getDisplayMessage();
    }
}
