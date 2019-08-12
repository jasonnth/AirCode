package com.airbnb.android.core.requests;

import android.util.SparseArray;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.CalendarDay;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.List;

public class CalendarUpdateRequestUtil {
    public static SparseArray<List<AirDate>> groupSimilarSuggestedPricedDates(List<CalendarDay> days) {
        SparseArray<List<AirDate>> similarlyPricedDays = new SparseArray<>();
        for (CalendarDay day : days) {
            Integer newPrice = Integer.valueOf(day.getPriceInfo().getNativeSuggestedPrice());
            List arrayList = similarlyPricedDays.get(newPrice.intValue()) != null ? (List) similarlyPricedDays.get(newPrice.intValue()) : new ArrayList();
            arrayList.add(day.getDate());
            similarlyPricedDays.put(newPrice.intValue(), arrayList);
        }
        return similarlyPricedDays;
    }

    public static List<AirDate> calendarDaysToAirDates(List<CalendarDay> days) {
        return FluentIterable.from((Iterable<E>) days).transform(CalendarUpdateRequestUtil$$Lambda$1.lambdaFactory$()).toList();
    }
}
