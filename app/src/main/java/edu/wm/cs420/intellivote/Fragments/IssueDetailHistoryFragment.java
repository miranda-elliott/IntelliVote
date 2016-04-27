package edu.wm.cs420.intellivote.Fragments;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import edu.wm.cs420.intellivote.Models.Issue;
import edu.wm.cs420.intellivote.R;

public class IssueDetailHistoryFragment extends Fragment {

    // Flags for args
    public static final String ARG_ISSUE = "issue";

    // Args
    private Issue issue;

    // Interaction listener
    OnIssueDetailHistoryFragmentInteractionListener listener;

    // Factory method
    public static Fragment newInstance(Issue issue) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ISSUE, issue);
        IssueDetailHistoryFragment issueDetailHistoryFragment = new IssueDetailHistoryFragment();
        issueDetailHistoryFragment.setArguments(args);

        return issueDetailHistoryFragment;
    }
    // Required no arg constructor
    public IssueDetailHistoryFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            issue = (Issue) getArguments().getSerializable(ARG_ISSUE);
        }
    }

    //--UI Widgets--//
    private ImageView icon;
    private TextView history;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_issue_history, container, false);

        icon = (ImageView) rootView.findViewById(R.id.image_icon);
        history = (TextView) rootView.findViewById(R.id.text_history);

        try {
            icon.setImageBitmap(BitmapFactory.decodeStream(getContext().getAssets().open(issue.icon)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        history.setText(issue.history);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnIssueDetailHistoryFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnIssueDetailHistoryFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnIssueDetailHistoryFragmentInteractionListener {

    }
}
