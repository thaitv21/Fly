package com.esp.fly.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.esp.fly.R;
import com.esp.fly.fragments.GroupFragment;
import com.esp.fly.fragments.InboxFragment;
import com.esp.fly.models.FragmentMap;

import java.util.ArrayList;
import java.util.List;

public class HomePagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<FragmentMap<String, Fragment>> fragments;

    public HomePagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        fragments = new ArrayList<>();
        fragments.add(new FragmentMap<String, Fragment>(context.getString(R.string.inbox), new InboxFragment()));
        fragments.add(new FragmentMap<String, Fragment>(context.getString(R.string.contact), new InboxFragment()));
        fragments.add(new FragmentMap<String, Fragment>(context.getString(R.string.group), new GroupFragment()));
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getName();
    }
}
