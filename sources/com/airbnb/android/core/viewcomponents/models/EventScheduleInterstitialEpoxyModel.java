package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.EventScheduleInterstitial;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class EventScheduleInterstitialEpoxyModel extends AirEpoxyModel<EventScheduleInterstitial> {
    CharSequence addressText;
    int addressTextRes;
    OnClickListener buttonClickListener;
    CharSequence buttonText;
    int buttonTextRes;
    CharSequence dateTimeText;
    int dateTimeTextRes;
    CharSequence headerText;
    int headerTextRes;
    int jellyFishPallete = 3;
    CharSequence titleText;
    int titleTextRes;

    public void bind(EventScheduleInterstitial view) {
        super.bind(view);
        Context context = view.getContext();
        CharSequence actualHeader = this.headerTextRes != 0 ? context.getString(this.headerTextRes) : this.headerText;
        CharSequence actualTitle = this.titleTextRes != 0 ? context.getString(this.titleTextRes) : this.titleText;
        CharSequence actualDateTime = this.dateTimeTextRes != 0 ? context.getString(this.dateTimeTextRes) : this.dateTimeText;
        CharSequence actualAddress = this.addressTextRes != 0 ? context.getString(this.addressTextRes) : this.addressText;
        CharSequence actualButtonText = this.buttonTextRes != 0 ? context.getString(this.buttonTextRes) : this.buttonText;
        view.setHeaderText(actualHeader);
        view.setTitleText(actualTitle);
        view.setDateTimeText(actualDateTime);
        view.setAddressText(actualAddress);
        view.setButtonText(actualButtonText);
        view.setButtonClickListener(this.buttonClickListener);
        view.setJellyfishPallete(this.jellyFishPallete, false);
    }

    public void unbind(EventScheduleInterstitial view) {
        super.unbind(view);
        view.setButtonClickListener(null);
    }

    public AirEpoxyModel<EventScheduleInterstitial> reset() {
        this.jellyFishPallete = 1;
        return super.reset();
    }
}
