package roman.game.myshiftmanager.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import roman.game.myshiftmanager.R;

public class Activity_MakeWorkplace extends AppCompatActivity {

    private MaterialButton workplace_BTN_back, workplace_BTN_save;
    private TextInputEditText workplace_textInputEditText_name, workplace_textInputEditText_hourly,workplace_textInputEditText_vacation
            ,workplace_textInputEditText_deduction, workplace_textInputEditText_bonuses, workplace_textInputEditText_break,
            workplace_textInputEditText_daily, workplace_textInputEditText_monthly;
    private RadioGroup workplace_RG_color;
    private ShapeableImageView workplace_IMG_blue, workplace_IMG_green, workplace_IMG_red, workplace_IMG_purple;

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

        // TODO: 09/02/2022 - edt input 

        // TODO: 09/02/2022 - radio group for picking color 
    }

    private void findViews() {
        workplace_BTN_back = findViewById(R.id.workplace_BTN_back);
        workplace_BTN_save = findViewById(R.id.workplace_BTN_save);
        workplace_textInputEditText_name = findViewById(R.id.workplace_textInputEditText_name);
        workplace_textInputEditText_hourly = findViewById(R.id.workplace_textInputEditText_hourly);
        workplace_textInputEditText_vacation = findViewById(R.id.workplace_textInputEditText_vacation);
        workplace_textInputEditText_deduction = findViewById(R.id.workplace_textInputEditText_deduction);
        workplace_textInputEditText_bonuses = findViewById(R.id.workplace_textInputEditText_bonuses);
        workplace_textInputEditText_break = findViewById(R.id.workplace_textInputEditText_break);
        workplace_textInputEditText_daily = findViewById(R.id.workplace_textInputEditText_daily);
        workplace_textInputEditText_monthly = findViewById(R.id.workplace_textInputEditText_monthly);
        workplace_RG_color = findViewById(R.id.workplace_RG_color);
        workplace_IMG_blue = findViewById(R.id.workplace_IMG_blue);
        workplace_IMG_green = findViewById(R.id.workplace_IMG_green);
        workplace_IMG_red = findViewById(R.id.workplace_IMG_red);
        workplace_IMG_purple = findViewById(R.id.workplace_IMG_purple);
    }
}