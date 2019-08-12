package com.airbnb.android.cohosting.controllers;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.cohosting.executors.CohostLeadsCenterActionExecutor;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.models.CohostInquiry;
import com.airbnb.android.core.models.CohostingContract;
import com.airbnb.android.utils.ListUtils;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import p032rx.functions.Action1;

public class CohostLeadsCenterDataController {
    public final CohostLeadsCenterActionExecutor actionExecutor;
    @State
    ArrayList<CohostingContract> contracts;
    @State
    ArrayList<CohostInquiry> inquiries;
    @State
    boolean loading;
    private final List<UpdateListener> updateListeners = Lists.newArrayList();

    public interface UpdateListener {
        void dataLoading(boolean z);

        void dataUpdated();
    }

    public CohostLeadsCenterDataController(Context context, CohostLeadsCenterActionExecutor actionExecutor2, Bundle savedInstanceState) {
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

    public List<CohostingContract> getAcceptedContracts() {
        ArrayList<CohostingContract> acceptedContracts = new ArrayList<>();
        if (ListUtils.isNullOrEmpty(this.contracts)) {
            return acceptedContracts;
        }
        return FluentIterable.from((Iterable<E>) this.contracts).filter(CohostLeadsCenterDataController$$Lambda$1.lambdaFactory$()).toList();
    }

    static /* synthetic */ boolean lambda$getAcceptedContracts$0(CohostingContract v) {
        return v.getStatus() == 2;
    }

    public ArrayList<CohostInquiry> getInquiries() {
        return this.inquiries;
    }

    public ArrayList<CohostInquiry> getRespondedInquiries() {
        return new ArrayList<>();
    }

    public void setLoading(boolean loading2) {
        this.loading = loading2;
        notifyListeners(CohostLeadsCenterDataController$$Lambda$2.lambdaFactory$(loading2));
    }

    public String getEarningSumWithCurrency(long inquiryId) {
        return "";
    }

    public String getLengthOfHosting(long inquiryId) {
        return "";
    }

    public String getHostingFee(long inquiryId) {
        return "";
    }

    public CohostInquiry getCohostInquiry(long inquiryId) {
        return (CohostInquiry) FluentIterable.from((Iterable<E>) this.inquiries).filter(CohostLeadsCenterDataController$$Lambda$3.lambdaFactory$(inquiryId)).first().orNull();
    }

    static /* synthetic */ boolean lambda$getCohostInquiry$2(long inquiryId, CohostInquiry v) {
        return v.getId() == inquiryId;
    }

    private void notifyListeners(Action1<UpdateListener> listenerAction) {
        for (UpdateListener updateListener : this.updateListeners) {
            listenerAction.call(updateListener);
        }
    }
}
