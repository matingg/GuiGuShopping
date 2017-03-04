package com.atguigu.guigushangcheng.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.guigushangcheng.R;
import com.atguigu.guigushangcheng.home.bean.HomeBean;
import com.atguigu.guigushangcheng.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/3/2.
 */

public class HotGridViewAdapter extends BaseAdapter {


    private final Context context;
    private final List<HomeBean.ResultBean.HotInfoBean> datas;

    public HotGridViewAdapter(Context context, List<HomeBean.ResultBean.HotInfoBean> hot_info) {
        this.context = context;
        this.datas = hot_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_hot_grid_view, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //根据位置得到数据
        HomeBean.ResultBean.HotInfoBean hotInfoBean = datas.get(position);

        viewHolder.tvName.setText(hotInfoBean.getName());
        viewHolder.tvPrice.setText("￥"+hotInfoBean.getCover_price());
        Glide.with(context).load(Constants.BASE_URL_IMAGE+hotInfoBean.getFigure()).into(viewHolder.ivHot);


        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_hot)
        ImageView ivHot;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }



}
