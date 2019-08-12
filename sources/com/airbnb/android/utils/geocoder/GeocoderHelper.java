package com.airbnb.android.utils.geocoder;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.maps.model.LatLng;
import java.io.IOException;
import java.util.List;

public class GeocoderHelper {
    /* access modifiers changed from: private */
    public static final String TAG = GeocoderHelper.class.getSimpleName();

    public interface FallbackRequestListener {
        void onFail();

        void onSuccess(Address address);
    }

    private static class ReverseGeocoderAsyncTask extends AsyncTask<LatLng, Void, Address> {
        private final Context context;
        private final FallbackRequestListener listener;

        ReverseGeocoderAsyncTask(Context context2, FallbackRequestListener listener2) {
            this.context = context2;
            this.listener = listener2;
        }

        /* access modifiers changed from: protected */
        public Address doInBackground(LatLng... latlng) {
            LatLng location = latlng[0];
            if (this.context != null) {
                List<Address> addresses = null;
                try {
                    addresses = new Geocoder(this.context).getFromLocation(location.latitude, location.longitude, 1);
                } catch (IOException e) {
                    Log.d(GeocoderHelper.TAG, e.getMessage());
                }
                if (addresses != null && addresses.size() > 0) {
                    return (Address) addresses.get(0);
                }
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Address address) {
            if (address != null) {
                this.listener.onSuccess(address);
            } else {
                this.listener.onFail();
            }
        }
    }

    public static Address fallbackGetFromLocationName(Context context, String query) {
        List<Address> address = null;
        try {
            address = new Geocoder(context).getFromLocationName(query, 1);
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }
        if (address == null || address.size() <= 0) {
            return null;
        }
        return (Address) address.get(0);
    }

    public static ReverseGeocoderAsyncTask fallbackGetFromLocation(Context context, LatLng cameraPosition, FallbackRequestListener listener) {
        ReverseGeocoderAsyncTask task = new ReverseGeocoderAsyncTask(context, listener);
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new LatLng[]{cameraPosition});
        return task;
    }
}
