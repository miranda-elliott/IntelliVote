package edu.wm.cs420.intellivote.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import edu.wm.cs420.intellivote.Fragments.CandidateDetailBioFragment;
import edu.wm.cs420.intellivote.Fragments.CandidateDetailIssuesFragment;
import edu.wm.cs420.intellivote.Fragments.CandidateDetailNewsFragment;
import edu.wm.cs420.intellivote.Models.Candidate;

public class CandidateDetailFragmentAdapter extends FragmentStatePagerAdapter {
    public final int TAB_BIO = 0;
    public final int TAB_ISSUES = 1;
    public final int TAB_NEWS = 2;

    static Candidate candidate = null;

    // Constructor
    public CandidateDetailFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public static CandidateDetailFragmentAdapter newInstance(FragmentManager fragmentManager, Candidate givenCandidate) {
        candidate = givenCandidate;
        return new CandidateDetailFragmentAdapter(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case TAB_BIO:
                return CandidateDetailBioFragment.newInstance(candidate);
            case TAB_ISSUES:
                return CandidateDetailIssuesFragment.newInstance(candidate);
            case TAB_NEWS:
                return CandidateDetailNewsFragment.newInstance(candidate);
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
