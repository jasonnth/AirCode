package android.support.p000v4.internal.view;

import android.support.p000v4.view.ActionProvider;
import android.support.p000v4.view.MenuItemCompat.OnActionExpandListener;
import android.view.MenuItem;
import android.view.View;

/* renamed from: android.support.v4.internal.view.SupportMenuItem */
public interface SupportMenuItem extends MenuItem {
    boolean collapseActionView();

    boolean expandActionView();

    View getActionView();

    ActionProvider getSupportActionProvider();

    boolean isActionViewExpanded();

    MenuItem setActionView(int i);

    MenuItem setActionView(View view);

    void setShowAsAction(int i);

    MenuItem setShowAsActionFlags(int i);

    SupportMenuItem setSupportActionProvider(ActionProvider actionProvider);

    SupportMenuItem setSupportOnActionExpandListener(OnActionExpandListener onActionExpandListener);
}
