package com.atguigu.guigushangcheng.community.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.guigushangcheng.base.BaseFragment;

/**
 * Created by 麻少亭 on 2017/2/22.
 *
 *                   发现
 */

public class CommunityFragment extends BaseFragment {

    private TextView textView;

    @Override
    public View initView() {

        textView = new TextView(context);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;

    }


    @Override
    public void initData() {
        super.initData();
        textView.setText("发现");
        Log.e("TAG", "CommunityFragment initData()--发现");

    }
}
