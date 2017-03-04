package com.atguigu.guigushangcheng.type.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.guigushangcheng.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/3/3.
 */

public class TypeLeftAdapter extends BaseAdapter {
    private final Context context;
    private final String[] datas;
    private int selectPosition;
    public TypeLeftAdapter(Context context, String[] titles) {
        this.context = context;
        this.datas = titles;
    }

    @Override
    public int getCount() {
        return datas.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_type, null);
            viewHolder =new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();

        }
        viewHolder.tvTitle.setText(datas[position]);
        if(selectPosition ==position){
            //设置选中或者高亮
            //选中项背景
            viewHolder.tvTitle.setTextColor(Color.parseColor("#fd3f3f"));
            convertView.setBackgroundResource(R.drawable.type_item_background_selector);
        }else{
            //设置默认
            viewHolder.tvTitle.setTextColor(Color.parseColor("#323437"));
            convertView.setBackgroundResource(R.drawable.bg2);  //其他项背景
        }


        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_title)
        TextView tvTitle;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
    public void setSelectPosition(int p){
        this.selectPosition = p ;
//        Toast.makeText(context, "selectPosition"+selectPosition, Toast.LENGTH_SHORT).show();
    }
}
