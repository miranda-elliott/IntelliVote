package edu.wm.cs420.intellivote.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import edu.wm.cs420.intellivote.Fragments.CandidateFragment;
import edu.wm.cs420.intellivote.Fragments.CandidateListFragment;
import edu.wm.cs420.intellivote.Models.Candidate;

public class CandidateFragmentAdapter extends FragmentStatePagerAdapter {
    final int TAB_BIO = 0;
    final int TAB_ISSUES = 1;
    final int TAB_NEWS = 2;

    static Candidate candidate = null;

    // Constructor
    public CandidateFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public static CandidateFragmentAdapter newInstance(FragmentManager fragmentManager, Candidate givenCandidate) {
        candidate = givenCandidate;
        return new CandidateFragmentAdapter(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case TAB_BIO:
                return CandidateFragment.newInstance(candidate, TAB_BIO);
            case TAB_ISSUES:
                return CandidateFragment.newInstance(candidate, TAB_ISSUES);
            case TAB_NEWS:
                return CandidateFragment.newInstance(candidate, TAB_NEWS);
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
            case TAB_BIO:
                return "Bio";
            case TAB_ISSUES:
                return "Issues";
            case TAB_NEWS:
                return "News";
            default:
                return null;
        }
    }
}
