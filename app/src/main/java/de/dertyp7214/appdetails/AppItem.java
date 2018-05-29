package de.dertyp7214.appdetails;

import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;

public class AppItem {

    private final Drawable icon;
    private final String title, packageName;
    private final ApplicationInfo applicationInfo;

    public AppItem(Drawable icon, String title, String packageName, ApplicationInfo applicationInfo){
        this.icon=icon;
        this.title=title;
        this.packageName=packageName;
        this.applicationInfo=applicationInfo;
    }

    public ApplicationInfo getApplicationInfo() {
        return applicationInfo;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getTitle() {
        return title;
    }
}
