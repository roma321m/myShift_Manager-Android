package roman.game.myshiftmanager.Managers;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class DateTimeDialogManager {

    private static DateTimeDialogManager single_instance = null;

    private Calendar calendar;
    private int year;
    private int month;
    private int day;

    private DateTimeDialogManager() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static DateTimeDialogManager getInstance() {
        if (single_instance == null) {
            single_instance = new DateTimeDialogManager();
        }
        return single_instance;
    }

    public void setDateTimeDialog(AppCompatActivity activity, TextInputEditText textInputEditText){
        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = "  " + hourOfDay + ":" + minute;
                        textInputEditText.setText(textInputEditText.getText() + time);
                        // TODO: 09/02/2022 - callback when the data ready with it for the activity that is using this. 
                    }
                },0,0,true);
                timePickerDialog.show();

                month = month + 1;
                String date = day+"/"+month+"/"+year;
                textInputEditText.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();
    }
}
