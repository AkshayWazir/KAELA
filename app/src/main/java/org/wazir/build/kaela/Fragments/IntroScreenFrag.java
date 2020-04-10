package org.wazir.build.kaela.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import org.wazir.build.kaela.Adapters.OnboardingAdapter;
import org.wazir.build.kaela.Interfaces.FragInteract;
import org.wazir.build.kaela.ModelObjects.OnboardingItemObject;
import org.wazir.build.kaela.R;

import java.util.ArrayList;

public class IntroScreenFrag extends Fragment {
    private OnboardingAdapter adapter;
    private LinearLayout onbdIndicator;
    private FragInteract interact;

    public void setInteract(FragInteract interact) {
        this.interact = interact;
    }

    public IntroScreenFrag() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_intro_screen, container, false);
        adapter = new OnboardingAdapter(prepareData());
        ViewPager2 onboardingVP = view.findViewById(R.id.viewpager_container);
        onboardingVP.setAdapter(adapter);
        onbdIndicator = view.findViewById(R.id.layout_onbd_indicator_id);
        view.findViewById(R.id.login_card_id)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (interact != null) {
                            interact.navigateToFragment(1);
                        }
                    }
                });
        view.findViewById(R.id.id_goto_signup)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (interact != null) {
                            interact.navigateToFragment(2);
                        }
                    }
                });

        layoutOnbdSetup();
        setCurrentIndicator(0);
        onboardingVP.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                setCurrentIndicator(position);
            }
        });

        return view;
    }

    void layoutOnbdSetup() {
        ImageView[] indicator = new ImageView[adapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicator.length; i++) {
            indicator[i] = new ImageView(getContext());
            indicator[i].setImageDrawable(ContextCompat.getDrawable(
                    getContext(),
                    R.drawable.onboard_indicator_inactive
            ));
            indicator[i].setLayoutParams(layoutParams);
            onbdIndicator.addView(indicator[i]);
        }
    }

    void setCurrentIndicator(int index) {
        int childCount = onbdIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView iv = (ImageView) onbdIndicator.getChildAt(i);
            if (index == i) {
                iv.setImageDrawable(
                        ContextCompat.getDrawable(getContext(), R.drawable.onboard_indicator_active)
                );
            } else {
                iv.setImageDrawable(
                        ContextCompat.getDrawable(getContext(), R.drawable.onboard_indicator_inactive)
                );
            }
        }
    }

    ArrayList<OnboardingItemObject> prepareData() {
        OnboardingItemObject obj1 = new OnboardingItemObject();
        obj1.setTitle("PROBLEM SOLVING SESSIONS");
        obj1.setDescription("Regular problem solving sessions with \n" +
                "your favorite mentors with best\n" +
                "practices and in shortest time\n" +
                "possible");
        obj1.setIcon(R.drawable.intro_icon1);

        OnboardingItemObject obj2 = new OnboardingItemObject();
        obj2.setTitle("LIVE LECURES");
        obj2.setDescription("We connect the teachers you love live \n" +
                "with you , so you learn better and \n" +
                "gain confidence ");
        obj2.setIcon(R.drawable.intro_icon2);

        OnboardingItemObject obj3 = new OnboardingItemObject();
        obj3.setTitle("DIRECT INTERACTION");
        obj3.setDescription("INTERACT WITH THE TEACHERS and \n" +
                "PROFESSORS DIRECTLY AND GET YOUR \n" +
                "QUERYS SOLVED");
        obj3.setIcon(R.drawable.intro_icon3);

        ArrayList<OnboardingItemObject> arr = new ArrayList<>();
        arr.add(obj1);
        arr.add(obj2);
        arr.add(obj3);

        return arr;
    }
}
