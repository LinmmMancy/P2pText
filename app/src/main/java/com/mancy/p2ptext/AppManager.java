package com.mancy.p2ptext;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Mancy on 2017/3/12.
 */

public class AppManager {

    private AppManager() {
    }

    ;

    private static AppManager appManager = new AppManager();

    private static AppManager getInstance() {
        return appManager;
    }

    private Stack<Activity> stack = new Stack<>();

    public void addActivity(Activity activity) {
        // 校验

        if (activity != null) {
            stack.add(activity);
        }
    }


    public void removeActivity(Activity activity) {
        if (activity != null) {

            for (int i = stack.size() - 1; i > 0; i--) {
                Activity currentAcvitity = stack.get(i);
                if (currentAcvitity.getClass().
                        equals(activity.getClass())) {
                    currentAcvitity.finish();

                    stack.remove(currentAcvitity);
                }

            }
        }
    }

    public void removeAll() {
        for (int i = stack.size() - 1; i > 0; i--) {
            Activity currentActivity = stack.get(i);

            currentActivity.finish();

            stack.remove(currentActivity);
        }
    }

    public void removeCurrentActivity() {
        Activity activity = stack.get(stack.size() - 1);
        activity.finish();
        stack.remove(activity);
    }

    public int getStackSize() {
        return stack.size();
    }

}
