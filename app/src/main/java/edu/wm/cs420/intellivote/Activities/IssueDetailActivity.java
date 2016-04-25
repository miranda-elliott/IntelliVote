package edu.wm.cs420.intellivote.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import edu.wm.cs420.intellivote.Adapters.IssueFragmentAdapter;
import edu.wm.cs420.intellivote.Fragments.IssueFragment;
import edu.wm.cs420.intellivote.Models.Issue;
import edu.wm.cs420.intellivote.R;

public class IssueDetailActivity extends BaseActivity
        implements IssueFragment.OnIssueFragmentInteractionListener {

    public Issue issue;

    IssueFragmentAdapter mIssueFragmentAdapter;
    ViewPager mViewPager;
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get extra
        issue = (Issue) getIntent().getSerializableExtra(IssuesActivity.EXTRA_ISSUE);

        // set shared variables
        this.currentActivitySelected = R.id.issues;
        this.pageTitle = issue.name;
        this.upNavEnabled = true;

        // inflate layout
        setContentView(R.layout.content_tab_pager);

        // set up fragment viewpager
        mIssueFragmentAdapter = IssueFragmentAdapter.newInstance(getSupportFragmentManager(), issue);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mIssueFragmentAdapter);

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
            issue.isFavorite = true;
            invalidateOptionsMenu();
            return true;
        } else if (id == R.id.action_unfavorite) {
            // TODO: Remove issue from favorites
            issue.isFavorite = false;
            invalidateOptionsMenu();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
