package edu.wm.cs420.intellivote.Activities;

import android.os.Bundle;

import edu.wm.cs420.intellivote.R;

public class VoteActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set shared variable
        this.currentActivitySelected = R.id.vote;
        this.pageTitle = getResources().getString(R.string.title_activity_vote);
        this.upNavEnabled = false;

        // inflate layout
        setContentView(R.layout.content_vote);
    }


}
