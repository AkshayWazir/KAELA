
package in.indilabz.student_helper.kaela.StudentActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import in.indilabz.student_helper.kaela.Adapters.TeacherAdapter;
import in.indilabz.student_helper.kaela.Interfaces.AskQuestion;
import in.indilabz.student_helper.kaela.ModelObjects.TeacherObject;
import in.indilabz.student_helper.kaela.PublicLinks;
import in.indilabz.student_helper.kaela.R;

public class ShowTeachers extends AppCompatActivity implements AskQuestion {
    RecyclerView recyclerView;
    ArrayList<String> teachersId;
    Button submit_btn;
    TeacherAdapter adapter;
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_teachers);

        Intent intent = getIntent();
        String subId = intent.getStringExtra("SUB_ID");

        recyclerView = findViewById(R.id.teacher_rcview);
        submit_btn = findViewById(R.id.button3);
        bar = findViewById(R.id.progressBar4);

        adapter = new TeacherAdapter(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        getTeachers(subId);
        adapter.setQuestion(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        teachersId = new ArrayList<>();
        submit_btn.setVisibility(View.INVISIBLE);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AskActivityQuestion.class);
                intent.putExtra("TEACHER_ID", teachersId);
                startActivity(intent);
            }
        });
    }

    void getTeachers(final String subid) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, PublicLinks.FETCH_TEACHERS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        for (int i = 0; i < 2; i++) {
                            System.out.println(i);
                        }
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            ArrayList<TeacherObject> objects = new ArrayList<>();
                            String success = jsonObject.getString("result");
                            if (!success.equals("-1")) {
                                JSONObject object = new JSONObject(response);
                                for (int i = 0; i < object.getJSONArray("dat").length(); i++) {
                                    JSONObject obj = object.getJSONArray("dat").getJSONObject(i);
                                    objects.add(new TeacherObject("", obj.getString("name"), obj.getString("exp"), obj.getString("rating"),obj.getString("tea_id")));
                                }
                                bar.setVisibility(View.GONE);
                                adapter.setObjects(objects);
                            } else {
                                Toast.makeText(getApplicationContext(), "No Data Available", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Login Failed : " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("sub_id", subid);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(this));
        requestQueue.add(stringRequest);
    }

    @Override
    public void selectTeacher(String teachId) {
        teachersId.add(teachId);
        submit_btn.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeTeacher(String teachId) {
        teachersId.remove(teachId);
        if (teachId.length() == 0) {
            submit_btn.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showProfile(String teachId) {
        startActivity(new Intent(this, TeacherProfile.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.teacher_filter, menu);
        MenuItem searchItem = menu.findItem(R.id.id_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }
}
