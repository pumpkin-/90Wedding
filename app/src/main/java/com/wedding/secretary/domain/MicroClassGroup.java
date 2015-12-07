package com.wedding.secretary.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hmy on 2015/11/26.
 */
public class MicroClassGroup {

    private int microClasseGroupImage;//图片

    private String microClasseGroupTitle;//一级标题

    private String microClasseGroupDesc;//描述

    private List<MicroClassChild> microClasseGroup;

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

    public List<MicroClassChild> getMicroClasseGroup() {
        if (microClasseGroup == null) {
            microClasseGroup = new ArrayList<MicroClassChild>();
        }
        return microClasseGroup;
    }

    public void setMicroClasseGroup(List<MicroClassChild> microClasseGroup) {
        this.microClasseGroup = microClasseGroup;
    }
}
