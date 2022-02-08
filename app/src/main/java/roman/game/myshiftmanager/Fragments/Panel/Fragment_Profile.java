package roman.game.myshiftmanager.Fragments.Panel;

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
import com.google.firebase.auth.FirebaseUser;

import roman.game.myshiftmanager.DB.FirebaseDB;
import roman.game.myshiftmanager.Managers.FirebaseAuthManager;
import roman.game.myshiftmanager.R;

public class Fragment_Profile extends Fragment {

    private FirebaseUser fbUser;
    private String userID;
    private FirebaseDB firebaseDB;

    private AppCompatActivity activity;

    private MaterialButton profile_BTN_add, profile_BTN_logout;
    private ShapeableImageView profile_IMG_pic;
    private MaterialTextView profile_LBL_first_name, profile_LBL_last_name, profile_LBL_email, profile_LBL_currency;

    public Fragment_Profile(){};

    public Fragment_Profile setActivity(AppCompatActivity activity) {
        this.activity = activity;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        findViews(view);

        fbUser = FirebaseAuthManager.getInstance().getUser();
        userID = fbUser.getUid();

        firebaseDB = FirebaseDB.getInstance();

        setData();

        profile_BTN_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 08/02/2022 - save the changes to db
                Toast.makeText(activity, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        profile_BTN_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 08/02/2022 - save the changes to db
                Toast.makeText(activity, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    
    private void setData(){
        // TODO: 08/02/2022 - set all the texts and picture with the user data from db
    }

    private void findViews(View view) {
        profile_BTN_add = view.findViewById(R.id.profile_BTN_add);
        profile_BTN_logout = view.findViewById(R.id.profile_BTN_logout);
        profile_IMG_pic = view.findViewById(R.id.profile_IMG_pic);
        profile_LBL_first_name = view.findViewById(R.id.profile_LBL_first_name);
        profile_LBL_last_name = view.findViewById(R.id.profile_LBL_last_name);
        profile_LBL_email = view.findViewById(R.id.profile_LBL_email);
        profile_LBL_currency = view.findViewById(R.id.profile_LBL_currency);
    }
}
