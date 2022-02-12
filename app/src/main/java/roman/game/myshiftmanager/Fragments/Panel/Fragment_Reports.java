package roman.game.myshiftmanager.Fragments.Panel;

import android.os.Bundle;
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

import roman.game.myshiftmanager.Adapters.Adapter_Shift;
import roman.game.myshiftmanager.Managers.ReportsMonthManager;
import roman.game.myshiftmanager.Objects.Shift;
import roman.game.myshiftmanager.R;

public class Fragment_Reports extends Fragment {

    public interface Callback_ReportMonthManager{
        void monthChange();
    }

    private AppCompatActivity activity;

    private RecyclerView reports_LST_shifts;
    private LinearLayoutManager linearLayoutManager;

    private MaterialTextView reports_LBL_month, reports_LBL_amount, reports_LBL_currency;
    private MaterialButton reports_BTN_right, reports_BTN_left;

    private ReportsMonthManager reportsMonthManager;

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

        reportsMonthManager = ReportsMonthManager.getInstance();
        reportsMonthManager.setViews(reports_LBL_month, reports_LBL_amount, reports_LBL_currency);
        reportsMonthManager.setCallback_reportMonthManager(callback_reportMonthManager);

        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        reports_LST_shifts.setLayoutManager(linearLayoutManager);
        reports_LST_shifts.setHasFixedSize(true);
        reports_LST_shifts.setItemAnimator(new DefaultItemAnimator());

        reportsMonthManager.setNewMonthViews();

        reports_BTN_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportsMonthManager.previousMonthAction();
            }
        });

        reports_BTN_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportsMonthManager.nextMonthAction();
            }
        });

        // UI test
        ArrayList<Shift> shifts = reportsMonthManager.getShifts();
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

    Callback_ReportMonthManager callback_reportMonthManager = new Callback_ReportMonthManager() {
        @Override
        public void monthChange() {
            ArrayList<Shift> shifts = reportsMonthManager.getShifts();
            setShifts(shifts);
        }
    };

    private void findViews(View view) {
        reports_LST_shifts = view.findViewById(R.id.reports_LST_shifts);
        reports_LBL_month = view.findViewById(R.id.reports_LBL_month);
        reports_LBL_amount = view.findViewById(R.id.reports_LBL_amount);
        reports_LBL_currency = view.findViewById(R.id.reports_LBL_currency);
        reports_BTN_right = view.findViewById(R.id.reports_BTN_right);
        reports_BTN_left = view.findViewById(R.id.reports_BTN_left);
    }
}
