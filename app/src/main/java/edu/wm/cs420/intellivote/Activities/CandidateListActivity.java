package edu.wm.cs420.intellivote.Activities;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import edu.wm.cs420.intellivote.Adapters.CandidateListFragmentAdapter;
import edu.wm.cs420.intellivote.Fragments.CandidateListFragment;
import edu.wm.cs420.intellivote.Models.Candidate;
import edu.wm.cs420.intellivote.R;

public class CandidateListActivity extends BaseActivity
        implements CandidateListFragment.OnCandidateListFragmentInteractionListener {

    CandidateListFragmentAdapter mCandidateListFragmentAdapter;
    ViewPager mViewPager;
    TabLayout mTabLayout;

    // Intent extra to send to detail activity
    public static final String EXTRA_CANDIDATE = "chosen_candidate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set shared variables
        this.currentActivitySelected = R.id.candidates;
        this.pageTitle = getResources().getString(R.string.title_activity_candidates);
        this.upNavEnabled = false;

        // inflate layout
        setContentView(R.layout.content_tab_pager);

        // set up fragment viewpager
        mCandidateListFragmentAdapter = new CandidateListFragmentAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCandidateListFragmentAdapter);

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
            // TODO: Search candidates
            Toast.makeText(CandidateListActivity.this, "Search for candidates", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void candidateSelected(Candidate candidate) {
        Intent intent = new Intent(getApplicationContext(), CandidateDetailActivity.class);
        intent.putExtra(EXTRA_CANDIDATE, candidate);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}

