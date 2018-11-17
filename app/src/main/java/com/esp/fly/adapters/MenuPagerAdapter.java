package com.esp.fly.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.esp.fly.R;
import com.esp.fly.fragments.GeneralFragment;
import com.esp.fly.fragments.UserFragment;
import com.esp.fly.models.FragmentMap;

import java.util.ArrayList;
import java.util.List;

public class MenuPagerAdapter extends FragmentPagerAdapter{

    private Context context;
    private List<FragmentMap<String, Fragment>> fragmentList;

    public MenuPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentMap<String, Fragment>(context.getString(R.string.general), new GeneralFragment()));
        fragmentList.add(new FragmentMap<String, Fragment>(context.getString(R.string.user), new UserFragment()));
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentList.get(position).getName();
    }
}
