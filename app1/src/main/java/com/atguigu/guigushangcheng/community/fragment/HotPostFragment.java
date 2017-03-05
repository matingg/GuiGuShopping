package com.atguigu.guigushangcheng.community.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.atguigu.guigushangcheng.R;
import com.atguigu.guigushangcheng.base.BaseFragment;
import com.atguigu.guigushangcheng.community.adapter.HotPostListViewAdapter;
import com.atguigu.guigushangcheng.community.bean.HotPostBean;
import com.atguigu.guigushangcheng.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 麻少亭 on 2017/3/4.
 */

public class HotPostFragment extends BaseFragment {


    @InjectView(R.id.lv_hot_post)
    ListView lvHotPost;
    private List<HotPostBean.ResultBean> result;
    private HotPostListViewAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_hot_post, null);
        ButterKnife.inject(this, view);
        return view;

    }


    @Override
    public void initData() {
        super.initData();
        getDataFromNet(Constants.HOT_POST_URL);
    }

    private void getDataFromNet(String hotPostUrl) {
        OkHttpUtils
                .get()
                .url(hotPostUrl)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "HotPostFragment联网失败" + e.getMessage());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "HotPostFragment联网成功" );
                        processData(response);

                    }


                });
    }

    private void processData(String response) {
        HotPostBean hotPostBean = JSON.parseObject(response,HotPostBean.class);
        result = hotPostBean.getResult();

        //设置适配器
        if(result != null && result.size() >0){
            adapter = new HotPostListViewAdapter(context, result);
            lvHotPost.setAdapter(adapter);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}

