package roman.game.myshiftmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

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
    private MaterialTextView panel_LBL_title;
    private ShapeableImageView panel_IMG_profile, panel_IMG_calendar, panel_IMG_reports, panel_IMG_settings;
    private MaterialButton panel_BTN_profile, panel_BTN_calendar, panel_BTN_reports, panel_BTN_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        findViews();
        setFragments();

        panel_BTN_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragments(panel_fragment_profile);
                panel_LBL_title.setText("Profile");
                panel_IMG_profile.setImageResource(R.drawable.img_profile_selected);
                panel_IMG_calendar.setImageResource(R.drawable.img_calendar);
                panel_IMG_reports.setImageResource(R.drawable.img_report);
                panel_IMG_settings.setImageResource(R.drawable.img_settings);
            }
        });

        panel_BTN_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragments(panel_fragment_calendar);
                panel_LBL_title.setText("Calendar");
                panel_IMG_profile.setImageResource(R.drawable.img_profile);
                panel_IMG_calendar.setImageResource(R.drawable.img_calendar_selected);
                panel_IMG_reports.setImageResource(R.drawable.img_report);
                panel_IMG_settings.setImageResource(R.drawable.img_settings);
            }
        });

        panel_BTN_reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragments(panel_fragment_reports);
                panel_LBL_title.setText("Reports");
                panel_IMG_profile.setImageResource(R.drawable.img_profile);
                panel_IMG_calendar.setImageResource(R.drawable.img_calendar);
                panel_IMG_reports.setImageResource(R.drawable.img_report_selected);
                panel_IMG_settings.setImageResource(R.drawable.img_settings);
            }
        });

        panel_BTN_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragments(panel_fragment_settings);
                panel_LBL_title.setText("Settings");
                panel_IMG_profile.setImageResource(R.drawable.img_profile);
                panel_IMG_calendar.setImageResource(R.drawable.img_calendar);
                panel_IMG_reports.setImageResource(R.drawable.img_report);
                panel_IMG_settings.setImageResource(R.drawable.img_settings_selected);
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
        panel_LBL_title = findViewById(R.id.panel_LBL_title);
        panel_BTN_profile = findViewById(R.id.panel_BTN_profile);
        panel_BTN_calendar = findViewById(R.id.panel_BTN_calendar);
        panel_BTN_reports = findViewById(R.id.panel_BTN_reports);
        panel_BTN_settings = findViewById(R.id.panel_BTN_settings);
        panel_IMG_profile = findViewById(R.id.panel_IMG_profile);
        panel_IMG_calendar = findViewById(R.id.panel_IMG_calendar);
        panel_IMG_reports = findViewById(R.id.panel_IMG_reports);
        panel_IMG_settings = findViewById(R.id.panel_IMG_settings);
    }
}