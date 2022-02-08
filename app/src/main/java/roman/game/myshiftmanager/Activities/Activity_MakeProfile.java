package roman.game.myshiftmanager.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseUser;

import roman.game.myshiftmanager.DB.FirebaseDB;
import roman.game.myshiftmanager.Managers.FirebaseAuthManager;
import roman.game.myshiftmanager.Objects.User;
import roman.game.myshiftmanager.R;

public class Activity_MakeProfile extends AppCompatActivity {

    private FirebaseUser fbUser;
    private String userID;
    private FirebaseDB firebaseDB;

    private TextInputEditText make_textInputEditText_firstName;
    private TextInputEditText make_textInputEditText_lastName;
    private TextInputEditText make_textInputEditText_email;
    private AutoCompleteTextView make_autoCompleteTextView;
    private MaterialButton make_BTN_register;

    @Override
    protected void onResume() {
        super.onResume();
        setDropdown();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_profile);

        findViews();

        fbUser = FirebaseAuthManager.getInstance().getUser();
        userID = fbUser.getUid();

        firebaseDB = FirebaseDB.getInstance();

        setDropdown();

        make_BTN_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void setDropdown(){
        String[] currency = getResources().getStringArray(R.array.currency);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.dropdown_item, currency);
        make_autoCompleteTextView.setAdapter(arrayAdapter);
    }

    private void registerUser() {
        // TODO: 07/02/2022 - better tf with error checks later, as we learned in the class.
        String firstName = make_textInputEditText_firstName.getText().toString().trim();
        String lastName = make_textInputEditText_lastName.getText().toString().trim();
        String email = make_textInputEditText_email.getText().toString().trim();

        if (firstName.isEmpty()) {
            make_textInputEditText_firstName.setError("Required field");
            make_textInputEditText_firstName.requestFocus();
            return;
        }
        if (lastName.isEmpty()) {
            make_textInputEditText_lastName.setError("Required field");
            make_textInputEditText_lastName.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            make_textInputEditText_email.setError("Required field");
            make_textInputEditText_email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            make_textInputEditText_email.setError("Not an Email");
            make_textInputEditText_email.requestFocus();
            return;
        }

        User user = new User();
        user.setUserID(userID);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(fbUser.getPhoneNumber());
        firebaseDB.createUser(user); // FIXME: 07/02/2022 - not adding to db 

        Intent intent = new Intent(this, Activity_Panel.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    private void findViews() {
        make_BTN_register = findViewById(R.id.make_BTN_register);
        make_textInputEditText_firstName = findViewById(R.id.make_textInputEditText_firstName);
        make_textInputEditText_lastName = findViewById(R.id.make_textInputEditText_lastName);
        make_textInputEditText_email = findViewById(R.id.make_textInputEditText_email);
        make_autoCompleteTextView = findViewById(R.id.make_autoCompleteTextView);
    }
}