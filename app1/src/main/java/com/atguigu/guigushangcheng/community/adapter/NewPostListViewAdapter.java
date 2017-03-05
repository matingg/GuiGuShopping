package com.atguigu.guigushangcheng.community.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.guigushangcheng.R;
import com.atguigu.guigushangcheng.community.bean.NewPostBean;
import com.atguigu.guigushangcheng.utils.Constants;
import com.atguigu.guigushangcheng.utils.Utils;
import com.bumptech.glide.Glide;
import com.opendanmaku.DanmakuItem;
import com.opendanmaku.DanmakuView;
import com.opendanmaku.IDanmakuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/3/5.
 */

public class NewPostListViewAdapter extends BaseAdapter {

    private Utils utils;
    private final Context context;
    private final List<NewPostBean.ResultBean> datas;

    public NewPostListViewAdapter(Context context, List<NewPostBean.ResultBean> result) {
        this.context = context;
        datas = result;
        utils = new Utils();
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_new_post, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        NewPostBean.ResultBean resultBean = datas.get(position);

        viewHolder.tvCommunityUsername.setText(resultBean.getUsername());
        //设置用户头像
        Glide.with(context).load(Constants.BASE_URL_IMAGE + resultBean.getAvatar()).into(viewHolder.ibNewPostAvatar);

        //时间
        viewHolder.tvCommunityAddtime.setText(utils.getTimeFromMillisecond(Long.parseLong(resultBean.getAdd_time())) + "小时");
        //设置大图片
        Glide.with(context).load(Constants.BASE_URL_IMAGE + resultBean.getFigure()).into(viewHolder.ivCommunityFigure);

        viewHolder.tvCommunitySaying.setText(resultBean.getSaying());

        viewHolder.tvCommunityLikes.setText(resultBean.getLikes());


        viewHolder.tvCommunityComments.setText(resultBean.getComments());

        List<String> strings = resultBean.getComment_list();

        if (strings != null && strings.size() > 0) {
            List<IDanmakuItem> list = initItems(strings, viewHolder.danmakuView);
            //把顺序打乱
            Collections.shuffle(list);
            //添加弹幕数据
            viewHolder.danmakuView.addItem(list, true);

            viewHolder.danmakuView.setVisibility(View.VISIBLE);
            //显示弹幕
            viewHolder.danmakuView.show();
        } else {
            viewHolder.danmakuView.hide();
            viewHolder.danmakuView.setVisibility(View.GONE);
        }


        return convertView;
    }

    // 构造弹幕
    private List<IDanmakuItem> initItems(List<String> strings, DanmakuView danmakuView) {
        List<IDanmakuItem> list = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            IDanmakuItem item = new DanmakuItem(context, strings.get(i), danmakuView.getWidth());
            list.add(item);
        }
        return list;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_community_username)
        TextView tvCommunityUsername;
        @InjectView(R.id.tv_community_addtime)
        TextView tvCommunityAddtime;
        @InjectView(R.id.rl)
        RelativeLayout rl;
        @InjectView(R.id.ib_new_post_avatar)
        ImageButton ibNewPostAvatar;
        @InjectView(R.id.iv_community_figure)
        ImageView ivCommunityFigure;
        @InjectView(R.id.danmakuView)
        DanmakuView danmakuView;
        @InjectView(R.id.tv_community_saying)
        TextView tvCommunitySaying;
        @InjectView(R.id.tv_community_likes)
        TextView tvCommunityLikes;
        @InjectView(R.id.tv_community_comments)
        TextView tvCommunityComments;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}

