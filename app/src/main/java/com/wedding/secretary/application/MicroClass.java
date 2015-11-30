package com.wedding.secretary.application;

/**
 * Created by hmy on 2015/11/26.
 */
public class MicroClass {

    //文章路径
    private String path;
    //一级标题（1—婚前筹备，2—服务协商，3—婚礼倒计时）
    private String lv1Title;
    //二级标题（1—双方协商，2—此刻定情，3—婚宴相关，4—婚照拍摄，5—第三方确定，6—当日活动确认，7—细节买办，8—后期计划）
    private String lv2Title;
    //文章标题
    private String lv3Title;
    //文章来源
    private String articleSource;
    //文章图片
    private int articleImage;
    //左右展示
    public int toggle;
    public MicroClass() {

    }

    public MicroClass(String path, int articleImage, String articleSource, String lv1Title, String lv2Title, String lv3Title) {
        this.path = path;
        this.articleImage = articleImage;
        this.lv1Title = lv1Title;
        this.lv2Title = lv2Title;
        this.lv3Title = lv3Title;
        this.articleSource = articleSource;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLv1Title() {
        return lv1Title;
    }

    public void setLv1Title(String lv1Title) {
        this.lv1Title = lv1Title;
    }

    public String getLv2Title() {
        return lv2Title;
    }

    public void setLv2Title(String lv2Title) {
        this.lv2Title = lv2Title;
    }

    public String getLv3Title() {
        return lv3Title;
    }

    public void setLv3Title(String lv3Title) {
        this.lv3Title = lv3Title;
    }

    public String getArticleSource() {
        return articleSource;
    }

    public void setArticleSource(String articleSource) {
        this.articleSource = articleSource;
    }

    public int getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(int articleImage) {
        this.articleImage = articleImage;
    }
}
