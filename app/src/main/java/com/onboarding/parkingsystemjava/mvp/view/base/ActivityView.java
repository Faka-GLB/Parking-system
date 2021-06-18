package com.onboarding.parkingsystemjava.mvp.view.base;

import android.app.Activity;
import java.lang.ref.WeakReference;

public class ActivityView {
    private final WeakReference<Activity> activityRef;

    public ActivityView(Activity activity) {
        activityRef = new WeakReference<>(activity);
    }

    public Activity getActivity() {
        return activityRef.get();
    }
}
