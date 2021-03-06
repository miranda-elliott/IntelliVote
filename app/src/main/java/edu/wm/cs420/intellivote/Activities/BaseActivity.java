package edu.wm.cs420.intellivote.Activities;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import edu.wm.cs420.intellivote.Models.Candidate;
import edu.wm.cs420.intellivote.Models.Issue;
import edu.wm.cs420.intellivote.R;

public class BaseActivity extends AppCompatActivity {

    // Layout widgets
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    Toolbar mToolbar;
    FrameLayout mFrameLayout;

    // Shared variables
    protected int currentActivitySelected = 0;
    protected String pageTitle = "IntelliVote";
    protected Boolean upNavEnabled = false;

    // Shared data objects
    public List<Candidate> nationalCandidates = new ArrayList<>();
    public List<Candidate> favoriteCandidates = new ArrayList<>();
    public List<Issue> allIssues = new ArrayList<>();
    public List<Issue> favoriteIssues = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        // Get sample data
        getCandidates();
        getIssues();

        // Inflate parent view
        mDrawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);

        // Init child layouts/views
        mToolbar = (Toolbar) mDrawerLayout.findViewById(R.id.toolbar);
        mNavigationView = (NavigationView) mDrawerLayout.findViewById(R.id.nav_view);
        mFrameLayout = (FrameLayout) mDrawerLayout.findViewById(R.id.content_frame);

        // Inflate content view
        getLayoutInflater().inflate(layoutResID, mFrameLayout, true);
        super.setContentView(mDrawerLayout);

        // Set up toolbar
        mToolbar.setTitle(pageTitle);
        setSupportActionBar(mToolbar);
        if (upNavEnabled) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else {
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            mDrawerLayout.setDrawerListener(toggle);
            toggle.syncState();
        }


        // Set up navigation drawer
        mNavigationView.setCheckedItem(currentActivitySelected);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawer(mNavigationView);
                onOptionsItemSelected(menuItem);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                if (upNavEnabled) {
                    onBackPressed();
                }
                return true;
            case R.id.candidates:
                if (currentActivitySelected != R.id.candidates) {
                    Intent intent = new Intent(this, CandidateListActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }
                return true;
            case R.id.issues:
                if (currentActivitySelected != R.id.issues) {
                    Intent intent = new Intent(this, IssueListActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }
                return true;
            case R.id.vote:
                if (currentActivitySelected != R.id.vote) {
                    Intent intent = new Intent(this, VoteActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                }
                return true;
            case R.id.settings:
                if (currentActivitySelected != R.id.settings) {
                    Intent intent = new Intent(this, SettingsActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Get sample candidates from asset csv
    private void getCandidates() {
        AssetManager assetManager = getAssets();
        InputStream is = null;

        try {
            is = assetManager.open("candidates.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        String line = "";
        StringTokenizer st = null;
        try {
            reader.readLine(); // skip header line
            while ((line = reader.readLine()) != null) {
                st = new StringTokenizer(line, ",");
                Candidate candidate = new Candidate();
                candidate.name = st.nextToken();
                candidate.icon = st.nextToken();
                candidate.description = st.nextToken();
                candidate.bio = st.nextToken();
                candidate.matchRate = Float.valueOf(st.nextToken());
                candidate.isFavorite = Boolean.valueOf(st.nextToken());

                candidate.abortionMatch = Boolean.valueOf(st.nextToken());
                candidate.abortionDescription = st.nextToken();
                candidate.abortionFull = st.nextToken();

                candidate.gunMatch = Boolean.valueOf(st.nextToken());
                candidate.gunDescription = st.nextToken();
                candidate.gunFull = st.nextToken();

                candidate.marriageMatch = Boolean.valueOf(st.nextToken());
                candidate.marriageDescription = st.nextToken();
                candidate.marriageFull = st.nextToken();

                candidate.energyMatch = Boolean.valueOf(st.nextToken());
                candidate.energyDescription = st.nextToken();
                candidate.energyFull = st.nextToken();

                candidate.healthMatch = Boolean.valueOf(st.nextToken());
                candidate.healthDescription = st.nextToken();
                candidate.healthFull = st.nextToken();

                if (candidate.isFavorite) {
                    favoriteCandidates.add(candidate);
                }

                nationalCandidates.add(candidate);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // Get sample issues from asset csv
    private void getIssues() {
        AssetManager assetManager = getAssets();
        InputStream is = null;

        try {
            is = assetManager.open("issues.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        String line = "";
        StringTokenizer st = null;
        try {
            reader.readLine(); // skip header line
            while ((line = reader.readLine()) != null) {
                st = new StringTokenizer(line, ",");
                Issue issue = new Issue();
                issue.name = st.nextToken();
                issue.icon = st.nextToken();
                issue.description = st.nextToken();
                issue.history = st.nextToken();
                issue.isFavorite = Boolean.valueOf(st.nextToken());

                if (issue.isFavorite) {
                    favoriteIssues.add(issue);
                }

                allIssues.add(issue);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}
