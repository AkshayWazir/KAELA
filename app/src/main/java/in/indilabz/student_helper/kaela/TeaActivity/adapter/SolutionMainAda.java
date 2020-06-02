package in.indilabz.student_helper.kaela.TeaActivity.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.zolad.zoominimageview.ZoomInImageView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import in.indilabz.student_helper.kaela.Interfaces.acceptSol;
import in.indilabz.student_helper.kaela.R;
import in.indilabz.student_helper.kaela.TeaActivity.moTea.Sol_moob;

import static android.content.Context.MODE_PRIVATE;

public class SolutionMainAda extends RecyclerView.Adapter<SolutionMainAda.SolutionMainHolder> {
    private ArrayList<Sol_moob> objs;
    private Context ctx;
    acceptSol obj;

    public void setObj(acceptSol obj) {
        this.obj = obj;
    }

    public SolutionMainAda(Context ctx) {
        this.ctx = ctx;
    }

    public void setObjs(ArrayList<Sol_moob> objs) {
        this.objs = objs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SolutionMainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.sols_main_layout, parent, false);
        return new SolutionMainHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SolutionMainHolder holder, final int position) {
        holder.solPic.setImageBitmap(objs.get(position).getSolPic());
        holder.propic.setImageBitmap(objs.get(position).getProPic());
        holder.name.setText(objs.get(position).getName());
        holder.desig.setText(objs.get(position).getDesig());
        holder.desc.setText(objs.get(position).getDesc());
        SharedPreferences prefs = ctx.getSharedPreferences("USER", MODE_PRIVATE);
        // section to check Who's calling
        if (objs.get(position).isAccepted()){
            holder.accepted.setVisibility(View.VISIBLE);
        }
        int teaOrStu = prefs.getInt("TYPE", 0);
        if (teaOrStu == 0) {
            holder.selectSolution.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obj.raiseDialog(objs.get(position).getSol_id());
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return objs.size();
    }

    static class SolutionMainHolder extends RecyclerView.ViewHolder {
        TextView name, desig, desc;
        CircleImageView propic;
        ZoomInImageView solPic;
        ConstraintLayout selectSolution;
        ImageView accepted;

        SolutionMainHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView73);
            desig = itemView.findViewById(R.id.textView74);
            desc = itemView.findViewById(R.id.textView75);
            propic = itemView.findViewById(R.id.circleImageView5);
            solPic = itemView.findViewById(R.id.sol_pic_id);
            selectSolution = itemView.findViewById(R.id.select_solution_id);
            accepted = itemView.findViewById(R.id.imageView17);
        }
    }
}
