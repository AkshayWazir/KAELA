package in.indilabz.student_helper.kaela;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

import in.indilabz.student_helper.kaela.StudentFragments.FragMainStu;

public class StudentMainScreen extends AppCompatActivity {
    FragMainStu main;
    BubbleNavigationConstraintView navBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main_screen);
        navBar = findViewById(R.id.navBar);
        main = new FragMainStu();
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
                        manager1
                                .beginTransaction()
                                .replace(R.id.frame_container_stu, main)
                                .commit();
                        break;
                    case (0):
                        Toast.makeText(StudentMainScreen.this, "Group Tapped", Toast.LENGTH_SHORT).show();
                        break;
                    case (2):
                        Toast.makeText(StudentMainScreen.this, "Chat Tapped", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
