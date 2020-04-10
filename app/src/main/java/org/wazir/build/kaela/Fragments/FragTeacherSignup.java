package org.wazir.build.kaela.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import org.wazir.build.kaela.Interfaces.FragInteract;
import org.wazir.build.kaela.R;

public class FragTeacherSignup extends Fragment {
    FragInteract interact;

    public void setInteract(FragInteract interact) {
        this.interact = interact;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_teacher_signup, container, false);
        view.findViewById(R.id.imageView8)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        interact.navigateToFragment(2);
                    }
                });
        return view;
    }
}
