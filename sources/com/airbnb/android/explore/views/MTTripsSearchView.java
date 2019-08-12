package com.airbnb.android.explore.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.os.Parcelable;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.animation.PathInterpolatorCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.views.MTExploreMarquee.ExploreMarqueeChildListener;
import com.airbnb.android.utils.animation.ManualValueAnimator;
import com.airbnb.android.utils.animation.ManualValueAnimator.Set;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.TextUtil;
import com.airbnb.p027n2.utils.ViewLibUtils;
import icepick.State;

public class MTTripsSearchView extends RelativeLayout implements MTTripsSearchInterface {
    public static final float SEARCH_BOX_SCALE_FACTOR = 1.03f;
    private static final float TRANSITION_DURATION_SCALE = 1.0f;
    @BindView
    ImageButton actionButton;
    private final Set animators;
    private OnClickListener backButtonClickListener;
    @BindView
    View bottomDivider;
    @BindView
    AirTextView bullet1;
    @BindView
    AirTextView bullet2;
    @BindView
    AirTextView clearAllButton;
    @State
    boolean clearAllHidden;
    /* access modifiers changed from: private */
    public ObjectAnimator collapseAnimator;
    @BindView
    ImageButton collapseButton;
    private final Set colorAnimators;
    @BindView
    MTSearchInputField datesRow;
    @BindView
    AirTextView datesView;
    /* access modifiers changed from: private */
    public ObjectAnimator expandAnimator;
    private ExploreMarqueeChildListener exploreMarqueeChildListener;
    private boolean firstLayout;
    @BindView
    MTSearchInputField guestsRow;
    @BindView
    AirTextView guestsView;
    @State
    float heightProgress;
    @BindDimen
    int iconSpace;
    @BindView
    MTSearchInputField locationRow;
    @BindView
    AirTextView locationView;
    @BindColor
    int mainTextColor;
    @State
    float progress;
    private OnProgressChangedListener progressChangedListener;
    private final Runnable requestLayoutRunnable;
    @BindViews
    View[] searchBoxExtras;
    @BindView
    LinearLayout searchContainer;
    @BindDimen
    int searchFieldsMarginTop;
    @BindColor
    int whiteColor;

    public interface OnProgressChangedListener {
        void onProgressChanged(float f);
    }

    public MTTripsSearchView(Context context) {
        super(context);
        this.animators = new Set();
        this.colorAnimators = new Set();
        this.requestLayoutRunnable = MTTripsSearchView$$Lambda$1.lambdaFactory$(this);
        this.firstLayout = true;
        init();
    }

    public MTTripsSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.animators = new Set();
        this.colorAnimators = new Set();
        this.requestLayoutRunnable = MTTripsSearchView$$Lambda$2.lambdaFactory$(this);
        this.firstLayout = true;
        init();
    }

    public MTTripsSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.animators = new Set();
        this.colorAnimators = new Set();
        this.requestLayoutRunnable = MTTripsSearchView$$Lambda$3.lambdaFactory$(this);
        this.firstLayout = true;
        init();
    }

    private void init() {
        inflate(getContext(), C0857R.layout.trips_search_view, this);
        ButterKnife.bind((View) this);
        this.progress = 0.0f;
        this.actionButton.setOnClickListener(MTTripsSearchView$$Lambda$4.lambdaFactory$(this));
        this.collapseButton.setOnClickListener(MTTripsSearchView$$Lambda$5.lambdaFactory$(this));
        this.searchContainer.setOnClickListener(MTTripsSearchView$$Lambda$6.lambdaFactory$(this));
        initAnimators();
        initColorAnimators();
    }

    static /* synthetic */ void lambda$init$0(MTTripsSearchView mTTripsSearchView, View v) {
        if (!mTTripsSearchView.isCollapsed()) {
            return;
        }
        if (mTTripsSearchView.backButtonClickListener != null) {
            mTTripsSearchView.backButtonClickListener.onClick(v);
        } else {
            mTTripsSearchView.expand();
        }
    }

    static /* synthetic */ void lambda$init$1(MTTripsSearchView mTTripsSearchView, View v) {
        if (!mTTripsSearchView.isCollapsed()) {
            mTTripsSearchView.collapse();
        }
    }

    static /* synthetic */ void lambda$init$2(MTTripsSearchView mTTripsSearchView, View v) {
        if (mTTripsSearchView.isCollapsed()) {
            mTTripsSearchView.expand();
        }
    }

    private void initColorAnimators() {
        this.colorAnimators.add(new ManualValueAnimator(0.0f, 1.0f).runFrom(0.0f, 1.0f).updateListener(MTTripsSearchView$$Lambda$7.lambdaFactory$(this)));
        this.colorAnimators.add(new ManualValueAnimator(255.0f, 50.0f).runFrom(0.0f, 1.0f).updateListener(MTTripsSearchView$$Lambda$8.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$initColorAnimators$3(MTTripsSearchView mTTripsSearchView, float fraction, float value) {
        int mainToWhite = ((Integer) new ArgbEvaluator().evaluate(value, Integer.valueOf(mTTripsSearchView.mainTextColor), Integer.valueOf(mTTripsSearchView.whiteColor))).intValue();
        mTTripsSearchView.locationRow.getIcon().setColorFilter(mainToWhite);
        mTTripsSearchView.datesRow.getIcon().setColorFilter(mainToWhite);
        mTTripsSearchView.guestsRow.getIcon().setColorFilter(mainToWhite);
        mTTripsSearchView.locationRow.getTitleTextView().setTextColor(mainToWhite);
        mTTripsSearchView.datesRow.getTitleTextView().setTextColor(mainToWhite);
        mTTripsSearchView.guestsRow.getTitleTextView().setTextColor(mainToWhite);
        mTTripsSearchView.locationView.setTextColor(mainToWhite);
        mTTripsSearchView.datesView.setTextColor(mainToWhite);
        mTTripsSearchView.guestsView.setTextColor(mainToWhite);
        mTTripsSearchView.bullet1.setTextColor(mainToWhite);
        mTTripsSearchView.bullet2.setTextColor(mainToWhite);
        mTTripsSearchView.actionButton.setColorFilter(mainToWhite);
        mTTripsSearchView.collapseButton.setColorFilter(mainToWhite);
        mTTripsSearchView.clearAllButton.setTextColor(mainToWhite);
    }

    static /* synthetic */ void lambda$initColorAnimators$4(MTTripsSearchView mTTripsSearchView, float fraction, float value) {
        mTTripsSearchView.locationRow.getBackgroundDrawable().setAlpha((int) value);
        mTTripsSearchView.datesRow.getBackgroundDrawable().setAlpha((int) value);
        mTTripsSearchView.guestsRow.getBackgroundDrawable().setAlpha((int) value);
    }

    public void setColorAnimatedProgress(float fraction) {
        this.colorAnimators.setAnimatedFraction(fraction);
    }

    private void initAnimators() {
        this.animators.add(new ManualValueAnimator(0.0f, 1.0f).updateListener(MTTripsSearchView$$Lambda$9.lambdaFactory$(this)));
        int clearAllCollapseTranslation = ViewLibUtils.dpToPx(getContext(), 18.0f);
        int datesRowTranslation = ViewLibUtils.dpToPx(getContext(), 50.0f);
        int guestsRowTranslation = ViewLibUtils.dpToPx(getContext(), 46.0f);
        this.animators.add(new ManualValueAnimator(0.0f, 1.0f).runFrom(0.5f, 0.9f).updateListener(MTTripsSearchView$$Lambda$10.lambdaFactory$(this)));
        this.animators.add(new ManualValueAnimator((float) (-clearAllCollapseTranslation), 0.0f).runFrom(0.4f, 1.0f).updateListener(MTTripsSearchView$$Lambda$11.lambdaFactory$(this)));
        this.animators.add(new ManualValueAnimator((float) (-datesRowTranslation), 0.0f).runFrom(0.0f, 1.0f).updateListener(MTTripsSearchView$$Lambda$12.lambdaFactory$(this)));
        this.animators.add(new ManualValueAnimator((float) (-guestsRowTranslation), 0.0f).runFrom(0.3f, 1.0f).updateListener(MTTripsSearchView$$Lambda$13.lambdaFactory$(this)));
        this.animators.add(new ManualValueAnimator(0.0f, 1.0f).runFrom(0.3f, 0.6f).updateListener(MTTripsSearchView$$Lambda$14.lambdaFactory$(this)));
        this.animators.add(new ManualValueAnimator(0.0f, 1.0f).runFrom(0.7f, 1.0f).updateListener(MTTripsSearchView$$Lambda$15.lambdaFactory$(this)));
        this.animators.add(new ManualValueAnimator(0.0f, (float) this.searchFieldsMarginTop).runFrom(0.0f, 1.0f).updateListener(MTTripsSearchView$$Lambda$16.lambdaFactory$(this)));
        this.animators.add(new ManualValueAnimator(1.0f, 0.0f).runFrom(0.0f, 0.5f).updateListener(MTTripsSearchView$$Lambda$17.lambdaFactory$(this)));
        this.animators.add(new ManualValueAnimator(1.0f, 0.0f).runFrom(0.15f, 0.35f).updateListener(MTTripsSearchView$$Lambda$18.lambdaFactory$(this)));
        this.animators.add(new ManualValueAnimator(0.0f, 1.0f).runFrom(0.1f, 0.25f).updateListener(MTTripsSearchView$$Lambda$19.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$initAnimators$5(MTTripsSearchView mTTripsSearchView, float fraction, float value) {
        mTTripsSearchView.heightProgress = value;
        if (ViewCompat.isInLayout(mTTripsSearchView)) {
            mTTripsSearchView.post(mTTripsSearchView.requestLayoutRunnable);
        } else {
            mTTripsSearchView.requestLayout();
        }
    }

    static /* synthetic */ void lambda$initAnimators$6(MTTripsSearchView mTTripsSearchView, float fraction, float value) {
        mTTripsSearchView.clearAllButton.setAlpha(value);
        mTTripsSearchView.collapseButton.setAlpha(value);
        mTTripsSearchView.setClearAllButtonVisibility();
        ViewLibUtils.setVisibleIf(mTTripsSearchView.collapseButton, !mTTripsSearchView.isCollapsed());
        mTTripsSearchView.setRowsClickability(mTTripsSearchView.isExpanded());
    }

    static /* synthetic */ void lambda$initAnimators$7(MTTripsSearchView mTTripsSearchView, float fraction, float value) {
        mTTripsSearchView.clearAllButton.setTranslationY(value);
        mTTripsSearchView.collapseButton.setTranslationY(value);
    }

    static /* synthetic */ void lambda$initAnimators$12(MTTripsSearchView mTTripsSearchView, float fraction, float value) {
        mTTripsSearchView.searchContainer.setTranslationY(value);
        mTTripsSearchView.locationRow.setTranslationY(value - ((float) mTTripsSearchView.searchFieldsMarginTop));
        mTTripsSearchView.invalidate();
    }

    static /* synthetic */ void lambda$initAnimators$13(MTTripsSearchView mTTripsSearchView, float fraction, float value) {
        mTTripsSearchView.searchContainer.setAlpha(value);
        mTTripsSearchView.locationRow.getIcon().setAlpha(1.0f - value);
    }

    public int getExpandedHeight() {
        return this.guestsRow.getBottom() + ViewLibUtils.getBottomMargin(this.guestsRow);
    }

    public boolean isCollapsed() {
        return this.progress <= 0.001f;
    }

    public boolean isExpanded() {
        return this.progress >= 0.999f;
    }

    public int getCollapsedHeight() {
        return ViewLibUtils.getTotalHeight(this.searchContainer);
    }

    public Parcelable onSaveInstanceState() {
        return IcepickWrapper.saveInstanceState(this, super.onSaveInstanceState());
    }

    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(IcepickWrapper.restoreInstanceState(this, state));
        setProgress(this.progress);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int startingHeight = getCollapsedHeight();
        int endHeight = getExpandedHeight();
        chompSearchViewsIfNeeded();
        setMeasuredDimension(getMeasuredWidth(), (int) (((float) startingHeight) + (this.heightProgress * ((float) (endHeight - startingHeight)))));
    }

    private void chompSearchViewsIfNeeded() {
        boolean chompGuests;
        boolean z;
        boolean z2;
        boolean chompDates;
        boolean z3;
        boolean z4 = true;
        int availableWidth = (this.searchContainer.getMeasuredWidth() - this.searchContainer.getPaddingLeft()) - this.searchContainer.getPaddingRight();
        int usedWidth = 0;
        for (int i = this.searchContainer.getChildCount() - 1; i >= 0; i--) {
            usedWidth += ViewLibUtils.getTotalMeasuredWidth(this.searchContainer.getChildAt(i));
        }
        if (usedWidth >= availableWidth) {
            chompGuests = true;
        } else {
            chompGuests = false;
        }
        int usedWidth2 = usedWidth - (ViewLibUtils.getTotalMeasuredWidth(this.guestsView) + ViewLibUtils.getTotalMeasuredWidth(this.bullet2));
        AirTextView airTextView = this.guestsView;
        if (!chompGuests) {
            z = true;
        } else {
            z = false;
        }
        ViewLibUtils.setVisibleIf(airTextView, z);
        AirTextView airTextView2 = this.bullet2;
        if (chompGuests || TextUtils.isEmpty(this.guestsView.getText())) {
            z2 = false;
        } else {
            z2 = true;
        }
        ViewLibUtils.setVisibleIf(airTextView2, z2);
        if (usedWidth2 >= availableWidth) {
            chompDates = true;
        } else {
            chompDates = false;
        }
        AirTextView airTextView3 = this.datesView;
        if (!chompDates) {
            z3 = true;
        } else {
            z3 = false;
        }
        ViewLibUtils.setVisibleIf(airTextView3, z3);
        AirTextView airTextView4 = this.bullet1;
        if (chompDates || TextUtils.isEmpty(this.datesView.getText())) {
            z4 = false;
        }
        ViewLibUtils.setVisibleIf(airTextView4, z4);
    }

    public void showBottomDivider(boolean show) {
        ViewLibUtils.setVisibleIf(this.bottomDivider, show);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (this.firstLayout) {
            this.firstLayout = false;
            setProgress(this.progress);
        }
        this.bottomDivider.layout(0, getBottom() - this.bottomDivider.getHeight(), this.bottomDivider.getWidth(), getBottom());
        setupActionButtonTouchDelegate();
    }

    public void setHeight(int height) {
        setProgress(ViewLibUtils.clamp(ViewLibUtils.clamp(1.0f - (((float) (getExpandedHeight() - height)) / ((float) (getExpandedHeight() - getCollapsedHeight()))), 0.0f, 1.0f), 0.0f, 1.0f));
    }

    private void setProgress(float progress2) {
        if (progress2 < 0.0f || progress2 > 1.0f) {
            throw new IllegalArgumentException("Progress must be between 0 and 1");
        }
        if (isAnimating()) {
            setLayerType(2, null);
        } else if (getLayerType() != 0) {
            setLayerType(0, null);
        }
        if (!isAnimating() && this.exploreMarqueeChildListener != null && ((double) progress2) > 0.2d) {
            this.exploreMarqueeChildListener.setBabuMode(true);
        }
        if (!(this.progressChangedListener == null || this.progress == progress2)) {
            this.progressChangedListener.onProgressChanged(progress2);
        }
        this.progress = progress2;
        this.searchContainer.setClickable(isCollapsed());
        setClickable(isCollapsed());
        this.animators.setAnimatedFraction(progress2);
    }

    public float getProgress() {
        return this.progress;
    }

    public void collapse() {
        collapse(true);
    }

    private void collapse(boolean animate) {
        if (!isCollapsed()) {
            if (animate) {
                if (this.expandAnimator != null) {
                    this.expandAnimator.cancel();
                }
                if (this.collapseAnimator == null) {
                    this.collapseAnimator = ObjectAnimator.ofFloat(this, "progress", new float[]{this.progress, 0.0f}).setDuration((long) (this.progress * 200.0f));
                    this.collapseAnimator.setInterpolator(PathInterpolatorCompat.create(0.0f, 0.25f, 0.25f, 1.0f));
                    this.collapseAnimator.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animation) {
                            MTTripsSearchView.this.collapseAnimator = null;
                        }
                    });
                    this.collapseAnimator.start();
                }
            } else {
                setProgress(0.0f);
            }
            setRowsClickability(false);
        }
    }

    public void expand() {
        expand(true);
    }

    private void expand(boolean animate) {
        if (!isExpanded()) {
            if (animate) {
                if (this.collapseAnimator != null) {
                    this.collapseAnimator.cancel();
                }
                if (this.expandAnimator == null) {
                    this.expandAnimator = ObjectAnimator.ofFloat(this, "progress", new float[]{this.progress, 1.0f}).setDuration((long) ((1.0f - this.progress) * 400.0f));
                    this.expandAnimator.setInterpolator(PathInterpolatorCompat.create(0.0f, 0.25f, 0.25f, 1.0f));
                    this.expandAnimator.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animation) {
                            MTTripsSearchView.this.expandAnimator = null;
                        }
                    });
                    this.expandAnimator.start();
                }
                if (this.exploreMarqueeChildListener != null) {
                    this.exploreMarqueeChildListener.setBabuMode(true);
                }
            } else {
                setProgress(1.0f);
            }
            setRowsClickability(true);
        }
    }

    private void setRowsClickability(boolean clickable) {
        this.locationRow.setClickable(clickable);
        this.datesRow.setClickable(clickable);
        this.guestsRow.setClickable(clickable);
    }

    public void setLocationText(String text) {
        setLocationText(text, text);
    }

    public void setTimeText(CharSequence text) {
        setTimeText(text, text);
    }

    public void setGuestsText(CharSequence text) {
        setGuestsText(text, text);
    }

    public void setLocationText(String textCollapsed, String textExpanded) {
        this.locationView.setText(TextUtil.trimTextToFirstComma(textCollapsed));
        this.locationRow.setTitle(TextUtil.trimTextToFirstComma(textExpanded));
    }

    public void setTimeText(CharSequence textCollapsed, CharSequence textExpanded) {
        this.datesView.setText(textCollapsed);
        this.datesRow.setTitle(textExpanded);
    }

    public void setGuestsText(CharSequence textCollapsed, CharSequence textExpanded) {
        this.guestsView.setText(textCollapsed);
        this.guestsRow.setTitle(textExpanded);
    }

    public void setBackButtonClickListener(OnClickListener clickListener) {
        this.backButtonClickListener = clickListener;
    }

    public void setClearAllClickListener(OnClickListener clickListener) {
        this.clearAllButton.setOnClickListener(clickListener);
    }

    public void setLocationClickListener(OnClickListener clickListener) {
        this.locationRow.setOnClickListener(clickListener);
    }

    public void setDatesClickListener(OnClickListener clickListener) {
        this.datesRow.setOnClickListener(clickListener);
    }

    public void setGuestsClickListener(OnClickListener clickListener) {
        this.guestsRow.setOnClickListener(clickListener);
    }

    public Rect getLocationRect() {
        return ViewLibUtils.getViewRect(this.locationRow);
    }

    public Rect getDatesRect() {
        return ViewLibUtils.getViewRect(this.datesRow);
    }

    public Rect getGuestsRect() {
        return ViewLibUtils.getViewRect(this.guestsRow);
    }

    public void showBackButtonInsteadOfSearchIcon(boolean show) {
        this.actionButton.setImageResource(show ? C0857R.C0858drawable.n2_ic_chevron_left : C0857R.C0858drawable.ic_trips_search_explore);
    }

    public void setProgressChangeListener(OnProgressChangedListener progressChangedListener2) {
        this.progressChangedListener = progressChangedListener2;
    }

    public void hideClearAll(boolean hide) {
        this.clearAllHidden = hide;
        setClearAllButtonVisibility();
    }

    public boolean isAnimating() {
        return (this.expandAnimator == null && this.collapseAnimator == null) ? false : true;
    }

    public void setExploreMarqueeChildListener(ExploreMarqueeChildListener exploreMarqueeChildListener2) {
        this.exploreMarqueeChildListener = exploreMarqueeChildListener2;
    }

    private void setClearAllButtonVisibility() {
        ViewLibUtils.setVisibleIf(this.clearAllButton, !this.clearAllHidden && !isCollapsed());
    }

    private void setupActionButtonTouchDelegate() {
        Rect delegateArea = new Rect();
        this.actionButton.getHitRect(delegateArea);
        int delegatePadding = this.iconSpace;
        delegateArea.top -= delegatePadding;
        delegateArea.left -= delegatePadding;
        delegateArea.right += delegatePadding;
        delegateArea.bottom += delegatePadding;
        ((View) this.actionButton.getParent()).setTouchDelegate(new TouchDelegate(delegateArea, this.actionButton));
    }
}
