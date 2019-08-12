package com.airbnb.android.airmapview;

import com.google.maps.android.geojson.GeoJsonPolygonStyle;

public class AirMapGeoJsonLayer {
    protected final int fillColor;
    protected final String geoJson;
    protected final int strokeColor;
    protected final float strokeWidth;

    public static class Builder {
        private final String json;
        private final GeoJsonPolygonStyle style = new GeoJsonPolygonStyle();

        public Builder(String json2) {
            this.json = json2;
        }

        public Builder fillColor(int color) {
            this.style.setFillColor(color);
            return this;
        }

        public Builder strokeColor(int color) {
            this.style.setStrokeColor(color);
            return this;
        }

        public Builder strokeWidth(float width) {
            this.style.setStrokeWidth(width);
            return this;
        }

        public AirMapGeoJsonLayer build() {
            return new AirMapGeoJsonLayer(this.json, this.style.getStrokeWidth(), this.style.getStrokeColor(), this.style.getFillColor());
        }
    }

    private AirMapGeoJsonLayer(String geoJson2, float strokeWidth2, int strokeColor2, int fillColor2) {
        this.geoJson = geoJson2;
        this.strokeWidth = strokeWidth2;
        this.strokeColor = strokeColor2;
        this.fillColor = fillColor2;
    }
}
