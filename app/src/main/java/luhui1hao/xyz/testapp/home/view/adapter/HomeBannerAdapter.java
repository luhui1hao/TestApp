package luhui1hao.xyz.testapp.home.view.adapter;

import android.content.Context;
import android.view.View;

import com.zhpan.bannerview.BaseBannerAdapter;

import luhui1hao.xyz.testapp.R;
import luhui1hao.xyz.testapp.common.widget.BannerViewPagerHolder;
import luhui1hao.xyz.testapp.home.model.entity.HomeData;

public class HomeBannerAdapter extends BaseBannerAdapter<HomeData.BannerData, BannerViewPagerHolder> {
    private Context mContext;

    public HomeBannerAdapter(Context context){
        this.mContext = context;
    }

    @Override
    protected void onBind(BannerViewPagerHolder holder, HomeData.BannerData data, int position, int pageSize) {
        holder.bindData(data, position, pageSize);
    }

    @Override
    public BannerViewPagerHolder createViewHolder(View itemView, int viewType) {
        return new BannerViewPagerHolder(mContext, itemView);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.home_banner_item_layout;
    }
}
