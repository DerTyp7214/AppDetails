package de.dertyp7214.appdetails;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppScreen extends AppCompatActivity {

    public static HashMap<String, AppItem> appItemHashMap = new HashMap<>();

    private AppItem appItem;
    private ImageView appIcon;
    private TextView txtLabel;
    private RecyclerView recyclerViewActivities;
    private ActivityAdapter activityAdapter;
    private List<ActivityItem> activityItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_screen);

        Bundle extra = getIntent().getExtras();

        if(extra==null)
            finish();
        if(extra.getString("id")==null)
            finish();

        appItem = appItemHashMap.get(extra.getString("id"));

        activityAdapter = new ActivityAdapter(this, activityItemList);

        appIcon = findViewById(R.id.ic_app);
        txtLabel = findViewById(R.id.txt_label);
        recyclerViewActivities = findViewById(R.id.rv_activity);

        recyclerViewActivities.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewActivities.setAdapter(activityAdapter);

        appIcon.setImageDrawable(appItem.getIcon());
        txtLabel.setText(appItem.getTitle());

        getActivities();

        activityAdapter.notifyDataSetChanged();
        recyclerViewActivities.getLayoutParams().height = activityItemList.size()*getDp(74);
    }

    private int getDp(int dp){
        final float scale = getResources().getDisplayMetrics().density;
        return  (int) (dp * scale + 0.5f);
    }

    private void getActivities(){
        activityItemList.clear();
        try {
            PackageManager pm = getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(appItem.getPackageName(), PackageManager.GET_ACTIVITIES);

            for (ActivityInfo info : packageInfo.activities) {
                ActivityItem item = new ActivityItem(
                        getActivityIcon(appItem.getPackageName(), info.name),
                        getActivityName(info.name),
                        info.name,
                        info
                );
                Log.d("ActivityItem", item.toString());
                activityItemList.add(item);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getActivityName(String name){
        return name.split("\\.")[name.split("\\.").length-1];
    }

    private Drawable getActivityIcon(String packageName, String activityName) {

        PackageManager packageManager = getPackageManager();

        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, activityName));
        ResolveInfo resolveInfo = packageManager.resolveActivity(intent, 0);

        return resolveInfo.loadIcon(packageManager);
    }
}
