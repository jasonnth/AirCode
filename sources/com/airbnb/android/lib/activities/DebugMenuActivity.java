package com.airbnb.android.lib.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.app.ActionBar;
import android.support.p002v7.app.AlertDialog;
import android.support.p002v7.app.AlertDialog.Builder;
import android.support.p002v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.contentframework.fragments.StoryDetailViewFragment;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.activities.AutoAirActivity;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.enums.SpaceType;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.identity.IdentityReactNativeInfoType;
import com.airbnb.android.core.intents.CheckinIntents;
import com.airbnb.android.core.intents.InboxActivityIntents;
import com.airbnb.android.core.intents.P3ActivityIntents;
import com.airbnb.android.core.intents.QuickPayActivityIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.intents.UserProfileIntents;
import com.airbnb.android.core.messaging.MessagingRequestFactory;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PartialListing;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.models.ReviewRole;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.payments.models.BillProductType;
import com.airbnb.android.core.payments.models.CartItem;
import com.airbnb.android.core.payments.models.PaymentMethodType;
import com.airbnb.android.core.payments.models.clientparameters.ResyClientParameters;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.DebugSettings.BooleanDebugSetting;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.PushHelper;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.itinerary.data.ItineraryTableOpenHelper;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.DebugSettingsVerifiedId;
import com.airbnb.android.lib.fragments.DebugVerificationsDialogFragment;
import com.airbnb.android.lib.fragments.EndpointSelectorDialogFragment;
import com.airbnb.android.lib.fragments.EndpointSelectorDialogFragment.OnEndpointSelectedListener;
import com.airbnb.android.lib.payments.addpayment.activities.AddPaymentMethodActivity;
import com.airbnb.android.lib.payments.factories.PaymentOptionFactory;
import com.airbnb.android.lib.postbooking.MTPostHomeBookingListFragment;
import com.airbnb.android.lib.reviews.activities.WriteReviewActivity;
import com.airbnb.android.lib.utils.erf.ErfOverrideActivity;
import com.airbnb.android.lib.views.GroupedCell;
import com.airbnb.android.lib.views.GroupedCheck;
import com.airbnb.android.referrals.ReferralsActivity;
import com.airbnb.android.superhero.SuperHeroTableOpenHelper;
import com.airbnb.android.utils.ParcelableStringMap;
import com.airbnb.p027n2.browser.DLSComponentBrowserActivity;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.facebook.common.util.UriUtil;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.spongycastle.asn1.eac.EACTags;
import p005cn.jpush.android.JPushConstants;

public class DebugMenuActivity extends AppCompatActivity {
    private static final String DIALOG_SWITCH_ENDPOINT = "switch_endpoint";
    AirbnbAccountManager accountManager;
    AirbnbApi airbnbApi;
    @BindView
    EditText debugMenuFilter;
    @BindView
    LinearLayout debugMenuItemContainer;
    DebugSettings debugSettings;
    ItineraryTableOpenHelper itineraryTableOpenHelper;
    MessagingRequestFactory messagingRequestFactory;
    PaymentOptionFactory paymentOptionFactory;
    SharedPrefsHelper prefsHelper;
    @BindView
    ScrollView scrollView;
    SuperHeroTableOpenHelper superHeroTableOpenHelper;
    @BindView
    View switchServerLayout;
    private TextView switchServerSubtitle;

    public interface OnUserInputListener {
        void onInput(String str);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(5);
        if (MiscUtils.isUserAMonkey()) {
            finish();
            return;
        }
        setContentView(C0880R.layout.debug_menu);
        ButterKnife.bind((Activity) this);
        ((AirbnbGraph) AirbnbApplication.instance(this).component()).inject(this);
        setupActionBar();
        ((TextView) ButterKnife.findById(this.switchServerLayout, C0880R.C0882id.txt_title)).setText(C0880R.string.debug_menu_choose_server);
        ButterKnife.findById(this.switchServerLayout, C0880R.C0882id.img_icon).setVisibility(8);
        this.switchServerSubtitle = (TextView) ButterKnife.findById(this.switchServerLayout, C0880R.C0882id.txt_desc);
        updateServerEndpoint();
        ((GroupedCell) ButterKnife.findById((Activity) this, C0880R.C0882id.show_git_sha)).setContent((CharSequence) BuildHelper.gitSha());
        String token = PushHelper.newInstance(this).getCachedRegistrationIdSafe();
        if (token != null) {
            int tokenLength = token.length();
            String lastSix = token.substring(Math.max(0, tokenLength - 6), tokenLength);
            if (!TextUtils.isEmpty(lastSix)) {
                ((GroupedCell) ButterKnife.findById((Activity) this, C0880R.C0882id.gcm_token)).setContent((CharSequence) "…" + lastSix);
            }
        }
        DebugSettings debugSettings2 = this.debugSettings;
        for (BooleanDebugSetting debugSetting : DebugSettings.debugSettingsList) {
            GroupedCheck checkView = (GroupedCheck) getLayoutInflater().inflate(C0880R.layout.view_holder_debug_menu_grouped_cell, this.debugMenuItemContainer, false);
            checkView.setTitle(debugSetting.titleRes);
            checkView.setChecked(debugSetting.isEnabled());
            checkView.setOnCheckedChangeListener(DebugMenuActivity$$Lambda$1.lambdaFactory$(debugSetting));
            this.debugMenuItemContainer.addView(checkView);
        }
    }

    /* access modifiers changed from: 0000 */
    @OnTextChanged
    public void filterDebugMenuItems(CharSequence filter) {
        String title;
        String filterString = filter.toString().toLowerCase();
        for (int i = this.debugMenuItemContainer.getChildCount() - 1; i >= 0; i--) {
            View child = this.debugMenuItemContainer.getChildAt(i);
            if (child instanceof GroupedCheck) {
                title = ((GroupedCheck) child).getTitle().getText().toString().toLowerCase();
            } else if (child instanceof GroupedCell) {
                title = ((GroupedCell) child).getTitle().getText().toString().toLowerCase();
            } else {
                title = "";
            }
            ViewLibUtils.setVisibleIf(child, title.contains(filterString));
        }
    }

    @OnClick
    public void onClickOverrideErfLayout() {
        startActivity(ErfOverrideActivity.newIntent(this));
    }

    @OnClick
    public void onClickDLSComponentBrowser() {
        startActivity(DLSComponentBrowserActivity.newIntent(this));
    }

    @SuppressLint({"RestrictedApi"})
    @OnClick
    public void onClickGoToListing() {
        EditText listingIdInput = new EditText(this);
        listingIdInput.setInputType(2);
        AlertDialog alertDialog = new Builder(this).setTitle((CharSequence) "Listing id:").setView(listingIdInput, 90, 30, 90, 0).setPositiveButton((CharSequence) "Go", DebugMenuActivity$$Lambda$2.lambdaFactory$(this, listingIdInput)).create();
        alertDialog.getWindow().setSoftInputMode(4);
        alertDialog.show();
    }

    static /* synthetic */ void lambda$onClickGoToListing$1(DebugMenuActivity debugMenuActivity, EditText listingIdInput, DialogInterface dialog, int id) {
        if (!TextUtils.isEmpty(listingIdInput.getText().toString())) {
            debugMenuActivity.startActivity(P3ActivityIntents.withListingId(debugMenuActivity, Long.valueOf(listingIdInput.getText().toString()).longValue()));
        }
    }

    @OnClick
    public void onClickGoToExperience() {
        View view = LayoutInflater.from(this).inflate(C0880R.layout.debug_menu_go_to_experience, null);
        EditText experienceIdInput = (EditText) view.findViewById(C0880R.C0882id.experience_id_input);
        experienceIdInput.setInputType(2);
        GroupedCheck check = (GroupedCheck) view.findViewById(C0880R.C0882id.immersion_check);
        check.setTitle("Is Immersion");
        boolean[] isImmersion = {false};
        check.setOnCheckedChangeListener(DebugMenuActivity$$Lambda$3.lambdaFactory$(isImmersion));
        AlertDialog alertDialog = new Builder(this).setTitle((CharSequence) "Go to experience").setView(view).setPositiveButton((CharSequence) "Go", DebugMenuActivity$$Lambda$4.lambdaFactory$(this, experienceIdInput, isImmersion)).create();
        alertDialog.getWindow().setSoftInputMode(4);
        alertDialog.show();
    }

    static /* synthetic */ void lambda$onClickGoToExperience$2(boolean[] isImmersion, CompoundButton checkView, boolean isChecked) {
        isImmersion[0] = isChecked;
    }

    static /* synthetic */ void lambda$onClickGoToExperience$3(DebugMenuActivity debugMenuActivity, EditText experienceIdInput, boolean[] isImmersion, DialogInterface dialog, int id) {
        if (!TextUtils.isEmpty(experienceIdInput.getText().toString())) {
            debugMenuActivity.startActivity(ReactNativeIntents.intentForExperiencePDP((Context) debugMenuActivity, isImmersion[0], Long.valueOf(experienceIdInput.getText().toString()).longValue()));
        }
    }

    @OnClick
    public void onClickGoToArticle() {
        View view = LayoutInflater.from(this).inflate(C0880R.layout.debug_menu_go_to_article, null);
        ((Button) view.findViewById(C0880R.C0882id.native_button)).setOnClickListener(DebugMenuActivity$$Lambda$5.lambdaFactory$(this, (EditText) view.findViewById(C0880R.C0882id.article_id_input)));
        AlertDialog alertDialog = new Builder(this).setTitle((CharSequence) "Article info:").setView(view).create();
        alertDialog.getWindow().setSoftInputMode(4);
        alertDialog.show();
    }

    static /* synthetic */ void lambda$onClickGoToArticle$4(DebugMenuActivity debugMenuActivity, EditText articleIdInput, View v) {
        if (!TextUtils.isEmpty(articleIdInput.getText().toString())) {
            debugMenuActivity.startActivity(StoryDetailViewFragment.forArticleId(debugMenuActivity, Long.valueOf(articleIdInput.getText().toString()).longValue(), null));
        }
    }

    @OnClick
    public void onClickGoToTripAssistant() {
        alertForPromptAndExecute(getString(C0880R.string.debug_trip_assistant_id), 2, DebugMenuActivity$$Lambda$6.lambdaFactory$(this));
    }

    @SuppressLint({"RestrictedApi"})
    @OnClick
    public void onClickViewCheckin() {
        EditText listingIdInput = new EditText(this);
        listingIdInput.setInputType(2);
        AlertDialog alertDialog = new Builder(this).setTitle((CharSequence) "Listing id:").setView(listingIdInput, 90, 30, 90, 0).setPositiveButton((CharSequence) "Go", DebugMenuActivity$$Lambda$7.lambdaFactory$(this, listingIdInput)).create();
        alertDialog.getWindow().setSoftInputMode(4);
        alertDialog.show();
    }

    static /* synthetic */ void lambda$onClickViewCheckin$6(DebugMenuActivity debugMenuActivity, EditText listingIdInput, DialogInterface dialog, int id) {
        if (!TextUtils.isEmpty(listingIdInput.getText().toString())) {
            debugMenuActivity.startActivity(CheckinIntents.intentForListingId(debugMenuActivity, Long.valueOf(listingIdInput.getText().toString()).longValue()));
        }
    }

    @OnClick
    public void onClickViewPHB() {
        startActivity(TransparentActionBarActivity.intentForFragment(this, MTPostHomeBookingListFragment.newInstance("344959998")));
    }

    @OnClick
    public void showBuildConfig() {
        new Builder(this).setTitle((CharSequence) "Build Config:").setMessage((CharSequence) String.format("DEBUG=%s \nAPPLICATION_ID=%s \nBUILD_TYPE=%s \nVERSION_NAME=%s \nVERSION_CODE=%s \nGIT_SHA=%s \nGIT_BRANCH=%s", new Object[]{Boolean.valueOf(BuildHelper.isDevelopmentBuild()), BuildHelper.applicationId(), BuildHelper.buildType(), BuildHelper.versionName(), Integer.valueOf(BuildHelper.versionCode()), BuildHelper.gitSha(), BuildHelper.gitBranch()})).create().show();
    }

    @OnClick
    public void onClickOverrideTrebuchetLayout() {
        startActivity(TrebuchetOverrideActivity.intentForDefault(this));
    }

    @OnClick
    public void onClickRequestDebugging() {
        startActivity(RequestTestSuiteActivity.newIntent(this));
    }

    @OnClick
    public void onClickSetUserVerifications() {
        startActivity(AutoAirActivity.intentForFragment(this, DebugSettingsVerifiedId.class, null));
    }

    @OnClick
    public void onClickLaunchVerificationsFlow() {
        DebugVerificationsDialogFragment.newInstance().show(getSupportFragmentManager(), (String) null);
    }

    @OnClick
    public void onClickLaunchNewP5() {
        showPostBookingDebugDialog();
    }

    @OnClick
    public void onClickClearMessageStorage() {
        this.messagingRequestFactory.clearAllUserData();
        Toast.makeText(this, C0880R.string.done, 0).show();
    }

    private void showPostBookingDebugDialog() {
        String[] items = {"IB with 1 guest", "IB with many guests", "RTB with 1 guest", "RTB with many guests"};
        Reservation[] Reservations = buildDebugReservations();
        Builder builder = new Builder(this);
        builder.setTitle((CharSequence) "Pick a reservation flow you want to see");
        int[] selected = {-1};
        builder.setSingleChoiceItems((CharSequence[]) items, selected[0], DebugMenuActivity$$Lambda$8.lambdaFactory$(selected)).setPositiveButton((CharSequence) "Done!", DebugMenuActivity$$Lambda$9.lambdaFactory$(this, Reservations, selected)).setNegativeButton((CharSequence) "Cancel", DebugMenuActivity$$Lambda$10.lambdaFactory$());
        builder.create().show();
    }

    static /* synthetic */ void lambda$showPostBookingDebugDialog$7(int[] selected, DialogInterface dialog, int which) {
        selected[0] = which;
    }

    static /* synthetic */ void lambda$showPostBookingDebugDialog$9(DialogInterface dialog1, int id) {
    }

    private static Reservation[] buildDebugReservations() {
        Reservation instantBookingReservationSingleGuest = new Reservation();
        instantBookingReservationSingleGuest.setInstantBooked(true);
        instantBookingReservationSingleGuest.setGuestCount(1);
        Reservation instantBookingReservationMultiGuests = new Reservation();
        instantBookingReservationMultiGuests.setInstantBooked(true);
        instantBookingReservationMultiGuests.setGuestCount(3);
        Reservation requestToBookingReservationSingleGuest = new Reservation();
        requestToBookingReservationSingleGuest.setInstantBooked(false);
        requestToBookingReservationSingleGuest.setGuestCount(1);
        Reservation requestToBookingReservationMultiGuest = new Reservation();
        requestToBookingReservationMultiGuest.setInstantBooked(false);
        requestToBookingReservationMultiGuest.setGuestCount(3);
        Reservation[] res = {instantBookingReservationSingleGuest, instantBookingReservationMultiGuests, requestToBookingReservationSingleGuest, requestToBookingReservationMultiGuest};
        Listing debugListing = new Listing();
        debugListing.setCity("Jinan");
        for (Reservation reservation : res) {
            reservation.setListing(debugListing);
        }
        return res;
    }

    @OnClick
    public void onTestSuperhero() {
        startActivity(HomeActivityIntents.intentForSuperHero((Context) this, -111));
    }

    @OnClick
    public void onClearItineraryCache() {
        this.itineraryTableOpenHelper.clearAll();
    }

    @OnClick
    public void onClearSuperHeroCache() {
        this.superHeroTableOpenHelper.clearAll();
    }

    @OnClick
    public void onClickForceProfile() {
        String[] items = {"Default", "No work or school", "Many languages", "No reviews", "No verifications"};
        User randomUser = new User();
        randomUser.setId(1337);
        randomUser.setPictureUrl("https://a2.muscache.com/im/pictures/858bb413-4359-40c6-95c2-7af3598eb7a6.jpg?aki_policy=profile_x_medium");
        randomUser.setPictureUrlLarge("https://a2.muscache.com/im/pictures/858bb413-4359-40c6-95c2-7af3598eb7a6.jpg?aki_policy=profile_large");
        randomUser.setBirthdate(AirDate.fromISODateString("1981-01-01"));
        randomUser.setCreatedAt(AirDateTime.now().plusMinutes(-4204800));
        randomUser.setName("Brian Chesky");
        randomUser.setAbout("I am originally from NY, and have been living in San Francisco since October, 2007. I have been living here ever since.");
        Review review = new Review();
        User reviewUser = new User();
        reviewUser.setFirstName("Gabriel");
        reviewUser.setThumbnailUrl("https://a2.muscache.com/im/pictures/5747d527-6a49-460d-8acf-823004fd7a04.jpg?aki_policy=profile_x_medium");
        review.setAuthor(reviewUser);
        review.setCreatedAt(AirDateTime.now().plusMinutes(-525600));
        review.setPublicFeedback("Brian was an awesome guest, took good care of our property and communicated from start to finish. We would definitely recommend him.");
        randomUser.setRecentReview(review);
        randomUser.setRevieweeCount(EACTags.COEXISTANT_TAG_ALLOCATION_AUTHORITY);
        randomUser.setVerificationLabels(ImmutableList.m1288of("Email address", "Phone number", "Reviewed", "Offline ID"));
        randomUser.setLanguages(ImmutableList.m1286of("English", "Spanish"));
        new Builder(this).setTitle((CharSequence) "Pick a marquee type.").setSingleChoiceItems((CharSequence[]) items, 0, DebugMenuActivity$$Lambda$11.lambdaFactory$(this, randomUser)).show();
    }

    static /* synthetic */ void lambda$onClickForceProfile$10(DebugMenuActivity debugMenuActivity, User randomUser, DialogInterface dialog, int which) {
        switch (which) {
            case 1:
                randomUser.setWork(null);
                randomUser.setSchool(null);
                break;
            case 2:
                randomUser.setLanguages(ImmutableList.m1292of("English", "Spanish", "French", "German", "Japenese", "Chinese", "Vietnamese", "Dutch"));
                break;
            case 3:
                randomUser.setRevieweeCount(0);
                randomUser.setReview(null);
                break;
            case 4:
                randomUser.setVerificationLabels(null);
                break;
        }
        debugMenuActivity.startActivity(UserProfileIntents.intentForUser(debugMenuActivity, randomUser));
    }

    @OnClick
    public void onClickLaunchRNGuidebooks() {
        startActivity(ReactNativeIntents.intentForGuidebook(this, getString(C0880R.string.airbnb_base_url) + "/things-to-do/new-york"));
    }

    @OnClick
    public void onClickLaunchRNGiftCards() {
        startActivity(ReactNativeIntents.intentForGiftCardsApp(this));
    }

    @OnClick
    public void onClickLaunchAddPaymentMethod() {
        List<PaymentOption> options = new ArrayList<>();
        options.add(this.paymentOptionFactory.createSkeletonPaymentOption(PaymentMethodType.CreditCard));
        startActivity(AddPaymentMethodActivity.intentForAddPayment(this, BillProductType.Homes, options));
    }

    @OnClick
    public void onClickLaunchRNGuidebooksSubcategory() {
        ParcelableStringMap queryParams = new ParcelableStringMap();
        queryParams.put("gpsLat", "37.7879");
        queryParams.put("gpsLng", "-122.4075");
        startActivity(ReactNativeIntents.intentForGuidebookSubcategory(this, queryParams, null));
    }

    @OnClick
    public void onClickLaunchRNGuidebookInsider() {
        startActivity(ReactNativeIntents.intentForGuidebookInsider(this, 1, "https://maps.googleapis.com/maps/api/place/photo?key=AIzaSyAdY0BdS9M2QPaGwjYD7_QuNLD1tv6IcRI&photoreference=CoQBdwAAACU41efZsjC1XYHV1TekFCaeO3xg8dCLSbUqnUQBRvlZNeyGtX0nB9DNrdhNxxGAaOToO6pPRNqKJXpC2sLrx75L2farcB6W_JGOX-qeoLn2b8YekRUFBraBplwQ6iXh_wScpcJ42JCapsyoYHqZHGDOPVlQvL7I7MSOfdpYPQYMEhBUwnsMi2C7Cp6YgjLP7_sQGhSNhiRFxhAumzuQDzHlREoSMUlwoA&maxheight=640&maxwidth=640", "Insider Name", null));
    }

    @OnClick
    public void onClickLaunchRNGuidebookDetour() {
        ParcelableStringMap queryParams = new ParcelableStringMap();
        queryParams.put("id", "1");
        startActivity(ReactNativeIntents.intentForGuidebookDetour(this, (String) queryParams.get("id"), "Little Tokyo: A Journey Home", null));
    }

    @OnClick
    public void onClickLaunchRNGuidebookMeetupCollection() {
        ParcelableStringMap queryParams = new ParcelableStringMap();
        queryParams.put("city", "Los Angeles");
        startActivity(ReactNativeIntents.intentForGuidebookMeetupCollection(this, queryParams, null));
    }

    @OnClick
    public void onClickLaunchRNExplorer() {
        startActivity(ReactNativeIntents.intentForDLSExplorer(this));
    }

    @OnClick
    public void onClickLaunchRNAppsMenu() {
        startActivity(ReactNativeIntents.intentForRNAppsMenu(this));
    }

    @OnClick
    public void onClickLaunchSupportChat() {
        startActivity(ReactNativeIntents.intentForSupportChat(this));
    }

    @OnClick
    public void onClickLaunchNewVerificationFlow() {
        showVerificationFlowSelectionDialog();
    }

    @OnClick
    public void onClickLaunchRedesignedReferrals() {
        startActivity(new Intent(this, ReferralsActivity.class));
    }

    private void showVerificationFlowSelectionDialog() {
        VerificationFlow[] flows = VerificationFlow.values();
        ArrayList<String> items = new ArrayList<>();
        for (VerificationFlow name : flows) {
            items.add(name.name());
        }
        Collections.sort(items);
        Builder builder = new Builder(this);
        builder.setTitle((CharSequence) "Choose your identity context");
        int[] selected = {-1};
        builder.setSingleChoiceItems((CharSequence[]) items.toArray(new String[0]), selected[0], DebugMenuActivity$$Lambda$12.lambdaFactory$(selected)).setPositiveButton((CharSequence) "Done!", DebugMenuActivity$$Lambda$13.lambdaFactory$(this, items, selected)).setNegativeButton((CharSequence) "Cancel", DebugMenuActivity$$Lambda$14.lambdaFactory$());
        builder.create().show();
    }

    static /* synthetic */ void lambda$showVerificationFlowSelectionDialog$11(int[] selected, DialogInterface dialog, int which) {
        selected[0] = which;
    }

    static /* synthetic */ void lambda$showVerificationFlowSelectionDialog$13(DialogInterface dialog1, int id) {
    }

    /* access modifiers changed from: private */
    public void showVerificationStepSelectionDialog(VerificationFlow flow) {
        List<AccountVerificationStep> requiredSteps = new ArrayList<>(ImmutableList.m1289of(AccountVerificationStep.ProfilePhoto, AccountVerificationStep.Phone, AccountVerificationStep.Email, AccountVerificationStep.OfflineId, AccountVerificationStep.Selfie));
        List<String> requiredStepStrings = new ArrayList<>();
        ArrayList<AccountVerificationStep> selectedSteps = new ArrayList<>();
        for (AccountVerificationStep step : requiredSteps) {
            requiredStepStrings.add(step.toString());
        }
        String[] items = (String[]) requiredStepStrings.toArray(new String[requiredStepStrings.size()]);
        Builder builder = new Builder(this);
        builder.setTitle((CharSequence) "Pick the step you want to show");
        builder.setMultiChoiceItems((CharSequence[]) items, (boolean[]) null, DebugMenuActivity$$Lambda$15.lambdaFactory$(selectedSteps)).setPositiveButton((CharSequence) "Done!", DebugMenuActivity$$Lambda$16.lambdaFactory$(this, flow, selectedSteps)).setNegativeButton((CharSequence) "Cancel", DebugMenuActivity$$Lambda$17.lambdaFactory$());
        builder.create().show();
    }

    static /* synthetic */ void lambda$showVerificationStepSelectionDialog$14(ArrayList selectedSteps, DialogInterface dialog1, int selectedItemId, boolean isSelected) {
        AccountVerificationStep step = AccountVerificationStep.values()[selectedItemId];
        if (isSelected) {
            selectedSteps.add(step);
        } else if (selectedSteps.contains(step)) {
            selectedSteps.remove(step);
        }
    }

    static /* synthetic */ void lambda$showVerificationStepSelectionDialog$16(DialogInterface dialog1, int id) {
    }

    @OnClick
    public void onClickLaunchReactVerificationFlow() {
        startActivity(ReactNativeIntents.intentForVerifications(this));
    }

    @OnClick
    public void onClickLaunchReactVerificationInfo() {
        IdentityReactNativeInfoType[] types = IdentityReactNativeInfoType.values();
        String[] items = new String[types.length];
        for (int i = 0; i < types.length; i++) {
            items[i] = types[i].name();
        }
        Builder builder = new Builder(this);
        builder.setTitle((CharSequence) "Choose identity step");
        int[] selected = {0};
        builder.setSingleChoiceItems((CharSequence[]) items, selected[0], DebugMenuActivity$$Lambda$18.lambdaFactory$(selected)).setPositiveButton((CharSequence) "Done!", DebugMenuActivity$$Lambda$19.lambdaFactory$(this, items, selected)).setNegativeButton((CharSequence) "Cancel", DebugMenuActivity$$Lambda$20.lambdaFactory$());
        builder.create().show();
    }

    static /* synthetic */ void lambda$onClickLaunchReactVerificationInfo$17(int[] selected, DialogInterface dialog, int which) {
        selected[0] = which;
    }

    static /* synthetic */ void lambda$onClickLaunchReactVerificationInfo$19(DialogInterface dialog1, int id) {
    }

    @OnClick
    public void onClickGitShaCell() {
        MiscUtils.copyToClipboard(this, BuildHelper.gitSha());
    }

    @OnClick
    public void onClickGcmTokenCell() {
        MiscUtils.copyToClipboard(this, PushHelper.newInstance(this).getCachedRegistrationIdSafe());
    }

    @OnClick
    public void onShowExperienceHostInbox() {
        startActivity(InboxActivityIntents.intentForInbox(this, InboxType.ExperienceHost));
    }

    @OnClick
    public void onClickWriteReview() {
        if (this.accountManager.getCurrentUser() == null) {
            Toast.makeText(this, getText(C0880R.string.debug_log_in_first), 0).show();
            return;
        }
        new Builder(this).setTitle((CharSequence) "Review as role:").setSingleChoiceItems((CharSequence[]) new String[]{"Guest reviewing host", "Host reviewing guest"}, -1, DebugMenuActivity$$Lambda$21.lambdaFactory$(this)).show();
    }

    static /* synthetic */ void lambda$onClickWriteReview$20(DebugMenuActivity debugMenuActivity, DialogInterface dialog, int selection) {
        debugMenuActivity.startActivity(WriteReviewActivity.newIntent((Context) debugMenuActivity, generateFakeReview(debugMenuActivity.accountManager.getCurrentUser(), selection == 0 ? ReviewRole.Guest : ReviewRole.Host)));
    }

    @OnClick
    public void onClickAdaptiveReview() {
        if (this.accountManager.getCurrentUser() == null) {
            Toast.makeText(this, getText(C0880R.string.debug_log_in_first), 0).show();
        } else {
            alertForPromptAndExecute(getString(C0880R.string.debug_review_id), 2, DebugMenuActivity$$Lambda$22.lambdaFactory$(this));
        }
    }

    @OnClick
    public void onClickSwitchServer() {
        EndpointSelectorDialogFragment fragment = EndpointSelectorDialogFragment.newInstance();
        fragment.setListener(new OnEndpointSelectedListener() {
            public void onEndpointSelected(String url) {
                DebugMenuActivity.this.setEndpoint(url);
            }

            public void onCustomEndpointSelected() {
                EditText tokenEditText = new EditText(DebugMenuActivity.this);
                new Builder(DebugMenuActivity.this).setTitle(C0880R.string.debug_menu_enter_endpoint_url).setView((View) tokenEditText).setPositiveButton(17039370, DebugMenuActivity$1$$Lambda$1.lambdaFactory$(this, tokenEditText)).setNegativeButton(17039360, (OnClickListener) null).show();
            }

            static /* synthetic */ void lambda$onCustomEndpointSelected$0(C65861 r3, EditText tokenEditText, DialogInterface dialog, int which) {
                String url = tokenEditText.getText().toString().trim();
                if (!TextUtils.isEmpty(url)) {
                    if (!url.startsWith(UriUtil.HTTP_SCHEME)) {
                        url = JPushConstants.HTTP_PRE + url;
                    }
                    if (!url.endsWith("/")) {
                        url = url + "/";
                    }
                    DebugMenuActivity.this.setEndpoint(url);
                }
            }
        });
        fragment.show(getSupportFragmentManager(), DIALOG_SWITCH_ENDPOINT);
    }

    @OnClick
    public void launchMTHostReactNativeActivity() {
        startActivity(ReactNativeIntents.intentForCityHostsManagerApp(this));
    }

    @OnClick
    public void launchResyQuickPay() {
        startActivity(QuickPayActivityIntents.intentForResyReservation(this, getResyTestCartItem(), null));
    }

    @OnClick
    public void launchActivityPDP() {
        startActivity(new Intent(this, DebugActivityPDPActivity.class));
    }

    private CartItem getResyTestCartItem() {
        return CartItem.builder().thumbnailUrl("http://data.whicdn.com/images/8184139/original.jpg").title("Izakaya Roku").description("Wed July 23rd at 7:45pm\n2 guests").quickPayParameters(ResyClientParameters.builder().reservationId(1).numberOfGuests(2).build()).build();
    }

    /* access modifiers changed from: private */
    public void setEndpoint(String url) {
        this.airbnbApi.setEndpointUrl(url);
        Toast.makeText(this, getString(C0880R.string.debug_menu_endpoint_changed, new Object[]{url}), 0).show();
        updateServerEndpoint();
        new Builder(this).setTitle(C0880R.string.debug_menu_restart_app_dialog_title).setMessage(C0880R.string.debug_menu_restart_app_dialog_message).setPositiveButton(17039370, DebugMenuActivity$$Lambda$23.lambdaFactory$()).setNegativeButton(17039360, (OnClickListener) null).show();
    }

    static /* synthetic */ void lambda$setEndpoint$22(DialogInterface dialog, int which) {
        throw new RuntimeException("Endpoint changed, force closing");
    }

    private void updateServerEndpoint() {
        this.switchServerSubtitle.setText(AirbnbApi.API_ENDPOINT_URL);
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(C0880R.string.debug_menu_title);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        finish();
        return true;
    }

    @SuppressLint({"RestrictedApi"})
    private void alertForPromptAndExecute(String inputPrompt, int inputType, OnUserInputListener action) {
        EditText userInput = new EditText(this);
        userInput.setInputType(inputType);
        AlertDialog alertDialog = new Builder(this).setTitle((CharSequence) inputPrompt).setView(userInput, getResources().getDimensionPixelSize(C0880R.dimen.debug_prompt_space_left), getResources().getDimensionPixelSize(C0880R.dimen.debug_prompt_space_top), getResources().getDimensionPixelSize(C0880R.dimen.debug_prompt_space_right), getResources().getDimensionPixelSize(C0880R.dimen.debug_prompt_space_bottom)).setPositiveButton((CharSequence) "Go", DebugMenuActivity$$Lambda$24.lambdaFactory$(userInput, action)).create();
        alertDialog.getWindow().setSoftInputMode(4);
        alertDialog.show();
    }

    static /* synthetic */ void lambda$alertForPromptAndExecute$23(EditText userInput, OnUserInputListener action, DialogInterface dialog, int id) {
        if (!TextUtils.isEmpty(userInput.getText().toString())) {
            action.onInput(userInput.getText().toString());
        }
    }

    private static Review generateFakeReview(User currentUser, ReviewRole role) {
        User reviewee = new User();
        reviewee.setFirstName("Prometheus");
        reviewee.setPictureUrl("https://a2.muscache.com/im/users/21373036/profile_pic/1410814030/original.jpg?aki_policy=profile_x_medium");
        reviewee.setCreatedAt(currentUser.getCreatedAt());
        PartialListing partialListing = new PartialListing();
        partialListing.setName("Buckingham Palace");
        partialListing.setListingId(1);
        Listing listing = new Listing();
        listing.setName("Buckingham Palace");
        listing.setCity("London, England");
        listing.setPictureUrl("https://a2.muscache.com/im/pictures/f91801fc-2e2c-4417-985f-ffd2d20e552e.jpg?aki_policy=large");
        listing.setThumbnailUrl("https://a2.muscache.com/im/pictures/f91801fc-2e2c-4417-985f-ffd2d20e552e.jpg?aki_policy=small");
        listing.setRoomTypeKey(SpaceType.EntireHome.serverDescKey);
        Reservation reservation = new Reservation();
        reservation.setStartDate(AirDate.today().plusWeeks(-1));
        reservation.setReservedNightsCount(3);
        reservation.setListing(listing);
        boolean isReviewAsGuest = ReviewRole.findRole(role.apiString) == ReviewRole.Guest;
        if (isReviewAsGuest) {
            reservation.setGuest(currentUser);
            reviewee.setRevieweeCount(12);
            reservation.setHost(reviewee);
        } else {
            reservation.setGuest(reviewee);
            reservation.setHost(currentUser);
        }
        Review review = new Review();
        review.setId(999999999);
        review.setReviewee(reviewee);
        review.setReviewer(currentUser);
        review.setPartialListing(partialListing);
        review.setReservation(reservation);
        review.setReviewRole(role.apiString);
        review.setCreatedAt(AirDateTime.now().plusDays(-4));
        review.setPublicFeedback(isReviewAsGuest ? "Absolutely loved staying in the apartment. It was perfect with a beautiful view!" : "Great guest!");
        return review;
    }

    @OnClick
    public void goToMythbustersQuiz() {
        startActivity(new Intent(this, Activities.mythbusters()));
    }
}
