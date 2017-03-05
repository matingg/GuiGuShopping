package com.atguigu.guigushangcheng.user.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by 麻少亭 on 2017/3/5.
 * 重写ScrollView 的三个构造方法 重写onScrollChanged 通过接口把数据传出去
 */

public class GradationScrollView extends ScrollView {



    public GradationScrollView(Context context) {
        super(context);
    }


    public GradationScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GradationScrollView(Context context, AttributeSet attrs,
                               int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldx, int oldy) {
        super.onScrollChanged(l, t, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, l, t, oldx, oldy);
        }
    }


/*
* 接口
* */
    private ScrollViewListener scrollViewListener = null;

    public interface ScrollViewListener {

        void onScrollChanged(GradationScrollView scrollView, int x, int y,
                             int oldx, int oldy);

    }
    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

}
