package roman.game.myshiftmanager.Fragments.Login;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import roman.game.myshiftmanager.R;

public class Fragment_PhoneNumber extends Fragment {

    public interface CallBack_PhoneNumber {
        void backClicked();
        void loginClicked(String number);
    }

    private CallBack_PhoneNumber callBack_phoneNumber;
    private AppCompatActivity activity;

    private MaterialButton phone_BTN_back;
    private MaterialButton phone_BTN_login;
    private TextInputEditText phone_textInputEditText;
    private ProgressBar phone_PB_login;

    public Fragment_PhoneNumber(){};

    public Fragment_PhoneNumber setActivity(AppCompatActivity activity) {
        this.activity = activity;
        return this;
    }

    public void setCallBackPhoneNumber(CallBack_PhoneNumber callBack_phoneNumber) {
        this.callBack_phoneNumber = callBack_phoneNumber;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phone_number, container, false);
        findViews(view);

        phone_PB_login.setVisibility(View.GONE);
        phone_BTN_login.setVisibility(View.VISIBLE);

        phone_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack_phoneNumber != null){
                    phone_PB_login.setVisibility(View.GONE);
                    phone_BTN_login.setVisibility(View.VISIBLE);
                    callBack_phoneNumber.backClicked();
                }
            }
        });

        phone_BTN_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumberEntered();
            }
        });

        phone_textInputEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER)
                {
                    phoneNumberEntered();
                }
                return false;
            }
        });

        return view;
    }

    private void phoneNumberEntered(){
        // FIXME: 06/02/2022 - need to check the input and make the ft red on error.
        String number = "+972" + phone_textInputEditText.getText().toString();
        Log.d("fttt", "number:" + number);
        if(callBack_phoneNumber != null){
            phone_PB_login.setVisibility(View.VISIBLE);
            phone_BTN_login.setVisibility(View.INVISIBLE);
            callBack_phoneNumber.loginClicked(number);
        }
    }

    private void findViews(View view) {
        phone_BTN_back = view.findViewById(R.id.phone_BTN_back);
        phone_BTN_login = view.findViewById(R.id.phone_BTN_login);
        phone_textInputEditText = view.findViewById(R.id.phone_textInputEditText);
        phone_PB_login = view.findViewById(R.id.phone_PB_login);
    }
}
