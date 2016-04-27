package edu.wm.cs420.intellivote.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import edu.wm.cs420.intellivote.Adapters.CandidateDetailFragmentAdapter;
import edu.wm.cs420.intellivote.Fragments.CandidateDetailBioFragment;
import edu.wm.cs420.intellivote.Fragments.CandidateDetailIssuesFragment;
import edu.wm.cs420.intellivote.Fragments.CandidateDetailNewsFragment;
import edu.wm.cs420.intellivote.Models.Candidate;
import edu.wm.cs420.intellivote.Models.Issue;
import edu.wm.cs420.intellivote.R;

public class CandidateDetailActivity extends BaseActivity
        implements CandidateDetailBioFragment.OnCandidateDetailBioFragmentInteractionListener,
        CandidateDetailIssuesFragment.OnCandidateDetailIssuesFragmentInteractionListener,
        CandidateDetailNewsFragment.OnCandidateDetailNewsFragmentInteractionListener {

    public Candidate candidate;

    // Intent extras to send to candidate issue activity
    public static final String EXTRA_CANDIDATE_NAME = "candidate_name";
    public static final String EXTRA_ISSUE_NAME = "issue_name";
    public static final String EXTRA_ACTIVITY = "parent_activity";

    CandidateDetailFragmentAdapter mCandidateDetailFragmentAdapter;
    ViewPager mViewPager;
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get extra
        candidate = (Candidate) getIntent().getSerializableExtra(CandidateListActivity.EXTRA_CANDIDATE);

        // set shared variables
        this.currentActivitySelected = R.id.candidates;
        this.pageTitle = candidate.name;
        this.upNavEnabled = true;

        // inflate layout
        setContentView(R.layout.content_tab_pager);

        // set up fragment viewpager
        mCandidateDetailFragmentAdapter = CandidateDetailFragmentAdapter.newInstance(getSupportFragmentManager(), candidate);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCandidateDetailFragmentAdapter);

        // set up tabs
        mTabLayout = (TabLayout) findViewById(R.id.pager_tabs);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (candidate.isFavorite) {
            getMenuInflater().inflate(R.menu.menu_unfavorite, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_favorite, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_favorite) {
            // TODO: Save candidate to favorites
            Toast.makeText(CandidateDetailActivity.this, "Add candidate to favorites", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_unfavorite) {
            // TODO: Remove candidate from favorites
            Toast.makeText(CandidateDetailActivity.this, "Remove candidate from favorites", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void issueSelected(Issue issue) {
        Intent intent = new Intent(this, CandidateIssueActivity.class);
        intent.putExtra(EXTRA_CANDIDATE_NAME, candidate.name);
        intent.putExtra(EXTRA_ISSUE_NAME, issue.name);
        intent.putExtra(EXTRA_ACTIVITY, R.id.candidates);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
