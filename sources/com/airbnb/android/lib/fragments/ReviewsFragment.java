package com.airbnb.android.lib.fragments;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.app.ActionBar;
import android.support.p002v7.app.ActionBar.OnNavigationListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.enums.ReviewsMode;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.interfaces.TranslateInterface;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.AirRequestFactory;
import com.airbnb.android.core.requests.ReviewsRequest;
import com.airbnb.android.core.requests.ReviewsRequest.ReviewsFrom;
import com.airbnb.android.core.responses.ReviewsResponse;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.ReviewsActivity;
import com.airbnb.android.lib.adapters.AirSpinnerAdapter;
import com.airbnb.android.lib.adapters.ReviewsAdapter;
import com.airbnb.android.lib.utils.InfiniteAdapter;
import com.airbnb.android.lib.utils.InfiniteAdapter.AdapterRequestListener;
import com.airbnb.android.lib.views.DetailedReviewsView;
import com.airbnb.android.lib.views.GroupedCheck;
import com.airbnb.android.lib.views.LoaderListView;
import p032rx.Observer;

public class ReviewsFragment extends AirFragment implements OnNavigationListener, TranslateInterface {
    private static final String ARG_LISTING = "listing";
    private static final String ARG_REVIEW_MODE = "reviewMode";
    private static final String ARG_USER = "user";
    private static final int NOT_YET_SET = -1;
    private static final String SAVED_SHOW_TRANSLATED = "show_translated";
    private static final String SAVED_SPINNER_POSITION = "spinner_position";
    private InfiniteAdapter<ReviewsRequest, Review, ReviewsResponse> infiniteAdapter;
    /* access modifiers changed from: private */
    public Listing mListing;
    @BindView
    LoaderListView mLoaderListView;
    private int mOppositeReviewsCount = -1;
    private ReviewsMode mReviewMode;
    /* access modifiers changed from: private */
    public int mReviewsCount = -1;
    private boolean mShowTranslated;
    private int mSpinnerPosition;
    @BindView
    GroupedCheck mTranslateCheckbox;
    /* access modifiers changed from: private */
    public User mUser;
    private ReviewsAdapter reviewListAdapter;

    public static Fragment newInstanceForUser(User user, ReviewsMode reviewMode) {
        Fragment f = new ReviewsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_REVIEW_MODE, reviewMode.ordinal());
        args.putParcelable("user", user);
        f.setArguments(args);
        return f;
    }

    public static Fragment newInstanceForListing(Listing listing, ReviewsMode reviewMode) {
        Fragment f = new ReviewsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_REVIEW_MODE, reviewMode.ordinal());
        args.putParcelable("listing", listing);
        f.setArguments(args);
        return f;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        this.mListing = (Listing) args.getParcelable("listing");
        this.mUser = this.mListing == null ? (User) args.getParcelable("user") : this.mListing.getHost();
        this.mReviewMode = ReviewsMode.values()[args.getInt(ARG_REVIEW_MODE)];
        if (savedInstanceState != null) {
            this.mSpinnerPosition = savedInstanceState.getInt(SAVED_SPINNER_POSITION, this.mSpinnerPosition);
            return;
        }
        switch (this.mReviewMode) {
            case MODE_ALL:
            case MODE_LISTING:
                this.mSpinnerPosition = 0;
                return;
            case MODE_HOST:
                this.mSpinnerPosition = 1;
                return;
            case MODE_GUEST:
                this.mSpinnerPosition = 2;
                return;
            default:
                throw new IllegalStateException("unexpected position");
        }
    }

    @SuppressLint({"ResourceAsColor"})
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int reviewCount;
        View v = inflater.inflate(C0880R.layout.fragment_reviews, container, false);
        bindViews(v);
        if (this.mReviewMode == ReviewsMode.MODE_LISTING) {
            reviewCount = this.mListing.getReviewsCount();
        } else {
            reviewCount = this.mUser.getRevieweeCount();
        }
        setReviewsActionBarTitle(this.mReviewMode, reviewCount);
        addActionBarSpinnerIfNeeded();
        if (savedInstanceState != null) {
            this.mShowTranslated = savedInstanceState.getBoolean(SAVED_SHOW_TRANSLATED, false);
        }
        this.mTranslateCheckbox.setTitleColor(C0880R.color.c_rausch);
        ListView listView = this.mLoaderListView.getListView();
        listView.setSelector(17170445);
        listView.setDivider(new ColorDrawable(getResources().getColor(C0880R.color.c_gray_4)));
        listView.setDividerHeight((int) getResources().getDimension(C0880R.dimen.line_thickness));
        this.mLoaderListView.finishLoaderImmediate();
        assignSubreviewsExperiment(listView);
        if (this.mReviewMode == ReviewsMode.MODE_LISTING) {
            setListingReviewAdapter();
        } else {
            setUserReviewAdapter(this.mReviewMode);
        }
        listView.setOnItemLongClickListener(ReviewsFragment$$Lambda$1.lambdaFactory$(this));
        return v;
    }

    static /* synthetic */ boolean lambda$onCreateView$0(ReviewsFragment reviewsFragment, AdapterView parent, View view, int position, long id) {
        Review review = (Review) reviewsFragment.reviewListAdapter.getItem(position);
        if (review != null) {
            MiscUtils.copyToClipboard(view.getContext(), review.getPublicFeedback());
        }
        return false;
    }

    private void assignSubreviewsExperiment(ListView listView) {
        if (this.mReviewMode == ReviewsMode.MODE_LISTING) {
            this.mErf.buildExperiment("mobile_p3_subreviews").treatment("subreviews_on_reviews_details_page", ReviewsFragment$$Lambda$2.lambdaFactory$(this, listView)).treatment("control", ReviewsFragment$$Lambda$3.lambdaFactory$()).notInExperimentOrUnknownTreatment("control").deliver();
        }
    }

    static /* synthetic */ void lambda$assignSubreviewsExperiment$2() {
    }

    /* access modifiers changed from: private */
    public void showSubreviewStarsAsHeader(ListView listView) {
        DetailedReviewsView detailedReviewsView = new DetailedReviewsView(getActivity());
        detailedReviewsView.setHorizontalPaddingOnCells(C0880R.dimen.gutter_padding);
        listView.addHeaderView(detailedReviewsView);
        detailedReviewsView.bindViewAndSetupDividers(this.mListing);
    }

    private void setUserReviewAdapter(final ReviewsMode mode) {
        this.reviewListAdapter = new ReviewsAdapter(getActivity(), this, getFragmentManager(), this.requestManager);
        this.infiniteAdapter = new InfiniteAdapter<>(this.reviewListAdapter, C0880R.layout.n2_loading_row_frame, new AirRequestFactory<ReviewsRequest, ReviewsResponse>() {
            public ReviewsRequest getNextOffset(int offset, BaseRequestListener<ReviewsResponse> callback) {
                return (ReviewsRequest) ReviewsRequest.forUser(ReviewsFragment.this.mAccountManager, ReviewsFragment.this.mUser.getId(), ReviewsFrom.transformFrom(mode), offset, 20).withListener((Observer) callback);
            }
        }, this.requestManager);
        this.infiniteAdapter.setRequestListener(new AdapterRequestListener<ReviewsResponse>() {
            public void onResponse(ReviewsResponse response) {
                if (ReviewsFragment.this.isResumed() && ReviewsFragment.this.mReviewsCount == -1) {
                    ReviewsFragment.this.inputReviewCount(Integer.valueOf(response.getReviewsCount()), null);
                }
            }

            public void onErrorResponse(NetworkException error) {
                if (ReviewsFragment.this.isResumed()) {
                    NetworkUtil.toastGenericNetworkError(ReviewsFragment.this.getActivity());
                }
            }
        });
        this.mLoaderListView.getListView().setAdapter(this.infiniteAdapter);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SHOW_TRANSLATED, this.mShowTranslated);
        outState.putInt(SAVED_SPINNER_POSITION, this.mSpinnerPosition);
    }

    private void setListingReviewAdapter() {
        this.reviewListAdapter = new ReviewsAdapter(getActivity(), this, getFragmentManager(), this.requestManager);
        this.infiniteAdapter = new InfiniteAdapter<>(this.reviewListAdapter, C0880R.layout.n2_loading_row_frame, new AirRequestFactory<ReviewsRequest, ReviewsResponse>() {
            public ReviewsRequest getNextOffset(int offset, BaseRequestListener<ReviewsResponse> listener) {
                return (ReviewsRequest) ReviewsRequest.forListing(ReviewsFragment.this.mListing.getId(), offset).withListener((Observer) listener);
            }
        }, this.requestManager);
        this.infiniteAdapter.setRequestListener(new AdapterRequestListener<ReviewsResponse>() {
            public void onResponse(ReviewsResponse response) {
                if (ReviewsFragment.this.mReviewsCount == -1) {
                    ReviewsFragment.this.inputReviewCount(Integer.valueOf(response.getReviewsCount()), null);
                }
            }

            public void onErrorResponse(NetworkException error) {
                NetworkUtil.toastGenericNetworkError(ReviewsFragment.this.getActivity());
            }
        });
        this.mLoaderListView.getListView().setAdapter(this.infiniteAdapter);
    }

    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        ReviewsMode mode;
        this.mSpinnerPosition = itemPosition;
        switch (itemPosition) {
            case 0:
                if (this.mReviewMode != ReviewsMode.MODE_LISTING) {
                    mode = ReviewsMode.MODE_ALL;
                    break;
                } else {
                    mode = ReviewsMode.MODE_LISTING;
                    break;
                }
            case 1:
                mode = ReviewsMode.MODE_HOST;
                break;
            case 2:
                mode = ReviewsMode.MODE_GUEST;
                break;
            default:
                throw new IllegalStateException("unexpected position");
        }
        if (mode == ReviewsMode.MODE_LISTING) {
            setListingReviewAdapter();
        } else {
            setUserReviewAdapter(mode);
        }
        return true;
    }

    private void addActionBarSpinnerIfNeeded() {
        ReviewsRequest.forReviewCount(this.mAccountManager, this.mUser.getId(), ReviewsFrom.transformFrom(getOppositeMode())).withListener((Observer) new NonResubscribableRequestListener<ReviewsResponse>() {
            public void onResponse(ReviewsResponse response) {
                ReviewsFragment.this.inputReviewCount(null, Integer.valueOf(response.getReviewsCount()));
            }

            public void onErrorResponse(AirRequestNetworkException error) {
            }
        }).execute(this.requestManager);
    }

    private void setReviewsActionBarTitle(ReviewsMode reviewsMode, int reviewCount) {
        Resources resources = getResources();
        int i = C0880R.plurals.reviews_for;
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(reviewCount);
        objArr[1] = reviewsMode == ReviewsMode.MODE_LISTING ? this.mListing.getName() : this.mUser.getName();
        setActionBarTitle((CharSequence) resources.getQuantityString(i, reviewCount, objArr));
    }

    private ReviewsMode getOppositeMode() {
        switch (this.mReviewMode) {
            case MODE_ALL:
            case MODE_LISTING:
            case MODE_GUEST:
                return ReviewsMode.MODE_HOST;
            case MODE_HOST:
                return ReviewsMode.MODE_GUEST;
            default:
                throw new IllegalArgumentException("is there an unsupported ReviewsMode? ");
        }
    }

    /* access modifiers changed from: private */
    public void inputReviewCount(Integer defaultModeCount, Integer oppositeModeCount) {
        boolean z;
        boolean z2 = true;
        if (defaultModeCount == null) {
            z = true;
        } else {
            z = false;
        }
        if (oppositeModeCount != null) {
            z2 = false;
        }
        if (z == z2) {
            throw new IllegalArgumentException("you are supposed to pass in one count type per response!");
        }
        this.mReviewsCount = defaultModeCount != null ? defaultModeCount.intValue() : this.mReviewsCount;
        this.mOppositeReviewsCount = oppositeModeCount != null ? oppositeModeCount.intValue() : this.mOppositeReviewsCount;
        if (this.mReviewsCount > 0 && this.mOppositeReviewsCount > 0) {
            showReviewTypeSpinnerInActionBar();
        } else if (this.mReviewsCount != 0 || this.mOppositeReviewsCount <= 0) {
            setReviewsActionBarTitle(this.mReviewMode, this.mReviewsCount);
        } else if (this.mReviewMode != ReviewsMode.MODE_LISTING) {
            setReviewsActionBarTitle(getOppositeMode(), this.mOppositeReviewsCount);
            setUserReviewAdapter(getOppositeMode());
        }
    }

    private void showReviewTypeSpinnerInActionBar() {
        String[] spinnerTitles;
        String[] dropdownTitles;
        ActionBar actionBar = ((ReviewsActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(1);
        String[] spinnerSubtitles = null;
        if (this.mReviewMode == ReviewsMode.MODE_LISTING) {
            spinnerTitles = new String[]{getString(C0880R.string.reviews_for_who, this.mListing.getName()), getString(C0880R.string.reviews_for_who, this.mUser.getFirstName())};
            dropdownTitles = new String[]{getString(C0880R.string.reviews_for_listing), getString(C0880R.string.reviews_for_host_all, this.mUser.getFirstName())};
        } else {
            String reviewsForUser = getString(C0880R.string.reviews_for_who, this.mUser.getFirstName());
            spinnerTitles = new String[]{reviewsForUser, reviewsForUser, reviewsForUser};
            spinnerSubtitles = new String[]{getString(C0880R.string.reviews_all_reviews), getString(C0880R.string.reviews_from_guests_short), getString(C0880R.string.reviews_from_hosts_short)};
            dropdownTitles = new String[]{getString(C0880R.string.reviews_all_reviews), getString(C0880R.string.reviews_from_guests), getString(C0880R.string.reviews_from_hosts)};
        }
        actionBar.setListNavigationCallbacks(new AirSpinnerAdapter(spinnerTitles, spinnerSubtitles, dropdownTitles), this);
        actionBar.setSelectedNavigationItem(this.mSpinnerPosition);
    }

    public void translationsAreAvailable() {
        if (!MiscUtils.shouldDisableTranslations() && this.mTranslateCheckbox.getVisibility() != 0) {
            this.mTranslateCheckbox.setVisibility(0);
            this.mTranslateCheckbox.setChecked(this.mShowTranslated);
            this.mTranslateCheckbox.setOnCheckedChangeListener(ReviewsFragment$$Lambda$4.lambdaFactory$(this));
        }
    }

    static /* synthetic */ void lambda$translationsAreAvailable$3(ReviewsFragment reviewsFragment, CompoundButton compoundButton, boolean checked) {
        reviewsFragment.mShowTranslated = checked;
        reviewsFragment.infiniteAdapter.notifyDataSetChanged();
    }

    public boolean shouldShowTranslations() {
        if (MiscUtils.shouldDisableTranslations()) {
            return false;
        }
        return this.mShowTranslated;
    }
}
