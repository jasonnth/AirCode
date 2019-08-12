package com.google.maps.android.geojson;

import android.util.Log;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class GeoJsonParser {
    private LatLngBounds mBoundingBox = null;
    private final ArrayList<GeoJsonFeature> mGeoJsonFeatures = new ArrayList<>();
    private final JSONObject mGeoJsonFile;

    GeoJsonParser(JSONObject geoJsonFile) {
        this.mGeoJsonFile = geoJsonFile;
        parseGeoJson();
    }

    private static boolean isGeometry(String type) {
        return type.matches("Point|MultiPoint|LineString|MultiLineString|Polygon|MultiPolygon|GeometryCollection");
    }

    private static GeoJsonFeature parseFeature(JSONObject geoJsonFeature) {
        String id = null;
        LatLngBounds boundingBox = null;
        GeoJsonGeometry geometry = null;
        HashMap<String, String> properties = new HashMap<>();
        try {
            if (geoJsonFeature.has("id")) {
                id = geoJsonFeature.getString("id");
            }
            if (geoJsonFeature.has("bbox")) {
                boundingBox = parseBoundingBox(geoJsonFeature.getJSONArray("bbox"));
            }
            if (geoJsonFeature.has("geometry") && !geoJsonFeature.isNull("geometry")) {
                geometry = parseGeometry(geoJsonFeature.getJSONObject("geometry"));
            }
            if (geoJsonFeature.has("properties") && !geoJsonFeature.isNull("properties")) {
                properties = parseProperties(geoJsonFeature.getJSONObject("properties"));
            }
            return new GeoJsonFeature(geometry, id, properties, boundingBox);
        } catch (JSONException e) {
            Log.w("GeoJsonParser", "Feature could not be successfully parsed " + geoJsonFeature.toString());
            return null;
        }
    }

    private static LatLngBounds parseBoundingBox(JSONArray coordinates) throws JSONException {
        return new LatLngBounds(new LatLng(coordinates.getDouble(1), coordinates.getDouble(0)), new LatLng(coordinates.getDouble(3), coordinates.getDouble(2)));
    }

    private static GeoJsonGeometry parseGeometry(JSONObject geoJsonGeometry) {
        JSONArray geometryArray;
        try {
            String geometryType = geoJsonGeometry.getString("type");
            if (geometryType.equals("GeometryCollection")) {
                geometryArray = geoJsonGeometry.getJSONArray("geometries");
            } else if (!isGeometry(geometryType)) {
                return null;
            } else {
                geometryArray = geoJsonGeometry.getJSONArray("coordinates");
            }
            return createGeometry(geometryType, geometryArray);
        } catch (JSONException e) {
            return null;
        }
    }

    private static GeoJsonFeature parseGeometryToFeature(JSONObject geoJsonGeometry) {
        GeoJsonGeometry geometry = parseGeometry(geoJsonGeometry);
        if (geometry != null) {
            return new GeoJsonFeature(geometry, null, new HashMap(), null);
        }
        Log.w("GeoJsonParser", "Geometry could not be parsed");
        return null;
    }

    private static HashMap<String, String> parseProperties(JSONObject properties) throws JSONException {
        HashMap<String, String> propertiesMap = new HashMap<>();
        Iterator propertyKeys = properties.keys();
        while (propertyKeys.hasNext()) {
            String key = (String) propertyKeys.next();
            propertiesMap.put(key, properties.getString(key));
        }
        return propertiesMap;
    }

    private static GeoJsonGeometry createGeometry(String geometryType, JSONArray geometryArray) throws JSONException {
        if (geometryType.equals("Point")) {
            return createPoint(geometryArray);
        }
        if (geometryType.equals("MultiPoint")) {
            return createMultiPoint(geometryArray);
        }
        if (geometryType.equals("LineString")) {
            return createLineString(geometryArray);
        }
        if (geometryType.equals("MultiLineString")) {
            return createMultiLineString(geometryArray);
        }
        if (geometryType.equals("Polygon")) {
            return createPolygon(geometryArray);
        }
        if (geometryType.equals("MultiPolygon")) {
            return createMultiPolygon(geometryArray);
        }
        if (geometryType.equals("GeometryCollection")) {
            return createGeometryCollection(geometryArray);
        }
        return null;
    }

    private static GeoJsonPoint createPoint(JSONArray coordinates) throws JSONException {
        return new GeoJsonPoint(parseCoordinate(coordinates));
    }

    private static GeoJsonMultiPoint createMultiPoint(JSONArray coordinates) throws JSONException {
        ArrayList<GeoJsonPoint> geoJsonPoints = new ArrayList<>();
        for (int i = 0; i < coordinates.length(); i++) {
            geoJsonPoints.add(createPoint(coordinates.getJSONArray(i)));
        }
        return new GeoJsonMultiPoint(geoJsonPoints);
    }

    private static GeoJsonLineString createLineString(JSONArray coordinates) throws JSONException {
        return new GeoJsonLineString(parseCoordinatesArray(coordinates));
    }

    private static GeoJsonMultiLineString createMultiLineString(JSONArray coordinates) throws JSONException {
        ArrayList<GeoJsonLineString> geoJsonLineStrings = new ArrayList<>();
        for (int i = 0; i < coordinates.length(); i++) {
            geoJsonLineStrings.add(createLineString(coordinates.getJSONArray(i)));
        }
        return new GeoJsonMultiLineString(geoJsonLineStrings);
    }

    private static GeoJsonPolygon createPolygon(JSONArray coordinates) throws JSONException {
        return new GeoJsonPolygon(parseCoordinatesArrays(coordinates));
    }

    private static GeoJsonMultiPolygon createMultiPolygon(JSONArray coordinates) throws JSONException {
        ArrayList<GeoJsonPolygon> geoJsonPolygons = new ArrayList<>();
        for (int i = 0; i < coordinates.length(); i++) {
            geoJsonPolygons.add(createPolygon(coordinates.getJSONArray(i)));
        }
        return new GeoJsonMultiPolygon(geoJsonPolygons);
    }

    private static GeoJsonGeometryCollection createGeometryCollection(JSONArray geometries) throws JSONException {
        ArrayList<GeoJsonGeometry> geometryCollectionElements = new ArrayList<>();
        for (int i = 0; i < geometries.length(); i++) {
            GeoJsonGeometry geometry = parseGeometry(geometries.getJSONObject(i));
            if (geometry != null) {
                geometryCollectionElements.add(geometry);
            }
        }
        return new GeoJsonGeometryCollection(geometryCollectionElements);
    }

    private static LatLng parseCoordinate(JSONArray coordinates) throws JSONException {
        return new LatLng(coordinates.getDouble(1), coordinates.getDouble(0));
    }

    private static ArrayList<LatLng> parseCoordinatesArray(JSONArray coordinates) throws JSONException {
        ArrayList<LatLng> coordinatesArray = new ArrayList<>();
        for (int i = 0; i < coordinates.length(); i++) {
            coordinatesArray.add(parseCoordinate(coordinates.getJSONArray(i)));
        }
        return coordinatesArray;
    }

    private static ArrayList<ArrayList<LatLng>> parseCoordinatesArrays(JSONArray coordinates) throws JSONException {
        ArrayList<ArrayList<LatLng>> coordinatesArray = new ArrayList<>();
        for (int i = 0; i < coordinates.length(); i++) {
            coordinatesArray.add(parseCoordinatesArray(coordinates.getJSONArray(i)));
        }
        return coordinatesArray;
    }

    private void parseGeoJson() {
        try {
            String type = this.mGeoJsonFile.getString("type");
            if (type.equals("Feature")) {
                GeoJsonFeature feature = parseFeature(this.mGeoJsonFile);
                if (feature != null) {
                    this.mGeoJsonFeatures.add(feature);
                }
            } else if (type.equals("FeatureCollection")) {
                this.mGeoJsonFeatures.addAll(parseFeatureCollection(this.mGeoJsonFile));
            } else if (isGeometry(type)) {
                GeoJsonFeature feature2 = parseGeometryToFeature(this.mGeoJsonFile);
                if (feature2 != null) {
                    this.mGeoJsonFeatures.add(feature2);
                }
            } else {
                Log.w("GeoJsonParser", "GeoJSON file could not be parsed.");
            }
        } catch (JSONException e) {
            Log.w("GeoJsonParser", "GeoJSON file could not be parsed.");
        }
    }

    private ArrayList<GeoJsonFeature> parseFeatureCollection(JSONObject geoJsonFeatureCollection) {
        ArrayList<GeoJsonFeature> features = new ArrayList<>();
        try {
            JSONArray geoJsonFeatures = geoJsonFeatureCollection.getJSONArray("features");
            if (geoJsonFeatureCollection.has("bbox")) {
                this.mBoundingBox = parseBoundingBox(geoJsonFeatureCollection.getJSONArray("bbox"));
            }
            for (int i = 0; i < geoJsonFeatures.length(); i++) {
                try {
                    JSONObject feature = geoJsonFeatures.getJSONObject(i);
                    if (feature.getString("type").equals("Feature")) {
                        GeoJsonFeature parsedFeature = parseFeature(feature);
                        if (parsedFeature != null) {
                            features.add(parsedFeature);
                        } else {
                            Log.w("GeoJsonParser", "Index of Feature in Feature Collection that could not be created: " + i);
                        }
                    }
                } catch (JSONException e) {
                    Log.w("GeoJsonParser", "Index of Feature in Feature Collection that could not be created: " + i);
                }
            }
        } catch (JSONException e2) {
            Log.w("GeoJsonParser", "Feature Collection could not be created.");
        }
        return features;
    }

    /* access modifiers changed from: 0000 */
    public ArrayList<GeoJsonFeature> getFeatures() {
        return this.mGeoJsonFeatures;
    }

    /* access modifiers changed from: 0000 */
    public LatLngBounds getBoundingBox() {
        return this.mBoundingBox;
    }
}
