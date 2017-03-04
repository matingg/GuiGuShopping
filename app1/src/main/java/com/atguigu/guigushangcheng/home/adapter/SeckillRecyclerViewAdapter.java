package com.atguigu.guigushangcheng.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.guigushangcheng.R;
import com.atguigu.guigushangcheng.home.bean.HomeBean;
import com.atguigu.guigushangcheng.utils.Constants;
import com.bumptech.glide.Glide;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/2/27.
 */

public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.ViewHolder> {


    private final Context context;
    private final HomeBean.ResultBean.SeckillInfoBean datas;
    private final LayoutInflater inflater;
    private HomeBean.ResultBean.SeckillInfoBean.ListBean listBean;

    public SeckillRecyclerViewAdapter(Context context, HomeBean.ResultBean.SeckillInfoBean seckill_info) {
        this.context = context;
        datas = seckill_info;
        Log.e("TAG", "-------------------------------SeckillRecyclerViewAdapter SeckillRecyclerViewAdapter()"+seckill_info);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_seckill, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        listBean = datas.getList().get(position);
        Glide.with(context).load(Constants.BASE_URL_IMAGE + listBean.getFigure()).into(holder.ivFigure);

        holder.tvCoverPrice.setText(listBean.getCover_price());
        holder.tvOriginPrice.setText(listBean.getOrigin_price());


    }

    @Override
    public int getItemCount() {
        return datas.getList().size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_figure)
        ImageView ivFigure;
        @InjectView(R.id.tv_cover_price)
        TextView tvCoverPrice;
        @InjectView(R.id.tv_origin_price)
        TextView tvOriginPrice;
        @InjectView(R.id.ll_root)
        LinearLayout llRoot;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null) {
                        onItemClickListener.onItemClick(v ,getLayoutPosition() );
                    }
                }
            });

        }
    }

    public interface OnItemClickListener{
        void onItemClick(View v , int position);
    }
    public OnItemClickListener onItemClickListener ;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener  = onItemClickListener ;
    }


}
