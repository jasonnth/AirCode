package com.airbnb.android.identity;

import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.fragments.CountryCodeSelectionFragment.CountryCodeSelectionStyle;
import com.airbnb.android.core.views.PhoneNumberInputSheet;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.KickerMarquee;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee.Style;

public enum IdentityStyle {
    BABU(R.color.n2_default_sheet_background, C6533R.color.white, C6533R.C6534drawable.profile_photo_placeholder, C6533R.C6534drawable.profile_photo_add, Style.BABU, KickerMarquee.Style.BABU, PhoneNumberInputSheet.Style.BABU, SheetInputText.Style.BABU, C6533R.color.white, C6533R.string.read_how_it_works, true, true, false, true, false, true, CountryCodeSelectionStyle.BABU),
    WHITE(C6533R.color.white, R.color.n2_black_20, C6533R.C6534drawable.profile_photo_placeholder_black, C6533R.C6534drawable.profile_photo_add_black, Style.WHITE, KickerMarquee.Style.WHITE, PhoneNumberInputSheet.Style.WHITE, SheetInputText.Style.WHITE, C6533R.color.black, C6533R.string.read_how_it_works_babu, false, false, true, false, true, false, CountryCodeSelectionStyle.WHITE);
    
    final boolean babuNextButtonVisible;
    final int backgroundColorRes;
    final CountryCodeSelectionStyle countryCodeSelectionStyle;
    final boolean hasJellyFish;
    final boolean hasSheetStates;
    final int imageTint;
    final SheetInputText.Style inputStyle;
    final KickerMarquee.Style kickerMarqueeStyle;
    final int learnMoreTextRes;
    final Style marqueeStyle;
    final PhoneNumberInputSheet.Style phoneInputStyle;
    final int photoAddPlaceholderRes;
    final int photoBorderColorRes;
    final int photoPlaceholderRes;
    final boolean secondaryButtonVisible;
    final boolean secondaryButtonWhiteVisible;
    final boolean whiteNextButtonVisible;

    private IdentityStyle(int backgroundColorRes2, int photoBorderColorRes2, int photoPlaceholderRes2, int photoAddPlaceholderRes2, Style marqueeStyle2, KickerMarquee.Style kickerMarqueeStyle2, PhoneNumberInputSheet.Style phoneInputStyle2, SheetInputText.Style inputStyle2, int imageTint2, int learnMoreTextRes2, boolean hasJellyFish2, boolean secondaryButtonVisible2, boolean secondaryButtonWhiteVisible2, boolean babuNextButtonVisible2, boolean whiteNextButtonVisible2, boolean hasSheetStates2, CountryCodeSelectionStyle countryCodeSelectionStyle2) {
        this.backgroundColorRes = backgroundColorRes2;
        this.photoBorderColorRes = photoBorderColorRes2;
        this.photoPlaceholderRes = photoPlaceholderRes2;
        this.photoAddPlaceholderRes = photoAddPlaceholderRes2;
        this.marqueeStyle = marqueeStyle2;
        this.kickerMarqueeStyle = kickerMarqueeStyle2;
        this.phoneInputStyle = phoneInputStyle2;
        this.inputStyle = inputStyle2;
        this.imageTint = imageTint2;
        this.learnMoreTextRes = learnMoreTextRes2;
        this.hasJellyFish = hasJellyFish2;
        this.secondaryButtonVisible = secondaryButtonVisible2;
        this.secondaryButtonWhiteVisible = secondaryButtonWhiteVisible2;
        this.babuNextButtonVisible = babuNextButtonVisible2;
        this.whiteNextButtonVisible = whiteNextButtonVisible2;
        this.hasSheetStates = hasSheetStates2;
        this.countryCodeSelectionStyle = countryCodeSelectionStyle2;
    }

    public static IdentityStyle getStyle(VerificationFlow flow) {
        return flow.isWhiteBackground() ? WHITE : BABU;
    }
}
