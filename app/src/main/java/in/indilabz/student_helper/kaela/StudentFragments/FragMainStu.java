package in.indilabz.student_helper.kaela.StudentFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.indilabz.student_helper.kaela.Adapters.StudentAdapter;
import in.indilabz.student_helper.kaela.ModelObjects.ClassObjectStudents;
import in.indilabz.student_helper.kaela.ModelObjects.TeacherObject;
import in.indilabz.student_helper.kaela.R;


public class FragMainStu extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_frag_main_stu, container, false);
        rcViewSetup();
        return view;
    }

    void rcViewSetup() {
        RecyclerView rcView = view.findViewById(R.id.id_stu_main_rcview);
        rcView.setHasFixedSize(true);
        rcView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rcView.setAdapter(new StudentAdapter(getContext(), getDataForDisplay()));
    }

    private ArrayList<ClassObjectStudents> getDataForDisplay() {
        ArrayList<ClassObjectStudents> container = new ArrayList<>();
        ClassObjectStudents obj = new ClassObjectStudents();
        obj.setTitle("5");
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
