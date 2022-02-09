package roman.game.myshiftmanager.Activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import roman.game.myshiftmanager.Managers.DateTimeDialogManager;
import roman.game.myshiftmanager.R;

public class Activity_MakeShift extends AppCompatActivity {

    private MaterialButton shift_BTN_back, shift_BTN_create, shift_BTN_switch, shift_BTN_delete;
    private MaterialTextView shift_LBL_total_time, shift_LBL_workplace_name;
    private TextInputEditText shift_textInputEditText_from, shift_textInputEditText_to;

    private DateTimeDialogManager dateTimeDialogManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_shift);

        findViews();
        dateTimeDialogManager = DateTimeDialogManager.getInstance();

        shift_textInputEditText_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateTimeDialogManager.setDateTimeDialog(Activity_MakeShift.this, shift_textInputEditText_from);
            }
        });

        shift_textInputEditText_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateTimeDialogManager.setDateTimeDialog(Activity_MakeShift.this, shift_textInputEditText_to);
            }
        });

        shift_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        shift_BTN_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 09/02/2022 - create new shift for the user
            }
        });
        
        shift_BTN_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 09/02/2022 - switch between workplaces in a loop.
            }
        });

        // FIXME: 09/02/2022 - need to show the delete card only if came from an existing shift.
        
        shift_BTN_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 09/02/2022 - delete the shift from db 
            }
        });
    }

    private void findViews() {
        shift_BTN_back = findViewById(R.id.shift_BTN_back);
        shift_BTN_create = findViewById(R.id.shift_BTN_create);
        shift_BTN_switch = findViewById(R.id.shift_BTN_switch);
        shift_BTN_delete = findViewById(R.id.shift_BTN_delete);
        shift_LBL_total_time = findViewById(R.id.shift_LBL_total_time);
        shift_LBL_workplace_name = findViewById(R.id.shift_LBL_workplace_name);
        shift_textInputEditText_from = findViewById(R.id.shift_textInputEditText_from);
        shift_textInputEditText_to = findViewById(R.id.shift_textInputEditText_to);
    }
}