package in.indilabz.student_helper.kaela;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

import in.indilabz.student_helper.kaela.Adapters.StudentAdapter;
import in.indilabz.student_helper.kaela.ModelObjects.ClassObjectStudents;
import in.indilabz.student_helper.kaela.ModelObjects.TeacherObject;

public class StudentMainScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main_screen);
        // toolbar event handler
        toolbarEventsHandler();
        // recycler Setup
        rcViewSetup();


    }

    void toolbarEventsHandler() {
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapse_id);
        AppBarLayout appBarLayout = findViewById(R.id.appbar_id);
        final Toolbar toolbar = findViewById(R.id.toolbar_id);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("Student Panel");
                    toolbar.setBackgroundColor(getColor(R.color.colorPrimary));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    toolbar.setBackgroundColor(Color.TRANSPARENT);
                    isShow = false;
                }
            }
        });
    }

    void rcViewSetup() {
        RecyclerView rcView = findViewById(R.id.id_stu_main_rcview);
        rcView.setHasFixedSize(true);
        rcView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rcView.setAdapter(new StudentAdapter(this, getDataForDisplay()));
    }

    private ArrayList<ClassObjectStudents> getDataForDisplay() {
        ArrayList<ClassObjectStudents> container = new ArrayList<>();
        ClassObjectStudents obj = new ClassObjectStudents();
        obj.setTitle("Class 5th Section");
        String[] subs = {"Hindi", "English", "Maths", "SST", "Physical Education"};
        obj.setSubs(subs);
        TeacherObject[] objects = {
                new TeacherObject("", "Rancho", "Teacher", "4"),
                new TeacherObject("", "Pappu", "Teacher", "4"),
                new TeacherObject("", "Jethalal", "Teacher", "4"),
                new TeacherObject("", "Tappu k papa", "Teacher", "4"),
                new TeacherObject("", "Salmon Khoon", "Teacher", "4"),
                new TeacherObject("", "Bhiide", "Teacher", "4"),
                new TeacherObject("", "Rancho", "Teacher", "4")
        };
        obj.setTeachers(objects);
        container.add(obj);
        container.add(obj);
        container.add(obj);
        container.add(obj);
        container.add(obj);
        container.add(obj);
        return container;
    }

}
