package edu.wm.cs420.intellivote.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import edu.wm.cs420.intellivote.Fragments.CandidateListFragment;
import edu.wm.cs420.intellivote.Fragments.IssueListFragment;

public class IssueListFragmentAdapter extends FragmentStatePagerAdapter {
    final int TAB_ALL = 0;
    final int TAB_FAVORITES = 1;

    // Constructor
    public IssueListFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public static IssueListFragmentAdapter newInstance(FragmentManager fragmentManager) {
        return new IssueListFragmentAdapter(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case TAB_ALL:
                return IssueListFragment.newInstance(TAB_ALL);
            case TAB_FAVORITES:
                return IssueListFragment.newInstance(TAB_FAVORITES);
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
            case TAB_ALL:
                return "All";
            case TAB_FAVORITES:
                return "Favorites";
            default:
                return null;
        }
    }
}
