package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import android.text.TextUtils;
import com.airbnb.android.core.models.ListingRegistrationContent;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.core.utils.listing.CityRegistrationUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import icepick.State;

public class ListingRegistrationAdapter extends AirEpoxyAdapter implements InputAdapter {
    @State
    String license;
    private final InlineInputRowEpoxyModel_ licenseOrRegistrationNumberModel;
    private final DocumentMarqueeEpoxyModel_ marqueeModel;

    public interface Listener {
        void inputHasChanged(boolean z);
    }

    public ListingRegistrationAdapter(ListingRegistrationProcess listingRegistrationProcess, String license2, Bundle savedInstanceState) {
        this(listingRegistrationProcess, license2, savedInstanceState, null);
    }

    public ListingRegistrationAdapter(ListingRegistrationProcess listingRegistrationProcess, String license2, Bundle savedInstanceState, Listener listener) {
        this.marqueeModel = new DocumentMarqueeEpoxyModel_();
        this.licenseOrRegistrationNumberModel = new InlineInputRowEpoxyModel_();
        if (savedInstanceState == null) {
            this.license = license2;
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
        ListingRegistrationContent content = listingRegistrationProcess.getContent();
        this.marqueeModel.titleText((CharSequence) content.getStepTitle()).captionText((CharSequence) TextUtils.join("\n\n", content.getStepSubtitles()));
        CityRegistrationUtils.addHelpLinkToMarquee(this.marqueeModel, content);
        addModel(this.marqueeModel);
        if (!listingRegistrationProcess.getListingRegistration().getStatus().isDeniedStatus()) {
            this.licenseOrRegistrationNumberModel.title(content.getRegistrationFieldLabel()).input(this.license).inputChangedListener(ListingRegistrationAdapter$$Lambda$1.lambdaFactory$(listener, license2));
            addModel(this.licenseOrRegistrationNumberModel);
        }
    }

    static /* synthetic */ void lambda$new$0(Listener listener, String license2, String input) {
        if (listener != null) {
            listener.inputHasChanged(!input.isEmpty() && !input.equals(license2));
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        if (!(this.licenseOrRegistrationNumberModel == null || this.licenseOrRegistrationNumberModel.input() == null)) {
            this.license = this.licenseOrRegistrationNumberModel.input().toString();
        }
        super.onSaveInstanceState(outState);
    }

    public String getLicense() {
        if (this.licenseOrRegistrationNumberModel == null || this.licenseOrRegistrationNumberModel.input() == null) {
            return "";
        }
        return this.licenseOrRegistrationNumberModel.input().toString();
    }

    public boolean hasChanged(String listingLicense) {
        return !TextUtils.isEmpty(getLicense()) && !getLicense().equals(listingLicense);
    }

    public void setInputEnabled(boolean enabled) {
        this.licenseOrRegistrationNumberModel.enabled(enabled);
        notifyModelChanged(this.licenseOrRegistrationNumberModel);
    }
}
