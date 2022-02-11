package roman.game.myshiftmanager.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import roman.game.myshiftmanager.R;
import roman.game.myshiftmanager.UserData.UserDataManager;

public class Activity_MakeWorkplace extends AppCompatActivity {

    private MaterialButton workplace_BTN_back, workplace_BTN_save;
    private TextInputEditText workplace_textInputEditText_name, workplace_textInputEditText_hourly,workplace_textInputEditText_vacation
            ,workplace_textInputEditText_deduction, workplace_textInputEditText_bonuses, workplace_textInputEditText_break,
            workplace_textInputEditText_daily, workplace_textInputEditText_monthly;
    private RadioGroup workplace_RG_color;
    private ShapeableImageView workplace_IMG_blue, workplace_IMG_green, workplace_IMG_red, workplace_IMG_purple;

    private UserDataManager userDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_workplace);

        findViews();
        userDataManager = UserDataManager.getInstance();
        
        workplace_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        workplace_BTN_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeNewWorkplace();
            }
        });

        // TODO: 09/02/2022 - edt input 

        // TODO: 09/02/2022 - radio group for picking color 
    }

    private void makeNewWorkplace(){
        String name = workplace_textInputEditText_name.getText().toString().trim();
        // TODO: 11/02/2022 - can improve the checks on tf if has time later on...
        if (name.isEmpty()) {
            workplace_textInputEditText_name.setError("Required field");
            workplace_textInputEditText_name.requestFocus();
            return;
        }
        String hourlyWage = workplace_textInputEditText_hourly.getText().toString().trim();
        String vacationPayments = workplace_textInputEditText_vacation.getText().toString().trim();
        String deductionPerShift= workplace_textInputEditText_deduction.getText().toString().trim();
        String bonusesPerShift= workplace_textInputEditText_bonuses.getText().toString().trim();
        String breakPerShiftUnpaid = workplace_textInputEditText_break.getText().toString().trim(); // int
        String dailyTravelExpenses = workplace_textInputEditText_daily.getText().toString().trim();
        String monthlyTravelExpenses = workplace_textInputEditText_monthly.getText().toString().trim();
        int color = 0; // FIXME: 11/02/2022 - need to implement the color picker... 

        userDataManager.addWorkplace(name, hourlyWage, vacationPayments, deductionPerShift, bonusesPerShift, breakPerShiftUnpaid,
                dailyTravelExpenses, monthlyTravelExpenses, color);
        finish();
    }

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
        workplace_RG_color = findViewById(R.id.workplace_RG_color);
        workplace_IMG_blue = findViewById(R.id.workplace_IMG_blue);
        workplace_IMG_green = findViewById(R.id.workplace_IMG_green);
        workplace_IMG_red = findViewById(R.id.workplace_IMG_red);
        workplace_IMG_purple = findViewById(R.id.workplace_IMG_purple);
    }
}