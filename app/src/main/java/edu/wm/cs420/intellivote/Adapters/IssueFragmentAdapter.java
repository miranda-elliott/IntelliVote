package edu.wm.cs420.intellivote.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import edu.wm.cs420.intellivote.Fragments.CandidateFragment;
import edu.wm.cs420.intellivote.Fragments.IssueFragment;
import edu.wm.cs420.intellivote.Models.Candidate;
import edu.wm.cs420.intellivote.Models.Issue;

public class IssueFragmentAdapter extends FragmentStatePagerAdapter {
    final int TAB_HISTORY = 0;
    final int TAB_CANDIDATES = 1;
    final int TAB_NEWS = 2;

    static Issue issue = null;

    // Constructor
    public IssueFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public static IssueFragmentAdapter newInstance(FragmentManager fragmentManager, Issue givenIssue) {
        issue = givenIssue;
        return new IssueFragmentAdapter(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case TAB_HISTORY:
                return IssueFragment.newInstance(issue, TAB_HISTORY);
            case TAB_CANDIDATES:
                return IssueFragment.newInstance(issue, TAB_CANDIDATES);
            case TAB_NEWS:
                return IssueFragment.newInstance(issue, TAB_NEWS);
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
