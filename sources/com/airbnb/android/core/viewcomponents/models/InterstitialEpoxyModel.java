package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.ExplorePromotionStyle;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.Interstitial;
import com.airbnb.p027n2.components.InterstitialStyleApplier;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.paris.Paris;

public abstract class InterstitialEpoxyModel extends AirEpoxyModel<Interstitial> {
    OnClickListener buttonClickListener;
    CharSequence buttonText;
    int buttonTextRes;
    CharSequence caption;
    int captionRes;
    int jellyFishPallete = 1;
    ExplorePromotionStyle style = ExplorePromotionStyle.BabuBackground;
    CharSequence text;
    int textRes;
    boolean withPadding = true;

    public void bind(Interstitial view) {
        super.bind(view);
        Context context = view.getContext();
        CharSequence actualText = this.textRes != 0 ? context.getString(this.textRes) : this.text;
        CharSequence actualCaption = this.captionRes != 0 ? context.getString(this.captionRes) : this.caption;
        CharSequence actualButtonText = this.buttonTextRes != 0 ? context.getString(this.buttonTextRes) : this.buttonText;
        view.setText(actualText);
        view.setCaption(actualCaption);
        view.setButtonText(actualButtonText);
        view.setButtonClickListener(this.buttonClickListener);
        view.setPaddingEnabled(this.withPadding);
        InterstitialStyleApplier styleApplier = Paris.style(view);
        switch (this.style) {
            case InverseBackground:
                styleApplier.textView().apply(R.style.n2_TitleText3);
                styleApplier.captionView().apply(R.style.n2_LargeText);
                styleApplier.button().apply(R.style.n2_InterstitialButton_White);
                view.setJellyfishPallete(null, false);
                return;
            default:
                styleApplier.textView().apply(R.style.n2_TitleText3_Inverse);
                styleApplier.captionView().apply(R.style.n2_LargeText_Inverse);
                styleApplier.button().apply(R.style.n2_InterstitialButton);
                view.setJellyfishPallete(Integer.valueOf(this.jellyFishPallete), false);
                return;
        }
    }

    public void unbind(Interstitial view) {
        super.unbind(view);
        view.setButtonClickListener(null);
    }

    public AirEpoxyModel<Interstitial> reset() {
        this.withPadding = true;
        this.jellyFishPallete = 1;
        return super.reset();
    }

    public int getDividerViewType() {
        return 4;
    }
}
