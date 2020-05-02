package in.indilabz.student_helper.kaela.TeaActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
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

import in.indilabz.student_helper.kaela.Interfaces.TeaSelecInter;
import in.indilabz.student_helper.kaela.PublicLinks;
import in.indilabz.student_helper.kaela.R;
import in.indilabz.student_helper.kaela.TeaActivity.adapter.SubSelecAdapter;
import in.indilabz.student_helper.kaela.TeaActivity.moTea.ClasObjTea;
import in.indilabz.student_helper.kaela.TeaActivity.moTea.SubObj;
import in.indilabz.student_helper.kaela.TeacherMainScreen;

public class SubSelcActivity extends AppCompatActivity implements TeaSelecInter {
    RecyclerView recyclerView;
    CardView submitBtn;
    ArrayList<String> selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_selc);
        selected = new ArrayList<>();
        recyclerView = findViewById(R.id.id_tea_sel_rcv);
        submitBtn = findViewById(R.id.id_tea_se_btn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveData();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SubSelecAdapter adapter = new SubSelecAdapter(getData(), this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    ArrayList<ClasObjTea> getData() {
        ArrayList<ClasObjTea> arrayList = new ArrayList<>();
        ArrayList<SubObj> subs = new ArrayList<>();
        subs.add(new SubObj("5M", "Maths"));
        subs.add(new SubObj("5S", "Science"));
        subs.add(new SubObj("5E", "English"));
        subs.add(new SubObj("5ES", "EVS"));
        ClasObjTea c5 = new ClasObjTea("5", subs);
        arrayList.add(c5);

        ArrayList<SubObj> subs6 = new ArrayList<>();
        subs6.add(new SubObj("6M", "Maths"));
        subs6.add(new SubObj("6S", "Science"));
        subs6.add(new SubObj("6E", "English"));
        subs6.add(new SubObj("6ES", "EVS"));
        subs6.add(new SubObj("6SS", "SST"));
        subs6.add(new SubObj("6GK", "GK"));
        ClasObjTea c6 = new ClasObjTea("6", subs6);
        arrayList.add(c6);

        ArrayList<SubObj> subs7 = new ArrayList<>();
        subs7.add(new SubObj("7M", "Maths"));
        subs7.add(new SubObj("7S", "Science"));
        subs7.add(new SubObj("7E", "English"));
        subs7.add(new SubObj("7ES", "EVS"));
        subs7.add(new SubObj("7SS", "SST"));
        subs7.add(new SubObj("7GK", "GK"));
        ClasObjTea c7 = new ClasObjTea("7", subs7);
        arrayList.add(c7);

        ArrayList<SubObj> subs8 = new ArrayList<>();
        subs8.add(new SubObj("8M", "Maths"));
        subs8.add(new SubObj("8S", "Science"));
        subs8.add(new SubObj("8E", "English"));
        subs8.add(new SubObj("8ES", "EVS"));
        subs8.add(new SubObj("8SS", "SST"));
        subs8.add(new SubObj("8GK", "GK"));
        ClasObjTea c8 = new ClasObjTea("8", subs8);
        arrayList.add(c8);

        ArrayList<SubObj> subs9 = new ArrayList<>();
        subs9.add(new SubObj("9M", "Maths"));
        subs9.add(new SubObj("9H", "History"));
        subs9.add(new SubObj("9EL", "English Lit."));
        subs9.add(new SubObj("9EG", "English Gram."));
        subs9.add(new SubObj("9P", "Physics"));
        subs9.add(new SubObj("9C", "Chemistry"));
        subs9.add(new SubObj("9B", "Biology"));
        ClasObjTea c9 = new ClasObjTea("9", subs9);
        arrayList.add(c9);

        ArrayList<SubObj> subs10 = new ArrayList<>();
        subs10.add(new SubObj("10M", "Maths"));
        subs10.add(new SubObj("10SS", "S. Sc."));
        subs10.add(new SubObj("10S", "Science"));
        subs10.add(new SubObj("10EG", "English"));
        ClasObjTea c10 = new ClasObjTea("10", subs10);
        arrayList.add(c10);

        ArrayList<SubObj> subs11s = new ArrayList<>();
        subs11s.add(new SubObj("11sM", "Maths"));
        subs11s.add(new SubObj("11sP", "Physics"));
        subs11s.add(new SubObj("11sC", "Chemistry"));
        subs11s.add(new SubObj("11sB", "Biology"));
        subs11s.add(new SubObj("11sE", "English"));
        ClasObjTea c11s = new ClasObjTea("11", "Science", subs11s);
        arrayList.add(c11s);

        ArrayList<SubObj> subs11c = new ArrayList<>();
        subs11c.add(new SubObj("11cM", "Maths"));
        subs11c.add(new SubObj("11cA", "Accountancy"));
        subs11c.add(new SubObj("11cB", "Business Stu."));
        subs11c.add(new SubObj("11cE", "Economics"));
        subs11c.add(new SubObj("11cE", "English"));
        ClasObjTea c11c = new ClasObjTea("11", "Commerce", subs11c);
        arrayList.add(c11c);

        ArrayList<SubObj> subs12s = new ArrayList<>();
        subs12s.add(new SubObj("12sM", "Maths"));
        subs12s.add(new SubObj("12sP", "Physics"));
        subs12s.add(new SubObj("12sC", "Chemistry"));
        subs12s.add(new SubObj("12sB", "Biology"));
        subs12s.add(new SubObj("12sE", "English"));
        ClasObjTea c12s = new ClasObjTea("12", "Science", subs12s);
        arrayList.add(c12s);

        ArrayList<SubObj> subs12c = new ArrayList<>();
        subs12c.add(new SubObj("12cM", "Maths"));
        subs12c.add(new SubObj("12cA", "Accountancy"));
        subs12c.add(new SubObj("12cB", "Business Stu."));
        subs12c.add(new SubObj("12cE", "Economics"));
        subs12c.add(new SubObj("12cE", "English"));
        ClasObjTea c12c = new ClasObjTea("12", "Commerce", subs12c);
        arrayList.add(c12c);


        return arrayList;
    }

    @Override
    public void addSub(String subId) {
        selected.add(subId);
    }

    @Override
    public void removeSub(String subId) {
        selected.remove(subId);
    }

    void SaveData() {
        // TODO: 5/2/2020 Save The Data here
        SharedPreferences sharedPref = this.getSharedPreferences("USER", MODE_PRIVATE);
        final String mail = sharedPref.getString("EMAIL", "");
        final StringBuilder builder = new StringBuilder();
        for (String s : selected) {
            builder.append(s);
            builder.append("/");
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, PublicLinks.UPDATE_SUB_SELECTED,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            for (int i = 0; i < 2; i++) {
                                System.out.println(i);
                            }
                            String success = jsonObject.getString("RESPONSE");
                            if (!success.equals("-1")) {
                                startActivity(new Intent(getApplicationContext(), TeacherMainScreen.class));
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Failed To update : " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("subs", builder.toString());
                params.put("mail", mail);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(this));
        requestQueue.add(stringRequest);
    }
}
