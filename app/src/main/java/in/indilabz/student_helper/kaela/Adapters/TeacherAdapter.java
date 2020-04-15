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

import in.indilabz.student_helper.kaela.ModelObjects.TeacherObject;
import in.indilabz.student_helper.kaela.R;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.teachViewHolder> {
    private Context ctx;
    private TeacherObject[] objects;

    TeacherAdapter(Context ctx, TeacherObject[] objects) {
        this.ctx = ctx;
        this.objects = objects;
    }

    @NonNull
    @Override
    public teachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.tchr_pannel_layout, parent, false);
        return new teachViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull teachViewHolder holder, int position) {
        holder.name.setText(objects[position].getName());
        holder.title.setText(objects[position].getDesignation());
        switch (Integer.parseInt(objects[position].getRating())) {
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
    }

    @Override
    public int getItemCount() {
        return objects.length;
    }

    static class teachViewHolder extends RecyclerView.ViewHolder {
        ImageView profPic;
        ImageView star1, star2, star3, star4, star5;
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
        }
    }
}
