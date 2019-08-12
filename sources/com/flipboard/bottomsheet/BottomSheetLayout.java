package com.flipboard.bottomsheet;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Property;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import com.airbnb.android.airmapview.AirMapInterface;
import flipboard.bottomsheet.R;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

public class BottomSheetLayout extends FrameLayout {
    private static final Property<BottomSheetLayout, Float> SHEET_TRANSLATION = new Property<BottomSheetLayout, Float>(Float.class, "sheetTranslation") {
        public Float get(BottomSheetLayout object) {
            return Float.valueOf(object.sheetTranslation);
        }

        public void set(BottomSheetLayout object, Float value) {
            object.setSheetTranslation(value.floatValue());
        }
    };
    private TimeInterpolator animationInterpolator;
    public boolean bottomSheetOwnsTouch;
    private Rect contentClipRect;
    /* access modifiers changed from: private */
    public Animator currentAnimator;
    /* access modifiers changed from: private */
    public int currentSheetViewHeight;
    private final int defaultSheetWidth;
    private ViewTransformer defaultViewTransformer;
    private View dimView;
    private float downSheetTranslation;
    private State downState;
    private float downX;
    private float downY;
    private boolean hasIntercepted;
    private boolean interceptContentTouch;
    private final boolean isTablet;
    private float minFlingVelocity;
    /* access modifiers changed from: private */
    public CopyOnWriteArraySet<OnSheetDismissedListener> onSheetDismissedListeners;
    /* access modifiers changed from: private */
    public CopyOnWriteArraySet<OnSheetStateChangeListener> onSheetStateChangeListeners;
    private float peek;
    private boolean peekOnDismiss;
    /* access modifiers changed from: private */
    public Runnable runAfterDismiss;
    private int screenWidth;
    private int sheetEndX;
    private int sheetStartX;
    /* access modifiers changed from: private */
    public float sheetTranslation;
    private OnLayoutChangeListener sheetViewOnLayoutChangeListener;
    private boolean sheetViewOwnsTouch;
    private boolean shouldDimContentView;
    /* access modifiers changed from: private */
    public State state;
    private float touchSlop;
    private boolean useHardwareLayerWhileAnimating;
    private VelocityTracker velocityTracker;
    /* access modifiers changed from: private */
    public ViewTransformer viewTransformer;

    private static class CancelDetectionAnimationListener extends AnimatorListenerAdapter {
        protected boolean canceled;

        private CancelDetectionAnimationListener() {
        }

        public void onAnimationCancel(Animator animation) {
            this.canceled = true;
        }
    }

    private static class IdentityViewTransformer extends BaseViewTransformer {
        private IdentityViewTransformer() {
        }

        public void transformView(float translation, float maxTranslation, float peekedTranslation, BottomSheetLayout parent, View view) {
        }
    }

    public interface OnSheetStateChangeListener {
        void onSheetStateChanged(State state);
    }

    public enum State {
        HIDDEN,
        PREPARING,
        PEEKED,
        EXPANDED
    }

    public BottomSheetLayout(Context context) {
        super(context);
        this.contentClipRect = new Rect();
        this.state = State.HIDDEN;
        this.peekOnDismiss = false;
        this.animationInterpolator = new DecelerateInterpolator(1.6f);
        this.defaultViewTransformer = new IdentityViewTransformer();
        this.shouldDimContentView = true;
        this.useHardwareLayerWhileAnimating = true;
        this.onSheetDismissedListeners = new CopyOnWriteArraySet<>();
        this.onSheetStateChangeListeners = new CopyOnWriteArraySet<>();
        this.interceptContentTouch = true;
        this.screenWidth = 0;
        this.isTablet = getResources().getBoolean(R.bool.bottomsheet_is_tablet);
        this.defaultSheetWidth = getResources().getDimensionPixelSize(R.dimen.bottomsheet_default_sheet_width);
        this.sheetStartX = 0;
        this.sheetEndX = 0;
        init();
    }

    public BottomSheetLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomSheetLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.contentClipRect = new Rect();
        this.state = State.HIDDEN;
        this.peekOnDismiss = false;
        this.animationInterpolator = new DecelerateInterpolator(1.6f);
        this.defaultViewTransformer = new IdentityViewTransformer();
        this.shouldDimContentView = true;
        this.useHardwareLayerWhileAnimating = true;
        this.onSheetDismissedListeners = new CopyOnWriteArraySet<>();
        this.onSheetStateChangeListeners = new CopyOnWriteArraySet<>();
        this.interceptContentTouch = true;
        this.screenWidth = 0;
        this.isTablet = getResources().getBoolean(R.bool.bottomsheet_is_tablet);
        this.defaultSheetWidth = getResources().getDimensionPixelSize(R.dimen.bottomsheet_default_sheet_width);
        this.sheetStartX = 0;
        this.sheetEndX = 0;
        init();
    }

    @TargetApi(21)
    public BottomSheetLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.contentClipRect = new Rect();
        this.state = State.HIDDEN;
        this.peekOnDismiss = false;
        this.animationInterpolator = new DecelerateInterpolator(1.6f);
        this.defaultViewTransformer = new IdentityViewTransformer();
        this.shouldDimContentView = true;
        this.useHardwareLayerWhileAnimating = true;
        this.onSheetDismissedListeners = new CopyOnWriteArraySet<>();
        this.onSheetStateChangeListeners = new CopyOnWriteArraySet<>();
        this.interceptContentTouch = true;
        this.screenWidth = 0;
        this.isTablet = getResources().getBoolean(R.bool.bottomsheet_is_tablet);
        this.defaultSheetWidth = getResources().getDimensionPixelSize(R.dimen.bottomsheet_default_sheet_width);
        this.sheetStartX = 0;
        this.sheetEndX = 0;
        init();
    }

    private void init() {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.minFlingVelocity = (float) viewConfiguration.getScaledMinimumFlingVelocity();
        this.touchSlop = (float) viewConfiguration.getScaledTouchSlop();
        this.dimView = new View(getContext());
        this.dimView.setBackgroundColor(AirMapInterface.CIRCLE_BORDER_COLOR);
        this.dimView.setAlpha(0.0f);
        this.dimView.setVisibility(4);
        this.peek = 0.0f;
        setFocusableInTouchMode(true);
        Point point = new Point();
        ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getSize(point);
        this.screenWidth = point.x;
        this.sheetEndX = this.screenWidth;
    }

    public void addView(View child) {
        if (getChildCount() > 0) {
            throw new IllegalArgumentException("You may not declare more then one child of bottom sheet. The sheet view must be added dynamically with showWithSheetView()");
        }
        setContentView(child);
    }

    public void addView(View child, int index) {
        addView(child);
    }

    public void addView(View child, int index, LayoutParams params) {
        addView(child);
    }

    public void addView(View child, LayoutParams params) {
        addView(child);
    }

    public void addView(View child, int width, int height) {
        addView(child);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.velocityTracker = VelocityTracker.obtain();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.velocityTracker.clear();
        cancelCurrentAnimation();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.contentClipRect.set(0, 0, getWidth(), (int) (((double) getHeight()) - Math.ceil((double) this.sheetTranslation)));
    }

    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == 4 && isSheetShowing()) {
            if (event.getAction() == 0 && event.getRepeatCount() == 0) {
                DispatcherState state2 = getKeyDispatcherState();
                if (state2 == null) {
                    return true;
                }
                state2.startTracking(event, this);
                return true;
            } else if (event.getAction() == 1) {
                DispatcherState dispatcherState = getKeyDispatcherState();
                if (dispatcherState != null) {
                    dispatcherState.handleUpEvent(event);
                }
                if (isSheetShowing() && event.isTracking() && !event.isCanceled()) {
                    if (this.state != State.EXPANDED || !this.peekOnDismiss) {
                        dismissSheet();
                        return true;
                    }
                    peekSheet();
                    return true;
                }
            }
        }
        return super.onKeyPreIme(keyCode, event);
    }

    /* access modifiers changed from: private */
    public void setSheetTranslation(float newTranslation) {
        int i = 0;
        this.sheetTranslation = Math.min(newTranslation, getMaxSheetTranslation());
        this.contentClipRect.set(0, 0, getWidth(), (int) (((double) getHeight()) - Math.ceil((double) this.sheetTranslation)));
        getSheetView().setTranslationY(((float) getHeight()) - this.sheetTranslation);
        transformView(this.sheetTranslation);
        if (this.shouldDimContentView) {
            float dimAlpha = getDimAlpha(this.sheetTranslation);
            this.dimView.setAlpha(dimAlpha);
            View view = this.dimView;
            if (dimAlpha <= 0.0f) {
                i = 4;
            }
            view.setVisibility(i);
        }
    }

    private void transformView(float sheetTranslation2) {
        if (this.viewTransformer != null) {
            this.viewTransformer.transformView(sheetTranslation2, getMaxSheetTranslation(), getPeekSheetTranslation(), this, getContentView());
        } else if (this.defaultViewTransformer != null) {
            this.defaultViewTransformer.transformView(sheetTranslation2, getMaxSheetTranslation(), getPeekSheetTranslation(), this, getContentView());
        }
    }

    private float getDimAlpha(float sheetTranslation2) {
        if (this.viewTransformer != null) {
            return this.viewTransformer.getDimAlpha(sheetTranslation2, getMaxSheetTranslation(), getPeekSheetTranslation(), this, getContentView());
        } else if (this.defaultViewTransformer == null) {
            return 0.0f;
        } else {
            return this.defaultViewTransformer.getDimAlpha(sheetTranslation2, getMaxSheetTranslation(), getPeekSheetTranslation(), this, getContentView());
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean downAction;
        boolean z = true;
        if (ev.getActionMasked() == 0) {
            downAction = true;
        } else {
            downAction = false;
        }
        if (downAction) {
            this.hasIntercepted = false;
        }
        if (this.interceptContentTouch || (ev.getY() > ((float) getHeight()) - this.sheetTranslation && isXInSheet(ev.getX()))) {
            if (!downAction || !isSheetShowing()) {
                z = false;
            }
            this.hasIntercepted = z;
        } else {
            this.hasIntercepted = false;
        }
        return this.hasIntercepted;
    }

    /* JADX WARNING: Removed duplicated region for block: B:88:0x0278  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x029c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
            r17 = this;
            boolean r12 = r17.isSheetShowing()
            if (r12 != 0) goto L_0x0008
            r12 = 0
        L_0x0007:
            return r12
        L_0x0008:
            boolean r12 = r17.isAnimating()
            if (r12 == 0) goto L_0x0010
            r12 = 0
            goto L_0x0007
        L_0x0010:
            r0 = r17
            boolean r12 = r0.hasIntercepted
            if (r12 != 0) goto L_0x001b
            boolean r12 = r17.onInterceptTouchEvent(r18)
            goto L_0x0007
        L_0x001b:
            int r12 = r18.getAction()
            if (r12 != 0) goto L_0x0052
            r12 = 0
            r0 = r17
            r0.bottomSheetOwnsTouch = r12
            r12 = 0
            r0 = r17
            r0.sheetViewOwnsTouch = r12
            float r12 = r18.getY()
            r0 = r17
            r0.downY = r12
            float r12 = r18.getX()
            r0 = r17
            r0.downX = r12
            r0 = r17
            float r12 = r0.sheetTranslation
            r0 = r17
            r0.downSheetTranslation = r12
            r0 = r17
            com.flipboard.bottomsheet.BottomSheetLayout$State r12 = r0.state
            r0 = r17
            r0.downState = r12
            r0 = r17
            android.view.VelocityTracker r12 = r0.velocityTracker
            r12.clear()
        L_0x0052:
            r0 = r17
            android.view.VelocityTracker r12 = r0.velocityTracker
            r0 = r18
            r12.addMovement(r0)
            float r6 = r17.getMaxSheetTranslation()
            float r8 = r17.getPeekSheetTranslation()
            r0 = r17
            float r12 = r0.downY
            float r13 = r18.getY()
            float r4 = r12 - r13
            r0 = r17
            float r12 = r0.downX
            float r13 = r18.getX()
            float r3 = r12 - r13
            r0 = r17
            boolean r12 = r0.bottomSheetOwnsTouch
            if (r12 != 0) goto L_0x00ea
            r0 = r17
            boolean r12 = r0.sheetViewOwnsTouch
            if (r12 != 0) goto L_0x00ea
            float r12 = java.lang.Math.abs(r4)
            r0 = r17
            float r13 = r0.touchSlop
            int r12 = (r12 > r13 ? 1 : (r12 == r13 ? 0 : -1))
            if (r12 <= 0) goto L_0x01bc
            r12 = 1
        L_0x0090:
            r0 = r17
            r0.bottomSheetOwnsTouch = r12
            float r12 = java.lang.Math.abs(r3)
            r0 = r17
            float r13 = r0.touchSlop
            int r12 = (r12 > r13 ? 1 : (r12 == r13 ? 0 : -1))
            if (r12 <= 0) goto L_0x01bf
            r12 = 1
        L_0x00a1:
            r0 = r17
            r0.sheetViewOwnsTouch = r12
            r0 = r17
            boolean r12 = r0.bottomSheetOwnsTouch
            if (r12 == 0) goto L_0x00ea
            r0 = r17
            com.flipboard.bottomsheet.BottomSheetLayout$State r12 = r0.state
            com.flipboard.bottomsheet.BottomSheetLayout$State r13 = com.flipboard.bottomsheet.BottomSheetLayout.State.PEEKED
            if (r12 != r13) goto L_0x00d3
            android.view.MotionEvent r2 = android.view.MotionEvent.obtain(r18)
            r12 = 0
            r0 = r17
            float r13 = r0.sheetTranslation
            int r14 = r17.getHeight()
            float r14 = (float) r14
            float r13 = r13 - r14
            r2.offsetLocation(r12, r13)
            r12 = 3
            r2.setAction(r12)
            android.view.View r12 = r17.getSheetView()
            r12.dispatchTouchEvent(r2)
            r2.recycle()
        L_0x00d3:
            r12 = 0
            r0 = r17
            r0.sheetViewOwnsTouch = r12
            float r12 = r18.getY()
            r0 = r17
            r0.downY = r12
            float r12 = r18.getX()
            r0 = r17
            r0.downX = r12
            r4 = 0
            r3 = 0
        L_0x00ea:
            r0 = r17
            float r12 = r0.downSheetTranslation
            float r7 = r12 + r4
            r0 = r17
            boolean r12 = r0.bottomSheetOwnsTouch
            if (r12 == 0) goto L_0x023c
            r12 = 0
            int r12 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r12 >= 0) goto L_0x01c2
            r9 = 1
        L_0x00fc:
            android.view.View r12 = r17.getSheetView()
            float r13 = r18.getX()
            float r14 = r18.getY()
            r0 = r17
            float r15 = r0.sheetTranslation
            int r16 = r17.getHeight()
            r0 = r16
            float r0 = (float) r0
            r16 = r0
            float r15 = r15 - r16
            float r14 = r14 + r15
            r0 = r17
            boolean r1 = r0.canScrollUp(r12, r13, r14)
            r0 = r17
            com.flipboard.bottomsheet.BottomSheetLayout$State r12 = r0.state
            com.flipboard.bottomsheet.BottomSheetLayout$State r13 = com.flipboard.bottomsheet.BottomSheetLayout.State.EXPANDED
            if (r12 != r13) goto L_0x0164
            if (r9 == 0) goto L_0x0164
            if (r1 != 0) goto L_0x0164
            float r12 = r18.getY()
            r0 = r17
            r0.downY = r12
            r0 = r17
            float r12 = r0.sheetTranslation
            r0 = r17
            r0.downSheetTranslation = r12
            r0 = r17
            android.view.VelocityTracker r12 = r0.velocityTracker
            r12.clear()
            com.flipboard.bottomsheet.BottomSheetLayout$State r12 = com.flipboard.bottomsheet.BottomSheetLayout.State.PEEKED
            r0 = r17
            r0.setState(r12)
            r12 = 2
            r0 = r17
            r0.setSheetLayerTypeIfEnabled(r12)
            r0 = r17
            float r7 = r0.sheetTranslation
            android.view.MotionEvent r2 = android.view.MotionEvent.obtain(r18)
            r12 = 3
            r2.setAction(r12)
            android.view.View r12 = r17.getSheetView()
            r12.dispatchTouchEvent(r2)
            r2.recycle()
        L_0x0164:
            r0 = r17
            com.flipboard.bottomsheet.BottomSheetLayout$State r12 = r0.state
            com.flipboard.bottomsheet.BottomSheetLayout$State r13 = com.flipboard.bottomsheet.BottomSheetLayout.State.PEEKED
            if (r12 != r13) goto L_0x0198
            int r12 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
            if (r12 <= 0) goto L_0x0198
            r0 = r17
            r0.setSheetTranslation(r6)
            float r7 = java.lang.Math.min(r6, r7)
            android.view.MotionEvent r5 = android.view.MotionEvent.obtain(r18)
            r12 = 0
            r5.setAction(r12)
            android.view.View r12 = r17.getSheetView()
            r12.dispatchTouchEvent(r5)
            r5.recycle()
            com.flipboard.bottomsheet.BottomSheetLayout$State r12 = com.flipboard.bottomsheet.BottomSheetLayout.State.EXPANDED
            r0 = r17
            r0.setState(r12)
            r12 = 0
            r0 = r17
            r0.setSheetLayerTypeIfEnabled(r12)
        L_0x0198:
            r0 = r17
            com.flipboard.bottomsheet.BottomSheetLayout$State r12 = r0.state
            com.flipboard.bottomsheet.BottomSheetLayout$State r13 = com.flipboard.bottomsheet.BottomSheetLayout.State.EXPANDED
            if (r12 != r13) goto L_0x01c5
            r12 = 0
            r0 = r17
            float r13 = r0.sheetTranslation
            int r14 = r17.getHeight()
            float r14 = (float) r14
            float r13 = r13 - r14
            r0 = r18
            r0.offsetLocation(r12, r13)
            android.view.View r12 = r17.getSheetView()
            r0 = r18
            r12.dispatchTouchEvent(r0)
        L_0x01b9:
            r12 = 1
            goto L_0x0007
        L_0x01bc:
            r12 = 0
            goto L_0x0090
        L_0x01bf:
            r12 = 0
            goto L_0x00a1
        L_0x01c2:
            r9 = 0
            goto L_0x00fc
        L_0x01c5:
            int r12 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r12 >= 0) goto L_0x01d0
            float r12 = r8 - r7
            r13 = 1082130432(0x40800000, float:4.0)
            float r12 = r12 / r13
            float r7 = r8 - r12
        L_0x01d0:
            r0 = r17
            r0.setSheetTranslation(r7)
            int r12 = r18.getAction()
            r13 = 3
            if (r12 != r13) goto L_0x01e7
            r0 = r17
            com.flipboard.bottomsheet.BottomSheetLayout$State r12 = r0.downState
            com.flipboard.bottomsheet.BottomSheetLayout$State r13 = com.flipboard.bottomsheet.BottomSheetLayout.State.EXPANDED
            if (r12 != r13) goto L_0x01f6
            r17.expandSheet()
        L_0x01e7:
            int r12 = r18.getAction()
            r13 = 1
            if (r12 != r13) goto L_0x01b9
            int r12 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r12 >= 0) goto L_0x01fa
            r17.dismissSheet()
            goto L_0x01b9
        L_0x01f6:
            r17.peekSheet()
            goto L_0x01e7
        L_0x01fa:
            r0 = r17
            android.view.VelocityTracker r12 = r0.velocityTracker
            r13 = 1000(0x3e8, float:1.401E-42)
            r12.computeCurrentVelocity(r13)
            r0 = r17
            android.view.VelocityTracker r12 = r0.velocityTracker
            float r11 = r12.getYVelocity()
            float r12 = java.lang.Math.abs(r11)
            r0 = r17
            float r13 = r0.minFlingVelocity
            int r12 = (r12 > r13 ? 1 : (r12 == r13 ? 0 : -1))
            if (r12 >= 0) goto L_0x022e
            r0 = r17
            float r12 = r0.sheetTranslation
            int r13 = r17.getHeight()
            int r13 = r13 / 2
            float r13 = (float) r13
            int r12 = (r12 > r13 ? 1 : (r12 == r13 ? 0 : -1))
            if (r12 <= 0) goto L_0x022a
            r17.expandSheet()
            goto L_0x01b9
        L_0x022a:
            r17.peekSheet()
            goto L_0x01b9
        L_0x022e:
            r12 = 0
            int r12 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            if (r12 >= 0) goto L_0x0237
            r17.expandSheet()
            goto L_0x01b9
        L_0x0237:
            r17.peekSheet()
            goto L_0x01b9
        L_0x023c:
            float r12 = r18.getY()
            int r13 = r17.getHeight()
            float r13 = (float) r13
            r0 = r17
            float r14 = r0.sheetTranslation
            float r13 = r13 - r14
            int r12 = (r12 > r13 ? 1 : (r12 == r13 ? 0 : -1))
            if (r12 < 0) goto L_0x025a
            float r12 = r18.getX()
            r0 = r17
            boolean r12 = r0.isXInSheet(r12)
            if (r12 != 0) goto L_0x0270
        L_0x025a:
            r10 = 1
        L_0x025b:
            int r12 = r18.getAction()
            r13 = 1
            if (r12 != r13) goto L_0x0272
            if (r10 == 0) goto L_0x0272
            r0 = r17
            boolean r12 = r0.interceptContentTouch
            if (r12 == 0) goto L_0x0272
            r17.dismissSheet()
            r12 = 1
            goto L_0x0007
        L_0x0270:
            r10 = 0
            goto L_0x025b
        L_0x0272:
            r0 = r17
            boolean r12 = r0.isTablet
            if (r12 == 0) goto L_0x029c
            float r12 = r17.getX()
            r0 = r17
            int r13 = r0.sheetStartX
            float r13 = (float) r13
            float r12 = r12 - r13
        L_0x0282:
            r0 = r17
            float r13 = r0.sheetTranslation
            int r14 = r17.getHeight()
            float r14 = (float) r14
            float r13 = r13 - r14
            r0 = r18
            r0.offsetLocation(r12, r13)
            android.view.View r12 = r17.getSheetView()
            r0 = r18
            r12.dispatchTouchEvent(r0)
            goto L_0x01b9
        L_0x029c:
            r12 = 0
            goto L_0x0282
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flipboard.bottomsheet.BottomSheetLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private boolean isXInSheet(float x) {
        return !this.isTablet || (x >= ((float) this.sheetStartX) && x <= ((float) this.sheetEndX));
    }

    private boolean isAnimating() {
        return this.currentAnimator != null;
    }

    private void cancelCurrentAnimation() {
        if (this.currentAnimator != null) {
            this.currentAnimator.cancel();
        }
    }

    private boolean canScrollUp(View view, float x, float y) {
        if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View child = vg.getChildAt(i);
                int childLeft = child.getLeft();
                int childTop = child.getTop();
                if ((x > ((float) childLeft) && x < ((float) child.getRight()) && y > ((float) childTop) && y < ((float) child.getBottom())) && canScrollUp(child, x - ((float) childLeft), y - ((float) childTop))) {
                    return true;
                }
            }
        }
        return view.canScrollVertically(-1);
    }

    /* access modifiers changed from: private */
    public void setSheetLayerTypeIfEnabled(int layerType) {
        if (this.useHardwareLayerWhileAnimating) {
            getSheetView().setLayerType(layerType, null);
        }
    }

    /* access modifiers changed from: private */
    public void setState(State state2) {
        if (state2 != this.state) {
            this.state = state2;
            Iterator it = this.onSheetStateChangeListeners.iterator();
            while (it.hasNext()) {
                ((OnSheetStateChangeListener) it.next()).onSheetStateChanged(state2);
            }
        }
    }

    private boolean hasFullHeightSheet() {
        return getSheetView() == null || getSheetView().getHeight() == getHeight();
    }

    private void initializeSheetValues() {
        this.sheetTranslation = 0.0f;
        this.contentClipRect.set(0, 0, getWidth(), getHeight());
        getSheetView().setTranslationY((float) getHeight());
        this.dimView.setAlpha(0.0f);
        this.dimView.setVisibility(4);
    }

    public void expandSheet() {
        cancelCurrentAnimation();
        setSheetLayerTypeIfEnabled(0);
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, SHEET_TRANSLATION, new float[]{getMaxSheetTranslation()});
        anim.setDuration(300);
        anim.setInterpolator(this.animationInterpolator);
        anim.addListener(new CancelDetectionAnimationListener() {
            public void onAnimationEnd(Animator animation) {
                if (!this.canceled) {
                    BottomSheetLayout.this.currentAnimator = null;
                }
            }
        });
        anim.start();
        this.currentAnimator = anim;
        setState(State.EXPANDED);
    }

    public void peekSheet() {
        cancelCurrentAnimation();
        setSheetLayerTypeIfEnabled(2);
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, SHEET_TRANSLATION, new float[]{getPeekSheetTranslation()});
        anim.setDuration(300);
        anim.setInterpolator(this.animationInterpolator);
        anim.addListener(new CancelDetectionAnimationListener() {
            public void onAnimationEnd(Animator animation) {
                if (!this.canceled) {
                    BottomSheetLayout.this.currentAnimator = null;
                }
            }
        });
        anim.start();
        this.currentAnimator = anim;
        setState(State.PEEKED);
    }

    public float getPeekSheetTranslation() {
        return this.peek == 0.0f ? getDefaultPeekTranslation() : this.peek;
    }

    private float getDefaultPeekTranslation() {
        return hasFullHeightSheet() ? (float) (getHeight() / 3) : (float) getSheetView().getHeight();
    }

    public void setPeekSheetTranslation(float peek2) {
        this.peek = peek2;
    }

    public float getMaxSheetTranslation() {
        return hasFullHeightSheet() ? (float) (getHeight() - getPaddingTop()) : (float) getSheetView().getHeight();
    }

    public View getContentView() {
        if (getChildCount() > 0) {
            return getChildAt(0);
        }
        return null;
    }

    public View getSheetView() {
        if (getChildCount() > 2) {
            return getChildAt(2);
        }
        return null;
    }

    public void setContentView(View contentView) {
        super.addView(contentView, -1, generateDefaultLayoutParams());
        super.addView(this.dimView, -1, generateDefaultLayoutParams());
    }

    public void showWithSheetView(View sheetView) {
        showWithSheetView(sheetView, null);
    }

    public void showWithSheetView(final View sheetView, final ViewTransformer viewTransformer2) {
        int i;
        if (this.state != State.HIDDEN) {
            dismissSheet(new Runnable() {
                public void run() {
                    BottomSheetLayout.this.showWithSheetView(sheetView, viewTransformer2);
                }
            });
            return;
        }
        setState(State.PREPARING);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) sheetView.getLayoutParams();
        if (params == null) {
            if (this.isTablet) {
                i = -2;
            } else {
                i = -1;
            }
            params = new FrameLayout.LayoutParams(i, -2, 1);
        }
        if (this.isTablet && params.width == -2) {
            if (params.gravity == -1) {
                params.gravity = 1;
            }
            params.width = this.defaultSheetWidth;
            this.sheetStartX = (this.screenWidth - this.defaultSheetWidth) / 2;
            this.sheetEndX = this.screenWidth - this.sheetStartX;
        }
        super.addView(sheetView, -1, params);
        initializeSheetValues();
        this.viewTransformer = viewTransformer2;
        getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
            public boolean onPreDraw() {
                BottomSheetLayout.this.getViewTreeObserver().removeOnPreDrawListener(this);
                BottomSheetLayout.this.post(new Runnable() {
                    public void run() {
                        if (BottomSheetLayout.this.getSheetView() != null) {
                            BottomSheetLayout.this.peekSheet();
                        }
                    }
                });
                return true;
            }
        });
        this.currentSheetViewHeight = sheetView.getMeasuredHeight();
        this.sheetViewOnLayoutChangeListener = new OnLayoutChangeListener() {
            public void onLayoutChange(View sheetView, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                int newSheetViewHeight = sheetView.getMeasuredHeight();
                if (BottomSheetLayout.this.state != State.HIDDEN && newSheetViewHeight < BottomSheetLayout.this.currentSheetViewHeight) {
                    if (BottomSheetLayout.this.state == State.EXPANDED) {
                        BottomSheetLayout.this.setState(State.PEEKED);
                    }
                    BottomSheetLayout.this.setSheetTranslation((float) newSheetViewHeight);
                }
                BottomSheetLayout.this.currentSheetViewHeight = newSheetViewHeight;
            }
        };
        sheetView.addOnLayoutChangeListener(this.sheetViewOnLayoutChangeListener);
    }

    public void dismissSheet() {
        dismissSheet(null);
    }

    private void dismissSheet(Runnable runAfterDismissThis) {
        if (this.state == State.HIDDEN) {
            this.runAfterDismiss = null;
            return;
        }
        this.runAfterDismiss = runAfterDismissThis;
        final View sheetView = getSheetView();
        sheetView.removeOnLayoutChangeListener(this.sheetViewOnLayoutChangeListener);
        cancelCurrentAnimation();
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, SHEET_TRANSLATION, new float[]{0.0f});
        anim.setDuration(300);
        anim.setInterpolator(this.animationInterpolator);
        anim.addListener(new CancelDetectionAnimationListener() {
            public void onAnimationEnd(Animator animation) {
                if (!this.canceled) {
                    BottomSheetLayout.this.currentAnimator = null;
                    BottomSheetLayout.this.setState(State.HIDDEN);
                    BottomSheetLayout.this.setSheetLayerTypeIfEnabled(0);
                    BottomSheetLayout.this.removeView(sheetView);
                    Iterator it = BottomSheetLayout.this.onSheetDismissedListeners.iterator();
                    while (it.hasNext()) {
                        ((OnSheetDismissedListener) it.next()).onDismissed(BottomSheetLayout.this);
                    }
                    BottomSheetLayout.this.viewTransformer = null;
                    BottomSheetLayout.this.onSheetDismissedListeners.clear();
                    BottomSheetLayout.this.onSheetStateChangeListeners.clear();
                    if (BottomSheetLayout.this.runAfterDismiss != null) {
                        BottomSheetLayout.this.runAfterDismiss.run();
                        BottomSheetLayout.this.runAfterDismiss = null;
                    }
                }
            }
        });
        anim.start();
        this.currentAnimator = anim;
        this.sheetStartX = 0;
        this.sheetEndX = this.screenWidth;
    }

    public void setPeekOnDismiss(boolean peekOnDismiss2) {
        this.peekOnDismiss = peekOnDismiss2;
    }

    public boolean getPeekOnDismiss() {
        return this.peekOnDismiss;
    }

    public void setInterceptContentTouch(boolean interceptContentTouch2) {
        this.interceptContentTouch = interceptContentTouch2;
    }

    public boolean getInterceptContentTouch() {
        return this.interceptContentTouch;
    }

    public State getState() {
        return this.state;
    }

    public boolean isSheetShowing() {
        return this.state != State.HIDDEN;
    }

    public void setDefaultViewTransformer(ViewTransformer defaultViewTransformer2) {
        this.defaultViewTransformer = defaultViewTransformer2;
    }

    public void setShouldDimContentView(boolean shouldDimContentView2) {
        this.shouldDimContentView = shouldDimContentView2;
    }

    public void setUseHardwareLayerWhileAnimating(boolean useHardwareLayerWhileAnimating2) {
        this.useHardwareLayerWhileAnimating = useHardwareLayerWhileAnimating2;
    }
}
