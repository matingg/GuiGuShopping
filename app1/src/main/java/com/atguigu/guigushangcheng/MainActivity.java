package com.atguigu.guigushangcheng;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.atguigu.guigushangcheng.app.GoodsInfoActivity;
import com.atguigu.guigushangcheng.base.BaseFragment;
import com.atguigu.guigushangcheng.community.fragment.CommunityFragment;
import com.atguigu.guigushangcheng.home.bean.GoodsBean;
import com.atguigu.guigushangcheng.home.bean.HomeBean;
import com.atguigu.guigushangcheng.home.fragment.HomeFragment;
import com.atguigu.guigushangcheng.shopping.fragment.ShoppingFragment;
import com.atguigu.guigushangcheng.type.fragment.TypeFragment;
import com.atguigu.guigushangcheng.user.fragment.UserFragment;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.atguigu.guigushangcheng.home.adapter.HomeAdapter.GOODS_BEAN;

public class MainActivity extends AppCompatActivity {

    private int position;
    List<BaseFragment> fragmentList;
    private Fragment tempFragment;

    @InjectView(R.id.fl_main)
    FrameLayout flMain;
    @InjectView(R.id.rg_main)
    RadioGroup rgMain;
    @InjectView(R.id.activity_main)
    LinearLayout activityMain;
    private FragmentTransaction ft;
    
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        //初始化二维码工具类
//        ZXingLibrary.initDisplayOpinion(this);
     //   ZXingLibrary.initDisplayOpinion(this);
        //初始化fragment
        initFragment();

        //初始化RadioGroup的监听事件
        initListener();

    }
    @Override
// 通过 onActivityResult的方法获取 扫描回来的 值
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult != null) {
            if(intentResult.getContents() == null) {
                Toast.makeText(this,"内容为空",Toast.LENGTH_LONG).show();
                Log.e("TAG", "MainActivity onActivityResult()-----------");
            } else {
                Toast.makeText(this,"扫描成功",Toast.LENGTH_LONG).show();
                Log.e("TAG", "MainActivity onActivityResult()");
                // ScanResult 为 获取到的字符串
                String ScanResult = intentResult.getContents();

                HomeBean.ResultBean result = homeFragment.getResult();
                List<HomeBean.ResultBean.RecommendInfoBean> recommend_info = result.getRecommend_info();

                HomeBean.ResultBean.RecommendInfoBean recommendInfoBean = null;
                for(int i = 0;i < recommend_info.size();i++){
                    if(recommend_info.get(i).getProduct_id().equals(ScanResult)) {
                        recommendInfoBean  = recommend_info.get(i);
                    }
                }
                
                if(recommendInfoBean != null) {

                    //商品新的的Bean对象
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setProduct_id(recommendInfoBean.getProduct_id());
                    goodsBean.setCover_price(recommendInfoBean.getCover_price());
                    goodsBean.setFigure(recommendInfoBean.getFigure());
                    goodsBean.setName(recommendInfoBean.getName());
                    Intent intent = new Intent(this, GoodsInfoActivity.class);
                    intent.putExtra(GOODS_BEAN, goodsBean);
                    startActivity(intent);
                }


            }
        } else {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_type:
                        position = 1;
                        break;
                    case R.id.rb_community:
                        position = 2;
                        break;
                    case R.id.rb_chrt:
                        position = 3;
                        break;
                    case R.id.rb_user:
                        position = 4;
                        break;
                }

                // 根据位置切换到对应得位置   记录当前位置
                Fragment currentFragment = fragmentList.get(position);

                //q切换
                switchFragment(currentFragment);


            }

        });

        rgMain.check(R.id.rb_home);//默认选择首页 


    }

    private void switchFragment(Fragment currentFragment) {

        if (currentFragment != tempFragment) { //判断当前的和缓存的是不是一个 如果不是

            ft = getSupportFragmentManager().beginTransaction();//开启事物

            if (!currentFragment.isAdded()) {//如果没有添加就添加

                if (tempFragment != null) {//h缓存的隐藏

                    ft.hide(tempFragment);
                }

                ft.add(R.id.fl_main, currentFragment);

            } else {
                if (tempFragment != null) {//h缓存的隐藏

                    ft.hide(tempFragment);
                }

                ft.show(currentFragment);

            }

            ft.commit();//提交事物
            tempFragment = currentFragment; //把当前的Fragment 赋值给缓存的

        }


    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        homeFragment = new HomeFragment();
        fragmentList.add(homeFragment);
        fragmentList.add(new TypeFragment());
        fragmentList.add(new CommunityFragment());
        fragmentList.add(new ShoppingFragment());
        fragmentList.add(new UserFragment());


    }



}
