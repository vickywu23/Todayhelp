package com.together.todayhelp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.together.todayhelp.ui.fragment.CampusFragment;
import com.together.todayhelp.ui.fragment.CityFragment;
import com.together.todayhelp.ui.fragment.CommunityFragment;

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private static final String[] CONTENT = new String[] { "人人快递", "校园帮", "社区帮" };
    private Fragment[] fragments = new Fragment[] { new CityFragment(), new CampusFragment(), new CommunityFragment() };

    public MainViewPagerAdapter (FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return CONTENT[position % CONTENT.length].toUpperCase();
    }

    @Override
    public int getCount() {
        return CONTENT.length;
    }
}
