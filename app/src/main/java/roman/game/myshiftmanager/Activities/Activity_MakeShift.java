package roman.game.myshiftmanager.Activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

import roman.game.myshiftmanager.Managers.DateTimeDialogManager;
import roman.game.myshiftmanager.Objects.Shift;
import roman.game.myshiftmanager.Objects.User;
import roman.game.myshiftmanager.Objects.Workplace;
import roman.game.myshiftmanager.R;
import roman.game.myshiftmanager.UserData.UserDataManager;

public class Activity_MakeShift extends AppCompatActivity {

    private MaterialButton shift_BTN_back, shift_BTN_create, shift_BTN_switch, shift_BTN_delete;
    private MaterialTextView shift_LBL_total_time, shift_LBL_workplace_name;
    private TextInputEditText shift_textInputEditText_from, shift_textInputEditText_to;
    private CardView shift_cardView_delete;

    private DateTimeDialogManager dateTimeDialogManager;
    private UserDataManager userDataManager;
    private ArrayList<Workplace> workplaces;
    private int workplacePos;
    private boolean newShift;
    private Shift shift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_shift);

        newShift = true;

        try {
            shift = (Shift) getIntent().getSerializableExtra("shifts");
            if(shift != null)
                newShift = false;
        } catch (Exception e){
        }

        findViews();
        dateTimeDialogManager = DateTimeDialogManager.getInstance();
        userDataManager = UserDataManager.getInstance();
        workplaces = userDataManager.getWorkplaces();

        if(workplaces.size() > 0){
            setWorkplaces();
        }

        if (!newShift){
            setShiftData();
        }

        // TODO: 11/02/2022 - calculate time

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
                setWorkplaces();
            }
        });
        
        shift_BTN_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 09/02/2022 - delete the shift from db 
            }
        });
    }

    private void setShiftData() {
        // TODO: 11/02/2022  setWorkplaces() and change the pos + set the shift data.
        shift_cardView_delete.setVisibility(View.VISIBLE);
    }

    private void setWorkplaces() {
        if (workplacePos >= workplaces.size()) {
            workplacePos = 0;
        }
        shift_LBL_workplace_name.setText(workplaces.get(workplacePos).getName());
        workplacePos++;
        // TODO: 11/02/2022 - color
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
        shift_cardView_delete = findViewById(R.id.shift_cardView_delete);
    }
}