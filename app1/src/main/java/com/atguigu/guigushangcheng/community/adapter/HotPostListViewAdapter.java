package com.atguigu.guigushangcheng.community.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.guigushangcheng.R;
import com.atguigu.guigushangcheng.community.bean.HotPostBean;
import com.atguigu.guigushangcheng.utils.Constants;
import com.atguigu.guigushangcheng.utils.DensityUtil;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/3/5.
 */

public class HotPostListViewAdapter extends BaseAdapter {

    private final Context context;
    private final List<HotPostBean.ResultBean> datas;

    public HotPostListViewAdapter(Context context, List<HotPostBean.ResultBean> result) {
        this.context = context;
        datas = result;
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
            convertView = View.inflate(context, R.layout.item_hotpost_listview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //根据位置得到对应的数据
        HotPostBean.ResultBean resultBean = datas.get(position);
        viewHolder.tvHotUsername.setText(resultBean.getUsername());
        SimpleDateFormat myFmt = new SimpleDateFormat("MM-dd HH:mm");

        viewHolder.tvHotAddtime.setText(myFmt.format(Integer.parseInt(resultBean.getAdd_time())));

        Glide.with(context).load(Constants.BASE_URL_IMAGE+resultBean.getAvatar()).into(viewHolder.ivNewPostAvatar);
        Glide.with(context).load(Constants.BASE_URL_IMAGE+resultBean.getFigure()).into(viewHolder.ivHotFigure);
        viewHolder.tvHotSaying.setText(resultBean.getSaying());
        viewHolder.tvHotLikes.setText(resultBean.getLikes());
        viewHolder.tvHotComments.setText(resultBean.getComments());


        //先把之前的一次，要在添加置顶之前移除之前的缓存
        viewHolder.llHotPost.removeAllViews();
        //置顶
        String isTop =  resultBean.getIs_top();//"1"，“0”
        if("1".equals(isTop)){

            LinearLayout.LayoutParams topParams = new LinearLayout.LayoutParams(-2,-2);

            TextView topTextView = new TextView(context);
            topTextView.setText("置顶");
            topTextView.setGravity(Gravity.CENTER);
            topTextView.setTextColor(Color.WHITE);
//            topTextView.setBackgroundColor(Color.RED);
            topTextView.setBackgroundResource(R.drawable.is_top_shape);

            topTextView.setPadding(DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5));

            topParams.setMargins(DensityUtil.dip2px(context, 8), 0, DensityUtil.dip2px(context, 5), 0);



            viewHolder.llHotPost.addView(topTextView,topParams);


        }

        //热门
        String isHot = resultBean.getIs_hot();
        if("1".equals(isHot)){
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            TextView textView = new TextView(context);
            textViewLp.setMargins(0, 0, DensityUtil.dip2px(context, 5), 0);
            textView.setText("热门");
            textView.setGravity(Gravity.CENTER);
            //文字为白色
            textView.setTextColor(Color.WHITE);
            //设置padding
            textView.setPadding(DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5));
            //设置橙色北京
            textView.setBackgroundResource(R.drawable.is_hot_shape);
            viewHolder.llHotPost.addView(textView, textViewLp);
        }


        //精华
        String is_essence = resultBean.getIs_essence();
        if ("1".equals(is_essence)) {
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //距离右边
            textViewLp.setMargins(0, 0, DensityUtil.dip2px(context, 5), 0);
            TextView textView = new TextView(context);
            textView.setText("精华");
            textView.setGravity(Gravity.CENTER);
            //文字白色
            textView.setTextColor(Color.WHITE);
            textView.setPadding(DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5));
            //设置背景为亮橙色
            textView.setBackgroundResource(R.drawable.is_essence_shape);
            viewHolder.llHotPost.addView(textView, textViewLp);
        }

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_hot_username)
        TextView tvHotUsername;
        @InjectView(R.id.tv_hot_addtime)
        TextView tvHotAddtime;
        @InjectView(R.id.rl)
        RelativeLayout rl;
        @InjectView(R.id.iv_new_post_avatar)
        ImageView ivNewPostAvatar;
        @InjectView(R.id.iv_hot_figure)
        ImageView ivHotFigure;
        @InjectView(R.id.ll_hot_post)
        LinearLayout llHotPost;
        @InjectView(R.id.tv_hot_saying)
        TextView tvHotSaying;
        @InjectView(R.id.tv_hot_likes)
        TextView tvHotLikes;
        @InjectView(R.id.tv_hot_comments)
        TextView tvHotComments;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
