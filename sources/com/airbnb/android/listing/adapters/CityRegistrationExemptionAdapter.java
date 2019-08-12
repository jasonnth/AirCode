package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import android.text.TextUtils;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingRegistrationContent;
import com.airbnb.android.core.models.ListingRegistrationExemptionFields;
import com.airbnb.android.core.models.ListingRegistrationExemptionFields.Field;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.core.utils.listing.CityRegistrationUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SwitchRowEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.SwitchStyle;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.google.common.base.Strings;
import icepick.State;
import java.util.ArrayList;

public class CityRegistrationExemptionAdapter extends AirEpoxyAdapter implements InputAdapter {
    private static final String PENDING_PERMIT = "City registration pending";
    @State
    String expiryDate;
    private InlineInputRowEpoxyModel_ expiryDateInput;
    @State
    String license;
    private final InlineInputRowEpoxyModel_ licenseOrRegistrationNumber;
    private SwitchRowEpoxyModel_ switchRow;
    @State
    String zipCode;
    private InlineInputRowEpoxyModel_ zipCodeInput;

    public interface Listener {
        void inputIsValid(boolean z);
    }

    public interface ListenerV2 extends Listener {
        void showDateSelection();
    }

    public CityRegistrationExemptionAdapter(String license2, ListingRegistrationContent content, Listener listener, Bundle savedInstanceState) {
        this(license2, null, null, content, null, listener, savedInstanceState);
    }

    public CityRegistrationExemptionAdapter(String license2, String expiryDate2, String zipCode2, ListingRegistrationContent content, ListingRegistrationProcess listingRegistrationProcess, Listener listener, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.license = license2;
            this.expiryDate = expiryDate2;
            this.zipCode = zipCode2;
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
        ArrayList<String> subtitles = new ArrayList<>(content.getLicenseStepSubtitles());
        subtitles.add(content.getExemptionFields().getPermitNumberAccuracyWarning());
        DocumentMarqueeEpoxyModel_ documentMarquee = new DocumentMarqueeEpoxyModel_().titleText((CharSequence) content.getLicenseStepTitle()).captionText((CharSequence) CityRegistrationUtils.textFromLines(subtitles, true));
        CityRegistrationUtils.addHelpLinkToMarquee(documentMarquee, content);
        boolean isPending = PENDING_PERMIT.equals(license2);
        ListingRegistrationExemptionFields exemptionFields = content.getExemptionFields();
        this.licenseOrRegistrationNumber = new InlineInputRowEpoxyModel_().title(exemptionFields.getPermitNumberLabel()).input(Strings.nullToEmpty(this.license)).hint(exemptionFields.getPermitNumberPlaceholder()).show(!isPending).inputChangedListener(CityRegistrationExemptionAdapter$$Lambda$1.lambdaFactory$(this, listener, listingRegistrationProcess));
        this.switchRow = new SwitchRowEpoxyModel_().title(content.getExemptionFields().getPendingToggleLabel()).style(SwitchStyle.Filled).showDivider(!isPending).show(true).checked(isPending).checkedChangeListener(CityRegistrationExemptionAdapter$$Lambda$2.lambdaFactory$(this, listener, listingRegistrationProcess));
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{documentMarquee, this.switchRow, this.licenseOrRegistrationNumber});
        if (listener instanceof ListenerV2) {
            if (listingRegistrationProcess.getRequiredForExemption().contains(Field.expiration_date.name())) {
                this.expiryDateInput = new InlineInputRowEpoxyModel_().title(exemptionFields.getExpirationDateLabel()).input(expiryDate2).hintRes(C7213R.string.select_date).inputType(16).show(!isPending).inputChangedListener(CityRegistrationExemptionAdapter$$Lambda$3.lambdaFactory$(this, listener, listingRegistrationProcess)).clickListener(CityRegistrationExemptionAdapter$$Lambda$4.lambdaFactory$(listener));
                addModel(this.expiryDateInput);
            }
            if (listingRegistrationProcess.getRequiredForExemption().contains(Field.zipcode.name())) {
                this.zipCodeInput = new InlineInputRowEpoxyModel_().title(exemptionFields.getZipcodeLabel()).inputChangedListener(CityRegistrationExemptionAdapter$$Lambda$5.lambdaFactory$(this, listener, listingRegistrationProcess)).input(Strings.nullToEmpty(this.zipCode)).show(!isPending);
                addModel(this.zipCodeInput);
            }
        }
    }

    static /* synthetic */ void lambda$new$1(CityRegistrationExemptionAdapter cityRegistrationExemptionAdapter, Listener listener, ListingRegistrationProcess listingRegistrationProcess, SwitchRowInterface switchRowInterface, boolean isChecked) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        if (cityRegistrationExemptionAdapter.licenseOrRegistrationNumber != null) {
            cityRegistrationExemptionAdapter.licenseOrRegistrationNumber.show(!isChecked);
            if (isChecked) {
                cityRegistrationExemptionAdapter.licenseOrRegistrationNumber.input(PENDING_PERMIT);
            } else if (PENDING_PERMIT.equals(cityRegistrationExemptionAdapter.licenseOrRegistrationNumber.input())) {
                cityRegistrationExemptionAdapter.licenseOrRegistrationNumber.input("");
            }
        }
        if (cityRegistrationExemptionAdapter.expiryDateInput != null) {
            InlineInputRowEpoxyModel_ inlineInputRowEpoxyModel_ = cityRegistrationExemptionAdapter.expiryDateInput;
            if (!isChecked) {
                z2 = true;
            } else {
                z2 = false;
            }
            inlineInputRowEpoxyModel_.show(z2);
        }
        if (cityRegistrationExemptionAdapter.zipCodeInput != null) {
            InlineInputRowEpoxyModel_ inlineInputRowEpoxyModel_2 = cityRegistrationExemptionAdapter.zipCodeInput;
            if (!isChecked) {
                z = true;
            } else {
                z = false;
            }
            inlineInputRowEpoxyModel_2.show(z);
        }
        SwitchRowEpoxyModel_ switchRowEpoxyModel_ = cityRegistrationExemptionAdapter.switchRow;
        if (isChecked) {
            z3 = false;
        }
        switchRowEpoxyModel_.showDivider(z3);
        cityRegistrationExemptionAdapter.notifyDataSetChanged();
        listener.inputIsValid(cityRegistrationExemptionAdapter.allInputIsValid(listingRegistrationProcess));
    }

    public void onSaveInstanceState(Bundle outState) {
        this.license = getLicense();
        this.zipCode = getZipCode();
        super.onSaveInstanceState(outState);
    }

    public boolean allInputIsValid(ListingRegistrationProcess listingRegistrationProcess) {
        if (this.switchRow.checked()) {
            return true;
        }
        if (listingRegistrationProcess == null) {
            return TextUtils.isEmpty(getLicense());
        }
        if (TextUtils.isEmpty(getLicense()) || ((listingRegistrationProcess.getRequiredForExemption().contains(Field.expiration_date.name()) && TextUtils.isEmpty(getExpiryDate())) || (listingRegistrationProcess.getRequiredForExemption().contains(Field.zipcode.name()) && TextUtils.isEmpty(getZipCode())))) {
            return false;
        }
        return true;
    }

    public String getLicense() {
        return this.licenseOrRegistrationNumber.input().toString();
    }

    public String getExpiryDate() {
        if (this.expiryDateInput == null || this.expiryDateInput.input() == null) {
            return null;
        }
        return this.expiryDateInput.input().toString();
    }

    public String getZipCode() {
        if (this.zipCodeInput == null || this.zipCodeInput.input() == null) {
            return null;
        }
        return this.zipCodeInput.input().toString();
    }

    public void setExpiryDate(String expiryDate2) {
        this.expiryDateInput.input(expiryDate2);
        notifyDataSetChanged();
    }

    public void setInputEnabled(boolean enabled) {
        this.licenseOrRegistrationNumber.enabled(enabled);
        notifyModelChanged(this.licenseOrRegistrationNumber);
        if (this.expiryDateInput != null) {
            this.expiryDateInput.enabled(enabled);
            notifyModelChanged(this.expiryDateInput);
        }
        if (this.zipCodeInput != null) {
            this.zipCodeInput.enabled(enabled);
            notifyModelChanged(this.zipCodeInput);
        }
    }

    public boolean hasChanged(Listing listing) {
        if (!TextUtils.isEmpty(getLicense()) && !getLicense().equals(SanitizeUtils.emptyIfNull(listing.getLicense()))) {
            return true;
        }
        if (!TextUtils.isEmpty(getExpiryDate()) && !getExpiryDate().equals(SanitizeUtils.emptyIfNull(this.expiryDate))) {
            return true;
        }
        if (TextUtils.isEmpty(getZipCode()) || getZipCode().equals(SanitizeUtils.emptyIfNull(this.zipCode))) {
            return false;
        }
        return true;
    }
}
