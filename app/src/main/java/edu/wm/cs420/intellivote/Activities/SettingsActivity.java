package edu.wm.cs420.intellivote.Activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

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

        // fill content
        TextView personalInfo = (TextView) findViewById(R.id.text_personalinfo);
        personalInfo.setText("Address:\n600 Ukrop Way\nWilliamsburg, VA 23185\nEmail:\nabrickhouse@email.wm.edu");

        TextView quizAnswers = (TextView) findViewById(R.id.text_quiz);
        quizAnswers.setText("What is your stance on abortion?: Pro-choice\n" +
                "Should there be more restrictions on the current process of purchasing a gun?: Yes\n" +
                "Do you support the legalization of same-sex marriage?: Yes\n" +
                "Should the government encourage the use of renewable energy?: Yes\n" +
                "Do you support universal healthcare?: Yes");

        TextView notifications = (TextView) findViewById(R.id.text_notifications);
        notifications.setText("Election reminders: Yes\n" +
                "Registration reminders: Yes\n" +
                "Favorites news updates: No");
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
            Toast.makeText(SettingsActivity.this, "Update personal information, quiz answers, notifications preferences, etc.", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
