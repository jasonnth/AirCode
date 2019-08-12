package com.airbnb.android.lib.contentproviders;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.SimpleRequestListener;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.analytics.AppLaunchAnalytics;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.models.DashboardAlert;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.DashboardAlertsRequest;
import com.airbnb.android.core.requests.UpcomingReservationsRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.DashboardAlertsResponse;
import com.airbnb.android.core.responses.UpcomingReservationsResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.HostWidgetHandlerActivity;
import com.airbnb.android.lib.analytics.HHWidgetAnalytics;
import com.airbnb.android.lib.services.HHListWidgetService;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class HostHomeWidgetProvider extends AppWidgetProvider {
    private static final String EXTRA_REFRESH = "refresh";
    private static final String EXTRA_WIDGET_MESSAGES = "widget_messages";
    AirbnbApi airbnbApi;
    /* access modifiers changed from: private */
    public DashboardAlertsResponse alertsResponse;
    AppLaunchAnalytics appLaunchAnalytics;
    AirbnbAccountManager mAccountManager;
    private int mMinHeight = -1;
    /* access modifiers changed from: private */
    public UpcomingReservationsResponse reservationsResponse;

    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {
        ((AirbnbGraph) AirbnbApplication.instance(context).component()).inject(this);
        if (this.mAccountManager.isCurrentUserAuthorized()) {
            this.reservationsResponse = null;
            this.alertsResponse = null;
            List<BaseRequestV2<?>> requests = new ArrayList<>();
            requests.add(UpcomingReservationsRequest.forHostDashboard(0, new SimpleRequestListener<UpcomingReservationsResponse>() {
                public void onResponse(UpcomingReservationsResponse data) {
                    HostHomeWidgetProvider.this.reservationsResponse = data;
                }
            }));
            requests.add(DashboardAlertsRequest.forHost(context).withListener((Observer) new SimpleRequestListener<DashboardAlertsResponse>() {
                public void onResponse(DashboardAlertsResponse data) {
                    HostHomeWidgetProvider.this.alertsResponse = data;
                }
            }));
            new AirBatchRequest(requests, new NonResubscribableRequestListener<AirBatchResponse>() {
                public void onResponse(AirBatchResponse data) {
                    if (HostHomeWidgetProvider.this.reservationsResponse == null || HostHomeWidgetProvider.this.alertsResponse == null) {
                        onErrorResponse(null);
                        return;
                    }
                    RemoteViews views = new RemoteViews(context.getPackageName(), C0880R.layout.widget_hh);
                    if (!HostHomeWidgetProvider.this.reservationsResponse.reservations.isEmpty()) {
                        HostHomeWidgetProvider.this.setupUpcomingRow(context, views, appWidgetManager, 0);
                    } else {
                        views = new RemoteViews(context.getPackageName(), C0880R.layout.widget_hh);
                        HostHomeWidgetProvider.this.setupTextView(context, views, C0880R.string.hh_widget_no_upcoming, 0);
                    }
                    HostHomeWidgetProvider.this.setupHeader(context, views, HostHomeWidgetProvider.this.alertsResponse.dashboardAlerts, appWidgetIds);
                    appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, C0880R.C0882id.widget_content);
                    appWidgetManager.updateAppWidget(new ComponentName(context, HostHomeWidgetProvider.class), views);
                }

                public void onErrorResponse(AirRequestNetworkException e) {
                    RemoteViews views = new RemoteViews(context.getPackageName(), C0880R.layout.widget_hh);
                    HostHomeWidgetProvider.this.setupHeader(context, views, null, appWidgetIds);
                    HostHomeWidgetProvider.this.setupTextView(context, views, C0880R.string.hh_widget_error_loading_data, C0880R.C0881drawable.icon_widget_offline);
                    appWidgetManager.updateAppWidget(new ComponentName(context, HostHomeWidgetProvider.class), views);
                    appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, C0880R.C0882id.widget_content);
                }
            }).execute(NetworkUtil.singleFireExecutor());
            RemoteViews views = new RemoteViews(context.getPackageName(), C0880R.layout.widget_hh);
            setupHeader(context, views, null, appWidgetIds);
            setupTextView(context, views, C0880R.string.loading, 0);
            appWidgetManager.updateAppWidget(new ComponentName(context, HostHomeWidgetProvider.class), views);
        } else {
            RemoteViews views2 = new RemoteViews(context.getPackageName(), C0880R.layout.widget_hh);
            setupHeader(context, views2, null, appWidgetIds);
            setupTextView(context, views2, C0880R.string.hh_widget_not_logged_in, 0);
            appWidgetManager.updateAppWidget(new ComponentName(context, HostHomeWidgetProvider.class), views2);
        }
        this.appLaunchAnalytics.trackColdLaunchCancelled("host_home_widget_triggered");
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, C0880R.C0882id.widget_content);
    }

    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.hasExtra(EXTRA_REFRESH)) {
            HHWidgetAnalytics.trackRefreshClick();
        }
    }

    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        HHWidgetAnalytics.trackWidgetUninstall();
    }

    public void onEnabled(Context context) {
        super.onEnabled(context);
        HHWidgetAnalytics.trackWidgetInstall();
    }

    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        this.mMinHeight = newOptions.getInt("appWidgetMinHeight", -1);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, C0880R.C0882id.widget_content);
    }

    /* access modifiers changed from: private */
    public void setupTextView(Context context, RemoteViews views, int stringResId, int imgId) {
        boolean imageAbove;
        int i;
        views.setTextViewText(C0880R.C0882id.empty_state_text_view, context.getString(stringResId));
        if (this.mMinHeight <= 0 || this.mMinHeight >= 100) {
            imageAbove = false;
        } else {
            imageAbove = true;
        }
        int i2 = C0880R.C0882id.empty_state_text_view;
        if (imageAbove) {
            i = 0;
        } else {
            i = imgId;
        }
        views.setTextViewCompoundDrawablesRelative(i2, i, imageAbove ? imgId : 0, 0, 0);
        views.setOnClickPendingIntent(C0880R.C0882id.section_empty_state, PendingIntent.getActivity(context, 0, HomeActivityIntents.intentForHostHome(context), 0));
        views.setEmptyView(C0880R.C0882id.widget_content, C0880R.C0882id.empty_state_text_view);
    }

    /* access modifiers changed from: private */
    public void setupHeader(Context context, RemoteViews views, List<DashboardAlert> alerts, int[] appWidgetIds) {
        views.setTextViewText(C0880R.C0882id.txt_notifs, getAlertsString(context, alerts));
        Intent hostHomeIntent = HomeActivityIntents.intentForHostHome(context);
        hostHomeIntent.putExtra(EXTRA_WIDGET_MESSAGES, true);
        views.setOnClickPendingIntent(C0880R.C0882id.txt_notifs, PendingIntent.getActivity(context, 0, hostHomeIntent, 0));
        Intent refreshIntent = new Intent(context, HostHomeWidgetProvider.class);
        refreshIntent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        refreshIntent.putExtra("appWidgetIds", appWidgetIds);
        refreshIntent.putExtra(EXTRA_REFRESH, true);
        views.setOnClickPendingIntent(C0880R.C0882id.img_refresh, PendingIntent.getBroadcast(context, 0, refreshIntent, 134217728));
    }

    private static String getAlertsString(Context context, List<DashboardAlert> alerts) {
        if (alerts == null || alerts.size() == 0) {
            return "";
        }
        return context.getResources().getQuantityString(C0880R.plurals.x_new_alerts, alerts.size(), new Object[]{Integer.valueOf(alerts.size())});
    }

    /* access modifiers changed from: private */
    public void setupUpcomingRow(Context context, RemoteViews views, AppWidgetManager appWidgetManager, int widgetId) {
        Intent intent = new Intent(context, HHListWidgetService.class);
        intent.putExtra("appWidgetId", widgetId);
        intent.setData(Uri.parse(intent.toUri(1)));
        views.setRemoteAdapter(C0880R.C0882id.widget_content, intent);
        appWidgetManager.updateAppWidget(0, views);
        views.setPendingIntentTemplate(C0880R.C0882id.widget_content, PendingIntent.getActivities(context, 0, new Intent[]{HomeActivityIntents.intentForDefaultTab(context), HostWidgetHandlerActivity.createGenericIntent(context)}, 0));
    }

    public static void update(Context context) {
        Intent intent = new Intent(context, HostHomeWidgetProvider.class);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        intent.putExtra("appWidgetIds", AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, HostHomeWidgetProvider.class)));
        context.sendBroadcast(intent);
    }
}
