package roman.game.myshiftmanager.Activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseUser;

import roman.game.myshiftmanager.Managers.FirebaseAuthManager;
import roman.game.myshiftmanager.R;

public class Activity_MakeProfile extends AppCompatActivity {

    private FirebaseUser fbUser;
    private String userID;

    private AutoCompleteTextView make_autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_profile);

        findViews();

        fbUser = FirebaseAuthManager.getInstance().getUser();
        userID = fbUser.getUid();

        String[] currency = getResources().getStringArray(R.array.currency);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.dropdown_currency, currency);
        make_autoCompleteTextView.setAdapter(arrayAdapter);

    }

    private void findViews() {
        make_autoCompleteTextView = findViewById(R.id.make_autoCompleteTextView);
    }
}