package com.stephentuso.welcomeexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.stephentuso.welcome.WelcomeScreenHelper;
import com.stephentuso.welcome.ui.WelcomeActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_WELCOME_SCREEN_RESULT = 13;

    private WelcomeScreenHelper sampleWelcomeScreen;

    private List<ScreenItem> welcomeScreens = new ArrayList<>();

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // The welcome screen for this app (only one that automatically shows)
        sampleWelcomeScreen = new WelcomeScreenHelper(this, SampleWelcomeActivity.class);
        sampleWelcomeScreen.show(savedInstanceState);

        //List of welcome screens
        welcomeScreens.add(new ScreenItem(R.string.title_sample, R.string.description_sample, sampleWelcomeScreen, null));
        welcomeScreens.add(new ScreenItem(R.string.title_light, R.string.description_light, LightWelcomeActivity.class));
        welcomeScreens.add(new ScreenItem(R.string.title_no_skip, R.string.description_no_skip, NoSkipWelcomeActivity.class, REQUEST_WELCOME_SCREEN_RESULT));
        welcomeScreens.add(new ScreenItem(R.string.title_back_exit, R.string.description_back_exit, BackExitWelcomeActivity.class));
        welcomeScreens.add(new ScreenItem(R.string.title_custom_page, R.string.description_custom_page, CustomPageWelcomeActivity.class));
        welcomeScreens.add(new ScreenItem(R.string.title_default, R.string.description_default, DefaultWelcomeActivity.class));

        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(new Adapter());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_WELCOME_SCREEN_RESULT) {

            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Completed (RESULT_OK)", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Canceled (RESULT_CANCELED)", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // This is needed to prevent welcome screens from being
        // automatically shown multiple times

        // This is the only one needed because it is the only one that
        // is shown automatically. The others are only force shown.
        sampleWelcomeScreen.onSaveInstanceState(outState);
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        View button;

        ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            button = itemView.findViewById(R.id.button);
        }

    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(View.inflate(MainActivity.this, R.layout.item_welcome_screen_list, null));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            final ScreenItem item = welcomeScreens.get(position);

            holder.title.setText(item.title);
            holder.description.setText(item.description);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.requestCode != null) {
                        item.helper.forceShow(item.requestCode);
                    } else {
                        item.helper.forceShow();
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return welcomeScreens.size();
        }

    }

    /**
     * Data model for list of welcome screens
     * Title to show in list, welcome screen to show, request code
     */
    private class ScreenItem {

        String title;
        String description;
        WelcomeScreenHelper helper;
        Integer requestCode;

        ScreenItem(int titleRes, int descriptionRes, Class<? extends WelcomeActivity> activityClass) {
            this(titleRes, descriptionRes, activityClass, null);
        }

        ScreenItem(int titleRes, int descriptionRes, Class<? extends WelcomeActivity> activityClass, Integer requestCode) {
            this(titleRes, descriptionRes, new WelcomeScreenHelper(MainActivity.this, activityClass), requestCode);
        }

        ScreenItem(int titleRes, int descriptionRes, WelcomeScreenHelper helper, Integer requestCode) {
            this.title = getString(titleRes);
            this.description = getString(descriptionRes);
            this.helper = helper;
            this.requestCode = requestCode;
        }

    }

}
