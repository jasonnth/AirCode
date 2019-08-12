package com.facebook.react.uimanager;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaConstants;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaOverflow;
import com.facebook.yoga.YogaPositionType;
import com.facebook.yoga.YogaWrap;
import java.util.Locale;

public class LayoutShadowNode extends ReactShadowNode {
    @ReactProp(defaultFloat = Float.NaN, name = "width")
    public void setWidth(float width) {
        if (!isVirtual()) {
            if (!YogaConstants.isUndefined(width)) {
                width = PixelUtil.toPixelFromDIP(width);
            }
            setStyleWidth(width);
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = "minWidth")
    public void setMinWidth(float minWidth) {
        if (!isVirtual()) {
            if (!YogaConstants.isUndefined(minWidth)) {
                minWidth = PixelUtil.toPixelFromDIP(minWidth);
            }
            setStyleMinWidth(minWidth);
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = "maxWidth")
    public void setMaxWidth(float maxWidth) {
        if (!isVirtual()) {
            if (!YogaConstants.isUndefined(maxWidth)) {
                maxWidth = PixelUtil.toPixelFromDIP(maxWidth);
            }
            setStyleMaxWidth(maxWidth);
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = "height")
    public void setHeight(float height) {
        if (!isVirtual()) {
            if (!YogaConstants.isUndefined(height)) {
                height = PixelUtil.toPixelFromDIP(height);
            }
            setStyleHeight(height);
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = "minHeight")
    public void setMinHeight(float minHeight) {
        if (!isVirtual()) {
            if (!YogaConstants.isUndefined(minHeight)) {
                minHeight = PixelUtil.toPixelFromDIP(minHeight);
            }
            setStyleMinHeight(minHeight);
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = "maxHeight")
    public void setMaxHeight(float maxHeight) {
        if (!isVirtual()) {
            if (!YogaConstants.isUndefined(maxHeight)) {
                maxHeight = PixelUtil.toPixelFromDIP(maxHeight);
            }
            setStyleMaxHeight(maxHeight);
        }
    }

    @ReactProp(defaultFloat = 0.0f, name = "flex")
    public void setFlex(float flex) {
        if (!isVirtual()) {
            super.setFlex(flex);
        }
    }

    @ReactProp(defaultFloat = 0.0f, name = "flexGrow")
    public void setFlexGrow(float flexGrow) {
        if (!isVirtual()) {
            super.setFlexGrow(flexGrow);
        }
    }

    @ReactProp(defaultFloat = 0.0f, name = "flexShrink")
    public void setFlexShrink(float flexShrink) {
        if (!isVirtual()) {
            super.setFlexShrink(flexShrink);
        }
    }

    @ReactProp(defaultFloat = 0.0f, name = "flexBasis")
    public void setFlexBasis(float flexBasis) {
        if (!isVirtual()) {
            super.setFlexBasis(flexBasis);
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = "aspectRatio")
    public void setAspectRatio(float aspectRatio) {
        setStyleAspectRatio(aspectRatio);
    }

    @ReactProp(name = "flexDirection")
    public void setFlexDirection(String flexDirection) {
        YogaFlexDirection valueOf;
        if (!isVirtual()) {
            if (flexDirection == null) {
                valueOf = YogaFlexDirection.COLUMN;
            } else {
                valueOf = YogaFlexDirection.valueOf(flexDirection.toUpperCase(Locale.US).replace("-", "_"));
            }
            setFlexDirection(valueOf);
        }
    }

    @ReactProp(name = "flexWrap")
    public void setFlexWrap(String flexWrap) {
        if (!isVirtual()) {
            if (flexWrap == null || flexWrap.equals("nowrap")) {
                setFlexWrap(YogaWrap.NO_WRAP);
            } else if (flexWrap.equals("wrap")) {
                setFlexWrap(YogaWrap.WRAP);
            } else {
                throw new IllegalArgumentException("Unknown flexWrap value: " + flexWrap);
            }
        }
    }

    @ReactProp(name = "alignSelf")
    public void setAlignSelf(String alignSelf) {
        if (!isVirtual()) {
            setAlignSelf(alignSelf == null ? YogaAlign.AUTO : YogaAlign.valueOf(alignSelf.toUpperCase(Locale.US).replace("-", "_")));
        }
    }

    @ReactProp(name = "alignItems")
    public void setAlignItems(String alignItems) {
        YogaAlign valueOf;
        if (!isVirtual()) {
            if (alignItems == null) {
                valueOf = YogaAlign.STRETCH;
            } else {
                valueOf = YogaAlign.valueOf(alignItems.toUpperCase(Locale.US).replace("-", "_"));
            }
            setAlignItems(valueOf);
        }
    }

    @ReactProp(name = "justifyContent")
    public void setJustifyContent(String justifyContent) {
        if (!isVirtual()) {
            setJustifyContent(justifyContent == null ? YogaJustify.FLEX_START : YogaJustify.valueOf(justifyContent.toUpperCase(Locale.US).replace("-", "_")));
        }
    }

    @ReactProp(name = "overflow")
    public void setOverflow(String overflow) {
        if (!isVirtual()) {
            setOverflow(overflow == null ? YogaOverflow.VISIBLE : YogaOverflow.valueOf(overflow.toUpperCase(Locale.US).replace("-", "_")));
        }
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"margin", "marginVertical", "marginHorizontal", "marginLeft", "marginRight", "marginTop", "marginBottom"})
    public void setMargins(int index, float margin) {
        if (!isVirtual()) {
            setMargin(ViewProps.PADDING_MARGIN_SPACING_TYPES[index], PixelUtil.toPixelFromDIP(margin));
        }
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"padding", "paddingVertical", "paddingHorizontal", "paddingLeft", "paddingRight", "paddingTop", "paddingBottom"})
    public void setPaddings(int index, float padding) {
        if (!isVirtual()) {
            int i = ViewProps.PADDING_MARGIN_SPACING_TYPES[index];
            if (!YogaConstants.isUndefined(padding)) {
                padding = PixelUtil.toPixelFromDIP(padding);
            }
            setPadding(i, padding);
        }
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidths(int index, float borderWidth) {
        if (!isVirtual()) {
            setBorder(ViewProps.BORDER_SPACING_TYPES[index], PixelUtil.toPixelFromDIP(borderWidth));
        }
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"left", "right", "top", "bottom"})
    public void setPositionValues(int index, float position) {
        if (!isVirtual()) {
            int i = ViewProps.POSITION_SPACING_TYPES[index];
            if (!YogaConstants.isUndefined(position)) {
                position = PixelUtil.toPixelFromDIP(position);
            }
            setPosition(i, position);
        }
    }

    @ReactProp(name = "position")
    public void setPosition(String position) {
        YogaPositionType positionType;
        if (!isVirtual()) {
            if (position == null) {
                positionType = YogaPositionType.RELATIVE;
            } else {
                positionType = YogaPositionType.valueOf(position.toUpperCase(Locale.US));
            }
            setPositionType(positionType);
        }
    }

    @ReactProp(name = "onLayout")
    public void setShouldNotifyOnLayout(boolean shouldNotifyOnLayout) {
        super.setShouldNotifyOnLayout(shouldNotifyOnLayout);
    }
}
