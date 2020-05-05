package in.indilabz.student_helper.kaela.TeacherFragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import in.indilabz.student_helper.kaela.ModelObjects.AdaUnsQueObj;
import in.indilabz.student_helper.kaela.PublicLinks;
import in.indilabz.student_helper.kaela.R;
import in.indilabz.student_helper.kaela.TeacherFragments.adptersTeach.AdaSolQue;
import in.indilabz.student_helper.kaela.TeacherFragments.adptersTeach.AdaUnsQue;

import static android.content.Context.MODE_PRIVATE;


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

        SharedPreferences sharedPref = getContext().getSharedPreferences("USER", MODE_PRIVATE);
        String mail = sharedPref.getString("TEACH_ID", "");
        setupAdas(mail);

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

    private void setupAdas(final String teacherId) {
        for (int i = 0; i < 1; i++) {
            System.out.println(i);
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, PublicLinks.SOLVED_UNSOLVED_FETCH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            for (int i = 0; i < 1; i++) {
                                System.out.println(i);
                            }
                            JSONObject jsonObject = new JSONObject(response);
                            ArrayList<AdaUnsQueObj> unsolvQues = new ArrayList<>();
                            ArrayList<AdaUnsQueObj> solvQues = new ArrayList<>();
                            String success = jsonObject.getString("result");
                            if (!success.equals("-1")) {
                                for (int i = 0; i < jsonObject.getJSONArray("dat").length(); i++) {
                                    JSONObject obj = jsonObject.getJSONArray("dat").getJSONObject(i);
                                    if (!obj.getString("solved").equals("-1")) {
                                        solvQues.add(new AdaUnsQueObj(obj.getString("ques_title"), obj.getString("ques_desc"), obj.getString("stu_name"), obj.getString("stu_id"), obj.getString("ques_id")));
                                    } else {
                                        unsolvQues.add(new AdaUnsQueObj(obj.getString("ques_title"), obj.getString("ques_desc"), obj.getString("stu_name"), obj.getString("stu_id"), obj.getString("ques_id")));
                                    }
                                }
                                ada1.setObjects(solvQues);
                                ada2.setObjects(unsolvQues);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getContext(), "Error : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Login Failed : " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("teach_id", teacherId);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        requestQueue.add(stringRequest);
    }
}
