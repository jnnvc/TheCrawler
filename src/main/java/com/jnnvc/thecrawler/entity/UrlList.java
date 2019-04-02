package com.jnnvc.thecrawler.entity;

import com.jnnvc.thecrawler.service.UrlService;

import java.util.List;

public class UrlList {

    /**
     * a标签url
     */
    private List<String> aUrl;

    /**
     * 图片Url
     */
    private List<String> imgUrl;


    public List<String> getaUrl() {
        return aUrl;
    }

    public void setaUrl(List<String> aUrl) {
        this.aUrl = aUrl;
    }

    public List<String> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<String> imgUrl) {
        this.imgUrl = imgUrl;
    }
}
