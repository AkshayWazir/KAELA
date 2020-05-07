package in.indilabz.student_helper.kaela;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;

import in.indilabz.student_helper.kaela.Interfaces.QuesInter;
import in.indilabz.student_helper.kaela.TeaActivity.SolutionActivity;
import in.indilabz.student_helper.kaela.TeacherFragments.QuestioningPannel;

public class TeacherMainScreen extends AppCompatActivity implements QuesInter {
    BubbleNavigationConstraintView bottomNav;
    QuestioningPannel FragAskQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main_screen);
        bottomNav = findViewById(R.id.id_teach_navBar);
        FragAskQuestion = new QuestioningPannel();
        FragAskQuestion.setCtx(this);
        if (savedInstanceState == null) {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.frame_teach_container, FragAskQuestion).commit();
            bottomNav.setCurrentActiveItem(2);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_stu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.id_logout):
                SharedPreferences prefs = this.getSharedPreferences("USER", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("NAME", "");
                editor.putString("EMAIL", "");
                editor.putInt("TYPE", -1);
                editor.commit();
                startActivity(new Intent(this, IntroductionActivity.class));
                finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void quesSelected(String quesId) {
        Intent intent = new Intent(this, SolutionActivity.class);
        intent.putExtra("QUES_ID", quesId);
        startActivity(intent);
    }
}
