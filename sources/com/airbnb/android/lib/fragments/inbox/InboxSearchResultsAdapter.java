package com.airbnb.android.lib.fragments.inbox;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.models.InboxSearchResult;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.InputMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.TextRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.viewcomponents.viewmodels.ThreadPreviewEpoxyModel;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;

class InboxSearchResultsAdapter extends AirEpoxyAdapter {
    private final AirbnbAccountManager accountManager;
    private final Context context;
    /* access modifiers changed from: private */
    public final InboxSearchListener inboxSearchListener;
    private final InboxType inboxType;
    private final InputMarqueeEpoxyModel_ inputMarqueeEpoxyModel = new InputMarqueeEpoxyModel_().hint(C0880R.string.inbox_search_query_hint).inputEditViewDrawable(C0880R.C0881drawable.gray_search_icon);
    private final LoadingRowEpoxyModel_ loadingEpoxyModel = new LoadingRowEpoxyModel_();
    private final TextRowEpoxyModel_ noResultsEpoxyModel = new TextRowEpoxyModel_().textRes(C0880R.string.inbox_search_no_results);
    private final LinkActionRowEpoxyModel_ pendingRowEpoxyModel = new LinkActionRowEpoxyModel_().textRes(C0880R.string.inbox_search_pending).showDivider(true);
    private final StandardRowEpoxyModel_ recentSearchesTitleModel = new StandardRowEpoxyModel_().title(C0880R.string.inbox_search_recent_searches).showDivider(false);

    public interface InboxSearchListener {
        void onClickPendingFilter();

        void onClickRecentSearch(String str);

        void onReviewButtonClick(InboxSearchResult inboxSearchResult);

        void onSearch(String str);

        void onThreadSelected(int i, InboxSearchResult inboxSearchResult);
    }

    InboxSearchResultsAdapter(Context context2, InboxType inboxType2, AirbnbAccountManager accountManager2, InboxSearchListener inboxSearchListener2, List<String> recentSearches, Bundle inState) {
        this.context = context2;
        this.inboxType = inboxType2;
        this.accountManager = accountManager2;
        this.inboxSearchListener = inboxSearchListener2;
        onRestoreInstanceState(inState);
        setupInputMarquee();
        if (inboxType2 == InboxType.Host) {
            setPendingFilterAndRecentSearches(recentSearches);
        } else {
            setRecentSearches(recentSearches);
        }
    }

    private void setupInputMarquee() {
        this.inputMarqueeEpoxyModel.showKeyboardOnFocus(true).requestFocus(true).forSearch(true).editorActionListener(InboxSearchResultsAdapter$$Lambda$1.lambdaFactory$(this));
        this.models.add(this.inputMarqueeEpoxyModel);
    }

    static /* synthetic */ boolean lambda$setupInputMarquee$0(InboxSearchResultsAdapter inboxSearchResultsAdapter, TextView v, int actionId, KeyEvent event) {
        String searchQuery = v.getText().toString().trim();
        if (!KeyboardUtils.isEnterOrSearch(actionId, event) || TextUtils.isEmpty(searchQuery)) {
            return false;
        }
        KeyboardUtils.dismissSoftKeyboard((View) v);
        inboxSearchResultsAdapter.inboxSearchListener.onSearch(searchQuery);
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void setSearchQuery(String query) {
        this.inputMarqueeEpoxyModel.text(query);
        notifyDataSetChanged();
    }

    /* access modifiers changed from: 0000 */
    public void clearAllExceptInput() {
        removeAllAfterModel(this.inputMarqueeEpoxyModel);
    }

    /* access modifiers changed from: 0000 */
    public void setInboxSearchResults(List<InboxSearchResult> results) {
        clearAllExceptInput();
        if (results.isEmpty()) {
            this.models.add(this.noResultsEpoxyModel);
        }
        addModels((Collection<? extends EpoxyModel<?>>) ListUtils.transformWithPosition(results, InboxSearchResultsAdapter$$Lambda$2.lambdaFactory$(this)));
        notifyDataSetChanged();
    }

    /* access modifiers changed from: 0000 */
    public void setPendingFilterAndRecentSearches(List<String> recentSearches) {
        clearAllExceptInput();
        this.pendingRowEpoxyModel.clickListener(InboxSearchResultsAdapter$$Lambda$3.lambdaFactory$(this));
        this.models.add(this.pendingRowEpoxyModel);
        addRecentSearchesIfNeeded(recentSearches);
    }

    /* access modifiers changed from: 0000 */
    public void setRecentSearches(List<String> recentSearches) {
        clearAllExceptInput();
        addRecentSearchesIfNeeded(recentSearches);
    }

    /* access modifiers changed from: 0000 */
    public void addRecentSearchesIfNeeded(List<String> recentSearches) {
        if (!recentSearches.isEmpty()) {
            this.models.add(this.recentSearchesTitleModel);
            this.models.addAll(recentSearchesToEpoxyModels(recentSearches));
        }
    }

    /* access modifiers changed from: 0000 */
    public void setLoading() {
        clearAllExceptInput();
        this.models.add(this.loadingEpoxyModel);
    }

    private ImmutableList<StandardRowEpoxyModel_> recentSearchesToEpoxyModels(List<String> recentSearches) {
        return FluentIterable.from((Iterable<E>) recentSearches).transform(InboxSearchResultsAdapter$$Lambda$4.lambdaFactory$(this)).toList();
    }

    static /* synthetic */ void lambda$null$2(InboxSearchResultsAdapter inboxSearchResultsAdapter, String s, View view) {
        inboxSearchResultsAdapter.inboxSearchListener.onClickRecentSearch(s);
        inboxSearchResultsAdapter.setSearchQuery(s);
    }

    /* access modifiers changed from: private */
    public ThreadPreviewEpoxyModel generateModel(final int position, final InboxSearchResult result) {
        return ThreadPreviewModelFactory.create(this.context, this.accountManager.getCurrentUser(), this.inboxType, result.getThread(), new ThreadClickListener() {
            public void onClick(Thread thread) {
                InboxSearchResultsAdapter.this.inboxSearchListener.onThreadSelected(position, result);
            }

            public boolean onLongClick(Thread thread) {
                return false;
            }

            public void onReviewButtonClick(Thread thread) {
                InboxSearchResultsAdapter.this.inboxSearchListener.onReviewButtonClick(result);
            }

            public void onSwipe(Thread thread) {
            }
        }, false).subtitleText(result.getAttributedTextsSpannable());
    }
}
