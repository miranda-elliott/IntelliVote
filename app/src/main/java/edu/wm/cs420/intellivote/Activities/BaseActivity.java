package edu.wm.cs420.intellivote.Activities;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
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
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            case R.id.candidates:
                if (currentActivitySelected != R.id.candidates) {
                    Intent intent = new Intent(this, CandidatesActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }
                return true;
            case R.id.issues:
                if (currentActivitySelected != R.id.issues) {
                    Intent intent = new Intent(this, IssuesActivity.class);
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
}
