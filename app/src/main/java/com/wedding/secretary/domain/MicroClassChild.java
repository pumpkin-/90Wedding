package com.wedding.secretary.domain;

/**
 * Created by hmy on 2015/11/26.
 */
public class MicroClassChild {

    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    //id
    private int id;
    //一级标题（1—婚前筹备，2—服务协商，3—婚礼倒计时）
    private String lv1Title;
    //二级标题
    private String lv2Title;
    //二级步骤
    private String lv2Step;
    //文章标题
    private String lv3Title;
    //文章来源
    private String articleSource;
    //文章图片
    private int articleImage;
    //左右展示
    public int toggle;

    public MicroClassChild() {

    }

    public MicroClassChild(int id, int articleImage, String articleSource, String lv1Title, String lv2Title, String lv2Step, String lv3Title) {
        this.id = id;
        this.articleImage = articleImage;
        this.lv1Title = lv1Title;
        this.lv2Title = lv2Title;
        this.lv2Step = lv2Step;
        this.lv3Title = lv3Title;
        this.articleSource = articleSource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLv2Step() {
        return lv2Step;
    }

    public void setLv2Step(String lv2Step) {
        this.lv2Step = lv2Step;
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
