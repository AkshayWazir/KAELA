package in.indilabz.student_helper.kaela.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.indilabz.student_helper.kaela.Interfaces.StuFraInt;
import in.indilabz.student_helper.kaela.ModelObjects.ClassObjectStudents;
import in.indilabz.student_helper.kaela.R;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ClassObjectHolder> {
    private Context context;
    private ArrayList<ClassObjectStudents> objects;
    private StuFraInt interact;

    public void setInteract(StuFraInt interact) {
        this.interact = interact;
    }

    public StudentAdapter(Context context, ArrayList<ClassObjectStudents> objects) {
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public ClassObjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rcview_subs_layout, parent, false);
        return new ClassObjectHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ClassObjectHolder holder, int position) {
        holder.title.setText(objects.get(position).getTitle());
        holder.rcView.setLayoutManager(new GridLayoutManager(context, 2));
        holder.rcView.setHasFixedSize(true);
        SubsAdapter adapter = new SubsAdapter(objects.get(position).getSubs(), context, objects.get(position).getTitle());
        adapter.setInteract(interact);
        holder.rcView.setAdapter(adapter);
        holder.clickEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.rcView.getVisibility() == View.GONE) {
                    holder.rcView.setVisibility(View.VISIBLE);
                    ConstraintSet set = new ConstraintSet();
                    set.clone(holder.mainLayout);
                    set.clear(R.id.cardView4, ConstraintSet.BOTTOM);
                    set.applyTo(holder.mainLayout);
                } else {
                    holder.rcView.setVisibility(View.GONE);
                    ConstraintSet set = new ConstraintSet();
                    set.clone(holder.mainLayout);
                    set.connect(R.id.cardView4,
                            ConstraintSet.BOTTOM,
                            ConstraintSet.PARENT_ID,
                            ConstraintSet.BOTTOM,
                            8);
                    set.applyTo(holder.mainLayout);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }


    static class ClassObjectHolder extends RecyclerView.ViewHolder {
        RecyclerView rcView;
        TextView title;
        ConstraintLayout clickEvent, mainLayout;
        CardView cardView;

        ClassObjectHolder(@NonNull View itemView) {
            super(itemView);
            rcView = itemView.findViewById(R.id.id_rcview_subs);
            title = itemView.findViewById(R.id.textView15);
            clickEvent = itemView.findViewById(R.id.constraintLayout);
            cardView = itemView.findViewById(R.id.cardView4);
            mainLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}
