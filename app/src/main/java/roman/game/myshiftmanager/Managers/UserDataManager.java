package roman.game.myshiftmanager.Managers;

import android.app.Activity;
import android.content.Context;

import roman.game.myshiftmanager.DB.FirebaseDB;

public class UserDataManager {

    private static UserDataManager single_instance = null;
    private Context context;

    private FirebaseAuthManager firebaseAuthManager;
    FirebaseDB firebaseDB;

    private UserDataManager(Context context) {
        this.context = context;
        firebaseAuthManager = FirebaseAuthManager.getInstance();
        firebaseDB = FirebaseDB.getInstance();
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
}
