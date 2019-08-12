package com.airbnb.android.lib.services;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.requests.UpcomingReservationsRequest;
import com.airbnb.android.core.responses.UpcomingReservationsResponse;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.HostWidgetHandlerActivity;
import com.airbnb.android.lib.adapters.HHBaseAdapter;
import com.airbnb.android.lib.analytics.HHWidgetAnalytics;
import com.airbnb.android.utils.TextUtil;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.GlideCircleTransform;
import java.util.ArrayList;
import java.util.List;

public class HHListRemoteViewsFactory implements RemoteViewsFactory {
    AirbnbApi airbnbApi;
    private final GlideCircleTransform circleTransform;
    AirbnbAccountManager mAccountManager;
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    /* access modifiers changed from: private */
    public List<Reservation> mReservations;

    public HHListRemoteViewsFactory(Context context) {
        ((AirbnbGraph) AirbnbApplication.instance(context).component()).inject(this);
        this.mContext = context;
        this.mReservations = new ArrayList();
        this.circleTransform = new GlideCircleTransform(this.mContext);
    }

    public int getCount() {
        return this.mReservations.size();
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public RemoteViews getLoadingView() {
        RemoteViews views = new RemoteViews(this.mContext.getPackageName(), C0880R.layout.widget_hh_text_view);
        views.setTextViewText(C0880R.C0882id.txt_message, this.mContext.getString(C0880R.string.loading));
        return views;
    }

    public RemoteViews getViewAt(int position) {
        if (this.mReservations.size() > position) {
            return setupUpcomingRow(position);
        }
        return null;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public boolean hasStableIds() {
        return true;
    }

    public void onCreate() {
    }

    public void onDataSetChanged() {
        this.mReservations.clear();
        UpcomingReservationsRequest request = UpcomingReservationsRequest.forHostDashboard(0, new NonResubscribableRequestListener<UpcomingReservationsResponse>() {
            public void onErrorResponse(AirRequestNetworkException error) {
                synchronized (HHListRemoteViewsFactory.this.mLock) {
                    HHListRemoteViewsFactory.this.mLock.notifyAll();
                }
            }

            public void onResponse(UpcomingReservationsResponse response) {
                HHListRemoteViewsFactory.this.mReservations = new ArrayList(response.reservations);
                synchronized (HHListRemoteViewsFactory.this.mLock) {
                    HHListRemoteViewsFactory.this.mLock.notifyAll();
                }
            }
        });
        if (this.mReservations.isEmpty()) {
            synchronized (this.mLock) {
                request.execute(NetworkUtil.singleFireExecutor());
                try {
                    this.mLock.wait();
                } catch (InterruptedException e) {
                }
            }
        }
        HHWidgetAnalytics.trackWidgetUpdate(getCount());
    }

    public void onDestroy() {
    }

    private RemoteViews setupUpcomingRow(int position) {
        RemoteViews item = new RemoteViews(this.mContext.getPackageName(), C0880R.layout.widget_hh_single_item);
        Reservation reservation = (Reservation) this.mReservations.get(position);
        item.setImageViewBitmap(C0880R.C0882id.img_profile, AirImageView.getImageBlocking(this.mContext, reservation.getGuest().getPictureUrl(), this.circleTransform));
        boolean isCheckingIn = reservation.isUpcoming();
        item.setTextViewText(C0880R.C0882id.arrive_time, DateHelper.getArrivalTimeWithName(this.mContext, isCheckingIn ? reservation.getStartDate() : reservation.getEndDate(), reservation.getGuest().getFirstName(), isCheckingIn));
        item.setTextViewText(C0880R.C0882id.details, TextUtil.fromHtmlSafe(this.mContext.getString(C0880R.string.bullet_with_space_parameterized, new Object[]{HHBaseAdapter.getDateFormattedString(this.mContext, reservation.getStartDate(), reservation.getEndDate()), this.mContext.getResources().getQuantityString(C0880R.plurals.x_guests, reservation.getGuestCount(), new Object[]{Integer.valueOf(reservation.getGuestCount())})})));
        if (!this.mAccountManager.hasCurrentUser() || this.mAccountManager.getCurrentUser().getTotalListingsCount() <= 1) {
            item.setViewVisibility(C0880R.C0882id.listing_description, 8);
        } else {
            item.setViewVisibility(C0880R.C0882id.listing_description, 0);
            item.setTextViewText(C0880R.C0882id.listing_description, reservation.getListing().getName());
        }
        item.setOnClickFillInIntent(C0880R.C0882id.hh_item_row, HostWidgetHandlerActivity.createIntentForConfirmationCode(this.mContext, reservation.getConfirmationCode()));
        return item;
    }
}
