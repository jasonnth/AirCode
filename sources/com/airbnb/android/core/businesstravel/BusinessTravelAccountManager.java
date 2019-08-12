package com.airbnb.android.core.businesstravel;

import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.businesstravel.BusinessTravelEmployeeFetchedEvent;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.businesstravel.models.BusinessTravelEmployee;
import com.airbnb.android.core.enums.WorkEmailStatus;
import com.airbnb.android.core.events.AuthStateEvent;
import com.airbnb.android.core.models.ReservationDetails.TripType;
import com.airbnb.android.core.requests.businesstravel.GetBusinessTravelEmployeeRequest;
import com.airbnb.android.core.responses.businesstravel.BusinessTravelEmployeeResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.ParcelStrap;
import com.airbnb.rxgroups.RequestSubscription;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import p032rx.Observer;

public class BusinessTravelAccountManager {
    private final AirbnbAccountManager accountManager;
    private final Bus bus;
    private final NonResubscribableRequestListener<BusinessTravelEmployeeResponse> businessTravelEmployeeListener = new C0699RL().onResponse(BusinessTravelAccountManager$$Lambda$1.lambdaFactory$(this)).onComplete(BusinessTravelAccountManager$$Lambda$2.lambdaFactory$(this)).buildWithoutResubscription();
    private BusinessTravelEmployee employee;
    /* access modifiers changed from: private */
    public RequestSubscription getBusinessTravelEmployeeRequest;
    private boolean isUsingBTRFilter;

    public BusinessTravelAccountManager(AirbnbAccountManager accountManager2, Bus bus2) {
        this.accountManager = accountManager2;
        this.bus = bus2;
        this.bus.register(this);
    }

    @Subscribe
    public void onAuthStatusChanged(AuthStateEvent event) {
        clearBusinessTravelEmployeeInfo();
    }

    public void fetchBusinessTravelEmployeeInfo() {
        if (this.employee == null && this.accountManager.isCurrentUserAuthorized() && this.getBusinessTravelEmployeeRequest == null) {
            this.getBusinessTravelEmployeeRequest = new GetBusinessTravelEmployeeRequest(this.accountManager.getCurrentUserId()).withListener((Observer) this.businessTravelEmployeeListener).execute(NetworkUtil.singleFireExecutor());
        }
    }

    public void forceFetchBusinessTravelEmployeeInfo() {
        this.getBusinessTravelEmployeeRequest = new GetBusinessTravelEmployeeRequest(this.accountManager.getCurrentUserId()).withListener((Observer) this.businessTravelEmployeeListener).skipCache().execute(NetworkUtil.singleFireExecutor());
    }

    public BusinessTravelEmployee getBusinessTravelEmployee() {
        return this.employee;
    }

    public void setBusinessTravelEmployee(BusinessTravelEmployee employee2) {
        this.employee = employee2;
    }

    public String getWorkEmail() {
        if (this.employee == null) {
            return null;
        }
        return this.employee.getEmail();
    }

    public WorkEmailStatus getWorkEmailStatus() {
        return this.employee == null ? WorkEmailStatus.None : this.employee.getWorkEmailStatus();
    }

    public boolean hasBusinessTravelEmployeeInfo() {
        return this.employee != null;
    }

    public boolean isVerifiedBusinessTraveler() {
        return hasBusinessTravelEmployeeInfo() && this.employee.isVerified();
    }

    public void logBTSurveyResults(TripType tripType, long reservationId, String confirmationCode) {
        if (tripType == TripType.BusinessUnverified || tripType == TripType.PersonalUnverified) {
            BusinessTravelAnalytics.trackEvent("p4", "about_this_trip", "success", tripType == TripType.BusinessUnverified ? "unmanaged_business_trip" : "unmanaged_personal_trip", ParcelStrap.make().mo9946kv("user_id", String.valueOf(this.accountManager.getCurrentUserId())).mo9946kv("reservation_id", String.valueOf(reservationId)).mo9946kv("confirmation_code", confirmationCode));
        }
    }

    public boolean isBTRFilterApplied() {
        return this.isUsingBTRFilter;
    }

    public void setUsingBTRFilter(boolean isUsingBTRFilter2) {
        this.isUsingBTRFilter = isUsingBTRFilter2;
    }

    private void clearBusinessTravelEmployeeInfo() {
        this.employee = null;
        cancelGetBusinessTravelEmployeeRequest();
    }

    private void cancelGetBusinessTravelEmployeeRequest() {
        if (this.getBusinessTravelEmployeeRequest != null) {
            this.getBusinessTravelEmployeeRequest.cancel();
            this.getBusinessTravelEmployeeRequest = null;
        }
    }

    /* access modifiers changed from: private */
    public void onFetchBusinessTravelEmployeeComplete(BusinessTravelEmployee businessTravelEmployee) {
        this.employee = businessTravelEmployee;
        this.bus.post(new BusinessTravelEmployeeFetchedEvent());
    }
}
