package de.dertyp7214.appdetails;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {

    private Activity context;
    private List<ActivityItem> activityItems;

    public ActivityAdapter(Activity context, List<ActivityItem> activityItems) {
        this.activityItems = activityItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ActivityAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityAdapter.ViewHolder holder, int position) {
        ActivityItem item = activityItems.get(position);

        holder.appTitle.setText(item.getTitle());
        holder.appPackage.setText(item.getPath());
        holder.appIcon.setImageDrawable(item.getIcon());

        holder.view.setOnClickListener(v -> {

        });

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView appIcon;
        TextView appTitle, appPackage;
        View view;

        ViewHolder(View itemView) {
            super(itemView);

            appIcon = itemView.findViewById(R.id.img_icon);
            appTitle = itemView.findViewById(R.id.txt_title);
            appPackage = itemView.findViewById(R.id.txt_path);
            view = itemView.findViewById(R.id.view);

        }
    }

    @Override
    public int getItemCount() {
        return activityItems.size();
    }
}