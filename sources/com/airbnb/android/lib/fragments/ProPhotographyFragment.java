package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.DialogFragment;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.fragments.AirDialogFragment;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.ProgressDialogFragment;
import com.airbnb.android.core.fragments.ProgressDialogFragment.ProgressDialogListener;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.ListingRequest;
import com.airbnb.android.core.requests.ProPhotoRequest;
import com.airbnb.android.core.requests.ProPhotoRequest.Type;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.responses.ProPhotoResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.ProPhotographyActivity;
import com.airbnb.android.lib.activities.ProPhotographyActivity.ProPhotoStatus;
import com.airbnb.android.lib.analytics.ProPhotoAnalytics;
import com.airbnb.android.lib.analytics.ProPhotoAnalytics.Origin;
import com.airbnb.android.lib.views.CircleBadgeView;
import com.airbnb.android.lib.views.LoaderListView;
import com.airbnb.android.lib.views.StickyButton;
import com.airbnb.rxgroups.RequestSubscription;
import icepick.State;
import java.util.ArrayList;
import java.util.List;

public class ProPhotographyFragment extends AirFragment {
    private static final int DIALOG_REQ_SUBMIT_PHOTO_REQUEST = 1555;
    private static final long INVALID_ID = -1;
    private static final String LISTING_ID = "listing_id";
    private static final String LISTING_NAME = "listing_name";
    private static final int PHOTO_LIMIT = 4;
    private DialogFragment mConfirmRequestDialog;
    private ZenDialog mLimitReachedDialog;
    /* access modifiers changed from: private */
    public ArrayList<Listing> mListings;
    /* access modifiers changed from: private */
    public ProgressDialogFragment mLoadingProgressDialog;
    private ProPhotoSelectListingDialogFragment mSelectListingDialog;
    @State
    long mSelectedListing = -1;
    /* access modifiers changed from: private */
    public Origin originForAnalytics;
    /* access modifiers changed from: private */
    public RequestSubscription proPhotoCall;

    public static class ProPhotoSelectListingAdapter extends ArrayAdapter<Listing> {
        final int mResource = C0880R.layout.list_item_pro_photo_listing;

        public ProPhotoSelectListingAdapter(Context context, List<Listing> listings) {
            super(context, C0880R.layout.list_item_pro_photo_listing, listings);
        }

        public View getView(int position, View v, ViewGroup parent) {
            if (v == null) {
                v = LayoutInflater.from(getContext()).inflate(this.mResource, parent, false);
            }
            Listing listing = (Listing) getItem(position);
            ((TextView) v.findViewById(C0880R.C0882id.listing_name)).setText(listing.getName());
            ((TextView) v.findViewById(C0880R.C0882id.listing_info)).setText(getContext().getString(C0880R.string.bullet_with_space_parameterized, new Object[]{listing.getPriceFormatted(), listing.getPropertyType()}));
            return v;
        }
    }

    public static class ProPhotoSelectListingDialogFragment extends AirDialogFragment {
        private static final String LISTINGS = "raw_listings";
        private LoaderListView mLoaderListView;
        private Origin originForAnalytics;
        private List<Listing> rawListings;

        public static ProPhotoSelectListingDialogFragment newInstance(ArrayList<Listing> listings, int proPhotoAnalyticsOriginOrdinal) {
            ProPhotoSelectListingDialogFragment f = new ProPhotoSelectListingDialogFragment();
            Bundle args = new Bundle();
            args.putInt(ProPhotographyActivity.ORIGIN_FOR_ANALYTICS, proPhotoAnalyticsOriginOrdinal);
            args.putParcelableArrayList(LISTINGS, listings);
            f.setArguments(args);
            return f;
        }

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.rawListings = getArguments().getParcelableArrayList(LISTINGS);
            setStyle(1, C0880R.C0885style.Theme_Airbnb_DialogNoTitle);
            this.originForAnalytics = getArguments().getInt(ProPhotographyActivity.ORIGIN_FOR_ANALYTICS) == Origin.EDIT_LISTING.ordinal() ? Origin.EDIT_LISTING : Origin.HOSPITALITY;
            ProPhotoAnalytics.trackImpression(this.originForAnalytics, "select_listing");
        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(C0880R.layout.dialog_pro_photo_select, container, false);
            ((TextView) v.findViewById(C0880R.C0882id.cancel)).setOnClickListener(C6858x1e1c3eb.lambdaFactory$(this));
            this.mLoaderListView = (LoaderListView) v.findViewById(C0880R.C0882id.loader_list_view);
            this.mLoaderListView.finishLoaderImmediate();
            setupListView(this.rawListings);
            return v;
        }

        private void setupListView(List<Listing> rawListings2) {
            View footer = LayoutInflater.from(getActivity()).inflate(C0880R.layout.list_item_pro_photo_info, null);
            ListView listView = this.mLoaderListView.getListView();
            listView.addFooterView(footer);
            setListViewData(rawListings2);
            listView.setOnItemClickListener(C6859x1e1c3ec.lambdaFactory$(this));
        }

        static /* synthetic */ void lambda$setupListView$1(ProPhotoSelectListingDialogFragment proPhotoSelectListingDialogFragment, AdapterView parent, View view, int position, long id) {
            ProPhotoAnalytics.trackClick(proPhotoSelectListingDialogFragment.originForAnalytics, "select_listing", ProPhotoAnalytics.SEL_LIST_CLICK);
            if (position != parent.getCount() - 1) {
                Listing listing = (Listing) parent.getItemAtPosition(position);
                ((ProPhotographyFragment) proPhotoSelectListingDialogFragment.getTargetFragment()).showConfirmDialogForListing(listing.getId(), listing.getName());
            }
        }

        /* access modifiers changed from: private */
        public void setListViewData(List<Listing> rawListings2) {
            List<Listing> eligibleListings = new ArrayList<>();
            for (Listing listing : rawListings2) {
                if (ProPhotoStatus.AVAILABLE.value.equals(listing.getProPhotoStatus())) {
                    eligibleListings.add(listing);
                }
            }
            this.mLoaderListView.getListView().setAdapter(new ProPhotoSelectListingAdapter(getActivity(), eligibleListings));
        }
    }

    public static ProPhotographyFragment newInstance(int proPhotoAnalyticsOriginOrdinal) {
        ProPhotographyFragment f = new ProPhotographyFragment();
        Bundle args = new Bundle();
        args.putInt(ProPhotographyActivity.ORIGIN_FOR_ANALYTICS, proPhotoAnalyticsOriginOrdinal);
        f.setArguments(args);
        return f;
    }

    public static ProPhotographyFragment newInstanceForListing(int proPhotoAnalyticsOriginOrdinal, long listingId, String listingName) {
        ProPhotographyFragment f = new ProPhotographyFragment();
        Bundle args = new Bundle();
        args.putInt(ProPhotographyActivity.ORIGIN_FOR_ANALYTICS, proPhotoAnalyticsOriginOrdinal);
        args.putLong("listing_id", listingId);
        args.putString("listing_name", listingName);
        f.setArguments(args);
        return f;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preLoadListings();
        this.originForAnalytics = getArguments().getInt(ProPhotographyActivity.ORIGIN_FOR_ANALYTICS) == Origin.EDIT_LISTING.ordinal() ? Origin.EDIT_LISTING : Origin.HOSPITALITY;
        ProPhotoAnalytics.trackImpression(this.originForAnalytics, "get_photography");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_pro_photo, container, false);
        ((CircleBadgeView) view.findViewById(C0880R.C0882id.circle_badge_view_photo)).initializeAsInactiveBadge();
        StickyButton applyButton = (StickyButton) view.findViewById(C0880R.C0882id.btn_apply_now);
        applyButton.setTitle(C0880R.string.pro_photo_check_availability);
        applyButton.setOnClickListener(ProPhotographyFragment$$Lambda$1.lambdaFactory$(this));
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(ProPhotographyFragment proPhotographyFragment, View v) {
        ProPhotoAnalytics.trackClick(proPhotographyFragment.originForAnalytics, "get_photography", ProPhotoAnalytics.REQUEST_CLICK);
        proPhotographyFragment.mLimitReachedDialog = null;
        long listingId = proPhotographyFragment.getArguments().getLong("listing_id", -1);
        String listingName = proPhotographyFragment.getArguments().getString("listing_name");
        if (listingId != -1) {
            proPhotographyFragment.showConfirmDialogForListing(listingId, listingName);
        } else {
            proPhotographyFragment.showSelectListingDialog();
        }
    }

    private void preLoadListings() {
        ListingRequest.forPhotographyStatus(this.mAccountManager.getCurrentUser().getId(), true, new NonResubscribableRequestListener<ListingResponse>() {
            public void onResponse(ListingResponse response) {
                ProPhotographyFragment.this.mListings = (ArrayList) response.getListings();
            }

            public void onErrorResponse(AirRequestNetworkException e) {
            }
        }).skipCache().execute(this.requestManager);
    }

    private void showSelectListingDialog() {
        if (this.mListings != null) {
            showSelectListingDialogWithData(this.mListings);
        } else {
            this.mLoadingProgressDialog = ProgressDialogFragment.newInstance(getContext(), C0880R.string.loading, 0);
            this.mLoadingProgressDialog.show(getFragmentManager(), (String) null);
        }
        ListingRequest.forPhotographyStatus(this.mAccountManager.getCurrentUser().getId(), true, new NonResubscribableRequestListener<ListingResponse>() {
            public void onResponse(ListingResponse response) {
                if (ProPhotographyFragment.this.mLoadingProgressDialog != null) {
                    ProPhotographyFragment.this.mLoadingProgressDialog.dismiss();
                }
                ProPhotographyFragment.this.showSelectListingDialogWithData((ArrayList) response.getListings());
            }

            public void onErrorResponse(AirRequestNetworkException e) {
                if (ProPhotographyFragment.this.mLoadingProgressDialog != null) {
                    ProPhotographyFragment.this.mLoadingProgressDialog.dismiss();
                }
                NetworkUtil.toastGenericNetworkError(ProPhotographyFragment.this.getActivity());
            }
        }).skipCache().execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void showSelectListingDialogWithData(ArrayList<Listing> rawListings) {
        if (checkForLimitReached(rawListings)) {
            return;
        }
        if (this.mSelectListingDialog == null || !this.mSelectListingDialog.isResumed()) {
            if (this.mSelectListingDialog != null) {
                this.mSelectListingDialog.dismiss();
            }
            if (this.mLimitReachedDialog != null) {
                this.mLimitReachedDialog.dismiss();
            }
            this.mSelectListingDialog = ProPhotoSelectListingDialogFragment.newInstance(rawListings, this.originForAnalytics.ordinal());
            this.mSelectListingDialog.show(getFragmentManager(), (String) null);
            this.mSelectListingDialog.setTargetFragment(this, 0);
            return;
        }
        this.mSelectListingDialog.setListViewData(rawListings);
    }

    private boolean checkForLimitReached(List<Listing> rawListings) {
        String requested = ProPhotoStatus.REQUESTED.value;
        String finished = ProPhotoStatus.FINISHED.value;
        String available = ProPhotoStatus.AVAILABLE.value;
        int countOfRequested = 0;
        for (Listing listing : rawListings) {
            String status = listing.getProPhotoStatus();
            if (requested.equals(status) || finished.equals(status)) {
                countOfRequested++;
            } else if (available.equals(status)) {
                return false;
            }
        }
        if (countOfRequested < 4) {
            return false;
        }
        if (this.mLimitReachedDialog == null) {
            if (this.mSelectListingDialog != null) {
                this.mSelectListingDialog.dismiss();
            }
            this.mLimitReachedDialog = ZenDialog.createSingleButtonDialog(C0880R.string.pro_photo_limit_reached, C0880R.string.pro_photo_limit_reached_info, C0880R.string.okay);
            this.mLimitReachedDialog.show(getFragmentManager(), (String) null);
            ProPhotoAnalytics.trackImpression(this.originForAnalytics, ProPhotoAnalytics.LIMIT_VIEW);
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void showConfirmDialogForListing(long listingId, String listingName) {
        this.mSelectedListing = listingId;
        this.mConfirmRequestDialog = ZenDialog.builder().withTitle(C0880R.string.pro_photo_confirm).withBodyText(C0880R.string.pro_photo_confirm_body).withSingleButton(C0880R.string.submit, (int) DIALOG_REQ_SUBMIT_PHOTO_REQUEST, (Fragment) this).create();
        if (this.mSelectListingDialog == null || !this.mSelectListingDialog.isResumed()) {
            this.mConfirmRequestDialog.show(getFragmentManager(), (String) null);
        } else {
            this.mConfirmRequestDialog.show(getFragmentManager().beginTransaction().remove(this.mSelectListingDialog).addToBackStack(null), (String) null);
        }
        ProPhotoAnalytics.trackImpression(this.originForAnalytics, "confirm_request");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DIALOG_REQ_SUBMIT_PHOTO_REQUEST) {
            ProPhotoAnalytics.trackClick(this.originForAnalytics, "confirm_request", ProPhotoAnalytics.REQUEST_CONFIRM);
            final ProgressDialogFragment progressDialog = ProgressDialogFragment.newInstance(getContext(), C0880R.string.pro_photo_requesting, 0);
            progressDialog.setProgressDialogListener(new ProgressDialogListener() {
                public void onProgressCompleted() {
                    if (ProPhotographyFragment.this.getActivity() != null) {
                        ProPhotographyFragment.this.getActivity().finish();
                    }
                }

                public void onProgressCancelled() {
                    ProPhotographyFragment.this.proPhotoCall.cancel();
                    progressDialog.dismiss();
                }
            });
            if (this.mConfirmRequestDialog == null || !this.mConfirmRequestDialog.isResumed()) {
                progressDialog.show(getFragmentManager(), (String) null);
            } else {
                progressDialog.show(getFragmentManager().beginTransaction().remove(this.mConfirmRequestDialog).addToBackStack(null), (String) null);
            }
            if (this.mSelectedListing == -1) {
                throw new IllegalStateException("a listing id should have been set before this was called");
            }
            this.proPhotoCall = new ProPhotoRequest(String.valueOf(this.mSelectedListing), Type.APPLY, new NonResubscribableRequestListener<ProPhotoResponse>() {
                public void onResponse(ProPhotoResponse response) {
                    ProPhotoAnalytics.trackImpression(ProPhotographyFragment.this.originForAnalytics, ProPhotoAnalytics.REQUEST_SUCCESS);
                    progressDialog.onProgressComplete(ProPhotographyFragment.this.getString(C0880R.string.pro_photo_app_submitted), "", C0880R.C0881drawable.icon_complete, 1000);
                    ((ProPhotographyActivity) ProPhotographyFragment.this.getActivity()).setPhotoRequestedForListing(true);
                }

                public void onErrorResponse(AirRequestNetworkException error) {
                    progressDialog.dismissAllowingStateLoss();
                    NetworkUtil.toastGenericNetworkError(ProPhotographyFragment.this.getActivity());
                }
            }).execute(this.requestManager);
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
