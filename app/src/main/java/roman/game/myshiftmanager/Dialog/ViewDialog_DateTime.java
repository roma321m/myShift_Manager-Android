package roman.game.myshiftmanager.Dialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import roman.game.myshiftmanager.Activities.Activity_MakeShift;

public class ViewDialog_DateTime {

    private static ViewDialog_DateTime single_instance = null;

    private Calendar calendar;
    private int year;
    private int month;
    private int day;

    private ViewDialog_DateTime() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static ViewDialog_DateTime getInstance() {
        if (single_instance == null) {
            single_instance = new ViewDialog_DateTime();
        }
        return single_instance;
    }

    public void setDateTimeDialog(AppCompatActivity activity, Activity_MakeShift.Callback_ViewDialogDateTime callback_viewDialogDateTime,
                                  TextInputEditText textInputEditText, int[] dateTime){
        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = String.format("  %02d:%02d",hourOfDay, minute);
                        textInputEditText.setText(textInputEditText.getText() + time);
                        dateTime[3] = hourOfDay;
                        dateTime[4] = minute;
                        callback_viewDialogDateTime.done();
                    }
                },0,0,true);
                timePickerDialog.show();

                month = month + 1;
                // TODO: 12/02/2022 - base on format from user data
                String date = String.format("%02d/%02d/%04d" , day, month, year);

                dateTime[0] = year;
                dateTime[1] = month;
                dateTime[2] = day;
                textInputEditText.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();
    }
}
