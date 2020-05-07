package in.indilabz.student_helper.kaela.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zolad.zoominimageview.ZoomInImageView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import in.indilabz.student_helper.kaela.ModelObjects.AdaTeaSolsMO;
import in.indilabz.student_helper.kaela.R;

public class AdaTeaSols extends RecyclerView.Adapter<AdaTeaSols.AdaTeaSolsHol> {
    private ArrayList<AdaTeaSolsMO> items;
    private Context ctx;

    public AdaTeaSols(ArrayList<AdaTeaSolsMO> items, Context ctx) {
        this.items = items;
        this.ctx = ctx;
    }

    public void setItems(ArrayList<AdaTeaSolsMO> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdaTeaSolsHol onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.layout_sol, parent, false);
        return new AdaTeaSolsHol(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaTeaSolsHol holder, int position) {
        holder.teaNam.setText(items.get(position).getTeaNam());
        holder.teaDes.setText(items.get(position).getTeaDesi());
        holder.teaSol.setText(items.get(position).getSolDes());
        // to convert Base64 image string to image
        byte[] pro = Base64.decode(items.get(position).getProImage(), Base64.DEFAULT);
        Bitmap decodedBytePro = BitmapFactory.decodeByteArray(pro, 0, pro.length);
        holder.teaPro.setImageBitmap(decodedBytePro);
        byte[] sol = Base64.decode(items.get(position).getProImage(), Base64.DEFAULT);
        Bitmap decodedByteSol = BitmapFactory.decodeByteArray(sol, 0, sol.length);
        holder.teaSolPic.setImageBitmap(decodedByteSol);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class AdaTeaSolsHol extends RecyclerView.ViewHolder {
        TextView teaNam, teaDes, teaSol;
        CircleImageView teaPro;
        ZoomInImageView teaSolPic;

        public AdaTeaSolsHol(@NonNull View itemView) {
            super(itemView);
            teaNam = itemView.findViewById(R.id.textView65);
            teaDes = itemView.findViewById(R.id.textView66);
            teaSol = itemView.findViewById(R.id.textView67);
            teaPro = itemView.findViewById(R.id.circleImageView3);
            teaSolPic = itemView.findViewById(R.id.zoomInImageView2);
        }
    }
}
