package com.airbnb.android.core.filters;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.models.LocationTypeaheadSuggestionItemForChina;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.utils.IOUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Lazy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LocationTypeaheadFilterForChina extends Filter {
    private static List<LocationTypeaheadSuggestionItemForChina> autoCompleteSource;
    private final Context context;
    private LocationTypeaheadFilterForChinaOnCompleteListener listener;
    Lazy<ObjectMapper> objectMapperLazy;

    public interface LocationTypeaheadFilterForChinaOnCompleteListener {
        void onChinaAutocompleteCompleted(List<LocationTypeaheadSuggestionItemForChina> list);
    }

    public LocationTypeaheadFilterForChina(Context context2, LocationTypeaheadFilterForChinaOnCompleteListener listener2) {
        this.context = context2;
        this.listener = listener2;
        ((CoreGraph) CoreApplication.instance(context2).component()).inject(this);
    }

    /* access modifiers changed from: protected */
    public FilterResults performFiltering(CharSequence constraint) {
        if (autoCompleteSource == null) {
            initializeAutoCompleteSource();
        }
        FilterResults results = new FilterResults();
        if (!TextUtils.isEmpty(constraint)) {
            List<LocationTypeaheadSuggestionItemForChina> typeaheadSuggestions = new ArrayList<>();
            int lastRankValue = 0;
            String query = constraint.toString().toLowerCase();
            Set<String> idSet = new HashSet<>();
            for (LocationTypeaheadSuggestionItemForChina item : autoCompleteSource) {
                if (!idSet.contains(item.getPlaceId()) && item.getRank() != lastRankValue && item.matchesQuery(query)) {
                    idSet.add(item.getPlaceId());
                    lastRankValue = item.getRank();
                    typeaheadSuggestions.add(item);
                }
            }
            results.values = typeaheadSuggestions;
            results.count = typeaheadSuggestions.size();
        }
        return results;
    }

    /* access modifiers changed from: protected */
    public void publishResults(CharSequence constraint, FilterResults results) {
        if (this.listener != null) {
            this.listener.onChinaAutocompleteCompleted((List) results.values);
        }
    }

    private void initializeAutoCompleteSource() {
        autoCompleteSource = new ArrayList();
        JsonParser jsonParser = null;
        try {
            jsonParser = ((ObjectMapper) this.objectMapperLazy.get()).getFactory().createParser(this.context.getAssets().open("search_suggestions_for_china.json"));
            List<LocationTypeaheadSuggestionItemForChina> items = (List) jsonParser.readValueAs(new TypeReference<List<LocationTypeaheadSuggestionItemForChina>>() {
            });
            if (ChinaUtils.isUserUsingSimplifiedChinese()) {
                for (LocationTypeaheadSuggestionItemForChina item : items) {
                    if (item.shouldShowInChineseLocale()) {
                        autoCompleteSource.add(item);
                    }
                }
            } else {
                autoCompleteSource.addAll(items);
            }
        } catch (IOException e) {
            BugsnagWrapper.notify((Throwable) e);
        } finally {
            IOUtils.closeQuietly(jsonParser);
        }
    }

    public void disconnect() {
        this.listener = null;
    }
}
