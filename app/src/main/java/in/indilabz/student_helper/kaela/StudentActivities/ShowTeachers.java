
package in.indilabz.student_helper.kaela.StudentActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

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
    TeacherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_teachers);
        recyclerView = findViewById(R.id.teacher_rcview);
        submit_btn = findViewById(R.id.button3);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new TeacherAdapter(this, getTeachers());
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


    ArrayList<TeacherObject> getTeachers() {
        ArrayList<TeacherObject> objects = new ArrayList<>();
        objects.add(new TeacherObject("", "Ram Raheem", "Professor", "3"));
        objects.add(new TeacherObject("", "Akshay Rein", "Professor", "2"));
        objects.add(new TeacherObject("", "Amisha Gupta", "Professor", "2"));
        objects.add(new TeacherObject("", "Amit Dubey", "Professor", "2"));
        objects.add(new TeacherObject("", "Shiwanshu Goshain", "Professor", "2"));
        objects.add(new TeacherObject("", "Karan Saspal", "Professor", "2"));
        objects.add(new TeacherObject("", "Deeku Topper", "Professor", "2"));
        objects.add(new TeacherObject("", "Maheep Walia", "Professor", "2"));
        objects.add(new TeacherObject("", "Ashwani Gupta", "Professor", "2"));
        objects.add(new TeacherObject("", "Topi Master", "Professor", "2"));
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

    @Override
    public void showProfile(String teachId) {
        startActivity(new Intent(this, TeacherProfile.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.teacher_filter, menu);
        MenuItem searchItem = menu.findItem(R.id.id_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }
}
