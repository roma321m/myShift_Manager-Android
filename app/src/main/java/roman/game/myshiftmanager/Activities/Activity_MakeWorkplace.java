package roman.game.myshiftmanager.Activities;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import roman.game.myshiftmanager.Dialog.ViewDialog_ColorPicker;
import roman.game.myshiftmanager.Objects.Workplace;
import roman.game.myshiftmanager.R;
import roman.game.myshiftmanager.UserData.UserDataManager;

public class Activity_MakeWorkplace extends AppCompatActivity {

    public interface Callback_ViewDialogColorPicker {
        void done(String rgb);
    }

    private MaterialButton workplace_BTN_back, workplace_BTN_save;
    private TextInputEditText workplace_textInputEditText_name, workplace_textInputEditText_hourly, workplace_textInputEditText_vacation, workplace_textInputEditText_deduction, workplace_textInputEditText_bonuses, workplace_textInputEditText_break,
            workplace_textInputEditText_daily, workplace_textInputEditText_monthly, workplace_textInputEditText_color;

    private UserDataManager userDataManager;
    private boolean newWorkplace;
    private Workplace workplace;
    private String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_workplace);

        newWorkplace = true;

        try {
            workplace = (Workplace) getIntent().getSerializableExtra("workplace");
            if (workplace != null)
                newWorkplace = false;
        } catch (Exception e) {
        }

        findViews();
        userDataManager = UserDataManager.getInstance();

        if (!newWorkplace) {
            setWorkplaceData();
            setKeyListener();
        }

        workplace_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        workplace_BTN_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newWorkplace) {
                    makeNewWorkplace();
                } else {
                    // TODO: 11/02/2022 - make edit to the db. 
                }
            }
        });

        workplace_textInputEditText_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialog_ColorPicker dialog = new ViewDialog_ColorPicker();
                dialog.showDialog(Activity_MakeWorkplace.this, callback_viewDialogColorPicker);
            }
        });
    }

    private void setKeyListener() {
        View.OnKeyListener onKeyListener = new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                workplace_BTN_save.setVisibility(View.VISIBLE);
                return false;
            }
        };
        workplace_textInputEditText_name.setOnKeyListener(onKeyListener);
        workplace_textInputEditText_hourly.setOnKeyListener(onKeyListener);
        workplace_textInputEditText_vacation.setOnKeyListener(onKeyListener);
        workplace_textInputEditText_deduction.setOnKeyListener(onKeyListener);
        workplace_textInputEditText_bonuses.setOnKeyListener(onKeyListener);
        workplace_textInputEditText_break.setOnKeyListener(onKeyListener);
        workplace_textInputEditText_daily.setOnKeyListener(onKeyListener);
        workplace_textInputEditText_monthly.setOnKeyListener(onKeyListener);
    }

    private void setWorkplaceData() {
        workplace_textInputEditText_name.setText(workplace.getName());
        workplace_textInputEditText_hourly.setText("" + workplace.getHourlyWage());
        workplace_textInputEditText_vacation.setText("" + workplace.getVacationPayments());
        workplace_textInputEditText_deduction.setText("" + workplace.getDeductionPerShift());
        workplace_textInputEditText_bonuses.setText("" + workplace.getBonusesPerShift());
        workplace_textInputEditText_break.setText("" + workplace.getBreakTimeUnpaid());
        workplace_textInputEditText_daily.setText("" + workplace.getDailyTravelExpenses());
        workplace_textInputEditText_monthly.setText("" + workplace.getMonthlyTravelExpenses());
        workplace_BTN_save.setVisibility(View.INVISIBLE);
    }

    private void makeNewWorkplace() {
        String name = workplace_textInputEditText_name.getText().toString().trim();
        if (name.isEmpty()) {
            workplace_textInputEditText_name.setError("Required field");
            workplace_textInputEditText_name.requestFocus();
            return;
        }
        String hourlyWage = workplace_textInputEditText_hourly.getText().toString().trim();
        String vacationPayments = workplace_textInputEditText_vacation.getText().toString().trim();
        String deductionPerShift = workplace_textInputEditText_deduction.getText().toString().trim();
        String bonusesPerShift = workplace_textInputEditText_bonuses.getText().toString().trim();
        String breakPerShiftUnpaid = workplace_textInputEditText_break.getText().toString().trim(); // int
        String dailyTravelExpenses = workplace_textInputEditText_daily.getText().toString().trim();
        String monthlyTravelExpenses = workplace_textInputEditText_monthly.getText().toString().trim();
        if (color == null){
            color = "#0000FF";
        }

        userDataManager.addWorkplace(name, hourlyWage, vacationPayments, deductionPerShift, bonusesPerShift, breakPerShiftUnpaid,
                dailyTravelExpenses, monthlyTravelExpenses, color);
        finish();
    }

    Callback_ViewDialogColorPicker callback_viewDialogColorPicker = new Callback_ViewDialogColorPicker() {
        @Override
        public void done(String rgb) {
            color = rgb;
        }
    };

    private void findViews() {
        workplace_BTN_back = findViewById(R.id.workplace_BTN_back);
        workplace_BTN_save = findViewById(R.id.workplace_BTN_save);
        workplace_textInputEditText_name = findViewById(R.id.workplace_textInputEditText_name);
        workplace_textInputEditText_hourly = findViewById(R.id.workplace_textInputEditText_hourly);
        workplace_textInputEditText_vacation = findViewById(R.id.workplace_textInputEditText_vacation);
        workplace_textInputEditText_deduction = findViewById(R.id.workplace_textInputEditText_deduction);
        workplace_textInputEditText_bonuses = findViewById(R.id.workplace_textInputEditText_bonuses);
        workplace_textInputEditText_break = findViewById(R.id.workplace_textInputEditText_break);
        workplace_textInputEditText_daily = findViewById(R.id.workplace_textInputEditText_daily);
        workplace_textInputEditText_monthly = findViewById(R.id.workplace_textInputEditText_monthly);
        workplace_textInputEditText_color = findViewById(R.id.workplace_textInputEditText_color);
    }
}