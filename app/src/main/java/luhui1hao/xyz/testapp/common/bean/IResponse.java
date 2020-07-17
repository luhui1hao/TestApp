package luhui1hao.xyz.testapp.common.bean;
/**
 * @Description: 通用HTTP返回数据结构
 */
public class IResponse<T>{
    /**
     * code : 0
     * message : "string"
     * data : "string"
     */
    public long code;
    public String message;
    public T data;
}
