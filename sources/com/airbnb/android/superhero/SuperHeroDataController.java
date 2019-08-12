package com.airbnb.android.superhero;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.superhero.SuperHeroManager;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.core.utils.MapUtil;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.superhero.SuperHeroMessage.Status;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import p032rx.Observable;
import p032rx.Observer;
import p032rx.android.schedulers.AndroidSchedulers;
import p032rx.schedulers.Schedulers;

public class SuperHeroDataController {
    private Context context;
    private SuperHeroDataChangedListener dataChangedListener;
    private final SuperHeroManager superHeroManager;
    @State
    SuperHeroSortedSet superHeroMessages = new SuperHeroSortedSet();
    @State
    ArrayList<SuperHeroMessage> superHeroPostMessages = new ArrayList<>();
    final RequestListener<SuperHeroActionResponse> superheroActionListener = new C0699RL().onResponse(SuperHeroDataController$$Lambda$1.lambdaFactory$(this)).onError(SuperHeroDataController$$Lambda$2.lambdaFactory$()).build();
    private final SuperHeroTableOpenHelper tableOpenHelper;

    public interface SuperHeroDataChangedListener {
        void onMessagesUpdated(Collection<SuperHeroMessage> collection);
    }

    public SuperHeroDataController(Context context2, Bundle savedState, SuperHeroTableOpenHelper tableOpenHelper2, SuperHeroManager superHeroManager2) {
        this.context = context2;
        this.tableOpenHelper = tableOpenHelper2;
        this.superHeroManager = superHeroManager2;
        if (savedState != null) {
            IcepickWrapper.restoreInstanceState(this, savedState);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void setDataChangedListener(SuperHeroDataChangedListener listener) {
        this.dataChangedListener = listener;
    }

    public void fetchMessagesForInbox() {
        Observable.fromCallable(SuperHeroDataController$$Lambda$3.lambdaFactory$(this)).subscribeOn(Schedulers.m4048io()).observeOn(AndroidSchedulers.mainThread()).subscribe(SuperHeroDataController$$Lambda$4.lambdaFactory$(this));
    }

    public Collection<SuperHeroMessage> getSuperHeroMessages() {
        return this.superHeroMessages;
    }

    public void onSuperHeroAction(SuperHeroAction superHeroAction) {
        SuperHeroPostResponse postResponse = superHeroAction.post_response();
        DestinationType destinationType = postResponse == null ? superHeroAction.destinationTypeEnum() : postResponse.destinationTypeEnum();
        String destination = postResponse == null ? superHeroAction.destination() : postResponse.destination();
        long id = superHeroAction.mo11513id();
        switch (destinationType) {
            case MAP:
                this.context.startActivity(MapUtil.intentFor(this.context, 0.0d, 0.0d, destination));
                return;
            case DEEPLINK:
                DeepLinkUtils.startActivityForDeepLink(this.context, destination);
                return;
            case POST:
                SuperHeroActionRequest.forMessageId(id).withListener((Observer) this.superheroActionListener).execute(NetworkUtil.singleFireExecutor());
                return;
            case HERO:
                SuperHeroMessage postMessage = postResponse == null ? null : postResponse.next_message();
                if (postMessage != null) {
                    this.superHeroPostMessages.add(postMessage.toBuilder().mo11547id(id).starts_at(AirDateTime.now()).status((long) Status.PRESENTED.value).build());
                    if (this.dataChangedListener != null) {
                        this.superHeroMessages.addAll(this.superHeroPostMessages);
                        this.dataChangedListener.onMessagesUpdated(this.superHeroMessages);
                        return;
                    }
                    return;
                }
                return;
            default:
                BugsnagWrapper.notify((Throwable) new IllegalStateException("Unknown destination type for SuperHeroAction " + destinationType));
                return;
        }
    }

    /* access modifiers changed from: private */
    public void handleDatabaseResults(List<SuperHeroMessage> superHeroMessages2) {
        if (this.dataChangedListener != null) {
            this.superHeroMessages.addAll(superHeroMessages2);
            this.dataChangedListener.onMessagesUpdated(this.superHeroMessages);
        }
        Observable.from((Iterable<? extends T>) superHeroMessages2).filter(SuperHeroDataController$$Lambda$5.lambdaFactory$()).observeOn(Schedulers.m4048io()).subscribe(SuperHeroDataController$$Lambda$6.lambdaFactory$(this));
    }

    static /* synthetic */ Boolean lambda$handleDatabaseResults$1(SuperHeroMessage superHeroMessage) {
        return Boolean.valueOf(superHeroMessage.statusEnum() == Status.TRIGGERED);
    }

    static /* synthetic */ void lambda$new$4(AirRequestNetworkException e) {
    }
}
