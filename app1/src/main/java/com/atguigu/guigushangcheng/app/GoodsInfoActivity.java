package com.atguigu.guigushangcheng.app;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.guigushangcheng.R;
import com.atguigu.guigushangcheng.home.adapter.HomeAdapter;
import com.atguigu.guigushangcheng.home.bean.GoodsBean;
import com.atguigu.guigushangcheng.shopping.utils.CartStorage;
import com.atguigu.guigushangcheng.shopping.utils.VirtualkeyboardHeight;
import com.atguigu.guigushangcheng.utils.Constants;
import com.atguigu.guigushangcheng.view.AddSubView;
import com.bumptech.glide.Glide;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class GoodsInfoActivity extends AppCompatActivity {

    @InjectView(R.id.ib_good_info_back)
    ImageButton ibGoodInfoBack;
    @InjectView(R.id.ib_good_info_more)
    ImageButton ibGoodInfoMore;
    @InjectView(R.id.iv_good_info_image)
    ImageView ivGoodInfoImage;
    @InjectView(R.id.tv_good_info_name)
    TextView tvGoodInfoName;
    @InjectView(R.id.tv_good_info_desc)
    TextView tvGoodInfoDesc;
    @InjectView(R.id.tv_good_info_price)
    TextView tvGoodInfoPrice;
    @InjectView(R.id.tv_good_info_store)
    TextView tvGoodInfoStore;
    @InjectView(R.id.tv_good_info_style)
    TextView tvGoodInfoStyle;
    @InjectView(R.id.wb_good_info_more)
    WebView wbGoodInfoMore;
    @InjectView(R.id.progressbar)
    ProgressBar progressbar;
    @InjectView(R.id.tv_good_info_callcenter)
    TextView tvGoodInfoCallcenter;
    @InjectView(R.id.tv_good_info_collection)
    TextView tvGoodInfoCollection;
    @InjectView(R.id.tv_good_info_cart)
    TextView tvGoodInfoCart;
    @InjectView(R.id.btn_good_info_addcart)
    Button btnGoodInfoAddcart;
    @InjectView(R.id.ll_goods_root)
    LinearLayout llGoodsRoot;
    @InjectView(R.id.tv_more_share)
    TextView tvMoreShare;
    @InjectView(R.id.tv_more_search)
    TextView tvMoreSearch;
    @InjectView(R.id.tv_more_home)
    TextView tvMoreHome;
    @InjectView(R.id.btn_more)
    Button btnMore;
    @InjectView(R.id.ll_root)
    LinearLayout llRoot;
    private GoodsBean goodsBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        ButterKnife.inject(this);
        getData();

    }
    private void getData() {
        goodsBean = (GoodsBean) getIntent().getSerializableExtra(HomeAdapter.GOODS_BEAN);
//        Toast.makeText(this, ""+goodsBean.toString(), Toast.LENGTH_SHORT).show();
        setData();
    }

    private void setData() {

        //1.设置图片
        Glide.with(this).load(Constants.BASE_URL_IMAGE + goodsBean.getFigure()).into(ivGoodInfoImage);

        //2.设置名称和价格
        tvGoodInfoName.setText(goodsBean.getName());

        tvGoodInfoPrice.setText("￥" + goodsBean.getCover_price());


        //3.设置加载网页
        loadWeb("http://mp.weixin.qq.com/s/Cf3DrW2lnlb-w4wYaxOEZg");

    }

    private void loadWeb(String url) {
        WebSettings webSettings = wbGoodInfoMore.getSettings();

        webSettings.setJavaScriptEnabled(true);  //设置支持js

        webSettings.setBuiltInZoomControls(true); //设置添加缩放按钮

        webSettings.setUseWideViewPort(true);//设置双击单击

        wbGoodInfoMore.setWebViewClient(new WebViewClient() { //设置WebViewClient,如果没有设置，调起系统的浏览器打开新的连接
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressbar.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;
            }
        });
        wbGoodInfoMore.loadUrl(url);

    }

    @OnClick({R.id.ib_good_info_back, R.id.ib_good_info_more, R.id.wb_good_info_more, R.id.tv_good_info_callcenter, R.id.tv_good_info_collection, R.id.tv_good_info_cart, R.id.btn_good_info_addcart, R.id.tv_more_share, R.id.tv_more_search, R.id.tv_more_home, R.id.btn_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_good_info_back:
                Toast.makeText(GoodsInfoActivity.this, "fanhui", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.ib_good_info_more://更多
                Toast.makeText(GoodsInfoActivity.this, "//更多", Toast.LENGTH_SHORT).show();

                if (llRoot.isShown()) {
                    llRoot.setVisibility(View.GONE);
                } else {
                    llRoot.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.wb_good_info_more:
                Toast.makeText(GoodsInfoActivity.this, "", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_callcenter://客服中心
                Toast.makeText(GoodsInfoActivity.this, "//客服中心", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_collection://收藏
                Toast.makeText(GoodsInfoActivity.this, "//收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_cart://跳转到购物车
                Toast.makeText(GoodsInfoActivity.this, "//跳转到购物车", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_good_info_addcart://添加到购物车
                Toast.makeText(GoodsInfoActivity.this, "//添加到购物车", Toast.LENGTH_SHORT).show();
                showPopwindow();
                break;
            case R.id.tv_more_share:
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_more_search:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_more_home:
                Toast.makeText(this, "主页", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_more://消失更多页面
                llRoot.setVisibility(View.GONE);
                break;
        }
    }
    /**
     * 显示popupWindow
     */
    private void showPopwindow() {

        // 1 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popupwindow_add_product, null);

        // 2下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        final PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        // 3 参数设置
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
        window.setBackgroundDrawable(dw);

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);


        // 4 控件处理
        ImageView iv_goodinfo_photo = (ImageView) view.findViewById(R.id.iv_goodinfo_photo);
        TextView tv_goodinfo_name = (TextView) view.findViewById(R.id.tv_goodinfo_name);
        TextView tv_goodinfo_price = (TextView) view.findViewById(R.id.tv_goodinfo_price);
        AddSubView nas_goodinfo_num = (AddSubView) view.findViewById(R.id.nas_goodinfo_num);
        Button bt_goodinfo_cancel = (Button) view.findViewById(R.id.bt_goodinfo_cancel);
        Button bt_goodinfo_confim = (Button) view.findViewById(R.id.bt_goodinfo_confim);

        // 加载图片
        Glide.with(GoodsInfoActivity.this).load(Constants.BASE_URL_IMAGE + goodsBean.getFigure()).into(iv_goodinfo_photo);

        // 名称
        tv_goodinfo_name.setText(goodsBean.getName());
        // 显示价格
        tv_goodinfo_price.setText(goodsBean.getCover_price());

        // 设置最大值和当前值
        nas_goodinfo_num.setMaxValue(8);
        nas_goodinfo_num.setValue(1);


        nas_goodinfo_num.setOnNumberChangerListener(new AddSubView.OnNumberChangerListener() {
            @Override
            public void onNumberChanger(int value) {
                goodsBean.setNumber(value);
            }
        });

        bt_goodinfo_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        bt_goodinfo_confim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
                //添加购物车
                CartStorage.getInstance(GoodsInfoActivity.this).addData(goodsBean);
                Log.e("TAG", "66:" + goodsBean.toString());
                Toast.makeText(GoodsInfoActivity.this, "添加购物车成功", Toast.LENGTH_SHORT).show();
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                window.dismiss();
            }
        });

        // 5 在底部显示
        window.showAtLocation(GoodsInfoActivity.this.findViewById(R.id.ll_goods_root),
                Gravity.BOTTOM, 0, VirtualkeyboardHeight.getBottomStatusHeight(GoodsInfoActivity.this));

    }
}