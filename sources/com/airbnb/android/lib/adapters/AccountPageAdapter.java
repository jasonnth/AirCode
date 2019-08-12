package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import com.airbnb.android.contentframework.StoriesExperimentUtil;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.businesstravel.BusinessTravelUtils;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.host.ListingPromoController;
import com.airbnb.android.core.models.ListingPickerInfo;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.HomeActivity.AccountMode;
import com.airbnb.android.lib.fragments.AccountPageFragment.AccountPageItem;
import com.airbnb.android.lib.utils.GiftCardUtils;
import com.airbnb.android.listing.utils.ListingProgressUtils;
import com.airbnb.android.profile_completion.CompletionStep;
import com.airbnb.android.profile_completion.ProfileCompletionGraph;
import com.airbnb.android.profile_completion.ProfileCompletionHelper;
import com.airbnb.android.profile_completion.ProfileCompletionManager;
import com.airbnb.android.profile_completion.models.ProfileCompletionBarRowEpoxyModel_;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.components.ListingInfoRowModel_;
import com.airbnb.p027n2.utils.TextUtil;
import java.util.Locale;

public class AccountPageAdapter extends AirEpoxyAdapter implements com.airbnb.android.core.host.ListingPromoController.Listener {
    private static final int HIDE_PROFILE_COMPLETION_WHEN_DONE_DELAY = 2000;
    private final AirbnbAccountManager accountManager;
    private final AccountMode accountMode;
    private final AirbnbApi airbnbApi;
    private final Context context;
    private final ListingPromoController controller;
    StandardRowEpoxyModel_ feedbackModel = new StandardRowEpoxyModel_().titleRes(C0880R.string.give_feedback).rowDrawableRes(C0880R.C0881drawable.paper_plane);
    StandardRowEpoxyModel_ giftCardsModel = new StandardRowEpoxyModel_().titleRes(C0880R.string.account_page_gift_cards).rowDrawableRes(C0880R.C0881drawable.gift_card);
    StandardRowEpoxyModel_ giftsModel = new StandardRowEpoxyModel_().titleRes(C0880R.string.account_page_gifts).rowDrawableRes(C0880R.C0881drawable.gift_card);
    StandardRowEpoxyModel_ groupsModel = new StandardRowEpoxyModel_().titleRes(C0880R.string.community_center).rowDrawableRes(C0880R.C0881drawable.group).hide();
    StandardRowEpoxyModel_ helpModel = new StandardRowEpoxyModel_().titleRes(C0880R.string.menu_help).rowDrawableRes(C0880R.C0881drawable.lifesaver);
    StandardRowEpoxyModel_ hostReferralModel = new StandardRowEpoxyModel_().titleRes(C0880R.string.refer_a_host).rowDrawableRes(C0880R.C0881drawable.gift);
    StandardRowEpoxyModel_ internalSettingsModel = new StandardRowEpoxyModel_().titleRes(C0880R.string.internal_settings).rowDrawableRes(C0880R.C0881drawable.cog).hide();
    StandardRowEpoxyModel_ inviteFriendsModel = new StandardRowEpoxyModel_().titleRes(C0880R.string.invite_friends).rowDrawableRes(C0880R.C0881drawable.gift);
    StandardRowEpoxyModel_ koreanCancellationPolicyModel = new StandardRowEpoxyModel_().titleRes(C0880R.string.korean_cancellation_policy).rowDrawableRes(C0880R.C0881drawable.lifesaver).hide();
    StandardRowEpoxyModel_ listYourSpaceModel = new StandardRowEpoxyModel_().rowDrawableRes(C0880R.C0881drawable.add_listing).hide();
    private final Listener listener;
    private final ProfileCompletionManager profileCompletionManager;
    ProfileCompletionBarRowEpoxyModel_ profileCompletionModel = new ProfileCompletionBarRowEpoxyModel_().progressLabelVisible(false).hide();
    StandardRowEpoxyModel_ settingsModel = new StandardRowEpoxyModel_().titleRes(C0880R.string.account_settings).rowDrawableRes(C0880R.C0881drawable.cog);
    private final SharedPrefsHelper sharedPrefsHelper;
    StandardRowEpoxyModel_ switchToHostModel = new StandardRowEpoxyModel_().titleRes(C0880R.string.menu_switch_to_host_mode).rowDrawableRes(C0880R.C0881drawable.switch_mode).hide();
    StandardRowEpoxyModel_ switchToTravelModel = new StandardRowEpoxyModel_().titleRes(C0880R.string.menu_switch_to_travel_mode).rowDrawableRes(C0880R.C0881drawable.switch_mode).hide();
    StandardRowEpoxyModel_ switchToTripHostModel = new StandardRowEpoxyModel_().titleRes(C0880R.string.menu_switch_to_trip_host_mode).rowDrawableRes(C0880R.C0881drawable.switch_mode).hide();
    StandardRowEpoxyModel_ travelForWorkModel = new StandardRowEpoxyModel_().titleRes(C0880R.string.bt_travel_for_work_title).disclosure().hide();
    StandardRowEpoxyModel_ tripsModel = new StandardRowEpoxyModel_().titleRes(C0880R.string.title_trips).rowDrawableRes(C0880R.C0881drawable.ic_trips_row).hide();
    ListingInfoRowModel_ unfinishedListingModel = new ListingInfoRowModel_().hide();
    DocumentMarqueeEpoxyModel_ userProfileModel = new DocumentMarqueeEpoxyModel_().captionRes(C0880R.string.view_and_edit_profile).hide();

    public interface Listener {
        void continueNewListing(long j);

        void itemSelected(AccountPageItem accountPageItem);
    }

    public AccountPageAdapter(Context context2, AirbnbAccountManager accountManager2, AccountMode accountMode2, AirbnbApi airbnbApi2, ProfileCompletionManager profileCompletionManager2, SharedPrefsHelper sharedPrefsHelper2, ListingPromoController controller2, Listener listener2) {
        super(true);
        this.context = context2;
        this.accountManager = accountManager2;
        this.accountMode = accountMode2;
        this.profileCompletionManager = profileCompletionManager2;
        this.airbnbApi = airbnbApi2;
        this.sharedPrefsHelper = sharedPrefsHelper2;
        this.controller = controller2;
        this.listener = listener2;
        enableDiffing();
        initModels();
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.userProfileModel, this.profileCompletionModel, this.unfinishedListingModel, this.travelForWorkModel, this.tripsModel, this.inviteFriendsModel, this.hostReferralModel, this.switchToTravelModel, this.switchToHostModel, this.switchToTripHostModel, this.giftCardsModel, this.giftsModel, this.listYourSpaceModel, this.internalSettingsModel, this.settingsModel, this.helpModel, this.groupsModel, this.feedbackModel, this.koreanCancellationPolicyModel});
        controller2.addListener(this);
    }

    private void initModels() {
        boolean z;
        boolean shouldShowTravelForWork;
        boolean z2 = false;
        if (this.accountMode == AccountMode.HOST) {
            this.groupsModel.show(shouldShowCommunityCenter());
        }
        this.inviteFriendsModel.show();
        this.hostReferralModel.titleRes(C0880R.string.refer_a_host).show(Experiments.showHostReferrals());
        StandardRowEpoxyModel_ standardRowEpoxyModel_ = this.switchToTravelModel;
        if (this.accountMode != AccountMode.GUEST) {
            z = true;
        } else {
            z = false;
        }
        standardRowEpoxyModel_.show(z);
        this.internalSettingsModel.show(BuildHelper.isInternalSettingsEnabled());
        if (this.accountMode != AccountMode.GUEST || !BusinessTravelUtils.shouldShowTravelForWork(this.accountManager.getCurrentUser())) {
            shouldShowTravelForWork = false;
        } else {
            shouldShowTravelForWork = true;
        }
        if (shouldShowTravelForWork) {
            this.travelForWorkModel.show();
            this.listener.itemSelected(AccountPageItem.TrackTravelForWork);
        }
        if (GiftCardUtils.isGiftCardsEnabled()) {
            this.giftsModel.show();
            this.giftCardsModel.hide();
        } else {
            this.giftsModel.hide();
            this.giftCardsModel.show(GiftCardUtils.isGiftCreditEnabled(this.context));
        }
        if (this.accountManager.isCurrentUserAuthorized()) {
            User user = this.accountManager.getCurrentUser();
            this.userProfileModel.titleText((CharSequence) user.getFirstName()).userImageUrl(user.getPictureUrl()).show();
            if (this.accountMode != AccountMode.HOST && this.accountManager.userHasListings()) {
                this.switchToHostModel.show();
            }
            if (this.accountMode != AccountMode.TRIP_HOST && user.isTripHost()) {
                this.switchToTripHostModel.show();
            }
            StandardRowEpoxyModel_ titleRes = this.listYourSpaceModel.titleRes(getListYourSpaceWording());
            if (!this.accountManager.userHasListings() || Experiments.showNewListingAllTheTime()) {
                z2 = true;
            }
            titleRes.show(z2);
        }
        if (Locale.getDefault().getLanguage().equals(AirbnbConstants.LANGUAGE_CODE_KOREA) && FeatureToggles.showKoreanCancellationPolicy()) {
            this.koreanCancellationPolicyModel.show();
        }
        this.userProfileModel.clickListener(AccountPageAdapter$$Lambda$1.lambdaFactory$(this));
        this.switchToTravelModel.clickListener(AccountPageAdapter$$Lambda$2.lambdaFactory$(this));
        this.switchToHostModel.clickListener(AccountPageAdapter$$Lambda$3.lambdaFactory$(this));
        this.switchToTripHostModel.clickListener(AccountPageAdapter$$Lambda$4.lambdaFactory$(this));
        this.giftCardsModel.clickListener(AccountPageAdapter$$Lambda$5.lambdaFactory$(this));
        this.giftsModel.clickListener(AccountPageAdapter$$Lambda$6.lambdaFactory$(this));
        this.listYourSpaceModel.clickListener(AccountPageAdapter$$Lambda$7.lambdaFactory$(this));
        this.internalSettingsModel.clickListener(AccountPageAdapter$$Lambda$8.lambdaFactory$(this));
        this.settingsModel.clickListener(AccountPageAdapter$$Lambda$9.lambdaFactory$(this));
        this.helpModel.clickListener(AccountPageAdapter$$Lambda$10.lambdaFactory$(this));
        this.inviteFriendsModel.clickListener(AccountPageAdapter$$Lambda$11.lambdaFactory$(this));
        this.hostReferralModel.clickListener(AccountPageAdapter$$Lambda$12.lambdaFactory$(this));
        this.groupsModel.clickListener(AccountPageAdapter$$Lambda$13.lambdaFactory$(this));
        this.feedbackModel.clickListener(AccountPageAdapter$$Lambda$14.lambdaFactory$(this));
        this.koreanCancellationPolicyModel.clickListener(AccountPageAdapter$$Lambda$15.lambdaFactory$(this));
        this.travelForWorkModel.clickListener(AccountPageAdapter$$Lambda$16.lambdaFactory$(this));
        this.inviteFriendsModel.title((CharSequence) CoreApplication.instance().component().resourceManager().getString(C0880R.string.referrals_credit_title));
        if (this.accountMode == AccountMode.GUEST && StoriesExperimentUtil.shouldMoveItineraryToProfile()) {
            this.tripsModel.show();
            this.tripsModel.clickListener(AccountPageAdapter$$Lambda$17.lambdaFactory$(this));
            this.tripsModel.showRowBadge(shouldShowBadgeOnTripsModel());
        }
        updateProfileCompletionModel();
        createInProgressListing(this.controller.getListing());
    }

    static /* synthetic */ void lambda$initModels$13(AccountPageAdapter accountPageAdapter, View v) {
        accountPageAdapter.listener.itemSelected(accountPageAdapter.accountMode == AccountMode.GUEST ? AccountPageItem.FeedbackGuest : AccountPageItem.FeedbackHost);
    }

    static /* synthetic */ void lambda$initModels$15(AccountPageAdapter accountPageAdapter, View v) {
        accountPageAdapter.listener.itemSelected(AccountPageItem.TravelForWork);
        accountPageAdapter.travelForWorkModel.hide();
        accountPageAdapter.notifyModelChanged(accountPageAdapter.travelForWorkModel);
    }

    private void updateProfileCompletionModel() {
        if (!Trebuchet.launch(TrebuchetKeys.PROFILE_COMPLETION) || this.accountMode != AccountMode.GUEST) {
            this.profileCompletionModel.hide();
            return;
        }
        if (!this.profileCompletionModel.isShown() && ProfileCompletionHelper.shouldShowProfileCompletionBar(this.profileCompletionManager, this.sharedPrefsHelper)) {
            this.profileCompletionModel.show();
            ((ProfileCompletionGraph) AirbnbApplication.instance(this.context).component()).profileCompletionJitneyLogger().logProfileCompletionBarImpression(this.profileCompletionManager);
        }
        if (this.profileCompletionModel.isShown()) {
            this.profileCompletionModel.clickListener(AccountPageAdapter$$Lambda$18.lambdaFactory$(this));
            int stepsLeft = this.profileCompletionManager.getIncompleteSteps().size();
            if (stepsLeft > 0) {
                this.profileCompletionModel.title((CharSequence) this.context.getResources().getString(C0880R.string.profile_completion_complete_your_profile));
                SpannableStringBuilder subtitleBuilder = new SpannableStringBuilder();
                subtitleBuilder.append(TextUtil.makeColored(this.context, C0880R.color.n2_text_color_actionable, this.context.getResources().getQuantityString(C0880R.plurals.profile_completion_x_steps_left, stepsLeft, new Object[]{Integer.valueOf(stepsLeft)}) + "."));
                subtitleBuilder.append(" ");
                subtitleBuilder.append(this.context.getResources().getString(((CompletionStep) this.profileCompletionManager.getIncompleteSteps().get(0)).getDescription()));
                this.profileCompletionModel.subtitle((CharSequence) subtitleBuilder);
            } else {
                this.profileCompletionModel.title((CharSequence) this.context.getResources().getString(C0880R.string.profile_completion_steps_youre_all_set));
                this.profileCompletionModel.subtitle((CharSequence) "");
            }
            float percentageCompleted = ((float) this.profileCompletionManager.getCompletedSteps().size()) / ((float) this.profileCompletionManager.getAllSteps().size());
            this.profileCompletionModel.filledSectionColor(C0880R.color.n2_jellyfish_babu_bg).value(percentageCompleted).progressLabel((CharSequence) ((int) (100.0f * percentageCompleted)) + "%").progressLabelVisible(true);
            if (stepsLeft <= 0) {
                new Handler().postDelayed(AccountPageAdapter$$Lambda$19.lambdaFactory$(this), 2000);
            }
        }
    }

    static /* synthetic */ void lambda$updateProfileCompletionModel$18(AccountPageAdapter accountPageAdapter) {
        accountPageAdapter.profileCompletionModel.hide();
        accountPageAdapter.notifyModelChanged(accountPageAdapter.profileCompletionModel);
    }

    public void notifyProfileCompletionChanged() {
        updateProfileCompletionModel();
        notifyModelChanged(this.profileCompletionModel);
    }

    public void hideBadgeOnTrips() {
        if (this.tripsModel != null) {
            this.tripsModel.showRowBadge(false);
            notifyModelChanged(this.tripsModel);
        }
    }

    private boolean shouldShowBadgeOnTripsModel() {
        if ((this.airbnbApi.hasActiveTrip() || this.airbnbApi.hasUpcomingTrips()) && !this.sharedPrefsHelper.isBadgeSeenAndClearedForTripsTabMove()) {
            return true;
        }
        return false;
    }

    private boolean shouldShowCommunityCenter() {
        return Trebuchet.launch("show_community_center_link", Trebuchet.KEY_ANDROID_ENABLED, false);
    }

    private int getListYourSpaceWording() {
        if (!(Experiments.showNewListingAllTheTime() || Experiments.showNewListingBecomeHost())) {
            return C0880R.string.account_page_list_your_space;
        }
        if (this.accountManager.userHasActiveListings()) {
            return C0880R.string.account_page_list_your_space_another;
        }
        if (this.accountManager.userHasListings()) {
            return C0880R.string.account_page_list_your_space_become_a_host_again;
        }
        return C0880R.string.account_page_list_your_space_become_a_host;
    }

    private void createInProgressListing(ListingPickerInfo listing) {
        if (listing == null || !FeatureToggles.shouldShowUnfinishedListingOnProfile()) {
            this.unfinishedListingModel.hide();
            return;
        }
        this.unfinishedListingModel.show();
        this.unfinishedListingModel.title(this.context.getString(C0880R.string.account_page_finish_your_listing)).subtitleText(listing.getNameOrPlaceholder(this.context)).progressBarVisible(true).progressBarPercentage(ListingProgressUtils.getListingPercentageComplete(listing)).onClickListener(AccountPageAdapter$$Lambda$20.lambdaFactory$(this, listing));
        if (TextUtils.isEmpty(listing.getThumbnailUrl())) {
            this.unfinishedListingModel.image(C0880R.C0881drawable.camera_icon_bg);
        } else {
            this.unfinishedListingModel.image(listing.getThumbnailUrl());
        }
    }

    public void refreshUnfinishedListing() {
        createInProgressListing(this.controller.getListing());
        notifyModelChanged(this.unfinishedListingModel);
    }

    public void destroy() {
        this.controller.removeListener(this);
    }
}
