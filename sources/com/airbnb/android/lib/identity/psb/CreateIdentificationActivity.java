package com.airbnb.android.lib.identity.psb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.activities.SheetFlowActivity;
import com.airbnb.android.core.analytics.BookingJitneyLogger;
import com.airbnb.android.core.interfaces.GuestIdentity.Type;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.requests.CreateGuestIdentityInformationRequest;
import com.airbnb.android.core.responses.SaveGuestIdentityInformationResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.airbnb.p027n2.utils.ViewLibUtils;
import icepick.State;
import p032rx.Observer;

public class CreateIdentificationActivity extends SheetFlowActivity {
    private static final String ARG_FIRST_IDENTIFICATION = "first_idenfitication";
    private static final String ARG_IS_INSTANT_BOOKABLE = "is_instant_bookable";
    private static final String ARG_IS_P4_REDESIGN = "is_p4_redesign";
    private static final String ARG_RESERVATION_DETAILS = "reservation_details";
    public static final String EXTRA_NEW_IDENTIFICATION = "new_identification";
    BookingJitneyLogger bookingJitneyLogger;
    @State
    String countryCode;
    @State
    AirDate dateOfExpiry;
    @State
    String givenNames;
    @State
    String identificationNumber;
    @State
    Type identityType;
    @State
    boolean isInstantBookable;
    @State
    boolean isP4Redesign;
    /* access modifiers changed from: private */
    public boolean isSavingIdentification;
    @State
    ReservationDetails reservationDetails;
    final RequestListener<SaveGuestIdentityInformationResponse> saveIdentityRequestListener = new RequestListener<SaveGuestIdentityInformationResponse>() {
        public void onResponse(SaveGuestIdentityInformationResponse data) {
            CreateIdentificationActivity.this.stopLoader();
            Intent intent = new Intent();
            intent.putExtra(CreateIdentificationActivity.EXTRA_NEW_IDENTIFICATION, data.getIdentity());
            CreateIdentificationActivity.this.setResult(-1, intent);
            new SnackbarWrapper().title(CreateIdentificationActivity.this.getString(C0880R.string.identification_saved), false).duration(-1).view(CreateIdentificationActivity.this.findViewById(16908290)).buildAndShow();
            CreateIdentificationActivity.this.finish();
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            CreateIdentificationActivity.this.isSavingIdentification = false;
            CreateIdentificationActivity.this.stopLoader();
            new SnackbarWrapper().title(CreateIdentificationActivity.this.getString(C0880R.string.error), true).body(NetworkUtil.errorDetails(e)).duration(-1).view(CreateIdentificationActivity.this.findViewById(16908290)).buildAndShow();
        }
    };
    @State
    String surname;

    public static Intent newIntent(Context context, boolean firstIdentification, Reservation reservation, ReservationDetails reservationDetails2, boolean isP4Redesign2) {
        if (isP4Redesign2) {
            return new Intent(context, CreateIdentificationActivity.class).putExtra(ARG_FIRST_IDENTIFICATION, firstIdentification).putExtra(ARG_IS_INSTANT_BOOKABLE, reservation.isInstantBookable()).putExtra(ARG_RESERVATION_DETAILS, reservationDetails2).putExtra(ARG_IS_P4_REDESIGN, isP4Redesign2);
        }
        return new Intent(context, CreateIdentificationActivity.class).putExtra(ARG_FIRST_IDENTIFICATION, firstIdentification).putExtra(ARG_IS_P4_REDESIGN, isP4Redesign2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(this).component()).inject(this);
        ViewLibUtils.setPaddingTop(findViewById(C0880R.C0882id.content_container), 0);
        if (savedInstanceState == null) {
            this.isInstantBookable = getIntent().getBooleanExtra(ARG_IS_INSTANT_BOOKABLE, false);
            this.reservationDetails = (ReservationDetails) getIntent().getParcelableExtra(ARG_RESERVATION_DETAILS);
            this.isP4Redesign = getIntent().getBooleanExtra(ARG_IS_P4_REDESIGN, false);
            showFragment(IdentificationNameFragment.newInstance(getIntent().getBooleanExtra(ARG_FIRST_IDENTIFICATION, false)));
        }
        this.toolbar.setTheme(this.isP4Redesign ? 3 : 2);
    }

    public void setName(String givenNames2, String surname2) {
        Check.notEmpty(givenNames2, "given name must be provided");
        Check.notEmpty(surname2, "surname must be provided");
        this.givenNames = givenNames2;
        this.surname = surname2;
        showFragment(new IdentificationNationalityFragment());
    }

    /* access modifiers changed from: 0000 */
    public void setIdentificationNumber(String identificationNumber2) {
        Check.notEmpty(identificationNumber2, "identification number must not be empty");
        this.identificationNumber = identificationNumber2;
        if (Type.ChineseNationalID.equals(this.identityType)) {
            saveIdentificationToServer();
        } else {
            showFragment(SelectIdentificationExpiryDateFragment.newInstance());
        }
    }

    private void saveIdentificationToServer() {
        if (!this.isSavingIdentification) {
            this.isSavingIdentification = true;
            startLoader();
            new CreateGuestIdentityInformationRequest(this.surname, this.givenNames, this.countryCode, this.identityType, this.identificationNumber, this.dateOfExpiry).withListener((Observer) this.saveIdentityRequestListener).execute(this.requestManager);
        }
    }

    public void setCountryCode(String countryCode2) {
        Check.notEmpty(countryCode2, "country code can not be empty");
        this.countryCode = countryCode2;
        if (AirbnbConstants.COUNTRY_CODE_CHINA.equals(countryCode2)) {
            showFragment(SelectIdentificationTypeFragment.newInstance());
        } else {
            setIdentificationType(Type.Passport);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setDateOfExpiry(AirDate dateOfExpiry2) {
        Check.state(Type.Passport.equals(this.identityType), "date of expiry only supported for passports");
        this.dateOfExpiry = dateOfExpiry2;
        saveIdentificationToServer();
    }

    /* access modifiers changed from: 0000 */
    public void setIdentificationType(Type identityType2) {
        Check.notNull(identityType2, "identity type must not be null");
        this.identityType = identityType2;
        showFragment(InputIdentificationNumberFragment.forIdentificationType(identityType2));
    }

    public boolean useHomeAsBack() {
        return true;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getGivenNames() {
        return this.givenNames;
    }

    /* access modifiers changed from: 0000 */
    public String getIdentificationNumber() {
        return this.identificationNumber;
    }

    /* access modifiers changed from: 0000 */
    public AirDate getDateOfExpiry() {
        return this.dateOfExpiry;
    }

    /* access modifiers changed from: 0000 */
    public Type getIdentificationType() {
        return this.identityType;
    }

    public void onBackPressed() {
        if (!getSupportFragmentManager().popBackStackImmediate()) {
            setResult(0, null);
            finish();
        }
    }

    public boolean isP4Redesign() {
        return this.isP4Redesign;
    }
}
