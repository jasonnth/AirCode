package com.airbnb.android.lib.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.controllers.BottomBarController;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.ThreadFragmentIntents;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.inbox.InboxFragment;
import com.airbnb.android.lib.fragments.inbox.ThreadList;
import com.airbnb.android.lib.fragments.inbox.ThreadList.Listener;
import com.airbnb.android.lib.views.EmptyResultsCardView;
import com.airbnb.android.superhero.SuperHeroThreadFragment;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ViewUtils;
import icepick.State;

public class InboxContainerFragment extends AirFragment {
    private static final String INBOX_TAG = "inbox_tag";
    private static final String KEY_INBOX_TYPE = "inbox_type";
    BottomBarController bottomBarController;
    @State
    long currentThreadId = -1;
    @BindView
    EmptyResultsCardView emptyResultsCard;
    @BindView
    View messagingContentContainer;
    private final Listener threadClickListener = new Listener() {
        public void onThreadClicked(InboxType inboxType, Thread thread, Long postId) {
            InboxContainerFragment.this.launchMessageThread(inboxType, thread.getId(), postId);
            thread.setUnread(false);
        }

        public void onThreadsLoaded(InboxType inboxType, Thread thread) {
            if (InboxContainerFragment.this.canShowDetailFragment() && InboxContainerFragment.this.currentThreadId == -1) {
                onThreadClicked(inboxType, thread, null);
            }
        }

        public void onSuperHeroClicked() {
            InboxContainerFragment.this.startActivity(SuperHeroThreadFragment.newIntent(InboxContainerFragment.this.getContext()));
        }

        public boolean setEmptyState(InboxType inboxType, boolean isEmpty) {
            if (!InboxContainerFragment.this.canShowEmptyView()) {
                return false;
            }
            InboxContainerFragment.this.showEmptyResults(isEmpty);
            return true;
        }
    };

    public static InboxContainerFragment newInstance(InboxType inboxType) {
        return (InboxContainerFragment) ((FragmentBundleBuilder) FragmentBundler.make(new InboxContainerFragment()).putSerializable("inbox_type", inboxType)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        View view = inflater.inflate(isTabletScreen() ? C0880R.layout.fragment_inbox_detail_view : C0880R.layout.fragment_inbox_only_container, container, false);
        bindViews(view);
        setupEmptyResults();
        InboxType inboxType = (InboxType) Check.notNull((InboxType) getArguments().getSerializable("inbox_type"));
        FragmentManager fragmentManager = getChildFragmentManager();
        if (fragmentManager.findFragmentByTag(INBOX_TAG) == null) {
            fragmentManager.beginTransaction().add(C0880R.C0882id.frame_inbox, createInboxFragment(inboxType), INBOX_TAG).commit();
        }
        return view;
    }

    private Fragment createInboxFragment(InboxType inboxType) {
        return InboxFragment.newInstance(inboxType);
    }

    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        if (childFragment instanceof ThreadList) {
            ((ThreadList) childFragment).setThreadListListener(this.threadClickListener);
        }
    }

    public void onStart() {
        super.onStart();
        this.bottomBarController.setShowBottomBar(true, true);
    }

    /* access modifiers changed from: private */
    public boolean canShowEmptyView() {
        return this.emptyResultsCard != null;
    }

    private void setupEmptyResults() {
        if (canShowEmptyView()) {
            boolean loggedIn = this.mAccountManager.isCurrentUserAuthorized();
            if (loggedIn) {
                this.emptyResultsCard.setupActionButton(C0880R.string.start_exploring, InboxContainerFragment$$Lambda$1.lambdaFactory$(this));
            } else {
                this.emptyResultsCard.setupActionButton(C0880R.string.sign_in, InboxContainerFragment$$Lambda$2.lambdaFactory$(this));
            }
            showEmptyResults(!loggedIn);
        }
    }

    /* access modifiers changed from: private */
    public void showEmptyResults(boolean showEmptyResults) {
        if (showEmptyResults) {
            this.emptyResultsCard.setBackgroundImageRes(C0880R.C0881drawable.empty_messages);
        }
        ViewUtils.setVisibleIf((View) this.emptyResultsCard, showEmptyResults);
        ViewUtils.setVisibleIf(this.messagingContentContainer, !showEmptyResults);
    }

    /* access modifiers changed from: private */
    public boolean canShowDetailFragment() {
        return this.messagingContentContainer != null;
    }

    private void showDetailFragment(Fragment fragment, long threadId) {
        if (canShowDetailFragment() && this.currentThreadId != threadId) {
            this.currentThreadId = threadId;
            getChildFragmentManager().beginTransaction().replace(C0880R.C0882id.frame_detail, fragment).commit();
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"RestrictedApi"})
    public void launchMessageThread(InboxType inboxType, long threadId, Long scrollToPostId) {
        if (canShowDetailFragment()) {
            showDetailFragment(ThreadFragmentIntents.newInstance(threadId, inboxType, scrollToPostId), threadId);
        } else {
            startActivity(ThreadFragmentIntents.newIntent(getActivity(), threadId, inboxType));
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.InboxContainer;
    }
}
