package edu.wm.cs420.intellivote.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.wm.cs420.intellivote.Models.Candidate;
import edu.wm.cs420.intellivote.Models.Issue;
import edu.wm.cs420.intellivote.R;

public class IssueListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnIssueSelectedListener {
        void issueSelected(Issue issue);
    }

    public List<Issue> issueList;
    private final OnIssueSelectedListener listener;

    // View Holder
    private class IssueViewHolder extends RecyclerView.ViewHolder {
        final CardView cardView;
        final ImageView icon;
        final TextView name;
        final TextView description;
        final TextView match;

        private IssueViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.card_view);
            icon = (ImageView) itemView.findViewById(R.id.issue_icon);
            name = (TextView) itemView.findViewById(R.id.issue_name);
            description = (TextView) itemView.findViewById(R.id.issue_desc);
            match = (TextView) itemView.findViewById(R.id.issue_match);
        }
    }

    // Constructor
    public IssueListAdapter(List<Issue> issueList, OnIssueSelectedListener listener) {
        this.issueList = issueList;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View issueView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_issue, viewGroup, false);
        return new IssueViewHolder(issueView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final Issue issue = issueList.get(position);

        IssueViewHolder issueViewHolder = IssueViewHolder.class.cast(viewHolder);
        issueViewHolder.icon.setImageResource(issue.icon);
        issueViewHolder.name.setText(issue.name);
        issueViewHolder.description.setText(issue.description);
        issueViewHolder.match.setText(String.format("%.0f%%", issue.matchRate));

        issueViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.issueSelected(issue);
            }
        });
    }

    @Override
    public int getItemCount() {
        return issueList.size();
    }
}