package in.indilabz.student_helper.kaela;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

import in.indilabz.student_helper.kaela.Interfaces.FragInteract;
import in.indilabz.student_helper.kaela.IntroFragments.SignUpStuFrag;
import in.indilabz.student_helper.kaela.IntroFragments.SignupTeachFrag;
import in.indilabz.student_helper.kaela.IntroFragments.FragmentChooseSignup;
import in.indilabz.student_helper.kaela.IntroFragments.IntroScreenFrag;
import in.indilabz.student_helper.kaela.IntroFragments.LoginFragment;
import in.indilabz.student_helper.kaela.TeaActivity.SubSelcActivity;

public class IntroductionActivity extends AppCompatActivity implements FragInteract {
    FrameLayout fragContainer;

    IntroScreenFrag frag;
    LoginFragment fragLog;
    FragmentChooseSignup fragSign;
    CardView intCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        fragContainer = findViewById(R.id.frame_layout_id);
        intCard = findViewById(R.id.intro_cardview);
        animationSequence1();

        frag = new IntroScreenFrag();
        frag.setInteract(this);

        fragLog = new LoginFragment();
        fragLog.setInteract(this);

        fragSign = new FragmentChooseSignup();
        fragSign.setInteract(this);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout_id, frag);
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_left);
        transaction.commit();
    }


    private void animationSequence1() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.intro_translate);
        anim.setFillAfter(true);
        anim.setFillEnabled(true);
        intCard.setAnimation(anim);
    }

    @Override
    public void navigateToFragment(int id) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_left);
        switch (id) {
            case (1):
                transaction.replace(R.id.frame_layout_id, fragLog);
                transaction.commit();
                break;
            case (2):
                transaction.replace(R.id.frame_layout_id, fragSign);
                transaction.commit();
                break;
            case (-1):
                transaction.replace(R.id.frame_layout_id, frag);
                transaction.commit();
                break;
            case (3):
                SignUpStuFrag temp1 = new SignUpStuFrag();
                temp1.setInteract(this);
                transaction.replace(R.id.frame_layout_id, temp1);
                transaction.commit();
                break;
            case (4):
                SignupTeachFrag temp2 = new SignupTeachFrag();
                temp2.setInteract(this);
                transaction.replace(R.id.frame_layout_id, temp2);
                transaction.commit();
                break;
        }
    }

    @Override
    public void registerComplete(int direc) {

        switch (direc) {
            case (0):
                startActivity(new Intent(this, StudentMainScreen.class));
                finish();
                break;
            case (1):
                startActivity(new Intent(this, SubSelcActivity.class));
                finish();
                break;
            case (2):
                startActivity(new Intent(this, AdminMainScreen.class));
                finish();
                break;
        }
    }

    @Override
    public void loginUser(int direc) {
        switch (direc) {
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
