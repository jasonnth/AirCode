package com.airbnb.deeplinkdispatch;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DeepLinkEntry {
    private static final Pattern PARAM_PATTERN = Pattern.compile("%7B(([a-zA-Z][a-zA-Z0-9_-]*))%7D");
    private final Class<?> activityClass;
    private final String method;
    private final Set<String> parameters;
    private final Pattern regex;
    private final Type type;

    public enum Type {
        CLASS,
        METHOD
    }

    public DeepLinkEntry(String uri, Type type2, Class<?> activityClass2, String method2) {
        DeepLinkUri parsedUri = DeepLinkUri.parse(uri);
        String schemeHostAndPath = schemeHostAndPath(parsedUri);
        this.type = type2;
        this.activityClass = activityClass2;
        this.method = method2;
        this.parameters = parseParameters(parsedUri);
        this.regex = Pattern.compile(schemeHostAndPath.replaceAll("%7B(([a-zA-Z][a-zA-Z0-9_-]*))%7D", "([a-zA-Z0-9_#'!+%~,\\-\\.\\@\\$\\:]+)") + "$");
    }

    public Type getType() {
        return this.type;
    }

    public Class<?> getActivityClass() {
        return this.activityClass;
    }

    public String getMethod() {
        return this.method;
    }

    private static Set<String> parseParameters(DeepLinkUri uri) {
        Matcher matcher = PARAM_PATTERN.matcher(uri.encodedHost() + uri.encodedPath());
        Set<String> patterns = new LinkedHashSet<>();
        while (matcher.find()) {
            patterns.add(matcher.group(1));
        }
        return patterns;
    }

    public Map<String, String> getParameters(String inputUri) {
        Map<String, String> paramsMap = new HashMap<>(this.parameters.size());
        Matcher matcher = this.regex.matcher(schemeHostAndPath(DeepLinkUri.parse(inputUri)));
        int i = 1;
        if (matcher.matches()) {
            for (String key : this.parameters) {
                int i2 = i + 1;
                String value = matcher.group(i);
                if (value != null && !"".equals(value.trim())) {
                    paramsMap.put(key, value);
                }
                i = i2;
            }
        }
        return paramsMap;
    }

    private static String parsePath(DeepLinkUri parsedUri) {
        return parsedUri.encodedPath();
    }

    public boolean matches(String inputUri) {
        DeepLinkUri deepLinkUri = DeepLinkUri.parse(inputUri);
        return deepLinkUri != null && this.regex.matcher(schemeHostAndPath(deepLinkUri)).find();
    }

    private String schemeHostAndPath(DeepLinkUri uri) {
        return uri.scheme() + "://" + uri.encodedHost() + parsePath(uri);
    }
}
