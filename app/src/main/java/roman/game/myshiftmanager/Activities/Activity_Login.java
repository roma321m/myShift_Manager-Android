package roman.game.myshiftmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;


import roman.game.myshiftmanager.DB.FirebaseDB;
import roman.game.myshiftmanager.Fragments.Login.Fragment_Login;
import roman.game.myshiftmanager.Fragments.Login.Fragment_PhoneNumber;
import roman.game.myshiftmanager.Fragments.Login.Fragment_PhoneNumberVerification;
import roman.game.myshiftmanager.Managers.FirebaseAuthManager;
import roman.game.myshiftmanager.Managers.UserDataManager;
import roman.game.myshiftmanager.R;

public class Activity_Login extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private Fragment_Login fragment_login;
    private Fragment_PhoneNumber fragment_phone;
    private Fragment_PhoneNumberVerification fragment_phoneNumberVerification;

    private UserDataManager userDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setFragments();
        replaceFragments(fragment_login);

        userDataManager = UserDataManager.getInstance();
        userDataManager.setLoginActivity(this);
        userDataManager.setCallback_moveToVerification(callback_moveToVerification);
        userDataManager.setCallback_moveToMakeProfile(callback_moveToMakeProfile);
        userDataManager.setCallback_checkUserExistence(callback_checkUserExistence);
    }

    private void replaceFragments(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.login_fragment_view, fragment, null);
        fragmentTransaction.commit();
    }

    private void setFragments() {
        fragment_login = new Fragment_Login().setActivity(this);
        fragment_login.setCallBackLogin(callBack_login);
        fragment_phone = new Fragment_PhoneNumber().setActivity(this);
        fragment_phone.setCallBackPhoneNumber(callBack_phoneNumber);
        fragment_phoneNumberVerification = new Fragment_PhoneNumberVerification().setActivity(this);
        fragment_phoneNumberVerification.setCallBackPhoneNumberVerification(callBack_phoneNumberVerification);
    }

    Fragment_Login.CallBack_Login callBack_login = new Fragment_Login.CallBack_Login() {
        @Override
        public void phoneClicked() {
            replaceFragments(fragment_phone);
        }

        @Override
        public void gmailClicked() {
            // TODO: 07/02/2022
        }
    };

    Fragment_PhoneNumber.CallBack_PhoneNumber callBack_phoneNumber = new Fragment_PhoneNumber.CallBack_PhoneNumber() {
        @Override
        public void backClicked() {
            replaceFragments(fragment_login);
        }

        @Override
        public void loginClicked(String number) {
            userDataManager.phoneNumberEntered(number);
        }
    };

    Fragment_PhoneNumberVerification.CallBack_PhoneNumberVerification callBack_phoneNumberVerification = new Fragment_PhoneNumberVerification.CallBack_PhoneNumberVerification(){
        @Override
        public void backClicked(){
            replaceFragments(fragment_login);
        }

        @Override
        public void verifiedClicked(String code){
            userDataManager.codeEntered(code);
        }

        @Override
        public void resendClicked(String number) {
            userDataManager.resendEntered(number);
        }
    };

    FirebaseAuthManager.Callback_MoveToVerification callback_moveToVerification = new FirebaseAuthManager.Callback_MoveToVerification() {
        @Override
        public void moveToVerificationFragment() {
            replaceFragments(fragment_phoneNumberVerification);
        }
    };

    FirebaseAuthManager.Callback_MoveToMakeProfile callback_moveToMakeProfile = new FirebaseAuthManager.Callback_MoveToMakeProfile() {
        @Override
        public void moveToMakeProfile() {
            // FIXME: 10/02/2022 - need check if the user already exists
            userDataManager.checkIfHasProfile();
        }
    };

    private void openActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    FirebaseDB.Callback_checkUserExistence callback_checkUserExistence = new FirebaseDB.Callback_checkUserExistence() {
        @Override
        public void profileExist() {
            openActivity(Activity_Panel.class);
        }

        @Override
        public void makeProfile() {
            openActivity(Activity_MakeProfile.class);
        }
    };
}