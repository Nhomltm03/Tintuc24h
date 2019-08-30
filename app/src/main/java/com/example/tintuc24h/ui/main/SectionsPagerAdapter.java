package com.example.tintuc24h.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tintuc24h.MainActivity;
import com.example.tintuc24h.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;
    private NewsFragment mNewsFragment;
    private WeatherFragment mWeatherFragment;


    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        mNewsFragment = new NewsFragment();
        mWeatherFragment = new WeatherFragment();
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        if(position == 0 ){
            return  mNewsFragment;
        } else if (position == 1 ){
            return  mWeatherFragment;
        } else {
            //ToDO Something
        }
        return  null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

}