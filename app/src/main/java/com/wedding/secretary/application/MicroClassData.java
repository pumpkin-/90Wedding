package com.wedding.secretary.application;

import com.wedding.secretary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hmy on 2015/11/26.
 */
public class MicroClassData {

    public static List<MicroClassGroup> microClassGroups = new ArrayList<MicroClassGroup>();

    static {
        //模块1
        MicroClassGroup microClassGroup1 = new MicroClassGroup();
        microClassGroup1.setMicroClasseGroupImage(R.mipmap.step1);
        microClassGroup1.setMicroClasseGroupTitle("前期筹备");
        microClassGroup1.setMicroClasseGroupDesc("描述");
        microClassGroup1.getMicroClasseGroup().add(new MicroClass("", R.mipmap.ic_launcher, "婚礼小秘书", "前期筹备", "双方协商", "双方父母见面商议结婚事宜"));
        microClassGroup1.getMicroClasseGroup().add(new MicroClass("", R.mipmap.ic_launcher, "婚礼小秘书", "前期筹备", "双方协商", "确定结婚日期和地点"));
        microClassGroup1.getMicroClasseGroup().add(new MicroClass("", R.mipmap.ic_launcher, "婚礼小秘书", "前期筹备", "双方协商", "商议婚礼开支分配"));
        microClassGroup1.getMicroClasseGroup().add(new MicroClass("", R.mipmap.ic_launcher, "婚礼小秘书", "前期筹备", "此刻定情", "办理登记手续"));
        microClassGroup1.getMicroClasseGroup().add(new MicroClass("", R.mipmap.ic_launcher, "婚礼小秘书", "前期筹备", "此刻定情", "选购对戒婚饰"));
        microClassGroups.add(microClassGroup1);

        //模块2
        MicroClassGroup microClassGroup2 = new MicroClassGroup();
        microClassGroup2.setMicroClasseGroupImage(R.mipmap.step1);
        microClassGroup2.setMicroClasseGroupTitle("服务协商");
        microClassGroup2.setMicroClasseGroupDesc("描述");
        microClassGroup2.getMicroClasseGroup().add(new MicroClass("", R.mipmap.ic_launcher, "婚礼小秘书", "服务协商", "第三方确定", "选定婚庆公司"));
        microClassGroup2.getMicroClasseGroup().add(new MicroClass("", R.mipmap.ic_launcher, "婚礼小秘书", "服务协商", "第三方确定", "确定司仪与沟通"));
        microClassGroup2.getMicroClasseGroup().add(new MicroClass("", R.mipmap.ic_launcher, "婚礼小秘书", "服务协商", "第三方确定", "确定摄像师—价格与服务"));
        microClassGroup2.getMicroClasseGroup().add(new MicroClass("", R.mipmap.ic_launcher, "婚礼小秘书", "服务协商", "第三方确定", "确定跟拍师—价格与服务"));
        microClassGroup2.getMicroClasseGroup().add(new MicroClass("", R.mipmap.ic_launcher, "婚礼小秘书", "服务协商", "第三方确定", "实地查看婚礼布置和服务"));
        microClassGroups.add(microClassGroup2);

        //模块3
        MicroClassGroup microClassGroup3 = new MicroClassGroup();
        microClassGroup3.setMicroClasseGroupImage(R.mipmap.step1);
        microClassGroup3.setMicroClasseGroupTitle("婚礼倒计时");
        microClassGroup3.setMicroClasseGroupDesc("描述");
        microClassGroup3.getMicroClasseGroup().add(new MicroClass("", R.mipmap.ic_launcher, "婚礼小秘书", "婚礼倒计时", "当日活动确认", "准备发言稿"));
        microClassGroup3.getMicroClasseGroup().add(new MicroClass("", R.mipmap.ic_launcher, "婚礼小秘书", "婚礼倒计时", "当日活动确认", "选定当日礼服"));
        microClassGroup3.getMicroClasseGroup().add(new MicroClass("", R.mipmap.ic_launcher, "婚礼小秘书", "婚礼倒计时", "当日活动确认", "选定亲友礼服"));
        microClassGroup3.getMicroClasseGroup().add(new MicroClass("", R.mipmap.ic_launcher, "婚礼小秘书", "婚礼倒计时", "细节买办", "购买婚礼当日消耗物品"));
        microClassGroup3.getMicroClasseGroup().add(new MicroClass("", R.mipmap.ic_launcher, "婚礼小秘书", "婚礼倒计时", "细节买办", "双喜字、红包、喜帖、烟酒、喜糖、鞭炮、随手礼"));
        microClassGroups.add(microClassGroup3);
    }
}
