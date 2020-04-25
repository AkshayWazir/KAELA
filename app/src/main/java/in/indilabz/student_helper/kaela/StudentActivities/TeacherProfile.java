package in.indilabz.student_helper.kaela.StudentActivities;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.indilabz.student_helper.kaela.Adapters.StudentReviewAdapter;
import in.indilabz.student_helper.kaela.Adapters.SubProgAdapter;
import in.indilabz.student_helper.kaela.ModelObjects.ReviewObject;
import in.indilabz.student_helper.kaela.ModelObjects.SubRatingObject;
import in.indilabz.student_helper.kaela.R;

public class TeacherProfile extends AppCompatActivity {
    RecyclerView subsReview, review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        subsReview = findViewById(R.id.subs_review);
        review = findViewById(R.id.reviewewview);
        subsReview.setHasFixedSize(true);
        review.setHasFixedSize(true);
        subsReview.setAdapter(new SubProgAdapter(getSubs(), this));
        review.setAdapter(new StudentReviewAdapter(getReviews(), this));
    }

    ArrayList<SubRatingObject> getSubs() {
        ArrayList<SubRatingObject> objects = new ArrayList<>();
        objects.add(new SubRatingObject("Hindi", 79));
        objects.add(new SubRatingObject("English", 7));
        objects.add(new SubRatingObject("Mathematics", 60));
        objects.add(new SubRatingObject("French", 90));
        objects.add(new SubRatingObject("BlueBerries", 50));
        return objects;
    }

    ArrayList<ReviewObject> getReviews() {
        ArrayList<ReviewObject> objects = new ArrayList<>();
        objects.add(new ReviewObject("Akshay Rein", "Some stupid Question", "Physics", 4));
        objects.add(new ReviewObject("Lakshay Paliwal", "Some stupid Question", "Physics", 5));
        objects.add(new ReviewObject("Amit Dubey", "Some Extremely stupid Question", "Physics", 5));
        objects.add(new ReviewObject("Shiwanshu Gosain", "Some stupid Question", "Physics", 3));
        objects.add(new ReviewObject("Zaiba Shaikh", "Some stupid Question", "Physics", 5));
        objects.add(new ReviewObject("Akshay Rein", "Some stupid Question", "Physics", 4));
        objects.add(new ReviewObject("Akshay Rein", "Some stupid Question", "Physics", 4));
        return objects;
    }
}
