package com.atguigu.guigushangcheng;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.atguigu.guigushangcheng.base.BaseFragment;
import com.atguigu.guigushangcheng.community.fragment.CommunityFragment;
import com.atguigu.guigushangcheng.home.fragment.HomeFragment;
import com.atguigu.guigushangcheng.shopping.fragment.ShoppingFragment;
import com.atguigu.guigushangcheng.type.fragment.TypeFragment;
import com.atguigu.guigushangcheng.user.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    private int position;
    List<BaseFragment> fragmentList;
    private  Fragment tempFragment ;

    @InjectView(R.id.fl_main)
    FrameLayout flMain;
    @InjectView(R.id.rg_main)
    RadioGroup rgMain;
    @InjectView(R.id.activity_main)
    LinearLayout activityMain;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        //初始化fragment
        initFragment();

        //初始化RadioGroup的监听事件
        initListener();

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

            if(currentFragment != tempFragment) { //判断当前的和缓存的是不是一个 如果不是

                ft = getSupportFragmentManager().beginTransaction();//开启事物

                if( ! currentFragment.isAdded()  ) {//如果没有添加就添加

                    if(tempFragment != null) {//h缓存的隐藏

                        ft.hide(tempFragment);
                    }

                    ft.add(R.id.fl_main ,currentFragment );

                }else{
                    if(tempFragment != null) {//h缓存的隐藏

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
        fragmentList.add(new HomeFragment());
        fragmentList.add(new TypeFragment());
        fragmentList.add(new CommunityFragment());
        fragmentList.add(new ShoppingFragment());
        fragmentList.add(new UserFragment());


    }
}
