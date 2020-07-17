package luhui1hao.xyz.testapp.home.model.entity;

import java.util.List;

import luhui1hao.xyz.testapp.common.bean.CommonBean;

public class HomeData extends CommonBean {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean{
        private List<BannerData> bannerDatas;
        private List<FundBean> funds;
        private List<NewsBean> news;

        public List<BannerData> getBannerDatas() {
            return bannerDatas;
        }

        public void setBannerDatas(List<BannerData> bannerDatas) {
            this.bannerDatas = bannerDatas;
        }

        public List<FundBean> getFunds() {
            return funds;
        }

        public void setFunds(List<FundBean> funds) {
            this.funds = funds;
        }

        public List<NewsBean> getNews() {
            return news;
        }

        public void setNews(List<NewsBean> news) {
            this.news = news;
        }
    }

    public class BannerData{
        private String id;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public class FundBean{
        private String id;
        private String title;
        private String gains;
        private boolean isGain;
        private String description;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGains() {
            return gains;
        }

        public void setGains(String gains) {
            this.gains = gains;
        }

        public boolean isGain() {
            return isGain;
        }

        public void setGain(boolean gain) {
            isGain = gain;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public class NewsBean{
        private String id;
        private String title;
        private String subTitle;
        private String time;
        private String imgPath;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }
    }
}
