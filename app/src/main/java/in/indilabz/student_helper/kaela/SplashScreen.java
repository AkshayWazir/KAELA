package in.indilabz.student_helper.kaela;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {
    LottieAnimationView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        view = findViewById(R.id.lottieAnimationView);
        view.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                checkCurrentPresentUser();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    void checkCurrentPresentUser() {
        SharedPreferences sharedPref = this.getSharedPreferences("USER", MODE_PRIVATE);
        int highScore = sharedPref.getInt("TYPE", -1);
        switch (highScore) {
            case (-1):
                startActivity(new Intent(this, IntroductionActivity.class));
                finish();
                break;
            case (0):
                startActivity(new Intent(this, StudentMainScreen.class));
                finish();
                break;
            case (1):
                startActivity(new Intent(this, TeacherMainScreen.class));
                finish();
                break;
            case (2):
                startActivity(new Intent(this, AdminMainScreen.class));
                finish();
                break;
        }
    }
}
