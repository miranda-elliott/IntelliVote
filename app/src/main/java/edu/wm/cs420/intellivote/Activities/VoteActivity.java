package edu.wm.cs420.intellivote.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import edu.wm.cs420.intellivote.R;

public class VoteActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set shared variable
        this.currentActivitySelected = R.id.vote;
        this.pageTitle = getResources().getString(R.string.title_activity_vote);
        this.upNavEnabled = false;

        // inflate layout
        setContentView(R.layout.content_vote);

        // open google maps for polling location address
        TextView pollingText = (TextView) findViewById(R.id.text_location);
        pollingText.setText("Williamsburg United Methodist Church\n500 Jamestown Rd\nWilliamsburg, VA 23185");
        pollingText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "geo:0,0?q=%s", "500 Jamestown Rd Williamsburg, VA 23185");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });


    }
}
