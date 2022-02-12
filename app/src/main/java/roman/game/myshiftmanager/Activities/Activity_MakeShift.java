package roman.game.myshiftmanager.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import roman.game.myshiftmanager.Dialog.ViewDialog_DateTime;
import roman.game.myshiftmanager.Objects.Shift;
import roman.game.myshiftmanager.R;
import roman.game.myshiftmanager.UserData.UserDataManager;

public class Activity_MakeShift extends AppCompatActivity {

    public interface Callback_ViewDialogDateTime {
        void done();
    }

    private MaterialButton shift_BTN_back, shift_BTN_create, shift_BTN_switch, shift_BTN_delete;
    private MaterialTextView shift_LBL_total_time, shift_LBL_workplace_name;
    private TextInputEditText shift_textInputEditText_from, shift_textInputEditText_to;
    private CardView shift_cardView_delete;
    private ShapeableImageView shift_IMG_color;

    private ViewDialog_DateTime viewDialogDateTime;
    private UserDataManager userDataManager;
    private int workplacePos;
    private boolean newShift;
    private Shift shift;
    private int[] from, to; // [year, month, day, hourOfDay, minute]
    private Calendar calendarFrom, calendarTo;
    private long milliseconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_shift);

        newShift = true;
        try {
            shift = (Shift) getIntent().getSerializableExtra("shifts");
            if (shift != null)
                newShift = false;
        } catch (Exception e) {
        }
        viewDialogDateTime = ViewDialog_DateTime.getInstance();
        userDataManager = UserDataManager.getInstance();
        from = new int[]{0, 0, 0, 0, 0};
        to = new int[]{0, 0, 0, 0, 0};

        findViews();
        shift_LBL_total_time.setVisibility(View.INVISIBLE);

        if (userDataManager.getWorkplaces().size() > 0) {
            setWorkplaces();
        }

        if (!newShift) {
            setShiftData();
        }

        shift_textInputEditText_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDialogDateTime.setDateTimeDialog(Activity_MakeShift.this, callback_viewDialogDateTime, shift_textInputEditText_from, from);
            }
        });

        shift_textInputEditText_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDialogDateTime.setDateTimeDialog(Activity_MakeShift.this, callback_viewDialogDateTime, shift_textInputEditText_to, to);
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
                if(milliseconds > 0){
                    userDataManager.addShift(workplacePos, calendarFrom, calendarTo);
                    finish();
                }
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
        if (workplacePos >= userDataManager.getWorkplaces().size()) {
            workplacePos = 0;
        }
        shift_LBL_workplace_name.setText(userDataManager.getWorkplaces().get(workplacePos).getName());
        try {
            String hex = userDataManager.getWorkplaces().get(workplacePos).getColor();
            int a = Integer.valueOf(hex.substring(1, 3), 16);
            int r = Integer.valueOf(hex.substring(3, 5), 16);
            int g = Integer.valueOf(hex.substring(5, 7), 16);
            int b = Integer.parseInt(hex.substring(7, 9), 16);
            shift_IMG_color.setBackgroundColor(Color.argb(a, r, g, b));
        } catch (Exception e) {

        }
        workplacePos++;
    }

    Callback_ViewDialogDateTime callback_viewDialogDateTime = new Callback_ViewDialogDateTime() {
        @Override
        public void done() {
            if (checkDateTime()) {
                milliseconds = getTotalTime();
                if (milliseconds >= 0) {
                    shift_LBL_total_time.setVisibility(View.VISIBLE);
                    String timeInHHMM = Shift.getTimeInHHMM(milliseconds);
                    shift_LBL_total_time.setText(timeInHHMM);
                }
            }
        }
    };

    private boolean checkDateTime() {
        for (int i =0;i>3;i++) {
            if (from[i] <= 0 || to[i] <= 0)
                return false;
        }
        return true;
    }

    private long getTotalTime() {
        calendarFrom = new GregorianCalendar(from[0], from[1], from[2], from[3], from[4]);
        calendarTo = new GregorianCalendar(to[0], to[1], to[2], to[3], to[4]);
        return calendarTo.getTime().getTime() - calendarFrom.getTime().getTime();
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
        shift_IMG_color = findViewById(R.id.shift_IMG_color);
    }
}