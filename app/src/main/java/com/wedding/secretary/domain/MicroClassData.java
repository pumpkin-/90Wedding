package com.wedding.secretary.domain;

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
        microClassGroup1.setMicroClasseGroupImage(R.mipmap.group1);
        microClassGroup1.setMicroClasseGroupTitle("前期筹备");
        microClassGroup1.setMicroClasseGroupDesc("包括双方父母见面、婚礼时间地点确定、婚礼费用开支、结婚登记、婚纱摄影、婚戒婚品购买、酒店酒席预订等婚前筹备事宜。");
        microClassGroup1.getMicroClasseGroup().add(new MicroClassChild(1, R.mipmap.group1_child1, "婚礼小秘书", "前期筹备", "双方协商", "1-1", "双方父母见面商议结婚事宜"));
        microClassGroup1.getMicroClasseGroup().add(new MicroClassChild(2, R.mipmap.group1_child2, "婚礼小秘书", "前期筹备", "双方协商", "1-1", "确定结婚日期和地点"));
        microClassGroup1.getMicroClasseGroup().add(new MicroClassChild(3, R.mipmap.group1_child3, "婚礼小秘书", "前期筹备", "双方协商", "1-1", "商议婚礼开支分配"));
        microClassGroup1.getMicroClasseGroup().add(new MicroClassChild(4, R.mipmap.group1_child4, "婚礼小秘书", "前期筹备", "此刻定情", "1-2", "办理登记手续"));
        microClassGroup1.getMicroClasseGroup().add(new MicroClassChild(5, R.mipmap.group1_child5, "婚礼小秘书", "前期筹备", "此刻定情", "1-2", "选购对戒婚饰"));
        microClassGroup1.getMicroClasseGroup().add(new MicroClassChild(6, R.mipmap.group1_child6, "婚礼小秘书", "前期筹备", "婚宴相关", "1-3", "选定婚宴酒店"));
        microClassGroup1.getMicroClasseGroup().add(new MicroClassChild(7, R.mipmap.group1_child7, "婚礼小秘书", "前期筹备", "婚宴相关", "1-3", "确定酒席桌数与菜单"));
        microClassGroup1.getMicroClasseGroup().add(new MicroClassChild(8, R.mipmap.group1_child8, "婚礼小秘书", "前期筹备", "婚宴相关", "1-3", "制定婚宴座位安排表"));
        microClassGroup1.getMicroClasseGroup().add(new MicroClassChild(9, R.mipmap.group1_child9, "婚礼小秘书", "前期筹备", "婚照拍摄", "1-4", "确定婚照商家"));
        microClassGroup1.getMicroClasseGroup().add(new MicroClassChild(10, R.mipmap.group1_child10, "婚礼小秘书", "前期筹备", "婚照拍摄", "1-4", "挑选婚照服装"));
        microClassGroup1.getMicroClasseGroup().add(new MicroClassChild(11, R.mipmap.group1_child11, "婚礼小秘书", "前期筹备", "婚照拍摄", "1-4", "婚照准备—保养与美发"));
        microClassGroup1.getMicroClasseGroup().add(new MicroClassChild(12, R.mipmap.group1_child12, "婚礼小秘书", "前期筹备", "婚照拍摄", "1-4", "婚照拍摄"));
        microClassGroup1.getMicroClasseGroup().add(new MicroClassChild(13, R.mipmap.group1_child13, "婚礼小秘书", "前期筹备", "婚照拍摄", "1-4", "婚照定稿结集"));
        microClassGroups.add(microClassGroup1);

        //模块2
        MicroClassGroup microClassGroup2 = new MicroClassGroup();
        microClassGroup2.setMicroClasseGroupImage(R.mipmap.group2);
        microClassGroup2.setMicroClasseGroupTitle("服务协商");
        microClassGroup2.setMicroClasseGroupDesc("第三方服务团队挑选及协商。包括如何选择婚庆公司、司仪、摄像、摄影、化妆师、现场布置、婚车服务等。");
        microClassGroup2.getMicroClasseGroup().add(new MicroClassChild(14, R.mipmap.group2_child1, "婚礼小秘书", "服务协商", "第三方确定", "2-1", "选定婚庆公司"));
        microClassGroup2.getMicroClasseGroup().add(new MicroClassChild(15, R.mipmap.group2_child2, "婚礼小秘书", "服务协商", "第三方确定", "2-1", "确定司仪与沟通"));
        microClassGroup2.getMicroClasseGroup().add(new MicroClassChild(16, R.mipmap.group2_child3, "婚礼小秘书", "服务协商", "第三方确定", "2-1", "确定摄像师—价格与服务"));
        microClassGroup2.getMicroClasseGroup().add(new MicroClassChild(17, R.mipmap.group2_child4, "婚礼小秘书", "服务协商", "第三方确定", "2-1", "确定跟拍师—价格与服务"));
        microClassGroup2.getMicroClasseGroup().add(new MicroClassChild(18, R.mipmap.group2_child5, "婚礼小秘书", "服务协商", "第三方确定", "2-1", "实地查看婚礼布置和服务"));
        microClassGroup2.getMicroClasseGroup().add(new MicroClassChild(19, R.mipmap.group2_child6, "婚礼小秘书", "服务协商", "第三方确定", "2-1", "预约化妆师、试妆定造型"));
        microClassGroup2.getMicroClasseGroup().add(new MicroClassChild(20, R.mipmap.group2_child7, "婚礼小秘书", "服务协商", "第三方确定", "2-1", "选定婚车和路线安排"));
        microClassGroups.add(microClassGroup2);

        //模块3
        MicroClassGroup microClassGroup3 = new MicroClassGroup();
        microClassGroup3.setMicroClasseGroupImage(R.mipmap.group3);
        microClassGroup3.setMicroClasseGroupTitle("婚礼倒计时");
        microClassGroup3.setMicroClasseGroupDesc("描述");
        microClassGroup3.getMicroClasseGroup().add(new MicroClassChild(21, R.mipmap.group3_child1, "婚礼小秘书", "婚礼倒计时", "当日活动确认", "3-1","准备发言稿"));
        microClassGroup3.getMicroClasseGroup().add(new MicroClassChild(22, R.mipmap.group3_child2, "婚礼小秘书", "婚礼倒计时", "当日活动确认", "3-1","选定当日礼服"));
        microClassGroup3.getMicroClasseGroup().add(new MicroClassChild(23, R.mipmap.group3_child3, "婚礼小秘书", "婚礼倒计时", "当日活动确认", "3-1","选定亲友礼服（伴郎伴娘、父母、花童）"));
        microClassGroup3.getMicroClasseGroup().add(new MicroClassChild(24, R.mipmap.group3_child4, "婚礼小秘书", "婚礼倒计时", "当日活动确认","3-1", "确认参加婚礼名单"));
        microClassGroup3.getMicroClasseGroup().add(new MicroClassChild(25, R.mipmap.group3_child5, "婚礼小秘书", "婚礼倒计时", "当日活动确认","3-1", "确认需寄送喜糖者名单"));
        microClassGroup3.getMicroClasseGroup().add(new MicroClassChild(26, R.mipmap.group3_child6, "婚礼小秘书", "婚礼倒计时", "当日活动确认","3-1", "布置新房"));
        microClassGroup3.getMicroClasseGroup().add(new MicroClassChild(27, R.mipmap.group3_child8, "婚礼小秘书", "婚礼倒计时", "细节买办", "3-2","购买婚礼当日消耗物品(双喜字、红包、喜帖、烟酒、喜糖、鞭炮、随手礼)"));
        microClassGroup3.getMicroClasseGroup().add(new MicroClassChild(28, R.mipmap.group3_child9, "婚礼小秘书", "婚礼倒计时", "细节买办", "3-2","准备崭新钞票和红包"));
        microClassGroup3.getMicroClasseGroup().add(new MicroClassChild(29, R.mipmap.group3_child10, "婚礼小秘书", "婚礼倒计时", "细节买办", "3-2","预定鲜花和装饰品"));
        microClassGroup3.getMicroClasseGroup().add(new MicroClassChild(30, R.mipmap.group3_child11, "婚礼小秘书", "婚礼倒计时", "细节买办", "3-2","预定蛋糕和甜点"));
        microClassGroup3.getMicroClasseGroup().add(new MicroClassChild(31, R.mipmap.group3_child12, "婚礼小秘书", "婚礼倒计时", "后期计划", "3-3","安排蜜月时间与行程"));
        microClassGroup3.getMicroClasseGroup().add(new MicroClassChild(32, R.mipmap.group3_child13, "婚礼小秘书", "婚礼倒计时", "后期计划", "3-3","预定蜜月酒店、车票"));
        microClassGroups.add(microClassGroup3);
    }
}
