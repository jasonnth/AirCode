package com.jumio.analytics.http;

import android.content.Context;
import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import com.jumio.analytics.AnalyticsEvent;
import com.jumio.analytics.DispatchException;
import com.jumio.analytics.EventDispatcher;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.commons.log.Log;
import com.jumio.commons.remote.exception.UnexpectedResponseException;
import com.jumio.core.network.ApiCall.DynamicProvider;
import com.jumio.core.network.SimpleApiCall;
import com.jumio.core.network.ale.AleKeyUpdateException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class HttpEventDispatcher extends SimpleApiCall<Void> implements EventDispatcher {
    private static final int TIMEOUT_MS = 20000;
    private Collection<AnalyticsEvent> events;

    private class EventTypeComparator implements Comparator<AnalyticsEvent> {
        private EventTypeComparator() {
        }

        public int compare(AnalyticsEvent lhs, AnalyticsEvent rhs) {
            if (lhs.getEventType() < rhs.getEventType()) {
                return -1;
            }
            if (lhs.getEventType() > rhs.getEventType()) {
                return 1;
            }
            return 0;
        }
    }

    public HttpEventDispatcher(Context context, DynamicProvider dynamicProvider, String endpoint, String userAgent) {
        super(context, dynamicProvider, null);
        configure(endpoint, userAgent);
        setTimeout(20000);
    }

    /* access modifiers changed from: protected */
    public String getUri() {
        return "analytics/events";
    }

    /* access modifiers changed from: protected */
    public void responseReceived(int status, String message, long time, String plainTextResponse) {
        super.responseReceived(status, message, time, plainTextResponse);
    }

    public void dispatchEvents(Collection<AnalyticsEvent> events2) throws DispatchException {
        this.events = events2;
        Log.m1909d(JumioAnalytics.LOGTAG, "HttpEventDispatcher Dispatching " + events2.size() + " events");
        try {
            if (isDeviceOffline()) {
                Log.m1929w(this.TAG, "Device is offline");
                throw new DispatchException("Device is offline");
            }
            Log.m1924v(this.TAG, "execute()");
            try {
                execute();
            } catch (AleKeyUpdateException e) {
                Log.m1919i(this.TAG, "### ALE key update required. Re-execute call");
                execute();
            }
        } catch (UnexpectedResponseException e2) {
            throw new DispatchException(e2.getStatusCode() + " -- " + e2.getMessage());
        } catch (IOException e3) {
            Log.m1930w(JumioAnalytics.LOGTAG, "Exception while sending!", (Throwable) e3);
            throw new DispatchException((Exception) e3);
        } catch (DispatchException e4) {
            throw e4;
        } catch (Exception e5) {
            Log.m1930w(JumioAnalytics.LOGTAG, "General exception", (Throwable) e5);
            throw new DispatchException(e5);
        }
    }

    /* access modifiers changed from: protected */
    public String getRequest() throws Exception {
        if (this.events == null || this.events.isEmpty()) {
            throw new IllegalArgumentException("event list cannot be empty!");
        }
        JSONObject request = new JSONObject();
        request.put("sessionId", ((AnalyticsEvent) this.events.iterator().next()).getSessionId().toString());
        EventTypeComparator comparator = new EventTypeComparator();
        ArrayList<AnalyticsEvent> sortedList = new ArrayList<>(this.events);
        Collections.sort(sortedList, comparator);
        JSONArray eventArray = new JSONArray();
        Iterator it = sortedList.iterator();
        while (it.hasNext()) {
            AnalyticsEvent e = (AnalyticsEvent) it.next();
            JSONObject event = new JSONObject();
            event.put("eventType", e.getEventType());
            event.put(ErfExperimentsModel.TIMESTAMP, e.getTimestamp());
            event.put("payload", e.getPayload().toJson());
            eventArray.put(event);
        }
        request.put("events", eventArray);
        Log.m1924v(JumioAnalytics.LOGTAG, "Request body: \n" + request.toString(3));
        return request.toString();
    }

    /* access modifiers changed from: protected */
    public Void parseResponse(String plainTextAnswer) {
        return null;
    }
}
