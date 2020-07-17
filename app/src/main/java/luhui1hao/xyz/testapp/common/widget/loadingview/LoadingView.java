package luhui1hao.xyz.testapp.common.widget.loadingview;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import luhui1hao.xyz.testapp.R;


public class LoadingView extends DialogFragment {

    public LoadingView(){}

    Animation operatingAnim;
    Dialog mDialog;
    RelativeLayout background;
    int color;
    private boolean isClickCancelAble = false;
    View arc;

    private boolean focusable=true;
    public void setFocusable(boolean focusable) {
        this.focusable=focusable;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (mDialog == null) {
            mDialog = new Dialog(getActivity(), R.style.train_dialog);
            mDialog.setContentView(R.layout.trainloading_main);
            mDialog.setCanceledOnTouchOutside(isClickCancelAble);
            mDialog.getWindow().setGravity(Gravity.CENTER);

            operatingAnim = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            operatingAnim.setRepeatCount(Animation.INFINITE);
            operatingAnim.setDuration(2000);


            LinearInterpolator lin = new LinearInterpolator();
            operatingAnim.setInterpolator(lin);

            View view = mDialog.getWindow().getDecorView();

            background = view.findViewById(R.id.background);

            if (color !=0)
                background.setBackgroundColor(color);

            arc = view.findViewById(R.id.arc_view);

            ShimmerLayout shimmerText = (ShimmerLayout) mDialog.findViewById(R.id.shimmer_text);
            shimmerText.startShimmerAnimation();

        }
        return mDialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        arc.setAnimation(operatingAnim);
        if(mDialog!=null){
            mDialog.getWindow().setFlags(focusable?0: WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        operatingAnim.reset();

        arc.clearAnimation();
    }

    public void setClickCancelAble(boolean bo){
        isClickCancelAble = bo;
    }

    public void setBackgroundColor(int color){
        this.color = color;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mDialog = null;
        System.gc();
    }

    public boolean isShowing() {
        boolean isShowing = false;
        if (mDialog != null) {
            isShowing = mDialog.isShowing();
        }
        return isShowing;
    }
}
