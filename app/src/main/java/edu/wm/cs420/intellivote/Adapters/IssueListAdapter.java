package edu.wm.cs420.intellivote.Adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
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
    private Context context;
    private Candidate candidate;

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
    public IssueListAdapter(Candidate candidate, Context context, List<Issue> issueList, OnIssueSelectedListener listener) {
        this.issueList = issueList;
        this.listener = listener;
        this.context = context;
        this.candidate = candidate;
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
        try {
            issueViewHolder.icon.setImageBitmap(BitmapFactory.decodeStream(context.getAssets().open(issue.icon)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        issueViewHolder.name.setText(issue.name);

        if (candidate != null) {
            switch (issue.name) {
                case "Abortion":
                    issueViewHolder.description.setText(candidate.abortionDescription);
                    if (candidate.abortionMatch) {
                        issueViewHolder.match.setText("Agree");
                        issueViewHolder.match.setTextColor(context.getResources().getColor(R.color.colorAgree));
                    } else {
                        issueViewHolder.match.setText("Disagree");
                        issueViewHolder.match.setTextColor(context.getResources().getColor(R.color.colorDisagree));
                    }
                    break;
                case "Gun Control":
                    issueViewHolder.description.setText(candidate.gunDescription);
                    if (candidate.gunMatch) {
                        issueViewHolder.match.setText("Agree");
                        issueViewHolder.match.setTextColor(context.getResources().getColor(R.color.colorAgree));
                    } else {
                        issueViewHolder.match.setText("Disagree");
                        issueViewHolder.match.setTextColor(context.getResources().getColor(R.color.colorDisagree));
                    }
                    break;
                case "Marriage Equality":
                    issueViewHolder.description.setText(candidate.marriageDescription);
                    if (candidate.marriageMatch) {
                        issueViewHolder.match.setText("Agree");
                        issueViewHolder.match.setTextColor(context.getResources().getColor(R.color.colorAgree));
                    } else {
                        issueViewHolder.match.setText("Disagree");
                        issueViewHolder.match.setTextColor(context.getResources().getColor(R.color.colorDisagree));
                    }
                    break;
                case "Renewable Energy":
                    issueViewHolder.description.setText(candidate.energyDescription);
                    if (candidate.energyMatch) {
                        issueViewHolder.match.setText("Agree");
                        issueViewHolder.match.setTextColor(context.getResources().getColor(R.color.colorAgree));
                    } else {
                        issueViewHolder.match.setText("Disagree");
                        issueViewHolder.match.setTextColor(context.getResources().getColor(R.color.colorDisagree));
                    }
                    break;
                case "Universal Healthcare":
                    issueViewHolder.description.setText(candidate.healthDescription);
                    if (candidate.healthMatch) {
                        issueViewHolder.match.setText("Agree");
                        issueViewHolder.match.setTextColor(context.getResources().getColor(R.color.colorAgree));
                    } else {
                        issueViewHolder.match.setText("Disagree");
                        issueViewHolder.match.setTextColor(context.getResources().getColor(R.color.colorDisagree));
                    }
                    break;
            }
        } else {
            issueViewHolder.description.setText(issue.description);
            issueViewHolder.match.setVisibility(View.INVISIBLE);
        }

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