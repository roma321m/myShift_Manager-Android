package roman.game.myshiftmanager.Fragments.Panel;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import roman.game.myshiftmanager.Adapters.Adapter_Shift;
import roman.game.myshiftmanager.Objects.Shift;
import roman.game.myshiftmanager.Objects.Workplace;
import roman.game.myshiftmanager.R;

public class Fragment_Reports extends Fragment {

    public static final String[] DATES = {"", "January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October", "November", "December"};

    private AppCompatActivity activity;

    private RecyclerView reports_LST_shifts;
    private LinearLayoutManager linearLayoutManager;

    private MaterialTextView reports_LBL_month, reports_LBL_amount, reports_LBL_currency;
    private MaterialButton reports_BTN_right, reports_BTN_left;
    private int month, year;

    public Fragment_Reports() {
    }

    public Fragment_Reports setActivity(AppCompatActivity activity) {
        this.activity = activity;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reports, container, false);

        findViews(view);

        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        reports_LST_shifts.setLayoutManager(linearLayoutManager);
        reports_LST_shifts.setHasFixedSize(true);
        reports_LST_shifts.setItemAnimator(new DefaultItemAnimator());

        reports_BTN_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 08/02/2022 - set new month and year - change the shift list
            }
        });

        reports_BTN_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 08/02/2022 - set new month and year - change the shift list
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
        reports_LST_shifts.setAdapter(adapter_shift);

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

    private void setDate() {
        // TODO: 08/02/2022 - set the month and the year to change the text view 
        Date today = Calendar.getInstance().getTime();
        month = today.getMonth();
        year = today.getYear();
    }

    private void setCurrency() {
        // TODO: 08/02/2022 - set the text view based on the currency in db
    }

    private void setAmount() {
        // TODO: 08/02/2022 - set the text view based on the total revenue on the list for the related month
    }

    private void findViews(View view) {
        reports_LST_shifts = view.findViewById(R.id.reports_LST_shifts);
        reports_LBL_month = view.findViewById(R.id.reports_LBL_month);
        reports_LBL_amount = view.findViewById(R.id.reports_LBL_amount);
        reports_LBL_currency = view.findViewById(R.id.reports_LBL_currency);
        reports_BTN_right = view.findViewById(R.id.reports_BTN_right);
        reports_BTN_left = view.findViewById(R.id.reports_BTN_left);
    }
}
