package roman.game.myshiftmanager.Fragments.Panel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import roman.game.myshiftmanager.Activities.Activity_MakeWorkplace;
import roman.game.myshiftmanager.Dialog.ViewDialog_List;
import roman.game.myshiftmanager.Objects.User;
import roman.game.myshiftmanager.Objects.Workplace;
import roman.game.myshiftmanager.R;
import roman.game.myshiftmanager.UserData.UserDataManager;

public class Fragment_Settings extends Fragment {

    private AppCompatActivity activity;

    private TextInputEditText settings_textInputEditText_currency;
    private TextInputEditText settings_textInputEditText_time;
    private TextInputEditText settings_textInputEditText_date;
    private TextInputEditText settings_textInputEditText_workplaces;
    private MaterialButton settings_BTN_save;

    private UserDataManager userDataManager;

    public Fragment_Settings() {
    }

    public Fragment_Settings setActivity(AppCompatActivity activity) {
        this.activity = activity;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        userDataManager = UserDataManager.getInstance();

        findViews(view);
        setData();

        settings_textInputEditText_workplaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userDataManager.getWorkplaces().size() > 0) {
                    ArrayList<String> list = new ArrayList<>();
                    for (Workplace w : userDataManager.getWorkplaces()) {
                        list.add(w.getName());
                    }
                    list.add("Add new workplace");

                    ViewDialog_List dialog = new ViewDialog_List();
                    dialog.showDialog(activity, "Workplaces", list, callback_viewDialogWorkplaces);

                } else {
                    openActivity(Activity_MakeWorkplace.class);
                }
            }
        });

        settings_textInputEditText_currency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialog_List dialog = new ViewDialog_List();
                dialog.showDialog(activity, "Currency", UserDataManager.currencyList, callback_viewDialogCurrency);
            }
        });

        settings_textInputEditText_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialog_List dialog = new ViewDialog_List();
                dialog.showDialog(activity, "Time Format", UserDataManager.timeFormatList, callback_viewDialogTimeFormat);
            }
        });

        settings_textInputEditText_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialog_List dialog = new ViewDialog_List();
                dialog.showDialog(activity, "Date Format", UserDataManager.dateFormatList, callback_viewDialogDateFormat);
            }
        });

        settings_BTN_save.setVisibility(View.INVISIBLE); // temp
        settings_BTN_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 10/02/2022 - add option for name change and save it to db..
            }
        });

        return view;
    }

    private void setData() {
        User user = userDataManager.getMyUser();
        settings_textInputEditText_currency.setText(UserDataManager.currencyList.get(user.getCurrency()));
        settings_textInputEditText_time.setText(UserDataManager.timeFormatList.get(user.getTimeFormat()));
        settings_textInputEditText_date.setText(UserDataManager.dateFormatList.get(user.getDateFormat()));
    }

    private void openActivity(Class activity) {
        Intent intent = new Intent(this.activity, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    ViewDialog_List.Callback_ViewDialog callback_viewDialogWorkplaces = new ViewDialog_List.Callback_ViewDialog() {
        @Override
        public void itemClicked(int pos) {
            if (pos == userDataManager.getWorkplaces().size()){
                openActivity(Activity_MakeWorkplace.class);
            } else {
                Workplace workplace = userDataManager.getWorkplaces().get(pos);
                Intent intent = new Intent(activity, Activity_MakeWorkplace.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("workplace", workplace);
                startActivity(intent);
            }
        }
    };

    ViewDialog_List.Callback_ViewDialog callback_viewDialogCurrency = new ViewDialog_List.Callback_ViewDialog() {
        @Override
        public void itemClicked(int pos) {
            settings_textInputEditText_currency.setText(UserDataManager.currencyList.get(pos));
            userDataManager.updateCurrency(pos);
        }
    };

    ViewDialog_List.Callback_ViewDialog callback_viewDialogTimeFormat = new ViewDialog_List.Callback_ViewDialog() {
        @Override
        public void itemClicked(int pos) {
            settings_textInputEditText_time.setText(UserDataManager.timeFormatList.get(pos));
            userDataManager.updateTimeFormat(pos);
        }
    };

    ViewDialog_List.Callback_ViewDialog callback_viewDialogDateFormat = new ViewDialog_List.Callback_ViewDialog() {
        @Override
        public void itemClicked(int pos) {
            settings_textInputEditText_date.setText(UserDataManager.dateFormatList.get(pos));
            userDataManager.updateDateFormat(pos);
        }
    };

    private void findViews(View view) {
        settings_textInputEditText_workplaces = view.findViewById(R.id.settings_textInputEditText_workplaces);
        settings_textInputEditText_currency = view.findViewById(R.id.settings_textInputEditText_currency);
        settings_textInputEditText_time = view.findViewById(R.id.settings_textInputEditText_time);
        settings_textInputEditText_date = view.findViewById(R.id.settings_textInputEditText_date);
        settings_BTN_save = view.findViewById(R.id.settings_BTN_save);
    }
}
