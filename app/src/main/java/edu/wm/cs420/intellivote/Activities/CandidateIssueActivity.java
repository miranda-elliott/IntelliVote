package edu.wm.cs420.intellivote.Activities;

import android.os.Bundle;
import android.widget.TextView;

import edu.wm.cs420.intellivote.R;

public class CandidateIssueActivity extends BaseActivity {

    String candidateName;
    String issueName;
    int currentActivity;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get extra
        candidateName = getIntent().getStringExtra(CandidateDetailActivity.EXTRA_CANDIDATE_NAME);
        issueName = getIntent().getStringExtra(CandidateDetailActivity.EXTRA_ISSUE_NAME);
        currentActivity = getIntent().getIntExtra(CandidateDetailActivity.EXTRA_ACTIVITY, 0);

        // set shared variables
        this.currentActivitySelected = R.id.candidates;
        this.pageTitle = candidateName + " on " + issueName;
        this.upNavEnabled = true;

        // inflate layout
        setContentView(R.layout.content_candidate_issue);

        // fill page
        textView = (TextView) findViewById(R.id.text_body);
        textView.setText(getCandidatePosition());
    }

    String getCandidatePosition() {
        return "Sample";
    }

}
