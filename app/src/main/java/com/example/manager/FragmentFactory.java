package com.example.manager;

import android.support.v4.app.Fragment;

import com.example.fragment.CategoryFragment;
import com.example.fragment.CircleAttenFragment;
import com.example.fragment.CircleFragment;
import com.example.fragment.CircleHotFragment;
import com.example.fragment.CircleTopicFragment;
import com.example.fragment.HomePagerFragment;
import com.example.fragment.MineFragment;

import java.util.HashMap;


/**
 * Created by lenovo on 2017/1/12.
 */

public class FragmentFactory {

    public static HashMap<String, Fragment> fragmentMap = new HashMap<>();
    private static Fragment fragment;

    public static Fragment getFragment(String sign) {

        fragment = fragmentMap.get(sign);

        if (fragment != null) {
            return fragment;
        } else if (sign.equals("HomePagerFragment")) {
            fragment = new HomePagerFragment();
        } else if (sign.equals("CategoryFragment")) {
            fragment = new CategoryFragment();
        } else if (sign.equals("CircleFragment")) {
            fragment = new CircleFragment();
        } else if (sign.equals("MineFragment")) {
            fragment = new MineFragment();
        }else if (sign.equals("话题")) {
            fragment = new CircleTopicFragment();
        }else if (sign.equals("热门")) {
            fragment = new CircleHotFragment();
        }else if (sign.equals("关注")) {
            fragment = new CircleAttenFragment();
        }


        fragmentMap.put(sign, fragment);

        return fragment;
    }

}
