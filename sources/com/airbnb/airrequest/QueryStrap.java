package com.airbnb.airrequest;

import android.text.TextUtils;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;
import retrofit2.Query;

public class QueryStrap extends TreeSet<Query> {
    public QueryStrap(Comparator<Query> comparator) {
        super(comparator);
    }

    public static QueryStrap make() {
        return new QueryStrap(new Comparator<Query>() {
            public int compare(Query lhs, Query rhs) {
                if (lhs.name().equals(rhs.name())) {
                    return lhs.value().compareTo(rhs.value());
                }
                return lhs.name().compareTo(rhs.name());
            }
        });
    }

    /* renamed from: kv */
    public QueryStrap mo7894kv(String k, long v) {
        return mo7895kv(k, Long.toString(v));
    }

    /* renamed from: kv */
    public QueryStrap mo7893kv(String k, int v) {
        return mo7895kv(k, Integer.toString(v));
    }

    /* renamed from: kv */
    public QueryStrap mo7897kv(String k, boolean v) {
        return mo7895kv(k, Boolean.toString(v));
    }

    /* renamed from: kv */
    public QueryStrap mo7892kv(String k, float v) {
        return mo7895kv(k, Float.toString(v));
    }

    /* renamed from: kv */
    public QueryStrap mo7891kv(String k, double v) {
        return mo7895kv(k, Double.toString(v));
    }

    /* renamed from: kv */
    public QueryStrap mo7895kv(String k, String v) {
        add(new Query(k, v));
        return this;
    }

    /* renamed from: kv */
    public QueryStrap mo7896kv(String k, List<String> valueList) {
        return mo7895kv(k, TextUtils.join(",", valueList));
    }

    public QueryStrap mix(Collection<Query> strap) {
        if (strap != null) {
            addAll(strap);
        }
        return this;
    }

    public QueryStrap mix(Map<String, String> strap) {
        if (strap != null) {
            for (Entry<String, String> entry : strap.entrySet()) {
                mo7895kv((String) entry.getKey(), (String) entry.getValue());
            }
        }
        return this;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("[").append("\n");
        Iterator it = iterator();
        while (it.hasNext()) {
            builder.append("\t").append(((Query) it.next()).toString()).append("\n");
        }
        return builder.append("]").toString();
    }
}
