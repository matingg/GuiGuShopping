package com.atguigu.guigushangcheng.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atguigu.guigushangcheng.home.bean.HomeBean;
import com.atguigu.guigushangcheng.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 麻少亭 on 2017/2/25.
 */

public class ViewPagerAdapter extends PagerAdapter {


    private Context context;
    private List<HomeBean.ResultBean.ActInfoBean> actInfoBeen;

    public ViewPagerAdapter(Context context, List<HomeBean.ResultBean.ActInfoBean> act_info) {

        this.context = context;
        actInfoBeen = act_info;
    }

    @Override
    public int getCount() {
        return actInfoBeen.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);//图片拉伸
        Glide.with(context).load(Constants.BASE_URL_IMAGE + actInfoBeen.get(position).getIcon_url()).into(imageView);

        container.addView(imageView);//添加到viewPager容器中

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, position);
                }
            }
        });


        return imageView;
    }

    /*
    * item点击借口
    * */
    public interface OnItemClickListener { //定义一个借口
        public void onItemClick(View v, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
