package com.wedding.secretary.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wedding.secretary.R;
import com.wedding.secretary.application.MicroClassData;

/**
 * Created by hmy on 2015/11/26.
 */
public class ExpandableListViexAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ViewHolderGroup viewHolderGroup;
    private ViewHolderChild viewHolderChild;
    String lv2Title = "";

    private static final int LEFT = 0;
    private static final int RIGHT = 1;

    public ExpandableListViexAdapter() {
    }

    public ExpandableListViexAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return MicroClassData.microClassGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return MicroClassData.microClassGroups.get(groupPosition).getMicroClasseGroup().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return MicroClassData.microClassGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return MicroClassData.microClassGroups.get(groupPosition).getMicroClasseGroup().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            viewHolderGroup = new ViewHolderGroup();
            convertView = View.inflate(context, R.layout.item_microclass_group, null);
            viewHolderGroup.iv_microclass_group_image = (ImageView) convertView.findViewById(R.id.iv_microclass_group_image);
            viewHolderGroup.tv_microclass_group_title = (TextView) convertView.findViewById(R.id.tv_microclass_group_title);
            viewHolderGroup.tv_microclass_group_desc = (TextView) convertView.findViewById(R.id.tv_microclass_group_desc);
            viewHolderGroup.tv_microclass_group_stepNum = (TextView) convertView.findViewById(R.id.tv_microclass_group_stepNum);
            convertView.setTag(viewHolderGroup);
        } else {
            viewHolderGroup = (ViewHolderGroup) convertView.getTag();
        }
        viewHolderGroup.iv_microclass_group_image.setImageResource(MicroClassData.microClassGroups.get(groupPosition).getMicroClasseGroupImage());
        viewHolderGroup.tv_microclass_group_title.setText(MicroClassData.microClassGroups.get(groupPosition).getMicroClasseGroupTitle());
        viewHolderGroup.tv_microclass_group_desc.setText(MicroClassData.microClassGroups.get(groupPosition).getMicroClasseGroupDesc());
        viewHolderGroup.tv_microclass_group_stepNum.setText(" STEP " + (groupPosition + 1));
        return convertView;
    }

    //左右布局开关
    private int toggle = LEFT;
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = View.inflate(context, R.layout.item_microclass_child, null);
            viewHolderChild = new ViewHolderChild();
            viewHolderChild.leftView = convertView.findViewById(R.id.item_microclass_child_left);
            viewHolderChild.rightView = convertView.findViewById(R.id.item_microclass_child_right);
            //new left 填充  title赋值

            viewHolderChild.tv_microclass_left_lv2Title = (TextView) convertView.findViewById(R.id.tv_microclass_left_lv2Title);
            viewHolderChild.tv_microclass_left_lv3Title = (TextView) convertView.findViewById(R.id.tv_microclass_left_lv3Title);
            viewHolderChild.tv_microclass_left_articleSource = (TextView) convertView.findViewById(R.id.tv_microclass_left_articleSource);
            viewHolderChild.iv_microclass_left_articleImage = (ImageView) convertView.findViewById(R.id.iv_microclass_left_articleImage);
            viewHolderChild.tv_microclass_right_lv2Title = (TextView) convertView.findViewById(R.id.tv_microclass_right_lv2Title);
            viewHolderChild.tv_microclass_right_lv3Title = (TextView) convertView.findViewById(R.id.tv_microclass_right_lv3Title);
            viewHolderChild.tv_microclass_right_articleSource = (TextView) convertView.findViewById(R.id.tv_microclass_right_articleSource);
            viewHolderChild.iv_microclass_right_articleImage = (ImageView) convertView.findViewById(R.id.iv_microclass_right_articleImage);
            convertView.setTag(viewHolderChild);

        } else {

            viewHolderChild = (ViewHolderChild) convertView.getTag();
        }
            viewHolderChild.leftView.setVisibility(View.GONE);
            viewHolderChild.rightView.setVisibility(View.GONE);
            //toggle + title
            //起始布局在左
            if(childPosition == 0) {

                //toggle = LEFT;
                for(int i=0; i<MicroClassData.microClassGroups.get(groupPosition).getMicroClasseGroup().size(); i++) {
                    if(i==0) {
                        MicroClassData.microClassGroups.get(groupPosition).getMicroClasseGroup().get(childPosition).toggle = LEFT;
                        lv2Title = MicroClassData.microClassGroups.get(groupPosition).getMicroClasseGroup().get(childPosition).getLv2Title();
                        
                    } else {

                    }
                }
            }
            //1   toggle左 title相同  -- 布局左 toggle左
            //4   toggle右   title不同  -- 左   toggle左
            if(toggle == LEFT && lv2Title.equals(MicroClassData.microClassGroups.get(groupPosition).getMicroClasseGroup().get(childPosition).getLv2Title()) ||
                    toggle == RIGHT && !lv2Title.equals(MicroClassData.microClassGroups.get(groupPosition).getMicroClasseGroup().get(childPosition).getLv2Title())) {
                toggle = LEFT;
                viewHolderChild.rightView.setVisibility(View.GONE);
                viewHolderChild.leftView.setVisibility(View.VISIBLE);
                viewHolderChild.tv_microclass_left_lv2Title.setText(MicroClassData.microClassGroups.get(groupPosition).getMicroClasseGroup().get(childPosition).getLv2Title());
                viewHolderChild.tv_microclass_left_lv3Title.setText(MicroClassData.microClassGroups.get(groupPosition).getMicroClasseGroup().get(childPosition).getLv3Title());
                viewHolderChild.tv_microclass_left_articleSource.setText(MicroClassData.microClassGroups.get(groupPosition).getMicroClasseGroup().get(childPosition).getArticleSource());
                viewHolderChild.iv_microclass_left_articleImage.setImageResource(MicroClassData.microClassGroups.get(groupPosition).getMicroClasseGroup().get(childPosition).getArticleImage());
            }
            //2   toggle左  title不同 -- 右     toggle右
            //3   toggle右  title相同  -- 右    toggle右
            if(toggle == LEFT && !lv2Title.equals(MicroClassData.microClassGroups.get(groupPosition).getMicroClasseGroup().get(childPosition).getLv2Title()) ||
                    (toggle == RIGHT && lv2Title.equals(MicroClassData.microClassGroups.get(groupPosition).getMicroClasseGroup().get(childPosition).getLv2Title()))) {
                toggle = RIGHT;
                viewHolderChild.rightView.setVisibility(View.VISIBLE);
                viewHolderChild.leftView.setVisibility(View.GONE);
                viewHolderChild.tv_microclass_right_lv2Title.setText(MicroClassData.microClassGroups.get(groupPosition).getMicroClasseGroup().get(childPosition).getLv2Title());
                viewHolderChild.tv_microclass_right_lv3Title.setText(MicroClassData.microClassGroups.get(groupPosition).getMicroClasseGroup().get(childPosition).getLv3Title());
                viewHolderChild.tv_microclass_right_articleSource.setText(MicroClassData.microClassGroups.get(groupPosition).getMicroClasseGroup().get(childPosition).getArticleSource());
                viewHolderChild.iv_microclass_right_articleImage.setImageResource(MicroClassData.microClassGroups.get(groupPosition).getMicroClasseGroup().get(childPosition).getArticleImage());
            }


        lv2Title = MicroClassData.microClassGroups.get(groupPosition).getMicroClasseGroup().get(childPosition).getLv2Title();

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ViewHolderGroup {
        private ImageView iv_microclass_group_image;
        private TextView tv_microclass_group_title;
        private TextView tv_microclass_group_desc;
        private TextView tv_microclass_group_stepNum;
    }

    class ViewHolderChild {

        View leftView;
        View rightView;

        //left
        private TextView tv_microclass_left_lv2Title;
        private TextView tv_microclass_left_lv3Title;
        private TextView tv_microclass_left_articleSource;
        private ImageView iv_microclass_left_articleImage;

        //right
        private TextView tv_microclass_right_lv2Title;
        private TextView tv_microclass_right_lv3Title;
        private TextView tv_microclass_right_articleSource;
        private ImageView iv_microclass_right_articleImage;
    }

}
