package luhui1hao.xyz.testapp.common.widget.loadingview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import luhui1hao.xyz.testapp.R;


public class ArcView extends View {

    private int color;
    private float strokeWidth = dipToPx(3);

    public ArcView(Context context) {
        this(context, null);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ArcView);
        color = a.getColor(R.styleable.ArcView_color, Color.GREEN);
        strokeWidth = a.getDimension(R.styleable.ArcView_stroke_width, dipToPx(3));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);// 画线模式
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);

        // 绘制弧形
        canvas.drawArc(0, 0, getMeasuredWidth() - strokeWidth/2, getMeasuredHeight() - strokeWidth/2, 0, 80, false, paint);
    }

    /**
     * dip 转换成px
     * @param dip
     * @return
     */
    private int dipToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int)(dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }
}
