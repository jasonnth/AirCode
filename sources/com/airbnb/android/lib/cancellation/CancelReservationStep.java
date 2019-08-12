package com.airbnb.android.lib.cancellation;

public enum CancelReservationStep {
    Reason(DLSCancelReservationReasonFragment.class, CancellationAnalytics.VALUE_PAGE_REASON),
    Asked(DLSCancelReservationAskedFragment.class, CancellationAnalytics.VALUE_PAGE_ASKED),
    Date(DLSCancelReservationDatesFragment.class, CancellationAnalytics.VALUE_PAGE_DATES),
    Emergency(DLSCancelReservationEmergencyFragment.class, CancellationAnalytics.VALUE_PAGE_EMERGENCY),
    Other(DLSCancelReservationOtherFragment.class, "other"),
    Summary(DLSCancelReservationSummaryFragment.class, "summary");
    
    public final Class<? extends DLSCancelReservationBaseFragment> fragmentClass;
    public final String pageName;

    private CancelReservationStep(Class fragmentClass2, String pageName2) {
        this.fragmentClass = fragmentClass2;
        this.pageName = pageName2;
    }
}
