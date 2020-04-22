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

    void rcViewSetup() {
        RecyclerView rcView = view.findViewById(R.id.id_stu_main_rcview);
        rcView.setHasFixedSize(true);
        rcView.setLayoutManager(new LinearLayoutManager(getContext()));
        StudentAdapter adapter = new StudentAdapter(getContext(), getDataForDisplay());
        adapter.setInteract(interact);
        rcView.setAdapter(adapter);
    }

    private ArrayList<ClassObjectStudents> getDataForDisplay() {
        ArrayList<ClassObjectStudents> container = new ArrayList<>();
        ClassObjectStudents obj = new ClassObjectStudents();
        obj.setTitle("5");
        String[] subs = {"Hindi", "English", "Maths", "SST", "Physical Education"};
        obj.setSubs(subs);
        container.add(obj);
        obj.setTitle("6");
        obj.setSubs(subs);
        container.add(obj);
        container.add(obj);
        container.add(obj);
        container.add(obj);
        container.add(obj);
        container.add(obj);
        container.add(obj);
        container.add(obj);
        return container;
    }
}
