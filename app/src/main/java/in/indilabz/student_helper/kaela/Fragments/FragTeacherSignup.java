package in.indilabz.student_helper.kaela.Fragments;

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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import in.indilabz.student_helper.kaela.Interfaces.FragInteract;
import in.indilabz.student_helper.kaela.PublicLinks;
import in.indilabz.student_helper.kaela.R;

public class FragTeacherSignup extends Fragment {
    private FragInteract interact;
    private View view;
    private ProgressBar bar;

    public void setInteract(FragInteract interact) {
        this.interact = interact;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_frag_teacher_signup, container, false);
        bar = view.findViewById(R.id.progressBar3);
        view.findViewById(R.id.imageView8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interact.navigateToFragment(2);
            }
        });

        view.findViewById(R.id.cardView5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateContent()) {
                    String[] result = getResultantString();
                    updateUi(true);
                    registerUser(result);
                } else {
                    Toast.makeText(getContext(), "Some Fields are empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    void updateUi(boolean state) {
        if (state) {
            bar.setVisibility(View.VISIBLE);
        } else {
            bar.setVisibility(View.INVISIBLE);
        }
    }

    private String[] getResultantString() {
        TextInputLayout name = view.findViewById(R.id.textInputLayout3);
        TextInputLayout phone = view.findViewById(R.id.textInputLayout4);
        TextInputLayout email = view.findViewById(R.id.textInputLayout5);
        TextInputLayout password = view.findViewById(R.id.textInputLayout6);
        TextInputLayout experience = view.findViewById(R.id.textInputLayout8);
        String[] objts = {name.getEditText().getText().toString()
                , phone.getEditText().getText().toString()
                , email.getEditText().getText().toString()
                , password.getEditText().getText().toString()
                , experience.getEditText().getText().toString()};
        return objts;
    }

    private boolean validateContent() {
        TextInputLayout name = view.findViewById(R.id.textInputLayout3);
        TextInputLayout phone = view.findViewById(R.id.textInputLayout4);
        TextInputLayout email = view.findViewById(R.id.textInputLayout5);
        TextInputLayout password = view.findViewById(R.id.textInputLayout6);
        TextInputLayout experience = view.findViewById(R.id.textInputLayout8);
        return !name.getEditText().getText().toString().equals("")
                && !phone.getEditText().getText().toString().equals("")
                && !email.getEditText().getText().toString().equals("")
                && !password.getEditText().getText().toString().equals("")
                && !experience.getEditText().getText().toString().equals("");
    }

    private void registerUser(final String[] objects) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, PublicLinks.TEACHER_URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("RESPONSE");
                            if (success.equals("1")) {
                                SharedPreferences prefs = getContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString("NAME", objects[0]);
                                editor.putString("EMAIL", objects[2]);
                                editor.putInt("TYPE", 1);
                                editor.commit();
                                interact.registerComplete(1);
                                Toast.makeText(getContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                            } else {
                                updateUi(false);
                                Toast.makeText(getContext(), "Failed to write Data", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            updateUi(false);
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        updateUi(false);
                        Toast.makeText(getContext(), "SignUp Failed :" + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", objects[0]);
                params.put("contact", objects[1]);
                params.put("mail", objects[2]);
                params.put("pass", objects[3]);
                params.put("exp", objects[4]);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        requestQueue.add(stringRequest);
    }
}
