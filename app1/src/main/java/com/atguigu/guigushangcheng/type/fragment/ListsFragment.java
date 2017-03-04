package com.atguigu.guigushangcheng.type.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.atguigu.guigushangcheng.R;
import com.atguigu.guigushangcheng.base.BaseFragment;
import com.atguigu.guigushangcheng.type.adapter.TypeLeftAdapter;
import com.atguigu.guigushangcheng.type.adapter.TypeRightAdapter;
import com.atguigu.guigushangcheng.type.bean.TypeBean;
import com.atguigu.guigushangcheng.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 麻少亭 on 2017/3/3.
 */

public class ListsFragment extends BaseFragment {


    @InjectView(R.id.lv_left)
    ListView lvLeft;
    @InjectView(R.id.rv_right)
    RecyclerView rvRight;
    //网络请求得到数据
    private String[] titles = new String[]{"小裙子", "上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品",
            "办公文具", "数码周边", "游戏专区"};

    //联网的url的集合
    private String[] urls = new String[]{Constants.SKIRT_URL, Constants.JACKET_URL, Constants.PANTS_URL, Constants.OVERCOAT_URL,
            Constants.ACCESSORY_URL, Constants.BAG_URL, Constants.DRESS_UP_URL, Constants.HOME_PRODUCTS_URL, Constants.STATIONERY_URL,
            Constants.DIGIT_URL, Constants.GAME_URL};

    private  TypeLeftAdapter adapter ;
    private TypeRightAdapter adapter2 ;


    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_list, null);
        ButterKnife.inject(this, view);

        return view;

    }


    @Override
    public void initData() {
        super.initData();

        adapter = new TypeLeftAdapter(context , titles);
        lvLeft.setAdapter(adapter);
        lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectPosition(position);//把点击位置传到适配器并刷新
                adapter.notifyDataSetChanged();

                //联网请求
                getDataFromNet(urls[position]);

            }
        });
        //联网请求
        getDataFromNet(urls[0]);//进来时默认选择0位置的数据 否则进去时不显示内容
    }

    private void getDataFromNet(String url) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG","联网失败了"+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG","裙子的数据联网成功了==");
                        processData(response);

                    }
                });
    }

    private void processData(String response) { //解析数据
        TypeBean typeBean = JSON.parseObject(response,TypeBean.class);
        List<TypeBean.ResultEntity> result = typeBean.getResult();
        if(result != null && result.size() >0 ){



            //设置RecyclerView的适配器
            adapter2 = new TypeRightAdapter(context,result);
            rvRight.setAdapter(adapter2);
            adapter.notifyDataSetChanged();

            //设置布局管理器
            GridLayoutManager manager = new GridLayoutManager(context,3);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(position ==0){
                        return 3;
                    }else{
                        return 1;
                    }
                }
            });
            rvRight.setLayoutManager(manager);

        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
