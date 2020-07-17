package luhui1hao.xyz.testapp.common.http;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import luhui1hao.xyz.testapp.BuildConfig;
import luhui1hao.xyz.testapp.common.http.converter.ResponseConverterFactory;
import luhui1hao.xyz.testapp.common.http.converter.StringConverterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by shaoyang on 2017/12/6.
 * Retrofit Service for http communication
 */
public class RetrofitManager {
    public static String BASE_URL = BuildConfig.BASE_URL;
    //Set the cache is valid for two days
    protected static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    //Cache-Control query cache settings, if-only-cache only query the cache without requesting the server, max-stale can be set with the cache expiration time
    protected static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //Query network Cache-Control settings, the head Cache-Control is set to max-age = 0 will not use the cache and request the server
    protected static final String CACHE_CONTROL_NETWORK = "max-age=0";

    private static OkHttpClient mOkHttpClient;
//    private static final int DEFAULT_TIMEOUT = 180;
    private static final int DEFAULT_TIMEOUT = 60;
//    private static final int READ_TIMEOUT = 120;
    private static final int READ_TIMEOUT = 60;
    public static int responseType = 0; // 0 ,default(GSON) ; 1, string

    private RetrofitManager() {
    }

    private volatile static RetrofitManager instance = null;

    public static RetrofitManager getInstance() {
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                if (instance == null) {
                    instance = new RetrofitManager();
                }
            }
        }
        return instance;
    }

    private volatile static Retrofit retrofit = null;
    private volatile static Retrofit retrofitString = null;

    public static Retrofit createRetrofit() {
        if (retrofit == null) {
            synchronized (RetrofitManager.class) {
                if (retrofit == null) {
                    initOkHttpClient();
                    retrofit = new Retrofit.Builder()
                            .client(mOkHttpClient)
                            .baseUrl(BASE_URL)
//                            .addConverterFactory(GsonConverterFactory.create())
                            .addConverterFactory(ResponseConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }

    public static void setRetrofitReset() {
        instance = null;
        retrofit = null;
        mOkHttpClient = null;
    }

    public static Retrofit createRetrofitForString() {
        if (retrofitString == null) {
            synchronized (RetrofitManager.class) {
                if (retrofitString == null) {
                    initOkHttpClient();
                    retrofitString = new Retrofit.Builder()
                            .client(mOkHttpClient)
                            .baseUrl(BASE_URL)
                            .addConverterFactory(StringConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }
        return retrofitString;
    }

    // init OkHttpClient
    private static void initOkHttpClient() {
        if (mOkHttpClient == null) {
            OkHttpClient.Builder builder = null;
                builder = new OkHttpClient.Builder()
                        .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
            //Stetho调试Retrofit的网络请求,添加拦截器
            if(BuildConfig.DEBUG){
                builder.addNetworkInterceptor(new StethoInterceptor());
            }
//            if (BuildConfig.DEBUG) {
//                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//                builder.addInterceptor(interceptor);
//            }
            mOkHttpClient = builder.build();
        }
    }

    /**
     * Caching policies based on network conditions
     *
     * @return
     */
//    @NonNull
//    public static String getCacheControl() {
//        return NetUtil.isConnected(CompanionApplication.getContext()) ? CACHE_CONTROL_NETWORK : CACHE_CONTROL_CACHE;
//    }

}
