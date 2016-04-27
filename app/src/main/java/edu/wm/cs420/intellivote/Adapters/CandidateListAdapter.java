package edu.wm.cs420.intellivote.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import edu.wm.cs420.intellivote.Activities.CandidateListActivity;
import edu.wm.cs420.intellivote.Activities.IssueDetailActivity;
import edu.wm.cs420.intellivote.Models.Candidate;
import edu.wm.cs420.intellivote.Models.Issue;
import edu.wm.cs420.intellivote.R;

public class CandidateListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnCandidateSelectedListener {
        void candidateSelected(Candidate candidate);
    }

    public List<Candidate> candidateList;
    private final OnCandidateSelectedListener listener;
    private Context context;
    private Issue issue;

    // View Holder
    private class CandidateViewHolder extends RecyclerView.ViewHolder {
        final CardView cardView;
        final ImageView icon;
        final TextView name;
        final TextView description;
        final TextView match;

        private CandidateViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.card_view);
            icon = (ImageView) itemView.findViewById(R.id.candidate_icon);
            name = (TextView) itemView.findViewById(R.id.candidate_name);
            description = (TextView) itemView.findViewById(R.id.candidate_desc);
            match = (TextView) itemView.findViewById(R.id.candidate_match);
        }
    }

    // Constructor
    public CandidateListAdapter(Issue issue, Context context, List<Candidate> candidateList, OnCandidateSelectedListener listener) {
        this.candidateList = candidateList;
        this.listener = listener;
        this.context = context;
        this.issue = issue;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View candidateView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_candidate, viewGroup, false);
        return new CandidateViewHolder(candidateView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final Candidate candidate = candidateList.get(position);

        CandidateViewHolder candidateViewHolder = CandidateViewHolder.class.cast(viewHolder);

        try {
            candidateViewHolder.icon.setImageBitmap(BitmapFactory.decodeStream(context.getAssets().open(candidate.icon)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        candidateViewHolder.name.setText(candidate.name);

        if (issue != null) {
            switch(issue.name) {
                case "Abortion":
                    candidateViewHolder.description.setText(candidate.abortionDescription);
                    if (candidate.abortionMatch) {
                        candidateViewHolder.match.setText("Agree");
                        candidateViewHolder.match.setTextColor(context.getResources().getColor(R.color.colorAgree));
                    } else {
                        candidateViewHolder.match.setText("Disagree");
                        candidateViewHolder.match.setTextColor(context.getResources().getColor(R.color.colorDisagree));
                    }
                    break;
                case "Gun Control":
                    candidateViewHolder.description.setText(candidate.gunDescription);
                    if (candidate.gunMatch) {
                        candidateViewHolder.match.setText("Agree");
                        candidateViewHolder.match.setTextColor(context.getResources().getColor(R.color.colorAgree));
                    } else {
                        candidateViewHolder.match.setText("Disagree");
                        candidateViewHolder.match.setTextColor(context.getResources().getColor(R.color.colorDisagree));
                    }
                    break;
                case "Marriage Equality":
                    candidateViewHolder.description.setText(candidate.marriageDescription);
                    if (candidate.marriageMatch) {
                        candidateViewHolder.match.setText("Agree");
                        candidateViewHolder.match.setTextColor(context.getResources().getColor(R.color.colorAgree));
                    } else {
                        candidateViewHolder.match.setText("Disagree");
                        candidateViewHolder.match.setTextColor(context.getResources().getColor(R.color.colorDisagree));
                    }
                    break;
                case "Renewable Energy":
                    candidateViewHolder.description.setText(candidate.energyDescription);
                    if (candidate.energyMatch) {
                        candidateViewHolder.match.setText("Agree");
                        candidateViewHolder.match.setTextColor(context.getResources().getColor(R.color.colorAgree));
                    } else {
                        candidateViewHolder.match.setText("Disagree");
                        candidateViewHolder.match.setTextColor(context.getResources().getColor(R.color.colorDisagree));
                    }
                    break;
                case "Universal Healthcare":
                    candidateViewHolder.description.setText(candidate.healthDescription);
                    if (candidate.healthMatch) {
                        candidateViewHolder.match.setText("Agree");
                        candidateViewHolder.match.setTextColor(context.getResources().getColor(R.color.colorAgree));
                    } else {
                        candidateViewHolder.match.setText("Disagree");
                        candidateViewHolder.match.setTextColor(context.getResources().getColor(R.color.colorDisagree));
                    }
                    break;
            }
        } else {
            candidateViewHolder.description.setText(candidate.description);
            candidateViewHolder.match.setText(String.format("%.0f%%", candidate.matchRate));
            if (candidate.matchRate > 50) {
                candidateViewHolder.match.setTextColor(context.getResources().getColor(R.color.colorAgree));
            } else {
                candidateViewHolder.match.setTextColor(context.getResources().getColor(R.color.colorDisagree));
            }
        }

        candidateViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.candidateSelected(candidate);
            }
        });
    }

    @Override
    public int getItemCount() {
        return candidateList.size();
    }
}