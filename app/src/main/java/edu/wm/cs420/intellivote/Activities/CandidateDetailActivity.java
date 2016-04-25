package edu.wm.cs420.intellivote.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import edu.wm.cs420.intellivote.Adapters.CandidateFragmentAdapter;
import edu.wm.cs420.intellivote.Fragments.CandidateFragment;
import edu.wm.cs420.intellivote.Models.Candidate;
import edu.wm.cs420.intellivote.R;

public class CandidateDetailActivity extends BaseActivity
        implements CandidateFragment.OnCandidateFragmentInteractionListener {

    public Candidate candidate;

    CandidateFragmentAdapter mCandidateFragmentAdapter;
    ViewPager mViewPager;
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get extra
        candidate = (Candidate) getIntent().getSerializableExtra(CandidatesActivity.EXTRA_CANDIDATE);

        // set shared variables
        this.currentActivitySelected = R.id.candidates;
        this.pageTitle = candidate.name;
        this.upNavEnabled = true;

        // inflate layout
        setContentView(R.layout.content_tab_pager);

        // set up fragment viewpager
        mCandidateFragmentAdapter = CandidateFragmentAdapter.newInstance(getSupportFragmentManager(), candidate);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCandidateFragmentAdapter);

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
            candidate.isFavorite = true;
            invalidateOptionsMenu();
            return true;
        } else if (id == R.id.action_unfavorite) {
            // TODO: Remove candidate from favorites
            candidate.isFavorite = false;
            invalidateOptionsMenu();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
