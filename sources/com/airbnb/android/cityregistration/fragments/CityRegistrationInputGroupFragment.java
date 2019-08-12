package com.airbnb.android.cityregistration.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.cityregistration.C5630R;
import com.airbnb.android.cityregistration.controller.CityRegistrationController;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.datepicker.DatePickerDialog;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.models.Country;
import com.airbnb.android.core.models.ListingRegistrationProcessInputGroup;
import com.airbnb.android.core.models.ListingRegistrationQuestion;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.listing.CityRegistrationUtils;
import com.airbnb.android.listing.adapters.CityRegistrationInputGroupAdapter;
import com.airbnb.android.listing.adapters.CityRegistrationInputGroupAdapter.ListenerV2;
import com.airbnb.android.listing.adapters.EditAddressAdapter;
import com.airbnb.android.listing.adapters.EditAddressAdapter.Listener;
import com.airbnb.android.listing.adapters.EditAddressAdapter.Mode;
import com.airbnb.android.listing.fragments.AddressAutoCompleteFragment.Builder;
import com.airbnb.android.listing.fragments.CountryFragment;
import com.airbnb.android.photopicker.PhotoPicker;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;
import java.io.File;

public class CityRegistrationInputGroupFragment extends CityRegistrationBaseFragment {
    private static final String ARG_INPUT_GROUP_INDEX = "inputGroupIndex";
    private static final String ARG_NEXT_GROUP_INDEX = "nextGroupIndex";
    private static final String ARG_STANDALONE_PAGE = "standalonePage";
    private static final int REQUEST_CODE_ADDRESS_AUTOCOMPLETE = 100;
    private static final int REQUEST_CODE_COUNTRY_SELECTED = 201;
    private static final int REQUEST_CODE_FILE_DELETED = 302;
    @State
    ListingRegistrationQuestion currentQuestionBeingUpdated;
    /* access modifiers changed from: private */
    public EditAddressAdapter editAddressAdapter;
    private final Listener editAddressListener = new Listener() {
        public void showCountrySelectModal() {
            CityRegistrationInputGroupFragment.this.startActivityForResult(CountryFragment.createIntent(CityRegistrationInputGroupFragment.this.getContext(), CityRegistrationInputGroupFragment.this.editAddressAdapter.getAddress().countryCode(), NavigationTag.CityRegistrationCountryPicker), CityRegistrationInputGroupFragment.REQUEST_CODE_COUNTRY_SELECTED);
        }

        public void showAddressAutoCompleteModal() {
            CityRegistrationInputGroupFragment.this.startActivityForResult(new Builder(CityRegistrationInputGroupFragment.this.getContext(), NavigationTag.CityRegistrationAddressAutoComplete).setCountryAndStreet(CityRegistrationInputGroupFragment.this.editAddressAdapter.getAddress()).build(), 100);
        }

        public void dismissErrorSnackbar() {
        }
    };
    private ListingRegistrationProcessInputGroup inputGroup;
    private CityRegistrationInputGroupAdapter inputGroupAdapter;
    private final ListenerV2 inputListener = new ListenerV2() {
        public void enableNextButton(boolean enable) {
            CityRegistrationInputGroupFragment.this.enableNextButton(enable);
        }

        public void showDocumentTypeSelection(ListingRegistrationQuestion question) {
            CityRegistrationInputGroupFragment.this.controller.showDocTypeSelection(question);
        }

        public void showDocUploadReviewWithUrl(ListingRegistrationQuestion question, String url) {
            CityRegistrationInputGroupFragment.this.currentQuestionBeingUpdated = question;
            CityRegistrationInputGroupFragment.this.controller.showDocUploadReviewWithUrl(question, url);
        }

        public String getDocFileUrl(String inputKey) {
            return CityRegistrationInputGroupFragment.this.controller.getDocFileUrl(inputKey);
        }

        public void showDateSelection(ListingRegistrationQuestion question) {
            CityRegistrationInputGroupFragment.this.currentQuestionBeingUpdated = question;
            AirDate today = AirDate.today();
            AirDate initialDate = today;
            if (question.getInputAnswer() != null) {
                AirDate potentialDate = AirDate.fromISODateString(question.getInputAnswer());
                if (potentialDate != null) {
                    initialDate = potentialDate;
                }
            }
            DatePickerDialog.newInstance(initialDate, false, CityRegistrationInputGroupFragment.this.getTargetFragment(), 0, null, today, DatePickerDialog.DATE_PICKER_OK).show(CityRegistrationInputGroupFragment.this.getFragmentManager(), (String) null);
        }

        public void showFileDeletionConfirmationDialog(ListingRegistrationQuestion question) {
            CityRegistrationInputGroupFragment.this.currentQuestionBeingUpdated = question;
            CityRegistrationConfirmationDialogFragment dialog = new CityRegistrationConfirmationDialogFragment();
            dialog.setTargetFragment(CityRegistrationInputGroupFragment.this, 302);
            dialog.show(CityRegistrationInputGroupFragment.this.getFragmentManager(), (String) null);
        }
    };
    @State
    AirAddress listingAddress;
    @BindView
    AirButton nextButton;
    @State
    boolean nextButtonClicked;
    private int nextGroupIndex;
    @BindView
    RecyclerView recyclerView;
    @State
    boolean saveButtonClicked;
    private boolean standalonePage;
    @BindView
    AirToolbar toolbar;

    public static CityRegistrationInputGroupFragment create(int inputGroupIndex, int nextGroupIndex2, boolean standalonePage2) {
        return (CityRegistrationInputGroupFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CityRegistrationInputGroupFragment()).putInt(ARG_INPUT_GROUP_INDEX, inputGroupIndex)).putInt(ARG_NEXT_GROUP_INDEX, nextGroupIndex2)).putBoolean(ARG_STANDALONE_PAGE, standalonePage2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int inputGroupIndex = getArguments().getInt(ARG_INPUT_GROUP_INDEX);
        this.nextGroupIndex = getArguments().getInt(ARG_NEXT_GROUP_INDEX);
        this.standalonePage = getArguments().getBoolean(ARG_STANDALONE_PAGE, false);
        this.inputGroup = this.controller.getInputGroupFromIndex(inputGroupIndex);
        setFragmentAdapter();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(this.controller.isLYS() ? C5630R.layout.fragment_city_registration_lys_next_btn : C5630R.layout.fragment_listing_recycler_view_with_next, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (this.standalonePage) {
            this.nextButton.setText(C5630R.string.save);
        }
        if (this.controller.shouldUpdateInputGroup(this.inputGroup.getGroupId())) {
            setFragmentAdapter();
        }
        if (this.inputGroupAdapter != null) {
            this.recyclerView.setAdapter(this.inputGroupAdapter);
            this.inputGroupAdapter.validateInputsWithUpdateErrorState(false);
        } else {
            this.recyclerView.setAdapter(this.editAddressAdapter);
        }
        return view;
    }

    private void setFragmentAdapter() {
        this.listingAddress = this.controller.getAddress(this.inputGroup);
        Check.notNull(this.listingAddress);
        if (this.inputGroup.hasAddressInput()) {
            this.inputGroupAdapter = null;
            this.editAddressAdapter = new EditAddressAdapter(getContext(), this.listingAddress, this.editAddressListener, null, Mode.CityRegistration);
            this.editAddressAdapter.setTitle(this.inputGroup.getGroupTitle());
            this.editAddressAdapter.setSubtitle(this.inputGroup.getSubtitleString());
            this.editAddressAdapter.setHelplink(this.inputGroup.getGroupHelpLink());
            return;
        }
        this.inputGroupAdapter = new CityRegistrationInputGroupAdapter(this.inputGroup, this.inputListener, null, getContext());
        this.editAddressAdapter = null;
    }

    public void notifyDataSetChanged() {
        if (this.saveButtonClicked) {
            this.controller.finishSaveAndExit();
        } else if (!this.nextButtonClicked) {
            this.inputGroupAdapter = new CityRegistrationInputGroupAdapter(this.inputGroup, this.inputListener, null, getContext());
            this.recyclerView.setAdapter(this.inputGroupAdapter);
        } else if (this.standalonePage) {
            this.controller.finishEdit(true, this.inputGroup.getGroupId());
        } else {
            finish();
        }
    }

    public void showLoader() {
        this.nextButton.setState(AirButton.State.Loading);
    }

    public void hideLoader() {
        this.nextButton.setState(AirButton.State.Normal);
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

    /* access modifiers changed from: protected */
    public boolean onSaveActionPressed() {
        this.saveButtonClicked = true;
        save();
        return false;
    }

    @OnClick
    public void onNext() {
        if (this.inputGroupAdapter == null || this.inputGroupAdapter.validateInputsWithUpdateErrorState(true)) {
            this.nextButtonClicked = true;
            save();
        }
    }

    private void save() {
        if (this.editAddressAdapter != null) {
            AirAddress address = this.editAddressAdapter.getAddress();
            saveAddressToFirstAddressQuestion(address);
            this.controller.saveAddress(this.inputGroup.getAddressInput(), address);
            return;
        }
        this.controller.saveTextAnswers(this.inputGroup);
    }

    public void finish() {
        if (this.saveButtonClicked) {
            this.controller.finishSaveAndExit();
        } else {
            this.controller.getActionExecutor().cityRegistrationInputGroup(this.nextGroupIndex);
        }
    }

    private void saveAddressToFirstAddressQuestion(AirAddress address) {
        ListingRegistrationQuestion question = this.inputGroup.getAddressInput();
        if (question != null) {
            question.setInputAnswer(CityRegistrationUtils.getStringFromAirAddress(address));
        }
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
                case CityRegistrationController.RC_CHOOSE_PHOTO /*202*/:
                    this.controller.handleImageCapture(Uri.fromFile(new File(data.getStringExtra(PhotoPicker.EXTRA_PHOTO_PATH))), this.currentQuestionBeingUpdated, this.currentQuestionBeingUpdated.getInputAnswer());
                    return;
                case 302:
                    this.controller.deleteDoc(this.currentQuestionBeingUpdated);
                    return;
                case DatePickerDialog.DATE_PICKER_OK /*2002*/:
                    this.currentQuestionBeingUpdated.setInputAnswer(((AirDate) data.getParcelableExtra("date")).getIsoDateString());
                    notifyDataSetChanged();
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
        return this.controller.isLYS();
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11639kv("group_id", this.inputGroup.getGroupId());
    }

    public void setQuestionInputGroup(ListingRegistrationProcessInputGroup inputGroup2) {
        this.inputGroup = inputGroup2;
        this.inputGroupAdapter = new CityRegistrationInputGroupAdapter(inputGroup2, this.inputListener, null, getContext());
        this.recyclerView.setAdapter(this.inputGroupAdapter);
    }
}
