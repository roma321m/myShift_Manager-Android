package roman.game.myshiftmanager.Fragments.Panel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import de.hdodenhof.circleimageview.CircleImageView;
import roman.game.myshiftmanager.Activities.Activity_Login;
import roman.game.myshiftmanager.Activities.Activity_MakeShift;
import roman.game.myshiftmanager.Activities.Activity_MakeWorkplace;
import roman.game.myshiftmanager.UserData.UserDataManager;
import roman.game.myshiftmanager.Objects.User;
import roman.game.myshiftmanager.R;

public class Fragment_Profile extends Fragment {

    private AppCompatActivity activity;

    private MaterialButton profile_BTN_add_workplace, profile_BTN_add_shift, profile_BTN_logout;
    private CircleImageView profile_IMG_pic;
    private MaterialTextView profile_LBL_first_name, profile_LBL_last_name, profile_LBL_email, profile_LBL_currency;

    private UserDataManager userDataManager;

    public Fragment_Profile() {
    }

    public Fragment_Profile setActivity(AppCompatActivity activity) {
        this.activity = activity;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        findViews(view);

        userDataManager = UserDataManager.getInstance();
        setData();

        profile_BTN_add_workplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(Activity_MakeWorkplace.class);
            }
        });

        profile_BTN_add_shift.setOnClickListener(new View.OnClickListener() {
            @Override
            // FIXME: 09/02/2022 - open only if the user has existing workplace 
            public void onClick(View v) {
                openActivity(Activity_MakeShift.class);
            }
        });

        profile_BTN_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        return view;
    }

    private void signOut(){
        userDataManager.signOut();
        openActivity(Activity_Login.class);
        activity.finish();
    }

    private void setData() {
        User user = userDataManager.getMyUser();
        profile_LBL_first_name.setText(user.getFirstName());
        profile_LBL_last_name.setText(user.getLastName());
        profile_LBL_email.setText(user.getEmail());
        if(user.getCurrency() < UserDataManager.currencyList.size())
            profile_LBL_currency.setText(UserDataManager.currencyList.get(user.getCurrency()));
        else
            profile_LBL_currency.setText(UserDataManager.currencyList.get(0));
        String url = userDataManager.getMyUser().getProfilePic();
        if(!url.equals("")){
            Glide.with(this).load(url).into(profile_IMG_pic);
        }
    }

    private void openActivity(Class activity) {
        Intent intent = new Intent(this.activity, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    private void findViews(View view) {
        profile_BTN_add_workplace = view.findViewById(R.id.profile_BTN_add_workplace);
        profile_BTN_add_shift = view.findViewById(R.id.profile_BTN_add_shift);
        profile_BTN_logout = view.findViewById(R.id.profile_BTN_logout);
        profile_IMG_pic = view.findViewById(R.id.profile_IMG_pic);
        profile_LBL_first_name = view.findViewById(R.id.profile_LBL_first_name);
        profile_LBL_last_name = view.findViewById(R.id.profile_LBL_last_name);
        profile_LBL_email = view.findViewById(R.id.profile_LBL_email);
        profile_LBL_currency = view.findViewById(R.id.profile_LBL_currency);
    }
}
