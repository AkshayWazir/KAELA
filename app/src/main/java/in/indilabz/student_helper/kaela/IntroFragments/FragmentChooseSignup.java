package in.indilabz.student_helper.kaela.IntroFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import in.indilabz.student_helper.kaela.Interfaces.FragInteract;
import in.indilabz.student_helper.kaela.R;


public class FragmentChooseSignup extends Fragment {
    private FragInteract interact;

    public void setInteract(FragInteract interact) {
        this.interact = interact;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_signup, container, false);

        view.findViewById(R.id.imageView7)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        interact.navigateToFragment(-1);
                    }
                });
        view.findViewById(R.id.id_student_selected)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        interact.navigateToFragment(3);
                    }
                });
        view.findViewById(R.id.id_teacher_selected)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        interact.navigateToFragment(4);
                    }
                });
        return view;
    }
}
