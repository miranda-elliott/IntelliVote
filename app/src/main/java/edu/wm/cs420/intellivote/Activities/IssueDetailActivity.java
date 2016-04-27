package edu.wm.cs420.intellivote.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import edu.wm.cs420.intellivote.Adapters.IssueDetailFragmentAdapter;
import edu.wm.cs420.intellivote.Fragments.IssueDetailCandidatesFragment;
import edu.wm.cs420.intellivote.Fragments.IssueDetailHistoryFragment;
import edu.wm.cs420.intellivote.Fragments.IssueDetailNewsFragment;
import edu.wm.cs420.intellivote.Models.Candidate;
import edu.wm.cs420.intellivote.Models.Issue;
import edu.wm.cs420.intellivote.R;

public class IssueDetailActivity extends BaseActivity
        implements IssueDetailHistoryFragment.OnIssueDetailHistoryFragmentInteractionListener,
        IssueDetailCandidatesFragment.OnIssueDetailCandidatesFragmentInteractionListener,
        IssueDetailNewsFragment.OnIssueDetailNewsFragmentInteractionListener {

    public Issue issue;

    // Intent extras to send to candidate issue activity
    public static final String EXTRA_CANDIDATE_NAME = "candidate_name";
    public static final String EXTRA_ISSUE_NAME = "issue_name";
    public static final String EXTRA_TEXT = "body_text";
    public static final String EXTRA_ACTIVITY = "parent_activity";

    IssueDetailFragmentAdapter mIssueDetailFragmentAdapter;
    ViewPager mViewPager;
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get extra
        issue = (Issue) getIntent().getSerializableExtra(IssueListActivity.EXTRA_ISSUE);

        // set shared variables
        this.currentActivitySelected = R.id.issues;
        this.pageTitle = issue.name;
        this.upNavEnabled = true;

        // inflate layout
        setContentView(R.layout.content_tab_pager);

        // set up fragment viewpager
        mIssueDetailFragmentAdapter = IssueDetailFragmentAdapter.newInstance(getSupportFragmentManager(), issue);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mIssueDetailFragmentAdapter);

        // set up tabs
        mTabLayout = (TabLayout) findViewById(R.id.pager_tabs);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (issue.isFavorite) {
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
            // TODO: Save issue to favorites
            Toast.makeText(IssueDetailActivity.this, "Add issue to favorites", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_unfavorite) {
            // TODO: Remove issue from favorites
            Toast.makeText(IssueDetailActivity.this, "Remove issue from favorites", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void candidateSelected(Candidate candidate) {
        Intent intent = new Intent(this, CandidateIssueActivity.class);
        intent.putExtra(EXTRA_CANDIDATE_NAME, candidate.name);
        intent.putExtra(EXTRA_ISSUE_NAME, issue.name);
        intent.putExtra(EXTRA_ACTIVITY, R.id.issues);

        switch(issue.name) {
            case "Abortion":
                intent.putExtra(EXTRA_TEXT, candidate.abortionFull);
                break;
            case "Gun Control":
                intent.putExtra(EXTRA_TEXT, candidate.gunFull);
                break;
            case "Marriage Equality":
                intent.putExtra(EXTRA_TEXT, candidate.marriageFull);
                break;
            case "Renewable Energy":
                intent.putExtra(EXTRA_TEXT, candidate.energyFull);
                break;
            case "Universal Healthcare":
                intent.putExtra(EXTRA_TEXT, candidate.healthFull);
                break;
        }

        startActivity(intent);
        overridePendingTransition(0,0);
    }
}
