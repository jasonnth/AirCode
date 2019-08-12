package com.airbnb.android.core.controllers;

import com.airbnb.android.core.utils.Check;
import java.util.HashSet;
import java.util.Set;

public class BottomBarController {
    private boolean showBottomBar = true;
    private final Set<OnBottomBarVisibilityChangeListener> visibilityChangeListeners = new HashSet();

    public interface OnBottomBarVisibilityChangeListener {
        void onBottomBarVisibilityChange(boolean z, boolean z2);
    }

    public void addOnBottomBarVisibilityChangeListener(OnBottomBarVisibilityChangeListener listener) {
        Check.state(this.visibilityChangeListeners.add(listener), "listener was already added to set");
    }

    public void removeOnBottomBarVisibilityChangeListener(OnBottomBarVisibilityChangeListener listener) {
        Check.state(this.visibilityChangeListeners.remove(listener), "listener did not exist in set");
    }

    public boolean shouldShowBottomBar() {
        return this.showBottomBar;
    }

    public void setShowBottomBar(boolean show) {
        setShowBottomBar(show, false);
    }

    public void setShowBottomBar(boolean show, boolean animate) {
        if (show != this.showBottomBar) {
            this.showBottomBar = show;
            notifyVisibilityChanged(animate);
        }
    }

    private void notifyVisibilityChanged(boolean animate) {
        for (OnBottomBarVisibilityChangeListener listener : this.visibilityChangeListeners) {
            listener.onBottomBarVisibilityChange(this.showBottomBar, animate);
        }
    }
}
