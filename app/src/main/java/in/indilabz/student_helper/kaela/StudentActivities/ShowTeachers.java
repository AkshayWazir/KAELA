
package in.indilabz.student_helper.kaela.StudentActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.indilabz.student_helper.kaela.Adapters.TeacherAdapter;
import in.indilabz.student_helper.kaela.Interfaces.AskQuestion;
import in.indilabz.student_helper.kaela.ModelObjects.TeacherObject;
import in.indilabz.student_helper.kaela.R;

public class ShowTeachers extends AppCompatActivity implements AskQuestion {
    RecyclerView recyclerView;
    ArrayList<String> teachersId;
    Button submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_teachers);
        recyclerView = findViewById(R.id.teacher_rcview);
        submit_btn = findViewById(R.id.button3);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        TeacherAdapter adapter = new TeacherAdapter(this, getTeachers());
        adapter.setQuestion(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        teachersId = new ArrayList<>();
        submit_btn.setVisibility(View.INVISIBLE);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AskActivityQuestion.class);
                intent.putExtra("TEACHER_ID", teachersId);
                startActivity(intent);
            }
        });
    }


    TeacherObject[] getTeachers() {
        TeacherObject[] objects = {
                new TeacherObject("", "Ram Raheem", "Professor", "3"),
                new TeacherObject("", "Ram Raheem", "Professor", "2"),
                new TeacherObject("", "Ram Raheem", "Professor", "4"),
                new TeacherObject("", "Ram Raheem", "Professor", "5"),
                new TeacherObject("", "Ram Raheem", "Professor", "3"),
                new TeacherObject("", "Ram Raheem", "Professor", "3"),
                new TeacherObject("", "Ram Raheem", "Professor", "4"),
                new TeacherObject("", "Ram Raheem", "Professor", "5"),
                new TeacherObject("", "Ram Raheem", "Professor", "1"),
                new TeacherObject("", "Ram Raheem", "Professor", "1"),
                new TeacherObject("", "Ram Raheem", "Professor", "3"),
                new TeacherObject("", "Ram Raheem", "Professor", "3"),
                new TeacherObject("", "Ram Raheem", "Professor", "3"),
                new TeacherObject("", "Ram Raheem", "Professor", "3")
        };
        return objects;
    }

    @Override
    public void selectTeacher(String teachId) {
        teachersId.add(teachId);
        submit_btn.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeTeacher(String teachId) {
        teachersId.remove(teachId);
        if (teachId.length() == 0) {
            submit_btn.setVisibility(View.INVISIBLE);
        }
    }
}
