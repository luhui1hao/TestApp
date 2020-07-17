package luhui1hao.xyz.testapp.common.base;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import luhui1hao.xyz.testapp.common.widget.loadingview.LoadingView;

public abstract class BaseActivity extends AppCompatActivity {

    protected BaseActivity mContext;
    protected LoadingView mLoadingView=new LoadingView();

    public abstract void clearPresenter();
    private static List<BaseActivity> displayedActivitys=new ArrayList<>();

    public static BaseActivity currentActivity() {
        return displayedActivitys.isEmpty()?null:displayedActivitys.get(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayedActivitys.add(0,this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        displayedActivitys.remove(this);
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mContext = this;
        Log.e("当前界面", getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        clearPresenter();
        super.onDestroy();
    }

    public void showLoading() {
        if (!mLoadingView.isShowing()) {
            getSupportFragmentManager().beginTransaction()
                    .remove(mLoadingView)
                    .commitAllowingStateLoss();

            getSupportFragmentManager().beginTransaction()
                    .add(mLoadingView, getClass().getName())
                    .commitAllowingStateLoss();
        }
    }

    public void hideLoading() {
        if(mLoadingView.isShowing()){
            mLoadingView.dismissAllowingStateLoss();
        }
    }

    public void showErrorMsg(String errorMsg) {
        if(mLoadingView.isShowing()){
            mLoadingView.dismissAllowingStateLoss();
        }
        Toast.makeText(mContext, errorMsg, Toast.LENGTH_SHORT).show();
    }
}
