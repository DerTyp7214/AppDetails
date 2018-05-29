package de.dertyp7214.appdetails;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<AppItem> appItems = new ArrayList<>();
    private Activity activity;

    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        activity = getActivity();

        recyclerView = rootView.findViewById(R.id.rv);
        AppAdapter adapter = new AppAdapter(activity, appItems);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        loadApps();

        adapter.notifyDataSetChanged();

        return rootView;
    }

    private void loadApps(){
        appItems.clear();
        PackageManager packageManager = activity.getPackageManager();
        List<ApplicationInfo> list = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        for(ApplicationInfo info : list){
            appItems.add(new AppItem(
                    packageManager.getApplicationIcon(info),
                    packageManager.getApplicationLabel(info).toString(),
                    info.packageName, info));
        }
        Collections.sort(appItems, (item, t1) -> {
            String s1 = item.getTitle();
            String s2 = t1.getTitle();
            return s1.compareToIgnoreCase(s2);
        });
    }
}
