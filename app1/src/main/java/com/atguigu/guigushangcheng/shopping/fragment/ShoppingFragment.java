package com.atguigu.guigushangcheng.shopping.fragment;

import android.util.Log;
import android.view.View;

import com.atguigu.guigushangcheng.R;
import com.atguigu.guigushangcheng.base.BaseFragment;

/**
 * Created by 麻少亭 on 2017/2/22.
 *
 *          购物车
 */

public class ShoppingFragment extends BaseFragment {

    @Override
    public View initView() {
        View view =View.inflate(context , R.layout.fragment_shopping_cart , null);
        return  view;

    }


    @Override
    public void initData() {
        super.initData();

        Log.e("TAG", "ShoppingFragment initData()-- 购物车");
    }
}
