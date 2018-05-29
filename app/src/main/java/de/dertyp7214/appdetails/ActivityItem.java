package de.dertyp7214.appdetails;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;

public class ActivityItem {

    private final Drawable icon;
    private final String title, path;
    private final ActivityInfo activityInfo;

    public ActivityItem(Drawable icon, String title, String path, ActivityInfo activityInfo){
        this.icon=icon;
        this.title=title;
        this.path=path;
        this.activityInfo=activityInfo;
    }

    public String getTitle() {
        return title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getPath() {
        return path;
    }

    public ActivityInfo getActivityInfo() {
        return activityInfo;
    }

    @Override
    public String toString() {
        return "Title: "+title+", Path: "+path+", ActivityInfo: "+activityInfo.toString();
    }
}
