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
import edu.wm.cs420.intellivote.R;

public class CandidateListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnCandidateSelectedListener {
        void candidateSelected(Candidate candidate);
    }

    public List<Candidate> candidateList;
    private final OnCandidateSelectedListener listener;

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
    public CandidateListAdapter(List<Candidate> candidateList, OnCandidateSelectedListener listener) {
        this.candidateList = candidateList;
        this.listener = listener;
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
        candidateViewHolder.icon.setImageResource(candidate.icon);
        candidateViewHolder.name.setText(candidate.name);
        candidateViewHolder.description.setText(candidate.description);
        candidateViewHolder.match.setText(String.format("%.0f%%", candidate.matchRate));

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