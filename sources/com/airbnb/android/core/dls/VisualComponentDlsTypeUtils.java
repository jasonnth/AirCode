package com.airbnb.android.core.dls;

import com.airbnb.jitney.event.logging.VisualComponentDlsType.p288v1.C2805VisualComponentDlsType;
import com.airbnb.p027n2.DLSComponentType;

public class VisualComponentDlsTypeUtils {
    public static C2805VisualComponentDlsType from(DLSComponentType componentType) {
        if (componentType == null) {
            return C2805VisualComponentDlsType.External;
        }
        switch (componentType) {
            case Core:
                return C2805VisualComponentDlsType.Core;
            case Team:
                return C2805VisualComponentDlsType.Team;
            case Deprecated:
                return C2805VisualComponentDlsType.Deprecated;
            default:
                throw new IllegalStateException("Unknown type");
        }
    }
}
