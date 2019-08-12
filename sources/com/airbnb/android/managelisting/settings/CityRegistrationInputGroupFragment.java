package com.airbnb.android.managelisting.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.enums.ListingRegistrationInputType;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.models.Country;
import com.airbnb.android.core.models.ListingRegistrationProcessInputGroup;
import com.airbnb.android.core.models.ListingRegistrationQuestion;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.listing.CityRegistrationUtils;
import com.airbnb.android.listing.adapters.CityRegistrationInputGroupAdapter;
import com.airbnb.android.listing.adapters.EditAddressAdapter;
import com.airbnb.android.listing.adapters.EditAddressAdapter.Listener;
import com.airbnb.android.listing.adapters.EditAddressAdapter.Mode;
import com.airbnb.android.listing.fragments.AddressAutoCompleteFragment.Builder;
import com.airbnb.android.listing.fragments.CountryFragment;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.google.common.collect.FluentIterable;
import icepick.State;

public class CityRegistrationInputGroupFragment extends ManageListingBaseFragment {
    private static final String ARG_INPUT_GROUP = "inputGroup";
    private static final String ARG_LISTING_ADDRESS = "listingAddress";
    private static final String ARG_NEXT_GROUP_INDEX = "nextGroupIndex";
    private static final int REQUEST_CODE_ADDRESS_AUTOCOMPLETE = 100;
    private static final int REQUEST_CODE_COUNTRY_SELECTED = 201;
    /* access modifiers changed from: private */
    public EditAddressAdapter editAddressAdapter;
    private final Listener editAddressListener = new Listener() {
        public void showCountrySelectModal() {
            CityRegistrationInputGroupFragment.this.startActivityForResult(CountryFragment.createIntent(CityRegistrationInputGroupFragment.this.getContext(), CityRegistrationInputGroupFragment.this.editAddressAdapter.getAddress().countryCode(), NavigationTag.CityRegistrationCountryPicker), CityRegistrationInputGroupFragment.REQUEST_CODE_COUNTRY_SELECTED);
        }

        public void showAddressAutoCompleteModal() {
            CityRegistrationInputGroupFragment.this.startActivityForResult(new Builder(CityRegistrationInputGroupFragment.this.getContext(), NavigationTag.CityRegistrationAddressAutoComplete).setCountryAndStreet(CityRegistrationInputGroupFragment.this.editAddressAdapter.getAddress()).setListingId(CityRegistrationInputGroupFragment.this.controller.getListingId()).build(), 100);
        }

        public void dismissErrorSnackbar() {
        }
    };
    @State
    ListingRegistrationProcessInputGroup inputGroup;
    private CityRegistrationInputGroupAdapter inputGroupAdapter;
    @State
    AirAddress listingAddress;
    @BindView
    AirButton nextButton;
    @State
    int nextGroupIndex;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static CityRegistrationInputGroupFragment create(ListingRegistrationProcessInputGroup inputGroup2, AirAddress address, int nextGroupIndex2) {
        return (CityRegistrationInputGroupFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CityRegistrationInputGroupFragment()).putParcelable(ARG_INPUT_GROUP, inputGroup2)).putInt(ARG_NEXT_GROUP_INDEX, nextGroupIndex2)).putParcelable(ARG_LISTING_ADDRESS, address)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.inputGroup = (ListingRegistrationProcessInputGroup) getArguments().getParcelable(ARG_INPUT_GROUP);
        this.nextGroupIndex = getArguments().getInt(ARG_NEXT_GROUP_INDEX);
        this.listingAddress = (AirAddress) getArguments().getParcelable(ARG_LISTING_ADDRESS);
        Check.notNull(this.listingAddress);
        if (this.inputGroup.hasAddressInput()) {
            this.inputGroupAdapter = null;
            this.editAddressAdapter = new EditAddressAdapter(getContext(), this.listingAddress, this.editAddressListener, savedInstanceState, Mode.CityRegistration);
            this.editAddressAdapter.setTitle(this.inputGroup.getGroupTitle());
            this.editAddressAdapter.setSubtitle(this.inputGroup.getSubtitleString());
            this.editAddressAdapter.setHelplink(this.inputGroup.getGroupHelpLink());
            return;
        }
        this.inputGroupAdapter = new CityRegistrationInputGroupAdapter(this.inputGroup, CityRegistrationInputGroupFragment$$Lambda$1.lambdaFactory$(this), savedInstanceState, getContext());
        this.editAddressAdapter = null;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_next, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (this.inputGroupAdapter != null) {
            this.recyclerView.setAdapter(this.inputGroupAdapter);
            this.inputGroupAdapter.validateInputsWithUpdateErrorState(false);
        } else {
            this.recyclerView.setAdapter(this.editAddressAdapter);
        }
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.inputGroupAdapter != null) {
            this.inputGroupAdapter.onSaveInstanceState(outState);
        }
        if (this.editAddressAdapter != null) {
            this.editAddressAdapter.onSaveInstanceState(outState);
        }
    }

    @OnClick
    public void onNext() {
        if (this.inputGroupAdapter == null || this.inputGroupAdapter.validateInputsWithUpdateErrorState(true)) {
            if (this.editAddressAdapter != null) {
                saveAddressToFirstAddressQuestion(this.editAddressAdapter.getAddress());
            }
            this.controller.actionExecutor.cityRegistrationInputGroup(this.nextGroupIndex);
        }
    }

    private void saveAddressToFirstAddressQuestion(AirAddress address) {
        ListingRegistrationQuestion question = (ListingRegistrationQuestion) FluentIterable.from((Iterable<E>) this.inputGroup.getQuestions()).firstMatch(CityRegistrationInputGroupFragment$$Lambda$2.lambdaFactory$()).orNull();
        if (question != null) {
            question.setInputAnswer(CityRegistrationUtils.getStringFromAirAddress(address));
        }
    }

    static /* synthetic */ boolean lambda$saveAddressToFirstAddressQuestion$0(ListingRegistrationQuestion v) {
        return v.getInputType() == ListingRegistrationInputType.Address;
    }

    /* access modifiers changed from: private */
    public void enableNextButton(boolean enabled) {
        if (this.nextButton != null) {
            this.nextButton.setEnabled(enabled);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 100:
                    AirAddress address = (AirAddress) data.getParcelableExtra("address");
                    if (address != null) {
                        this.editAddressAdapter.setAutoCompleteAddress(address);
                        return;
                    }
                    String street = data.getStringExtra("street");
                    if (street != null) {
                        this.editAddressAdapter.setStreet(street);
                        return;
                    }
                    return;
                case REQUEST_CODE_COUNTRY_SELECTED /*201*/:
                    Country country = (Country) data.getParcelableExtra("country");
                    this.editAddressAdapter.setCountry(country.getLocalizedName(), country.getAlpha_2());
                    return;
                default:
                    return;
            }
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CityRegistrationInputGroup;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11639kv("group_id", this.inputGroup.getGroupId());
    }
}
