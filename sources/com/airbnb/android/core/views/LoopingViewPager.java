package com.airbnb.android.core.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.p000v4.view.ViewPager.SimpleOnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.adapters.LoopingPagerAdapter;
import com.airbnb.android.core.utils.MemoryUtils;
import com.airbnb.android.core.utils.MiscUtils;

@Deprecated
public class LoopingViewPager extends FrameLayout {
    @BindView
    View mClickOverlay;
    @BindView
    ImageView mCurtainImageView;
    /* access modifiers changed from: private */
    public boolean mLoopingEnabled;
    MemoryUtils mMemoryUtils;
    @BindView
    ClickableViewPager mViewPager;

    public LoopingViewPager(Context context) {
        super(context);
        init(context);
    }

    public LoopingViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoopingViewPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void setViewPagerClickListener(OnClickListener onClickListener) {
        this.mViewPager.setOnClickListener(LoopingViewPager$$Lambda$1.lambdaFactory$(this, onClickListener));
    }

    static /* synthetic */ void lambda$setViewPagerClickListener$0(LoopingViewPager loopingViewPager, OnClickListener onClickListener, View v) {
        MiscUtils.fakeOverlayClick(loopingViewPager.mClickOverlay);
        onClickListener.onClick(v);
    }

    private void init(Context context) {
        ButterKnife.bind(this, LayoutInflater.from(context).inflate(C0716R.layout.looping_view_pager, this, true));
        ((CoreGraph) CoreApplication.instance(context).component()).inject(this);
        this.mViewPager.addOnPageChangeListener(new SimpleOnPageChangeListener() {
            private void hideViewPagerAndScroll(int position) {
                if (LoopingViewPager.this.mLoopingEnabled) {
                    Bitmap bitmap = MiscUtils.getBitmapFromView(LoopingViewPager.this.mMemoryUtils, LoopingViewPager.this.mViewPager);
                    if (bitmap != null) {
                        LoopingViewPager.this.mCurtainImageView.setBackground(new BitmapDrawable(LoopingViewPager.this.mViewPager.getContext().getResources(), bitmap));
                    }
                    LoopingViewPager.this.mCurtainImageView.setVisibility(0);
                    LoopingViewPager.this.mViewPager.setCurrentItem(position, false);
                    LoopingViewPager.this.mViewPager.post(LoopingViewPager$1$$Lambda$1.lambdaFactory$(this));
                }
            }

            static /* synthetic */ void lambda$hideViewPagerAndScroll$0(C62971 r2) {
                if (LoopingViewPager.this.mCurtainImageView != null) {
                    LoopingViewPager.this.mCurtainImageView.setVisibility(8);
                }
            }

            public void onPageScrollStateChanged(int state) {
                if (!LoopingViewPager.this.mLoopingEnabled) {
                    return;
                }
                if ((state == 0 || state == 2) && state == 0) {
                    int pos = LoopingViewPager.this.mViewPager.getCurrentItem();
                    if (pos <= 1) {
                        hideViewPagerAndScroll(LoopingViewPager.this.mViewPager.getAdapter().getCount() - 3);
                    } else if (pos >= LoopingViewPager.this.mViewPager.getAdapter().getCount() - 2) {
                        hideViewPagerAndScroll(2);
                    }
                }
            }
        });
    }

    public ClickableViewPager getViewPager() {
        return this.mViewPager;
    }

    public void setAdapter(LoopingPagerAdapter pagerAdapter) {
        this.mViewPager.setAdapter(pagerAdapter);
        this.mLoopingEnabled = pagerAdapter.isLoopingEnabled();
    }

    public void setPeekEnabled(boolean enabled) {
        this.mViewPager.setPageMargin(enabled ? getContext().getResources().getDimensionPixelOffset(C0716R.dimen.locations_view_pager_margin) : 0);
    }

    public LoopingPagerAdapter getAdapter() {
        return (LoopingPagerAdapter) this.mViewPager.getAdapter();
    }
}
