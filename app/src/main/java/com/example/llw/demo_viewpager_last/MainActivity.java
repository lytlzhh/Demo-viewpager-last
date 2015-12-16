package com.example.llw.demo_viewpager_last;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager viewpagerId;
    private LinearLayout liearId;
    private TextView tvImageDescription;
    private LinearLayout llPoints;

    private List<ImageView> imagelist;

    private void assignViews() {
        viewpagerId = (ViewPager) findViewById(R.id.viewpager_id);
        liearId = (LinearLayout) findViewById(R.id.liear_id);
        tvImageDescription = (TextView) findViewById(R.id.tv_image_description);
        llPoints = (LinearLayout) findViewById(R.id.ll_points);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        init();
        viewpagerId.setAdapter(new Myviewpageradapter());
        viewpagerId.setOnPageChangeListener(this);
    }

    private int[] imageids() {
        return new int[]{
                R.mipmap.f,
                R.mipmap.g,
                R.mipmap.h,
                R.mipmap.j,
                R.mipmap.songjia,
                R.mipmap.uu,
                R.mipmap.yy
        };

    }

    private void init() {
        imagelist = new ArrayList<ImageView>();
        int[] myimages = imageids();
        for (int i = 0; i < imageids().length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(myimages[i]);
            imagelist.add(imageView);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //设置要显示的view资源必须要大于1才合理
        if (imagelist.size() > 1) {
            //当滑到下标为0 的时候，跳转到最后的view
            if (position < 1) {
                position = imageids().length;
                viewpagerId.setCurrentItem(position, false);//跳转
            } else if (position > imageids().length) {
                //当滑到下标的最大值时，跳转到第一个位置
                position = 1;
                viewpagerId.setCurrentItem(1, false);//跳转
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    private class Myviewpageradapter extends PagerAdapter {

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imagelist.get(position));
        }

        @Override
        public int getCount() {
            return imagelist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imagelist.get(position));
            return imagelist.get(position);
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
