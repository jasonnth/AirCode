package com.airbnb.android.lib.contentproviders;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.location.LocationManager;
import android.net.Uri;
import android.text.TextUtils;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.requests.PlacesSearchRequest;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.superhero.SuperHeroMessageModel;
import com.airbnb.android.utils.Strap;
import com.facebook.appevents.AppEventsConstants;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.data.DbHelper;

@Deprecated
public class PlacesSearchSuggestionProvider extends ContentProvider {
    public static final String ADDRESS = "address";
    private static final int ADDRESS_SUGGEST = 2;
    public static final String AUTHORITY = (BuildHelper.applicationId() + ".contentproviders.PlacesSearchSuggestionProvider");
    public static final String CITIES = "cities";
    private static final int CITIES_SUGGEST = 1;
    public static final String DISPLAY_CITY = "city";
    public static final String DISPLAY_REGION = "region";
    private static final String JSON_ARRAY_PREDICTIONS = "predictions";
    private static final String JSON_ARRAY_TERMS = "terms";
    private static final String JSON_STRING_DESCRIPTION = "description";
    private static final String JSON_STRING_VALUE = "value";
    private static final int MIN_AUTOCOMPLETE_CHARS = 3;
    private static final long SEARCH_BIAS_RADIUS = 200000;
    public static final String SEARCH_NO_LOCATION = "search_no_location";
    private static final int SEARCH_NO_LOCATION_SUGGEST = 3;
    private static final int SEARCH_SUGGEST = 0;
    public static final String[] SEARCH_SUGGEST_COLUMNS_ADDRESS = {DbHelper.TABLE_ID, "suggest_text_1", "suggest_intent_data", "suggest_text_2"};
    public static final String[] SEARCH_SUGGEST_COLUMNS_CITIES = {DbHelper.TABLE_ID, "suggest_text_1", "suggest_intent_data", "suggest_text_2", "city", DISPLAY_REGION};
    public static final String[] SEARCH_SUGGEST_MY_LOCATION_COLUMNS = {DbHelper.TABLE_ID, "suggest_icon_1", "suggest_text_1", "suggest_intent_data"};
    private static final UriMatcher sUriMatcher = new UriMatcher(-1);
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private String mQuery;
    /* access modifiers changed from: private */
    public String mRequestResult;

    static {
        sUriMatcher.addURI(AUTHORITY, "search_suggest_query", 0);
        sUriMatcher.addURI(AUTHORITY, "search_suggest_query/*", 0);
        sUriMatcher.addURI(AUTHORITY, SEARCH_NO_LOCATION, 3);
        sUriMatcher.addURI(AUTHORITY, "search_no_location/*", 3);
        sUriMatcher.addURI(AUTHORITY, CITIES, 1);
        sUriMatcher.addURI(AUTHORITY, "cities/*", 1);
        sUriMatcher.addURI(AUTHORITY, "address", 2);
        sUriMatcher.addURI(AUTHORITY, "address/*", 2);
    }

    public boolean onCreate() {
        return true;
    }

    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case 0:
            case 1:
            case 2:
            case 3:
                return "vnd.android.cursor.dir/vnd.android.search.suggest";
            default:
                throw new IllegalArgumentException(uri.getPath());
        }
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        MatrixCursor cursor;
        int uriMatch = sUriMatcher.match(uri);
        if (uriMatch != 0 && uriMatch != 1 && uriMatch != 2 && uriMatch != 3) {
            return null;
        }
        String query = uri.getLastPathSegment();
        if (uriMatch != 0 || (query.length() >= 3 && !"search_suggest_query".equals(query))) {
            String[] queries = query.split("\u0000");
            this.mRequestResult = null;
            if (queries[0].length() >= 3 && (this.mQuery == null || queries[0].length() > this.mQuery.length())) {
                Strap strap = new Strap().mo11639kv("sensor", InternalLogger.EVENT_PARAM_EXTRAS_FALSE).mo11639kv("key", getContext().getString(C0880R.string.google_api_key)).mo11639kv("language", Locale.getDefault().getLanguage()).mo11639kv("input", queries[0]);
                if (queries.length > 1) {
                    strap = strap.mo11639kv(SuperHeroMessageModel.RADIUS, String.valueOf(SEARCH_BIAS_RADIUS)).mo11639kv("location", queries[1]);
                }
                if (uriMatch == 1) {
                    strap = strap.mo11639kv("types", "(cities)");
                } else if (uriMatch == 0 || uriMatch == 3) {
                    strap = strap.mo11639kv("types", "geocode");
                }
                PlacesSearchRequest placesSearchRequest = new PlacesSearchRequest(getContext(), strap);
                C08871 r0 = new NonResubscribableRequestListener<JsonNode>() {
                    public void onResponse(JsonNode response) {
                        PlacesSearchSuggestionProvider.this.mRequestResult = response.toString();
                        synchronized (PlacesSearchSuggestionProvider.this.mLock) {
                            PlacesSearchSuggestionProvider.this.mLock.notifyAll();
                        }
                    }

                    public void onErrorResponse(AirRequestNetworkException error) {
                        PlacesSearchSuggestionProvider.this.mRequestResult = null;
                        synchronized (PlacesSearchSuggestionProvider.this.mLock) {
                            PlacesSearchSuggestionProvider.this.mLock.notifyAll();
                        }
                    }
                };
                PlacesSearchRequest request = (PlacesSearchRequest) placesSearchRequest.withListener(r0);
                synchronized (this.mLock) {
                    request.execute(NetworkUtil.singleFireExecutor());
                    try {
                        this.mLock.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
            this.mQuery = queries[0];
            if (TextUtils.isEmpty(this.mRequestResult)) {
                return null;
            }
            try {
                JSONArray predictions = new JSONObject(this.mRequestResult).getJSONArray(JSON_ARRAY_PREDICTIONS);
                int numResults = predictions.length();
                if (uriMatch == 1) {
                    cursor = new MatrixCursor(SEARCH_SUGGEST_COLUMNS_CITIES, numResults);
                } else if (uriMatch == 2) {
                    cursor = new MatrixCursor(SEARCH_SUGGEST_COLUMNS_ADDRESS, numResults);
                } else {
                    cursor = new MatrixCursor(SEARCH_SUGGEST_MY_LOCATION_COLUMNS, numResults);
                }
                for (int i = 0; i < numResults; i++) {
                    String id = Integer.toString(i);
                    JSONObject item = predictions.getJSONObject(i);
                    String prediction = item.getString("description");
                    if (uriMatch == 2) {
                        JSONArray terms = item.getJSONArray(JSON_ARRAY_TERMS);
                        int numTerms = terms.length();
                        if (numTerms > 0) {
                            String[] components = new String[(numTerms - 1)];
                            String address = terms.getJSONObject(0).getString("value");
                            for (int j = 1; j < numTerms; j++) {
                                components[j - 1] = terms.getJSONObject(j).getString("value");
                            }
                            cursor.addRow(new Object[]{id, address, prediction, TextUtils.join(", ", components)});
                        }
                    } else if (uriMatch == 1) {
                        JSONArray terms2 = item.getJSONArray(JSON_ARRAY_TERMS);
                        int numTerms2 = terms2.length();
                        if (numTerms2 > 0) {
                            String[] components2 = new String[numTerms2];
                            String[] regionComponents = new String[(numTerms2 - 1)];
                            for (int j2 = 0; j2 < numTerms2; j2++) {
                                components2[j2] = terms2.getJSONObject(j2).getString("value");
                                if (j2 >= 1) {
                                    regionComponents[j2 - 1] = components2[j2];
                                }
                            }
                            cursor.addRow(new Object[]{id, prediction, prediction, TextUtils.join(", ", components2), components2[0], TextUtils.join(", ", regionComponents)});
                        }
                    } else {
                        cursor.addRow(new Object[]{id, Integer.valueOf(0), prediction, prediction});
                    }
                }
                return cursor;
            } catch (JSONException e2) {
                return null;
            }
        } else {
            this.mQuery = null;
            if (!getContext().getPackageManager().hasSystemFeature("android.hardware.location")) {
                return null;
            }
            if (((LocationManager) getContext().getSystemService("location")).getProviders(true).size() == 0) {
                return null;
            }
            String myLocation = getContext().getResources().getString(C0880R.string.current_location);
            MatrixCursor cursor2 = new MatrixCursor(SEARCH_SUGGEST_MY_LOCATION_COLUMNS, 1);
            cursor2.addRow(new String[]{AppEventsConstants.EVENT_PARAM_VALUE_NO, Integer.toString(C0880R.C0881drawable.icon_location), myLocation, ""});
            return cursor2;
        }
    }

    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException();
    }

    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException();
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException();
    }
}
