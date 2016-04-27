package edu.wm.cs420.intellivote.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.wm.cs420.intellivote.Activities.BaseActivity;
import edu.wm.cs420.intellivote.Adapters.CandidateListAdapter;
import edu.wm.cs420.intellivote.Adapters.IssueListAdapter;
import edu.wm.cs420.intellivote.Models.Candidate;
import edu.wm.cs420.intellivote.Models.Issue;
import edu.wm.cs420.intellivote.R;

public class IssueDetailCandidatesFragment extends Fragment {

    // Flags for args
    public static final String ARG_ISSUE = "issue";

    // Args
    private Issue issue;

    // Interaction listener
    OnIssueDetailCandidatesFragmentInteractionListener listener;

    // Factory method
    public static Fragment newInstance(Issue issue) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ISSUE, issue);
        IssueDetailCandidatesFragment issueDetailCandidatesFragment = new IssueDetailCandidatesFragment();
        issueDetailCandidatesFragment.setArguments(args);

        return issueDetailCandidatesFragment;
    }
    // Required no arg constructor
    public IssueDetailCandidatesFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            issue = (Issue) getArguments().getSerializable(ARG_ISSUE);
        }
    }

    //--UI Widgets--//
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        BaseActivity parentActivity = (BaseActivity) getActivity();
        recyclerView.setAdapter(new CandidateListAdapter(issue, getContext(), parentActivity.nationalCandidates, new CandidateListAdapter.OnCandidateSelectedListener() {
            @Override
            public void candidateSelected(Candidate candidate) {
                listener.candidateSelected(candidate);
            }
        }));

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnIssueDetailCandidatesFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnIssueDetailCandidatesFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnIssueDetailCandidatesFragmentInteractionListener {
        void candidateSelected(Candidate candidate);
    }
}
