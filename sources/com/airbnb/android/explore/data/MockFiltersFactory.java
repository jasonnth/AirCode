package com.airbnb.android.explore.data;

import android.content.res.Resources;
import com.airbnb.android.core.enums.FilterSuggestionType;
import com.airbnb.android.core.models.C6120RoomType;
import com.airbnb.android.core.models.CategorizedFilters;
import com.airbnb.android.core.models.FilterSuggestionFilters;
import com.airbnb.android.core.models.FilterSuggestionItem;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MockFiltersFactory {
    private final Resources res;

    public MockFiltersFactory(Resources res2) {
        this.res = res2;
    }

    public List<CategorizedFilters> getTestItems() {
        List<CategorizedFilters> testItems = new ArrayList<>();
        testItems.add(createRoomTypeFilters());
        testItems.add(createCategorizedFilters("Bedrooms", FilterSuggestionType.Bedrooms.type, createBedroomFilter(1), createBedroomFilter(2), createBedroomFilter(3)));
        testItems.add(createCategorizedFilters("Beds", FilterSuggestionType.Beds.type, createBedFilter(1), createBedFilter(2)));
        return testItems;
    }

    public CategorizedFilters createRoomTypeFilters() {
        return createCategorizedFilters("", FilterSuggestionType.RoomTypes.type, createRoomTypeFilter(C6120RoomType.EntireHome), createRoomTypeFilter(C6120RoomType.SharedRoom), createRoomTypeFilter(C6120RoomType.PrivateRoom));
    }

    public List<CategorizedFilters> getTestItemsCollapsed() {
        List<CategorizedFilters> testItems = new ArrayList<>();
        testItems.add(createCategorizedFilters("", FilterSuggestionType.RoomTypes.type, createRoomTypeFilter(C6120RoomType.EntireHome)));
        testItems.add(createCategorizedFilters("Bedrooms", FilterSuggestionType.Bedrooms.type, createBedroomFilter(1), createBedroomFilter(2), createBedroomFilter(3)));
        testItems.add(createCategorizedFilters("Beds", FilterSuggestionType.Beds.type, createBedFilter(1), createBedFilter(2)));
        return testItems;
    }

    private CategorizedFilters createCategorizedFilters(String title, String type, FilterSuggestionItem... items) {
        CategorizedFilters categorizedFilters = new CategorizedFilters();
        categorizedFilters.setTitle(title);
        ArrayList<FilterSuggestionItem> filters = new ArrayList<>();
        Collections.addAll(filters, items);
        categorizedFilters.setItems(filters);
        return categorizedFilters;
    }

    private FilterSuggestionItem createTestFilter(String displayText) {
        FilterSuggestionItem item = new FilterSuggestionItem();
        item.setDisplayText(displayText);
        item.setFilters(new FilterSuggestionFilters());
        return item;
    }

    private FilterSuggestionItem createRoomTypeFilter(C6120RoomType roomType) {
        FilterSuggestionItem item = createTestFilter(this.res.getString(roomType.titleRes));
        FilterSuggestionFilters filter = new FilterSuggestionFilters();
        filter.setRoomTypes(Lists.newArrayList((E[]) new String[]{roomType.key}));
        item.setFilters(filter);
        return item;
    }

    private FilterSuggestionItem createBedroomFilter(int numBedrooms) {
        FilterSuggestionItem item = createTestFilter(String.valueOf(numBedrooms));
        FilterSuggestionFilters filter = new FilterSuggestionFilters();
        filter.setNumBedrooms(numBedrooms);
        item.setFilters(filter);
        return item;
    }

    private FilterSuggestionItem createBedFilter(int numBeds) {
        FilterSuggestionItem item = createTestFilter(String.valueOf(numBeds));
        FilterSuggestionFilters filter = new FilterSuggestionFilters();
        filter.setNumBeds(numBeds);
        item.setFilters(filter);
        return item;
    }
}
