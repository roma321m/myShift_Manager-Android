package roman.game.myshiftmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import roman.game.myshiftmanager.Fragments.Fragment_Calendar;
import roman.game.myshiftmanager.Fragments.Fragment_Profile;
import roman.game.myshiftmanager.Fragments.Fragment_Reports;
import roman.game.myshiftmanager.Fragments.Fragment_Settings;
import roman.game.myshiftmanager.R;

public class Activity_Panel extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment_Profile panel_fragment_profile;
    private Fragment_Calendar panel_fragment_calendar;
    private Fragment_Reports panel_fragment_reports;
    private Fragment_Settings panel_fragment_settings;
    private MaterialToolbar panel_TLB_toolbar;
    private MaterialButton panel_BTN_profile, panel_BTN_calendar, panel_BTN_reports, panel_BTN_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        findViews();
        setFragments();

        setSupportActionBar(panel_TLB_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        panel_TLB_toolbar.setNavigationOnClickListener(v -> onBackPressed());

        panel_BTN_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragments(panel_fragment_profile);
                panel_TLB_toolbar.setTitle(R.string.profile);
                panel_BTN_profile.setIcon(getDrawable(R.drawable.img_profile_selected));
                panel_BTN_calendar.setIcon(getDrawable(R.drawable.img_calendar));
                panel_BTN_reports.setIcon(getDrawable(R.drawable.img_report));
                panel_BTN_settings.setIcon(getDrawable(R.drawable.img_settings));
            }
        });

        panel_BTN_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragments(panel_fragment_calendar);
                panel_TLB_toolbar.setTitle(R.string.calendar);
                panel_BTN_profile.setIcon(getDrawable(R.drawable.img_profile));
                panel_BTN_calendar.setIcon(getDrawable(R.drawable.img_calendar_selected));
                panel_BTN_reports.setIcon(getDrawable(R.drawable.img_report));
                panel_BTN_settings.setIcon(getDrawable(R.drawable.img_settings));
            }
        });

        panel_BTN_reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragments(panel_fragment_reports);
                panel_TLB_toolbar.setTitle(R.string.reports);
                panel_BTN_profile.setIcon(getDrawable(R.drawable.img_profile));
                panel_BTN_calendar.setIcon(getDrawable(R.drawable.img_calendar));
                panel_BTN_reports.setIcon(getDrawable(R.drawable.img_report_selected));
                panel_BTN_settings.setIcon(getDrawable(R.drawable.img_settings));
            }
        });

        panel_BTN_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragments(panel_fragment_settings);
                panel_TLB_toolbar.setTitle(R.string.settings);
                panel_BTN_profile.setIcon(getDrawable(R.drawable.img_profile));
                panel_BTN_calendar.setIcon(getDrawable(R.drawable.img_calendar));
                panel_BTN_reports.setIcon(getDrawable(R.drawable.img_report));
                panel_BTN_settings.setIcon(getDrawable(R.drawable.img_settings_selected));
            }
        });
    }

    private void setFragments() {
        panel_fragment_profile = new Fragment_Profile();
        panel_fragment_profile.setActivity(this);
        panel_fragment_calendar = new Fragment_Calendar();
        panel_fragment_calendar.setActivity(this);
        panel_fragment_reports = new Fragment_Reports();
        panel_fragment_reports.setActivity(this);
        panel_fragment_settings = new Fragment_Settings();
        panel_fragment_settings.setActivity(this);
    }

    private void replaceFragments(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.panel_fragment_view, fragment, null);
        fragmentTransaction.commit();
    }

    private void findViews() {
        panel_TLB_toolbar = findViewById(R.id.panel_TLB_toolbar);
        panel_BTN_profile = findViewById(R.id.panel_BTN_profile);
        panel_BTN_calendar = findViewById(R.id.panel_BTN_calendar);
        panel_BTN_reports = findViewById(R.id.panel_BTN_reports);
        panel_BTN_settings = findViewById(R.id.panel_BTN_settings);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            // TODO: 09/01/2022 add about
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}