package com.esp.fly.models;

public class FragmentMap<String, Fragment> {

    private String name;
    private Fragment fragment;

    public FragmentMap(String name, Fragment fragment) {
        this.name = name;
        this.fragment = fragment;
    }

    public String getName() {
        return name;
    }

    public Fragment getFragment() {
        return fragment;
    }
}
