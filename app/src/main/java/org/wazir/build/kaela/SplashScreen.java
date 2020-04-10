package org.wazir.build.kaela;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    FrameLayout c1, c2, c3;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c1 = findViewById(R.id.card_purple);
        c2 = findViewById(R.id.card_green);
        c3 = findViewById(R.id.card_red);
        title = findViewById(R.id.textView);

        final Animation a = AnimationUtils.loadAnimation(this, R.anim.fade_in_animation);
        final Animation b = AnimationUtils.loadAnimation(this, R.anim.fade_in_animation);
        final Animation c = AnimationUtils.loadAnimation(this, R.anim.fade_in_animation);
        final Animation d = AnimationUtils.loadAnimation(this, R.anim.fade_in_animation);

        a.setFillAfter(true);
        b.setFillAfter(true);
        c.setFillAfter(true);
        d.setFillAfter(true);

        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                c2.startAnimation(b);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        b.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                c3.startAnimation(c);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        c.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                title.startAnimation(d);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        d.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                checkCurrentPresentUser();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        c1.startAnimation(a);

    }

    void checkCurrentPresentUser() {
        SharedPreferences sp = getSharedPreferences("USER_STATUS", MODE_PRIVATE);
        if (sp.getBoolean("USER_LOGED_IN", false)) {

        } else {
            startActivity(new Intent(this, IntroductionActivity.class));
            finish();
        }
    }
}
