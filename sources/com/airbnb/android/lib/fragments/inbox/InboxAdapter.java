package com.airbnb.android.lib.fragments.inbox;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.messaging.MessagingRequestFactory;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Post;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.requests.AirRequestFactory.Consumer;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.viewcomponents.viewmodels.ThreadPreviewEpoxyModel_;
import com.airbnb.android.superhero.SuperHeroMessage;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InboxAdapter extends AirEpoxyAdapter implements Consumer<Thread> {
    AirbnbAccountManager accountManager;
    private final Context context;
    /* access modifiers changed from: private */
    public final InboxType inboxType;
    @State
    Thread lastInsertedThread;
    /* access modifiers changed from: private */
    public final Listener listener;
    MessagingRequestFactory messagingRequestFactory;
    @State
    ArrayList<Thread> savedThreads = new ArrayList<>();
    @State
    SuperHeroMessage superHeroMessageToPreview;
    final ThreadClickListener threadClickProxy = new ThreadClickListener() {
        public void onClick(Thread thread) {
            if (thread.isUnread()) {
                InboxAdapter.this.messagingRequestFactory.expireCacheForThread(thread.getId(), InboxAdapter.this.inboxType);
                ThreadPreviewEpoxyModel_ model = InboxAdapter.this.findThreadModel(thread.getId());
                if (model != null) {
                    thread.setUnread(false);
                    model.showUnread(false);
                    InboxAdapter.this.notifyModelChanged(model);
                }
            }
            InboxAdapter.this.listener.onClick(thread, (long) ListUtils.firstIndexOf(InboxAdapter.this.models, InboxAdapter$1$$Lambda$1.lambdaFactory$(thread)));
        }

        static /* synthetic */ boolean lambda$onClick$0(Thread thread, EpoxyModel model) {
            return model.mo11715id() == thread.getId();
        }

        public boolean onLongClick(Thread thread) {
            return InboxAdapter.this.listener.onLongClick(thread);
        }

        public void onReviewButtonClick(Thread thread) {
            InboxAdapter.this.listener.onReviewButtonClick(thread);
        }

        public void onSwipe(Thread thread) {
            InboxAdapter.this.listener.onSwipe(thread);
        }
    };

    interface Listener {
        void onClick(Thread thread, long j);

        boolean onLongClick(Thread thread);

        void onReviewButtonClick(Thread thread);

        void onSuperHeroClick();

        void onSwipe(Thread thread);
    }

    InboxAdapter(Context context2, Bundle inState, InboxType inboxType2, Listener listener2) {
        this.context = context2;
        this.inboxType = inboxType2;
        this.listener = listener2;
        ((AirbnbGraph) AirbnbApplication.instance(context2).component()).inject(this);
        onRestoreInstanceState(inState);
        updateSuperHeroModel();
        appendThreadModels(this.savedThreads);
    }

    public void removeThread(long threadId) {
        ThreadPreviewEpoxyModel_ model = findThreadModel(threadId);
        if (model != null) {
            removeModel(model);
        }
    }

    public void hideThread(long threadId) {
        ThreadPreviewEpoxyModel_ model = findThreadModel(threadId);
        if (model != null) {
            hideModel(model);
        }
    }

    public void showThread(long threadId) {
        ThreadPreviewEpoxyModel_ model = findThreadModel(threadId);
        if (model != null) {
            showModel(model);
        }
    }

    public void setThreads(List<Thread> topThreads) {
        if (!ThreadPreviewModelFactory.doModelsMatchThreads(FluentIterable.from((Iterable<E>) this.models).skip(isFirstModelSuperHero() ? 1 : 0).limit(topThreads.size()), topThreads)) {
            this.lastInsertedThread = null;
            this.models.clear();
            this.savedThreads.clear();
            updateSuperHeroModel();
            addAll(topThreads);
            notifyDataSetChanged();
        }
    }

    public void removeAllThreads() {
        removeAllModels();
        this.savedThreads.clear();
    }

    public void setSuperHeroThreadPreview(SuperHeroMessage superHeroMessageToPreview2) {
        this.superHeroMessageToPreview = superHeroMessageToPreview2;
        updateSuperHeroModel();
    }

    private void updateSuperHeroModel() {
        if (this.superHeroMessageToPreview != null) {
            ThreadPreviewEpoxyModel_ superHeroModel = ThreadPreviewModelFactory.createSuperHeroModel(this.context, this.superHeroMessageToPreview, InboxAdapter$$Lambda$1.lambdaFactory$(this));
            if (isFirstModelSuperHero()) {
                this.models.set(0, superHeroModel);
                notifyItemChanged(0);
                return;
            }
            this.models.add(0, superHeroModel);
            notifyItemInserted(0);
        }
    }

    private boolean isFirstModelSuperHero() {
        return !ListUtils.isEmpty((Collection<?>) this.models) && ThreadPreviewModelFactory.isSuperHeroModel((EpoxyModel) this.models.get(0));
    }

    public void addAll(Collection<? extends Thread> collection) {
        if (!collection.isEmpty()) {
            this.lastInsertedThread = (Thread) ListUtils.getLast(Lists.newArrayList((Iterable<? extends E>) collection));
        }
        this.savedThreads.addAll(collection);
        appendThreadModels(collection);
    }

    private void appendThreadModels(Collection<? extends Thread> threads) {
        addModels((Collection<? extends EpoxyModel<?>>) threadsToEpoxyModels(threads));
    }

    public boolean onMessageReceived(long threadId, Post post) {
        boolean wasMarkedUnread = false;
        ThreadPreviewEpoxyModel_ model = findThreadModel(threadId);
        if (model != null) {
            boolean wasUnread = model.showUnread();
            ThreadPreviewModelFactory.updateModel(model, post);
            if (!wasUnread && model.showUnread()) {
                wasMarkedUnread = true;
            }
            notifyModelChanged(model);
        }
        return wasMarkedUnread;
    }

    public Thread getLastThread() {
        return this.lastInsertedThread;
    }

    /* access modifiers changed from: private */
    public ThreadPreviewEpoxyModel_ findThreadModel(long threadId) {
        for (EpoxyModel<?> model : this.models) {
            if (model.mo11715id() == threadId && (model instanceof ThreadPreviewEpoxyModel_)) {
                return (ThreadPreviewEpoxyModel_) model;
            }
        }
        return null;
    }

    private List<ThreadPreviewEpoxyModel_> threadsToEpoxyModels(Collection<? extends Thread> threads) {
        return FluentIterable.from((Iterable<E>) threads).transform(InboxAdapter$$Lambda$2.lambdaFactory$(this)).toList();
    }

    /* access modifiers changed from: private */
    public ThreadPreviewEpoxyModel_ threadToModel(Thread thread) {
        return ThreadPreviewModelFactory.create(this.context, this.accountManager.getCurrentUser(), this.inboxType, thread, this.threadClickProxy, FeatureToggles.shouldEnableSwipeToArchive(this.inboxType));
    }
}
