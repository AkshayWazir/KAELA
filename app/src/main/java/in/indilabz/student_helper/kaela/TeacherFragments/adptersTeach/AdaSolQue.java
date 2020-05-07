package in.indilabz.student_helper.kaela.TeacherFragments.adptersTeach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.indilabz.student_helper.kaela.Interfaces.QuesInter;
import in.indilabz.student_helper.kaela.ModelObjects.AdaUnsQueObj;
import in.indilabz.student_helper.kaela.R;

public class AdaSolQue extends RecyclerView.Adapter<AdaSolQue.AdaSolQueHolder> {
    private ArrayList<AdaUnsQueObj> objects;
    private Context context;
    private QuesInter interact;

    public void setInteract(QuesInter interact) {
        this.interact = interact;
    }

    public AdaSolQue(Context context) {
        this.context = context;
        objects = new ArrayList<>();
    }

    public void setObjects(ArrayList<AdaUnsQueObj> objects) {
        this.objects = objects;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdaSolQueHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.ada_sol_que_layo, parent, false);
        return new AdaSolQueHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaSolQueHolder holder, final int position) {
        holder.ques.setText(objects.get(position).getQues());
        holder.id.setText(objects.get(position).getId());
        holder.name.setText(objects.get(position).getName());
        holder.catchit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interact.quesSelected(objects.get(position).getQuesId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    static class AdaSolQueHolder extends RecyclerView.ViewHolder {
        TextView ques, name, id;
        ConstraintLayout catchit;

        AdaSolQueHolder(@NonNull View itemView) {
            super(itemView);
            ques = itemView.findViewById(R.id.textView58);
            name = itemView.findViewById(R.id.textView59);
            id = itemView.findViewById(R.id.textView60);
            catchit = itemView.findViewById(R.id.id_catch_1);
        }
    }
}
