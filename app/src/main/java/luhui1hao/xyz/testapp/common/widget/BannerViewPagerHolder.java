package luhui1hao.xyz.testapp.common.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zhpan.bannerview.BaseViewHolder;

import luhui1hao.xyz.testapp.R;
import luhui1hao.xyz.testapp.common.utils.GlideRoundTransform;
import luhui1hao.xyz.testapp.home.model.entity.HomeData;

public class BannerViewPagerHolder extends BaseViewHolder<HomeData.BannerData> {
    private Context mContext;

    public BannerViewPagerHolder(Context mContext, @NonNull View itemView) {
        super(itemView);

        this.mContext = mContext;
    }

    @Override
    public void bindData(HomeData.BannerData data, int position, int pageSize) {
        ImageView iv = itemView.findViewById(R.id.iv_img);

        if(data != null){
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.img_placeholder)
                    .transform(new GlideRoundTransform(8)); //圆角
            Glide.with(mContext).load(data.getUrl()).apply(options).into(iv);
        }
    }
}
