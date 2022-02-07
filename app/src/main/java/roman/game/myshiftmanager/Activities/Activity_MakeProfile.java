package roman.game.myshiftmanager.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseUser;

import roman.game.myshiftmanager.Managers.FirebaseAuthManager;
import roman.game.myshiftmanager.R;

public class Activity_MakeProfile extends AppCompatActivity {

    private FirebaseUser fbUser;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_profile);

        findViews();

        fbUser = FirebaseAuthManager.getInstance().getUser();
        userID = fbUser.getUid();
    }

    private void findViews() {
        //panel_TLB_toolbar = findViewById(R.id.panel_TLB_toolbar);
    }
}