package com.paypal.android.sdk.onetouch.core.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OAuth2Recipe extends Recipe<OAuth2Recipe> {
    private final Map<String, ConfigEndpoint> mEndpoints = new HashMap();
    private boolean mIsValidForAllScopes;
    private final Collection<String> mScope = new HashSet();

    public OAuth2Recipe getThis() {
        return this;
    }

    public void validForScope(String singleScopeValue) {
        this.mScope.add(singleScopeValue);
    }

    public void withEndpoint(String name, ConfigEndpoint endpoint) {
        this.mEndpoints.put(name, endpoint);
    }

    public boolean isValidForScopes(Set<String> scopes) {
        if (this.mIsValidForAllScopes) {
            return true;
        }
        return scopes.containsAll(scopes);
    }

    public void validForAllScopes() {
        this.mIsValidForAllScopes = true;
    }

    public ConfigEndpoint getEndpoint(String environment) {
        if (this.mEndpoints.containsKey(environment)) {
            return (ConfigEndpoint) this.mEndpoints.get(environment);
        }
        if (this.mEndpoints.containsKey("develop")) {
            return (ConfigEndpoint) this.mEndpoints.get("develop");
        }
        return (ConfigEndpoint) this.mEndpoints.get("live");
    }
}
