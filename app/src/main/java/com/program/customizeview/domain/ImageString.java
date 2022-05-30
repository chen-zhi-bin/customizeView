package com.program.customizeview.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class ImageString implements Serializable {

    /**
     * images : [{"startdate":"20220528","fullstartdate":"202205281600","enddate":"20220529","url":"/th?id=OHR.HyaliteCreek_ZH-CN0400013447_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp","urlbase":"/th?id=OHR.HyaliteCreek_ZH-CN0400013447","copyright":"加勒廷国家森林里的小溪，美国蒙大拿州 (© Jared Lloyd/Getty Images)","copyrightlink":"https://www.bing.com/search?q=%E5%8A%A0%E5%8B%92%E5%BB%B7%E5%9B%BD%E5%AE%B6%E6%A3%AE%E6%9E%97&form=hpcapt&mkt=zh-cn","title":"垂钓者的天堂","quiz":"/search?q=Bing+homepage+quiz&filters=WQOskey:%22HPQuiz_20220528_HyaliteCreek%22&FORM=HPQUIZ","wp":true,"hsh":"a330040d247623e7256685002da995bd","drk":1,"top":1,"bot":1,"hs":[]}]
     * tooltips : {"loading":"正在加载...","previous":"上一个图像","next":"下一个图像","walle":"此图片不能下载用作壁纸。","walls":"下载今日美图。仅限用作桌面壁纸。"}
     */

    @SerializedName("tooltips")
    private TooltipsBean tooltips;
    @SerializedName("images")
    private List<ImagesBean> images;


    public static class TooltipsBean implements Serializable {
        /**
         * loading : 正在加载...
         * previous : 上一个图像
         * next : 下一个图像
         * walle : 此图片不能下载用作壁纸。
         * walls : 下载今日美图。仅限用作桌面壁纸。
         */

        @SerializedName("loading")
        private String loading;
        @SerializedName("previous")
        private String previous;
        @SerializedName("next")
        private String next;
        @SerializedName("walle")
        private String walle;
        @SerializedName("walls")
        private String walls;
    }

    public TooltipsBean getTooltips() {
        return tooltips;
    }

    public void setTooltips(TooltipsBean tooltips) {
        this.tooltips = tooltips;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "ImageString{" +
                "tooltips=" + tooltips +
                ", images=" + images +
                '}';
    }

    public static class ImagesBean implements Serializable {
        /**
         * startdate : 20220528
         * fullstartdate : 202205281600
         * enddate : 20220529
         * url : /th?id=OHR.HyaliteCreek_ZH-CN0400013447_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp
         * urlbase : /th?id=OHR.HyaliteCreek_ZH-CN0400013447
         * copyright : 加勒廷国家森林里的小溪，美国蒙大拿州 (© Jared Lloyd/Getty Images)
         * copyrightlink : https://www.bing.com/search?q=%E5%8A%A0%E5%8B%92%E5%BB%B7%E5%9B%BD%E5%AE%B6%E6%A3%AE%E6%9E%97&form=hpcapt&mkt=zh-cn
         * title : 垂钓者的天堂
         * quiz : /search?q=Bing+homepage+quiz&filters=WQOskey:%22HPQuiz_20220528_HyaliteCreek%22&FORM=HPQUIZ
         * wp : true
         * hsh : a330040d247623e7256685002da995bd
         * drk : 1
         * top : 1
         * bot : 1
         * hs : []
         */

        @SerializedName("startdate")
        private String startdate;
        @SerializedName("fullstartdate")
        private String fullstartdate;
        @SerializedName("enddate")
        private String enddate;
        @SerializedName("url")
        private String url;
        @SerializedName("urlbase")
        private String urlbase;
        @SerializedName("copyright")
        private String copyright;
        @SerializedName("copyrightlink")
        private String copyrightlink;
        @SerializedName("title")
        private String title;
        @SerializedName("quiz")
        private String quiz;
        @SerializedName("wp")
        private Boolean wp;
        @SerializedName("hsh")
        private String hsh;
        @SerializedName("drk")
        private Integer drk;
        @SerializedName("top")
        private Integer top;
        @SerializedName("bot")
        private Integer bot;
        @SerializedName("hs")
        private List<?> hs;

        public String getStartdate() {
            return startdate;
        }

        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }

        public String getFullstartdate() {
            return fullstartdate;
        }

        public void setFullstartdate(String fullstartdate) {
            this.fullstartdate = fullstartdate;
        }

        public String getEnddate() {
            return enddate;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlbase() {
            return urlbase;
        }

        public void setUrlbase(String urlbase) {
            this.urlbase = urlbase;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        public String getCopyrightlink() {
            return copyrightlink;
        }

        public void setCopyrightlink(String copyrightlink) {
            this.copyrightlink = copyrightlink;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getQuiz() {
            return quiz;
        }

        public void setQuiz(String quiz) {
            this.quiz = quiz;
        }

        public Boolean getWp() {
            return wp;
        }

        public void setWp(Boolean wp) {
            this.wp = wp;
        }

        public String getHsh() {
            return hsh;
        }

        public void setHsh(String hsh) {
            this.hsh = hsh;
        }

        public Integer getDrk() {
            return drk;
        }

        public void setDrk(Integer drk) {
            this.drk = drk;
        }

        public Integer getTop() {
            return top;
        }

        public void setTop(Integer top) {
            this.top = top;
        }

        public Integer getBot() {
            return bot;
        }

        public void setBot(Integer bot) {
            this.bot = bot;
        }

        public List<?> getHs() {
            return hs;
        }

        public void setHs(List<?> hs) {
            this.hs = hs;
        }

        @Override
        public String toString() {
            return "ImagesBean{" +
                    "startdate='" + startdate + '\'' +
                    ", fullstartdate='" + fullstartdate + '\'' +
                    ", enddate='" + enddate + '\'' +
                    ", url='" + url + '\'' +
                    ", urlbase='" + urlbase + '\'' +
                    ", copyright='" + copyright + '\'' +
                    ", copyrightlink='" + copyrightlink + '\'' +
                    ", title='" + title + '\'' +
                    ", quiz='" + quiz + '\'' +
                    ", wp=" + wp +
                    ", hsh='" + hsh + '\'' +
                    ", drk=" + drk +
                    ", top=" + top +
                    ", bot=" + bot +
                    ", hs=" + hs +
                    '}';
        }
    }
}
