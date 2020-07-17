package luhui1hao.xyz.testapp.home.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.indicator.enums.IndicatorStyle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luhui1hao.xyz.testapp.R;
import luhui1hao.xyz.testapp.common.base.BaseActivity;
import luhui1hao.xyz.testapp.common.decoration.DividerItemDecoration;
import luhui1hao.xyz.testapp.common.decoration.HorizontalSpacingItemDecoration;
import luhui1hao.xyz.testapp.common.utils.DensityUtil;
import luhui1hao.xyz.testapp.common.utils.GlideRoundTransform;
import luhui1hao.xyz.testapp.common.utils.StatusBarUtil;
import luhui1hao.xyz.testapp.common.widget.BannerViewPagerHolder;
import luhui1hao.xyz.testapp.home.model.entity.HomeData;
import luhui1hao.xyz.testapp.home.presenter.HomePresenter;
import luhui1hao.xyz.testapp.home.view.adapter.HomeBannerAdapter;
import luhui1hao.xyz.testapp.home.view.presenterview.IHomeView;

public class HomeActivity extends BaseActivity implements IHomeView, OnRefreshListener {

    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.rv_fund)
    RecyclerView rvFund;
    @BindView(R.id.rv_news)
    RecyclerView rvNews;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private HomePresenter presenter;
    private BannerViewPager<HomeData.BannerData, BannerViewPagerHolder> mViewPager;
    private List<HomeData.BannerData> bannerDatas = new ArrayList<>();
    private List<HomeData.FundBean> funds = new ArrayList<>();
    private List<HomeData.NewsBean> news = new ArrayList<>();
    private BaseQuickAdapter fundAdapter;
    private BaseQuickAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        presenter = new HomePresenter(this);

        initView();
        initData();
    }

    private void initView() {
        StatusBarUtil.immersive(this);
        StatusBarUtil.setMargin(this, refreshLayout);
        StatusBarUtil.darkMode(mContext, true);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setEnableLoadMore(false);

        setupViewPager();
        initFundRecyclerView();
        initNewsRecyclerView();
    }

    private void initData() {
        presenter.getHomeData(true);
    }

    /**
     * 初始化Banner
     */
    private void setupViewPager() {
        mViewPager = findViewById(R.id.banner_view);
        mViewPager
                .setAutoPlay(true)
                .setScrollDuration(800)
                .setLifecycleRegistry(getLifecycle())
                .setIndicatorStyle(IndicatorStyle.ROUND_RECT)
                .setIndicatorSliderGap(getResources().getDimensionPixelOffset(R.dimen.qb_px_4))
                .setIndicatorSliderWidth(getResources().getDimensionPixelOffset(R.dimen.qb_px_4), getResources().getDimensionPixelOffset(R.dimen.qb_px_10))
                .setIndicatorSliderColor(getResources().getColor(R.color.white), getResources().getColor(R.color.gray))
                .setInterval(2000)
                .setOrientation(ViewPager2.ORIENTATION_HORIZONTAL)
                .setAdapter(new HomeBannerAdapter(mContext))
                .setOnPageClickListener(new BannerViewPager.OnPageClickListener() {
                    @Override
                    public void onPageClick(int position) {
                        Toast.makeText(mContext, "点击了第" + (position + 1) + "张图", Toast.LENGTH_SHORT).show();
                    }
                })
                .create(bannerDatas);
    }

    private void initFundRecyclerView() {
        rvFund.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        if(rvFund.getItemDecorationCount() == 0) {
            rvFund.addItemDecoration(new HorizontalSpacingItemDecoration(DensityUtil.dip2px(35), false));
        }
        fundAdapter = new BaseQuickAdapter<HomeData.FundBean, BaseViewHolder>(R.layout.home_funds_item_layout, funds) {
            @Override
            protected void convert(BaseViewHolder helper, HomeData.FundBean item) {
                helper.setText(R.id.tv_title, item.getTitle());
                helper.setText(R.id.tv_gains, item.getGains());
                helper.setText(R.id.tv_description, item.getDescription());

                TextView tvGain = helper.getView(R.id.tv_gains);
                if(item.isGain()){
                    tvGain.setTextColor(getResources().getColor(R.color.home_red));
                }else {
                    tvGain.setTextColor(getResources().getColor(R.color.green));
                }

                Button btn = helper.getView(R.id.btn_buy);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "buy", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        rvFund.setAdapter(fundAdapter);
    }

    private void initNewsRecyclerView() {
        rvNews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        if(rvNews.getItemDecorationCount() == 0) {
            rvNews.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL_LIST));
        }
        newsAdapter = new BaseQuickAdapter<HomeData.NewsBean, BaseViewHolder>(R.layout.home_news_item_layout, news) {
            @Override
            protected void convert(BaseViewHolder helper, HomeData.NewsBean item) {
                helper.setText(R.id.tv_title, item.getTitle());
                helper.setText(R.id.tv_subtitle, item.getSubTitle());
                helper.setText(R.id.tv_time, item.getTime());

                ImageView iv = helper.getView(R.id.iv_img);

                if(!TextUtils.isEmpty(item.getImgPath())){
                    iv.setVisibility(View.VISIBLE);
                    RequestOptions options = new RequestOptions()
                            .centerCrop()
                            .placeholder(R.drawable.img_placeholder)
                            .transform(new GlideRoundTransform(3)); //圆角
                    Glide.with(mContext).load(item.getImgPath()).apply(options).into(iv);
                }else {
                    iv.setVisibility(View.GONE);
                }
            }
        };
        rvNews.setAdapter(newsAdapter);
    }

    @OnClick({R.id.ll_scan, R.id.ll_pay, R.id.ll_transfer, R.id.ll_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_scan:
                break;
            case R.id.ll_pay:
                break;
            case R.id.ll_transfer:
                break;
            case R.id.ll_account:
                break;
        }
    }

    @Override
    public void clearPresenter() {
        presenter = null;
    }

    @Override
    public void loadData(HomeData data) {
        refreshLayout.finishRefresh();
        if (data == null || data.getData() == null) {
            return;
        }
        HomeData.DataBean dataBean = data.getData();
        if (dataBean.getBannerDatas() != null) {
            bannerDatas.clear();
            bannerDatas.addAll(dataBean.getBannerDatas());
            mViewPager.create(bannerDatas);
        }
        if (dataBean.getFunds() != null) {
            funds.clear();
            funds.addAll(dataBean.getFunds());

            fundAdapter.notifyDataSetChanged();
        }
        if (dataBean.getNews() != null) {
            news.clear();
            news.addAll(dataBean.getNews());

            newsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
        refreshLayout.finishRefresh();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        presenter.getHomeData(false);
    }
}
