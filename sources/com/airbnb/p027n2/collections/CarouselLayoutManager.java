package com.airbnb.p027n2.collections;

import android.content.Context;
import android.graphics.PointF;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.LinearSmoothScroller;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.OnScrollListener;
import android.support.p002v7.widget.RecyclerView.State;
import android.view.View;
import android.view.ViewConfiguration;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.collections.CarouselLayoutManager */
public class CarouselLayoutManager extends LinearLayoutManager {
    /* access modifiers changed from: private */
    public int currentIdlePosition;
    private boolean isSingleScroll;
    /* access modifiers changed from: private */
    public OnSnapToPositionListener onSnapToPositionListener;
    private final float physicalCoefficient;
    /* access modifiers changed from: private */
    public int positionAtStartOfDrag;

    /* renamed from: com.airbnb.n2.collections.CarouselLayoutManager$OnSnapToPositionListener */
    public interface OnSnapToPositionListener {
        void onSnappedToPosition(int i, boolean z);
    }

    public CarouselLayoutManager(Context context, RecyclerView recyclerView) {
        super(context, 0, false);
        this.physicalCoefficient = computeDeceleration(context, 30.0f);
        recyclerView.addOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView1, int newState) {
                super.onScrollStateChanged(recyclerView1, newState);
                int closestPosition = CarouselLayoutManager.this.getClosestPosition();
                if (newState == 0 && CarouselLayoutManager.this.currentIdlePosition != closestPosition) {
                    if (CarouselLayoutManager.this.onSnapToPositionListener != null) {
                        CarouselLayoutManager.this.onSnapToPositionListener.onSnappedToPosition(closestPosition, CarouselLayoutManager.this.currentIdlePosition > closestPosition);
                    }
                    CarouselLayoutManager.this.currentIdlePosition = closestPosition;
                }
                if (newState == 1) {
                    CarouselLayoutManager.this.positionAtStartOfDrag = closestPosition;
                }
            }

            public void onScrolled(RecyclerView recyclerView1, int dx, int dy) {
                super.onScrolled(recyclerView1, dx, dy);
            }
        });
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, State state, int position) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {
            /* access modifiers changed from: protected */
            public int getHorizontalSnapPreference() {
                return -1;
            }

            /* access modifiers changed from: protected */
            public int getVerticalSnapPreference() {
                return -1;
            }

            public PointF computeScrollVectorForPosition(int targetPosition) {
                return CarouselLayoutManager.this.computeScrollVectorForPosition(targetPosition);
            }

            /* access modifiers changed from: protected */
            public int calculateTimeForDeceleration(int dx) {
                return (int) (((float) super.calculateTimeForDeceleration(dx)) * 2.0f);
            }
        };
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
        this.positionAtStartOfDrag = position;
    }

    public void scrollToPosition(int position) {
        super.scrollToPosition(position);
        this.positionAtStartOfDrag = position;
        this.currentIdlePosition = position;
    }

    public void scrollToPositionWithOffset(int position, int offset) {
        super.scrollToPositionWithOffset(position, offset);
        this.positionAtStartOfDrag = position;
        this.currentIdlePosition = position;
    }

    public void setIsSingleScroll(boolean isSingleScroll2) {
        this.isSingleScroll = isSingleScroll2;
    }

    /* access modifiers changed from: protected */
    public int getExtraLayoutSpace(State state) {
        return (getWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private View getMostVisibleChild() {
        View mostVisibleChild = null;
        float mostVisibleChildPercent = 0.0f;
        for (int i = getChildCount() - 1; i >= 0; i--) {
            View child = getChildAt(i);
            float amountVisible = calculateAmountVisible(child);
            if (amountVisible >= mostVisibleChildPercent) {
                mostVisibleChildPercent = amountVisible;
                mostVisibleChild = child;
            }
        }
        return mostVisibleChild;
    }

    private float calculateAmountVisible(View child) {
        int left = child.getLeft();
        int right = child.getRight();
        int width = child.getWidth();
        if (left < 0) {
            return ((float) right) / ((float) width);
        }
        if (right > getWidth()) {
            return ((float) (getWidth() - left)) / ((float) width);
        }
        return 1.0f;
    }

    public int getPositionForVelocity(int velocityX) {
        int positionsToScroll;
        if (getItemCount() == 0) {
            return -1;
        }
        if (this.isSingleScroll) {
            return velocityX > 0 ? Math.min(this.positionAtStartOfDrag + 1, getItemCount() - 1) : Math.max(this.positionAtStartOfDrag - 1, 0);
        }
        double distance = getSplineFlingDistance((float) velocityX) * ((double) Math.signum((float) velocityX));
        View mostVisibleChild = getMostVisibleChild();
        if (mostVisibleChild == null) {
            return 0;
        }
        double positionsToScrollRaw = distance / ((double) mostVisibleChild.getWidth());
        double positionsToScrollRawAbs = Math.abs(positionsToScrollRaw);
        if (positionsToScrollRawAbs <= 0.15000000596046448d) {
            positionsToScroll = 0;
        } else if (positionsToScrollRawAbs < 2.5d) {
            positionsToScroll = 1;
        } else if (positionsToScrollRawAbs < 4.0d) {
            positionsToScroll = 2;
        } else {
            positionsToScroll = (int) Math.ceil(positionsToScrollRawAbs - 2.0d);
        }
        return ViewLibUtils.clamp(this.positionAtStartOfDrag + Math.round((float) ((int) (((double) positionsToScroll) * Math.signum(positionsToScrollRaw)))), 0, getItemCount() - 1);
    }

    public int getClosestPosition() {
        if (getChildCount() == 0) {
            return 0;
        }
        View child = getMostVisibleChild();
        if (child == null) {
            return 0;
        }
        int childPosition = getPosition(child);
        if (calculateAmountVisible(child) > 0.8f) {
            return childPosition;
        }
        if (child.getLeft() < 0) {
            return Math.max(childPosition + 1, 0);
        }
        return Math.min(childPosition - 1, getItemCount() - 1);
    }

    public int getPositionAtStartOfDrag() {
        return this.positionAtStartOfDrag;
    }

    private static float computeDeceleration(Context context, float friction) {
        return 91.88831f * context.getResources().getDisplayMetrics().density * 160.0f * friction;
    }

    private double getSplineDeceleration(float velocity) {
        return Math.log((double) ((0.35f * Math.abs(velocity)) / (ViewConfiguration.getScrollFriction() * this.physicalCoefficient)));
    }

    private double getSplineFlingDistance(float velocity) {
        return ((double) (ViewConfiguration.getScrollFriction() * this.physicalCoefficient)) * Math.exp(1.7352941743642858d * getSplineDeceleration(velocity));
    }

    public void setOnSnapToPositionListener(OnSnapToPositionListener onSnapToPositionListener2) {
        this.onSnapToPositionListener = onSnapToPositionListener2;
    }
}
