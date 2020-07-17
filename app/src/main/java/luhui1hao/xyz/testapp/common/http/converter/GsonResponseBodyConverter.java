package luhui1hao.xyz.testapp.common.http.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.lang.reflect.Type;

import luhui1hao.xyz.testapp.common.http.exception.ApiException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @ClassName: GsonResponseBodyConverter
 * @Description:
 * @Author: Shao Yang
 * @CreateDate: 2019/4/1 11:25
 */
public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    public GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();        //先将返回的json数据解析到Response中，如果code==200，则解析到我们的实体基类中，否则抛异常
//        Response httpResult = null;
        ApiException apiException = null;
        try {
            T httpResult = gson.fromJson(response, type);
            return httpResult;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
//            apiException = new ApiException(e, ERROR.PARSE_ERROR);
            throw e;
        }
//        return gson.fromJson(response, type);
    }
}

