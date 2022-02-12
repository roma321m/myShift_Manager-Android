package roman.game.myshiftmanager.Fragments.Panel;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import roman.game.myshiftmanager.Adapters.Adapter_Shift;
import roman.game.myshiftmanager.Objects.Shift;
import roman.game.myshiftmanager.Objects.Workplace;
import roman.game.myshiftmanager.R;
import roman.game.myshiftmanager.UserData.UserDataManager;

public class Fragment_Calendar extends Fragment {

    private AppCompatActivity activity;

    private RecyclerView calendar_LST_shifts;
    private LinearLayoutManager linearLayoutManager;
    private CalendarView calendar_calendar;

    private UserDataManager userDataManager;

    public Fragment_Calendar() {
    }

    ;

    public Fragment_Calendar setActivity(AppCompatActivity activity) {
        this.activity = activity;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        findViews(view);

        userDataManager = UserDataManager.getInstance();

        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        calendar_LST_shifts.setLayoutManager(linearLayoutManager);
        calendar_LST_shifts.setHasFixedSize(true);
        calendar_LST_shifts.setItemAnimator(new DefaultItemAnimator());

        calendar_calendar.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Date d = eventDay.getCalendar().getTime();
                String str = d.toString();
                str = str.substring(8, 10);
                int year = d.getYear() + 1900;
                int month = d.getMonth() + 1;
                int dayofmonth = Integer.parseInt(str);

                ArrayList<Shift> shifts = getShifts(year, month, dayofmonth);
                setShifts(shifts);
            }
        });

        setEvents();

        ArrayList<Shift> shifts = getShifts();
        setShifts(shifts);

        return view;
    }

    private void setEvents(){
        List<EventDay> events = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        ArrayList<Shift> shifts = userDataManager.getShifts();
        ArrayList<Workplace> workplaces = userDataManager.getWorkplaces();

        for(Shift s: shifts){
            calendar.set(s.getStartYear(), s.getStartMonth(), s.getStartDayOfMonth());
            String color = workplaces.get(Integer.parseInt(s.getWorkplaceID())-1).getColor();
            events.add(new EventDay(calendar, R.drawable.ic_dot, Color.parseColor(color)));
            // FIXME: 12/02/2022 - not working here
        }
        calendar_calendar.setEvents(events);
    }

    private ArrayList<Shift> getShifts(int year, int month, int dayOfMonth) {
        ArrayList<Shift> shifts = userDataManager.getShifts();
        ArrayList<Shift> show = new ArrayList<>();
        for (Shift s : shifts) {
            if (s.getStartYear() == year && s.getStartMonth() == month && s.getStartDayOfMonth() == dayOfMonth) {
                show.add(s);
            }
        }
        return show;
    }

    private ArrayList<Shift> getShifts() {
        LocalDate selectedDate = LocalDate.now();
        ArrayList<Shift> shifts = userDataManager.getShifts();
        ArrayList<Shift> show = new ArrayList<>();
        for (Shift s : shifts) {
            if (s.getStartYear() == selectedDate.getYear() && s.getStartMonth() == selectedDate.getMonthValue()
                    && s.getStartDayOfMonth() == selectedDate.getDayOfMonth()) {
                show.add(s);
            }
        }
        return show;
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
