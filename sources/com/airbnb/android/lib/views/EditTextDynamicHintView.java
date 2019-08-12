package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p000v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.views.ClickableViewPager;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.DynamicHintPagerAdapter;
import com.airbnb.android.utils.ViewUtils;

public final class EditTextDynamicHintView extends LinearLayout {
    private DynamicHintPagerAdapter mAdapter;
    private int mHintAppearance;
    @BindView
    ClickableViewPager mHintViewPager;
    @BindView
    ImageView mNextHintButton;
    @BindView
    ImageView mPrevHintButton;
    private final OnClickListener mShowNextOnClick;
    private boolean mStopAutoScrollOnUserAction;
    /* access modifiers changed from: private */
    public boolean mUserHasSwiped;

    public EditTextDynamicHintView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mShowNextOnClick = EditTextDynamicHintView$$Lambda$1.lambdaFactory$(this);
        init(context, attrs);
    }

    public EditTextDynamicHintView(Context context) {
        this(context, null, 0);
    }

    public EditTextDynamicHintView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private void init(Context context, AttributeSet attrs) {
        ButterKnife.bind(this, LayoutInflater.from(context).inflate(C0880R.layout.edit_text_dynamic_hint_view, this, true));
        TypedArray a = context.obtainStyledAttributes(attrs, C0880R.styleable.EditTextDynamicHintView);
        this.mHintAppearance = a.getResourceId(C0880R.styleable.EditTextDynamicHintView_hintAppearance, 16973894);
        int staticHints = a.getResourceId(C0880R.styleable.EditTextDynamicHintView_staticHints, 0);
        if (staticHints > 0) {
            setHints(staticHints);
        }
        a.recycle();
    }

    public void setHints(String[] hintsArray) {
        this.mAdapter = new DynamicHintPagerAdapter(hintsArray, this.mHintAppearance, this.mShowNextOnClick);
        updateHintsViewPager();
    }

    public void setHints(int hintsArrayId) {
        this.mAdapter = new DynamicHintPagerAdapter(getContext(), hintsArrayId, this.mHintAppearance, this.mShowNextOnClick);
        updateHintsViewPager();
    }

    private void updateHintsViewPager() {
        this.mHintViewPager.setAdapter(this.mAdapter);
        this.mHintViewPager.setVisibility(0);
        this.mHintViewPager.setOnClickListener(this.mShowNextOnClick);
        this.mNextHintButton.setOnClickListener(this.mShowNextOnClick);
        this.mPrevHintButton.setOnClickListener(EditTextDynamicHintView$$Lambda$2.lambdaFactory$(this));
        this.mHintViewPager.setOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageScrollStateChanged(int state) {
                if (state == 1) {
                    EditTextDynamicHintView.this.mUserHasSwiped = true;
                }
            }

            public void onPageSelected(int position) {
                boolean canShowNext;
                boolean canShowPrev = true;
                int currIndex = EditTextDynamicHintView.this.mHintViewPager.getCurrentItem();
                if (currIndex != EditTextDynamicHintView.this.mHintViewPager.getAdapter().getCount() - 1) {
                    canShowNext = true;
                } else {
                    canShowNext = false;
                }
                if (currIndex <= 0) {
                    canShowPrev = false;
                }
                ViewUtils.setEnableIf(EditTextDynamicHintView.this.mNextHintButton, canShowNext);
                ViewUtils.setEnableIf(EditTextDynamicHintView.this.mPrevHintButton, canShowPrev);
            }
        });
        this.mHintViewPager.setCurrentItem(0);
        this.mPrevHintButton.setEnabled(false);
    }

    /* access modifiers changed from: private */
    public void scrollToPrevOrNextHint(boolean showNext) {
        int currIndex = this.mHintViewPager.getCurrentItem();
        if (showNext) {
            if (currIndex != this.mHintViewPager.getAdapter().getCount() - 1) {
                this.mHintViewPager.setCurrentItem(currIndex + 1, true);
                this.mUserHasSwiped = true;
            }
        } else if (currIndex > 0) {
            this.mHintViewPager.setCurrentItem(currIndex - 1, true);
            this.mUserHasSwiped = true;
        }
    }

    public void showDynamicHint(int hintIndex) {
        if ((!this.mStopAutoScrollOnUserAction || !this.mUserHasSwiped) && hintIndex < this.mHintViewPager.getAdapter().getCount() && this.mHintViewPager.getCurrentItem() < hintIndex) {
            this.mHintViewPager.setCurrentItem(hintIndex, true);
        }
    }

    public void setStopAutoScrollOnUserAction(boolean stopAutoScrollOnUserAction) {
        this.mStopAutoScrollOnUserAction = stopAutoScrollOnUserAction;
    }

    public int getNumHints() {
        return this.mHintViewPager.getAdapter().getCount();
    }
}
