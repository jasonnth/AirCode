package com.appboy.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public enum CardCategory {
    ADVERTISING,
    ANNOUNCEMENTS,
    NEWS,
    SOCIAL,
    NO_CATEGORY;
    
    @Deprecated
    public static final EnumSet<CardCategory> ALL_CATEGORIES = null;

    /* renamed from: a */
    private static final Map<String, CardCategory> f1367a = null;

    static {
        f1367a = new HashMap();
        ALL_CATEGORIES = EnumSet.allOf(CardCategory.class);
        Iterator it = EnumSet.allOf(CardCategory.class).iterator();
        while (it.hasNext()) {
            CardCategory cardCategory = (CardCategory) it.next();
            f1367a.put(cardCategory.toString(), cardCategory);
        }
    }

    public static CardCategory get(String value) {
        return (CardCategory) f1367a.get(value.toUpperCase(Locale.US));
    }

    public static EnumSet<CardCategory> getAllCategories() {
        return EnumSet.allOf(CardCategory.class);
    }
}
