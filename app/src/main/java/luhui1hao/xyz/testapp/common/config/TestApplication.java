package luhui1hao.xyz.testapp.common.config;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

import luhui1hao.xyz.testapp.BuildConfig;

public class TestApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        if(BuildConfig.DEBUG){
            Stetho.initializeWithDefaults(this);
        }
    }

    public static Context getContext(){
        return mContext;
    }
}
