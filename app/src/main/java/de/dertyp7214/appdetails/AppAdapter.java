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

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {

    private Activity context;
    private List<AppItem> appItemList;

    public AppAdapter(Activity context, List<AppItem> appItemList) {
        this.appItemList = appItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public AppAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AppAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.app_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AppAdapter.ViewHolder holder, int position) {
        AppItem item = appItemList.get(position);

        holder.appTitle.setText(item.getTitle());
        holder.appPackage.setText(item.getPackageName());
        holder.appIcon.setImageDrawable(item.getIcon());

        AppScreen.appItemHashMap.put(item.getPackageName(), item);

        holder.view.setOnClickListener(v -> {
            Pair<View, String> icon = Pair.create(holder.appIcon, "icon");
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(context, icon);
            context.startActivity(new Intent(context, AppScreen.class).putExtra("id", item.getPackageName()), options.toBundle());
        });

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView appIcon;
        TextView appTitle, appPackage;
        View view;

        ViewHolder(View itemView) {
            super(itemView);

            appIcon = itemView.findViewById(R.id.img_icon);
            appTitle = itemView.findViewById(R.id.txt_label);
            appPackage = itemView.findViewById(R.id.txt_pkg);
            view = itemView.findViewById(R.id.view);

        }
    }

    @Override
    public int getItemCount() {
        return appItemList.size();
    }
}