package edu.wm.cs420.intellivote.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.wm.cs420.intellivote.Models.Issue;
import edu.wm.cs420.intellivote.R;

public class IssueDetailNewsFragment extends Fragment {

    // Flags for args
    public static final String ARG_ISSUE = "issue";

    // Args
    private Issue issue;

    // Interaction listener
    OnIssueDetailNewsFragmentInteractionListener listener;

    // Factory method
    public static Fragment newInstance(Issue issue) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ISSUE, issue);
        IssueDetailNewsFragment issueDetailNewsFragment = new IssueDetailNewsFragment();
        issueDetailNewsFragment.setArguments(args);

        return issueDetailNewsFragment;
    }
    // Required no arg constructor
    public IssueDetailNewsFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            issue = (Issue) getArguments().getSerializable(ARG_ISSUE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_issue_news, container, false);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnIssueDetailNewsFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnIssueDetailNewsFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnIssueDetailNewsFragmentInteractionListener {

    }
}
