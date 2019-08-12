package com.airbnb.android.lib.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.p000v4.content.LocalBroadcastManager;
import com.airbnb.android.contentframework.ContentFrameworkDeepLinkModuleLoader;
import com.airbnb.android.core.CoreDeepLinkModuleLoader;
import com.airbnb.android.explore.ExploreDeepLinkModuleLoader;
import com.airbnb.android.lib.deeplinks.AirbnbDeepLinkModuleLoader;
import com.airbnb.android.payout.PayoutDeepLinkModuleLoader;
import com.airbnb.android.registration.RegistrationDeepLinkModuleLoader;
import com.airbnb.deeplinkdispatch.DeepLinkEntry;
import com.airbnb.deeplinkdispatch.DeepLinkResult;
import com.airbnb.deeplinkdispatch.Parser;
import java.util.Arrays;
import java.util.List;

public final class DeepLinkDelegate {
    private static final String TAG = DeepLinkDelegate.class.getSimpleName();
    private final List<? extends Parser> loaders;

    public DeepLinkDelegate(AirbnbDeepLinkModuleLoader airbnbDeepLinkModuleLoader, CoreDeepLinkModuleLoader coreDeepLinkModuleLoader, ExploreDeepLinkModuleLoader exploreDeepLinkModuleLoader, RegistrationDeepLinkModuleLoader registrationDeepLinkModuleLoader, ContentFrameworkDeepLinkModuleLoader contentFrameworkDeepLinkModuleLoader, PayoutDeepLinkModuleLoader payoutDeepLinkModuleLoader) {
        this.loaders = Arrays.asList(new Parser[]{airbnbDeepLinkModuleLoader, coreDeepLinkModuleLoader, exploreDeepLinkModuleLoader, registrationDeepLinkModuleLoader, contentFrameworkDeepLinkModuleLoader, payoutDeepLinkModuleLoader});
    }

    private DeepLinkEntry findEntry(String uriString) {
        for (Parser loader : this.loaders) {
            DeepLinkEntry entry = loader.parseUri(uriString);
            if (entry != null) {
                return entry;
            }
        }
        return null;
    }

    public DeepLinkResult dispatchFrom(Activity activity) {
        if (activity != null) {
            return dispatchFrom(activity, activity.getIntent());
        }
        throw new NullPointerException("activity == null");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:77:?, code lost:
        return createResultAndNotify(r24, false, r16, "Deep link to non-existent method: " + r6.getMethod());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
        return createResultAndNotify(r24, false, r16, "Could not deep link to method: " + r6.getMethod());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
        return createResultAndNotify(r24, false, r16, "Could not deep link to method: " + r6.getMethod());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x02bd A[ExcHandler: IllegalAccessException (e java.lang.IllegalAccessException), Splitter:B:29:0x00e2] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x02e6 A[ExcHandler: InvocationTargetException (e java.lang.reflect.InvocationTargetException), Splitter:B:29:0x00e2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.airbnb.deeplinkdispatch.DeepLinkResult dispatchFrom(android.app.Activity r24, android.content.Intent r25) {
        /*
            r23 = this;
            if (r24 != 0) goto L_0x000b
            java.lang.NullPointerException r18 = new java.lang.NullPointerException
            java.lang.String r19 = "activity == null"
            r18.<init>(r19)
            throw r18
        L_0x000b:
            if (r25 != 0) goto L_0x0016
            java.lang.NullPointerException r18 = new java.lang.NullPointerException
            java.lang.String r19 = "sourceIntent == null"
            r18.<init>(r19)
            throw r18
        L_0x0016:
            android.net.Uri r16 = r25.getData()
            if (r16 != 0) goto L_0x0030
            r18 = 0
            r19 = 0
            java.lang.String r20 = "No Uri in given activity's intent."
            r0 = r24
            r1 = r18
            r2 = r19
            r3 = r20
            com.airbnb.deeplinkdispatch.DeepLinkResult r18 = createResultAndNotify(r0, r1, r2, r3)
        L_0x002f:
            return r18
        L_0x0030:
            java.lang.String r17 = r16.toString()
            r0 = r23
            r1 = r17
            com.airbnb.deeplinkdispatch.DeepLinkEntry r6 = r0.findEntry(r1)
            if (r6 == 0) goto L_0x030f
            com.airbnb.deeplinkdispatch.DeepLinkUri r5 = com.airbnb.deeplinkdispatch.DeepLinkUri.parse(r17)
            r0 = r17
            java.util.Map r11 = r6.getParameters(r0)
            java.util.Set r18 = r5.queryParameterNames()
            java.util.Iterator r18 = r18.iterator()
        L_0x0050:
            boolean r19 = r18.hasNext()
            if (r19 == 0) goto L_0x0095
            java.lang.Object r13 = r18.next()
            java.lang.String r13 = (java.lang.String) r13
            java.util.List r19 = r5.queryParameterValues(r13)
            java.util.Iterator r19 = r19.iterator()
        L_0x0064:
            boolean r20 = r19.hasNext()
            if (r20 == 0) goto L_0x0050
            java.lang.Object r14 = r19.next()
            java.lang.String r14 = (java.lang.String) r14
            boolean r20 = r11.containsKey(r13)
            if (r20 == 0) goto L_0x0091
            java.lang.String r20 = TAG
            java.lang.StringBuilder r21 = new java.lang.StringBuilder
            r21.<init>()
            java.lang.String r22 = "Duplicate parameter name in path and query param: "
            java.lang.StringBuilder r21 = r21.append(r22)
            r0 = r21
            java.lang.StringBuilder r21 = r0.append(r13)
            java.lang.String r21 = r21.toString()
            android.util.Log.w(r20, r21)
        L_0x0091:
            r11.put(r13, r14)
            goto L_0x0064
        L_0x0095:
            java.lang.String r18 = "deep_link_uri"
            java.lang.String r19 = r16.toString()
            r0 = r18
            r1 = r19
            r11.put(r0, r1)
            android.os.Bundle r18 = r25.getExtras()
            if (r18 == 0) goto L_0x00dc
            android.os.Bundle r12 = new android.os.Bundle
            android.os.Bundle r18 = r25.getExtras()
            r0 = r18
            r12.<init>(r0)
        L_0x00b4:
            java.util.Set r18 = r11.entrySet()
            java.util.Iterator r20 = r18.iterator()
        L_0x00bc:
            boolean r18 = r20.hasNext()
            if (r18 == 0) goto L_0x00e2
            java.lang.Object r10 = r20.next()
            java.util.Map$Entry r10 = (java.util.Map.Entry) r10
            java.lang.Object r18 = r10.getKey()
            java.lang.String r18 = (java.lang.String) r18
            java.lang.Object r19 = r10.getValue()
            java.lang.String r19 = (java.lang.String) r19
            r0 = r18
            r1 = r19
            r12.putString(r0, r1)
            goto L_0x00bc
        L_0x00dc:
            android.os.Bundle r12 = new android.os.Bundle
            r12.<init>()
            goto L_0x00b4
        L_0x00e2:
            java.lang.Class r4 = r6.getActivityClass()     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r15 = 0
            com.airbnb.deeplinkdispatch.DeepLinkEntry$Type r18 = r6.getType()     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            com.airbnb.deeplinkdispatch.DeepLinkEntry$Type r19 = com.airbnb.deeplinkdispatch.DeepLinkEntry.Type.CLASS     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r0 = r18
            r1 = r19
            if (r0 != r1) goto L_0x0155
            android.content.Intent r9 = new android.content.Intent     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r0 = r24
            r9.<init>(r0, r4)     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
        L_0x00fa:
            java.lang.String r18 = r9.getAction()     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            if (r18 != 0) goto L_0x0109
            java.lang.String r18 = r25.getAction()     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r0 = r18
            r9.setAction(r0)     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
        L_0x0109:
            android.net.Uri r18 = r9.getData()     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            if (r18 != 0) goto L_0x0118
            android.net.Uri r18 = r25.getData()     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r0 = r18
            r9.setData(r0)     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
        L_0x0118:
            r9.putExtras(r12)     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            java.lang.String r18 = "is_deep_link_flag"
            r19 = 1
            r0 = r18
            r1 = r19
            r9.putExtra(r0, r1)     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            java.lang.String r18 = "android.intent.extra.REFERRER"
            r0 = r18
            r1 = r16
            r9.putExtra(r0, r1)     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            android.content.ComponentName r18 = r24.getCallingActivity()     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            if (r18 == 0) goto L_0x013e
            r18 = 33554432(0x2000000, float:9.403955E-38)
            r0 = r18
            r9.setFlags(r0)     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
        L_0x013e:
            if (r15 == 0) goto L_0x028d
            r15.startActivities()     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
        L_0x0143:
            r18 = 1
            r19 = 0
            r0 = r24
            r1 = r18
            r2 = r16
            r3 = r19
            com.airbnb.deeplinkdispatch.DeepLinkResult r18 = createResultAndNotify(r0, r1, r2, r3)     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            goto L_0x002f
        L_0x0155:
            java.lang.String r18 = r6.getMethod()     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r19 = 1
            r0 = r19
            java.lang.Class[] r0 = new java.lang.Class[r0]     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r19 = r0
            r20 = 0
            java.lang.Class<android.content.Context> r21 = android.content.Context.class
            r19[r20] = r21     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r0 = r18
            r1 = r19
            java.lang.reflect.Method r8 = r4.getMethod(r0, r1)     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            java.lang.Class r18 = r8.getReturnType()     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            java.lang.Class<android.support.v4.app.TaskStackBuilder> r19 = android.support.p000v4.app.TaskStackBuilder.class
            boolean r18 = r18.equals(r19)     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            if (r18 == 0) goto L_0x01d5
            r18 = 1
            r0 = r18
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r18 = r0
            r19 = 0
            r18[r19] = r24     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r0 = r18
            java.lang.Object r18 = r8.invoke(r4, r0)     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r0 = r18
            android.support.v4.app.TaskStackBuilder r0 = (android.support.p000v4.app.TaskStackBuilder) r0     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r15 = r0
            int r18 = r15.getIntentCount()     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            if (r18 != 0) goto L_0x01c7
            r18 = 0
            java.lang.StringBuilder r19 = new java.lang.StringBuilder     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r19.<init>()     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            java.lang.String r20 = "Could not deep link to method: "
            java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            java.lang.String r20 = r6.getMethod()     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            java.lang.String r20 = " intents length == 0"
            java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            java.lang.String r19 = r19.toString()     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r0 = r24
            r1 = r18
            r2 = r16
            r3 = r19
            com.airbnb.deeplinkdispatch.DeepLinkResult r18 = createResultAndNotify(r0, r1, r2, r3)     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            goto L_0x002f
        L_0x01c7:
            int r18 = r15.getIntentCount()     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            int r18 = r18 + -1
            r0 = r18
            android.content.Intent r9 = r15.editIntentAt(r0)     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            goto L_0x00fa
        L_0x01d5:
            r18 = 1
            r0 = r18
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r18 = r0
            r19 = 0
            r18[r19] = r24     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r0 = r18
            java.lang.Object r9 = r8.invoke(r4, r0)     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            android.content.Intent r9 = (android.content.Intent) r9     // Catch:{ NoSuchMethodException -> 0x01eb, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            goto L_0x00fa
        L_0x01eb:
            r7 = move-exception
            java.lang.String r18 = r6.getMethod()     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r19 = 2
            r0 = r19
            java.lang.Class[] r0 = new java.lang.Class[r0]     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r19 = r0
            r20 = 0
            java.lang.Class<android.content.Context> r21 = android.content.Context.class
            r19[r20] = r21     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r20 = 1
            java.lang.Class<android.os.Bundle> r21 = android.os.Bundle.class
            r19[r20] = r21     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r0 = r18
            r1 = r19
            java.lang.reflect.Method r8 = r4.getMethod(r0, r1)     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            java.lang.Class r18 = r8.getReturnType()     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            java.lang.Class<android.support.v4.app.TaskStackBuilder> r19 = android.support.p000v4.app.TaskStackBuilder.class
            boolean r18 = r18.equals(r19)     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            if (r18 == 0) goto L_0x0273
            r18 = 2
            r0 = r18
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r18 = r0
            r19 = 0
            r18[r19] = r24     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r19 = 1
            r18[r19] = r12     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r0 = r18
            java.lang.Object r15 = r8.invoke(r4, r0)     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            android.support.v4.app.TaskStackBuilder r15 = (android.support.p000v4.app.TaskStackBuilder) r15     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            int r18 = r15.getIntentCount()     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            if (r18 != 0) goto L_0x0265
            r18 = 0
            java.lang.StringBuilder r19 = new java.lang.StringBuilder     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r19.<init>()     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            java.lang.String r20 = "Could not deep link to method: "
            java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            java.lang.String r20 = r6.getMethod()     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            java.lang.String r20 = " intents length == 0"
            java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            java.lang.String r19 = r19.toString()     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r0 = r24
            r1 = r18
            r2 = r16
            r3 = r19
            com.airbnb.deeplinkdispatch.DeepLinkResult r18 = createResultAndNotify(r0, r1, r2, r3)     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            goto L_0x002f
        L_0x0265:
            int r18 = r15.getIntentCount()     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            int r18 = r18 + -1
            r0 = r18
            android.content.Intent r9 = r15.editIntentAt(r0)     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            goto L_0x00fa
        L_0x0273:
            r18 = 2
            r0 = r18
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r18 = r0
            r19 = 0
            r18[r19] = r24     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r19 = 1
            r18[r19] = r12     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            r0 = r18
            java.lang.Object r9 = r8.invoke(r4, r0)     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            android.content.Intent r9 = (android.content.Intent) r9     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            goto L_0x00fa
        L_0x028d:
            r0 = r24
            r0.startActivity(r9)     // Catch:{ NoSuchMethodException -> 0x0294, IllegalAccessException -> 0x02bd, InvocationTargetException -> 0x02e6 }
            goto L_0x0143
        L_0x0294:
            r7 = move-exception
            r18 = 0
            java.lang.StringBuilder r19 = new java.lang.StringBuilder
            r19.<init>()
            java.lang.String r20 = "Deep link to non-existent method: "
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r20 = r6.getMethod()
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r19 = r19.toString()
            r0 = r24
            r1 = r18
            r2 = r16
            r3 = r19
            com.airbnb.deeplinkdispatch.DeepLinkResult r18 = createResultAndNotify(r0, r1, r2, r3)
            goto L_0x002f
        L_0x02bd:
            r7 = move-exception
            r18 = 0
            java.lang.StringBuilder r19 = new java.lang.StringBuilder
            r19.<init>()
            java.lang.String r20 = "Could not deep link to method: "
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r20 = r6.getMethod()
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r19 = r19.toString()
            r0 = r24
            r1 = r18
            r2 = r16
            r3 = r19
            com.airbnb.deeplinkdispatch.DeepLinkResult r18 = createResultAndNotify(r0, r1, r2, r3)
            goto L_0x002f
        L_0x02e6:
            r7 = move-exception
            r18 = 0
            java.lang.StringBuilder r19 = new java.lang.StringBuilder
            r19.<init>()
            java.lang.String r20 = "Could not deep link to method: "
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r20 = r6.getMethod()
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r19 = r19.toString()
            r0 = r24
            r1 = r18
            r2 = r16
            r3 = r19
            com.airbnb.deeplinkdispatch.DeepLinkResult r18 = createResultAndNotify(r0, r1, r2, r3)
            goto L_0x002f
        L_0x030f:
            r18 = 0
            java.lang.StringBuilder r19 = new java.lang.StringBuilder
            r19.<init>()
            java.lang.String r20 = "No registered entity to handle deep link: "
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r20 = r16.toString()
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r19 = r19.toString()
            r0 = r24
            r1 = r18
            r2 = r16
            r3 = r19
            com.airbnb.deeplinkdispatch.DeepLinkResult r18 = createResultAndNotify(r0, r1, r2, r3)
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.lib.activities.DeepLinkDelegate.dispatchFrom(android.app.Activity, android.content.Intent):com.airbnb.deeplinkdispatch.DeepLinkResult");
    }

    private static DeepLinkResult createResultAndNotify(Context context, boolean successful, Uri uri, String error) {
        notifyListener(context, !successful, uri, error);
        return new DeepLinkResult(successful, uri != null ? uri.toString() : null, error);
    }

    private static void notifyListener(Context context, boolean isError, Uri uri, String errorMessage) {
        Intent intent = new Intent();
        intent.setAction("com.airbnb.deeplinkdispatch.DEEPLINK_ACTION");
        intent.putExtra("com.airbnb.deeplinkdispatch.EXTRA_URI", uri != null ? uri.toString() : "");
        intent.putExtra("com.airbnb.deeplinkdispatch.EXTRA_SUCCESSFUL", !isError);
        if (isError) {
            intent.putExtra("com.airbnb.deeplinkdispatch.EXTRA_ERROR_MESSAGE", errorMessage);
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public boolean supportsUri(String uriString) {
        return findEntry(uriString) != null;
    }
}
