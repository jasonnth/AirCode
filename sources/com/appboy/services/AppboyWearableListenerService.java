package com.appboy.services;

import android.content.Context;
import com.appboy.Appboy;
import com.appboy.support.AppboyLogger;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;
import java.util.Iterator;
import p004bo.app.C0371ba;
import p004bo.app.C0376be;
import p004bo.app.C0406cg;
import p004bo.app.C0465dw;
import p004bo.app.C0637z;

public class AppboyWearableListenerService extends WearableListenerService {

    /* renamed from: a */
    private static final String f2907a = AppboyLogger.getAppboyLogTag(AppboyWearableListenerService.class);

    /* renamed from: b */
    private C0376be<C0406cg> f2908b;

    /* renamed from: c */
    private GoogleApiClient f2909c;

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.google.android.gms.wearable.WearableListenerService, com.appboy.services.AppboyWearableListenerService] */
    public void onCreate() {
        AppboyWearableListenerService.super.onCreate();
        AppboyLogger.m1733d(f2907a, "AppboyWearableListenerService started via onCreate().");
        this.f2909c = new Builder(this).addApiIfAvailable(Wearable.API, new Scope[0]).build();
        this.f2909c.connect();
        this.f2908b = new C0371ba(AppboyWearableListenerService.super.getApplicationContext());
    }

    public void onDataChanged(DataEventBuffer dataEvents) {
        if (this.f2909c.hasConnectedApi(Wearable.API)) {
            Iterator it = dataEvents.iterator();
            while (it.hasNext()) {
                DataEvent dataEvent = (DataEvent) it.next();
                if (dataEvent.getType() == 1) {
                    DataItem dataItem = dataEvent.getDataItem();
                    doAppboySdkActionFromDataMap(AppboyWearableListenerService.super.getApplicationContext(), DataMapItem.fromDataItem(dataItem).getDataMap());
                    Wearable.DataApi.deleteDataItems(this.f2909c, dataItem.getUri());
                }
            }
        }
    }

    public void doAppboySdkActionFromDataMap(Context context, DataMap dataMap) {
        C0637z b = C0465dw.m558b(dataMap);
        if (b == C0637z.SEND_WEAR_DEVICE) {
            C0406cg a = C0465dw.m555a(dataMap);
            if (a != null) {
                this.f2908b.mo6792a(a);
                return;
            }
            return;
        }
        AppboyLogger.m1737i(f2907a, "Received Wear sdk action of type: " + b.name());
        C0465dw.m557a(dataMap, Appboy.getInstance(context));
    }
}
