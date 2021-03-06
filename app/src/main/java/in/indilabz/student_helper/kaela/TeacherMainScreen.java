package in.indilabz.student_helper.kaela;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import in.indilabz.student_helper.kaela.Interfaces.QuesInter;
import in.indilabz.student_helper.kaela.Services.TeacherJobScheduler;
import in.indilabz.student_helper.kaela.Services.TeacherServices;
import in.indilabz.student_helper.kaela.StudentActivities.TeacherProfile;
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
        bottomNav.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                switch (position) {
                    case (0):
                        startActivity(new Intent(getApplicationContext(), TeacherProfile.class));
                        bottomNav.setCurrentActiveItem(2);
                        break;
                    case (1):
                        FragmentManager manager = getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.frame_teach_container, FragAskQuestion).commit();
                        break;
                }
            }
        });


        ComponentName componentName = new ComponentName(this, TeacherJobScheduler.class);
        JobInfo info = new JobInfo.Builder(123, componentName)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int result = scheduler.schedule(info);
        if (result == JobScheduler.RESULT_SUCCESS) {
            Toast.makeText(this, "Schedudled", Toast.LENGTH_SHORT).show();
        } else {
            Intent serviceIntent = new Intent(this, TeacherServices.class);
            TeacherServices.enqueWork(this, serviceIntent);
            Toast.makeText(this, "Lower Job", Toast.LENGTH_SHORT).show();
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
                FirebaseAuth.getInstance().signOut();
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
