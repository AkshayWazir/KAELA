package in.indilabz.student_helper.kaela.TeacherFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

import java.util.ArrayList;

import in.indilabz.student_helper.kaela.ModelObjects.AdaUnsQueObj;
import in.indilabz.student_helper.kaela.R;
import in.indilabz.student_helper.kaela.TeacherFragments.adptersTeach.AdaSolQue;
import in.indilabz.student_helper.kaela.TeacherFragments.adptersTeach.AdaUnsQue;


public class QuestioningPannel extends Fragment {
    private RecyclerView unRecView, soRecView;
    private AdaSolQue ada1;
    private AdaUnsQue ada2;
    private BubbleNavigationConstraintView bottomNav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questioning_pannel, container, false);
        unRecView = view.findViewById(R.id.id_unsolved);
        soRecView = view.findViewById(R.id.id_solved);
        bottomNav = view.findViewById(R.id.id_tea_ask_bar);

        soRecView.setLayoutManager(new LinearLayoutManager(getContext()));
        unRecView.setLayoutManager(new LinearLayoutManager(getContext()));

        ada1 = new AdaSolQue(getContext());
        ada2 = new AdaUnsQue(getContext());

        unRecView.setAdapter(ada2);
        soRecView.setAdapter(ada1);

        ada1.setObjects(getData());
        ada2.setObjects(getData());

        if (savedInstanceState == null) {
            bottomNav.setCurrentActiveItem(0);
            unRecView.setVisibility(View.VISIBLE);
        }

        bottomNav.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                switch (position) {
                    case (0):
                        unRecView.setVisibility(View.VISIBLE);
                        soRecView.setVisibility(View.GONE);
                        break;
                    case (1):
                        unRecView.setVisibility(View.GONE);
                        soRecView.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        return view;
    }

    private ArrayList<AdaUnsQueObj> getData() {
        ArrayList<AdaUnsQueObj> objects = new ArrayList<>();
        objects.add(new AdaUnsQueObj("Some Randome Question Comes here with Some More random Stuff", "Some Random Description to give more detail to what question is asking for", "Akshay Rein", "id", "123"));
        objects.add(new AdaUnsQueObj("Some Randome Question Comes here with Some More random Stuff", "Some Random Description to give more detail to what question is asking for", "Akshay Rein", "id", "123"));
        objects.add(new AdaUnsQueObj("Some Randome Question Comes here with Some More random Stuff", "Some Random Description to give more detail to what question is asking for", "Akshay Rein", "id", "123"));
        objects.add(new AdaUnsQueObj("Some Randome Question Comes here with Some More random Stuff", "Some Random Description to give more detail to what question is asking for", "Akshay Rein", "id", "123"));
        objects.add(new AdaUnsQueObj("Some Randome Question Comes here with Some More random Stuff", "Some Random Description to give more detail to what question is asking for", "Akshay Rein", "id", "123"));
        objects.add(new AdaUnsQueObj("Some Randome Question Comes here with Some More random Stuff", "Some Random Description to give more detail to what question is asking for", "Akshay Rein", "id", "123"));
        objects.add(new AdaUnsQueObj("Some Randome Question Comes here with Some More random Stuff", "Some Random Description to give more detail to what question is asking for", "Akshay Rein", "id", "123"));
        return objects;
    }
}
