package edu.wm.cs420.intellivote.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import edu.wm.cs420.intellivote.Activities.BaseActivity;
import edu.wm.cs420.intellivote.Adapters.CandidateListAdapter;
import edu.wm.cs420.intellivote.Models.Candidate;
import edu.wm.cs420.intellivote.R;

public class CandidateListFragment extends Fragment {
    // Flags for args
    public static final String ARG_TYPE = "LIST_TYPE";

    // Args
    private int listType;

    // Interaction listener
    OnCandidateListFragmentInteractionListener listener;

    List<Candidate> candidateList;

    // Factory method
    public static Fragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TYPE, type);
        CandidateListFragment candidateListFragment = new CandidateListFragment();
        candidateListFragment.setArguments(args);

        return candidateListFragment;
    }
    // Required no arg constructor
    public CandidateListFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            listType = (int) getArguments().getSerializable(ARG_TYPE);
        }

        // get candidates list corresponding to tab
        BaseActivity parentActivity = (BaseActivity) getActivity();
        if (listType == 1) {
            candidateList = parentActivity.favoriteCandidates;
        } else {
            candidateList = parentActivity.nationalCandidates;
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

        recyclerView.setAdapter(new CandidateListAdapter(null, getContext(), candidateList, new CandidateListAdapter.OnCandidateSelectedListener() {
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
            listener = (OnCandidateListFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnCandidateListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnCandidateListFragmentInteractionListener {
        void candidateSelected(Candidate candidate);
    }
}
