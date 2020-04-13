package in.indilabz.student_helper.kaela.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import in.indilabz.student_helper.kaela.ModelObjects.OnboardingItemObject;
import in.indilabz.student_helper.kaela.R;

import java.util.ArrayList;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.BoardViewHolder> {
    ArrayList<OnboardingItemObject> objects;

    public OnboardingAdapter(ArrayList<OnboardingItemObject> objects) {
        this.objects = objects;
    }

    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingAdapter.BoardViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.onboarding_layout, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        holder.setOnboardData(objects.get(position));
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    static class BoardViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description;
        private ImageView iconView;

        BoardViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.onbd_t_id);
            description = itemView.findViewById(R.id.onbd_st_id);
            iconView = itemView.findViewById(R.id.onbd_ic_id);
        }

        void setOnboardData(OnboardingItemObject obj) {
            title.setText(obj.getTitle());
            description.setText(obj.getDescription());
            iconView.setImageResource(obj.getIcon());
        }
    }
}
