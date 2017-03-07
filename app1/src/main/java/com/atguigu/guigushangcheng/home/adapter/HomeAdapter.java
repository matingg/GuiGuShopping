package com.atguigu.guigushangcheng.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.guigushangcheng.R;
import com.atguigu.guigushangcheng.app.GoodsInfoActivity;
import com.atguigu.guigushangcheng.home.activity.GoodsListActivity;
import com.atguigu.guigushangcheng.home.bean.GoodsBean;
import com.atguigu.guigushangcheng.home.bean.HomeBean;
import com.atguigu.guigushangcheng.home.view.MyGridView;
import com.atguigu.guigushangcheng.utils.Constants;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.AccordionTransformer;
import com.youth.banner.transformer.RotateDownTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.iwgang.countdownview.CountdownView;

/**
 * Created by 麻少亭 on 2017/2/23.
 */

public class HomeAdapter extends RecyclerView.Adapter {

    /**
     * 六种类型
     */
    /**
     * 横幅广告
     */
    public static final int BANNER = 0;
    /**
     * 频道
     */
    public static final int CHANNEL = 1;

    /**
     * 活动
     */
    public static final int ACT = 2;

    /**
     * 秒杀
     */
    public static final int SECKILL = 3;
    /**
     * 推荐
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;
    private final LayoutInflater inflater;


    /**
     * 当前类型
     */
    public int currentType = BANNER;
    public static final String GOODS_BEAN = "goodsBean";

    private final Context context;
    private final HomeBean.ResultBean result;


    public HomeAdapter(Context context, HomeBean.ResultBean result) {//从 HomeFragment 传过来的数据

        this.context = context;
        this.result = result;

        inflater = LayoutInflater.from(context);

    }


    @Override
    public int getItemViewType(int position) {


        if (position == BANNER) {  // 横幅
            currentType = BANNER;

        } else if (position == CHANNEL) {  //频道
            currentType = CHANNEL;
        } else if (position == ACT) {  //活动
            currentType = ACT;
        } else if (position == SECKILL) {  // 秒杀
            currentType = SECKILL;
        } else if (position == RECOMMEND) {  //推荐
            currentType = RECOMMEND;
        } else if (position == HOT) {  //热卖
            currentType = HOT;
        }
        return currentType; //当前类型
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {//绑定ViewHolder
        if (getItemViewType(position) == BANNER) {  // 横幅
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            //设置数据Banner的数据
            bannerViewHolder.setData(result.getBanner_info());

        } else if (getItemViewType(position) == CHANNEL) {  //频道

            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(result.getChannel_info());

        } else if (getItemViewType(position) == ACT) {  //活动

            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(result.getAct_info());


        } else if (getItemViewType(position) == SECKILL) {  // 秒杀

            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(context, result.getSeckill_info());

        } else if (getItemViewType(position) == RECOMMEND) {  //推荐

            RecommendViewHolder rec = (RecommendViewHolder) holder;
            rec.setDatas(context, result.getRecommend_info());

        } else if (getItemViewType(position) == HOT) {  //热卖
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(context, result.getHot_info());


        }

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == BANNER) {  // 横幅

            return new BannerViewHolder(context, inflater.inflate(R.layout.banner_viewpager, null));

        } else if (viewType == CHANNEL) {  //频道

            return new ChannelViewHolder(context, inflater.inflate(R.layout.channel_item, null));

        } else if (viewType == ACT) {  //活动

            return new ActViewHolder(context, inflater.inflate(R.layout.act_item, null));

        } else if (viewType == SECKILL) {  // 秒杀

            return new SeckillViewHolder(context, inflater.inflate(R.layout.seckillview_item, null));

        } else if (viewType == RECOMMEND) {  //推荐

            return new RecommendViewHolder(context, inflater.inflate(R.layout.recommend_item, null));

        } else if (viewType == HOT) {  //热卖

            return new HotViewHolder(context, inflater.inflate(R.layout.hot_item, null));

        }


        return null;


    }

    class HotViewHolder extends RecyclerView.ViewHolder {//热卖
        @InjectView(R.id.tv_more_hot)
        TextView tvMoreHot;
        @InjectView(R.id.gv_hot)
        MyGridView gvHot;

        private Context context;

        HotGridViewAdapter adapter;

        public HotViewHolder(Context context, View inflate) {
            super(inflate);
            gvHot = (MyGridView) inflate.findViewById(R.id.gv_hot);
            this.context = context;
        }

        public void setData(final Context context, List<HomeBean.ResultBean.HotInfoBean> hot_info) {
            adapter = new HotGridViewAdapter(context, hot_info);
            gvHot.setAdapter(adapter);

//            热卖的点击事件
            gvHot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "beidianjikkkk==" + position, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {//推荐
        private Context context;
        private GridView gridView;
        RecommendGridViewAdapter adapter;

        public RecommendViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            gridView = (GridView) itemView.findViewById(R.id.gv_recommend);


        }

        public void setDatas(final Context context, List<HomeBean.ResultBean.RecommendInfoBean> recommend_info) {
            adapter = new RecommendGridViewAdapter(context, recommend_info);
            gridView.setAdapter(adapter);
//            推荐的点击事件
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "被电击了" + position, Toast.LENGTH_SHORT).show();


                }
            });
        }
    }

    // -------------------------------------------------秒杀--------------------------
    private boolean isFrist = false;

    class SeckillViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        @InjectView(R.id.countdownview)
        CountdownView countdownview;
        @InjectView(R.id.tv_more_seckill)
        TextView tvMoreSeckill;
        @InjectView(R.id.rv_seckill)
        RecyclerView rvSeckill;
        private HomeBean.ResultBean.SeckillInfoBean seckillInfoBean;
        SeckillRecyclerViewAdapter adapter;
        Handler mHandler = new Handler();

        public SeckillViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.inject(this, itemView);

        }

        void startRefreshTime() {//刷新时间
            mHandler.postDelayed(mRefreshTimeRunnable, 1);

        }

        Runnable mRefreshTimeRunnable = new Runnable() {
            @Override
            public void run() {

                long currentTime = System.currentTimeMillis();//得到当前时间

                if (currentTime >= Long.parseLong(seckillInfoBean.getEnd_time())) {

                    mHandler.removeCallbacksAndMessages(null);// 倒计时结束
                } else {
                    //更新时间
                    countdownview.updateShow(Long.parseLong(seckillInfoBean.getEnd_time()) - currentTime);

                    mHandler.postDelayed(mRefreshTimeRunnable, 1000); //每隔1000毫秒更新一次
                }

            }
        };

        public void setData(final Context context, HomeBean.ResultBean.SeckillInfoBean seckill_info) {
            seckillInfoBean = seckill_info;
            adapter = new SeckillRecyclerViewAdapter(context, seckillInfoBean);
            rvSeckill.setAdapter(adapter);//設置适配器     设置布局管理器
            rvSeckill.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            adapter.setOnItemClickListener(new SeckillRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Toast.makeText(context, "beidianji ==" + position, Toast.LENGTH_SHORT).show();
                }
            });
            if (!isFrist) {
                isFrist = true;
                //计算倒计时持续的时间
                long totalTime = Long.parseLong(seckillInfoBean.getEnd_time()) - Long.parseLong(seckillInfoBean.getStart_time());

                // 校对倒计时
                long curTime = System.currentTimeMillis();
                //重新设置结束数据时间
                seckillInfoBean.setEnd_time((curTime + totalTime + ""));

                //开始刷新
                startRefreshTime();


            }

        }

    }

    //-----------------------------活动---------------------------------------------------------
    class ActViewHolder extends RecyclerView.ViewHolder {//活动
        @InjectView(R.id.act_viewpager)
        ViewPager actViewpager;
        private ViewPagerAdapter adapter;

        public ActViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);


        }

        public void setData(List<HomeBean.ResultBean.ActInfoBean> act_info) {

            adapter = new ViewPagerAdapter(context, act_info);

            actViewpager.setPageMargin(20);//设置page间间距
            actViewpager.setOffscreenPageLimit(3);
            actViewpager.setAdapter(adapter);
            actViewpager.setPageTransformer(true, new RotateDownTransformer());

            //设置点击事件  通过借口回调
            adapter.setOnItemClickListener(new ViewPagerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Toast.makeText(context, "position==" + position, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;

        GridView gvChannel;
        ChannelAdapter adapter;

        public ChannelViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            gvChannel = (GridView) itemView.findViewById(R.id.gv_channel);//初始化视图


            gvChannel.setOnItemClickListener(new MyOnItemClickListener());//给GridViewGridView的item设置监听


        }


        class MyOnItemClickListener implements AdapterView.OnItemClickListener {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
//跳转至商品详情页面  并把点击的位置传出去 用来寻找网路地址
                Intent intent = new Intent(context, GoodsListActivity.class);
                intent.putExtra("position", position);
                context.startActivity(intent);


            }
        }

        //设置数据
        public void setData(List<HomeBean.ResultBean.ChannelInfoBean> channel_info) {


            //设置适配器.已经有数据
            adapter = new ChannelAdapter(context, channel_info);
            gvChannel.setAdapter(adapter);

        }

    }

    @Override
    public int getItemCount() {
        return 6;   //从 1 到 六 慢慢的加上来的  不显示的时候注意修改


    }


    class BannerViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.banner)
        Banner banner;
        private final Context context;


        public BannerViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            banner = (Banner) itemView.findViewById(R.id.banner);

        }

        public void setData(final List<HomeBean.ResultBean.BannerInfoBean> banner_info) {

            //准备图片集合
            List<String> imageUrls = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                imageUrls.add(Constants.BASE_URL_IMAGE + banner_info.get(i).getImage());
            }
            //简单使用
            banner.setImages(imageUrls)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Glide.with(context)
                                    .load(path)
                                    .crossFade()
                                    .into(imageView);
                        }
                    })
                    .start();
            //设置动画效果-手风琴效果
            banner.setBannerAnimation(AccordionTransformer.class);

            //设置点击事件
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(context, "realPostion==" + position, Toast.LENGTH_SHORT).show();
                    int realPostion = position;
                    if (realPostion < banner_info.size()) {
                        String product_id = "";
                        String name = "";
                        String cover_price = "";
                        String image = "";
                        if (realPostion == 0) {
                            product_id = "627";
                            cover_price = "32.00";
                            name = "剑三T恤批发";
                        } else if (realPostion == 1) {
                            product_id = "21";
                            cover_price = "8.00";
                            name = "同人原创】剑网3 剑侠情缘叁 Q版成男 口袋胸针";
                        } else {
                            product_id = "1341";
                            cover_price = "50.00";
                            name = "【蓝诺】《天下吾双》 剑网3同人本";
                        }

                        image = banner_info.get(position).getImage();

                        GoodsBean goodsBean = new GoodsBean();
                        goodsBean.setProduct_id(product_id);
                        goodsBean.setName(name);
                        goodsBean.setCover_price(cover_price);
                        goodsBean.setFigure(image);

                        Intent intent = new Intent(context, GoodsInfoActivity.class);
                        intent.putExtra(GOODS_BEAN, goodsBean);
                        context.startActivity(intent);
                    }
                }
            });


        }

    }


}




