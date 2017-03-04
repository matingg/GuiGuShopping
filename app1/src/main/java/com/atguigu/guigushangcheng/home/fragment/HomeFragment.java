package com.atguigu.guigushangcheng.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.guigushangcheng.R;
import com.atguigu.guigushangcheng.base.BaseFragment;
import com.atguigu.guigushangcheng.home.adapter.HomeAdapter;
import com.atguigu.guigushangcheng.home.bean.HomeBean;
import com.atguigu.guigushangcheng.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by 麻少亭 on 2017/2/22.
 * <p>
 * 首页
 */

public class HomeFragment extends BaseFragment {


    @InjectView(R.id.tv_search_home)
    TextView tvSearchHome;
    @InjectView(R.id.tv_message_home)
    TextView tvMessageHome;
    @InjectView(R.id.rv_home)
    RecyclerView rvHome;
    @InjectView(R.id.ib_top)
    ImageButton ibTop;
    private HomeBean.ResultBean result;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_home, null);
        ButterKnife.inject(this, view);
        return view;

    }


    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "CommunityFragment initData()--首页");
        getDataFromNet();//从网络获取数据

    }

    private void getDataFromNet() {//从网络获取数据

        OkHttpUtils .get()
                .url(Constants.HOME_URL)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {//请求数据失败
                        Log.e("TAG", "请求数据失败 HomeFragment onError()"+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {//请求数据成功
                        Log.e("TAG", "请求数据成功 HomeFragment onResponse()");
                            processData(response);// 处理数据

                    }
                });
    }

    private void processData(String response) {   //处理从网络请求来的数据
        if(TextUtils.isEmpty(response)) {
            return;//判断传过来的json数据是不是空
        }
        HomeBean homeBean = JSON.parseObject(response, HomeBean.class);//使用fastjson把 json对象封装到 HomeBean 对象中

        result = homeBean.getResult();

        Log.e("TAG", "*****"+ homeBean.getResult().getAct_info().get(0).getName());

        HomeAdapter adapter = new HomeAdapter( context ,result );

        rvHome.setAdapter(adapter);//RecyclerView设置适配器
            //设置布局管理器
        rvHome.setLayoutManager(new LinearLayoutManager(context ,LinearLayoutManager.VERTICAL, false));
    }


    @OnClick({R.id.tv_search_home, R.id.tv_message_home, R.id.ib_top})//顶部布局的监听 搜索 消息 扫一扫
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search_home:
                Toast.makeText(context , "搜索" ,Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_message_home:
                Toast.makeText(context , "消息" ,Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_top:
                Toast.makeText(context , "扫一扫" ,Toast.LENGTH_SHORT).show();
                break;
        }
    }










    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
