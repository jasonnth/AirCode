package com.airbnb.android.react.maps;

import android.content.Context;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.UrlTileProvider;
import java.net.MalformedURLException;
import java.net.URL;

public class AirMapUrlTile extends AirMapFeature {
    private TileOverlay tileOverlay;
    private TileOverlayOptions tileOverlayOptions;
    private AIRMapUrlTileProvider tileProvider;
    private String urlTemplate;
    private float zIndex;

    class AIRMapUrlTileProvider extends UrlTileProvider {
        private String urlTemplate;

        public AIRMapUrlTileProvider(int width, int height, String urlTemplate2) {
            super(width, height);
            this.urlTemplate = urlTemplate2;
        }

        public synchronized URL getTileUrl(int x, int y, int zoom) {
            try {
            } catch (MalformedURLException e) {
                throw new AssertionError(e);
            }
            return new URL(this.urlTemplate.replace("{x}", Integer.toString(x)).replace("{y}", Integer.toString(y)).replace("{z}", Integer.toString(zoom)));
        }

        public void setUrlTemplate(String urlTemplate2) {
            this.urlTemplate = urlTemplate2;
        }
    }

    public AirMapUrlTile(Context context) {
        super(context);
    }

    public void setUrlTemplate(String urlTemplate2) {
        this.urlTemplate = urlTemplate2;
        if (this.tileProvider != null) {
            this.tileProvider.setUrlTemplate(urlTemplate2);
        }
        if (this.tileOverlay != null) {
            this.tileOverlay.clearTileCache();
        }
    }

    public void setZIndex(float zIndex2) {
        this.zIndex = zIndex2;
        if (this.tileOverlay != null) {
            this.tileOverlay.setZIndex(zIndex2);
        }
    }

    public TileOverlayOptions getTileOverlayOptions() {
        if (this.tileOverlayOptions == null) {
            this.tileOverlayOptions = createTileOverlayOptions();
        }
        return this.tileOverlayOptions;
    }

    private TileOverlayOptions createTileOverlayOptions() {
        TileOverlayOptions options = new TileOverlayOptions();
        options.zIndex(this.zIndex);
        this.tileProvider = new AIRMapUrlTileProvider(256, 256, this.urlTemplate);
        options.tileProvider(this.tileProvider);
        return options;
    }

    public Object getFeature() {
        return this.tileOverlay;
    }

    public void addToMap(GoogleMap map) {
        this.tileOverlay = map.addTileOverlay(getTileOverlayOptions());
    }

    public void removeFromMap(GoogleMap map) {
        this.tileOverlay.remove();
    }
}
