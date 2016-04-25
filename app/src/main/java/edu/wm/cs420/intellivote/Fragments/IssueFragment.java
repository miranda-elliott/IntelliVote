package edu.wm.cs420.intellivote.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.wm.cs420.intellivote.Models.Issue;
import edu.wm.cs420.intellivote.R;

public class IssueFragment extends Fragment {

    // Flags for args
    public static final String ARG_ISSUE = "issue";
    public static final String ARG_TAB = "tab";

    // Args
    private Issue issue;
    private int tab;

    // Interaction listener
    OnIssueFragmentInteractionListener listener;

    // Factory method
    public static IssueFragment newInstance(Issue issue, int tab) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ISSUE, issue);
        args.putInt(ARG_TAB, tab);
        IssueFragment issueFragment = new IssueFragment();
        issueFragment.setArguments(args);

        return issueFragment;
    }
    // Required no arg constructor
    public IssueFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            issue = (Issue) getArguments().getSerializable(ARG_ISSUE);
            tab = (int) getArguments().getInt(ARG_TAB);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: create layouts for each tab

        View rootView = inflater.inflate(R.layout.fragment_issue_history, container, false);

        switch (tab) {
            case 0:
                rootView = inflater.inflate(R.layout.fragment_issue_history, container, false);

            case 1:
                rootView = inflater.inflate(R.layout.fragment_issue_candidates, container, false);

            case 2:
                rootView = inflater.inflate(R.layout.fragment_issue_news, container, false);
        }

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnIssueFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnIssueFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnIssueFragmentInteractionListener {

    }
}
