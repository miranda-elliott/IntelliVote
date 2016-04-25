package edu.wm.cs420.intellivote.Activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import edu.wm.cs420.intellivote.R;

public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set shared variable
        this.currentActivitySelected = R.id.settings;
        this.pageTitle = getResources().getString(R.string.title_activity_settings);
        this.upNavEnabled = false;

        // inflate layout
        setContentView(R.layout.content_settings);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_edit) {
            // TODO: allow to update personal information, change quiz answers, notification preferences, etc.
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
