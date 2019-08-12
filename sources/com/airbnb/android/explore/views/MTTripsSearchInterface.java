package com.airbnb.android.explore.views;

import android.graphics.Rect;
import android.view.View.OnClickListener;
import com.airbnb.android.explore.views.MTExploreMarquee.ExploreMarqueeChildListener;
import com.airbnb.android.explore.views.MTTripsSearchView.OnProgressChangedListener;

public interface MTTripsSearchInterface {
    void collapse();

    void expand();

    int getCollapsedHeight();

    Rect getDatesRect();

    int getExpandedHeight();

    Rect getGuestsRect();

    int getHeight();

    Rect getLocationRect();

    float getProgress();

    void hideClearAll(boolean z);

    boolean isAnimating();

    boolean isCollapsed();

    boolean isExpanded();

    void setBackButtonClickListener(OnClickListener onClickListener);

    void setBackgroundColor(int i);

    void setClearAllClickListener(OnClickListener onClickListener);

    void setColorAnimatedProgress(float f);

    void setDatesClickListener(OnClickListener onClickListener);

    void setExploreMarqueeChildListener(ExploreMarqueeChildListener exploreMarqueeChildListener);

    void setGuestsClickListener(OnClickListener onClickListener);

    void setGuestsText(CharSequence charSequence);

    void setGuestsText(CharSequence charSequence, CharSequence charSequence2);

    void setHeight(int i);

    void setLocationClickListener(OnClickListener onClickListener);

    void setLocationText(String str);

    void setLocationText(String str, String str2);

    void setProgressChangeListener(OnProgressChangedListener onProgressChangedListener);

    void setTimeText(CharSequence charSequence);

    void setTimeText(CharSequence charSequence, CharSequence charSequence2);

    void showBackButtonInsteadOfSearchIcon(boolean z);

    void showBottomDivider(boolean z);
}
