package com.airbnb.p027n2.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.p000v4.view.ViewPager;
import android.support.p000v4.view.ViewPager.OnPageChangeListener;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.OnScrollListener;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.DecelerateInterpolator;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.InfiniteDotIndicator */
public class InfiniteDotIndicator extends View implements OnPageChangeListener {
    private static final DecelerateInterpolator DOT_RADIUS_INTERPOLATOR = new DecelerateInterpolator(0.8f);
    private int activeDotColor;
    private final Paint activePaint = new Paint();
    private int dotSeparationDistancePx;
    private int inactiveDotColor;
    private final Paint inactivePaint = new Paint();
    private InternalScrollListener internalScrollListener;
    private int largeDotCount;
    private int largeRadiusPx;
    /* access modifiers changed from: private */
    public float offsetPercent;
    /* access modifiers changed from: private */
    public RecyclerView recyclerView;
    /* access modifiers changed from: private */
    public int selectedPosition;
    private ViewPager viewPager;

    /* renamed from: com.airbnb.n2.components.InfiniteDotIndicator$InternalScrollListener */
    private class InternalScrollListener extends OnScrollListener {
        private int intermediateSelectedPosition;
        private View previousMostVisibleChild;

        private InternalScrollListener() {
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            View view = getMostVisibleChild();
            setIntermediatePosition(view);
            InfiniteDotIndicator.this.offsetPercent = ((float) view.getLeft()) / ((float) view.getMeasuredWidth());
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            if (this.previousMostVisibleChild != layoutManager.findViewByPosition(dx >= 0 ? layoutManager.findLastVisibleItemPosition() : layoutManager.findFirstVisibleItemPosition())) {
                InfiniteDotIndicator.this.selectedPosition = this.intermediateSelectedPosition;
            }
            this.previousMostVisibleChild = view;
            InfiniteDotIndicator.this.invalidate();
        }

        private View getMostVisibleChild() {
            View mostVisibleChild = null;
            float mostVisibleChildPercent = 0.0f;
            for (int i = InfiniteDotIndicator.this.recyclerView.getLayoutManager().getChildCount() - 1; i >= 0; i--) {
                View child = InfiniteDotIndicator.this.recyclerView.getLayoutManager().getChildAt(i);
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
            if (right > InfiniteDotIndicator.this.getWidth()) {
                return ((float) (InfiniteDotIndicator.this.getWidth() - left)) / ((float) width);
            }
            return 1.0f;
        }

        private void setIntermediatePosition(View mostVisibleChild) {
            ViewHolder mostVisibleChildViewHolder = InfiniteDotIndicator.this.recyclerView.findContainingViewHolder(mostVisibleChild);
            if (mostVisibleChildViewHolder != null) {
                this.intermediateSelectedPosition = mostVisibleChildViewHolder.getAdapterPosition();
            }
        }
    }

    public InfiniteDotIndicator(Context context) {
        super(context);
        init(null);
    }

    public InfiniteDotIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public InfiniteDotIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        Paris.style(this).apply(attrs);
        this.activePaint.setStyle(Style.FILL);
        this.activePaint.setColor(this.activeDotColor);
        this.inactivePaint.setStyle(Style.FILL);
        this.inactivePaint.setColor(this.inactiveDotColor);
        this.largeRadiusPx = ViewLibUtils.dpToPx(getContext(), 4.0f);
        this.dotSeparationDistancePx = ViewLibUtils.dpToPx(getContext(), 16.0f);
    }

    public void setActiveDotColor(int activeDotColor2) {
        this.activeDotColor = activeDotColor2;
        this.activePaint.setColor(activeDotColor2);
        invalidate();
    }

    public void setInactiveDotColor(int inactiveDotColor2) {
        this.inactiveDotColor = inactiveDotColor2;
        this.inactivePaint.setColor(inactiveDotColor2);
        invalidate();
    }

    public void setLargeDotCount(int largeDotCount2) {
        if (this.largeDotCount != largeDotCount2) {
            this.largeDotCount = largeDotCount2;
            requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int viewWidth = getWidth();
        int yCoordinate = getDotYCoordinate();
        int count = getItemCount();
        for (int i = 0; i < count; i++) {
            int xCoordinate = getDotXCoordinate(i, this.offsetPercent);
            canvas.drawCircle((float) ((viewWidth / 2) + xCoordinate), (float) yCoordinate, getRadius(xCoordinate), getPaint(i));
        }
    }

    private int getDotXCoordinate(int listPosition, float offsetPercent2) {
        return ((listPosition - this.selectedPosition) * this.dotSeparationDistancePx) + ((int) (((float) this.dotSeparationDistancePx) * offsetPercent2));
    }

    private int getDotYCoordinate() {
        return this.largeRadiusPx;
    }

    private float getRadius(int xCoordinate) {
        int xAbs = Math.abs(xCoordinate);
        float largeDotThreshold = (((float) this.largeDotCount) / 2.0f) * ((float) this.dotSeparationDistancePx);
        if (((float) xAbs) <= largeDotThreshold) {
            return (float) this.largeRadiusPx;
        }
        return DOT_RADIUS_INTERPOLATOR.getInterpolation(1.0f - ((((float) xAbs) - largeDotThreshold) / ((((float) getCalculatedWidth()) / 2.0f) - largeDotThreshold))) * ((float) this.largeRadiusPx);
    }

    private Paint getPaint(int listPosition) {
        if (listPosition == this.selectedPosition) {
            return this.activePaint;
        }
        return this.inactivePaint;
    }

    private int getCalculatedWidth() {
        return (((this.largeDotCount + 4) - 1) * this.dotSeparationDistancePx) + (this.largeRadiusPx * 2);
    }

    public int getItemCount() {
        if (this.viewPager != null) {
            return this.viewPager.getAdapter().getCount();
        }
        if (this.recyclerView != null) {
            return this.recyclerView.getAdapter().getItemCount();
        }
        return 0;
    }

    public void setRecyclerView(RecyclerView recyclerView2) {
        if (this.viewPager != null) {
            this.viewPager.removeOnPageChangeListener(this);
            this.viewPager = null;
        }
        if (this.recyclerView != null) {
            this.recyclerView.removeOnScrollListener(this.internalScrollListener);
        }
        this.recyclerView = recyclerView2;
        this.internalScrollListener = new InternalScrollListener();
        this.recyclerView.addOnScrollListener(this.internalScrollListener);
    }

    public void setViewPager(ViewPager viewPager2) {
        if (this.recyclerView != null) {
            this.recyclerView.removeOnScrollListener(this.internalScrollListener);
            this.recyclerView = null;
        }
        if (this.viewPager != null) {
            this.viewPager.removeOnPageChangeListener(this);
        }
        this.viewPager = viewPager2;
        this.viewPager.addOnPageChangeListener(this);
        this.selectedPosition = viewPager2.getCurrentItem();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), this.largeRadiusPx * 2);
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        this.offsetPercent = -1.0f * positionOffset;
        invalidate();
    }

    public void onPageSelected(int position) {
        this.selectedPosition = position;
        invalidate();
    }

    public void onPageScrollStateChanged(int state) {
    }

    public static void mock(InfiniteDotIndicator infiniteDotIndicator) {
    }
}
