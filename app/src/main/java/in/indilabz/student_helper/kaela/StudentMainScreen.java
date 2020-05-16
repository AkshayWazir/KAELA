package in.indilabz.student_helper.kaela;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

import in.indilabz.student_helper.kaela.Interfaces.QuesInter;
import in.indilabz.student_helper.kaela.Interfaces.StuFraInt;
import in.indilabz.student_helper.kaela.StudentActivities.ShowTeachers;
import in.indilabz.student_helper.kaela.StudentFragments.FragGroup;
import in.indilabz.student_helper.kaela.StudentFragments.FragMainStu;
import in.indilabz.student_helper.kaela.StudentFragments.FragShowSol;
import in.indilabz.student_helper.kaela.TeaActivity.SolutionActivity;

public class StudentMainScreen extends AppCompatActivity implements StuFraInt, QuesInter {
    FragMainStu main;
    FragGroup groups;
    FragShowSol solFrag;
    BubbleNavigationConstraintView navBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main_screen);
        navBar = findViewById(R.id.navBar);
        main = new FragMainStu();
        main.setInteract(this);
        solFrag = new FragShowSol();
        solFrag.setCtx(this);
        groups = new FragGroup();
        if (savedInstanceState == null) {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.frame_container_stu, main).commit();
            navBar.setCurrentActiveItem(1);
        }
        navBar.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                FragmentManager manager1 = getSupportFragmentManager();
                switch (position) {
                    case (1):
                        manager1.beginTransaction()
                                .replace(R.id.frame_container_stu, main)
                                .commit();
                        break;
                    case (0):
                        manager1.beginTransaction()
                                .replace(R.id.frame_container_stu, groups)
                                .commit();
                        break;
                    case (2):
                        manager1.beginTransaction()
                                .replace(R.id.frame_container_stu, solFrag)
                                .commit();
                        break;
                }
            }
        });
    }

    @Override
    public void showTeachers(String subId) {
        Intent intent = new Intent(this, ShowTeachers.class);
        intent.putExtra("SUB_ID", subId);
        startActivity(intent);
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
        for (int i = 0; i < 1; i++) {
            System.out.println(i);
        }
        Intent intent = new Intent(this, SolutionActivity.class);
        intent.putExtra("QUES_ID", quesId);
        startActivity(intent);
    }
}
