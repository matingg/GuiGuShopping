package com.atguigu.guigushangcheng.community.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.atguigu.guigushangcheng.R;
import com.atguigu.guigushangcheng.base.BaseFragment;
import com.atguigu.guigushangcheng.community.adapter.CommunityViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by 麻少亭 on 2017/2/22.
 * <p>
 * 发现
 */

public class CommunityFragment extends BaseFragment {


    @InjectView(R.id.ib_community_icon)
    ImageButton ibCommunityIcon;
    @InjectView(R.id.ib_community_message)
    ImageButton ibCommunityMessage;
    @InjectView(R.id.tablayout)
    android.support.design.widget.TabLayout tablayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    private List<Fragment> fragmentList;
    private CommunityViewPagerAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_community, null);
        ButterKnife.inject(this, view);
        return view;

    }


    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "CommunityFragment initData()--发现");

        initFragment();

        adapter = new CommunityViewPagerAdapter(getFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        //在设置适配器之后
        tablayout.setupWithViewPager(viewPager);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }


    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new NewPostFragment());
        fragmentList.add(new HotPostFragment());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.ib_community_icon, R.id.ib_community_message})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_community_icon:
                break;
            case R.id.ib_community_message:
                break;
        }
    }
}



