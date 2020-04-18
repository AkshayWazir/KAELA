package in.indilabz.student_helper.kaela.IntroFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;
import in.indilabz.student_helper.kaela.Interfaces.FragInteract;
import in.indilabz.student_helper.kaela.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import in.indilabz.student_helper.kaela.PublicLinks;

public class FragChildSignup extends Fragment {
    private FragInteract interact;
    private ProgressBar bar;
    private View view;
    private Context ctx;

    public void setInteract(FragInteract interact) {
        this.interact = interact;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_frag_child_signup, container, false);
        bar = view.findViewById(R.id.progressBar);
        ctx = getContext();
        view.findViewById(R.id.imageView9)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        interact.navigateToFragment(2);
                    }
                });

        view.findViewById(R.id.create_account_student)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getData();
                    }
                });
        return view;
    }

    private void getData() {
        TextInputLayout name_et = view.findViewById(R.id.textInputLayout3);
        String name = name_et.getEditText().getText().toString();

        TextInputLayout contact_et = view.findViewById(R.id.textInputLayout4);
        String contact = contact_et.getEditText().getText().toString();

        TextInputLayout email_et = view.findViewById(R.id.textInputLayout5);
        String email = email_et.getEditText().getText().toString();

        TextInputLayout password_et = view.findViewById(R.id.textInputLayout6);
        String password = password_et.getEditText().getText().toString();

        TextInputLayout fname_et = view.findViewById(R.id.textInputLayout8);
        String fname = fname_et.getEditText().getText().toString();

        TextInputLayout city_et = view.findViewById(R.id.textInputLayout9);
        String city = city_et.getEditText().getText().toString();

        TextInputLayout school_et = view.findViewById(R.id.textInputLayout10);
        String school = school_et.getEditText().getText().toString();

        if (name.equals("") || contact.equals("") || email.equals("") || password.equals("") || fname.equals("") || city.equals("") || school.equals("")) {
            Toast.makeText(getContext(), "Some Fields are empty", Toast.LENGTH_SHORT).show();
        } else {
            String[] res = {name, email, password, contact, fname, city, school};
            registerStudent(res);
        }
    }

    private void updateUI(boolean state) {
        if (state) {
            bar.setVisibility(View.VISIBLE);
        } else {
            bar.setVisibility(View.INVISIBLE);
        }
    }

    private void registerStudent(final String[] object) {
        updateUI(true);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, PublicLinks.STUDENT_URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("RESPONSE");
                            if (success.equals("1")) {

                                SharedPreferences prefs = ctx.getSharedPreferences("USER", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString("NAME", object[0]);
                                editor.putString("EMAIL", object[1]);
                                editor.putInt("TYPE", 0);
                                editor.commit();
                                interact.registerComplete(0);
                                Toast.makeText(getContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(ctx, "Error : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "SignUp Failed : " + error.getStackTrace(), Toast.LENGTH_LONG).show();
                        updateUI(false);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", object[0]);
                params.put("email", object[1]);
                params.put("password", object[2]);
                params.put("contact", object[3]);
                params.put("father", object[4]);
                params.put("city", object[5]);
                params.put("school", object[6]);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        requestQueue.add(stringRequest);
    }
}
