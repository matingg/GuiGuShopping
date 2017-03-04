package com.atguigu.guigushangcheng.type.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.atguigu.guigushangcheng.MainActivity;
import com.atguigu.guigushangcheng.R;
import com.atguigu.guigushangcheng.base.BaseFragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/2/22.
 * <p>
 * 分类
 */

public class TypeFragment extends BaseFragment {

    @InjectView(R.id.tl_1)
    SegmentTabLayout tl1;
    @InjectView(R.id.iv_type_search)
    ImageView ivTypeSearch;
    @InjectView(R.id.fl_type)
    FrameLayout flType;
    List<Fragment> listFragment;
    String[] title = {"分类", "标签"};
    private Fragment tempFragment;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_type, null);
        ButterKnife.inject(this, view);
        return view;

    }


    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "ShoppingFragment initData()-- 分类");
        tl1.setTabData(title);
        initFragment();//初始化Fragment
        initListener();//初始化监听事件 分类 标签
        switchFragment(listFragment.get(0));
    }

    private void initListener() {
        tl1.setTabData(title);
        tl1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                Toast.makeText(context, "position==" + position, Toast.LENGTH_SHORT).show();
                switchFragment(listFragment.get(position));

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void switchFragment(Fragment currentFragment) {
        //切换的不是同一个页面
        if (tempFragment != currentFragment) {

            MainActivity activity = (MainActivity) context;
            //得到FragmentMager
            FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
            //如果没有添加就添加
            if (!currentFragment.isAdded()) {
                //缓存的隐藏
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }

                //添加
                ft.add(R.id.fl_type, currentFragment);

            } else {
                //缓存的隐藏
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }

                //显示
                ft.show(currentFragment);
            }

            //事务提交
            ft.commit();


            //把当前的赋值成缓存的
            tempFragment = currentFragment;

        }

    }


    private void initFragment() {
        listFragment = new ArrayList<>();
        listFragment.add(new ListsFragment());
        listFragment.add(new TagFragment());


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
