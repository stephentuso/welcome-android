package com.stephentuso.welcomeexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by stephentuso on 11/16/16.
 */

public class ExampleActionActivity extends AppCompatActivity {

    public static final String KEY_ACTION_TITLE = "action_title";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_action);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String action = getIntent().getStringExtra(KEY_ACTION_TITLE);

        setTitle(action);

        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(getString(R.string.example_action_description, action));

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complete();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void complete() {
        setResult(RESULT_OK);
        finish();
    }

}
