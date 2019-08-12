package com.airbnb.android.cohosting.controllers;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.cohosting.executors.CohostInvitationActionExecutor;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.models.CohostInvitation;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.User;
import com.google.common.collect.Lists;
import icepick.State;
import java.util.List;
import p032rx.functions.Action1;

public class CohostInvitationDataController {
    public final CohostInvitationActionExecutor actionExecutor;
    @State
    CohostInvitation cohostInvitation;
    @State
    boolean loading;
    private final List<UpdateListener> updateListeners = Lists.newArrayList();

    public interface UpdateListener {
        void dataLoading(boolean z);

        void dataUpdated();
    }

    public CohostInvitationDataController(Context context, CohostInvitationActionExecutor actionExecutor2, Bundle savedInstanceState) {
        this.actionExecutor = actionExecutor2;
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
        setLoading(this.loading);
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void addListener(UpdateListener updateListener) {
        this.updateListeners.add(updateListener);
        updateListener.dataLoading(this.loading);
        updateListener.dataUpdated();
    }

    public void removeListener(UpdateListener updateListener) {
        this.updateListeners.remove(updateListener);
    }

    public void setLoading(boolean loading2) {
        this.loading = loading2;
        notifyListeners(CohostInvitationDataController$$Lambda$1.lambdaFactory$(loading2));
    }

    public CohostInvitation getCohostInvitation() {
        return this.cohostInvitation;
    }

    public long getListingId() {
        return this.cohostInvitation.getListing().getId();
    }

    public User getInviter() {
        return this.cohostInvitation.getInviter();
    }

    public Listing getListing() {
        return this.cohostInvitation.getListing();
    }

    public void setCohostInvitation(CohostInvitation invitation) {
        this.cohostInvitation = invitation;
        this.loading = false;
        updateListeners();
    }

    public void setListingAddressForInvitation(CohostInvitation cohostInvitation2) {
        this.cohostInvitation.getListing().setAddress(cohostInvitation2.getListing().getAddress());
        this.loading = false;
        updateListeners();
    }

    /* access modifiers changed from: 0000 */
    public void updateListeners() {
        notifyListeners(CohostInvitationDataController$$Lambda$2.lambdaFactory$());
    }

    private void notifyListeners(Action1<UpdateListener> listenerAction) {
        for (UpdateListener updateListener : this.updateListeners) {
            listenerAction.call(updateListener);
        }
    }
}
