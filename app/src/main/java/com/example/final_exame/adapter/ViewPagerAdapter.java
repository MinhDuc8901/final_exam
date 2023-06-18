package com.example.final_exame.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.final_exame.fragment.ClassFragment;
import com.example.final_exame.fragment.HomeFrag;
import com.example.final_exame.fragment.StudentFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFrag();
            case 1:
                return new StudentFragment();
            case 2:
                return new ClassFragment();
            default:
                return new HomeFrag();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
