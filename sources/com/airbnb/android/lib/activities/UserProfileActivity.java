package com.airbnb.android.lib.activities;

import android.os.Bundle;
import com.airbnb.android.contentframework.fragments.StoryDetailViewFragment;
import com.airbnb.android.contentframework.fragments.StoryFeedFragment;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.businesstravel.BusinessTravelJitneyLogger;
import com.airbnb.android.core.businesstravel.WorkEmailLaunchSource;
import com.airbnb.android.core.enums.FlagContent;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.enums.ReviewsMode;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.enums.WorkEmailStatus;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.identity.ReplaceVerifiedIdWithIdentityActivity;
import com.airbnb.android.core.intents.BusinessTravelIntents;
import com.airbnb.android.core.intents.HelpCenterIntents;
import com.airbnb.android.core.intents.P3ActivityIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.models.Article;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.UserFlag;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.UserProfileAdapter.ClickListener;
import com.airbnb.android.lib.fragments.UserProfileFragment;
import com.airbnb.android.lib.fragments.UserProfileVerificationsFragment;

public class UserProfileActivity extends AirActivity implements ClickListener {
    private static final String PROFILE_FRAGMENT_TAG = "profile_fragment";
    public static final int REQUEST_CODE_FLAG_CONTENT = 42;
    BusinessTravelJitneyLogger businessTravelJitneyLogger;
    private UserProfileFragment profileFragment;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(this).component()).inject(this);
        setContentView(C0880R.layout.activity_user_profile);
        if (savedInstanceState == null) {
            this.profileFragment = UserProfileFragment.newInstance(getIntent().getExtras());
            showFragment(this.profileFragment, C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true, PROFILE_FRAGMENT_TAG);
            return;
        }
        this.profileFragment = (UserProfileFragment) getSupportFragmentManager().findFragmentByTag(PROFILE_FRAGMENT_TAG);
    }

    public void onSeeAllReviewsClicked() {
        startActivity(ReviewsActivity.intentForUser(this, this.profileFragment.getUser(), ReviewsMode.MODE_ALL));
    }

    public void onListingClicked(Listing listing) {
        startActivity(P3ActivityIntents.withListing(this, listing));
    }

    public void onViewVerificationsClicked(User user) {
        showModal(UserProfileVerificationsFragment.newInstance(user), C0880R.C0882id.content_container, C0880R.C0882id.modal_container, true);
    }

    public void onIdentityLearnMore() {
        startActivity(HelpCenterIntents.intentForHelpCenterArticle(this, HelpCenterArticle.VERIFIED_ID_LEARN_MORE).toIntent());
    }

    public void onVerifyMyIdentity() {
        startActivity(ReplaceVerifiedIdWithIdentityActivity.intent(this, this.profileFragment.getUser().getId(), VerificationFlow.UserProfile));
    }

    public void onReportUser(UserFlag flag) {
        startActivityForResult(ReactNativeIntents.intentForFlagContent(getBaseContext(), this.profileFragment.getUser().getId(), flag == null ? null : Long.valueOf(flag.getId()), FlagContent.User), 42);
    }

    public void onAddOrVerifyWorkEmail(WorkEmailStatus workEmailStatus) {
        WorkEmailLaunchSource launchSource = WorkEmailLaunchSource.ViewProfile;
        if (workEmailStatus.equals(WorkEmailStatus.None)) {
            this.businessTravelJitneyLogger.logViewProfileAddWorkEmailClickAdd(launchSource);
        } else if (workEmailStatus.equals(WorkEmailStatus.Pending)) {
            this.businessTravelJitneyLogger.logViewProfileVerifyWorkEmailClickVerify(launchSource);
        }
        startActivityForResult(BusinessTravelIntents.intentForWorkEmail(this, launchSource), BusinessTravelIntents.REQUEST_CODE_ADD_EMAIL);
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    public void onStoryClicked(Article article) {
        startActivity(StoryDetailViewFragment.forPartialArticle(this, article, NavigationTag.UserProfile.trackingName));
    }

    public void onSeeAllStoriesClicked() {
        if (this.profileFragment.getUser() != null) {
            startActivity(StoryFeedFragment.intentForUserStories(this, this.profileFragment.getUser().getId(), this.profileFragment.getUser().getFirstName()));
        }
    }
}
