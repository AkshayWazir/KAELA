package in.indilabz.student_helper.kaela.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.indilabz.student_helper.kaela.ModelObjects.ReviewObject;
import in.indilabz.student_helper.kaela.R;

public class StudentReviewAdapter extends RecyclerView.Adapter<StudentReviewAdapter.ReviewViewHolder> {
    private ArrayList<ReviewObject> objects;
    private Context context;

    public StudentReviewAdapter(Context context) {
        this.context = context;
        objects = new ArrayList<>();
    }

    public void setObjects(ArrayList<ReviewObject> objects) {
        this.objects = objects;
        notifyDataSetChanged();
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
        holder.stuSchool.setText(objects.get(position).getSchool());
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView StudentName, question, stuSchool;

        ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            StudentName = itemView.findViewById(R.id.textView39);
            question = itemView.findViewById(R.id.textView41);
            stuSchool = itemView.findViewById(R.id.textView77);
        }
    }
}
