package edu.wm.cs420.intellivote.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import edu.wm.cs420.intellivote.Adapters.IssueListFragmentAdapter;
import edu.wm.cs420.intellivote.Fragments.IssueListFragment;
import edu.wm.cs420.intellivote.Models.Issue;
import edu.wm.cs420.intellivote.R;

public class IssueListActivity extends BaseActivity
        implements IssueListFragment.OnIssueListFragmentInteractionListener {

    IssueListFragmentAdapter mIssueListFragmentAdapter;
    ViewPager mViewPager;
    TabLayout mTabLayout;

    // Intent extra to send to detail activity
    public static final String EXTRA_ISSUE = "chosen_issue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set shared variable
        this.currentActivitySelected = R.id.issues;
        this.pageTitle = getResources().getString(R.string.title_activity_issues);
        this.upNavEnabled = false;

        // inflate view
        setContentView(R.layout.content_tab_pager);

        // set up fragment viewpager
        mIssueListFragmentAdapter = new IssueListFragmentAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mIssueListFragmentAdapter);

        // set up tabs
        mTabLayout = (TabLayout) findViewById(R.id.pager_tabs);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_search) {
            // TODO: Search issues
            Toast.makeText(IssueListActivity.this, "Search for issue", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void issueSelected(Issue issue) {
        Intent intent = new Intent(getApplicationContext(), IssueDetailActivity.class);
        intent.putExtra(EXTRA_ISSUE, issue);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
