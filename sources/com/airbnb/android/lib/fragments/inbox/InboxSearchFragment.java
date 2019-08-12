package com.airbnb.android.lib.fragments.inbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.JitneyPublisher;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.ThreadFragmentIntents;
import com.airbnb.android.core.models.InboxSearchResult;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.requests.InboxSearchRequest;
import com.airbnb.android.core.responses.InboxSearchResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.inbox.InboxSearchResultsAdapter.InboxSearchListener;
import com.airbnb.android.lib.reviews.activities.WriteReviewActivity;
import com.airbnb.android.lib.utils.ThemeUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.Inbox.p017v1.InboxSearchQueryEvent;
import com.airbnb.jitney.event.logging.Inbox.p017v1.InboxSearchRecentSearchEvent;
import com.airbnb.jitney.event.logging.InboxSearch.p127v1.InboxSearchClickResultEvent.Builder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.utils.TextUtil;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import p032rx.Observer;

public class InboxSearchFragment extends AirFragment {
    private static final String ARG_INBOX_TYPE = "inbox_type";
    private static final String ARG_QUERY = "query";
    private static final int RESULT_CODE_WRITE_REVIEW = 10000;
    @State
    String currentQuery;
    @State
    boolean forPendingFilter = false;
    private final InboxSearchListener inboxSearchListener = new InboxSearchListener() {
        public void onThreadSelected(int position, InboxSearchResult selectedResult) {
            Thread thread = selectedResult.getThread();
            long postId = selectedResult.getMessage().getId();
            JitneyPublisher.publish(new Builder(InboxSearchFragment.this.loggingContextFactory.newInstance(), Long.valueOf(thread.getId()), Long.valueOf((long) position), InboxSearchFragment.this.inboxType.inboxRole, InboxSearchFragment.this.inboxType.inboxTypeLoggingString()));
            InboxSearchFragment.this.startActivity(ThreadFragmentIntents.newIntent(InboxSearchFragment.this.getActivity(), thread.getId(), InboxSearchFragment.this.inboxType, Long.valueOf(postId), null));
        }

        public void onSearch(String query) {
            InboxSearchFragment.this.beginSearch(query, false);
        }

        public void onClickRecentSearch(String s) {
            JitneyPublisher.publish(new InboxSearchRecentSearchEvent.Builder(InboxSearchFragment.this.loggingContextFactory.newInstance(), Long.valueOf((long) InboxSearchFragment.this.mPrefsHelper.getRecentInboxSearches().indexOf(s)), InboxSearchFragment.this.inboxType.inboxRole, InboxSearchFragment.this.inboxType.inboxTypeLoggingString()));
            InboxSearchFragment.this.beginSearch(s, false);
        }

        public void onClickPendingFilter() {
            InboxSearchFragment.this.beginSearch("", true);
        }

        public void onReviewButtonClick(InboxSearchResult selectedResult) {
            InboxSearchFragment.this.startActivityForResult(WriteReviewActivity.newIntent(InboxSearchFragment.this.getContext(), selectedResult.getThread().getReviewId()), 10000);
        }
    };
    @State
    InboxType inboxType;
    final RequestListener<InboxSearchResponse> requestListener = new RequestListener<InboxSearchResponse>() {
        public void onResponse(InboxSearchResponse data) {
            InboxSearchFragment.this.results.clear();
            Set<Long> threadIds = new HashSet<>();
            for (InboxSearchResult result : data.searchResults) {
                long threadId = result.getThread().getId();
                if (!threadIds.contains(Long.valueOf(threadId))) {
                    InboxSearchFragment.this.results.add(result);
                    threadIds.add(Long.valueOf(threadId));
                }
            }
            InboxSearchFragment.this.searchAdapter.setInboxSearchResults(InboxSearchFragment.this.results);
            InboxSearchFragment.this.navigationAnalytics.trackImpression(InboxSearchFragment.this);
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            NetworkUtil.tryShowErrorWithSnackbar(InboxSearchFragment.this.searchResultsRecyclerView, e);
        }
    };
    @State
    ArrayList<InboxSearchResult> results = new ArrayList<>();
    /* access modifiers changed from: private */
    public InboxSearchResultsAdapter searchAdapter;
    @BindView
    RecyclerView searchResultsRecyclerView;

    public static InboxSearchFragment newInstance(InboxType inboxType2, String query) {
        return (InboxSearchFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new InboxSearchFragment()).putString(ARG_QUERY, query)).putSerializable("inbox_type", inboxType2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = ThemeUtils.inflaterForPhonePadding(inflater).inflate(C0880R.layout.fragment_inbox_search, container, false);
        bindViews(view);
        this.inboxType = (InboxType) Check.notNull(getArguments().getSerializable("inbox_type"));
        this.searchAdapter = new InboxSearchResultsAdapter(getContext(), this.inboxType, this.mAccountManager, this.inboxSearchListener, this.mPrefsHelper.getRecentInboxSearches(), savedInstanceState);
        this.searchResultsRecyclerView.setHasFixedSize(true);
        this.searchResultsRecyclerView.setAdapter(this.searchAdapter);
        getActivity().getWindow().setSoftInputMode(32);
        if (getActivity() instanceof TransparentActionBarActivity) {
            AirToolbar toolbar = ((TransparentActionBarActivity) getActivity()).getAirToolbar();
            toolbar.scrollWith(this.searchResultsRecyclerView);
            toolbar.setTheme(3);
        }
        if (savedInstanceState == null) {
            String currentQuery2 = getArguments().getString(ARG_QUERY);
            if (currentQuery2 != null && !TextUtils.isEmpty(TextUtil.getFieldTextTrimmed(currentQuery2))) {
                this.searchAdapter.setSearchQuery(currentQuery2);
                beginSearch(currentQuery2, false);
            }
        }
        return view;
    }

    /* access modifiers changed from: private */
    public void beginSearch(String currentQuery2, boolean forPendingFilter2) {
        this.searchAdapter.setLoading();
        this.currentQuery = currentQuery2;
        this.forPendingFilter = forPendingFilter2;
        this.searchAdapter.setSearchQuery(currentQuery2);
        if (currentQuery2 != null && currentQuery2.trim().length() > 0) {
            this.mPrefsHelper.addRecentInboxSearch(currentQuery2);
        }
        JitneyPublisher.publish(new InboxSearchQueryEvent.Builder(this.loggingContextFactory.newInstance(), this.inboxType.inboxRole, this.inboxType.inboxTypeLoggingString()));
        new InboxSearchRequest(currentQuery2, this.inboxType, forPendingFilter2).withListener((Observer) this.requestListener).execute(this.requestManager);
    }

    public void onPause() {
        super.onPause();
        KeyboardUtils.hideSoftKeyboardForCurrentlyFocusedView(getContext());
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.searchAdapter.onSaveInstanceState(outState);
    }

    public NavigationTag getNavigationTrackingTag() {
        return ListUtils.isEmpty((Collection<?>) this.results) ? NavigationTag.InboxSearch : NavigationTag.InboxSearchResults;
    }

    public Strap getNavigationTrackingParams() {
        return this.inboxType.addLoggingParams(super.getNavigationTrackingParams());
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 10000:
                if (resultCode == -1) {
                    this.searchAdapter.clearAllExceptInput();
                    beginSearch(this.currentQuery, false);
                    return;
                }
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }
}
