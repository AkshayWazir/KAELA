package in.indilabz.student_helper.kaela.IntroFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.indilabz.student_helper.kaela.Interfaces.FragInteract;
import in.indilabz.student_helper.kaela.Networking.MySingleton;
import in.indilabz.student_helper.kaela.PublicLinks;
import in.indilabz.student_helper.kaela.R;

public class LoginFragment extends Fragment {
    private FragInteract interact;
    private View view;

    public void setInteract(FragInteract interact) {
        this.interact = interact;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);

        view.findViewById(R.id.cardView12)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        interact.navigateToFragment(-1);
                    }
                });

        view.findViewById(R.id.cardView13)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (validate()) {
                            String[] params = getData();
                            verifyLogin(params);
                        }
                    }
                });
        return view;
    }

    private void updateui(boolean state) {
        if (state) {
            view.findViewById(R.id.progressBar2).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.progressBar2).setVisibility(View.INVISIBLE);
        }
    }

    private String[] getData() {
        TextInputLayout mailEt = view.findViewById(R.id.textInputLayout);
        TextInputLayout passEt = view.findViewById(R.id.textInputLayout2);
        String[] res = {mailEt.getEditText().getText().toString(), passEt.getEditText().getText().toString()};
        return res;
    }

    private boolean validate() {
        TextInputLayout mailEt = view.findViewById(R.id.textInputLayout);
        TextInputLayout passEt = view.findViewById(R.id.textInputLayout2);
        return !mailEt.getEditText().getText().toString().equals("") &&
                !passEt.getEditText().getText().toString().equals("");
    }

    private void verifyLogin(final String[] object) {
        updateui(true);
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, PublicLinks.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("RESPONSE");
                            if (!success.equals("-1") && !success.equals("2")) {
                                SharedPreferences prefs = getContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = prefs.edit();
                                if (success.equals("1")) {
                                    editor.putString("NAME", jsonObject.getJSONObject("OBJ").getString("name"));
                                    editor.putString("EMAIL", jsonObject.getJSONObject("OBJ").getString("mail"));
                                    editor.putString("TEACH_ID", jsonObject.getJSONObject("TEACH_ID").getString("tea_id"));
                                } else if (success.equals("0")) {
                                    editor.putString("NAME", jsonObject.getJSONObject("OBJ").getString("stu_name"));
                                    editor.putString("EMAIL", jsonObject.getJSONObject("OBJ").getString("stu_email"));
                                }
                                editor.putInt("TYPE", Integer.parseInt(success));
                                editor.commit();

                                interact.loginUser(Integer.parseInt(success));
                                Toast.makeText(getContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "User Not found", Toast.LENGTH_SHORT).show();
                                updateui(false);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getContext(), "Error : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            updateui(false);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext().getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("mail", object[0]);
                params.put("pass", object[1]);
                return params;
            }
        };
        MySingleton.getInstance(getContext().getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }
}