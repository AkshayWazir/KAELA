package in.indilabz.student_helper.kaela.TeaActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.zolad.zoominimageview.ZoomInImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import in.indilabz.student_helper.kaela.PublicLinks;
import in.indilabz.student_helper.kaela.R;

public class SolveQuestionActi extends AppCompatActivity {
    TextView quesTitle, quesDesc;
    ZoomInImageView quesImage, sol_image;
    AlertDialog alertDialog;
    TextInputLayout solTxt;
    Bitmap solImg;
    ProgressBar bar;
    String quesId, teaId;
    ConstraintLayout begin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_question);
        quesTitle = findViewById(R.id.textView69);
        quesDesc = findViewById(R.id.textView70);
        quesImage = findViewById(R.id.zoomInImageView3);
        quesImage.setVisibility(View.GONE);
        solTxt = findViewById(R.id.textInputLayout12);
        bar = findViewById(R.id.progressBar7);
        begin = findViewById(R.id.begin_trans_id);
        sol_image = findViewById(R.id.zoomInImageView4);

        // setup data
        Intent intent = getIntent();
        quesId = intent.getStringExtra("QUES_ID");
        teaId = intent.getStringExtra("TEA_ID");
        setupQuestiom(quesId);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(true);
            }
        });
    }


    public void getPic(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    private void showDialog(boolean state) {
        if (state) {
            final AlertDialog.Builder alert = new AlertDialog.Builder(SolveQuestionActi.this);
            final View view1 = getLayoutInflater().inflate(R.layout.custom_dialog, null);
            ConstraintLayout proceed = view1.findViewById(R.id.id_proceed_cl);
            ConstraintLayout notNow = view1.findViewById(R.id.id_wait_cl);

            alert.setView(view1);
            alertDialog = alert.create();
            alertDialog.setCanceledOnTouchOutside(false);
            proceed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    uploadSol();
                    view1.findViewById(R.id.progressBar5).setVisibility(View.VISIBLE);
                }
            });
            notNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });
            alertDialog.show();
        } else {
            alertDialog.dismiss();
        }
    }

    private void uploadSol() {
        StringRequest request = new StringRequest(Request.Method.POST, PublicLinks.UPLOAD_SOLUTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        for (int i = 0; i < 2; i++) {
                            System.out.println(i);
                        }
                        try {
                            JSONObject object = new JSONObject(response);
                            String response1 = object.getString("RESPONSE");
                            if (response1.equals("1")) {
                                Toast.makeText(SolveQuestionActi.this, "Uploaded", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                showDialog(false);
                                Toast.makeText(SolveQuestionActi.this, "Failed To upload", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            showDialog(false);
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showDialog(false);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("SOL_DESC", solTxt.getEditText().getText().toString());
                params.put("SOL_IMG", imToSt(solImg));
                params.put("TEA_ID", teaId);
                params.put("QUES_ID", quesId);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }

    void setupQuestiom(final String ques) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, PublicLinks.GET_SOLS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject objects = new JSONObject(response);
                            String success = objects.getString("result");
                            if (success.equals("1")) {

                                quesTitle.setText(objects.getJSONObject("ques").getString("ques_title"));
                                quesDesc.setText(objects.getJSONObject("ques").getString("ques_desc"));
                                String quesImageStr = objects.getJSONObject("ques").getString("image");
                                if (!quesImageStr.equals("")) {
                                    byte[] decodedString = Base64.decode(quesImageStr, Base64.DEFAULT);
                                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                    Glide.with(getApplicationContext())
                                            .load(decodedByte)
                                            .into(quesImage);
                                    quesImage.setVisibility(View.VISIBLE);
                                }
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

    private String imToSt(Bitmap bitmap) {
        if (bitmap == null) {
            return "";
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imgByte = stream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null) {
            Uri imageUri = data.getData();
            try {
                solImg = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                sol_image.setImageBitmap(solImg);
                sol_image.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
