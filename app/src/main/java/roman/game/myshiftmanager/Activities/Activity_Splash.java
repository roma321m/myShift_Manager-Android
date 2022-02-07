package roman.game.myshiftmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import roman.game.myshiftmanager.DB.FirebaseDB;
import roman.game.myshiftmanager.Managers.FirebaseAuthManager;
import roman.game.myshiftmanager.R;

public class Activity_Splash extends AppCompatActivity {

    private static final String TAG = Activity_Splash.class.getSimpleName();

    final int ANIM_DURATION = 4400;

    private ImageView splash_IMG_logo;

    FirebaseAuthManager firebaseAuthManager;
    FirebaseDB firebaseDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove notification bar
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        findViews();

        firebaseAuthManager = FirebaseAuthManager.getInstance();
        firebaseDB = FirebaseDB.getInstance();
        firebaseDB.setCallback_splashCheck(callback_splashCheck);

        splash_IMG_logo.setVisibility(View.INVISIBLE);

        showViewSlideDown(splash_IMG_logo);
    }

    public void showViewSlideDown(final View v) {
        v.setVisibility(View.VISIBLE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        v.setY(-height / 2);
        v.setRotation(0.0f);
        v.setScaleY(0.3f);
        v.setScaleX(0.3f);
        v.animate()
                .scaleY(1.0f)
                .scaleX(1.0f)
                .rotation(360.0f)
                .translationY(0)
                .setDuration(ANIM_DURATION)
                .setInterpolator(new AccelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animationDone();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
    }

    private void animationDone() {
        FirebaseUser user = firebaseAuthManager.getUser();
        if(user != null){
            // FIXME: 07/02/2022 firebaseDB.hasProfile(user.getUid());
            //temp
            Toast.makeText(this, ""+user.getPhoneNumber(), Toast.LENGTH_SHORT).show();
            openActivity(Activity_Panel.class);
        }else{
            openActivity(Activity_Login.class);
        }
    }

    private void openActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    private void findViews() {
        splash_IMG_logo = findViewById(R.id.splash_IMG_logo);
    }

    FirebaseDB.Callback_splashCheck callback_splashCheck = new FirebaseDB.Callback_splashCheck() {
        @Override
        public void profileExist(String userID) {
            Toast.makeText(Activity_Splash.this, ""+userID, Toast.LENGTH_SHORT).show();
            openActivity(Activity_Panel.class);
        }

        @Override
        public void makeProfile() {
            openActivity(Activity_MakeProfile.class);
        }
    };
}