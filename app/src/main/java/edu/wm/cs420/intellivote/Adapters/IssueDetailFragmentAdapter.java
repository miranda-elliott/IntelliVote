package edu.wm.cs420.intellivote.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import edu.wm.cs420.intellivote.Fragments.IssueDetailCandidatesFragment;
import edu.wm.cs420.intellivote.Fragments.IssueDetailHistoryFragment;
import edu.wm.cs420.intellivote.Fragments.IssueDetailNewsFragment;
import edu.wm.cs420.intellivote.Models.Issue;

public class IssueDetailFragmentAdapter extends FragmentStatePagerAdapter {
    final int TAB_HISTORY = 0;
    final int TAB_CANDIDATES = 1;
    final int TAB_NEWS = 2;

    static Issue issue = null;

    // Constructor
    public IssueDetailFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public static IssueDetailFragmentAdapter newInstance(FragmentManager fragmentManager, Issue givenIssue) {
        issue = givenIssue;
        return new IssueDetailFragmentAdapter(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case TAB_HISTORY:
                return IssueDetailHistoryFragment.newInstance(issue);
            case TAB_CANDIDATES:
                return IssueDetailCandidatesFragment.newInstance(issue);
            case TAB_NEWS:
                return IssueDetailNewsFragment.newInstance(issue);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case TAB_HISTORY:
                return "History";
            case TAB_CANDIDATES:
                return "Candidates";
            case TAB_NEWS:
                return "News";
            default:
                return null;
        }
    }
}
