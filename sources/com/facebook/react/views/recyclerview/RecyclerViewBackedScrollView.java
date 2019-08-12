package com.facebook.react.views.recyclerview;

import android.content.Context;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.ContentSizeChangeEvent;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import com.facebook.react.views.scroll.ScrollEvent;
import com.facebook.react.views.scroll.ScrollEventType;
import java.util.ArrayList;
import java.util.List;

@VisibleForTesting
public class RecyclerViewBackedScrollView extends RecyclerView {
    private boolean mSendContentSizeChangeEvents;

    private static class ConcreteViewHolder extends ViewHolder {
        public ConcreteViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class ReactListAdapter extends Adapter<ConcreteViewHolder> {
        private final OnLayoutChangeListener mChildLayoutChangeListener = new OnLayoutChangeListener() {
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                int oldHeight = oldBottom - oldTop;
                int newHeight = bottom - top;
                if (oldHeight != newHeight) {
                    ReactListAdapter.this.updateTotalChildrenHeight(newHeight - oldHeight);
                    ReactListAdapter.this.mScrollOffsetTracker.onHeightChange(ReactListAdapter.this.mViews.indexOf(v), oldHeight, newHeight);
                    if (v.getParent() != null && v.getParent().getParent() != null) {
                        View wrapper = (View) v.getParent();
                        wrapper.forceLayout();
                        ((View) wrapper.getParent()).forceLayout();
                    }
                }
            }
        };
        /* access modifiers changed from: private */
        public final ScrollOffsetTracker mScrollOffsetTracker;
        private final RecyclerViewBackedScrollView mScrollView;
        private int mTotalChildrenHeight = 0;
        /* access modifiers changed from: private */
        public final List<View> mViews = new ArrayList();

        public ReactListAdapter(RecyclerViewBackedScrollView scrollView) {
            this.mScrollView = scrollView;
            this.mScrollOffsetTracker = new ScrollOffsetTracker(this);
            setHasStableIds(true);
        }

        public void addView(View child, int index) {
            this.mViews.add(index, child);
            updateTotalChildrenHeight(child.getMeasuredHeight());
            child.addOnLayoutChangeListener(this.mChildLayoutChangeListener);
            notifyItemInserted(index);
        }

        public void removeViewAt(int index) {
            View child = (View) this.mViews.get(index);
            if (child != null) {
                this.mViews.remove(index);
                child.removeOnLayoutChangeListener(this.mChildLayoutChangeListener);
                updateTotalChildrenHeight(-child.getMeasuredHeight());
                notifyItemRemoved(index);
            }
        }

        /* access modifiers changed from: private */
        public void updateTotalChildrenHeight(int delta) {
            if (delta != 0) {
                this.mTotalChildrenHeight += delta;
                this.mScrollView.onTotalChildrenHeightChange(this.mTotalChildrenHeight);
            }
        }

        public ConcreteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ConcreteViewHolder(new RecyclableWrapperViewGroup(parent.getContext()));
        }

        public void onBindViewHolder(ConcreteViewHolder holder, int position) {
            RecyclableWrapperViewGroup vg = (RecyclableWrapperViewGroup) holder.itemView;
            View row = (View) this.mViews.get(position);
            if (row.getParent() != vg) {
                vg.addView(row, 0);
            }
        }

        public void onViewRecycled(ConcreteViewHolder holder) {
            super.onViewRecycled(holder);
            ((RecyclableWrapperViewGroup) holder.itemView).removeAllViews();
        }

        public int getItemCount() {
            return this.mViews.size();
        }

        public long getItemId(int position) {
            return (long) ((View) this.mViews.get(position)).getId();
        }

        public View getView(int index) {
            return (View) this.mViews.get(index);
        }

        public int getTotalChildrenHeight() {
            return this.mTotalChildrenHeight;
        }

        public int getTopOffsetForItem(int index) {
            return this.mScrollOffsetTracker.getTopOffsetForItem(index);
        }
    }

    private static class RecyclableWrapperViewGroup extends ViewGroup {
        public RecyclableWrapperViewGroup(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void onLayout(boolean changed, int l, int t, int r, int b) {
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            if (getChildCount() > 0) {
                View child = getChildAt(0);
                setMeasuredDimension(child.getMeasuredWidth(), child.getMeasuredHeight());
                return;
            }
            Assertions.assertUnreachable("RecyclableWrapperView measured but no view attached");
        }
    }

    private static class ScrollOffsetTracker {
        private int mLastRequestedPosition;
        private int mOffsetForLastPosition;
        private final ReactListAdapter mReactListAdapter;

        private ScrollOffsetTracker(ReactListAdapter reactListAdapter) {
            this.mReactListAdapter = reactListAdapter;
        }

        public void onHeightChange(int index, int oldHeight, int newHeight) {
            if (index < this.mLastRequestedPosition) {
                this.mOffsetForLastPosition = (this.mOffsetForLastPosition - oldHeight) + newHeight;
            }
        }

        public int getTopOffsetForItem(int index) {
            int sum;
            int startIndex;
            if (this.mLastRequestedPosition != index) {
                if (this.mLastRequestedPosition < index) {
                    if (this.mLastRequestedPosition != -1) {
                        sum = this.mOffsetForLastPosition;
                        startIndex = this.mLastRequestedPosition;
                    } else {
                        sum = 0;
                        startIndex = 0;
                    }
                    for (int i = startIndex; i < index; i++) {
                        sum += ((View) this.mReactListAdapter.mViews.get(i)).getMeasuredHeight();
                    }
                } else if (index < this.mLastRequestedPosition - index) {
                    int sum2 = 0;
                    for (int i2 = 0; i2 < index; i2++) {
                        sum2 = sum + ((View) this.mReactListAdapter.mViews.get(i2)).getMeasuredHeight();
                    }
                } else {
                    int sum3 = this.mOffsetForLastPosition;
                    for (int i3 = this.mLastRequestedPosition - 1; i3 >= index; i3--) {
                        sum3 = sum - ((View) this.mReactListAdapter.mViews.get(i3)).getMeasuredHeight();
                    }
                }
                this.mLastRequestedPosition = index;
                this.mOffsetForLastPosition = sum;
            }
            return this.mOffsetForLastPosition;
        }
    }

    public void setSendContentSizeChangeEvents(boolean sendContentSizeChangeEvents) {
        this.mSendContentSizeChangeEvents = sendContentSizeChangeEvents;
    }

    private int calculateAbsoluteOffset() {
        if (getChildCount() <= 0) {
            return 0;
        }
        View recyclerViewChild = getChildAt(0);
        return ((ReactListAdapter) getAdapter()).getTopOffsetForItem(getChildViewHolder(recyclerViewChild).getLayoutPosition()) - recyclerViewChild.getTop();
    }

    /* access modifiers changed from: 0000 */
    public void scrollTo(int scrollX, int scrollY, boolean animated) {
        int deltaY = scrollY - calculateAbsoluteOffset();
        if (animated) {
            smoothScrollBy(0, deltaY);
        } else {
            scrollBy(0, deltaY);
        }
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        ((UIManagerModule) ((ReactContext) getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(ScrollEvent.obtain(getId(), ScrollEventType.SCROLL, 0, calculateAbsoluteOffset(), getWidth(), ((ReactListAdapter) getAdapter()).getTotalChildrenHeight(), getWidth(), getHeight()));
    }

    /* access modifiers changed from: private */
    public void onTotalChildrenHeightChange(int newTotalChildrenHeight) {
        if (this.mSendContentSizeChangeEvents) {
            ((UIManagerModule) ((ReactContext) getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(new ContentSizeChangeEvent(getId(), getWidth(), newTotalChildrenHeight));
        }
    }

    public RecyclerViewBackedScrollView(Context context) {
        super(context);
        setHasFixedSize(true);
        setItemAnimator(new NotAnimatedItemAnimator());
        setLayoutManager(new LinearLayoutManager(context));
        setAdapter(new ReactListAdapter(this));
    }

    /* access modifiers changed from: 0000 */
    public void addViewToAdapter(View child, int index) {
        ((ReactListAdapter) getAdapter()).addView(child, index);
    }

    /* access modifiers changed from: 0000 */
    public void removeViewFromAdapter(int index) {
        ((ReactListAdapter) getAdapter()).removeViewAt(index);
    }

    /* access modifiers changed from: 0000 */
    public View getChildAtFromAdapter(int index) {
        return ((ReactListAdapter) getAdapter()).getView(index);
    }

    /* access modifiers changed from: 0000 */
    public int getChildCountFromAdapter() {
        return getAdapter().getItemCount();
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!super.onInterceptTouchEvent(ev)) {
            return false;
        }
        NativeGestureUtil.notifyNativeGestureStarted(this, ev);
        return true;
    }
}
