package com.airbnb.p027n2.components;

import android.view.View;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.airbnb.n2.components.DLSComponents */
public class DLSComponents extends DLSComponentsBase {
    private static boolean componentNamesInitialized;
    private static final Map<String, DLSComponent<?>> componentNamesToInstance = new HashMap();
    private static boolean viewClassesInitialized;
    private static final Map<Class<? extends View>, DLSComponent<?>> viewClassesToInstance = new HashMap();

    public static DLSComponent<?>[] all() {
        return ALL;
    }

    public static DLSComponent<?> fromClass(Class<? extends View> viewClass) {
        if (!viewClassesInitialized) {
            synchronized (DLSComponents.class) {
                if (!viewClassesInitialized) {
                    initializeClassMap();
                }
            }
        }
        return (DLSComponent) viewClassesToInstance.get(viewClass);
    }

    private static void initializeClassMap() {
        DLSComponent<?>[] dLSComponentArr;
        for (DLSComponent<?> component : ALL) {
            viewClassesToInstance.put(component.viewClass(), component);
        }
        viewClassesInitialized = true;
    }

    public static DLSComponent<?> fromName(String name) {
        if (!componentNamesInitialized) {
            synchronized (DLSComponents.class) {
                if (!componentNamesInitialized) {
                    initializeNameMap();
                }
            }
        }
        return (DLSComponent) componentNamesToInstance.get(name);
    }

    private static void initializeNameMap() {
        DLSComponent<?>[] dLSComponentArr;
        for (DLSComponent<?> component : ALL) {
            componentNamesToInstance.put(component.name(), component);
        }
        componentNamesInitialized = true;
    }
}
