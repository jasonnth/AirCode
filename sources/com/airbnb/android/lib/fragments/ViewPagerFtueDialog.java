package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentStatePagerAdapter;
import android.support.p000v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.airbnb.android.core.fragments.AirDialogFragment;
import com.airbnb.android.core.views.ClickableViewPager;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.StickyButton;
import com.airbnb.p027n2.primitives.DotsCounter;

public abstract class ViewPagerFtueDialog extends AirDialogFragment {
    protected int mCurrPage = 0;
    protected OnPageChangeListener mOnPageChangeListener;

    private class FtueAdapter extends FragmentStatePagerAdapter {
        public FtueAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            return ViewPagerFtueDialog.this.forStep(position);
        }

        public int getCount() {
            return ViewPagerFtueDialog.this.getNumPages();
        }
    }

    public abstract Fragment forStep(int i);

    public abstract int getNumPages();

    public abstract OnClickListener getStickyButtonClickListener();

    public abstract String getStickyButtonTitle();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, C0880R.C0885style.Theme_Airbnb_DialogNoTitle);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C0880R.layout.view_pager_ftue_dialog, container, false);
        StickyButton stickyButton = (StickyButton) v.findViewById(C0880R.C0882id.sticky_button);
        stickyButton.setVisibility(0);
        stickyButton.setOnClickListener(getStickyButtonClickListener());
        stickyButton.setTitle(getStickyButtonTitle());
        ClickableViewPager viewPager = (ClickableViewPager) v.findViewById(C0880R.C0882id.pager_dialog_ftue_content);
        viewPager.setOnClickListener(ViewPagerFtueDialog$$Lambda$1.lambdaFactory$(this, viewPager));
        final DotsCounter dots = (DotsCounter) v.findViewById(C0880R.C0882id.dots_counter);
        int numDots = getNumPages();
        if (numDots > 1) {
            dots.setVisibility(0);
            dots.setNumDots(numDots);
            viewPager.setOnPageChangeListener(new OnPageChangeListener() {
                public void onPageSelected(int position) {
                    ViewPagerFtueDialog.this.goToNextFtuePage(position, dots);
                    if (ViewPagerFtueDialog.this.mOnPageChangeListener != null) {
                        ViewPagerFtueDialog.this.mOnPageChangeListener.onPageSelected(position);
                    }
                }

                public void onPageScrollStateChanged(int state) {
                    if (ViewPagerFtueDialog.this.mOnPageChangeListener != null) {
                        ViewPagerFtueDialog.this.mOnPageChangeListener.onPageScrollStateChanged(state);
                    }
                }

                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    if (ViewPagerFtueDialog.this.mOnPageChangeListener != null) {
                        ViewPagerFtueDialog.this.mOnPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    }
                }
            });
        } else {
            dots.setNumDots(0);
            dots.setVisibility(8);
        }
        viewPager.setAdapter(new FtueAdapter(getChildFragmentManager()));
        return v;
    }

    static /* synthetic */ void lambda$onCreateView$0(ViewPagerFtueDialog viewPagerFtueDialog, ClickableViewPager viewPager, View view) {
        if (viewPagerFtueDialog.mCurrPage != viewPagerFtueDialog.getLastPageIndex()) {
            viewPager.setCurrentItem(viewPagerFtueDialog.mCurrPage + 1, true);
        }
    }

    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(getResources().getDimensionPixelSize(C0880R.dimen.dialog_fragment_width), -2);
    }

    /* access modifiers changed from: protected */
    public int getLastPageIndex() {
        return getNumPages() - 1;
    }

    /* access modifiers changed from: private */
    public void goToNextFtuePage(int position, DotsCounter mDots) {
        mDots.setSelectedDot(position);
        this.mCurrPage = position;
    }
}
