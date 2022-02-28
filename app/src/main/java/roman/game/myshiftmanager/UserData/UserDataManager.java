package roman.game.myshiftmanager.UserData;

import android.app.Activity;
import android.content.Context;

import java.time.Duration;
import java.util.ArrayList;

import roman.game.myshiftmanager.Objects.Shift;
import roman.game.myshiftmanager.Objects.User;
import roman.game.myshiftmanager.Objects.Workplace;

public class UserDataManager {

    public static ArrayList<String> currencyList;
    public static ArrayList<String> timeFormatList;
    public static ArrayList<String> dateFormatList;

    private static UserDataManager single_instance = null;
    private Context context;

    private User myUser;
    private ArrayList<Workplace> workplaces;
    private ArrayList<Shift> shifts;

    private FirebaseAuthManager firebaseAuthManager;
    private FirebaseDB firebaseDB;

    private UserDataManager(Context context) {
        this.context = context;
        setLists();
        firebaseAuthManager = FirebaseAuthManager.getInstance();
        firebaseDB = FirebaseDB.getInstance();
        firebaseDB.setCallback_loadUserData(callback_loadUserData);
    }

    public static UserDataManager initHelper(Context context) {
        if (single_instance == null) {
            single_instance = new UserDataManager(context);
        }
        return single_instance;
    }

    public static UserDataManager getInstance() {
        return single_instance;
    }

    private void setLists() {
        currencyList = new ArrayList<>();
        currencyList.add("₪  Shekel");
        currencyList.add("$  US Dollar");
        currencyList.add("€  Euro");

        timeFormatList = new ArrayList<>();
        timeFormatList.add("24h");
        timeFormatList.add("12h");

        dateFormatList = new ArrayList<>();
        dateFormatList.add("dd/mm/yy");
        dateFormatList.add("mm/dd/yy");
    }

    public String getUID(){
        return firebaseAuthManager.getUser().getUid();
    }

    public User getMyUser() {
        return myUser;
    }

    public void setMyUser(String firstName, String lastName, String email, int currency, String urlIMG) {
        workplaces = new ArrayList<>();
        shifts = new ArrayList<>();
        myUser = new User();
        myUser.setPhoneNumber(firebaseAuthManager.getUser().getPhoneNumber());
        if (firstName != null) {
            myUser.setFirstName(firstName);
        }
        if (lastName != null) {
            myUser.setLastName(lastName);
        }
        if (email != null) {
            myUser.setEmail(email);
        }
        myUser.setCurrency(currency);
        myUser.setProfilePic(urlIMG);
        myUser.setTimeFormat(0);
        myUser.setDateFormat(0);
        String uid = firebaseAuthManager.getUser().getUid();
        firebaseDB.createUser(uid, myUser);
    }

    public void setLoginActivity(Activity activity) {
        firebaseAuthManager.setActivity(activity);
    }

    public void setCallback_moveToVerification(FirebaseAuthManager.Callback_MoveToVerification callback_moveToVerification) {
        firebaseAuthManager.setCallback_moveToVerification(callback_moveToVerification);
    }

    public void setCallback_moveToMakeProfile(FirebaseAuthManager.Callback_MoveToMakeProfile callback_moveToMakeProfile) {
        firebaseAuthManager.setCallback_moveToMakeProfile(callback_moveToMakeProfile);
    }

    public void setCallback_checkUserExistence(FirebaseDB.Callback_checkUserExistence callback_checkUserExistence) {
        firebaseDB.setCallback_checkUserExistence(callback_checkUserExistence);
    }

    public void phoneNumberEntered(String number) {
        firebaseAuthManager.phoneNumberEntered(number);
    }

    public void codeEntered(String code) {
        firebaseAuthManager.codeEntered(code);
    }

    public void resendEntered(String number) {
        firebaseAuthManager.resendEntered(number);
    }

    public boolean checkIfHasUID() {
        return firebaseAuthManager.getUser() != null;
    }

    public void checkIfHasProfile() {
        String uid = firebaseAuthManager.getUser().getUid();
        firebaseDB.hasProfile(uid);
    }

    public void signOut() {
        firebaseAuthManager.signOut();
    }

    public String getDateInFormat(int day, int month, int year) {
        switch (myUser.getDateFormat()) {
            case 0:
                return String.format("%02d/%02d/%04d", day, month, year);
            case 1:
                return String.format("%02d/%02d/%04d", month, day, year);
            default:
                return "";
        }
    }

    public String getTimeInFormat(int hours, int minutes) {
        switch (myUser.getTimeFormat()) {
            case 0:
                return String.format("%02d:%02d", hours, minutes);
            case 1:
                if (hours > 12) {
                    hours = hours - 12;
                    return String.format("%02d:%02d PM", hours, minutes);
                } else {
                    return String.format("%02d:%02d AM", hours, minutes);
                }
            default:
                return "";
        }
    }

    public String getDateTimeInFormat(int day, int month, int year, int hours, int minutes) {
        return getDateInFormat(day, month, year) + "  " + getTimeInFormat(hours, minutes);
    }

    public void addShift(int workplacePos, int[] from, int[] to, long milliseconds) {
        Shift shift = new Shift();
        shift.setWorkplaceID(workplacePos);
        shift.setStart(from);
        shift.setEnd(to);
        Duration duration = Duration.ofMillis(milliseconds);
        long seconds = duration.getSeconds();
        double hours = seconds / 3600.0;
        shift.setTotalTime(hours);
        shift.setRevenue(workplaces.get(workplacePos - 1).getRevenue(shift.getTotalTime()));

        shifts.add(shift);
        int shiftId = shifts.size();

        firebaseDB.addShift(firebaseAuthManager.getUser().getUid(), shiftId, shift);
    }

    public void addWorkplace(String name, String hourlyWage, String vacationPayments,
                             String deductionPerShift, String bonusesPerShift,
                             String breakPerShiftUnpaid, String dailyTravelExpenses,
                             String monthlyTravelExpenses, String color) {
        Workplace workplace = new Workplace();
        workplace.setName(name);
        workplace.setColor(color);
        if (!hourlyWage.equals(""))
            workplace.setHourlyWage(Double.parseDouble(hourlyWage));
        if (!vacationPayments.equals(""))
            workplace.setVacationPayments(Double.parseDouble(vacationPayments));
        if (!deductionPerShift.equals(""))
            workplace.setDeductionPerShift(Double.parseDouble(deductionPerShift));
        if (!bonusesPerShift.equals(""))
            workplace.setBonusesPerShift(Double.parseDouble(bonusesPerShift));
        if (!breakPerShiftUnpaid.equals(""))
            workplace.setBreakTimeUnpaid(Integer.parseInt(breakPerShiftUnpaid));
        if (!dailyTravelExpenses.equals(""))
            workplace.setDailyTravelExpenses(Double.parseDouble(dailyTravelExpenses));
        if (!monthlyTravelExpenses.equals(""))
            workplace.setMonthlyTravelExpenses(Double.parseDouble(monthlyTravelExpenses));

        workplaces.add(workplace);
        int workId = workplaces.size();

        firebaseDB.addWorkplace(firebaseAuthManager.getUser().getUid(), workId, workplace);
    }

    FirebaseDB.Callback_loadUserData callback_loadUserData = new FirebaseDB.Callback_loadUserData() {
        @Override
        public void callback_loadUserData(User user) {
            if (user != null) {
                myUser = user;
            }
        }

        @Override
        public void callback_loadWorkplaces(ArrayList<Workplace> workplaces1) {
            workplaces = workplaces1;
        }

        @Override
        public void callback_loadShifts(ArrayList<Shift> shifts1) {
            shifts = shifts1;
        }
    };

    public ArrayList<Workplace> getWorkplaces() {
        return workplaces;
    }

    public ArrayList<Shift> getShifts() {
        return shifts;
    }

    public void updateCurrency(int pos) {
        myUser.setCurrency(pos);
        firebaseDB.updateCurrency(firebaseAuthManager.getUser().getUid(), pos);
    }

    public void updateTimeFormat(int pos) {
        myUser.setTimeFormat(pos);
        firebaseDB.updateTimeFormat(firebaseAuthManager.getUser().getUid(), pos);
    }

    public void updateDateFormat(int pos) {
        myUser.setDateFormat(pos);
        firebaseDB.updateDateFormat(firebaseAuthManager.getUser().getUid(), pos);
    }
}
