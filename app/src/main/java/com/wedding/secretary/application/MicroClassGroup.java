package com.wedding.secretary.application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hmy on 2015/11/26.
 */
public class MicroClassGroup {

    private int microClasseGroupImage;//图片

    private String microClasseGroupTitle;//一级标题

    private String microClasseGroupDesc;//描述

    private List<MicroClass> microClasseGroup;

    public int getMicroClasseGroupImage() {
        return microClasseGroupImage;
    }

    public void setMicroClasseGroupImage(int microClasseGroupImage) {
        this.microClasseGroupImage = microClasseGroupImage;
    }

    public String getMicroClasseGroupTitle() {
        return microClasseGroupTitle;
    }

    public void setMicroClasseGroupTitle(String microClasseGroupTitle) {
        this.microClasseGroupTitle = microClasseGroupTitle;
    }

    public String getMicroClasseGroupDesc() {
        return microClasseGroupDesc;
    }

    public void setMicroClasseGroupDesc(String microClasseGroupDesc) {
        this.microClasseGroupDesc = microClasseGroupDesc;
    }

    public List<MicroClass> getMicroClasseGroup() {
        if (microClasseGroup == null) {
            microClasseGroup = new ArrayList<MicroClass>();
        }
        return microClasseGroup;
    }

    public void setMicroClasseGroup(List<MicroClass> microClasseGroup) {
        this.microClasseGroup = microClasseGroup;
    }
}
