package in.indilabz.student_helper.kaela;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import in.indilabz.student_helper.kaela.Fragments.FragChildSignup;
import in.indilabz.student_helper.kaela.Fragments.FragTeacherSignup;
import in.indilabz.student_helper.kaela.Fragments.FragmentChooseSignup;
import in.indilabz.student_helper.kaela.Fragments.IntroScreenFrag;
import in.indilabz.student_helper.kaela.Fragments.LoginFragment;
import in.indilabz.student_helper.kaela.Interfaces.FragInteract;

import in.indilabz.student_helper.kaela.R;

public class IntroductionActivity extends AppCompatActivity implements FragInteract {
    FrameLayout fragContainer;

    IntroScreenFrag frag;
    LoginFragment fragLog;
    FragmentChooseSignup fragSign;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        fragContainer = findViewById(R.id.frame_layout_id);
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
        fragContainer.setAnimation(anim);
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
                FragChildSignup temp1 = new FragChildSignup();
                temp1.setInteract(this);
                transaction.replace(R.id.frame_layout_id, temp1);
                transaction.commit();
                break;
            case (4):
                FragTeacherSignup temp2 = new FragTeacherSignup();
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
                startActivity(new Intent(this, TeacherMainScreen.class));
                finish();
                break;
            case (2):
                startActivity(new Intent(this, AdminMainScreen.class));
                finish();
                break;
        }
    }

    @Override
    public void regisTeacher() {

    }

}
