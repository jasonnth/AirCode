package com.daimajia.swipe;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.p000v4.view.GravityCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.widget.ViewDragHelper;
import android.support.p000v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SwipeLayout extends FrameLayout {
    private static final DragEdge DefaultDragEdge = DragEdge.Right;
    OnClickListener clickListener;
    private boolean enableViewBoundCache;
    private GestureDetector gestureDetector;
    private Rect hitSurfaceRect;
    OnLongClickListener longClickListener;
    /* access modifiers changed from: private */
    public boolean mClickToClose;
    /* access modifiers changed from: private */
    public DragEdge mCurrentDragEdge;
    /* access modifiers changed from: private */
    public DoubleClickListener mDoubleClickListener;
    /* access modifiers changed from: private */
    public int mDragDistance;
    private LinkedHashMap<DragEdge, View> mDragEdges;
    private ViewDragHelper mDragHelper;
    private Callback mDragHelperCallback;
    private float[] mEdgeSwipesOffset;
    private int mEventCounter;
    private boolean mIsBeingDragged;
    private List<OnLayout> mOnLayoutListeners;
    private Map<View, ArrayList<OnRevealListener>> mRevealListeners;
    private Map<View, Boolean> mShowEntirely;
    /* access modifiers changed from: private */
    public ShowMode mShowMode;
    private List<SwipeDenier> mSwipeDeniers;
    private boolean mSwipeEnabled;
    /* access modifiers changed from: private */
    public List<SwipeListener> mSwipeListeners;
    private boolean[] mSwipesEnabled;
    private int mTouchSlop;
    private Map<View, Rect> mViewBoundCache;
    private float mWillOpenPercentAfterClose;
    private float mWillOpenPercentAfterOpen;

    /* renamed from: sX */
    private float f1371sX;

    /* renamed from: sY */
    private float f1372sY;

    public interface DoubleClickListener {
        void onDoubleClick(SwipeLayout swipeLayout, boolean z);
    }

    public enum DragEdge {
        Left,
        Top,
        Right,
        Bottom
    }

    public interface OnLayout {
        void onLayout(SwipeLayout swipeLayout);
    }

    public interface OnRevealListener {
        void onReveal(View view, DragEdge dragEdge, float f, int i);
    }

    public enum ShowMode {
        LayDown,
        PullOut
    }

    public enum Status {
        Middle,
        Open,
        Close
    }

    public interface SwipeDenier {
        boolean shouldDenySwipe(MotionEvent motionEvent);
    }

    class SwipeDetector extends SimpleOnGestureListener {
        SwipeDetector() {
        }

        public boolean onSingleTapUp(MotionEvent e) {
            if (SwipeLayout.this.mClickToClose && SwipeLayout.this.isTouchOnSurface(e)) {
                SwipeLayout.this.close();
            }
            return super.onSingleTapUp(e);
        }

        public boolean onDoubleTap(MotionEvent e) {
            View target;
            if (SwipeLayout.this.mDoubleClickListener != null) {
                View bottom = SwipeLayout.this.getCurrentBottomView();
                View surface = SwipeLayout.this.getSurfaceView();
                if (bottom == null || e.getX() <= ((float) bottom.getLeft()) || e.getX() >= ((float) bottom.getRight()) || e.getY() <= ((float) bottom.getTop()) || e.getY() >= ((float) bottom.getBottom())) {
                    target = surface;
                } else {
                    target = bottom;
                }
                SwipeLayout.this.mDoubleClickListener.onDoubleClick(SwipeLayout.this, target == surface);
            }
            return true;
        }
    }

    public interface SwipeListener {
        void onClose(SwipeLayout swipeLayout);

        void onHandRelease(SwipeLayout swipeLayout, float f, float f2);

        void onOpen(SwipeLayout swipeLayout);

        void onStartClose(SwipeLayout swipeLayout);

        void onStartOpen(SwipeLayout swipeLayout);

        void onUpdate(SwipeLayout swipeLayout, int i, int i2);
    }

    public SwipeLayout(Context context) {
        this(context, null);
    }

    public SwipeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mCurrentDragEdge = DefaultDragEdge;
        this.mDragDistance = 0;
        this.mDragEdges = new LinkedHashMap<>();
        this.mEdgeSwipesOffset = new float[4];
        this.mSwipeListeners = new ArrayList();
        this.mSwipeDeniers = new ArrayList();
        this.mRevealListeners = new HashMap();
        this.mShowEntirely = new HashMap();
        this.mViewBoundCache = new HashMap();
        this.mSwipeEnabled = true;
        this.mSwipesEnabled = new boolean[]{true, true, true, true};
        this.mClickToClose = false;
        this.mWillOpenPercentAfterOpen = 0.75f;
        this.mWillOpenPercentAfterClose = 0.25f;
        this.enableViewBoundCache = false;
        this.mDragHelperCallback = new Callback() {
            boolean isCloseBeforeDrag = true;

            public int clampViewPositionHorizontal(View child, int left, int dx) {
                if (child == SwipeLayout.this.getSurfaceView()) {
                    switch (C10104.$SwitchMap$com$daimajia$swipe$SwipeLayout$DragEdge[SwipeLayout.this.mCurrentDragEdge.ordinal()]) {
                        case 1:
                        case 2:
                            return SwipeLayout.this.getPaddingLeft();
                        case 3:
                            if (left < SwipeLayout.this.getPaddingLeft()) {
                                return SwipeLayout.this.getPaddingLeft();
                            }
                            if (left > SwipeLayout.this.getPaddingLeft() + SwipeLayout.this.mDragDistance) {
                                return SwipeLayout.this.getPaddingLeft() + SwipeLayout.this.mDragDistance;
                            }
                            return left;
                        case 4:
                            if (left > SwipeLayout.this.getPaddingLeft()) {
                                return SwipeLayout.this.getPaddingLeft();
                            }
                            if (left < SwipeLayout.this.getPaddingLeft() - SwipeLayout.this.mDragDistance) {
                                return SwipeLayout.this.getPaddingLeft() - SwipeLayout.this.mDragDistance;
                            }
                            return left;
                        default:
                            return left;
                    }
                } else if (SwipeLayout.this.getCurrentBottomView() != child) {
                    return left;
                } else {
                    switch (C10104.$SwitchMap$com$daimajia$swipe$SwipeLayout$DragEdge[SwipeLayout.this.mCurrentDragEdge.ordinal()]) {
                        case 1:
                        case 2:
                            return SwipeLayout.this.getPaddingLeft();
                        case 3:
                            if (SwipeLayout.this.mShowMode != ShowMode.PullOut || left <= SwipeLayout.this.getPaddingLeft()) {
                                return left;
                            }
                            return SwipeLayout.this.getPaddingLeft();
                        case 4:
                            if (SwipeLayout.this.mShowMode != ShowMode.PullOut || left >= SwipeLayout.this.getMeasuredWidth() - SwipeLayout.this.mDragDistance) {
                                return left;
                            }
                            return SwipeLayout.this.getMeasuredWidth() - SwipeLayout.this.mDragDistance;
                        default:
                            return left;
                    }
                }
            }

            public int clampViewPositionVertical(View child, int top, int dy) {
                if (child == SwipeLayout.this.getSurfaceView()) {
                    switch (C10104.$SwitchMap$com$daimajia$swipe$SwipeLayout$DragEdge[SwipeLayout.this.mCurrentDragEdge.ordinal()]) {
                        case 1:
                            if (top < SwipeLayout.this.getPaddingTop()) {
                                return SwipeLayout.this.getPaddingTop();
                            }
                            if (top > SwipeLayout.this.getPaddingTop() + SwipeLayout.this.mDragDistance) {
                                return SwipeLayout.this.getPaddingTop() + SwipeLayout.this.mDragDistance;
                            }
                            return top;
                        case 2:
                            if (top < SwipeLayout.this.getPaddingTop() - SwipeLayout.this.mDragDistance) {
                                return SwipeLayout.this.getPaddingTop() - SwipeLayout.this.mDragDistance;
                            }
                            if (top > SwipeLayout.this.getPaddingTop()) {
                                return SwipeLayout.this.getPaddingTop();
                            }
                            return top;
                        case 3:
                        case 4:
                            return SwipeLayout.this.getPaddingTop();
                        default:
                            return top;
                    }
                } else {
                    View surfaceView = SwipeLayout.this.getSurfaceView();
                    int surfaceViewTop = surfaceView == null ? 0 : surfaceView.getTop();
                    switch (C10104.$SwitchMap$com$daimajia$swipe$SwipeLayout$DragEdge[SwipeLayout.this.mCurrentDragEdge.ordinal()]) {
                        case 1:
                            if (SwipeLayout.this.mShowMode == ShowMode.PullOut) {
                                if (top > SwipeLayout.this.getPaddingTop()) {
                                    return SwipeLayout.this.getPaddingTop();
                                }
                                return top;
                            } else if (surfaceViewTop + dy < SwipeLayout.this.getPaddingTop()) {
                                return SwipeLayout.this.getPaddingTop();
                            } else {
                                if (surfaceViewTop + dy > SwipeLayout.this.getPaddingTop() + SwipeLayout.this.mDragDistance) {
                                    return SwipeLayout.this.getPaddingTop() + SwipeLayout.this.mDragDistance;
                                }
                                return top;
                            }
                        case 2:
                            if (SwipeLayout.this.mShowMode == ShowMode.PullOut) {
                                if (top < SwipeLayout.this.getMeasuredHeight() - SwipeLayout.this.mDragDistance) {
                                    return SwipeLayout.this.getMeasuredHeight() - SwipeLayout.this.mDragDistance;
                                }
                                return top;
                            } else if (surfaceViewTop + dy >= SwipeLayout.this.getPaddingTop()) {
                                return SwipeLayout.this.getPaddingTop();
                            } else {
                                if (surfaceViewTop + dy <= SwipeLayout.this.getPaddingTop() - SwipeLayout.this.mDragDistance) {
                                    return SwipeLayout.this.getPaddingTop() - SwipeLayout.this.mDragDistance;
                                }
                                return top;
                            }
                        case 3:
                        case 4:
                            return SwipeLayout.this.getPaddingTop();
                        default:
                            return top;
                    }
                }
            }

            public boolean tryCaptureView(View child, int pointerId) {
                boolean result;
                boolean z = true;
                if (child == SwipeLayout.this.getSurfaceView() || SwipeLayout.this.getBottomViews().contains(child)) {
                    result = true;
                } else {
                    result = false;
                }
                if (result) {
                    if (SwipeLayout.this.getOpenStatus() != Status.Close) {
                        z = false;
                    }
                    this.isCloseBeforeDrag = z;
                }
                return result;
            }

            public int getViewHorizontalDragRange(View child) {
                return SwipeLayout.this.mDragDistance;
            }

            public int getViewVerticalDragRange(View child) {
                return SwipeLayout.this.mDragDistance;
            }

            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                SwipeLayout.this.processHandRelease(xvel, yvel, this.isCloseBeforeDrag);
                for (SwipeListener l : SwipeLayout.this.mSwipeListeners) {
                    l.onHandRelease(SwipeLayout.this, xvel, yvel);
                }
                SwipeLayout.this.invalidate();
            }

            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                View surfaceView = SwipeLayout.this.getSurfaceView();
                if (surfaceView != null) {
                    View currentBottomView = SwipeLayout.this.getCurrentBottomView();
                    int evLeft = surfaceView.getLeft();
                    int evRight = surfaceView.getRight();
                    int evTop = surfaceView.getTop();
                    int evBottom = surfaceView.getBottom();
                    if (changedView == surfaceView) {
                        if (SwipeLayout.this.mShowMode == ShowMode.PullOut && currentBottomView != null) {
                            if (SwipeLayout.this.mCurrentDragEdge == DragEdge.Left || SwipeLayout.this.mCurrentDragEdge == DragEdge.Right) {
                                currentBottomView.offsetLeftAndRight(dx);
                            } else {
                                currentBottomView.offsetTopAndBottom(dy);
                            }
                        }
                    } else if (SwipeLayout.this.getBottomViews().contains(changedView)) {
                        if (SwipeLayout.this.mShowMode == ShowMode.PullOut) {
                            surfaceView.offsetLeftAndRight(dx);
                            surfaceView.offsetTopAndBottom(dy);
                        } else {
                            Rect rect = SwipeLayout.this.computeBottomLayDown(SwipeLayout.this.mCurrentDragEdge);
                            if (currentBottomView != null) {
                                currentBottomView.layout(rect.left, rect.top, rect.right, rect.bottom);
                            }
                            int newLeft = surfaceView.getLeft() + dx;
                            int newTop = surfaceView.getTop() + dy;
                            if (SwipeLayout.this.mCurrentDragEdge == DragEdge.Left && newLeft < SwipeLayout.this.getPaddingLeft()) {
                                newLeft = SwipeLayout.this.getPaddingLeft();
                            } else if (SwipeLayout.this.mCurrentDragEdge == DragEdge.Right && newLeft > SwipeLayout.this.getPaddingLeft()) {
                                newLeft = SwipeLayout.this.getPaddingLeft();
                            } else if (SwipeLayout.this.mCurrentDragEdge == DragEdge.Top && newTop < SwipeLayout.this.getPaddingTop()) {
                                newTop = SwipeLayout.this.getPaddingTop();
                            } else if (SwipeLayout.this.mCurrentDragEdge == DragEdge.Bottom && newTop > SwipeLayout.this.getPaddingTop()) {
                                newTop = SwipeLayout.this.getPaddingTop();
                            }
                            surfaceView.layout(newLeft, newTop, SwipeLayout.this.getMeasuredWidth() + newLeft, SwipeLayout.this.getMeasuredHeight() + newTop);
                        }
                    }
                    SwipeLayout.this.dispatchRevealEvent(evLeft, evTop, evRight, evBottom);
                    SwipeLayout.this.dispatchSwipeEvent(evLeft, evTop, dx, dy);
                    SwipeLayout.this.invalidate();
                    SwipeLayout.this.captureChildrenBound();
                }
            }
        };
        this.mEventCounter = 0;
        this.f1371sX = -1.0f;
        this.f1372sY = -1.0f;
        this.gestureDetector = new GestureDetector(getContext(), new SwipeDetector());
        this.mDragHelper = ViewDragHelper.create(this, this.mDragHelperCallback);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SwipeLayout);
        int dragEdgeChoices = a.getInt(R.styleable.SwipeLayout_drag_edge, 2);
        this.mEdgeSwipesOffset[DragEdge.Left.ordinal()] = a.getDimension(R.styleable.SwipeLayout_leftEdgeSwipeOffset, 0.0f);
        this.mEdgeSwipesOffset[DragEdge.Right.ordinal()] = a.getDimension(R.styleable.SwipeLayout_rightEdgeSwipeOffset, 0.0f);
        this.mEdgeSwipesOffset[DragEdge.Top.ordinal()] = a.getDimension(R.styleable.SwipeLayout_topEdgeSwipeOffset, 0.0f);
        this.mEdgeSwipesOffset[DragEdge.Bottom.ordinal()] = a.getDimension(R.styleable.SwipeLayout_bottomEdgeSwipeOffset, 0.0f);
        setClickToClose(a.getBoolean(R.styleable.SwipeLayout_clickToClose, this.mClickToClose));
        if ((dragEdgeChoices & 1) == 1) {
            this.mDragEdges.put(DragEdge.Left, null);
        }
        if ((dragEdgeChoices & 4) == 4) {
            this.mDragEdges.put(DragEdge.Top, null);
        }
        if ((dragEdgeChoices & 2) == 2) {
            this.mDragEdges.put(DragEdge.Right, null);
        }
        if ((dragEdgeChoices & 8) == 8) {
            this.mDragEdges.put(DragEdge.Bottom, null);
        }
        this.mShowMode = ShowMode.values()[a.getInt(R.styleable.SwipeLayout_show_mode, ShowMode.PullOut.ordinal())];
        a.recycle();
    }

    public void addSwipeListener(SwipeListener l) {
        this.mSwipeListeners.add(l);
    }

    public void removeAllSwipeListener() {
        this.mSwipeListeners.clear();
    }

    /* access modifiers changed from: private */
    public void captureChildrenBound() {
        View[] views;
        View currentBottomView = getCurrentBottomView();
        if (getOpenStatus() != Status.Close) {
            for (View child : new View[]{getSurfaceView(), currentBottomView}) {
                Rect rect = null;
                if (this.enableViewBoundCache) {
                    this.mViewBoundCache.get(child);
                }
                if (0 == 0) {
                    rect = new Rect();
                    if (this.enableViewBoundCache) {
                        this.mViewBoundCache.put(child, rect);
                    }
                }
                rect.left = child.getLeft();
                rect.top = child.getTop();
                rect.right = child.getRight();
                rect.bottom = child.getBottom();
            }
        } else if (this.enableViewBoundCache) {
            this.mViewBoundCache.remove(currentBottomView);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isViewTotallyFirstShowed(View child, Rect relativePosition, DragEdge edge, int surfaceLeft, int surfaceTop, int surfaceRight, int surfaceBottom) {
        if (((Boolean) this.mShowEntirely.get(child)).booleanValue()) {
            return false;
        }
        int childLeft = relativePosition.left;
        int childRight = relativePosition.right;
        int childTop = relativePosition.top;
        int childBottom = relativePosition.bottom;
        if (getShowMode() == ShowMode.LayDown) {
            if ((edge != DragEdge.Right || surfaceRight > childLeft) && ((edge != DragEdge.Left || surfaceLeft < childRight) && ((edge != DragEdge.Top || surfaceTop < childBottom) && (edge != DragEdge.Bottom || surfaceBottom > childTop)))) {
                return false;
            }
            return true;
        } else if (getShowMode() != ShowMode.PullOut) {
            return false;
        } else {
            if ((edge != DragEdge.Right || childRight > getWidth()) && ((edge != DragEdge.Left || childLeft < getPaddingLeft()) && ((edge != DragEdge.Top || childTop < getPaddingTop()) && (edge != DragEdge.Bottom || childBottom > getHeight())))) {
                return false;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean isViewShowing(View child, Rect relativePosition, DragEdge availableEdge, int surfaceLeft, int surfaceTop, int surfaceRight, int surfaceBottom) {
        int childLeft = relativePosition.left;
        int childRight = relativePosition.right;
        int childTop = relativePosition.top;
        int childBottom = relativePosition.bottom;
        if (getShowMode() == ShowMode.LayDown) {
            switch (availableEdge) {
                case Top:
                    if (surfaceTop >= childTop && surfaceTop < childBottom) {
                        return true;
                    }
                case Bottom:
                    if (surfaceBottom > childTop && surfaceBottom <= childBottom) {
                        return true;
                    }
                case Left:
                    if (surfaceLeft < childRight && surfaceLeft >= childLeft) {
                        return true;
                    }
                case Right:
                    if (surfaceRight > childLeft && surfaceRight <= childRight) {
                        return true;
                    }
            }
        } else if (getShowMode() == ShowMode.PullOut) {
            switch (availableEdge) {
                case Top:
                    if (childTop < getPaddingTop() && childBottom >= getPaddingTop()) {
                        return true;
                    }
                case Bottom:
                    if (childTop < getHeight() && childTop >= getPaddingTop()) {
                        return true;
                    }
                case Left:
                    if (childRight >= getPaddingLeft() && childLeft < getPaddingLeft()) {
                        return true;
                    }
                case Right:
                    if (childLeft <= getWidth() && childRight > getWidth()) {
                        return true;
                    }
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public Rect getRelativePosition(View child) {
        View t = child;
        Rect r = new Rect(t.getLeft(), t.getTop(), 0, 0);
        while (t.getParent() != null && t != getRootView()) {
            t = (View) t.getParent();
            if (t == this) {
                break;
            }
            r.left += t.getLeft();
            r.top += t.getTop();
        }
        r.right = r.left + child.getMeasuredWidth();
        r.bottom = r.top + child.getMeasuredHeight();
        return r;
    }

    /* access modifiers changed from: protected */
    public void dispatchSwipeEvent(int surfaceLeft, int surfaceTop, int dx, int dy) {
        DragEdge edge = getDragEdge();
        boolean open = true;
        if (edge == DragEdge.Left) {
            if (dx < 0) {
                open = false;
            }
        } else if (edge == DragEdge.Right) {
            if (dx > 0) {
                open = false;
            }
        } else if (edge == DragEdge.Top) {
            if (dy < 0) {
                open = false;
            }
        } else if (edge == DragEdge.Bottom && dy > 0) {
            open = false;
        }
        dispatchSwipeEvent(surfaceLeft, surfaceTop, open);
    }

    /* access modifiers changed from: protected */
    public void dispatchSwipeEvent(int surfaceLeft, int surfaceTop, boolean open) {
        safeBottomView();
        Status status = getOpenStatus();
        if (!this.mSwipeListeners.isEmpty()) {
            this.mEventCounter++;
            for (SwipeListener l : this.mSwipeListeners) {
                if (this.mEventCounter == 1) {
                    if (open) {
                        l.onStartOpen(this);
                    } else {
                        l.onStartClose(this);
                    }
                }
                l.onUpdate(this, surfaceLeft - getPaddingLeft(), surfaceTop - getPaddingTop());
            }
            if (status == Status.Close) {
                for (SwipeListener l2 : this.mSwipeListeners) {
                    l2.onClose(this);
                }
                this.mEventCounter = 0;
            }
            if (status == Status.Open) {
                View currentBottomView = getCurrentBottomView();
                if (currentBottomView != null) {
                    currentBottomView.setEnabled(true);
                }
                for (SwipeListener l3 : this.mSwipeListeners) {
                    l3.onOpen(this);
                }
                this.mEventCounter = 0;
            }
        }
    }

    private void safeBottomView() {
        Status status = getOpenStatus();
        List<View> bottoms = getBottomViews();
        if (status == Status.Close) {
            for (View bottom : bottoms) {
                if (!(bottom == null || bottom.getVisibility() == 4)) {
                    bottom.setVisibility(4);
                }
            }
            return;
        }
        View currentBottomView = getCurrentBottomView();
        if (currentBottomView != null && currentBottomView.getVisibility() != 0) {
            currentBottomView.setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchRevealEvent(int surfaceLeft, int surfaceTop, int surfaceRight, int surfaceBottom) {
        if (!this.mRevealListeners.isEmpty()) {
            for (Entry<View, ArrayList<OnRevealListener>> entry : this.mRevealListeners.entrySet()) {
                View child = (View) entry.getKey();
                Rect rect = getRelativePosition(child);
                if (isViewShowing(child, rect, this.mCurrentDragEdge, surfaceLeft, surfaceTop, surfaceRight, surfaceBottom)) {
                    this.mShowEntirely.put(child, Boolean.valueOf(false));
                    int distance = 0;
                    float fraction = 0.0f;
                    if (getShowMode() != ShowMode.LayDown) {
                        if (getShowMode() == ShowMode.PullOut) {
                            switch (this.mCurrentDragEdge) {
                                case Top:
                                    distance = rect.bottom - getPaddingTop();
                                    fraction = ((float) distance) / ((float) child.getHeight());
                                    break;
                                case Bottom:
                                    distance = rect.top - getHeight();
                                    fraction = ((float) distance) / ((float) child.getHeight());
                                    break;
                                case Left:
                                    distance = rect.right - getPaddingLeft();
                                    fraction = ((float) distance) / ((float) child.getWidth());
                                    break;
                                case Right:
                                    distance = rect.left - getWidth();
                                    fraction = ((float) distance) / ((float) child.getWidth());
                                    break;
                            }
                        }
                    } else {
                        switch (this.mCurrentDragEdge) {
                            case Top:
                                distance = rect.top - surfaceTop;
                                fraction = ((float) distance) / ((float) child.getHeight());
                                break;
                            case Bottom:
                                distance = rect.bottom - surfaceBottom;
                                fraction = ((float) distance) / ((float) child.getHeight());
                                break;
                            case Left:
                                distance = rect.left - surfaceLeft;
                                fraction = ((float) distance) / ((float) child.getWidth());
                                break;
                            case Right:
                                distance = rect.right - surfaceRight;
                                fraction = ((float) distance) / ((float) child.getWidth());
                                break;
                        }
                    }
                    Iterator it = ((ArrayList) entry.getValue()).iterator();
                    while (it.hasNext()) {
                        ((OnRevealListener) it.next()).onReveal(child, this.mCurrentDragEdge, Math.abs(fraction), distance);
                        if (Math.abs(fraction) == 1.0f) {
                            this.mShowEntirely.put(child, Boolean.valueOf(true));
                        }
                    }
                }
                if (isViewTotallyFirstShowed(child, rect, this.mCurrentDragEdge, surfaceLeft, surfaceTop, surfaceRight, surfaceBottom)) {
                    this.mShowEntirely.put(child, Boolean.valueOf(true));
                    Iterator it2 = ((ArrayList) entry.getValue()).iterator();
                    while (it2.hasNext()) {
                        OnRevealListener l = (OnRevealListener) it2.next();
                        if (this.mCurrentDragEdge == DragEdge.Left || this.mCurrentDragEdge == DragEdge.Right) {
                            l.onReveal(child, this.mCurrentDragEdge, 1.0f, child.getWidth());
                        } else {
                            l.onReveal(child, this.mCurrentDragEdge, 1.0f, child.getHeight());
                        }
                    }
                }
            }
        }
    }

    public void computeScroll() {
        super.computeScroll();
        if (this.mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void clearDragEdge() {
        this.mDragEdges.clear();
    }

    public void addView(View child, int index, LayoutParams params) {
        if (child != null) {
            int gravity = 0;
            try {
                gravity = ((Integer) params.getClass().getField("gravity").get(params)).intValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (gravity <= 0) {
                Iterator it = this.mDragEdges.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Entry<DragEdge, View> entry = (Entry) it.next();
                    if (entry.getValue() == null) {
                        this.mDragEdges.put(entry.getKey(), child);
                        break;
                    }
                }
            } else {
                int gravity2 = GravityCompat.getAbsoluteGravity(gravity, ViewCompat.getLayoutDirection(this));
                if ((gravity2 & 3) == 3) {
                    this.mDragEdges.put(DragEdge.Left, child);
                }
                if ((gravity2 & 5) == 5) {
                    this.mDragEdges.put(DragEdge.Right, child);
                }
                if ((gravity2 & 48) == 48) {
                    this.mDragEdges.put(DragEdge.Top, child);
                }
                if ((gravity2 & 80) == 80) {
                    this.mDragEdges.put(DragEdge.Bottom, child);
                }
            }
            if (child.getParent() != this) {
                super.addView(child, index, params);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        updateBottomViews();
        if (this.mOnLayoutListeners != null) {
            for (int i = 0; i < this.mOnLayoutListeners.size(); i++) {
                ((OnLayout) this.mOnLayoutListeners.get(i)).onLayout(this);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void layoutPullOut() {
        View surfaceView = getSurfaceView();
        Rect surfaceRect = null;
        if (this.enableViewBoundCache) {
            surfaceRect = (Rect) this.mViewBoundCache.get(surfaceView);
        }
        if (surfaceRect == null) {
            surfaceRect = computeSurfaceLayoutArea(false);
        }
        if (surfaceView != null) {
            surfaceView.layout(surfaceRect.left, surfaceRect.top, surfaceRect.right, surfaceRect.bottom);
            bringChildToFront(surfaceView);
        }
        View currentBottomView = getCurrentBottomView();
        Rect bottomViewRect = null;
        if (this.enableViewBoundCache) {
            bottomViewRect = (Rect) this.mViewBoundCache.get(currentBottomView);
        }
        if (bottomViewRect == null) {
            bottomViewRect = computeBottomLayoutAreaViaSurface(ShowMode.PullOut, surfaceRect);
        }
        if (currentBottomView != null) {
            currentBottomView.layout(bottomViewRect.left, bottomViewRect.top, bottomViewRect.right, bottomViewRect.bottom);
        }
    }

    /* access modifiers changed from: 0000 */
    public void layoutLayDown() {
        View surfaceView = getSurfaceView();
        Rect surfaceRect = null;
        if (this.enableViewBoundCache) {
            surfaceRect = (Rect) this.mViewBoundCache.get(surfaceView);
        }
        if (surfaceRect == null) {
            surfaceRect = computeSurfaceLayoutArea(false);
        }
        if (surfaceView != null) {
            surfaceView.layout(surfaceRect.left, surfaceRect.top, surfaceRect.right, surfaceRect.bottom);
            bringChildToFront(surfaceView);
        }
        View currentBottomView = getCurrentBottomView();
        Rect bottomViewRect = null;
        if (this.enableViewBoundCache) {
            bottomViewRect = (Rect) this.mViewBoundCache.get(currentBottomView);
        }
        if (bottomViewRect == null) {
            bottomViewRect = computeBottomLayoutAreaViaSurface(ShowMode.LayDown, surfaceRect);
        }
        if (currentBottomView != null) {
            currentBottomView.layout(bottomViewRect.left, bottomViewRect.top, bottomViewRect.right, bottomViewRect.bottom);
        }
    }

    private void checkCanDrag(MotionEvent ev) {
        boolean z;
        DragEdge dragEdge;
        if (!this.mIsBeingDragged) {
            if (getOpenStatus() == Status.Middle) {
                this.mIsBeingDragged = true;
                return;
            }
            Status status = getOpenStatus();
            float distanceX = ev.getRawX() - this.f1371sX;
            float distanceY = ev.getRawY() - this.f1372sY;
            float angle = (float) Math.toDegrees(Math.atan((double) Math.abs(distanceY / distanceX)));
            if (getOpenStatus() == Status.Close) {
                if (angle < 45.0f) {
                    if (distanceX > 0.0f && isLeftSwipeEnabled()) {
                        dragEdge = DragEdge.Left;
                    } else if (distanceX < 0.0f && isRightSwipeEnabled()) {
                        dragEdge = DragEdge.Right;
                    } else {
                        return;
                    }
                } else if (distanceY > 0.0f && isTopSwipeEnabled()) {
                    dragEdge = DragEdge.Top;
                } else if (distanceY < 0.0f && isBottomSwipeEnabled()) {
                    dragEdge = DragEdge.Bottom;
                } else {
                    return;
                }
                setCurrentDragEdge(dragEdge);
            }
            boolean doNothing = false;
            if (this.mCurrentDragEdge == DragEdge.Right) {
                boolean suitable = ((status == Status.Open && (distanceX > ((float) this.mTouchSlop) ? 1 : (distanceX == ((float) this.mTouchSlop) ? 0 : -1)) > 0) || (status == Status.Close && (distanceX > ((float) (-this.mTouchSlop)) ? 1 : (distanceX == ((float) (-this.mTouchSlop)) ? 0 : -1)) < 0)) || status == Status.Middle;
                if (angle > 30.0f || !suitable) {
                    doNothing = true;
                }
            }
            if (this.mCurrentDragEdge == DragEdge.Left) {
                boolean suitable2 = ((status == Status.Open && (distanceX > ((float) (-this.mTouchSlop)) ? 1 : (distanceX == ((float) (-this.mTouchSlop)) ? 0 : -1)) < 0) || (status == Status.Close && (distanceX > ((float) this.mTouchSlop) ? 1 : (distanceX == ((float) this.mTouchSlop) ? 0 : -1)) > 0)) || status == Status.Middle;
                if (angle > 30.0f || !suitable2) {
                    doNothing = true;
                }
            }
            if (this.mCurrentDragEdge == DragEdge.Top) {
                boolean suitable3 = ((status == Status.Open && (distanceY > ((float) (-this.mTouchSlop)) ? 1 : (distanceY == ((float) (-this.mTouchSlop)) ? 0 : -1)) < 0) || (status == Status.Close && (distanceY > ((float) this.mTouchSlop) ? 1 : (distanceY == ((float) this.mTouchSlop) ? 0 : -1)) > 0)) || status == Status.Middle;
                if (angle < 60.0f || !suitable3) {
                    doNothing = true;
                }
            }
            if (this.mCurrentDragEdge == DragEdge.Bottom) {
                boolean suitable4 = ((status == Status.Open && (distanceY > ((float) this.mTouchSlop) ? 1 : (distanceY == ((float) this.mTouchSlop) ? 0 : -1)) > 0) || (status == Status.Close && (distanceY > ((float) (-this.mTouchSlop)) ? 1 : (distanceY == ((float) (-this.mTouchSlop)) ? 0 : -1)) < 0)) || status == Status.Middle;
                if (angle < 60.0f || !suitable4) {
                    doNothing = true;
                }
            }
            if (!doNothing) {
                z = true;
            } else {
                z = false;
            }
            this.mIsBeingDragged = z;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!isSwipeEnabled()) {
            return false;
        }
        if (this.mClickToClose && getOpenStatus() == Status.Open && isTouchOnSurface(ev)) {
            return true;
        }
        for (SwipeDenier denier : this.mSwipeDeniers) {
            if (denier != null && denier.shouldDenySwipe(ev)) {
                return false;
            }
        }
        switch (ev.getAction()) {
            case 0:
                this.mDragHelper.processTouchEvent(ev);
                this.mIsBeingDragged = false;
                this.f1371sX = ev.getRawX();
                this.f1372sY = ev.getRawY();
                if (getOpenStatus() == Status.Middle) {
                    this.mIsBeingDragged = true;
                    break;
                }
                break;
            case 1:
            case 3:
                this.mIsBeingDragged = false;
                this.mDragHelper.processTouchEvent(ev);
                break;
            case 2:
                boolean beforeCheck = this.mIsBeingDragged;
                checkCanDrag(ev);
                if (this.mIsBeingDragged) {
                    ViewParent parent = getParent();
                    if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    }
                }
                if (!beforeCheck && this.mIsBeingDragged) {
                    return false;
                }
            default:
                this.mDragHelper.processTouchEvent(ev);
                break;
        }
        return this.mIsBeingDragged;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (!isSwipeEnabled()) {
            return super.onTouchEvent(event);
        }
        int action = event.getActionMasked();
        this.gestureDetector.onTouchEvent(event);
        switch (action) {
            case 0:
                this.mDragHelper.processTouchEvent(event);
                this.f1371sX = event.getRawX();
                this.f1372sY = event.getRawY();
                break;
            case 1:
            case 3:
                this.mIsBeingDragged = false;
                this.mDragHelper.processTouchEvent(event);
                break;
            case 2:
                break;
            default:
                this.mDragHelper.processTouchEvent(event);
                break;
        }
        checkCanDrag(event);
        if (this.mIsBeingDragged) {
            getParent().requestDisallowInterceptTouchEvent(true);
            this.mDragHelper.processTouchEvent(event);
        }
        if (super.onTouchEvent(event) || this.mIsBeingDragged || action == 0) {
            return true;
        }
        return false;
    }

    public void setClickToClose(boolean mClickToClose2) {
        this.mClickToClose = mClickToClose2;
    }

    public void setSwipeEnabled(boolean enabled) {
        this.mSwipeEnabled = enabled;
    }

    public boolean isSwipeEnabled() {
        return this.mSwipeEnabled;
    }

    public boolean isLeftSwipeEnabled() {
        View bottomView = (View) this.mDragEdges.get(DragEdge.Left);
        return bottomView != null && bottomView.getParent() == this && bottomView != getSurfaceView() && this.mSwipesEnabled[DragEdge.Left.ordinal()];
    }

    public void setLeftSwipeEnabled(boolean leftSwipeEnabled) {
        this.mSwipesEnabled[DragEdge.Left.ordinal()] = leftSwipeEnabled;
    }

    public boolean isRightSwipeEnabled() {
        View bottomView = (View) this.mDragEdges.get(DragEdge.Right);
        return bottomView != null && bottomView.getParent() == this && bottomView != getSurfaceView() && this.mSwipesEnabled[DragEdge.Right.ordinal()];
    }

    public void setRightSwipeEnabled(boolean rightSwipeEnabled) {
        this.mSwipesEnabled[DragEdge.Right.ordinal()] = rightSwipeEnabled;
    }

    public boolean isTopSwipeEnabled() {
        View bottomView = (View) this.mDragEdges.get(DragEdge.Top);
        return bottomView != null && bottomView.getParent() == this && bottomView != getSurfaceView() && this.mSwipesEnabled[DragEdge.Top.ordinal()];
    }

    public void setTopSwipeEnabled(boolean topSwipeEnabled) {
        this.mSwipesEnabled[DragEdge.Top.ordinal()] = topSwipeEnabled;
    }

    public boolean isBottomSwipeEnabled() {
        View bottomView = (View) this.mDragEdges.get(DragEdge.Bottom);
        return bottomView != null && bottomView.getParent() == this && bottomView != getSurfaceView() && this.mSwipesEnabled[DragEdge.Bottom.ordinal()];
    }

    public void setBottomSwipeEnabled(boolean bottomSwipeEnabled) {
        this.mSwipesEnabled[DragEdge.Bottom.ordinal()] = bottomSwipeEnabled;
    }

    public float getWillOpenPercentAfterOpen() {
        return this.mWillOpenPercentAfterOpen;
    }

    public void setWillOpenPercentAfterOpen(float willOpenPercentAfterOpen) {
        this.mWillOpenPercentAfterOpen = willOpenPercentAfterOpen;
    }

    public float getWillOpenPercentAfterClose() {
        return this.mWillOpenPercentAfterClose;
    }

    public void setWillOpenPercentAfterClose(float willOpenPercentAfterClose) {
        this.mWillOpenPercentAfterClose = willOpenPercentAfterClose;
    }

    private boolean insideAdapterView() {
        return getAdapterView() != null;
    }

    private AdapterView getAdapterView() {
        ViewParent t = getParent();
        if (t instanceof AdapterView) {
            return (AdapterView) t;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void performAdapterViewItemClick() {
        if (getOpenStatus() == Status.Close) {
            ViewParent t = getParent();
            if (t instanceof AdapterView) {
                AdapterView view = (AdapterView) t;
                int p = view.getPositionForView(this);
                if (p != -1) {
                    view.performItemClick(view.getChildAt(p - view.getFirstVisiblePosition()), p, view.getAdapter().getItemId(p));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean performAdapterViewItemLongClick() {
        if (getOpenStatus() != Status.Close) {
            return false;
        }
        ViewParent t = getParent();
        if (!(t instanceof AdapterView)) {
            return false;
        }
        AdapterView view = (AdapterView) t;
        int p = view.getPositionForView(this);
        if (p == -1) {
            return false;
        }
        long vId = view.getItemIdAtPosition(p);
        boolean handled = false;
        try {
            Method m = AbsListView.class.getDeclaredMethod("performLongPress", new Class[]{View.class, Integer.TYPE, Long.TYPE});
            m.setAccessible(true);
            return ((Boolean) m.invoke(view, new Object[]{this, Integer.valueOf(p), Long.valueOf(vId)})).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            if (view.getOnItemLongClickListener() != null) {
                handled = view.getOnItemLongClickListener().onItemLongClick(view, this, p, vId);
            }
            if (!handled) {
                return handled;
            }
            view.performHapticFeedback(0);
            return handled;
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (insideAdapterView()) {
            if (this.clickListener == null) {
                setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        SwipeLayout.this.performAdapterViewItemClick();
                    }
                });
            }
            if (this.longClickListener == null) {
                setOnLongClickListener(new OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        SwipeLayout.this.performAdapterViewItemLongClick();
                        return true;
                    }
                });
            }
        }
    }

    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
        this.clickListener = l;
    }

    public void setOnLongClickListener(OnLongClickListener l) {
        super.setOnLongClickListener(l);
        this.longClickListener = l;
    }

    /* access modifiers changed from: private */
    public boolean isTouchOnSurface(MotionEvent ev) {
        View surfaceView = getSurfaceView();
        if (surfaceView == null) {
            return false;
        }
        if (this.hitSurfaceRect == null) {
            this.hitSurfaceRect = new Rect();
        }
        surfaceView.getHitRect(this.hitSurfaceRect);
        return this.hitSurfaceRect.contains((int) ev.getX(), (int) ev.getY());
    }

    public void setDragDistance(int max) {
        if (max < 0) {
            max = 0;
        }
        this.mDragDistance = dp2px((float) max);
        requestLayout();
    }

    public void setShowMode(ShowMode mode) {
        this.mShowMode = mode;
        requestLayout();
    }

    public DragEdge getDragEdge() {
        return this.mCurrentDragEdge;
    }

    public int getDragDistance() {
        return this.mDragDistance;
    }

    public ShowMode getShowMode() {
        return this.mShowMode;
    }

    public View getSurfaceView() {
        if (getChildCount() == 0) {
            return null;
        }
        return getChildAt(getChildCount() - 1);
    }

    public View getCurrentBottomView() {
        List<View> bottoms = getBottomViews();
        if (this.mCurrentDragEdge.ordinal() < bottoms.size()) {
            return (View) bottoms.get(this.mCurrentDragEdge.ordinal());
        }
        return null;
    }

    public List<View> getBottomViews() {
        ArrayList<View> bottoms = new ArrayList<>();
        for (DragEdge dragEdge : DragEdge.values()) {
            bottoms.add(this.mDragEdges.get(dragEdge));
        }
        return bottoms;
    }

    public Status getOpenStatus() {
        View surfaceView = getSurfaceView();
        if (surfaceView == null) {
            return Status.Close;
        }
        int surfaceLeft = surfaceView.getLeft();
        int surfaceTop = surfaceView.getTop();
        if (surfaceLeft == getPaddingLeft() && surfaceTop == getPaddingTop()) {
            return Status.Close;
        }
        if (surfaceLeft == getPaddingLeft() - this.mDragDistance || surfaceLeft == getPaddingLeft() + this.mDragDistance || surfaceTop == getPaddingTop() - this.mDragDistance || surfaceTop == getPaddingTop() + this.mDragDistance) {
            return Status.Open;
        }
        return Status.Middle;
    }

    /* access modifiers changed from: protected */
    public void processHandRelease(float xvel, float yvel, boolean isCloseBeforeDragged) {
        float minVelocity = this.mDragHelper.getMinVelocity();
        View surfaceView = getSurfaceView();
        DragEdge currentDragEdge = this.mCurrentDragEdge;
        if (currentDragEdge != null && surfaceView != null) {
            float willOpenPercent = isCloseBeforeDragged ? this.mWillOpenPercentAfterClose : this.mWillOpenPercentAfterOpen;
            if (currentDragEdge == DragEdge.Left) {
                if (xvel > minVelocity) {
                    open();
                } else if (xvel < (-minVelocity)) {
                    close();
                } else if ((((float) getSurfaceView().getLeft()) * 1.0f) / ((float) this.mDragDistance) > willOpenPercent) {
                    open();
                } else {
                    close();
                }
            } else if (currentDragEdge == DragEdge.Right) {
                if (xvel > minVelocity) {
                    close();
                } else if (xvel < (-minVelocity)) {
                    open();
                } else if ((((float) (-getSurfaceView().getLeft())) * 1.0f) / ((float) this.mDragDistance) > willOpenPercent) {
                    open();
                } else {
                    close();
                }
            } else if (currentDragEdge == DragEdge.Top) {
                if (yvel > minVelocity) {
                    open();
                } else if (yvel < (-minVelocity)) {
                    close();
                } else if ((((float) getSurfaceView().getTop()) * 1.0f) / ((float) this.mDragDistance) > willOpenPercent) {
                    open();
                } else {
                    close();
                }
            } else if (currentDragEdge != DragEdge.Bottom) {
            } else {
                if (yvel > minVelocity) {
                    close();
                } else if (yvel < (-minVelocity)) {
                    open();
                } else if ((((float) (-getSurfaceView().getTop())) * 1.0f) / ((float) this.mDragDistance) > willOpenPercent) {
                    open();
                } else {
                    close();
                }
            }
        }
    }

    public void open() {
        open(true, true);
    }

    public void open(boolean smooth, boolean notify) {
        View surface = getSurfaceView();
        View bottom = getCurrentBottomView();
        if (surface != null) {
            Rect rect = computeSurfaceLayoutArea(true);
            if (smooth) {
                this.mDragHelper.smoothSlideViewTo(surface, rect.left, rect.top);
            } else {
                int dx = rect.left - surface.getLeft();
                int dy = rect.top - surface.getTop();
                surface.layout(rect.left, rect.top, rect.right, rect.bottom);
                if (getShowMode() == ShowMode.PullOut) {
                    Rect bRect = computeBottomLayoutAreaViaSurface(ShowMode.PullOut, rect);
                    if (bottom != null) {
                        bottom.layout(bRect.left, bRect.top, bRect.right, bRect.bottom);
                    }
                }
                if (notify) {
                    dispatchRevealEvent(rect.left, rect.top, rect.right, rect.bottom);
                    dispatchSwipeEvent(rect.left, rect.top, dx, dy);
                } else {
                    safeBottomView();
                }
            }
            invalidate();
        }
    }

    public void close() {
        close(true, true);
    }

    public void close(boolean smooth, boolean notify) {
        View surface = getSurfaceView();
        if (surface != null) {
            if (smooth) {
                this.mDragHelper.smoothSlideViewTo(getSurfaceView(), getPaddingLeft(), getPaddingTop());
            } else {
                Rect rect = computeSurfaceLayoutArea(false);
                int dx = rect.left - surface.getLeft();
                int dy = rect.top - surface.getTop();
                surface.layout(rect.left, rect.top, rect.right, rect.bottom);
                if (notify) {
                    dispatchRevealEvent(rect.left, rect.top, rect.right, rect.bottom);
                    dispatchSwipeEvent(rect.left, rect.top, dx, dy);
                } else {
                    safeBottomView();
                }
            }
            invalidate();
        }
    }

    private Rect computeSurfaceLayoutArea(boolean open) {
        int l = getPaddingLeft();
        int t = getPaddingTop();
        if (open) {
            if (this.mCurrentDragEdge == DragEdge.Left) {
                l = getPaddingLeft() + this.mDragDistance;
            } else if (this.mCurrentDragEdge == DragEdge.Right) {
                l = getPaddingLeft() - this.mDragDistance;
            } else {
                t = this.mCurrentDragEdge == DragEdge.Top ? getPaddingTop() + this.mDragDistance : getPaddingTop() - this.mDragDistance;
            }
        }
        return new Rect(l, t, getMeasuredWidth() + l, getMeasuredHeight() + t);
    }

    private Rect computeBottomLayoutAreaViaSurface(ShowMode mode, Rect surfaceArea) {
        int i = 0;
        Rect rect = surfaceArea;
        View bottomView = getCurrentBottomView();
        int bl = rect.left;
        int bt = rect.top;
        int br = rect.right;
        int bb = rect.bottom;
        if (mode == ShowMode.PullOut) {
            if (this.mCurrentDragEdge == DragEdge.Left) {
                bl = rect.left - this.mDragDistance;
            } else if (this.mCurrentDragEdge == DragEdge.Right) {
                bl = rect.right;
            } else {
                bt = this.mCurrentDragEdge == DragEdge.Top ? rect.top - this.mDragDistance : rect.bottom;
            }
            if (this.mCurrentDragEdge == DragEdge.Left || this.mCurrentDragEdge == DragEdge.Right) {
                bb = rect.bottom;
                if (bottomView != null) {
                    i = bottomView.getMeasuredWidth();
                }
                br = bl + i;
            } else {
                if (bottomView != null) {
                    i = bottomView.getMeasuredHeight();
                }
                bb = bt + i;
                br = rect.right;
            }
        } else if (mode == ShowMode.LayDown) {
            if (this.mCurrentDragEdge == DragEdge.Left) {
                br = bl + this.mDragDistance;
            } else if (this.mCurrentDragEdge == DragEdge.Right) {
                bl = br - this.mDragDistance;
            } else if (this.mCurrentDragEdge == DragEdge.Top) {
                bb = bt + this.mDragDistance;
            } else {
                bt = bb - this.mDragDistance;
            }
        }
        return new Rect(bl, bt, br, bb);
    }

    /* access modifiers changed from: private */
    public Rect computeBottomLayDown(DragEdge dragEdge) {
        int br;
        int bb;
        int bl = getPaddingLeft();
        int bt = getPaddingTop();
        if (dragEdge == DragEdge.Right) {
            bl = getMeasuredWidth() - this.mDragDistance;
        } else if (dragEdge == DragEdge.Bottom) {
            bt = getMeasuredHeight() - this.mDragDistance;
        }
        if (dragEdge == DragEdge.Left || dragEdge == DragEdge.Right) {
            br = bl + this.mDragDistance;
            bb = bt + getMeasuredHeight();
        } else {
            br = bl + getMeasuredWidth();
            bb = bt + this.mDragDistance;
        }
        return new Rect(bl, bt, br, bb);
    }

    public void setOnDoubleClickListener(DoubleClickListener doubleClickListener) {
        this.mDoubleClickListener = doubleClickListener;
    }

    private int dp2px(float dp) {
        return (int) ((getContext().getResources().getDisplayMetrics().density * dp) + 0.5f);
    }

    @Deprecated
    public void setDragEdge(DragEdge dragEdge) {
        clearDragEdge();
        if (getChildCount() >= 2) {
            this.mDragEdges.put(dragEdge, getChildAt(getChildCount() - 2));
        }
        setCurrentDragEdge(dragEdge);
    }

    /* access modifiers changed from: protected */
    public void onViewRemoved(View child) {
        for (Entry<DragEdge, View> entry : new HashMap(this.mDragEdges).entrySet()) {
            if (entry.getValue() == child) {
                this.mDragEdges.remove(entry.getKey());
            }
        }
    }

    public Map<DragEdge, View> getDragEdgeMap() {
        return this.mDragEdges;
    }

    @Deprecated
    public List<DragEdge> getDragEdges() {
        return new ArrayList(this.mDragEdges.keySet());
    }

    @Deprecated
    public void setDragEdges(List<DragEdge> dragEdges) {
        clearDragEdge();
        int size = Math.min(dragEdges.size(), getChildCount() - 1);
        for (int i = 0; i < size; i++) {
            this.mDragEdges.put((DragEdge) dragEdges.get(i), getChildAt(i));
        }
        if (dragEdges.size() == 0 || dragEdges.contains(DefaultDragEdge)) {
            setCurrentDragEdge(DefaultDragEdge);
        } else {
            setCurrentDragEdge((DragEdge) dragEdges.get(0));
        }
    }

    @Deprecated
    public void setDragEdges(DragEdge... mDragEdges2) {
        clearDragEdge();
        setDragEdges(Arrays.asList(mDragEdges2));
    }

    private float getCurrentOffset() {
        if (this.mCurrentDragEdge == null) {
            return 0.0f;
        }
        return this.mEdgeSwipesOffset[this.mCurrentDragEdge.ordinal()];
    }

    private void setCurrentDragEdge(DragEdge dragEdge) {
        this.mCurrentDragEdge = dragEdge;
        updateBottomViews();
    }

    private void updateBottomViews() {
        View currentBottomView = getCurrentBottomView();
        if (currentBottomView != null) {
            if (this.mCurrentDragEdge == DragEdge.Left || this.mCurrentDragEdge == DragEdge.Right) {
                this.mDragDistance = currentBottomView.getMeasuredWidth() - dp2px(getCurrentOffset());
            } else {
                this.mDragDistance = currentBottomView.getMeasuredHeight() - dp2px(getCurrentOffset());
            }
        }
        if (this.mShowMode == ShowMode.PullOut) {
            layoutPullOut();
        } else if (this.mShowMode == ShowMode.LayDown) {
            layoutLayDown();
        }
        safeBottomView();
    }
}
