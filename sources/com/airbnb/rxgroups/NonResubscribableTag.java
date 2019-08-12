package com.airbnb.rxgroups;

import java.util.regex.Pattern;

class NonResubscribableTag {
    private static final String IDENTIFIER = NonResubscribableTag.class.getSimpleName();
    private static final Pattern REGEX_MATCHER = Pattern.compile(IDENTIFIER + "_.*#\\d+");
    private static final String TEMPLATE = (IDENTIFIER + "_%s#%d");

    NonResubscribableTag() {
    }

    static String create(Object object) {
        return String.format(TEMPLATE, new Object[]{object.getClass().getSimpleName(), Integer.valueOf(object.hashCode())});
    }

    static boolean isNonResubscribableTag(String tag) {
        return REGEX_MATCHER.matcher(tag).find();
    }
}
