package com.facebook.react.uimanager;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

public abstract class ViewGroupManager<T extends ViewGroup> extends BaseViewManager<T, LayoutShadowNode> {
    public static WeakHashMap<View, Integer> mZIndexHash = new WeakHashMap<>();

    public LayoutShadowNode createShadowNodeInstance() {
        return new LayoutShadowNode();
    }

    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return LayoutShadowNode.class;
    }

    public void updateExtraData(T t, Object extraData) {
    }

    public void addView(T parent, View child, int index) {
        parent.addView(child, index);
        reorderChildrenByZIndex(parent);
    }

    public void addViews(T parent, List<View> views) {
        int size = views.size();
        for (int i = 0; i < size; i++) {
            addView(parent, (View) views.get(i), i);
        }
    }

    public static void setViewZIndex(View view, int zIndex) {
        mZIndexHash.put(view, Integer.valueOf(zIndex));
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            reorderChildrenByZIndex(parent);
        }
    }

    public static void reorderChildrenByZIndex(ViewGroup view) {
        boolean containsZIndexedElement = false;
        Iterator it = mZIndexHash.values().iterator();
        while (true) {
            if (it.hasNext()) {
                if (((Integer) it.next()).intValue() != 0) {
                    containsZIndexedElement = true;
                    break;
                }
            } else {
                break;
            }
        }
        if (containsZIndexedElement) {
            ArrayList<View> viewsToSort = new ArrayList<>();
            for (int i = 0; i < view.getChildCount(); i++) {
                viewsToSort.add(view.getChildAt(i));
            }
            Collections.sort(viewsToSort, new Comparator<View>() {
                public int compare(View view1, View view2) {
                    Integer view1ZIndex = (Integer) ViewGroupManager.mZIndexHash.get(view1);
                    if (view1ZIndex == null) {
                        view1ZIndex = Integer.valueOf(0);
                    }
                    Integer view2ZIndex = (Integer) ViewGroupManager.mZIndexHash.get(view2);
                    if (view2ZIndex == null) {
                        view2ZIndex = Integer.valueOf(0);
                    }
                    return view1ZIndex.intValue() - view2ZIndex.intValue();
                }
            });
            for (int i2 = 0; i2 < viewsToSort.size(); i2++) {
                ((View) viewsToSort.get(i2)).bringToFront();
            }
            view.invalidate();
        }
    }

    public int getChildCount(T parent) {
        return parent.getChildCount();
    }

    public View getChildAt(T parent, int index) {
        return parent.getChildAt(index);
    }

    public void removeViewAt(T parent, int index) {
        parent.removeViewAt(index);
    }

    public void removeView(T parent, View view) {
        for (int i = 0; i < getChildCount(parent); i++) {
            if (getChildAt(parent, i) == view) {
                removeViewAt(parent, i);
                return;
            }
        }
    }

    public void removeAllViews(T parent) {
        for (int i = getChildCount(parent) - 1; i >= 0; i--) {
            removeViewAt(parent, i);
        }
    }

    public boolean needsCustomLayoutForChildren() {
        return false;
    }

    public boolean shouldPromoteGrandchildren() {
        return false;
    }
}
