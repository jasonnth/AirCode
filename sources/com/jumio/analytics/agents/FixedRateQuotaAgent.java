package com.jumio.analytics.agents;

import com.jumio.analytics.EventDispatcher;
import com.jumio.analytics.Filter;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.commons.log.Log;

public class FixedRateQuotaAgent extends FixedRateAgent {
    private static final int QUOTA_DEFAULT = 20;
    public final int mEventQueueQuota;

    public FixedRateQuotaAgent() {
        this.mEventQueueQuota = 20;
        Log.m1924v(JumioAnalytics.LOGTAG, "new FixedRateQuotaAgent(): quota is " + this.mEventQueueQuota);
    }

    public FixedRateQuotaAgent(int quota) {
        if (quota < 0) {
            throw new IllegalArgumentException("Quota must be >0!");
        }
        this.mEventQueueQuota = quota;
        Log.m1924v(JumioAnalytics.LOGTAG, "new FixedRateQuotaAgent(): quota is " + this.mEventQueueQuota);
    }

    public FixedRateQuotaAgent(EventDispatcher dispatcher) {
        this.mEventQueueQuota = 20;
        Log.m1924v(JumioAnalytics.LOGTAG, "new FixedRateQuotaAgent(): quota is " + this.mEventQueueQuota);
        setEventDispatcher(dispatcher);
    }

    public FixedRateQuotaAgent(EventDispatcher dispatcher, Filter disabledEvents) {
        this(dispatcher);
        this.mFilter = disabledEvents;
    }

    /* access modifiers changed from: protected */
    public void eventEnqueued() {
        if (this.mRequestQueue.size() >= this.mEventQueueQuota) {
            Log.m1909d(JumioAnalytics.LOGTAG, "Quota trigger (" + this.mRequestQueue.size() + " >= " + this.mEventQueueQuota + "), starting dispatch");
            dispatchAndClear();
        }
    }

    public int getQuota() {
        return this.mEventQueueQuota;
    }
}
