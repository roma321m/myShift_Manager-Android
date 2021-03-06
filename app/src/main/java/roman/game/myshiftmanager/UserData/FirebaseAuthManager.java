package roman.game.myshiftmanager.UserData;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class FirebaseAuthManager {

    public interface Callback_MoveToVerification{
        void moveToVerificationFragment();
    }

    public interface Callback_MoveToMakeProfile {
        void moveToMakeProfile();
    }

    private Context context;
    private Activity activity;
    private PhoneAuthProvider.ForceResendingToken forceResendingToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks;
    private String myVerificationId;
    private FirebaseAuth firebaseAuth;

    private Callback_MoveToVerification callback_moveToVerification;
    private Callback_MoveToMakeProfile callback_moveToMakeProfile;

    private static FirebaseAuthManager single_instance;

    private FirebaseAuthManager(Context context) {
        this.context = context;
        firebaseAuth = FirebaseAuth.getInstance();

        onVerificationStateChangedCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                /* This callback will be invoked when:
                *  1) - Instant verification 
                *  2) - Auto-retrieval, Google play services automatically detect the SMS and verify without user action */
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                /* This callback will be invoked when the provided phone number is not valid */
                Toast.makeText(context, ""+ e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String verificationID, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                super.onCodeSent(verificationID, forceResendingToken);

                myVerificationId = verificationID;
                forceResendingToken = token;

                Toast.makeText(context, "Verification code sent", Toast.LENGTH_SHORT).show();
                if(callback_moveToVerification != null)
                    callback_moveToVerification.moveToVerificationFragment();
            }
        };
    }

    public static FirebaseAuthManager initHelper(Context context) {
        if (single_instance == null) {
            single_instance = new FirebaseAuthManager(context);
        }
        return single_instance;
    }

    public static FirebaseAuthManager getInstance() {
        return single_instance;
    }

    public void setActivity(Activity activity){
        this.activity = activity;
    }

    public FirebaseAuthManager setCallback_moveToVerification(Callback_MoveToVerification callback_moveToVerification) {
        this.callback_moveToVerification = callback_moveToVerification;
        return this;
    }

    public FirebaseAuthManager setCallback_moveToMakeProfile(Callback_MoveToMakeProfile callback_moveToMakeProfile) {
        this.callback_moveToMakeProfile = callback_moveToMakeProfile;
        return this;
    }

    public FirebaseUser getUser(){
        return firebaseAuth.getCurrentUser();
    }

    public void phoneNumberEntered(String number){
        if(TextUtils.isEmpty(number)){
            Toast.makeText(context, "Please enter phone number", Toast.LENGTH_SHORT).show();
        }else{
            startPhoneNumberVerification(number);
        }
    }

    public void resendEntered(String number){
        if(TextUtils.isEmpty(number)){
            Toast.makeText(context, "Please enter phone number", Toast.LENGTH_SHORT).show();
        }else{
            resendVerificationCode(number, forceResendingToken);
        }
    }

    public void codeEntered(String code){
        if(TextUtils.isEmpty(code)){
            Toast.makeText(context, "Please enter verification code", Toast.LENGTH_SHORT).show();
        }else{
            verifyPhoneNumberWithCode(myVerificationId, code);
        }
    }

    private void startPhoneNumberVerification(String number){
        if(activity==null)
            return;
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(number)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(activity)
                .setCallbacks(onVerificationStateChangedCallbacks)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void resendVerificationCode(String number, PhoneAuthProvider.ForceResendingToken token){
        if(activity==null)
            return;

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(number)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(activity)
                .setCallbacks(onVerificationStateChangedCallbacks)
                .setForceResendingToken(token)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential){
        firebaseAuth.signInWithCredential(credential)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        // successfully signed in
                        String number = firebaseAuth.getCurrentUser().getPhoneNumber();
                        Toast.makeText(context, "Logging in as " + number, Toast.LENGTH_SHORT).show();
                        if(callback_moveToMakeProfile != null)
                            callback_moveToMakeProfile.moveToMakeProfile();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String number = firebaseAuth.getCurrentUser().getPhoneNumber();
                        Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void signOut() {
        firebaseAuth.signOut();
    }
}
