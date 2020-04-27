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
        holder.StudentName.setText(objects.get(position).getStudentName());
        holder.question.setText(objects.get(position).getReview());
        switch (objects.get(position).getRating()) {
            case (1):
                holder.st1.setImageResource(R.drawable.ic_star_filled);
                break;
            case (2):
                holder.st1.setImageResource(R.drawable.ic_star_filled);
                holder.st2.setImageResource(R.drawable.ic_star_filled);
                break;
            case (3):
                holder.st1.setImageResource(R.drawable.ic_star_filled);
                holder.st2.setImageResource(R.drawable.ic_star_filled);
                holder.st3.setImageResource(R.drawable.ic_star_filled);
                break;
            case (4):
                holder.st1.setImageResource(R.drawable.ic_star_filled);
                holder.st2.setImageResource(R.drawable.ic_star_filled);
                holder.st3.setImageResource(R.drawable.ic_star_filled);
                holder.st4.setImageResource(R.drawable.ic_star_filled);
                break;
            case (5):
                holder.st1.setImageResource(R.drawable.ic_star_filled);
                holder.st2.setImageResource(R.drawable.ic_star_filled);
                holder.st3.setImageResource(R.drawable.ic_star_filled);
                holder.st4.setImageResource(R.drawable.ic_star_filled);
                holder.st5.setImageResource(R.drawable.ic_star_filled);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView StudentName, question;
        ImageView st1, st2, st3, st4, st5;

        ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            StudentName = itemView.findViewById(R.id.textView39);
            question = itemView.findViewById(R.id.textView41);
            st1 = itemView.findViewById(R.id.imageView27);
            st2 = itemView.findViewById(R.id.imageView28);
            st3 = itemView.findViewById(R.id.imageView29);
            st4 = itemView.findViewById(R.id.imageView30);
            st5 = itemView.findViewById(R.id.imageView31);
        }
    }
}
