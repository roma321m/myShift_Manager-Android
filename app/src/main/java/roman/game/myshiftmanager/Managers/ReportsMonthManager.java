package roman.game.myshiftmanager.Managers;

import com.google.android.material.textview.MaterialTextView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReportsMonthManager {

    private static ReportsMonthManager single_instance = null;
    private LocalDate selectedDate;
    private MaterialTextView reports_LBL_month;
    private MaterialTextView reports_LBL_currency;
    private MaterialTextView reports_LBL_amount;

    private ReportsMonthManager() {
        this.selectedDate = LocalDate.now();
    }

    public static ReportsMonthManager getInstance() {
        if (single_instance == null) {
            single_instance = new ReportsMonthManager();
        }
        return single_instance;
    }

    public void setViews(MaterialTextView reports_LBL_month,
                         MaterialTextView reports_LBL_amount,
                         MaterialTextView reports_LBL_currency){
        if(reports_LBL_month == null || reports_LBL_amount == null || reports_LBL_currency == null)
            return;
        this.reports_LBL_month = reports_LBL_month;
        this.reports_LBL_amount = reports_LBL_amount;
        this.reports_LBL_currency = reports_LBL_currency;

    }

    public void previousMonthAction() {
        selectedDate = selectedDate.minusMonths(1);
        setNewMonthViews();

        // TODO: 09/02/2022 - change the shift list based on the month
    }

    public void nextMonthAction() {
        selectedDate = selectedDate.plusMonths(1);
        setNewMonthViews();

        // TODO: 09/02/2022 - change the shift list based on the month
    }

    public void setNewMonthViews() {
        setMonthView();
        setCurrencyView();
        setAmountView();
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
        // TODO: 08/02/2022 - set the text view based on the currency in db
    }

    private void setAmountView() {
        if (reports_LBL_amount == null)
            return;
        // TODO: 08/02/2022 - set the text view based on the total revenue on the list for the related month
    }

}
