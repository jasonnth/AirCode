package com.airbnb.android.listing.adapters;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.models.AirAddress.Builder;
import com.airbnb.android.core.models.ListingRegistrationHelpLink;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.core.utils.listing.CityRegistrationUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.listing.utils.AddressFieldType;
import com.airbnb.android.listing.utils.AddressFormUtil;
import com.airbnb.android.listing.utils.AddressFormUtil.AddressForm;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import icepick.State;
import java.util.Collection;
import java.util.Set;

public class EditAddressAdapter extends AirEpoxyAdapter implements InputAdapter {
    @State
    AirAddress address;
    private AddressForm addressForm;
    private final InlineInputRowEpoxyModel_ apartmentInput = new InlineInputRowEpoxyModel_().m4884id((CharSequence) "apartment").titleRes(C7213R.string.address_apt_suite_etc).focusChangeListener(EditAddressAdapter$$Lambda$3.lambdaFactory$(this));
    private final InlineInputRowEpoxyModel_ cityInput = new InlineInputRowEpoxyModel_().m4884id((CharSequence) "city").titleRes(C7213R.string.city).focusChangeListener(EditAddressAdapter$$Lambda$4.lambdaFactory$(this));
    private final Context context;
    private final InlineInputRowEpoxyModel_ countryInput = new InlineInputRowEpoxyModel_().m4884id((CharSequence) "country").titleRes(C7213R.string.country).clickListener(EditAddressAdapter$$Lambda$1.lambdaFactory$(this));
    private final DocumentMarqueeEpoxyModel_ header = new DocumentMarqueeEpoxyModel_();
    private final ImmutableMap<AddressFieldType, InlineInputRowEpoxyModel_> inlineInputRows = ImmutableMap.builder().put(AddressFieldType.Country, this.countryInput).put(AddressFieldType.Street, this.streetInput).put(AddressFieldType.Apt, this.apartmentInput).put(AddressFieldType.City, this.cityInput).put(AddressFieldType.State, this.stateInput).put(AddressFieldType.Zipcode, this.zipCodeInput).build();
    private final Listener listener;
    private final Mode mode;
    private final InlineInputRowEpoxyModel_ stateInput = new InlineInputRowEpoxyModel_().m4884id((CharSequence) "state").titleRes(C7213R.string.address_state).inputType(524288).focusChangeListener(EditAddressAdapter$$Lambda$5.lambdaFactory$(this));
    private final InlineInputRowEpoxyModel_ streetInput = new InlineInputRowEpoxyModel_().m4884id((CharSequence) "street").titleRes(C7213R.string.street).clickListener(EditAddressAdapter$$Lambda$2.lambdaFactory$(this));
    private final InlineInputRowEpoxyModel_ zipCodeInput = new InlineInputRowEpoxyModel_().m4884id((CharSequence) "zipCode").titleRes(C7213R.string.address_zip_hint).focusChangeListener(EditAddressAdapter$$Lambda$6.lambdaFactory$(this));

    public interface Listener {
        void dismissErrorSnackbar();

        void showAddressAutoCompleteModal();

        void showCountrySelectModal();
    }

    public enum Mode {
        ManageListing,
        ListYourSpace,
        CityRegistration
    }

    public EditAddressAdapter(Context context2, AirAddress existingAddress, Listener listener2, Bundle savedInstanceState, Mode mode2) {
        boolean z;
        super(true);
        enableDiffing();
        this.listener = listener2;
        this.context = context2;
        this.mode = mode2;
        if (savedInstanceState == null) {
            this.address = existingAddress;
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
        InlineInputRowEpoxyModel_ inlineInputRowEpoxyModel_ = this.countryInput;
        if (mode2 == Mode.ListYourSpace || mode2 == Mode.CityRegistration) {
            z = true;
        } else {
            z = false;
        }
        inlineInputRowEpoxyModel_.enabled(z);
        this.header.titleRes(this.mode == Mode.ManageListing ? C7213R.string.address : C7213R.string.lys_dls_location_section_title);
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.header});
        setAddress(this.address);
    }

    public void setTitle(String title) {
        this.header.titleText((CharSequence) title);
        notifyModelChanged(this.header);
    }

    public void setSubtitle(String subtitle) {
        this.header.captionText((CharSequence) subtitle);
        notifyModelChanged(this.header);
    }

    public void setHelplink(ListingRegistrationHelpLink helplink) {
        CityRegistrationUtils.addHelpLinkToMarquee(this.header, helplink);
        notifyModelChanged(this.header);
    }

    public void onSaveInstanceState(Bundle outState) {
        this.address = getAddress();
        super.onSaveInstanceState(outState);
    }

    public boolean hasChanged(AirAddress originalAddress) {
        return !getAddress().equals(filterToValidAddressFields(this.addressForm, originalAddress));
    }

    public AirAddress getAddress() {
        return this.address.toBuilder().country(this.countryInput.input().toString()).streetAddressOne(this.streetInput.input().toString()).streetAddressTwo(this.apartmentInput.input().toString()).city(this.cityInput.input().toString()).state(this.stateInput.input().toString()).postalCode(this.zipCodeInput.input().toString()).build();
    }

    public void setInputEnabled(boolean enabled) {
        this.countryInput.enabled(enabled && (this.mode == Mode.ListYourSpace || this.mode == Mode.CityRegistration));
        this.streetInput.enabled(enabled);
        this.apartmentInput.enabled(enabled);
        this.cityInput.enabled(enabled);
        this.stateInput.enabled(enabled);
        this.zipCodeInput.enabled(enabled);
        notifyModelsChanged();
    }

    public void setAutoCompleteAddress(AirAddress addressSearchResult) {
        setAddress(addressSearchResult);
        validateInputsAndShowError();
    }

    public void setCountry(String country, String countryCode) {
        setAddress(AirAddress.builder().country(country).countryCode(countryCode).build());
    }

    public void setStreet(String street) {
        setAddress(this.address.toBuilder().streetAddressOne(street).build());
    }

    public void setAddress(AirAddress updatedAddress) {
        AddressFieldType[] values;
        this.addressForm = AddressFormUtil.forCountryCode(this.context, updatedAddress.countryCode());
        this.address = filterToValidAddressFields(this.addressForm, updatedAddress);
        removeAllAfterModel(this.header);
        ImmutableMap<AddressFieldType, String> hintTextMap = this.addressForm.getPlaceholders();
        for (AddressFieldType field : AddressFieldType.values()) {
            ((InlineInputRowEpoxyModel_) this.inlineInputRows.get(field)).hint(hintTextMap.containsKey(field) ? (CharSequence) hintTextMap.get(field) : "").input(AddressFieldType.getAirAddressValue(field, this.address));
        }
        FluentIterable append = FluentIterable.m1282of(AddressFieldType.Country, new AddressFieldType[0]).append((Iterable<? extends E>) this.addressForm.getNonCountryOrderedFields());
        ImmutableMap<AddressFieldType, InlineInputRowEpoxyModel_> immutableMap = this.inlineInputRows;
        immutableMap.getClass();
        addModels((Collection<? extends EpoxyModel<?>>) append.transform(EditAddressAdapter$$Lambda$7.lambdaFactory$(immutableMap)).toList());
        notifyModelsChanged();
    }

    public boolean validateInputsAndShowError() {
        Set<InlineInputRowEpoxyModel_> invalidInputs = getInvalidInputs();
        UnmodifiableIterator it = this.inlineInputRows.values().iterator();
        while (it.hasNext()) {
            InlineInputRowEpoxyModel_ input = (InlineInputRowEpoxyModel_) it.next();
            notifyModelChanged(input.showError(invalidInputs.contains(input)));
        }
        return invalidInputs.isEmpty();
    }

    /* access modifiers changed from: private */
    public void streetClicked() {
        this.listener.dismissErrorSnackbar();
        this.listener.showAddressAutoCompleteModal();
    }

    /* access modifiers changed from: private */
    public void countryClicked() {
        this.listener.dismissErrorSnackbar();
        this.listener.showCountrySelectModal();
    }

    /* access modifiers changed from: private */
    public void focusChanged() {
        this.listener.dismissErrorSnackbar();
    }

    private Set<InlineInputRowEpoxyModel_> getInvalidInputs() {
        FluentIterable from = FluentIterable.from((Iterable<E>) this.addressForm.getRequiredFields());
        ImmutableMap<AddressFieldType, InlineInputRowEpoxyModel_> immutableMap = this.inlineInputRows;
        immutableMap.getClass();
        return from.transform(EditAddressAdapter$$Lambda$8.lambdaFactory$(immutableMap)).filter(EditAddressAdapter$$Lambda$9.lambdaFactory$()).toSet();
    }

    private static AirAddress filterToValidAddressFields(AddressForm form, AirAddress address2) {
        Set<AddressFieldType> validFields = FluentIterable.from((Iterable<E>) form.getNonCountryOrderedFields()).append((E[]) new AddressFieldType[]{AddressFieldType.Country}).toSet();
        Builder builder = AirAddress.builder();
        builder.countryCode(address2.countryCode());
        AddressFieldType[] values = AddressFieldType.values();
        int length = values.length;
        for (int i = 0; i < length; i++) {
            AddressFieldType field = values[i];
            AddressFieldType.setAirAddressValue(builder, field, SanitizeUtils.emptyIfNull(validFields.contains(field) ? AddressFieldType.getAirAddressValue(field, address2) : null));
        }
        return builder.build();
    }
}
