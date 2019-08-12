package com.facebook.react.uimanager;

import java.util.Comparator;

class ViewAtIndex {
    public static Comparator<ViewAtIndex> COMPARATOR = new Comparator<ViewAtIndex>() {
        public int compare(ViewAtIndex lhs, ViewAtIndex rhs) {
            return lhs.mIndex - rhs.mIndex;
        }
    };
    public final int mIndex;
    public final int mTag;

    public ViewAtIndex(int tag, int index) {
        this.mTag = tag;
        this.mIndex = index;
    }
}
