package com.together.todayhelp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Window;

import com.together.todayhelp.R;
import com.together.todayhelp.ui.adapter.MainViewPagerAdapter;
import com.viewpagerindicator.TabPageIndicator;

public class MainActivity extends FragmentActivity {
    private FragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.common_title);
        initView();
    }

    private void initView() {
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);
        indicator.setOnPageChangeListener(new ViewPageOnPageChangeListener());
    }

    class ViewPageOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int page) {
//            viewPagerCurrentPage = page;
//            if (page == 0) {
//                menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//            } else {
//                menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
//            }
        }
    }

}
