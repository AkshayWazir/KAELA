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
import in.indilabz.student_helper.kaela.Interfaces.StuFraInt;
import in.indilabz.student_helper.kaela.ModelObjects.ClassObjectStudents;
import in.indilabz.student_helper.kaela.R;


public class FragMainStu extends Fragment {
    private View view;
    private StuFraInt interact;

    public void setInteract(StuFraInt interact) {
        this.interact = interact;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_frag_main_stu, container, false);
        rcViewSetup();
        return view;
    }

    private void rcViewSetup() {
        RecyclerView rcView = view.findViewById(R.id.id_stu_main_rcview);
        rcView.setHasFixedSize(true);
        rcView.setLayoutManager(new LinearLayoutManager(getContext()));
        StudentAdapter adapter = new StudentAdapter(getContext(), getDataForDisplay());
        adapter.setTeachCount(getFacultyCounter());
        adapter.setInteract(interact);
        rcView.setAdapter(adapter);
    }

    private int[] getFacultyCounter() {
        return new int[]{45, 56, 67, 45, 11, 2, 59, 8, 1, 32, 65, 45};
    }

    private ArrayList<ClassObjectStudents> getDataForDisplay() {
        ArrayList<ClassObjectStudents> container = new ArrayList<>();

        String[] subs = {"Hindi", "English", "Maths", "SST", "Physical Education"};

        container.add(new ClassObjectStudents("5", subs));
        container.add(new ClassObjectStudents("6", subs));
        container.add(new ClassObjectStudents("7", subs));
        container.add(new ClassObjectStudents("8", subs));
        container.add(new ClassObjectStudents("9", subs));
        container.add(new ClassObjectStudents("10", subs));
        container.add(new ClassObjectStudents("11", subs, "Science"));
        container.add(new ClassObjectStudents("11", subs, "Commerce"));
        container.add(new ClassObjectStudents("11", subs, "Arts"));
        container.add(new ClassObjectStudents("12", subs, "Science"));
        container.add(new ClassObjectStudents("12", subs, "Commerce"));
        container.add(new ClassObjectStudents("12", subs, "Arts"));

        return container;
    }
}
