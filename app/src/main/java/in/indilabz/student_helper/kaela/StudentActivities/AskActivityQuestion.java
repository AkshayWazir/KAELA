package in.indilabz.student_helper.kaela.StudentActivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
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
import com.google.android.material.textfield.TextInputLayout;
import com.zolad.zoominimageview.ZoomInImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import in.indilabz.student_helper.kaela.PublicLinks;
import in.indilabz.student_helper.kaela.R;

public class AskActivityQuestion extends AppCompatActivity {
    ZoomInImageView imageView;
    TextView prevTitle, prevDesc;
    TextInputLayout title, desc;
    Bitmap bitmap;
    AlertDialog alertDialog;
    StringBuilder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);
        Intent intent = getIntent();
        ArrayList<String> tta = intent.getStringArrayListExtra("TEACHER_ID");
        builder = new StringBuilder();
        if (tta != null) {
            for (String s : tta) {
                builder.append("-");
                builder.append(s);
                builder.append("-");
            }
        }
        imageView = findViewById(R.id.imageView13);
        prevTitle = findViewById(R.id.textView45);
        prevDesc = findViewById(R.id.textView46);
        title = findViewById(R.id.textInputLayout7);
        desc = findViewById(R.id.textInputLayout11);

        Objects.requireNonNull(title.getEditText()).addTextChangedListener(titleChangeWatcher);
        Objects.requireNonNull(desc.getEditText()).addTextChangedListener(descChangeWatcher);
    }

    private TextWatcher titleChangeWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String temp = s.toString();
            prevTitle.setText(temp);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    private TextWatcher descChangeWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String temp = s.toString();
            prevDesc.setText(temp);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public void getPicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && data != null) {
            Bitmap bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageBitmap(bitmap);
        } else if (requestCode == 1 && data != null) {
            Uri imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void chooseGallery(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    public void raiseDialog(View view) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(AskActivityQuestion.this);
        final View view1 = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        ConstraintLayout proceed = view1.findViewById(R.id.id_proceed_cl);
        ConstraintLayout notNow = view1.findViewById(R.id.id_wait_cl);

        alert.setView(view1);
        alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
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
    }

    void uploadImage() {
        StringRequest request = new StringRequest(Request.Method.POST, PublicLinks.UPLOAD_QUESTION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                for (int i = 0; i < 2; i++) {
                    System.out.println(i);
                }
                try {
                    JSONObject object = new JSONObject(response);
                    String response1 = object.getString("RESPONSE");
                    if (response1.equals("1")) {
                        Toast.makeText(AskActivityQuestion.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        alertDialog.dismiss();
                        Toast.makeText(AskActivityQuestion.this, "Failed To upload", Toast.LENGTH_SHORT).show();
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
                params.put("title", title.getEditText().getText().toString());
                params.put("desc", desc.getEditText().getText().toString());
                params.put("name", prefs.getString("NAME", "No Name"));
                params.put("id", prefs.getString("EMAIL", "No Mail"));
                params.put("image", imToSt(bitmap));
                params.put("temp_id", getTempId());
                params.put("ta_tea", builder.toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }

    // method to convert image to stream Bytes
    private String imToSt(Bitmap bitmap) {
        if (bitmap == null) {
            return "";
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imgByte = stream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }


    private String getTempId() {
        Date currentTime = Calendar.getInstance().getTime();
        return currentTime.toString();
    }
}
