package edu.wm.cs420.intellivote.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.wm.cs420.intellivote.Adapters.CandidateFragmentAdapter;
import edu.wm.cs420.intellivote.Adapters.CandidateListAdapter;
import edu.wm.cs420.intellivote.Models.Candidate;
import edu.wm.cs420.intellivote.R;

public class CandidateFragment extends Fragment{

    // Flags for args
    public static final String ARG_CANDIDATE = "candidate";
    public static final String ARG_TAB = "tab";

    // Args
    private Candidate candidate;
    private int tab;

    // Interaction listener
    OnCandidateFragmentInteractionListener listener;

    // Factory method
    public static CandidateFragment newInstance(Candidate candidate, int tab) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CANDIDATE, candidate);
        args.putInt(ARG_TAB, tab);
        CandidateFragment candidateFragment = new CandidateFragment();
        candidateFragment.setArguments(args);

        return candidateFragment;
    }
    // Required no arg constructor
    public CandidateFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            candidate = (Candidate) getArguments().getSerializable(ARG_CANDIDATE);
            tab = (int) getArguments().getInt(ARG_TAB);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: create layouts for each tab

        View rootView = inflater.inflate(R.layout.fragment_candidate_bio, container, false);

        switch (tab) {
            case 0:
                rootView = inflater.inflate(R.layout.fragment_candidate_bio, container, false);

            case 1:
                rootView = inflater.inflate(R.layout.fragment_candidate_issues, container, false);

            case 2:
                rootView = inflater.inflate(R.layout.fragment_candidate_news, container, false);
        }

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnCandidateFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnCandidateFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnCandidateFragmentInteractionListener {

    }

}
