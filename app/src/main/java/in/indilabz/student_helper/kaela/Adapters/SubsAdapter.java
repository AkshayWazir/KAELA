package in.indilabz.student_helper.kaela.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import in.indilabz.student_helper.kaela.Interfaces.StuFraInt;
import in.indilabz.student_helper.kaela.R;

public class SubsAdapter extends RecyclerView.Adapter<SubsAdapter.SubsViewHolder> {
    private String[] subs;
    private Context ctx;
    private String classSubject;
    private StuFraInt interact;

    public void setInteract(StuFraInt interact) {
        this.interact = interact;
    }

    SubsAdapter(String[] subs, Context ctx, String classSubject) {
        this.subs = subs;
        this.ctx = ctx;
        this.classSubject = classSubject;
    }

    @NonNull
    @Override
    public SubsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.subs_layout, parent, false);
        return new SubsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SubsViewHolder holder, final int position) {
        holder.sub.setText(subs[position]);
        holder.subs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interact.showTeachers(classSubject, subs[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subs.length;
    }


    static class SubsViewHolder extends RecyclerView.ViewHolder {
        TextView sub;
        CardView subs;

        SubsViewHolder(@NonNull View itemView) {
            super(itemView);
            sub = itemView.findViewById(R.id.textView16);
            subs = itemView.findViewById(R.id.card_subs);
        }
    }
}
