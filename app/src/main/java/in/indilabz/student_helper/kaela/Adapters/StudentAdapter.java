package in.indilabz.student_helper.kaela.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.indilabz.student_helper.kaela.ModelObjects.ClassObjectStudents;
import in.indilabz.student_helper.kaela.R;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ClassObjectHolder> {
    private Context context;
    private ArrayList<ClassObjectStudents> objects;

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
    public void onBindViewHolder(@NonNull ClassObjectHolder holder, int position) {
        holder.title.setText(objects.get(position).getTitle());
        holder.rcView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        holder.rcViewTeach.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        holder.rcView.setHasFixedSize(true);
        holder.rcViewTeach.setHasFixedSize(true);
        holder.rcView.setAdapter(new SubsAdapter(objects.get(position).getSubs(), context));
        holder.rcViewTeach.setAdapter(new TeacherAdapter(context, objects.get(position).getTeachers()));

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }


    static class ClassObjectHolder extends RecyclerView.ViewHolder {
        RecyclerView rcView, rcViewTeach;
        TextView title;

        public ClassObjectHolder(@NonNull View itemView) {
            super(itemView);
            rcView = itemView.findViewById(R.id.id_rcview_subs);
            rcViewTeach = itemView.findViewById(R.id.id_rcview_teach);
            title = itemView.findViewById(R.id.textView15);
        }
    }
}
