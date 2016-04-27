package edu.wm.cs420.intellivote.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.wm.cs420.intellivote.Models.Candidate;
import edu.wm.cs420.intellivote.R;

public class CandidateDetailNewsFragment extends Fragment {

    // Flags for args
    public static final String ARG_CANDIDATE = "candidate";

    // Args
    private Candidate candidate;

    // Interaction listener
    OnCandidateDetailNewsFragmentInteractionListener listener;

    // Factory method
    public static Fragment newInstance(Candidate candidate) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CANDIDATE, candidate);
        CandidateDetailNewsFragment candidateDetailNewsFragment = new CandidateDetailNewsFragment();
        candidateDetailNewsFragment.setArguments(args);

        return candidateDetailNewsFragment;
    }
    // Required no arg constructor
    public CandidateDetailNewsFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            candidate = (Candidate) getArguments().getSerializable(ARG_CANDIDATE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_candidate_news, container, false);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnCandidateDetailNewsFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnCandidateDetailNewsFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnCandidateDetailNewsFragmentInteractionListener {

    }

}
