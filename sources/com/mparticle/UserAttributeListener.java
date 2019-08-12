package com.mparticle;

import java.util.List;
import java.util.Map;

public interface UserAttributeListener {
    void onUserAttributesReceived(Map<String, String> map, Map<String, List<String>> map2);
}
