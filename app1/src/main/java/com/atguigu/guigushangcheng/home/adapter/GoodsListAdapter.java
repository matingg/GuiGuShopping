package com.atguigu.guigushangcheng.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.guigushangcheng.R;
import com.atguigu.guigushangcheng.home.bean.GoodsListBean;
import com.atguigu.guigushangcheng.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/3/6.
 */

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.MyViewHolder> {


    private final Context context;
    private final List<GoodsListBean.ResultBean.PageDataBean> datas;


    public GoodsListAdapter(Context context, GoodsListBean.ResultBean result) {
        this.context = context;
        datas = result.getPage_data();

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_goods_list, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        GoodsListBean.ResultBean.PageDataBean pageDataBean = datas.get(position);//根据位置得到数据
        Glide.with(context).load(Constants.BASE_URL_IMAGE + pageDataBean.getFigure()).into(holder.ivHot);
        holder.tvName.setText(pageDataBean.getName());
        holder.tvPrice.setText(pageDataBean.getCover_price());


    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_hot)
        ImageView ivHot;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_price)
        TextView tvPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null) {
                        onItemClickListener.setOnItemClickListener(datas.get(getLayoutPosition()));
                        Toast.makeText(context, "8520+++==="+getLayoutPosition(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }



    public interface OnItemClickListener {//设置item的点击事件的监听
        public void setOnItemClickListener(GoodsListBean.ResultBean.PageDataBean data);
    }

    private OnItemClickListener onItemClickListener ;

  //设置点名某条的监听
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}

