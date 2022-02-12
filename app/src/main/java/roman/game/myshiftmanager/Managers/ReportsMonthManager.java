package roman.game.myshiftmanager.Managers;

import com.google.android.material.textview.MaterialTextView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import roman.game.myshiftmanager.Fragments.Panel.Fragment_Reports;
import roman.game.myshiftmanager.Objects.Shift;
import roman.game.myshiftmanager.Objects.Workplace;
import roman.game.myshiftmanager.UserData.UserDataManager;

public class ReportsMonthManager {

    private static ReportsMonthManager single_instance = null;
    private LocalDate selectedDate;
    private MaterialTextView reports_LBL_month;
    private MaterialTextView reports_LBL_currency;
    private MaterialTextView reports_LBL_amount;

    private UserDataManager userDataManager;
    private Fragment_Reports.Callback_ReportMonthManager callback_reportMonthManager;

    private ReportsMonthManager() {
        this.selectedDate = LocalDate.now();
        userDataManager = UserDataManager.getInstance();
    }

    public static ReportsMonthManager getInstance() {
        if (single_instance == null) {
            single_instance = new ReportsMonthManager();
        }
        return single_instance;
    }

    public void setViews(MaterialTextView reports_LBL_month,
                         MaterialTextView reports_LBL_amount,
                         MaterialTextView reports_LBL_currency) {
        if (reports_LBL_month == null || reports_LBL_amount == null || reports_LBL_currency == null)
            return;
        this.reports_LBL_month = reports_LBL_month;
        this.reports_LBL_amount = reports_LBL_amount;
        this.reports_LBL_currency = reports_LBL_currency;
    }

    public void setCallback_reportMonthManager(Fragment_Reports.Callback_ReportMonthManager callback_reportMonthManager) {
        this.callback_reportMonthManager = callback_reportMonthManager;
    }

    public void previousMonthAction() {
        selectedDate = selectedDate.minusMonths(1);
        setNewMonthViews();

        callback_reportMonthManager.monthChange();
    }

    public void nextMonthAction() {
        selectedDate = selectedDate.plusMonths(1);
        setNewMonthViews();

        callback_reportMonthManager.monthChange();
    }

    public void setNewMonthViews() {
        setMonthView();
        setCurrencyView();
        setAmountView();
    }

    public ArrayList<Shift> getShifts() {
        ArrayList<Shift> shifts = userDataManager.getShifts();
        ArrayList<Shift> show = new ArrayList<>();
        for (Shift s : shifts) {
            if (s.getStartYear() == selectedDate.getYear() && s.getStartMonth() == selectedDate.getMonthValue()) {
                show.add(s);
            }
        }
        return show;
    }

    private String monthYearFromDate(LocalDate date) {
        if (date == null)
            return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    private void setMonthView() {
        if (reports_LBL_month == null)
            return;
        reports_LBL_month.setText(monthYearFromDate(selectedDate));
    }

    private void setCurrencyView() {
        if (reports_LBL_currency == null)
            return;
        // FIXME: 12/02/2022 - currency / need to get the string from resources by the index
        // userDataManager.getMyUser().getCurrency();
        //reports_LBL_currency.setText(userDataManager.getMyUser().getCurrency());
    }

    private void setAmountView() {
        if (reports_LBL_amount == null)
            return;
        ArrayList<Shift> shifts = getShifts();
        int revenue = 0;
        for (Shift s : shifts) {
            revenue += s.getRevenue();
        }

        // add monthly stuff
        if (revenue != 0) {
            ArrayList<Workplace> workplaces = userDataManager.getWorkplaces();
            for (Workplace w : workplaces) {
                revenue += w.getMonthlyRevenue();
            }
        }
        reports_LBL_amount.setText("" + revenue);
    }

}
