package com.airbnb.android.p011p3;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.ReviewSearchJitneyLogger;
import com.airbnb.android.core.models.ReviewKeyword;
import com.airbnb.android.core.models.ReviewSearchResult;
import com.airbnb.android.core.requests.ReviewKeywordsRequest;
import com.airbnb.android.core.requests.ReviewSearchRequest;
import com.airbnb.android.core.responses.ReviewKeywordsResponse;
import com.airbnb.android.core.responses.ReviewSearchResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.p011p3.P3Component.Builder;
import com.airbnb.android.p011p3.P3ReviewSearchController.ReviewSearchListener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.InputMarquee;
import icepick.State;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.airbnb.android.p3.P3ReviewSearchFragment */
public class P3ReviewSearchFragment extends P3BaseFragment {
    private static final String ARG_LISTING_ID = "listing_id";
    private static final String ARG_QUERY = "query";
    private P3ReviewSearchController controller;
    @State
    String currentQuery;
    ReviewSearchJitneyLogger jitneyLogger;
    /* access modifiers changed from: 0000 */
    @State
    public ArrayList<ReviewKeyword> keywords;
    final RequestListener<ReviewKeywordsResponse> keywordsRequestListener = new C0699RL().onResponse(P3ReviewSearchFragment$$Lambda$1.lambdaFactory$(this)).onError(P3ReviewSearchFragment$$Lambda$2.lambdaFactory$(this)).onComplete(P3ReviewSearchFragment$$Lambda$3.lambdaFactory$(this)).build();
    @State
    long listingId;
    @BindView
    InputMarquee marquee;
    @State
    ArrayList<ReviewSearchResult> reviewResults;
    private final ReviewSearchListener reviewSearchListener = new ReviewSearchListener() {
        public boolean isLoading() {
            return P3ReviewSearchFragment.this.requestManager.hasRequests(P3ReviewSearchFragment.this.reviewSearchResultsRequestListener) || P3ReviewSearchFragment.this.requestManager.hasRequests(P3ReviewSearchFragment.this.keywordsRequestListener);
        }

        public void onClickKeyword(ReviewKeyword keyword) {
            String searchTerm = keyword.getTerm();
            P3ReviewSearchFragment.this.jitneyLogger.logClickReviewSearchKeyword(P3ReviewSearchFragment.this.listingId, searchTerm);
            P3ReviewSearchFragment.this.marquee.setText((CharSequence) searchTerm);
            P3ReviewSearchFragment.this.beginSearch(searchTerm);
        }

        public List<ReviewKeyword> getKeywords() {
            return P3ReviewSearchFragment.this.keywords;
        }

        public List<ReviewSearchResult> getResults() {
            return P3ReviewSearchFragment.this.reviewResults;
        }
    };
    final RequestListener<ReviewSearchResponse> reviewSearchResultsRequestListener = new C0699RL().onResponse(P3ReviewSearchFragment$$Lambda$4.lambdaFactory$(this)).onError(P3ReviewSearchFragment$$Lambda$5.lambdaFactory$(this)).onComplete(P3ReviewSearchFragment$$Lambda$6.lambdaFactory$(this)).build();
    @BindView
    RecyclerView searchRecyclerView;
    @BindView
    AirToolbar toolbar;

    static /* synthetic */ void lambda$new$3(P3ReviewSearchFragment p3ReviewSearchFragment, ReviewSearchResponse response) {
        if (response.results.isEmpty()) {
            p3ReviewSearchFragment.marquee.requestFocus();
        }
        p3ReviewSearchFragment.reviewResults = new ArrayList<>(response.results);
    }

    static /* synthetic */ void lambda$new$4(P3ReviewSearchFragment p3ReviewSearchFragment, AirRequestNetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(p3ReviewSearchFragment.getView(), e);
        p3ReviewSearchFragment.reviewResults = null;
    }

    public static P3ReviewSearchFragment forListing(long listingId2) {
        return forListingWithQuery(listingId2, null);
    }

    public static P3ReviewSearchFragment forListingWithQuery(long listingId2, String query) {
        return (P3ReviewSearchFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new P3ReviewSearchFragment()).putLong("listing_id", listingId2)).putString(ARG_QUERY, query)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7532R.layout.fragment_p3_review_search, container, false);
        bindViews(view);
        ((Builder) ((P3Bindings) CoreApplication.instance(getActivity()).componentProvider()).p3ComponentProvider().get()).build().inject(this);
        this.toolbar.setTheme(3);
        setToolbar(this.toolbar);
        this.controller = new P3ReviewSearchController(getContext(), this.reviewSearchListener);
        if (savedInstanceState == null) {
            this.currentQuery = getArguments().getString(ARG_QUERY);
            this.listingId = getArguments().getLong("listing_id");
        }
        setupInputMarquee();
        if (this.currentQuery != null) {
            this.marquee.setText((CharSequence) this.currentQuery);
            beginSearch(this.currentQuery);
        } else {
            beginFetchKeywords();
        }
        this.searchRecyclerView.setHasFixedSize(true);
        this.searchRecyclerView.setAdapter(this.controller.getAdapter());
        return view;
    }

    private void beginFetchKeywords() {
        new ReviewKeywordsRequest(this.listingId).doubleResponse().withListener(this.keywordsRequestListener).execute(this.requestManager);
        this.controller.requestModelBuild();
    }

    /* access modifiers changed from: private */
    public void beginSearch(String currentQuery2) {
        this.currentQuery = currentQuery2;
        new ReviewSearchRequest(currentQuery2, this.listingId).doubleResponse().withListener(this.reviewSearchResultsRequestListener).execute(this.requestManager);
        this.controller.requestModelBuild();
    }

    private void setupInputMarquee() {
        this.marquee.setHint(C7532R.string.review_search_query_hint);
        this.marquee.requestFocus();
        this.marquee.setShowKeyboardOnFocus(true);
        this.marquee.post(P3ReviewSearchFragment$$Lambda$7.lambdaFactory$(this));
        this.marquee.setForSearch(true);
        this.marquee.setOnEditorActionListener(P3ReviewSearchFragment$$Lambda$8.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setupInputMarquee$6(P3ReviewSearchFragment p3ReviewSearchFragment) {
        if (p3ReviewSearchFragment.marquee != null) {
            p3ReviewSearchFragment.marquee.requestFocus();
        }
    }

    static /* synthetic */ boolean lambda$setupInputMarquee$7(P3ReviewSearchFragment p3ReviewSearchFragment, TextView v, int actionId, KeyEvent event) {
        String searchQuery = v.getText().toString().trim();
        if (!KeyboardUtils.isEnterOrSearch(actionId, event) || TextUtils.isEmpty(searchQuery)) {
            return false;
        }
        KeyboardUtils.dismissSoftKeyboard((View) v);
        p3ReviewSearchFragment.beginSearch(searchQuery);
        return true;
    }
}
