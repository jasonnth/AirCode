package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.R;
import android.support.p000v4.view.ViewCompat;
import android.support.p002v7.view.menu.ListMenuPresenter;
import android.support.p002v7.view.menu.MenuBuilder;
import android.support.p002v7.view.menu.MenuItemImpl;
import android.support.p002v7.view.menu.MenuPresenter;
import android.support.p002v7.view.menu.MenuPresenter.Callback;
import android.support.p002v7.view.menu.MenuView;
import android.support.p002v7.view.menu.SubMenuBuilder;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class NavigationMenuPresenter implements MenuPresenter {
    NavigationMenuAdapter mAdapter;
    private Callback mCallback;
    LinearLayout mHeaderLayout;
    ColorStateList mIconTintList;
    private int mId;
    Drawable mItemBackground;
    LayoutInflater mLayoutInflater;
    MenuBuilder mMenu;
    private NavigationMenuView mMenuView;
    final OnClickListener mOnClickListener;
    int mPaddingSeparator;
    int mTextAppearance;
    boolean mTextAppearanceSet;
    ColorStateList mTextColor;

    private static class HeaderViewHolder extends ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class NavigationMenuAdapter extends Adapter<ViewHolder> {
        private MenuItemImpl mCheckedItem;
        private final ArrayList<NavigationMenuItem> mItems = new ArrayList<>();
        private boolean mUpdateSuspended;

        NavigationMenuAdapter() {
            prepareMenuItems();
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public int getItemCount() {
            return this.mItems.size();
        }

        public int getItemViewType(int position) {
            NavigationMenuItem item = (NavigationMenuItem) this.mItems.get(position);
            if (item instanceof NavigationMenuSeparatorItem) {
                return 2;
            }
            if (item instanceof NavigationMenuHeaderItem) {
                return 3;
            }
            if (!(item instanceof NavigationMenuTextItem)) {
                throw new RuntimeException("Unknown item type.");
            } else if (((NavigationMenuTextItem) item).getMenuItem().hasSubMenu()) {
                return 1;
            } else {
                return 0;
            }
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case 0:
                    return new NormalViewHolder(NavigationMenuPresenter.this.mLayoutInflater, parent, NavigationMenuPresenter.this.mOnClickListener);
                case 1:
                    return new SubheaderViewHolder(NavigationMenuPresenter.this.mLayoutInflater, parent);
                case 2:
                    return new SeparatorViewHolder(NavigationMenuPresenter.this.mLayoutInflater, parent);
                case 3:
                    return new HeaderViewHolder(NavigationMenuPresenter.this.mHeaderLayout);
                default:
                    return null;
            }
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            switch (getItemViewType(position)) {
                case 0:
                    NavigationMenuItemView itemView = (NavigationMenuItemView) holder.itemView;
                    itemView.setIconTintList(NavigationMenuPresenter.this.mIconTintList);
                    if (NavigationMenuPresenter.this.mTextAppearanceSet) {
                        itemView.setTextAppearance(NavigationMenuPresenter.this.mTextAppearance);
                    }
                    if (NavigationMenuPresenter.this.mTextColor != null) {
                        itemView.setTextColor(NavigationMenuPresenter.this.mTextColor);
                    }
                    ViewCompat.setBackground(itemView, NavigationMenuPresenter.this.mItemBackground != null ? NavigationMenuPresenter.this.mItemBackground.getConstantState().newDrawable() : null);
                    NavigationMenuTextItem item = (NavigationMenuTextItem) this.mItems.get(position);
                    itemView.setNeedsEmptyIcon(item.needsEmptyIcon);
                    itemView.initialize(item.getMenuItem(), 0);
                    return;
                case 1:
                    ((TextView) holder.itemView).setText(((NavigationMenuTextItem) this.mItems.get(position)).getMenuItem().getTitle());
                    return;
                case 2:
                    NavigationMenuSeparatorItem item2 = (NavigationMenuSeparatorItem) this.mItems.get(position);
                    holder.itemView.setPadding(0, item2.getPaddingTop(), 0, item2.getPaddingBottom());
                    return;
                default:
                    return;
            }
        }

        public void onViewRecycled(ViewHolder holder) {
            if (holder instanceof NormalViewHolder) {
                ((NavigationMenuItemView) holder.itemView).recycle();
            }
        }

        public void update() {
            prepareMenuItems();
            notifyDataSetChanged();
        }

        private void prepareMenuItems() {
            if (!this.mUpdateSuspended) {
                this.mUpdateSuspended = true;
                this.mItems.clear();
                this.mItems.add(new NavigationMenuHeaderItem());
                int currentGroupId = -1;
                int currentGroupStart = 0;
                boolean currentGroupHasIcon = false;
                int totalSize = NavigationMenuPresenter.this.mMenu.getVisibleItems().size();
                for (int i = 0; i < totalSize; i++) {
                    MenuItemImpl item = (MenuItemImpl) NavigationMenuPresenter.this.mMenu.getVisibleItems().get(i);
                    if (item.isChecked()) {
                        setCheckedItem(item);
                    }
                    if (item.isCheckable()) {
                        item.setExclusiveCheckable(false);
                    }
                    if (item.hasSubMenu()) {
                        SubMenu subMenu = item.getSubMenu();
                        if (subMenu.hasVisibleItems()) {
                            if (i != 0) {
                                this.mItems.add(new NavigationMenuSeparatorItem(NavigationMenuPresenter.this.mPaddingSeparator, 0));
                            }
                            ArrayList<NavigationMenuItem> arrayList = this.mItems;
                            NavigationMenuTextItem navigationMenuTextItem = new NavigationMenuTextItem(item);
                            arrayList.add(navigationMenuTextItem);
                            boolean subMenuHasIcon = false;
                            int subMenuStart = this.mItems.size();
                            int size = subMenu.size();
                            for (int j = 0; j < size; j++) {
                                MenuItemImpl subMenuItem = (MenuItemImpl) subMenu.getItem(j);
                                if (subMenuItem.isVisible()) {
                                    if (!subMenuHasIcon && subMenuItem.getIcon() != null) {
                                        subMenuHasIcon = true;
                                    }
                                    if (subMenuItem.isCheckable()) {
                                        subMenuItem.setExclusiveCheckable(false);
                                    }
                                    if (item.isChecked()) {
                                        setCheckedItem(item);
                                    }
                                    ArrayList<NavigationMenuItem> arrayList2 = this.mItems;
                                    NavigationMenuTextItem navigationMenuTextItem2 = new NavigationMenuTextItem(subMenuItem);
                                    arrayList2.add(navigationMenuTextItem2);
                                }
                            }
                            if (subMenuHasIcon) {
                                appendTransparentIconIfMissing(subMenuStart, this.mItems.size());
                            }
                        }
                    } else {
                        int groupId = item.getGroupId();
                        if (groupId != currentGroupId) {
                            currentGroupStart = this.mItems.size();
                            currentGroupHasIcon = item.getIcon() != null;
                            if (i != 0) {
                                currentGroupStart++;
                                this.mItems.add(new NavigationMenuSeparatorItem(NavigationMenuPresenter.this.mPaddingSeparator, NavigationMenuPresenter.this.mPaddingSeparator));
                            }
                        } else if (!currentGroupHasIcon && item.getIcon() != null) {
                            currentGroupHasIcon = true;
                            appendTransparentIconIfMissing(currentGroupStart, this.mItems.size());
                        }
                        NavigationMenuTextItem textItem = new NavigationMenuTextItem(item);
                        textItem.needsEmptyIcon = currentGroupHasIcon;
                        this.mItems.add(textItem);
                        currentGroupId = groupId;
                    }
                }
                this.mUpdateSuspended = false;
            }
        }

        private void appendTransparentIconIfMissing(int startIndex, int endIndex) {
            for (int i = startIndex; i < endIndex; i++) {
                ((NavigationMenuTextItem) this.mItems.get(i)).needsEmptyIcon = true;
            }
        }

        public void setCheckedItem(MenuItemImpl checkedItem) {
            if (this.mCheckedItem != checkedItem && checkedItem.isCheckable()) {
                if (this.mCheckedItem != null) {
                    this.mCheckedItem.setChecked(false);
                }
                this.mCheckedItem = checkedItem;
                checkedItem.setChecked(true);
            }
        }

        public Bundle createInstanceState() {
            Bundle state = new Bundle();
            if (this.mCheckedItem != null) {
                state.putInt("android:menu:checked", this.mCheckedItem.getItemId());
            }
            SparseArray<ParcelableSparseArray> actionViewStates = new SparseArray<>();
            int size = this.mItems.size();
            for (int i = 0; i < size; i++) {
                NavigationMenuItem navigationMenuItem = (NavigationMenuItem) this.mItems.get(i);
                if (navigationMenuItem instanceof NavigationMenuTextItem) {
                    MenuItemImpl item = ((NavigationMenuTextItem) navigationMenuItem).getMenuItem();
                    View actionView = item != null ? item.getActionView() : null;
                    if (actionView != null) {
                        ParcelableSparseArray container = new ParcelableSparseArray();
                        actionView.saveHierarchyState(container);
                        actionViewStates.put(item.getItemId(), container);
                    }
                }
            }
            state.putSparseParcelableArray("android:menu:action_views", actionViewStates);
            return state;
        }

        public void restoreInstanceState(Bundle state) {
            int checkedItem = state.getInt("android:menu:checked", 0);
            if (checkedItem != 0) {
                this.mUpdateSuspended = true;
                int i = 0;
                int size = this.mItems.size();
                while (true) {
                    if (i >= size) {
                        break;
                    }
                    NavigationMenuItem item = (NavigationMenuItem) this.mItems.get(i);
                    if (item instanceof NavigationMenuTextItem) {
                        MenuItemImpl menuItem = ((NavigationMenuTextItem) item).getMenuItem();
                        if (menuItem != null && menuItem.getItemId() == checkedItem) {
                            setCheckedItem(menuItem);
                            break;
                        }
                    }
                    i++;
                }
                this.mUpdateSuspended = false;
                prepareMenuItems();
            }
            SparseArray<ParcelableSparseArray> actionViewStates = state.getSparseParcelableArray("android:menu:action_views");
            if (actionViewStates != null) {
                int size2 = this.mItems.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    NavigationMenuItem navigationMenuItem = (NavigationMenuItem) this.mItems.get(i2);
                    if (navigationMenuItem instanceof NavigationMenuTextItem) {
                        MenuItemImpl item2 = ((NavigationMenuTextItem) navigationMenuItem).getMenuItem();
                        if (item2 != null) {
                            View actionView = item2.getActionView();
                            if (actionView != null) {
                                ParcelableSparseArray container = (ParcelableSparseArray) actionViewStates.get(item2.getItemId());
                                if (container != null) {
                                    actionView.restoreHierarchyState(container);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static class NavigationMenuHeaderItem implements NavigationMenuItem {
        NavigationMenuHeaderItem() {
        }
    }

    private interface NavigationMenuItem {
    }

    private static class NavigationMenuSeparatorItem implements NavigationMenuItem {
        private final int mPaddingBottom;
        private final int mPaddingTop;

        public NavigationMenuSeparatorItem(int paddingTop, int paddingBottom) {
            this.mPaddingTop = paddingTop;
            this.mPaddingBottom = paddingBottom;
        }

        public int getPaddingTop() {
            return this.mPaddingTop;
        }

        public int getPaddingBottom() {
            return this.mPaddingBottom;
        }
    }

    private static class NavigationMenuTextItem implements NavigationMenuItem {
        private final MenuItemImpl mMenuItem;
        boolean needsEmptyIcon;

        NavigationMenuTextItem(MenuItemImpl item) {
            this.mMenuItem = item;
        }

        public MenuItemImpl getMenuItem() {
            return this.mMenuItem;
        }
    }

    private static class NormalViewHolder extends ViewHolder {
        public NormalViewHolder(LayoutInflater inflater, ViewGroup parent, OnClickListener listener) {
            super(inflater.inflate(R.layout.design_navigation_item, parent, false));
            this.itemView.setOnClickListener(listener);
        }
    }

    private static class SeparatorViewHolder extends ViewHolder {
        public SeparatorViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.design_navigation_item_separator, parent, false));
        }
    }

    private static class SubheaderViewHolder extends ViewHolder {
        public SubheaderViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.design_navigation_item_subheader, parent, false));
        }
    }

    private static abstract class ViewHolder extends android.support.p002v7.widget.RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void initForMenu(Context context, MenuBuilder menu) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mMenu = menu;
        this.mPaddingSeparator = context.getResources().getDimensionPixelOffset(R.dimen.design_navigation_separator_vertical_padding);
    }

    public MenuView getMenuView(ViewGroup root) {
        if (this.mMenuView == null) {
            this.mMenuView = (NavigationMenuView) this.mLayoutInflater.inflate(R.layout.design_navigation_menu, root, false);
            if (this.mAdapter == null) {
                this.mAdapter = new NavigationMenuAdapter();
            }
            this.mHeaderLayout = (LinearLayout) this.mLayoutInflater.inflate(R.layout.design_navigation_item_header, this.mMenuView, false);
            this.mMenuView.setAdapter(this.mAdapter);
        }
        return this.mMenuView;
    }

    public void updateMenuView(boolean cleared) {
        if (this.mAdapter != null) {
            this.mAdapter.update();
        }
    }

    public void setCallback(Callback cb) {
        this.mCallback = cb;
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenu) {
        return false;
    }

    public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
        if (this.mCallback != null) {
            this.mCallback.onCloseMenu(menu, allMenusAreClosing);
        }
    }

    public boolean flagActionItems() {
        return false;
    }

    public boolean expandItemActionView(MenuBuilder menu, MenuItemImpl item) {
        return false;
    }

    public boolean collapseItemActionView(MenuBuilder menu, MenuItemImpl item) {
        return false;
    }

    public int getId() {
        return this.mId;
    }

    public Parcelable onSaveInstanceState() {
        if (VERSION.SDK_INT < 11) {
            return null;
        }
        Bundle state = new Bundle();
        if (this.mMenuView != null) {
            SparseArray<Parcelable> hierarchy = new SparseArray<>();
            this.mMenuView.saveHierarchyState(hierarchy);
            state.putSparseParcelableArray(ListMenuPresenter.VIEWS_TAG, hierarchy);
        }
        if (this.mAdapter != null) {
            state.putBundle("android:menu:adapter", this.mAdapter.createInstanceState());
        }
        if (this.mHeaderLayout == null) {
            return state;
        }
        SparseArray<Parcelable> header = new SparseArray<>();
        this.mHeaderLayout.saveHierarchyState(header);
        state.putSparseParcelableArray("android:menu:header", header);
        return state;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle state = (Bundle) parcelable;
            SparseArray<Parcelable> hierarchy = state.getSparseParcelableArray(ListMenuPresenter.VIEWS_TAG);
            if (hierarchy != null) {
                this.mMenuView.restoreHierarchyState(hierarchy);
            }
            Bundle adapterState = state.getBundle("android:menu:adapter");
            if (adapterState != null) {
                this.mAdapter.restoreInstanceState(adapterState);
            }
            SparseArray<Parcelable> header = state.getSparseParcelableArray("android:menu:header");
            if (header != null) {
                this.mHeaderLayout.restoreHierarchyState(header);
            }
        }
    }

    public void setCheckedItem(MenuItemImpl item) {
        this.mAdapter.setCheckedItem(item);
    }

    public int getHeaderCount() {
        return this.mHeaderLayout.getChildCount();
    }

    public ColorStateList getItemTintList() {
        return this.mIconTintList;
    }

    public void setItemIconTintList(ColorStateList tint) {
        this.mIconTintList = tint;
        updateMenuView(false);
    }

    public ColorStateList getItemTextColor() {
        return this.mTextColor;
    }

    public void setItemTextColor(ColorStateList textColor) {
        this.mTextColor = textColor;
        updateMenuView(false);
    }

    public void setItemTextAppearance(int resId) {
        this.mTextAppearance = resId;
        this.mTextAppearanceSet = true;
        updateMenuView(false);
    }

    public Drawable getItemBackground() {
        return this.mItemBackground;
    }

    public void setItemBackground(Drawable itemBackground) {
        this.mItemBackground = itemBackground;
        updateMenuView(false);
    }
}
