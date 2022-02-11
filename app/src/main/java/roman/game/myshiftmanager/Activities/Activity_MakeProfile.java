package roman.game.myshiftmanager.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import roman.game.myshiftmanager.UserData.UserDataManager;
import roman.game.myshiftmanager.R;

public class Activity_MakeProfile extends AppCompatActivity {

    private TextInputEditText make_textInputEditText_firstName;
    private TextInputEditText make_textInputEditText_lastName;
    private TextInputEditText make_textInputEditText_email;
    private AutoCompleteTextView make_autoCompleteTextView;
    private MaterialButton make_BTN_register;

    private UserDataManager userDataManager;
    private int currency;

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
        setDropdown();

        userDataManager = UserDataManager.getInstance();
        currency = 0;

        make_BTN_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        make_autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currency = position;
            }
        });
    }

    private void setDropdown(){
        String[] currency = getResources().getStringArray(R.array.currency);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.dropdown_item, currency);
        make_autoCompleteTextView.setAdapter(arrayAdapter);
    }

    private void registerUser() {
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

        userDataManager.setMyUser(firstName, lastName, email, currency);

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