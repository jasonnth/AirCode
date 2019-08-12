package com.airbnb.android.lib.identity.psb;

import com.airbnb.android.lib.C0880R;
import com.airbnb.n2.R;
import com.airbnb.p027n2.collections.BaseSelectionView;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee.Style;

public enum GuestProfilesStyle {
    BABU(R.color.n2_default_sheet_background, true, Style.BABU, SheetInputText.Style.BABU, BaseSelectionView.Style.BABU, true, false),
    WHITE(C0880R.color.white, false, Style.WHITE, SheetInputText.Style.WHITE, BaseSelectionView.Style.WHITE, false, true);
    
    final int backgroundColorRes;
    final boolean bookingNextButtonVisible;
    final boolean hasJellyFish;
    final SheetInputText.Style inputStyle;
    final Style marqueeStyle;
    final boolean nextButtonVisible;
    final BaseSelectionView.Style selectionViewStyle;

    private GuestProfilesStyle(int backgroundColorRes2, boolean hasJellyFish2, Style marqueeStyle2, SheetInputText.Style inputStyle2, BaseSelectionView.Style selectionViewStyle2, boolean nextButtonVisible2, boolean bookingNextButtonVisible2) {
        this.backgroundColorRes = backgroundColorRes2;
        this.hasJellyFish = hasJellyFish2;
        this.marqueeStyle = marqueeStyle2;
        this.inputStyle = inputStyle2;
        this.selectionViewStyle = selectionViewStyle2;
        this.nextButtonVisible = nextButtonVisible2;
        this.bookingNextButtonVisible = bookingNextButtonVisible2;
    }

    public static GuestProfilesStyle getStyle(boolean isP4Redesign) {
        return isP4Redesign ? WHITE : BABU;
    }
}
