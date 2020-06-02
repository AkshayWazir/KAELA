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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.zolad.zoominimageview.ZoomInImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.indilabz.student_helper.kaela.ChatSystem.ChatScreen;
import in.indilabz.student_helper.kaela.Interfaces.acceptSol;
import in.indilabz.student_helper.kaela.Networking.MySingleton;
import in.indilabz.student_helper.kaela.PublicLinks;
import in.indilabz.student_helper.kaela.R;
import in.indilabz.student_helper.kaela.TeaActivity.adapter.SolutionMainAda;
import in.indilabz.student_helper.kaela.TeaActivity.moTea.Sol_moob;

public class SolutionActivity extends AppCompatActivity implements acceptSol {
    RecyclerView recyclerView;
    TextView title, des;
    ZoomInImageView imageView;
    CardView solve, discuss;
    ConstraintLayout clickEvent;
    String quesImageStr, teacher_id, ques_id;
    JSONObject objects;
    ProgressBar bar;
    Bitmap decodedByte;
    AlertDialog alertDialog;

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
        discuss = findViewById(R.id.id_start_discuss);

        clickEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pSList(objects);
            }
        });
        SharedPreferences preferences = getSharedPreferences("USER", MODE_PRIVATE);
        if (preferences.getInt("TYPE", 0) == 1) {
            solve.setVisibility(View.VISIBLE);
        }
        teacher_id = preferences.getString("TEACH_ID", "");
        ques_id = getIntent().getStringExtra("QUES_ID");
        setupQuestiom(ques_id);
        discuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChatScreen.class);
                intent.putExtra("ROOM_ID", ques_id);
                startActivity(intent);
            }
        });
    }

    void setupQuestiom(final String ques) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, PublicLinks.GET_SOLS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String solution = "";
                        try {
                            objects = new JSONObject(response);
                            String success = objects.getString("result");
                            if (success.equals("1")) {
                                title.setText(objects.getJSONObject("ques").getString("ques_title"));
                                des.setText(objects.getJSONObject("ques").getString("ques_desc"));
                                quesImageStr = objects.getJSONObject("ques").getString("image");
                                solution = objects.getJSONObject("ques").getString("solved");

                                if (!quesImageStr.equals("")) {
                                    byte[] decodedString = Base64.decode(quesImageStr, Base64.DEFAULT);
                                    decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                    Glide.with(getApplicationContext())
                                            .load(decodedByte)
                                            .into(imageView);
                                    imageView.setVisibility(View.VISIBLE);
                                    imageView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            raiseDialog(decodedByte);

                                        }
                                    });
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
                                    String solId = obj.getString("sol_id");
                                    moob.setSol_id(solId);
                                    if (solution.equals(solId)) {
                                        moob.setAccepted(true);
                                    }
                                    if (!imgSol.equals("")) {
                                        byte[] decodedString = Base64.decode(imgSol, Base64.DEFAULT);
                                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                        moob.setSolPic(decodedByte);
                                    }
                                    if (!imgPro.equals("")) {
                                        byte[] dsPro = Base64.decode(imgPro, Base64.DEFAULT);
                                        Bitmap dbPro = BitmapFactory.decodeByteArray(dsPro, 0, dsPro.length);
                                        moob.setProPic(dbPro);
                                    }
                                    container.add(moob);
                                }
                                setup_sols(container);
                                bar.setVisibility(View.GONE);
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
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Toast.makeText(getApplicationContext(), "Request Timeout", Toast.LENGTH_LONG).show();
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(getApplicationContext(), "Auth Failure", Toast.LENGTH_LONG).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_LONG).show();
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_LONG).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getApplicationContext(), "Parse Error", Toast.LENGTH_LONG).show();
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("QUES_ID", ques);
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    void setup_sols(ArrayList<Sol_moob> objs) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SolutionMainAda adapter = new SolutionMainAda(this);
        adapter.setObjs(objs);
        adapter.setObj(this);
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

    private void raiseDialog(Bitmap bitmap) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(SolutionActivity.this);
        final View view1 = getLayoutInflater().inflate(R.layout.full_img_view, null);
        ZoomInImageView viewImage = view1.findViewById(R.id.img_show_id);
        viewImage.setImageBitmap(bitmap);
        alert.setView(view1);
        alertDialog = alert.create();
        alertDialog.show();
    }

    @Override
    public void raiseDialog(final String solId) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(SolutionActivity.this);
        final View view1 = getLayoutInflater().inflate(R.layout.solution_chose_dia_layout, null);
        CardView accept = view1.findViewById(R.id.cardView15);
        TextView decline = view1.findViewById(R.id.textView83);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToSelected(ques_id, solId);
                v.setEnabled(false);
                alertDialog.dismiss();
            }
        });

        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alert.setView(view1);
        alertDialog = alert.create();
        alertDialog.show();
    }

    void addToSelected(final String ques_id, final String sol_id) {
        StringRequest request = new StringRequest(Request.Method.POST, PublicLinks.ADD_TO_SOLVED, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    String response1 = object.getString("RESPONSE");
                    if (response1.equals("1")) {
                        Toast.makeText(SolutionActivity.this, "UPDATED", Toast.LENGTH_SHORT).show();
                    } else {
                        alertDialog.dismiss();
                        Toast.makeText(SolutionActivity.this, "Failed To upload", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    alertDialog.dismiss();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                alertDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("QUES_ID", ques_id);
                params.put("SOL_ID", sol_id);
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }
}
