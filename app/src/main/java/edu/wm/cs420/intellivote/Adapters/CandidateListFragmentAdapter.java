package edu.wm.cs420.intellivote.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import edu.wm.cs420.intellivote.Fragments.CandidateListFragment;

public class CandidateListFragmentAdapter extends FragmentStatePagerAdapter {
    final int TAB_NATIONAL = 0;
    final int TAB_FAVORITES = 1;

    // Constructor
    public CandidateListFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public static CandidateListFragmentAdapter newInstance(FragmentManager fragmentManager) {
        return new CandidateListFragmentAdapter(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case TAB_NATIONAL:
                return CandidateListFragment.newInstance(TAB_NATIONAL);
            case TAB_FAVORITES:
                return CandidateListFragment.newInstance(TAB_FAVORITES);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case TAB_NATIONAL:
                return "National";
            case TAB_FAVORITES:
                return "Favorites";
            default:
                return null;
        }
    }
}
