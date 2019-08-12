package com.airbnb.android.cohosting.controllers;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.enums.CohostManagementLaunchType;
import com.airbnb.android.cohosting.executors.CohostManagementActionExecutor;
import com.airbnb.android.cohosting.utils.CohostingConstants.FeeType;
import com.airbnb.android.cohosting.utils.CohostingLoggingUtil;
import com.airbnb.android.cohosting.utils.CohostingUtil;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.models.CohostInvitation;
import com.airbnb.android.core.models.CohostingContract;
import com.airbnb.android.core.models.CohostingNotification;
import com.airbnb.android.core.models.CohostingNotification.MuteType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.core.utils.Check;
import com.airbnb.jitney.event.logging.CohostingContext.p062v1.C1951CohostingContext;
import com.airbnb.jitney.event.logging.CohostingSourceFlow.p068v1.C1958CohostingSourceFlow;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import icepick.State;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p032rx.functions.Action1;

public class CohostManagementDataController {
    AirbnbAccountManager accountManager;
    public final CohostManagementActionExecutor actionExecutor;
    @State
    boolean canCurrentUserAccessResolutionCenter;
    @State
    ArrayList<CohostInvitation> cohostInvitations;
    @State
    FeeType feeType;
    @State
    boolean isCurrentUserListingAdmin;
    @State
    Listing listing;
    @State
    String listingManagerIdOfCurrentUser;
    @State
    ArrayList<ListingManager> listingManagers;
    @State
    boolean loading;
    @State
    MuteType muteType;
    @State
    CohostManagementLaunchType type;
    private final List<UpdateListener> updateListeners = Lists.newArrayList();

    public interface UpdateListener {
        void dataLoading(boolean z);

        void dataUpdated();
    }

    public CohostManagementDataController(Context context, CohostManagementActionExecutor actionExecutor2, Bundle savedInstanceState) {
        this.actionExecutor = actionExecutor2;
        ((CohostingGraph) CoreApplication.instance(context).component()).inject(this);
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
        setLoading(this.loading);
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public List<ListingManager> getListingManagers() {
        return this.listingManagers;
    }

    public ListingManager getListingManager(String id) {
        Iterator it = this.listingManagers.iterator();
        while (it.hasNext()) {
            ListingManager manager = (ListingManager) it.next();
            if (manager.getId().equals(id)) {
                return manager;
            }
        }
        return null;
    }

    public List<CohostInvitation> getCohostInvitations() {
        return this.cohostInvitations;
    }

    public CohostInvitation getCohostInvitation(long invitationId) {
        Iterator it = this.cohostInvitations.iterator();
        while (it.hasNext()) {
            CohostInvitation invitation = (CohostInvitation) it.next();
            if (invitation.getId() == invitationId) {
                return invitation;
            }
        }
        return null;
    }

    public int getNumOfPendingCohostInvitations() {
        int activeInvitationNum = 0;
        Iterator it = this.cohostInvitations.iterator();
        while (it.hasNext()) {
            if (((CohostInvitation) it.next()).getStatus() == 1) {
                activeInvitationNum++;
            }
        }
        return activeInvitationNum;
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
        notifyListeners(CohostManagementDataController$$Lambda$1.lambdaFactory$(loading2));
    }

    public void setListingPrimaryHost(ListingManager manager) {
        this.listing.setPrimaryHost(manager.getUser());
        updateListeners();
    }

    public void setCohostingContract(CohostingContract contract, String managerId) {
        Iterator it = this.listingManagers.iterator();
        while (it.hasNext()) {
            ListingManager manager = (ListingManager) it.next();
            if (manager.getId().equals(managerId)) {
                manager.setContract(contract);
            } else {
                manager.setContract(null);
            }
        }
        updateListeners();
    }

    public void removeCohostingContract(String managerId) {
        Iterator it = this.listingManagers.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ListingManager manager = (ListingManager) it.next();
            if (manager.getId().equals(managerId)) {
                manager.setContract(null);
                break;
            }
        }
        updateListeners();
    }

    public void setData(Listing listing2, List<ListingManager> listingManagers2, List<CohostInvitation> cohostInvitations2, CohostingNotification cohostingNotification, CohostManagementLaunchType type2) {
        this.listingManagers = new ArrayList<>(listingManagers2);
        this.cohostInvitations = new ArrayList<>(cohostInvitations2);
        this.listing = listing2;
        this.type = type2;
        this.muteType = MuteType.values()[cohostingNotification.getMuteType()];
        this.loading = false;
        setCurrentUserValues();
        updateListeners();
    }

    public void updateCohostInvitation(CohostInvitation newInvitation) {
        int index = 0;
        while (true) {
            if (index >= this.cohostInvitations.size()) {
                break;
            } else if (((CohostInvitation) this.cohostInvitations.get(index)).getId() == newInvitation.getId()) {
                this.cohostInvitations.set(index, newInvitation);
                break;
            } else {
                index++;
            }
        }
        updateListeners();
    }

    public void appendCohostInvitation(CohostInvitation newInvitation) {
        this.cohostInvitations.add(newInvitation);
        updateListeners();
    }

    public void removeCohostInvitation(long invitationId) {
        Iterator it = this.cohostInvitations.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            CohostInvitation invitation = (CohostInvitation) it.next();
            if (invitation.getId() == invitationId) {
                this.cohostInvitations.remove(invitation);
                break;
            }
        }
        updateListeners();
    }

    public void removeCohost(ListingManager listingManager) {
        Iterator it = this.listingManagers.iterator();
        while (it.hasNext()) {
            ListingManager manager = (ListingManager) it.next();
            if (manager.getId().equals(listingManager.getId())) {
                this.listingManagers.remove(manager);
                return;
            }
        }
        updateListeners();
    }

    public void updateMuteType(CohostingNotification notification) {
        this.muteType = MuteType.values()[notification.getMuteType()];
    }

    public void makeListingManagerPrimaryHost(ListingManager newManager) {
        Iterator it = this.listingManagers.iterator();
        while (it.hasNext()) {
            ListingManager manager = (ListingManager) it.next();
            manager.setIsPrimaryHost(Boolean.valueOf(manager.getId().equals(newManager.getId())));
        }
        updateListeners();
    }

    /* access modifiers changed from: 0000 */
    public void updateListeners() {
        notifyListeners(CohostManagementDataController$$Lambda$2.lambdaFactory$());
    }

    private void notifyListeners(Action1<UpdateListener> listenerAction) {
        for (UpdateListener updateListener : this.updateListeners) {
            listenerAction.call(updateListener);
        }
    }

    public C1951CohostingContext getCohostingContext() {
        return CohostingLoggingUtil.getCohostingContext(this.listing, this.listingManagers);
    }

    public Listing getListing() {
        return this.listing;
    }

    public long getListingId() {
        return this.listing.getId();
    }

    public String getListingManagerIdFromUserId(long userId) {
        Iterator it = this.listingManagers.iterator();
        while (it.hasNext()) {
            ListingManager manager = (ListingManager) it.next();
            if (manager.getUser().getId() == userId) {
                return manager.getId();
            }
        }
        return null;
    }

    public boolean hasCohostsOrInvitations() {
        return this.listingManagers.size() > 1 || this.cohostInvitations.size() > 0;
    }

    public ListingManager getPaidListingManager() {
        return CohostingUtil.getPaidListingManager(this.listingManagers);
    }

    public boolean hasPaidListingManager() {
        return CohostingUtil.hasPaidListingManager(this.listingManagers);
    }

    public CohostInvitation getPaidCohostInvitation() {
        return CohostingUtil.getPaidCohostInvitation(this.cohostInvitations);
    }

    public boolean hasPaidCohostInvitation() {
        return CohostingUtil.hasPaidCohostInvitation(this.cohostInvitations);
    }

    public boolean hasPaidListingManagerOrPaidCohostInvitation() {
        return hasPaidListingManager() || hasPaidCohostInvitation();
    }

    public String getListingManagerIdOfCurrentUser() {
        return this.listingManagerIdOfCurrentUser;
    }

    public boolean isCurrentUserListingAdmin() {
        return this.isCurrentUserListingAdmin;
    }

    public boolean canCurrentUserAccessResolutionCenter() {
        return this.canCurrentUserAccessResolutionCenter;
    }

    public boolean canListingManagerAccessResolutionCenter(ListingManager listingManager) {
        return listingManager.getContract() != null && listingManager.getContract().isCanAccessResolutionCenter().booleanValue();
    }

    public void setFeeType(FeeType feeType2) {
        this.feeType = feeType2;
        updateListeners();
    }

    public FeeType getFeeType() {
        return this.feeType;
    }

    public boolean isUpsell() {
        return this.type == CohostManagementLaunchType.CohostUpsell;
    }

    public C1958CohostingSourceFlow getSourceFlow() {
        return isUpsell() ? C1958CohostingSourceFlow.PostListYourSpace : C1958CohostingSourceFlow.CohostManagement;
    }

    private void setCurrentUserValues() {
        if (TextUtils.isEmpty(this.listingManagerIdOfCurrentUser)) {
            ListingManager manager = (ListingManager) FluentIterable.from((Iterable<E>) this.listingManagers).filter(CohostManagementDataController$$Lambda$3.lambdaFactory$(this)).first().orNull();
            Check.notNull(manager);
            this.listingManagerIdOfCurrentUser = manager.getId();
            this.isCurrentUserListingAdmin = manager.isIsListingAdmin().booleanValue();
            this.canCurrentUserAccessResolutionCenter = manager.getContract() != null && manager.getContract().isCanAccessResolutionCenter().booleanValue();
        }
    }

    static /* synthetic */ boolean lambda$setCurrentUserValues$1(CohostManagementDataController cohostManagementDataController, ListingManager v) {
        return v.getUser().getId() == cohostManagementDataController.accountManager.getCurrentUserId();
    }

    public ListingManager getListingAdmin() {
        Iterator it = this.listingManagers.iterator();
        while (it.hasNext()) {
            ListingManager manager = (ListingManager) it.next();
            if (manager.isIsListingAdmin().booleanValue()) {
                return manager;
            }
        }
        return null;
    }

    public MuteType getMuteType() {
        return this.muteType;
    }
}
