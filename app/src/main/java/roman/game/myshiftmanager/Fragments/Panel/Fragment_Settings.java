package roman.game.myshiftmanager.Fragments.Panel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import roman.game.myshiftmanager.Activities.Activity_MakeWorkplace;
import roman.game.myshiftmanager.R;

public class Fragment_Settings extends Fragment {

    private AppCompatActivity activity;

    private AutoCompleteTextView settings_autoCompleteTextView_currency;
    private AutoCompleteTextView settings_autoCompleteTextView_time;
    private AutoCompleteTextView settings_autoCompleteTextView_date;
    private MaterialButton settings_BTN_add, settings_BTN_currency, settings_BTN_time, settings_BTN_date;

    public Fragment_Settings() {
    }

    public Fragment_Settings setActivity(AppCompatActivity activity) {
        this.activity = activity;
        return this;
    }

    @Override
    public void onResume() {
        super.onResume();

        setDropdown();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        findViews(view);

        setDropdown();

        settings_BTN_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(Activity_MakeWorkplace.class);
            }
        });

        settings_BTN_currency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 08/02/2022 - save the changes to db
                Toast.makeText(activity, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        settings_BTN_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 08/02/2022 - save the changes to db
                Toast.makeText(activity, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        settings_BTN_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 08/02/2022 - save the changes to db
                Toast.makeText(activity, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        settings_autoCompleteTextView_currency.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: 08/02/2022 - open save button
                Toast.makeText(activity, "item" + position, Toast.LENGTH_SHORT).show();
            }
        });

        settings_autoCompleteTextView_time.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: 08/02/2022 - open save button
                Toast.makeText(activity, "item" + position, Toast.LENGTH_SHORT).show();
            }
        });

        settings_autoCompleteTextView_date.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: 08/02/2022 - open save button
                Toast.makeText(activity, "item" + position, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void openActivity(Class activity) {
        Intent intent = new Intent(this.activity, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    private void setDropdown() {
        String[] currency = getResources().getStringArray(R.array.currency);
        ArrayAdapter arrayAdapterCurrency = new ArrayAdapter(activity, R.layout.dropdown_item, currency);
        settings_autoCompleteTextView_currency.setAdapter(arrayAdapterCurrency);

        String[] time = getResources().getStringArray(R.array.time);
        ArrayAdapter arrayAdapterTime = new ArrayAdapter(activity, R.layout.dropdown_item, time);
        settings_autoCompleteTextView_time.setAdapter(arrayAdapterTime);

        String[] date = getResources().getStringArray(R.array.date);
        ArrayAdapter arrayAdapterDate = new ArrayAdapter(activity, R.layout.dropdown_item, date);
        settings_autoCompleteTextView_date.setAdapter(arrayAdapterDate);
    }

    private void findViews(View view) {
        settings_BTN_add = view.findViewById(R.id.settings_BTN_add);
        settings_BTN_currency = view.findViewById(R.id.settings_BTN_currency);
        settings_BTN_time = view.findViewById(R.id.settings_BTN_time);
        settings_BTN_date = view.findViewById(R.id.settings_BTN_date);
        settings_autoCompleteTextView_currency = view.findViewById(R.id.settings_autoCompleteTextView_currency);
        settings_autoCompleteTextView_time = view.findViewById(R.id.settings_autoCompleteTextView_time);
        settings_autoCompleteTextView_date = view.findViewById(R.id.settings_autoCompleteTextView_date);
    }
}
