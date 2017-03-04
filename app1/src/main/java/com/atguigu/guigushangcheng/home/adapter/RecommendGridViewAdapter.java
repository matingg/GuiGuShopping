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

public class RecommendGridViewAdapter extends BaseAdapter {


    private final Context context;
    private final List<HomeBean.ResultBean.RecommendInfoBean> datas;

    public RecommendGridViewAdapter(Context context, List<HomeBean.ResultBean.RecommendInfoBean> recommend_info) {
        this.context = context;
        this.datas = recommend_info;
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

            convertView = View.inflate(context, R.layout.item_recommend_grid_view, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //根据位置得到对应的数据
        HomeBean.ResultBean.RecommendInfoBean infoEntity = datas.get(position);
        //绑定数据
        viewHolder.tvName.setText(infoEntity.getName());
        viewHolder.tvPrice.setText("￥"+infoEntity.getCover_price());
        Glide.with(context).load(Constants.BASE_URL_IMAGE+infoEntity.getFigure()).into(viewHolder.ivRecommend);


        return convertView;
    }



    static class ViewHolder {
        @InjectView(R.id.iv_recommend)
        ImageView ivRecommend;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
