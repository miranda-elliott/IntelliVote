package edu.wm.cs420.intellivote.Fragments;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import edu.wm.cs420.intellivote.Models.Candidate;
import edu.wm.cs420.intellivote.R;

public class CandidateDetailBioFragment extends Fragment{

    // Flags for args
    public static final String ARG_CANDIDATE = "candidate";

    // Args
    private Candidate candidate;

    // Interaction listener
    OnCandidateDetailBioFragmentInteractionListener listener;

    // Factory method
    public static Fragment newInstance(Candidate candidate) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CANDIDATE, candidate);
        CandidateDetailBioFragment candidateDetailBioFragment = new CandidateDetailBioFragment();
        candidateDetailBioFragment.setArguments(args);

        return candidateDetailBioFragment;
    }
    // Required no arg constructor
    public CandidateDetailBioFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            candidate = (Candidate) getArguments().getSerializable(ARG_CANDIDATE);
        }
    }

    //--UI Widgets--//
    private ImageView icon;
    private TextView bio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_candidate_bio, container, false);

        icon = (ImageView) rootView.findViewById(R.id.image_icon);
        bio = (TextView) rootView.findViewById(R.id.text_bio);

        try {
            icon.setImageBitmap(BitmapFactory.decodeStream(getContext().getAssets().open(candidate.icon)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bio.setText(candidate.bio);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnCandidateDetailBioFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnCandidateDetailBioFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnCandidateDetailBioFragmentInteractionListener {

    }

}
