package luhui1hao.xyz.testapp.common.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import luhui1hao.xyz.testapp.common.config.TestApplication;


/**
 * Created by shaoyang on 2017/12/1.
 */

public class DensityUtil {
    /**
     * dp to px
     * @param dpValue the unit is the value of dp
     * @return the unit is the value of px
     */
    public static int dip2px(float dpValue) {
        final float scale = TestApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px to dp
     * @param pxValue the unit is the value of px
     * @return the unit is the value of dp
     */
    public static int px2dip(float pxValue) {
        final float scale = TestApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * Get screen width
     * @return screen width
     */
    public static int getScreenWidth() {
        DisplayMetrics dm =TestApplication.getContext().getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        return w_screen;
    }

    /**
     * Get screen height
     * @return screen height
     */
    public static int getScreenHeight() {
        DisplayMetrics dm =TestApplication.getContext().getResources().getDisplayMetrics();
        int h_screen = dm.heightPixels;
        return h_screen;
    }

    /**
     * 获取ActionBar 高度
     * @param context
     * @return
     */
    public static int getActionBarHeight(Context context){
        TypedValue tv = new TypedValue();
        try {
            if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize,tv,true)){
                return  TypedValue.complexToDimensionPixelSize(tv.data,
                        context.getResources().getDisplayMetrics());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
