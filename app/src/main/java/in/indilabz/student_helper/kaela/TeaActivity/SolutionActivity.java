package in.indilabz.student_helper.kaela.TeaActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.zolad.zoominimageview.ZoomInImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import in.indilabz.student_helper.kaela.Adapters.AdaTeaSols;
import in.indilabz.student_helper.kaela.ModelObjects.TeacherObject;
import in.indilabz.student_helper.kaela.PublicLinks;
import in.indilabz.student_helper.kaela.R;
import in.indilabz.student_helper.kaela.TeaActivity.adapter.SolutionMainAda;
import in.indilabz.student_helper.kaela.TeaActivity.moTea.Sol_moob;

public class SolutionActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView title, des;
    ZoomInImageView imageView;
    AdaTeaSols adapter;
    CardView solve;
    ConstraintLayout clickEvent;
    String quesImageStr, teacher_id;
    JSONObject objects;
    ProgressBar bar;
    Bitmap decodedByte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);
        // setup findViewByIDs
        recyclerView = findViewById(R.id.id_solview);
        title = findViewById(R.id.textView63);
        des = findViewById(R.id.textView64);
        imageView = findViewById(R.id.zoomInImageView);
        clickEvent = findViewById(R.id.id_catch_123456);
        bar = findViewById(R.id.progressBar6);
        solve = findViewById(R.id.solv_ques);

        clickEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pSList(objects);
            }
        });
        SharedPreferences preferences = getSharedPreferences("USERS", MODE_PRIVATE);
        teacher_id = preferences.getString("TEACH_ID", "");
        setupQuestiom(getIntent().getStringExtra("QUES_ID"));
    }


    void setupQuestiom(final String ques) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, PublicLinks.GET_SOLS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            objects = new JSONObject(response);
                            String success = objects.getString("result");
                            if (success.equals("1")) {

                                title.setText(objects.getJSONObject("ques").getString("ques_title"));
                                des.setText(objects.getJSONObject("ques").getString("ques_desc"));
                                quesImageStr = objects.getJSONObject("ques").getString("image");

                                if (!quesImageStr.equals("")) {
                                    byte[] decodedString = Base64.decode(quesImageStr, Base64.DEFAULT);
                                    decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                    Glide.with(getApplicationContext())
                                            .load(decodedByte)
                                            .into(imageView);
                                    imageView.setVisibility(View.VISIBLE);
                                }
                                ArrayList<Sol_moob> container = new ArrayList<>();
                                for (int i = 0; i < objects.getJSONArray("dat").length(); i++) {
                                    JSONObject obj = objects.getJSONArray("dat").getJSONObject(i);
                                    Sol_moob moob = new Sol_moob();
                                    moob.setName(obj.getString("name"));
                                    moob.setDesc(obj.getString("description"));
                                    moob.setDesig(obj.getString("exp"));
                                    String imgSol = obj.getString("image");
                                    String imgPro = obj.getString("tea_propic");
                                    if (!imgSol.equals("")){
                                        byte[] decodedString = Base64.decode(imgSol, Base64.DEFAULT);
                                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                        moob.setSolPic(decodedByte);
                                    }
                                    if (!imgPro.equals("")){
                                        byte[] dsPro = Base64.decode(imgPro, Base64.DEFAULT);
                                        Bitmap dbPro = BitmapFactory.decodeByteArray(dsPro, 0, dsPro.length);
                                        moob.setSolPic(dbPro);
                                    }
                                    container.add(moob);
                                }
                                setup_sols(container);
                                bar.setVisibility(View.GONE);
                                solve.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("QUES_ID", ques);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getApplicationContext()));
        requestQueue.add(stringRequest);
    }

    void setup_sols(ArrayList<Sol_moob> objs) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SolutionMainAda adapter = new SolutionMainAda(this);
        adapter.setObjs(objs);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    public void pSList(JSONObject object) {
        Intent inte = new Intent(this, SolveQuestionActi.class);
        try {
            inte.putExtra("QUES_ID", object.getJSONObject("ques").getString("ques_id"));
            inte.putExtra("TEA_ID", teacher_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        startActivity(inte);
    }

}
