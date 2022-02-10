package roman.game.myshiftmanager.Fragments.Panel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import roman.game.myshiftmanager.Activities.Activity_Login;
import roman.game.myshiftmanager.Activities.Activity_MakeShift;
import roman.game.myshiftmanager.Activities.Activity_MakeWorkplace;
import roman.game.myshiftmanager.Managers.FirebaseAuthManager;
import roman.game.myshiftmanager.R;

public class Fragment_Profile extends Fragment {

    private AppCompatActivity activity;

    private MaterialButton profile_BTN_add_workplace, profile_BTN_add_shift, profile_BTN_logout;
    private ShapeableImageView profile_IMG_pic;
    private MaterialTextView profile_LBL_first_name, profile_LBL_last_name, profile_LBL_email, profile_LBL_currency;

    private FirebaseAuthManager firebaseAuthManager;

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

        setData();

        firebaseAuthManager = FirebaseAuthManager.getInstance();

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
        firebaseAuthManager.signOut();
        openActivity(Activity_Login.class);
        activity.finish();
    }

    private void setData() {
        // TODO: 08/02/2022 - set all the texts and picture with the user data from db
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
