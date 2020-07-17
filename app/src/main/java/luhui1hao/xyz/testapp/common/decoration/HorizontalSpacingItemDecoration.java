package luhui1hao.xyz.testapp.common.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class HorizontalSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spacing; //间隔
    private boolean includeEdge; //是否包含边缘

    public HorizontalSpacingItemDecoration(int spacing, boolean includeEdge) {
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int size = parent.getChildCount();

        if (!includeEdge) {
            if (position == 0) {
                outRect.left = 0;
            }else {
                outRect.left = spacing/2;
            }

            if(position == size-1){
                outRect.right = 0;
            }else {
                outRect.right = spacing/2;
            }
        } else {
            outRect.left = spacing;
            outRect.right = spacing;
        }
    }
}