package com.wyjson.debug_banner;

import androidx.annotation.ColorRes;

/**
 * debug show checked mode banner label
 *
 * @author Wyjson
 * @version 1
 * @date 2019-09-21 23:40
 */
public class Banner {

    private BannerGravity bannerGravity;
    private @ColorRes int bannerColorRes;
    private @ColorRes int textColorRes;
    private String bannerText;

    public Banner() {
        this.bannerGravity = BannerGravity.END;
        this.bannerColorRes = android.R.color.holo_red_dark;
        this.textColorRes = android.R.color.white;
        this.bannerText = "DEBUG";
    }

    public Banner(BannerGravity bannerGravity, @ColorRes int bannerColorRes, @ColorRes int textColorRes, String bannerText) {
        this.bannerGravity = bannerGravity;
        this.bannerColorRes = bannerColorRes;
        this.textColorRes = textColorRes;
        this.bannerText = bannerText;
    }

    public BannerGravity getBannerGravity() {
        return bannerGravity;
    }

    public void setBannerGravity(BannerGravity bannerGravity) {
        this.bannerGravity = bannerGravity;
    }

    public int getBannerColorRes() {
        return bannerColorRes;
    }

    public void setBannerColorRes(int bannerColorRes) {
        this.bannerColorRes = bannerColorRes;
    }

    public int getTextColorRes() {
        return textColorRes;
    }

    public void setTextColorRes(int textColorRes) {
        this.textColorRes = textColorRes;
    }

    public String getBannerText() {
        return bannerText;
    }

    public void setBannerText(String bannerText) {
        this.bannerText = bannerText;
    }
}
