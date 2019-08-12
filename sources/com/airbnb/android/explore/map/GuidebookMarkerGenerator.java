package com.airbnb.android.explore.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.p000v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.airbnb.android.core.beta.models.guidebook.GuidebookPlace;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.utils.ViewUtils;
import java.lang.ref.WeakReference;

public class GuidebookMarkerGenerator {
    private static final ArrayMap<String, WeakReference<Bitmap>> markerCache = new ArrayMap<>();
    private final ImageView markerIcon = ((ImageView) this.markerView.findViewById(C0857R.C0859id.place_icon));
    private final View markerView;

    public GuidebookMarkerGenerator(Context context) {
        this.markerView = LayoutInflater.from(context).inflate(C0857R.layout.guidebook_place_map_marker, null);
    }

    public Bitmap getMarker(GuidebookPlace place, boolean isViewed, boolean isHighlighted) {
        String cacheKey = String.valueOf(place.getPrimaryPlace().getId() + "_" + isHighlighted);
        WeakReference<Bitmap> cachedReference = (WeakReference) markerCache.get(cacheKey);
        if (cachedReference != null && cachedReference.get() != null) {
            return (Bitmap) cachedReference.get();
        }
        Bitmap marker = ViewUtils.getBitmapFromUnmeasuredView(this.markerView);
        markerCache.put(cacheKey, new WeakReference(marker));
        return marker;
    }
}
