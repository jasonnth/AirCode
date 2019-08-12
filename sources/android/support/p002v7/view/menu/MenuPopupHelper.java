package android.support.p002v7.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.p000v4.view.GravityCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p002v7.appcompat.C0199R;
import android.support.p002v7.view.menu.MenuPresenter.Callback;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow.OnDismissListener;

/* renamed from: android.support.v7.view.menu.MenuPopupHelper */
public class MenuPopupHelper implements MenuHelper {
    private static final int TOUCH_EPICENTER_SIZE_DP = 48;
    private View mAnchorView;
    private final Context mContext;
    private int mDropDownGravity;
    private boolean mForceShowIcon;
    private final OnDismissListener mInternalOnDismissListener;
    private final MenuBuilder mMenu;
    private OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    private MenuPopup mPopup;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private Callback mPresenterCallback;

    public MenuPopupHelper(Context context, MenuBuilder menu) {
        this(context, menu, null, false, C0199R.attr.popupMenuStyle, 0);
    }

    public MenuPopupHelper(Context context, MenuBuilder menu, View anchorView) {
        this(context, menu, anchorView, false, C0199R.attr.popupMenuStyle, 0);
    }

    public MenuPopupHelper(Context context, MenuBuilder menu, View anchorView, boolean overflowOnly, int popupStyleAttr) {
        this(context, menu, anchorView, overflowOnly, popupStyleAttr, 0);
    }

    public MenuPopupHelper(Context context, MenuBuilder menu, View anchorView, boolean overflowOnly, int popupStyleAttr, int popupStyleRes) {
        this.mDropDownGravity = 8388611;
        this.mInternalOnDismissListener = new OnDismissListener() {
            public void onDismiss() {
                MenuPopupHelper.this.onDismiss();
            }
        };
        this.mContext = context;
        this.mMenu = menu;
        this.mAnchorView = anchorView;
        this.mOverflowOnly = overflowOnly;
        this.mPopupStyleAttr = popupStyleAttr;
        this.mPopupStyleRes = popupStyleRes;
    }

    public void setOnDismissListener(OnDismissListener listener) {
        this.mOnDismissListener = listener;
    }

    public void setAnchorView(View anchor) {
        this.mAnchorView = anchor;
    }

    public void setForceShowIcon(boolean forceShowIcon) {
        this.mForceShowIcon = forceShowIcon;
        if (this.mPopup != null) {
            this.mPopup.setForceShowIcon(forceShowIcon);
        }
    }

    public void setGravity(int gravity) {
        this.mDropDownGravity = gravity;
    }

    public int getGravity() {
        return this.mDropDownGravity;
    }

    public void show() {
        if (!tryShow()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public void show(int x, int y) {
        if (!tryShow(x, y)) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public MenuPopup getPopup() {
        if (this.mPopup == null) {
            this.mPopup = createPopup();
        }
        return this.mPopup;
    }

    public boolean tryShow() {
        if (isShowing()) {
            return true;
        }
        if (this.mAnchorView == null) {
            return false;
        }
        showPopup(0, 0, false, false);
        return true;
    }

    public boolean tryShow(int x, int y) {
        if (isShowing()) {
            return true;
        }
        if (this.mAnchorView == null) {
            return false;
        }
        showPopup(x, y, true, true);
        return true;
    }

    private MenuPopup createPopup() {
        MenuPopup popup;
        Display display = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay();
        Point displaySize = new Point();
        if (VERSION.SDK_INT >= 17) {
            display.getRealSize(displaySize);
        } else if (VERSION.SDK_INT >= 13) {
            display.getSize(displaySize);
        } else {
            displaySize.set(display.getWidth(), display.getHeight());
        }
        if (Math.min(displaySize.x, displaySize.y) >= this.mContext.getResources().getDimensionPixelSize(C0199R.dimen.abc_cascading_menus_min_smallest_width)) {
            popup = new CascadingMenuPopup(this.mContext, this.mAnchorView, this.mPopupStyleAttr, this.mPopupStyleRes, this.mOverflowOnly);
        } else {
            popup = new StandardMenuPopup(this.mContext, this.mMenu, this.mAnchorView, this.mPopupStyleAttr, this.mPopupStyleRes, this.mOverflowOnly);
        }
        popup.addMenu(this.mMenu);
        popup.setOnDismissListener(this.mInternalOnDismissListener);
        popup.setAnchorView(this.mAnchorView);
        popup.setCallback(this.mPresenterCallback);
        popup.setForceShowIcon(this.mForceShowIcon);
        popup.setGravity(this.mDropDownGravity);
        return popup;
    }

    private void showPopup(int xOffset, int yOffset, boolean useOffsets, boolean showTitle) {
        MenuPopup popup = getPopup();
        popup.setShowTitle(showTitle);
        if (useOffsets) {
            if ((GravityCompat.getAbsoluteGravity(this.mDropDownGravity, ViewCompat.getLayoutDirection(this.mAnchorView)) & 7) == 5) {
                xOffset -= this.mAnchorView.getWidth();
            }
            popup.setHorizontalOffset(xOffset);
            popup.setVerticalOffset(yOffset);
            int halfSize = (int) ((48.0f * this.mContext.getResources().getDisplayMetrics().density) / 2.0f);
            popup.setEpicenterBounds(new Rect(xOffset - halfSize, yOffset - halfSize, xOffset + halfSize, yOffset + halfSize));
        }
        popup.show();
    }

    public void dismiss() {
        if (isShowing()) {
            this.mPopup.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void onDismiss() {
        this.mPopup = null;
        if (this.mOnDismissListener != null) {
            this.mOnDismissListener.onDismiss();
        }
    }

    public boolean isShowing() {
        return this.mPopup != null && this.mPopup.isShowing();
    }

    public void setPresenterCallback(Callback cb) {
        this.mPresenterCallback = cb;
        if (this.mPopup != null) {
            this.mPopup.setCallback(cb);
        }
    }
}
