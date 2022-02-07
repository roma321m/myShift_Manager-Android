package roman.game.myshiftmanager.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import roman.game.myshiftmanager.R;

public class Fragment_Login extends Fragment {

    public interface CallBack_Login {
        void phoneClicked();
        void gmailClicked();
    }

    private AppCompatActivity activity;

    private CallBack_Login callBack_login;

    private MaterialButton login_BTN_phone;
    private MaterialButton login_BTN_gmail;

    public Fragment_Login(){};

    public Fragment_Login setActivity(AppCompatActivity activity) {
        this.activity = activity;
        return this;
    }

    public void setCallBackLogin(CallBack_Login callBackLogin) {
        this.callBack_login = callBackLogin;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        findViews(view);

        login_BTN_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack_login.phoneClicked();
            }
        });

        login_BTN_gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack_login.gmailClicked();
            }
        });

        return view;
    }

    private void findViews(View view) {
        login_BTN_phone = view.findViewById(R.id.login_BTN_phone);
        login_BTN_gmail = view.findViewById(R.id.login_BTN_gmail);
    }
}
