package roman.game.myshiftmanager.UserData;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

import roman.game.myshiftmanager.Objects.Shift;
import roman.game.myshiftmanager.Objects.User;
import roman.game.myshiftmanager.Objects.Workplace;

public class UserDataManager {

    private static UserDataManager single_instance = null;
    private Context context;

    private User myUser;
    private ArrayList<Workplace> workplaces;
    private ArrayList<Shift> shifts;

    private FirebaseAuthManager firebaseAuthManager;
    FirebaseDB firebaseDB;

    private UserDataManager(Context context) {
        this.context = context;
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

    public User getMyUser(){
        return myUser;
    }

    public void setMyUser(String firstName, String lastName, String email, int currency){
        workplaces = new ArrayList<>();
        shifts = new ArrayList<>();
        myUser = new User();
        myUser.setPhoneNumber(firebaseAuthManager.getUser().getPhoneNumber());
        if (firstName != null){
            myUser.setFirstName(firstName);
        }
        if (lastName != null){
            myUser.setLastName(lastName);
        }
        if (email != null){
            myUser.setEmail(email);
        }
        myUser.setCurrency(currency);
        myUser.setTimeFormat(0);
        myUser.setDateFormat(0);
        String uid = firebaseAuthManager.getUser().getUid();
        myUser.setShiftsID(uid + "_shifts");
        myUser.setWorksID(uid + "_works");
        firebaseDB.createUser(uid, myUser);
    }

    public void setLoginActivity(Activity activity){
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

    public boolean checkIfHasUID(){
        return firebaseAuthManager.getUser() != null;
    }

    public void checkIfHasProfile(){
        String uid = firebaseAuthManager.getUser().getUid();
        firebaseDB.hasProfile(uid);
    }

    public void signOut() {
        firebaseAuthManager.signOut();
    }

    public void addShift(){
        Shift shift = new Shift();

        // TODO: 10/02/2022 create new shift and add it to the user

        firebaseDB.addShift(firebaseAuthManager.getUser().getUid(), shift);
    }

    public void addWorkplace(){
        Workplace workplace = new Workplace();

        // TODO: 10/02/2022 create new workplace and add it to the user

        firebaseDB.addWorkplace(firebaseAuthManager.getUser().getUid(), workplace);
    }

    FirebaseDB.Callback_loadUserData callback_loadUserData = new FirebaseDB.Callback_loadUserData() {
        @Override
        public void callback_loadUserData(User user) {
            if (user != null){
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
}
