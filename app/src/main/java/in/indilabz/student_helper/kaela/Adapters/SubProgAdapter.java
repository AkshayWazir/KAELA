package in.indilabz.student_helper.kaela.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;

import in.indilabz.student_helper.kaela.ModelObjects.SubRatingObject;
import in.indilabz.student_helper.kaela.R;

public class SubProgAdapter extends RecyclerView.Adapter<SubProgAdapter.SubsViewHolder> {
    private ArrayList<SubRatingObject> objects;
    private Context ctx;

    public SubProgAdapter(ArrayList<SubRatingObject> objects, Context ctx) {
        this.objects = objects;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public SubsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.subs_progress, parent, false);
        return new SubsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SubsViewHolder holder, int position) {
        holder.title.setText(objects.get(position).getTitle());
        holder.progess.setProgress(objects.get(position).getProgress());
        float prog = objects.get(position).getProgress();
        if (prog <= 40) {
            holder.progess.setProgressBarColor(ContextCompat.getColor(ctx, R.color.red));
        } else if (prog > 40 && prog < 75) {
            holder.progess.setProgressBarColor(ContextCompat.getColor(ctx, R.color.colorYellow));
        } else {
            holder.progess.setProgressBarColor(ContextCompat.getColor(ctx, R.color.green));
        }
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    static class SubsViewHolder extends RecyclerView.ViewHolder {
        CircularProgressBar progess;
        TextView title;

        SubsViewHolder(@NonNull View itemView) {
            super(itemView);
            progess = itemView.findViewById(R.id.circularProgressBar);
            title = itemView.findViewById(R.id.textView38);
        }
    }
}
