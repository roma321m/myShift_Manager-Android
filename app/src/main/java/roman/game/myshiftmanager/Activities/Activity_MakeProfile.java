package roman.game.myshiftmanager.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import de.hdodenhof.circleimageview.CircleImageView;
import roman.game.myshiftmanager.R;
import roman.game.myshiftmanager.UserData.FirebaseS;
import roman.game.myshiftmanager.UserData.UserDataManager;

public class Activity_MakeProfile extends AppCompatActivity {

    private TextInputEditText make_textInputEditText_firstName;
    private TextInputEditText make_textInputEditText_lastName;
    private TextInputEditText make_textInputEditText_email;
    private AutoCompleteTextView make_autoCompleteTextView;
    private MaterialButton make_BTN_register;
    private CircleImageView make_IMG_circle_image;

    private UserDataManager userDataManager;
    private FirebaseS firebaseS;
    private int currency;
    private String urlIMG = "";

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
        firebaseS = FirebaseS.getInstance();
        firebaseS.setCallBack_uploadImg(new FirebaseS.CallBack_UploadImg() {
            @Override
            public void urlReady(String url, Activity activity) {
                urlIMG = url;
            }
        });
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

        make_IMG_circle_image.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(Activity_MakeProfile.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri resultUri = data.getData();
        make_IMG_circle_image.setImageURI(resultUri);
        firebaseS.uploadImageProfile(resultUri, userDataManager.getUID(), this);
    }

    private void openActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    private void setDropdown(){
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.dropdown_item, UserDataManager.currencyList);
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

        userDataManager.setMyUser(firstName, lastName, email, currency, urlIMG);

        openActivity(Activity_Panel.class);
    }

    private void findViews() {
        make_BTN_register = findViewById(R.id.make_BTN_register);
        make_textInputEditText_firstName = findViewById(R.id.make_textInputEditText_firstName);
        make_textInputEditText_lastName = findViewById(R.id.make_textInputEditText_lastName);
        make_textInputEditText_email = findViewById(R.id.make_textInputEditText_email);
        make_autoCompleteTextView = findViewById(R.id.make_autoCompleteTextView);
        make_IMG_circle_image = findViewById(R.id.make_IMG_circle_image);
    }
}