package in.indilabz.student_helper.kaela.TeacherFragments.adptersTeach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.indilabz.student_helper.kaela.ModelObjects.AdaUnsQueObj;
import in.indilabz.student_helper.kaela.R;

public class AdaUnsQue extends RecyclerView.Adapter<AdaUnsQue.AdaUnsQueHolder> {
    private ArrayList<AdaUnsQueObj> objects;
    private Context ctx;

    public AdaUnsQue(Context ctx) {
        this.ctx = ctx;
        objects = new ArrayList<>();
    }

    public void setObjects(ArrayList<AdaUnsQueObj> objects) {
        this.objects = objects;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdaUnsQueHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.un_ques_lay, parent, false);
        return new AdaUnsQueHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaUnsQueHolder holder, int position) {
        holder.stuId.setText(objects.get(position).getId());
        holder.ques.setText(objects.get(position).getQues());
        holder.desc.setText(objects.get(position).getDesc());
        holder.stuNam.setText(objects.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    static class AdaUnsQueHolder extends RecyclerView.ViewHolder {
        TextView ques, desc, stuNam, stuId;

        AdaUnsQueHolder(@NonNull View itemView) {
            super(itemView);
            ques = itemView.findViewById(R.id.textView42);
            desc = itemView.findViewById(R.id.textView54);
            stuNam = itemView.findViewById(R.id.textView55);
            stuId = itemView.findViewById(R.id.textView56);
        }
    }
}
