package com.facebook.react.cxxbridge;

import com.facebook.jni.HybridData;
import java.util.Collection;

public class ModuleRegistryHolder {
    private final HybridData mHybridData;

    private static native HybridData initHybrid(CatalystInstanceImpl catalystInstanceImpl, Collection<JavaModuleWrapper> collection, Collection<CxxModuleWrapper> collection2);

    public ModuleRegistryHolder(CatalystInstanceImpl catalystInstanceImpl, Collection<JavaModuleWrapper> javaModules, Collection<CxxModuleWrapper> cxxModules) {
        this.mHybridData = initHybrid(catalystInstanceImpl, javaModules, cxxModules);
    }
}
