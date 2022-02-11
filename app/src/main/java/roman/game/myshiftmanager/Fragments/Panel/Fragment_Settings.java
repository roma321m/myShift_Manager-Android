package roman.game.myshiftmanager.Fragments.Panel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import roman.game.myshiftmanager.Activities.Activity_MakeWorkplace;
import roman.game.myshiftmanager.Dialog.ViewDialog_Workplaces;
import roman.game.myshiftmanager.Objects.User;
import roman.game.myshiftmanager.Objects.Workplace;
import roman.game.myshiftmanager.R;
import roman.game.myshiftmanager.UserData.UserDataManager;

public class Fragment_Settings extends Fragment {

    private AppCompatActivity activity;

    private AutoCompleteTextView settings_autoCompleteTextView_currency;
    private AutoCompleteTextView settings_autoCompleteTextView_time;
    private AutoCompleteTextView settings_autoCompleteTextView_date;
    private TextInputEditText settings_textInputEditText_workplaces;
    private MaterialButton settings_BTN_save;

    private UserDataManager userDataManager;
    private int currency, dateFormat, timeFormat;

    public interface Callback_ViewDialogWorkplaces {
        void workplaceSelected(int pos);
        void newWorkplaceSelected();
    }

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

        userDataManager = UserDataManager.getInstance();
        setData();
        setOnStartSelected();

        settings_textInputEditText_workplaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userDataManager.getWorkplaces().size() > 0) {
                    ViewDialog_Workplaces dialog = new ViewDialog_Workplaces();
                    dialog.showDialog(activity, userDataManager.getWorkplaces(), callback_viewDialogWorkplaces);
                } else {
                    openActivity(Activity_MakeWorkplace.class);
                }
            }
        });
        
        settings_BTN_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 10/02/2022 - check if one of the setting different from the user data, if so, update user data and db... 
            }
        });

        settings_autoCompleteTextView_currency.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currency = position;
            }
        });

        settings_autoCompleteTextView_time.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                timeFormat = position;
            }
        });

        settings_autoCompleteTextView_date.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dateFormat = position;
            }
        });

        return view;
    }

    private void setOnStartSelected() {
        // FIXME: 10/02/2022 - not working...
        /*
        settings_autoCompleteTextView_currency.setSelection(currency);
        settings_autoCompleteTextView_time.setSelection(timeFormat);
        settings_autoCompleteTextView_date.setSelection(dateFormat);*/
    }

    private void setData() {
        User user = userDataManager.getMyUser();
        currency = user.getCurrency();
        dateFormat = user.getDateFormat();
        timeFormat = user.getTimeFormat();
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

    Callback_ViewDialogWorkplaces callback_viewDialogWorkplaces = new Callback_ViewDialogWorkplaces() {
        @Override
        public void workplaceSelected(int pos) {
            Workplace workplace = userDataManager.getWorkplaces().get(pos);
            Intent intent = new Intent(activity, Activity_MakeWorkplace.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.putExtra("workplace", workplace);
            startActivity(intent);
        }

        @Override
        public void newWorkplaceSelected() {
            openActivity(Activity_MakeWorkplace.class);
        }
    };

    private void findViews(View view) {
        settings_textInputEditText_workplaces = view.findViewById(R.id.settings_textInputEditText_workplaces);
        settings_autoCompleteTextView_currency = view.findViewById(R.id.settings_autoCompleteTextView_currency);
        settings_autoCompleteTextView_time = view.findViewById(R.id.settings_autoCompleteTextView_time);
        settings_autoCompleteTextView_date = view.findViewById(R.id.settings_autoCompleteTextView_date);
        settings_BTN_save = view.findViewById(R.id.settings_BTN_save);
    }
}
