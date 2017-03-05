package com.atguigu.guigushangcheng.community.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.atguigu.guigushangcheng.R;
import com.atguigu.guigushangcheng.base.BaseFragment;
import com.atguigu.guigushangcheng.community.adapter.NewPostListViewAdapter;
import com.atguigu.guigushangcheng.community.bean.NewPostBean;
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

public class NewPostFragment extends BaseFragment {


    @InjectView(R.id.lv_new_post)
    ListView lvNewPost;
    private List<NewPostBean.ResultBean> result;
    private NewPostListViewAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_new_post, null);
        ButterKnife.inject(this, view);
        return view;

    }


    @Override
    public void initData() {
        super.initData();

        //联网请求shuju
        getDataFromNet(Constants.NEW_POST_URL);

    }

    private void getDataFromNet(String url) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "NewPostFragment联网失败" + e.getMessage());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "NewPostFragment联网成功" + response);
                        processData(response);

                    }


                });

    }

    private void processData(String json) {
        NewPostBean newPostBean = JSON.parseObject(json, NewPostBean.class);
        result = newPostBean.getResult();
        if (result != null && result.size() > 0) {
            //设置适配器
            adapter = new NewPostListViewAdapter(context, result);
            lvNewPost.setAdapter(adapter);
        }
    }
        @Override
        public void onDestroyView () {
            super.onDestroyView();
            ButterKnife.reset(this);
        }


}
