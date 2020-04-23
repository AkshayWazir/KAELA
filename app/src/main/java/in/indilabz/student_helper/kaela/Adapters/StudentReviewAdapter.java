package in.indilabz.student_helper.kaela.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.indilabz.student_helper.kaela.ModelObjects.ReviewObject;
import in.indilabz.student_helper.kaela.R;

public class StudentReviewAdapter extends RecyclerView.Adapter<StudentReviewAdapter.ReviewViewHolder> {
    private ArrayList<ReviewObject> objects;
    private Context context;

    public StudentReviewAdapter(ArrayList<ReviewObject> objects, Context context) {
        this.objects = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.review_layout, parent, false);
        return new ReviewViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView StudentName, subject, question;
        ImageView st1, st2, st3, st4, st5;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            StudentName = itemView.findViewById(R.id.textView39);
            question = itemView.findViewById(R.id.textView41);
            subject = itemView.findViewById(R.id.textView40);
            st1 = itemView.findViewById(R.id.imageView27);
            st2 = itemView.findViewById(R.id.imageView28);
            st3 = itemView.findViewById(R.id.imageView29);
            st4 = itemView.findViewById(R.id.imageView30);
            st5 = itemView.findViewById(R.id.imageView31);
        }
    }
}
