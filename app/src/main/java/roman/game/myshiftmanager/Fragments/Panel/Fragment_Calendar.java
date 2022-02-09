package roman.game.myshiftmanager.Fragments.Panel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import roman.game.myshiftmanager.Adapters.Adapter_Shift;
import roman.game.myshiftmanager.Objects.Shift;
import roman.game.myshiftmanager.Objects.Workplace;
import roman.game.myshiftmanager.R;

public class Fragment_Calendar extends Fragment {

    private AppCompatActivity activity;

    private RecyclerView calendar_LST_shifts;
    private LinearLayoutManager linearLayoutManager;
    private CalendarView calendar_calendar;

    public Fragment_Calendar(){};

    public Fragment_Calendar setActivity(AppCompatActivity activity) {
        this.activity = activity;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        findViews(view);

        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        calendar_LST_shifts.setLayoutManager(linearLayoutManager);
        calendar_LST_shifts.setHasFixedSize(true);
        calendar_LST_shifts.setItemAnimator(new DefaultItemAnimator());

        calendar_calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // TODO: 09/02/2022 change the list of shifts base on the day
                String date = dayOfMonth + "-" + (month+1) + "-" + year;
                Toast.makeText(activity, date, Toast.LENGTH_SHORT).show();
            }
        });

        // UI test
        ArrayList<Shift> shifts = new ArrayList<>();
        Date d = Calendar.getInstance().getTime();
        Workplace w = new Workplace(Workplace.GREEN, "text work place", 10);

        Shift s1 = new Shift(d, d, w);
        Shift s2 = new Shift(d, d, w);
        Shift s3 = new Shift(d, d, w);
        Shift s4 = new Shift(d, d, w);
        Shift s5 = new Shift(d, d, w);
        shifts.add(s1);
        shifts.add(s2);
        shifts.add(s3);
        shifts.add(s4);
        shifts.add(s5);
        shifts.add(s5);
        shifts.add(s5);

        setShifts(shifts);

        return view;
    }

    public void setShifts(ArrayList<Shift> shifts) {
        Adapter_Shift adapter_shift = new Adapter_Shift(activity, shifts);
        calendar_LST_shifts.setAdapter(adapter_shift);

        adapter_shift.setShiftItemClickListener(new Adapter_Shift.ShiftItemClickListener() {
            @Override
            public void editClicked(Shift shift) {
                // TODO: 08/02/2022 - open the shift to edit it
            }

            @Override
            public void deleteClicked(Shift shift) {
                // TODO: 08/02/2022 - popup for conformation
            }
        });
    }

    private void findViews(View view) {
        calendar_LST_shifts = view.findViewById(R.id.calendar_LST_shifts);
        calendar_calendar = view.findViewById(R.id.calendar_calendar);
    }
}
