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
import edu.wm.cs420.intellivote.Adapters.IssueListAdapter;
import edu.wm.cs420.intellivote.Models.Candidate;
import edu.wm.cs420.intellivote.Models.Issue;
import edu.wm.cs420.intellivote.R;

public class CandidateDetailIssuesFragment extends Fragment {

    // Flags for args
    public static final String ARG_CANDIDATE = "candidate";

    // Args
    private Candidate candidate;

    // Interaction listener
    OnCandidateDetailIssuesFragmentInteractionListener listener;

    // Factory method
    public static Fragment newInstance(Candidate candidate) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CANDIDATE, candidate);
        CandidateDetailIssuesFragment candidateDetailIssuesFragment = new CandidateDetailIssuesFragment();
        candidateDetailIssuesFragment.setArguments(args);

        return candidateDetailIssuesFragment;
    }
    // Required no arg constructor
    public CandidateDetailIssuesFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            candidate = (Candidate) getArguments().getSerializable(ARG_CANDIDATE);
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
        recyclerView.setAdapter(new IssueListAdapter(candidate, getContext(), parentActivity.allIssues, new IssueListAdapter.OnIssueSelectedListener() {
            @Override
            public void issueSelected(Issue issue) {
                listener.issueSelected(issue);
            }
        }));

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnCandidateDetailIssuesFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnCandidateDetailIssuesFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnCandidateDetailIssuesFragmentInteractionListener {
        void issueSelected(Issue issue);
    }
}
