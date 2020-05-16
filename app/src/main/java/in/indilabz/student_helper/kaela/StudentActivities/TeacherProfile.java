package in.indilabz.student_helper.kaela.StudentActivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import in.indilabz.student_helper.kaela.Adapters.StudentReviewAdapter;
import in.indilabz.student_helper.kaela.ModelObjects.ReviewObject;
import in.indilabz.student_helper.kaela.Networking.MySingleton;
import in.indilabz.student_helper.kaela.PublicLinks;
import in.indilabz.student_helper.kaela.R;

public class TeacherProfile extends AppCompatActivity {
    RecyclerView review;
    String TEACH_ID, STU_ID;
    TextView connect_count, review_count, teach_name, teach_desig;
    CardView stuReview;
    CircleImageView profilePicture;
    AlertDialog alertDialog;
    View view1;
    Bitmap bitmap;
    ImageView upload_image;
    StudentReviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        connect_count = findViewById(R.id.textView36);
        teach_name = findViewById(R.id.textView30);
        teach_desig = findViewById(R.id.textView32);
        stuReview = findViewById(R.id.cardView_stuReview);
        profilePicture = findViewById(R.id.circleImageView2);
        upload_image = findViewById(R.id.imageView2);
        Intent intent = getIntent();
        SharedPreferences prefs = this.getSharedPreferences("USER", MODE_PRIVATE);

        // section to check Who's calling
        int teaOrStu = prefs.getInt("TYPE", 0);
        if (teaOrStu == 1) {
            TEACH_ID = prefs.getString("TEACH_ID", "");
            stuReview.setVisibility(View.GONE);
        } else {
            upload_image.setVisibility(View.GONE);
            TEACH_ID = intent.getStringExtra("TEACH_ID");
            STU_ID = prefs.getString("EMAIL", "");
        }

        setUpProfile(TEACH_ID);
        stuReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(TeacherProfile.this);

                view1 = getLayoutInflater().inflate(R.layout.tsr_pop_layout, null);

                final TextInputLayout reviewText = view1.findViewById(R.id.textInputLayout13);
                CardView begin = view1.findViewById(R.id.id_begin_review);
                final ProgressBar bar = view1.findViewById(R.id.progressBar8);

                begin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String review = reviewText.getEditText().getText().toString();
                        bar.setVisibility(View.VISIBLE);
                        if (!review.equals("")) {
                            submitReview(review);
                        }
                    }
                });

                alert.setView(view1);
                alertDialog = alert.create();
                alertDialog.show();
            }
        });
        review = findViewById(R.id.reviewewview);
        review.setHasFixedSize(true);
        adapter = new StudentReviewAdapter(this);
        review.setAdapter(adapter);
    }

    void setUpProfile(final String teachId) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, PublicLinks.TEACH_PROFILE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            teach_name.setText(jsonObject.getJSONObject("TEACH_DETAIL").getString("name"));
                            teach_desig.setText(jsonObject.getJSONObject("TEACH_DETAIL").getString("exp"));
                            connect_count.setText(jsonObject.getJSONObject("TEACH_DETAIL").getString("connections"));
                            String image = jsonObject.getJSONObject("TEACH_DETAIL").getString("tea_propic");
                            if (!image.equals("")) {
                                byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                Glide.with(getApplicationContext())
                                        .load(decodedByte)
                                        .into(profilePicture);
                            }
                            ArrayList<ReviewObject> objects = new ArrayList<>();
                            for (int i = 0; i < jsonObject.getJSONArray("REVIEWS").length(); i++) {
                                JSONObject obj = jsonObject.getJSONArray("REVIEWS").getJSONObject(i);
                                objects.add(new ReviewObject(obj.getString("stu_name"), obj.getString("review"), obj.getString("school")));
                            }
                            adapter.setObjects(objects);
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error : " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TEACH_ID", teachId);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    void submitReview(final String review) {
        for (int i = 0; i < 1; i++) {
            System.out.println(i);
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, PublicLinks.SUBMIT_REVIEW,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("RESPONSE").equals("1")) {
                                alertDialog.dismiss();
                            } else {
                                alertDialog.dismiss();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Failed To submit Review :" + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            alertDialog.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_LONG).show();
                        alertDialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TEACH_ID", TEACH_ID);
                params.put("REVIEW", review);
                params.put("STU_ID", STU_ID);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void fetchPropic(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

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

    void startImageUpload() {
        StringRequest request = new StringRequest(Request.Method.POST, PublicLinks.UPDATE_PROPIC_TEACHER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                for (int i = 0; i < 2; i++) {
                    System.out.println(i);
                }
                try {
                    JSONObject object = new JSONObject(response);
                    String response1 = object.getString("RESPONSE");
                    if (response1.equals("1")) {
                        Toast.makeText(TeacherProfile.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        alertDialog.dismiss();
                        Toast.makeText(TeacherProfile.this, "Failed To upload", Toast.LENGTH_SHORT).show();
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
                SharedPreferences prefs = getApplicationContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
                params.put("id", prefs.getString("TEACH_ID", ""));
                params.put("image", imToSt(bitmap));
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null) {
            Uri imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profilePicture.setImageBitmap(bitmap);
                startImageUpload();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
