package edu.wm.cs420.intellivote.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.wm.cs420.intellivote.Adapters.IssueListAdapter;
import edu.wm.cs420.intellivote.Models.Issue;
import edu.wm.cs420.intellivote.R;

public class IssueListFragment extends Fragment {
    // Flags for args
    public static final String ARG_TYPE = "LIST_TYPE";

    // Args
    private int listType;

    // Interaction listener
    OnIssueListFragmentInteractionListener listener;

    List<Issue> issueList;

    // Populate sample data lists of menu_search
    private List<Issue> getAllIssues() {
        List<Issue> allIssues = new ArrayList<>();
        List<String> sampleCandidates = Arrays.asList("Sample Candidate 1", "Sample Candidate 2", "Sample Candidate 3");
        List<String> sampleNews = Arrays.asList("Sample News 1", "Sample News 2", "Sample News 3");
        Issue issue1 = new Issue("Climate Change", R.drawable.img_issue1, "Sample description", "Sample history", sampleCandidates, sampleNews, 86, false);
        allIssues.add(issue1);
        return allIssues;
    }

    private List<Issue> getFavoriteIssues() {
        List<Issue> favoriteIssues = new ArrayList<>();
        List<String> sampleCandidates = Arrays.asList("Sample Candidate 1", "Sample Candidate 2", "Sample Candidate 3");
        List<String> sampleNews = Arrays.asList("Sample News 1", "Sample News 2", "Sample News 3");
        Issue issue1 = new Issue("Climate Change", R.drawable.img_issue1, "Sample description", "Sample history", sampleCandidates, sampleNews, 86, true);
        favoriteIssues.add(issue1);
        return favoriteIssues;
    }

    // Factory method
    public static IssueListFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TYPE, type);
        IssueListFragment issueListFragment = new IssueListFragment();
        issueListFragment.setArguments(args);

        return issueListFragment;
    }
    // Required no arg constructor
    public IssueListFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            listType = (int) getArguments().getSerializable(ARG_TYPE);
        }

        // get list corresponding to tab
        if (listType == 1) {
            issueList = getFavoriteIssues();
        } else {
            issueList = getAllIssues();
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

        recyclerView.setAdapter(new IssueListAdapter(issueList, new IssueListAdapter.OnIssueSelectedListener() {
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
            listener = (OnIssueListFragmentInteractionListener) context;
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

    public interface OnIssueListFragmentInteractionListener {
        void issueSelected(Issue issue);
    }
}
