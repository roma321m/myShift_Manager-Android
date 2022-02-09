package roman.game.myshiftmanager.Activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import roman.game.myshiftmanager.R;

public class Activity_MakeWorkplace extends AppCompatActivity {

    private MaterialButton workplace_BTN_back, workplace_BTN_save;
    private TextInputEditText workplace_textInputEditText_name, workplace_textInputEditText_hourly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_workplace);

        findViews();
        
        workplace_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        workplace_BTN_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 09/02/2022 - create new workplace for the user
            }
        });
    }

    private void findViews() {
        workplace_BTN_back = findViewById(R.id.workplace_BTN_back);
        workplace_BTN_save = findViewById(R.id.workplace_BTN_save);
    }
}