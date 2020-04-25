package in.indilabz.student_helper.kaela.StudentActivities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import in.indilabz.student_helper.kaela.R;

public class AskActivityQuestion extends AppCompatActivity {
    ImageView imageView;
    TextView prevTitle, prevDesc;
    TextInputLayout title, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);
        imageView = findViewById(R.id.imageView13);
        prevTitle = findViewById(R.id.textView45);
        prevDesc = findViewById(R.id.textView46);
        title = findViewById(R.id.textInputLayout7);
        desc = findViewById(R.id.textInputLayout11);
        title.getEditText().addTextChangedListener(titleChangeWatcher);
        desc.getEditText().addTextChangedListener(descChangeWatcher);
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
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageBitmap(bitmap);
        } else if (requestCode == 1 && data != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
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
        View view1 = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        ConstraintLayout proceed = view1.findViewById(R.id.id_proceed_cl);
        ConstraintLayout notNow = view1.findViewById(R.id.id_wait_cl);

        alert.setView(view1);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 4/25/2020 Raise the Request and exit the

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
}
