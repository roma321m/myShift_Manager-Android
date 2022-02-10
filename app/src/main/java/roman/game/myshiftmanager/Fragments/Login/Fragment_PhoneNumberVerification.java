package roman.game.myshiftmanager.Fragments.Login;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import roman.game.myshiftmanager.R;

public class Fragment_PhoneNumberVerification extends Fragment {

    public interface CallBack_PhoneNumberVerification {
        void backClicked();
        void verifiedClicked(String code);
        void resendClicked(String number);
    }

    private CallBack_PhoneNumberVerification callBack_phoneNumberVerification;
    private AppCompatActivity activity;

    private MaterialButton phone_verification_BTN_back;
    private MaterialButton phone_verification_BTN_continue;
    private MaterialTextView phone_verification_LBL_resend;
    private TextInputEditText phone_verification_textInputEditText;
    private ProgressBar phone_verification_PB_login;

    public Fragment_PhoneNumberVerification(){};

    public Fragment_PhoneNumberVerification setActivity(AppCompatActivity activity) {
        this.activity = activity;
        return this;
    }

    public void setCallBackPhoneNumberVerification(CallBack_PhoneNumberVerification callBack_phoneNumberVerification) {
        this.callBack_phoneNumberVerification = callBack_phoneNumberVerification;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phone_number_verification, container, false);
        findViews(view);

        phone_verification_PB_login.setVisibility(View.GONE);
        phone_verification_BTN_continue.setVisibility(View.VISIBLE);

        phone_verification_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack_phoneNumberVerification != null){
                    phone_verification_PB_login.setVisibility(View.GONE);
                    phone_verification_BTN_continue.setVisibility(View.VISIBLE);
                    callBack_phoneNumberVerification.backClicked();
                }
            }
        });

        phone_verification_BTN_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeEntered();
            }
        });

        phone_verification_textInputEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER)
                {
                    codeEntered();
                }
                return false;
            }
        });

        phone_verification_LBL_resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack_phoneNumberVerification != null){
                    // FIXME: 10/02/2022 - get from the intent the phone number and pass it to the verification fragment
                    callBack_phoneNumberVerification.resendClicked("");
                }
            }
        });
        
        return view;
    }

    private void codeEntered(){
        // FIXME: 06/02/2022 - need to check the input
        String code = phone_verification_textInputEditText.getText().toString();
        Log.d("fttt", "code:" + code);
        if(callBack_phoneNumberVerification != null){
            phone_verification_PB_login.setVisibility(View.VISIBLE);
            phone_verification_BTN_continue.setVisibility(View.INVISIBLE);
            callBack_phoneNumberVerification.verifiedClicked(code);
        }
    }

    private void findViews(View view) {
        phone_verification_BTN_back = view.findViewById(R.id.phone_verification_BTN_back);
        phone_verification_BTN_continue = view.findViewById(R.id.phone_verification_BTN_continue);
        phone_verification_LBL_resend = view.findViewById(R.id.phone_verification_LBL_resend);
        phone_verification_textInputEditText = view.findViewById(R.id.phone_verification_textInputEditText);
        phone_verification_PB_login = view.findViewById(R.id.phone_verification_PB_login);
    }
}
