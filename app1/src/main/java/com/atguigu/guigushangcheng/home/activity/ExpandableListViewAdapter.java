package com.atguigu.guigushangcheng.home.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.guigushangcheng.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 麻少亭 on 2017/3/6.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {


    private Context context;
    private List<String> group;
    private List<List<String>> child;
    private int childP;
    private int groupP;


    public ExpandableListViewAdapter(Context context, ArrayList<String> group, ArrayList<List<String>> child) {
        this.context = context;
        this.group = group;
        this.child = child;
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(childPosition).get(childPosition);
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.group_list_item, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.textView);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(group.get(groupPosition));
        //  holder.textView.setTextSize(20);
        holder.textView.setPadding(0, 10, 0, 10);
        if (isExpanded) {
            holder.imageView.setImageResource(R.drawable.filter_list_selected);
        } else {
            holder.imageView.setImageResource(R.drawable.filter_list_unselected);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.child_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
            viewHolder.childImageView = (ImageView) convertView.findViewById(R.id.childImageView);
            viewHolder.ll_child_root = (LinearLayout) convertView.findViewById(R.id.ll_child_root);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (groupPosition != 0) {
            viewHolder.textView.setText(child.get(groupPosition).get(childPosition));
        }

        if (childP == childPosition && groupP == groupPosition) {
            viewHolder.childImageView.setVisibility(View.VISIBLE);
        } else {
            viewHolder.childImageView.setVisibility(View.GONE);
        }

        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        childP = childPosition;
        groupP = groupPosition;
        return true;
    }

    class ViewHolder {
        TextView textView;
        ImageView imageView;
        ImageView childImageView;
        LinearLayout ll_child_root;
    }
}
