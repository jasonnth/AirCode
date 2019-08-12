package com.horcrux.svg;

import android.view.View;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManager;

public class RNSVGRenderableViewManager extends ViewManager<View, LayoutShadowNode> {
    static final String CLASS_CIRCLE = "RNSVGCircle";
    static final String CLASS_CLIP_PATH = "RNSVGClipPath";
    static final String CLASS_DEFS = "RNSVGDefs";
    static final String CLASS_ELLIPSE = "RNSVGEllipse";
    static final String CLASS_GROUP = "RNSVGGroup";
    static final String CLASS_IMAGE = "RNSVGImage";
    static final String CLASS_LINE = "RNSVGLine";
    static final String CLASS_LINEAR_GRADIENT = "RNSVGLinearGradient";
    static final String CLASS_PATH = "RNSVGPath";
    static final String CLASS_RADIAL_GRADIENT = "RNSVGRadialGradient";
    static final String CLASS_RECT = "RNSVGRect";
    static final String CLASS_TEXT = "RNSVGText";
    static final String CLASS_USE = "RNSVGUse";
    static final String CLASS_VIEW_BOX = "RNSVGViewBox";
    private final String mClassName;

    public static RNSVGRenderableViewManager createRNSVGGroupViewManager() {
        return new RNSVGRenderableViewManager(CLASS_GROUP);
    }

    public static RNSVGRenderableViewManager createRNSVGPathViewManager() {
        return new RNSVGRenderableViewManager(CLASS_PATH);
    }

    public static RNSVGRenderableViewManager createRNSVGTextViewManager() {
        return new RNSVGRenderableViewManager(CLASS_TEXT);
    }

    public static RNSVGRenderableViewManager createRNSVGImageViewManager() {
        return new RNSVGRenderableViewManager(CLASS_IMAGE);
    }

    public static RNSVGRenderableViewManager createRNSVGCircleViewManager() {
        return new RNSVGRenderableViewManager(CLASS_CIRCLE);
    }

    public static RNSVGRenderableViewManager createRNSVGEllipseViewManager() {
        return new RNSVGRenderableViewManager(CLASS_ELLIPSE);
    }

    public static RNSVGRenderableViewManager createRNSVGLineViewManager() {
        return new RNSVGRenderableViewManager(CLASS_LINE);
    }

    public static RNSVGRenderableViewManager createRNSVGRectViewManager() {
        return new RNSVGRenderableViewManager(CLASS_RECT);
    }

    public static RNSVGRenderableViewManager createRNSVGClipPathViewManager() {
        return new RNSVGRenderableViewManager(CLASS_CLIP_PATH);
    }

    public static RNSVGRenderableViewManager createRNSVGDefsViewManager() {
        return new RNSVGRenderableViewManager(CLASS_DEFS);
    }

    public static RNSVGRenderableViewManager createRNSVGUseViewManager() {
        return new RNSVGRenderableViewManager(CLASS_USE);
    }

    public static RNSVGRenderableViewManager createRNSVGViewBoxViewManager() {
        return new RNSVGRenderableViewManager(CLASS_VIEW_BOX);
    }

    public static RNSVGRenderableViewManager createRNSVGLinearGradientManager() {
        return new RNSVGRenderableViewManager(CLASS_LINEAR_GRADIENT);
    }

    public static RNSVGRenderableViewManager createRNSVGRadialGradientManager() {
        return new RNSVGRenderableViewManager(CLASS_RADIAL_GRADIENT);
    }

    private RNSVGRenderableViewManager(String className) {
        this.mClassName = className;
    }

    public String getName() {
        return this.mClassName;
    }

    public LayoutShadowNode createShadowNodeInstance() {
        String str = this.mClassName;
        char c = 65535;
        switch (str.hashCode()) {
            case -1866779881:
                if (str.equals(CLASS_RADIAL_GRADIENT)) {
                    c = 13;
                    break;
                }
                break;
            case -1487762378:
                if (str.equals(CLASS_ELLIPSE)) {
                    c = 3;
                    break;
                }
                break;
            case -503960650:
                if (str.equals(CLASS_DEFS)) {
                    c = 9;
                    break;
                }
                break;
            case -503718244:
                if (str.equals(CLASS_LINE)) {
                    c = 4;
                    break;
                }
                break;
            case -503606579:
                if (str.equals(CLASS_PATH)) {
                    c = 1;
                    break;
                }
                break;
            case -503543668:
                if (str.equals(CLASS_RECT)) {
                    c = 5;
                    break;
                }
                break;
            case -503483435:
                if (str.equals(CLASS_TEXT)) {
                    c = 6;
                    break;
                }
                break;
            case -154787361:
                if (str.equals(CLASS_USE)) {
                    c = 10;
                    break;
                }
                break;
            case 622918974:
                if (str.equals(CLASS_VIEW_BOX)) {
                    c = 11;
                    break;
                }
                break;
            case 748220957:
                if (str.equals(CLASS_LINEAR_GRADIENT)) {
                    c = 12;
                    break;
                }
                break;
            case 1000530296:
                if (str.equals(CLASS_CIRCLE)) {
                    c = 2;
                    break;
                }
                break;
            case 1560255703:
                if (str.equals(CLASS_GROUP)) {
                    c = 0;
                    break;
                }
                break;
            case 1561939891:
                if (str.equals(CLASS_IMAGE)) {
                    c = 7;
                    break;
                }
                break;
            case 1852960317:
                if (str.equals(CLASS_CLIP_PATH)) {
                    c = 8;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return new RNSVGGroupShadowNode();
            case 1:
                return new RNSVGPathShadowNode();
            case 2:
                return new RNSVGCircleShadowNode();
            case 3:
                return new RNSVGEllipseShadowNode();
            case 4:
                return new RNSVGLineShadowNode();
            case 5:
                return new RNSVGRectShadowNode();
            case 6:
                return new RNSVGTextShadowNode();
            case 7:
                return new RNSVGImageShadowNode();
            case 8:
                return new RNSVGClipPathShadowNode();
            case 9:
                return new RNSVGDefsShadowNode();
            case 10:
                return new RNSVGUseShadowNode();
            case 11:
                return new RNSVGViewBoxShadowNode();
            case 12:
                return new RNSVGLinearGradientShadowNode();
            case 13:
                return new RNSVGRadialGradientShadowNode();
            default:
                throw new IllegalStateException("Unexpected type " + this.mClassName);
        }
    }

    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        String str = this.mClassName;
        char c = 65535;
        switch (str.hashCode()) {
            case -1866779881:
                if (str.equals(CLASS_RADIAL_GRADIENT)) {
                    c = 13;
                    break;
                }
                break;
            case -1487762378:
                if (str.equals(CLASS_ELLIPSE)) {
                    c = 3;
                    break;
                }
                break;
            case -503960650:
                if (str.equals(CLASS_DEFS)) {
                    c = 9;
                    break;
                }
                break;
            case -503718244:
                if (str.equals(CLASS_LINE)) {
                    c = 4;
                    break;
                }
                break;
            case -503606579:
                if (str.equals(CLASS_PATH)) {
                    c = 1;
                    break;
                }
                break;
            case -503543668:
                if (str.equals(CLASS_RECT)) {
                    c = 5;
                    break;
                }
                break;
            case -503483435:
                if (str.equals(CLASS_TEXT)) {
                    c = 6;
                    break;
                }
                break;
            case -154787361:
                if (str.equals(CLASS_USE)) {
                    c = 10;
                    break;
                }
                break;
            case 622918974:
                if (str.equals(CLASS_VIEW_BOX)) {
                    c = 11;
                    break;
                }
                break;
            case 748220957:
                if (str.equals(CLASS_LINEAR_GRADIENT)) {
                    c = 12;
                    break;
                }
                break;
            case 1000530296:
                if (str.equals(CLASS_CIRCLE)) {
                    c = 2;
                    break;
                }
                break;
            case 1560255703:
                if (str.equals(CLASS_GROUP)) {
                    c = 0;
                    break;
                }
                break;
            case 1561939891:
                if (str.equals(CLASS_IMAGE)) {
                    c = 7;
                    break;
                }
                break;
            case 1852960317:
                if (str.equals(CLASS_CLIP_PATH)) {
                    c = 8;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return RNSVGGroupShadowNode.class;
            case 1:
                return RNSVGPathShadowNode.class;
            case 2:
                return RNSVGCircleShadowNode.class;
            case 3:
                return RNSVGEllipseShadowNode.class;
            case 4:
                return RNSVGLineShadowNode.class;
            case 5:
                return RNSVGRectShadowNode.class;
            case 6:
                return RNSVGTextShadowNode.class;
            case 7:
                return RNSVGImageShadowNode.class;
            case 8:
                return RNSVGClipPathShadowNode.class;
            case 9:
                return RNSVGDefsShadowNode.class;
            case 10:
                return RNSVGUseShadowNode.class;
            case 11:
                return RNSVGViewBoxShadowNode.class;
            case 12:
                return RNSVGLinearGradientShadowNode.class;
            case 13:
                return RNSVGRadialGradientShadowNode.class;
            default:
                throw new IllegalStateException("Unexpected type " + this.mClassName);
        }
    }

    /* access modifiers changed from: protected */
    public View createViewInstance(ThemedReactContext reactContext) {
        throw new IllegalStateException("SVG elements does not map into a native view");
    }

    public void updateExtraData(View root, Object extraData) {
        throw new IllegalStateException("SVG elements does not map into a native view");
    }
}
