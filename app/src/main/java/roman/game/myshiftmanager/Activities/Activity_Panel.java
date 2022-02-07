package roman.game.myshiftmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import roman.game.myshiftmanager.Fragments.Panel.Fragment_Calendar;
import roman.game.myshiftmanager.Fragments.Panel.Fragment_Profile;
import roman.game.myshiftmanager.Fragments.Panel.Fragment_Reports;
import roman.game.myshiftmanager.Fragments.Panel.Fragment_Settings;

import roman.game.myshiftmanager.Managers.BottomMenuManager;
import roman.game.myshiftmanager.R;

public class Activity_Panel extends AppCompatActivity {
    public final int PROFILE = BottomMenuManager.PROFILE, CALENDAR = BottomMenuManager.CALENDAR,
            REPORTS = BottomMenuManager.REPORTS, SETTINGS = BottomMenuManager.SETTINGS, SIZE = BottomMenuManager.SIZE;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private Fragment[] panel_fragments;
    private MaterialToolbar panel_TLB_toolbar;
    private MaterialButton[] panel_BTN_bottomMenu;

    private BottomMenuManager bottomMenuManager;

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

        bottomMenuManager = BottomMenuManager.getInstance();
        bottomMenuManager.onStartView(panel_BTN_bottomMenu, panel_TLB_toolbar);
        replaceFragments(panel_fragments[PROFILE]);

        OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = PROFILE;
                try {
                    pos = (Integer)v.getTag();
                    Log.d("pttt", "pos:" + pos);
                }catch (Exception e){
                    return;
                }
                bottomMenuManager.onClickView(pos, panel_BTN_bottomMenu, panel_TLB_toolbar);
                replaceFragments(panel_fragments[pos]);
            }
        };

        panel_BTN_bottomMenu[PROFILE].setOnClickListener(onClickListener);
        panel_BTN_bottomMenu[CALENDAR].setOnClickListener(onClickListener);
        panel_BTN_bottomMenu[REPORTS].setOnClickListener(onClickListener);
        panel_BTN_bottomMenu[SETTINGS].setOnClickListener(onClickListener);
    }

    private void setFragments() {
        panel_fragments = new Fragment[SIZE];
        panel_fragments[PROFILE] = new Fragment_Profile().setActivity(this);
        panel_fragments[CALENDAR] = new Fragment_Calendar().setActivity(this);
        panel_fragments[REPORTS] = new Fragment_Reports().setActivity(this);
        panel_fragments[SETTINGS] = new Fragment_Settings().setActivity(this);
    }

    private void replaceFragments(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.panel_fragment_view, fragment, null);
        fragmentTransaction.commit();
    }

    private void findViews() {
        panel_TLB_toolbar = findViewById(R.id.panel_TLB_toolbar);

        panel_BTN_bottomMenu = new MaterialButton[SIZE];
        panel_BTN_bottomMenu[PROFILE] = findViewById(R.id.panel_BTN_profile);
        panel_BTN_bottomMenu[PROFILE].setTag(new Integer(PROFILE));
        panel_BTN_bottomMenu[CALENDAR] = findViewById(R.id.panel_BTN_calendar);
        panel_BTN_bottomMenu[CALENDAR].setTag(new Integer(CALENDAR));
        panel_BTN_bottomMenu[REPORTS] = findViewById(R.id.panel_BTN_reports);
        panel_BTN_bottomMenu[REPORTS].setTag(new Integer(REPORTS));
        panel_BTN_bottomMenu[SETTINGS] = findViewById(R.id.panel_BTN_settings);
        panel_BTN_bottomMenu[SETTINGS].setTag(new Integer(SETTINGS));
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