package in.indilabz.student_helper.kaela.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.indilabz.student_helper.kaela.Interfaces.AskQuestion;
import in.indilabz.student_helper.kaela.ModelObjects.TeacherObject;
import in.indilabz.student_helper.kaela.R;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.teachViewHolder> implements Filterable {
    private Context ctx;
    private ArrayList<TeacherObject> objects, allObjects;
    private AskQuestion question;

    public TeacherAdapter(Context ctx, ArrayList<TeacherObject> objects) {
        this.ctx = ctx;
        this.objects = objects;
        this.allObjects = new ArrayList<>(objects);
    }

    public void setQuestion(AskQuestion question) {
        this.question = question;
    }

    @NonNull
    @Override
    public teachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.tchr_pannel_layout, parent, false);
        return new teachViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final teachViewHolder holder, final int position) {
        holder.name.setText(objects.get(position).getName());
        holder.title.setText(objects.get(position).getDesignation());
        switch (Integer.parseInt(objects.get(position).getRating())) {
            case (1):
                holder.star2.setImageResource(R.drawable.ic_star_empty);
                holder.star3.setImageResource(R.drawable.ic_star_empty);
                holder.star4.setImageResource(R.drawable.ic_star_empty);
                holder.star5.setImageResource(R.drawable.ic_star_empty);
                break;
            case (2):
                holder.star3.setImageResource(R.drawable.ic_star_empty);
                holder.star4.setImageResource(R.drawable.ic_star_empty);
                holder.star5.setImageResource(R.drawable.ic_star_empty);
                break;
            case (3):
                holder.star4.setImageResource(R.drawable.ic_star_empty);
                holder.star5.setImageResource(R.drawable.ic_star_empty);
                break;
            case (4):
                holder.star5.setImageResource(R.drawable.ic_star_empty);
                break;
        }
        holder.clickable
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!objects.get(position).isSelected()) {
                            question.selectTeacher(objects.get(position).getId());
                            holder.check.setImageResource(R.drawable.tick);
                            objects.get(position).setSelected(true);
                        } else {
                            question.removeTeacher(objects.get(position).getId());
                            holder.check.setImageResource(R.drawable.hollo_cirle);
                            objects.get(position).setSelected(false);
                        }
                    }
                });
        holder.showProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question.showProfile(objects.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<TeacherObject> filtered = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filtered.addAll(allObjects);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (TeacherObject item : allObjects) {
                    if (item.getName().toLowerCase().trim().contains(filterPattern)) {
                        filtered.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtered;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            objects.clear();
            objects.addAll((List<? extends TeacherObject>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    static class teachViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout clickable, showProfile;
        ImageView profPic;
        ImageView star1, star2, star3, star4, star5, check;
        TextView name, title;

        teachViewHolder(@NonNull View itemView) {
            super(itemView);
            profPic = itemView.findViewById(R.id.circleImageView);
            star1 = itemView.findViewById(R.id.imageView14);
            star2 = itemView.findViewById(R.id.imageView17);
            star3 = itemView.findViewById(R.id.imageView16);
            star4 = itemView.findViewById(R.id.imageView18);
            star5 = itemView.findViewById(R.id.imageView15);
            name = itemView.findViewById(R.id.textView17);
            title = itemView.findViewById(R.id.textView18);
            clickable = itemView.findViewById(R.id.teacher_container);
            check = itemView.findViewById(R.id.imageView20);
            showProfile = itemView.findViewById(R.id.id_showprof);
        }
    }
}
