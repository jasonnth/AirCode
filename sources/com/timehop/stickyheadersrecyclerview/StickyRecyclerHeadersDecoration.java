package com.timehop.stickyheadersrecyclerview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.p000v4.util.LongSparseArray;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.ItemDecoration;
import android.support.p002v7.widget.RecyclerView.LayoutParams;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.util.SparseArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;

public class StickyRecyclerHeadersDecoration extends ItemDecoration {
    private final StickyRecyclerHeadersAdapter mAdapter;
    private final SparseArray<Rect> mHeaderRects;
    private final LongSparseArray<ViewHolder> mHeaderViewHolders;
    private final LongSparseArray<State> mHeaderViewStates;
    private final LongSparseArray<View> mHeaderViews;

    public enum State {
        Stacked,
        Inline
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, android.support.p002v7.widget.RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int orientation = getOrientation(parent);
        int itemPosition = parent.getChildPosition(view);
        if (hasNewHeader(itemPosition)) {
            View header = getHeaderView(parent, itemPosition);
            if (orientation == 1) {
                outRect.top = header.getHeight();
            } else {
                outRect.left = header.getWidth();
            }
        }
    }

    public void onDrawOver(Canvas canvas, RecyclerView parent, android.support.p002v7.widget.RecyclerView.State state) {
        super.onDrawOver(canvas, parent, state);
        int orientation = getOrientation(parent);
        this.mHeaderRects.clear();
        if (parent.getChildCount() > 0 && this.mAdapter.getItemCount() > 0) {
            View firstView = parent.getChildAt(0);
            int firstPosition = parent.getChildPosition(firstView);
            long firstHeaderId = this.mAdapter.getHeaderId(firstPosition);
            if (firstHeaderId >= 0) {
                View firstHeader = getHeaderView(parent, firstPosition);
                View nextView = getNextView(parent);
                int leftOffset = firstView.getLeft() - firstHeader.getWidth();
                int topOffset = firstView.getTop() - firstHeader.getHeight();
                if (orientation == 1) {
                    if (topOffset == 0) {
                        setHeaderStateAndNotify(firstHeaderId, firstPosition, State.Inline);
                    } else if (topOffset < 0) {
                        setHeaderStateAndNotify(firstHeaderId, firstPosition, State.Stacked);
                    }
                } else if (orientation == 0) {
                    if (leftOffset == 0) {
                        setHeaderStateAndNotify(firstHeaderId, firstPosition, State.Inline);
                    } else if (leftOffset < 0) {
                        setHeaderStateAndNotify(firstHeaderId, firstPosition, State.Stacked);
                    }
                }
                int translationX = Math.max(leftOffset, 0);
                int translationY = Math.max(topOffset, 0);
                int nextPosition = parent.getChildPosition(nextView);
                if (nextPosition > 0 && hasNewHeader(nextPosition)) {
                    View secondHeader = getHeaderView(parent, nextPosition);
                    long secondHeaderId = this.mAdapter.getHeaderId(nextPosition);
                    if (orientation == 1 && (nextView.getTop() - secondHeader.getHeight()) - firstHeader.getHeight() < 0) {
                        translationY += (nextView.getTop() - secondHeader.getHeight()) - firstHeader.getHeight();
                        setHeaderStateAndNotify(firstHeaderId, firstPosition, State.Inline);
                        setHeaderStateAndNotify(secondHeaderId, nextPosition, State.Inline);
                    } else if (orientation == 0 && (nextView.getLeft() - secondHeader.getWidth()) - firstHeader.getWidth() < 0) {
                        translationX += (nextView.getLeft() - secondHeader.getWidth()) - firstHeader.getWidth();
                        setHeaderStateAndNotify(firstHeaderId, firstPosition, State.Inline);
                        setHeaderStateAndNotify(secondHeaderId, nextPosition, State.Inline);
                    }
                }
                canvas.save();
                canvas.translate((float) translationX, (float) translationY);
                firstHeader.draw(canvas);
                canvas.restore();
                SparseArray<Rect> sparseArray = this.mHeaderRects;
                Rect rect = new Rect(translationX, translationY, firstHeader.getWidth() + translationX, firstHeader.getHeight() + translationY);
                sparseArray.put(firstPosition, rect);
            }
            for (int i = 1; i < parent.getChildCount(); i++) {
                int position = parent.getChildPosition(parent.getChildAt(i));
                if (hasNewHeader(position)) {
                    int translationX2 = 0;
                    int translationY2 = 0;
                    View header = getHeaderView(parent, position);
                    if (orientation == 1) {
                        translationY2 = parent.getChildAt(i).getTop() - header.getHeight();
                    } else {
                        translationX2 = parent.getChildAt(i).getLeft() - header.getWidth();
                    }
                    canvas.save();
                    canvas.translate((float) translationX2, (float) translationY2);
                    header.draw(canvas);
                    canvas.restore();
                    SparseArray<Rect> sparseArray2 = this.mHeaderRects;
                    Rect rect2 = new Rect(translationX2, translationY2, header.getWidth() + translationX2, header.getHeight() + translationY2);
                    sparseArray2.put(position, rect2);
                }
            }
        }
    }

    private void setHeaderStateAndNotify(long headerId, int position, State state) {
        if (this.mHeaderViewStates.get(headerId) != state) {
            this.mHeaderViewStates.put(headerId, state);
            this.mAdapter.onStickyHeaderStateChange((ViewHolder) this.mHeaderViewHolders.get(headerId), state, position);
        }
    }

    private View getNextView(RecyclerView parent) {
        View firstHeader = getHeaderView(parent, parent.getChildPosition(parent.getChildAt(0)));
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
            if (getOrientation(parent) == 1) {
                if (child.getTop() - layoutParams.topMargin > firstHeader.getHeight()) {
                    return child;
                }
            } else if (child.getLeft() - layoutParams.leftMargin > firstHeader.getWidth()) {
                return child;
            }
        }
        return null;
    }

    private int getOrientation(RecyclerView parent) {
        if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) parent.getLayoutManager()).getOrientation();
        }
        throw new IllegalStateException("StickyListHeadersDecoration can only be used with a LinearLayoutManager.");
    }

    public View getHeaderView(RecyclerView parent, int position) {
        int widthSpec;
        int heightSpec;
        long headerId = this.mAdapter.getHeaderId(position);
        View header = (View) this.mHeaderViews.get(headerId);
        if (header == null) {
            ViewHolder viewHolder = this.mAdapter.onCreateHeaderViewHolder(parent, position);
            this.mHeaderViewHolders.put(headerId, viewHolder);
            this.mAdapter.onBindHeaderViewHolder(viewHolder, position);
            header = viewHolder.itemView;
            if (header.getLayoutParams() == null) {
                header.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            if (getOrientation(parent) == 1) {
                widthSpec = MeasureSpec.makeMeasureSpec(parent.getWidth(), 1073741824);
                heightSpec = MeasureSpec.makeMeasureSpec(parent.getHeight(), 0);
            } else {
                widthSpec = MeasureSpec.makeMeasureSpec(parent.getWidth(), 0);
                heightSpec = MeasureSpec.makeMeasureSpec(parent.getHeight(), 1073741824);
            }
            header.measure(ViewGroup.getChildMeasureSpec(widthSpec, parent.getPaddingLeft() + parent.getPaddingRight(), header.getLayoutParams().width), ViewGroup.getChildMeasureSpec(heightSpec, parent.getPaddingTop() + parent.getPaddingBottom(), header.getLayoutParams().height));
            header.layout(0, 0, header.getMeasuredWidth(), header.getMeasuredHeight());
            this.mHeaderViews.put(headerId, header);
            this.mHeaderViewStates.put(headerId, State.Inline);
        }
        return header;
    }

    private boolean hasNewHeader(int position) {
        if (getFirstHeaderPosition() == position) {
            return true;
        }
        if (this.mAdapter.getHeaderId(position) < 0) {
            return false;
        }
        if (position <= 0 || position >= this.mAdapter.getItemCount()) {
            return false;
        }
        if (this.mAdapter.getHeaderId(position) == this.mAdapter.getHeaderId(position - 1)) {
            return false;
        }
        return true;
    }

    private int getFirstHeaderPosition() {
        for (int i = 0; i < this.mAdapter.getItemCount(); i++) {
            if (this.mAdapter.getHeaderId(i) >= 0) {
                return i;
            }
        }
        return -1;
    }
}
