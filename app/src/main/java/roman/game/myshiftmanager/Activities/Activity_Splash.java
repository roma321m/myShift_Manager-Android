package roman.game.myshiftmanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;

import roman.game.myshiftmanager.UserData.FirebaseDB;
import roman.game.myshiftmanager.UserData.UserDataManager;
import roman.game.myshiftmanager.R;

public class Activity_Splash extends AppCompatActivity {

    private final int ANIM_DURATION = 2000;
    private ImageView splash_IMG_logo;
    private ProgressBar splash_PB;

    UserDataManager userDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        findViews();

        userDataManager = UserDataManager.getInstance();
        userDataManager.setCallback_checkUserExistence(callback_checkUserExistence);

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
        splash_PB.setVisibility(View.VISIBLE);
        if(userDataManager.checkIfHasUID()){
            userDataManager.checkIfHasProfile();
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

    FirebaseDB.Callback_checkUserExistence callback_checkUserExistence = new FirebaseDB.Callback_checkUserExistence() {
        @Override
        public void profileExist() {
            openActivity(Activity_Panel.class);
        }

        @Override
        public void makeProfile() {
            openActivity(Activity_MakeProfile.class);
        }
    };

    private void findViews() {
        splash_IMG_logo = findViewById(R.id.splash_IMG_logo);
        splash_PB = findViewById(R.id.splash_PB);
    }
}