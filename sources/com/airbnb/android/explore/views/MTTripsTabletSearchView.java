package com.airbnb.android.explore.views;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.views.MTExploreMarquee.ExploreMarqueeChildListener;
import com.airbnb.android.explore.views.MTTripsSearchView.OnProgressChangedListener;
import com.airbnb.android.utils.animation.ManualValueAnimator;
import com.airbnb.android.utils.animation.ManualValueAnimator.Set;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class MTTripsTabletSearchView extends FrameLayout implements MTTripsSearchInterface {
    private final Set colorAnimators = new Set();
    @BindView
    MTSearchInputField datesRow;
    @BindColor
    int foggyColor;
    @BindView
    MTSearchInputField guestsRow;
    @BindView
    MTSearchInputField locationRow;
    @BindColor
    int mainTextColor;
    @BindView
    View searchFieldsContainer;
    @BindView
    View separator1;
    @BindView
    View separator2;
    @BindColor
    int white60Color;
    @BindColor
    int whiteColor;

    public MTTripsTabletSearchView(Context context) {
        super(context);
        init();
    }

    public MTTripsTabletSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MTTripsTabletSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C0857R.layout.trips_tablet_search_view, this);
        ButterKnife.bind((View) this);
        initColorAnimators();
        this.locationRow.getBackgroundDrawable().setAlpha(0);
        this.guestsRow.getBackgroundDrawable().setAlpha(0);
        this.datesRow.getBackgroundDrawable().setAlpha(0);
    }

    public void collapse() {
    }

    public boolean isCollapsed() {
        return false;
    }

    public int getCollapsedHeight() {
        return getHeight();
    }

    public void expand() {
    }

    public boolean isExpanded() {
        return false;
    }

    public boolean isAnimating() {
        return false;
    }

    public void setHeight(int height) {
    }

    public int getExpandedHeight() {
        return getHeight();
    }

    public float getProgress() {
        return 1.0f;
    }

    public void showBottomDivider(boolean show) {
    }

    public void setColorAnimatedProgress(float fraction) {
        this.colorAnimators.setAnimatedFraction(fraction);
    }

    private void initColorAnimators() {
        this.colorAnimators.add(new ManualValueAnimator(0.0f, 1.0f).runFrom(0.0f, 1.0f).updateListener(MTTripsTabletSearchView$$Lambda$1.lambdaFactory$(this)));
        this.colorAnimators.add(new ManualValueAnimator(255.0f, 50.0f).runFrom(0.0f, 1.0f).updateListener(MTTripsTabletSearchView$$Lambda$2.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$initColorAnimators$0(MTTripsTabletSearchView mTTripsTabletSearchView, float fraction, float value) {
        int mainToWhite = ((Integer) new ArgbEvaluator().evaluate(value, Integer.valueOf(mTTripsTabletSearchView.mainTextColor), Integer.valueOf(mTTripsTabletSearchView.whiteColor))).intValue();
        int foggyToWhite60 = ((Integer) new ArgbEvaluator().evaluate(value, Integer.valueOf(mTTripsTabletSearchView.foggyColor), Integer.valueOf(mTTripsTabletSearchView.white60Color))).intValue();
        mTTripsTabletSearchView.locationRow.getIcon().setColorFilter(mainToWhite);
        mTTripsTabletSearchView.datesRow.getIcon().setColorFilter(mainToWhite);
        mTTripsTabletSearchView.guestsRow.getIcon().setColorFilter(mainToWhite);
        mTTripsTabletSearchView.locationRow.getTitleTextView().setTextColor(mainToWhite);
        mTTripsTabletSearchView.datesRow.getTitleTextView().setTextColor(mainToWhite);
        mTTripsTabletSearchView.guestsRow.getTitleTextView().setTextColor(mainToWhite);
        mTTripsTabletSearchView.separator1.setBackgroundColor(foggyToWhite60);
        mTTripsTabletSearchView.separator2.setBackgroundColor(foggyToWhite60);
    }

    public void setLocationText(String text) {
        this.locationRow.setTitle(text);
    }

    public void setTimeText(CharSequence text) {
        this.datesRow.setTitle(text);
    }

    public void setGuestsText(CharSequence text) {
        this.guestsRow.setTitle(text);
    }

    public void setLocationText(String textCollapsed, String textExpanded) {
        setLocationText(textExpanded);
    }

    public void setTimeText(CharSequence textCollapsed, CharSequence textExpanded) {
        setTimeText(textExpanded);
    }

    public void setGuestsText(CharSequence textCollapsed, CharSequence textExpanded) {
        setTimeText(textExpanded);
    }

    public void setBackButtonClickListener(OnClickListener clickListener) {
    }

    public void setClearAllClickListener(OnClickListener clickListener) {
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
        return getRowRect(this.locationRow);
    }

    public Rect getDatesRect() {
        return getRowRect(this.datesRow);
    }

    public Rect getGuestsRect() {
        return getRowRect(this.guestsRow);
    }

    private Rect getRowRect(View view) {
        Rect rect = ViewLibUtils.getViewRectWithMargins(view);
        rect.offset(ViewLibUtils.getLeftMargin(this.searchFieldsContainer), ViewLibUtils.getTopMargin(this.searchFieldsContainer));
        return rect;
    }

    public void setExploreMarqueeChildListener(ExploreMarqueeChildListener exploreMarqueeChildListener) {
    }

    public void showBackButtonInsteadOfSearchIcon(boolean show) {
    }

    public void setProgressChangeListener(OnProgressChangedListener progressChangedListener) {
        progressChangedListener.onProgressChanged(0.0f);
    }

    public void hideClearAll(boolean hide) {
    }
}
