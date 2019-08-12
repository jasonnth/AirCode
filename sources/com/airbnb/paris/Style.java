package com.airbnb.paris;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import com.facebook.places.model.PlaceFields;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Style.kt */
public final class Style {
    public static final Companion Companion = new Companion(null);
    private final SparseIntArray attributeMap;
    private final AttributeSet attributeSet;
    private final Config config;
    private DebugListener debugListener;
    private final int styleRes;

    /* compiled from: Style.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    /* compiled from: Style.kt */
    public static final class Config {
        public static final Companion Companion = new Companion(null);
        private final Set<Option> options;

        /* compiled from: Style.kt */
        public static final class Builder {
            private Set<Option> options;

            public Builder() {
                this.options = new LinkedHashSet();
            }

            public Builder(Config config) {
                Intrinsics.checkParameterIsNotNull(config, "config");
                this();
                this.options = CollectionsKt.toMutableSet(config.getOptions());
            }

            public final Builder addOption(Option option) {
                Intrinsics.checkParameterIsNotNull(option, "option");
                this.options.add(option);
                return this;
            }

            public final Config build() {
                return new Config(this.options);
            }
        }

        /* compiled from: Style.kt */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }

            public final Builder builder() {
                return new Builder();
            }
        }

        /* compiled from: Style.kt */
        public interface Option {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual(r2.options, ((com.airbnb.paris.Style.Config) r3).options) != false) goto L_0x0012;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r3) {
            /*
                r2 = this;
                if (r2 == r3) goto L_0x0012
                boolean r0 = r3 instanceof com.airbnb.paris.Style.Config
                if (r0 == 0) goto L_0x0014
                com.airbnb.paris.Style$Config r3 = (com.airbnb.paris.Style.Config) r3
                java.util.Set<com.airbnb.paris.Style$Config$Option> r0 = r2.options
                java.util.Set<com.airbnb.paris.Style$Config$Option> r1 = r3.options
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                if (r0 == 0) goto L_0x0014
            L_0x0012:
                r0 = 1
            L_0x0013:
                return r0
            L_0x0014:
                r0 = 0
                goto L_0x0013
            */
            throw new UnsupportedOperationException("Method not decompiled: com.airbnb.paris.Style.Config.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            Set<Option> set = this.options;
            if (set != null) {
                return set.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "Config(options=" + this.options + ")";
        }

        public Config(Set<? extends Option> options2) {
            Intrinsics.checkParameterIsNotNull(options2, "options");
            this.options = options2;
        }

        public final Set<Option> getOptions() {
            return this.options;
        }

        public final Builder toBuilder() {
            return new Builder(this);
        }

        public final boolean contains(Option option) {
            Intrinsics.checkParameterIsNotNull(option, "option");
            return this.options.contains(option);
        }
    }

    /* compiled from: Style.kt */
    public interface DebugListener {
        void beforeTypedArrayProcessed(Style style, TypedArrayWrapper typedArrayWrapper);
    }

    private Style(SparseIntArray attributeMap2, AttributeSet attributeSet2, int styleRes2, Config config2) {
        this.attributeMap = attributeMap2;
        this.attributeSet = attributeSet2;
        this.styleRes = styleRes2;
        this.config = config2;
    }

    public final AttributeSet getAttributeSet() {
        return this.attributeSet;
    }

    public Style(AttributeSet attributeSet2) {
        Intrinsics.checkParameterIsNotNull(attributeSet2, "attributeSet");
        this(null, attributeSet2, 0, null);
    }

    public Style(AttributeSet attributeSet2, Config config2) {
        Intrinsics.checkParameterIsNotNull(attributeSet2, "attributeSet");
        Intrinsics.checkParameterIsNotNull(config2, "config");
        this(null, attributeSet2, 0, config2);
    }

    public Style(int styleRes2) {
        this(null, null, styleRes2, null);
    }

    public Style(int styleRes2, Config config2) {
        Intrinsics.checkParameterIsNotNull(config2, "config");
        this(null, null, styleRes2, config2);
    }

    public final DebugListener getDebugListener() {
        return this.debugListener;
    }

    public final void setDebugListener(DebugListener debugListener2) {
        this.debugListener = debugListener2;
    }

    @SuppressLint({"Recycle"})
    public final TypedArrayWrapper obtainStyledAttributes(Context context, int[] attrs) {
        Intrinsics.checkParameterIsNotNull(context, PlaceFields.CONTEXT);
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
        if (this.attributeMap != null) {
            SparseIntArray filteredAttributeMap = new SparseIntArray();
            for (int attrRes : attrs) {
                int value = this.attributeMap.get(attrRes, -1);
                if (value != -1) {
                    filteredAttributeMap.put(attrRes, value);
                }
            }
            Resources resources = context.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
            return new SparseIntArrayTypedArrayWrapper(resources, filteredAttributeMap);
        } else if (this.attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(this.attributeSet, attrs, 0, this.styleRes);
            Intrinsics.checkExpressionValueIsNotNull(obtainStyledAttributes, "context.obtainStyledAttr…eSet, attrs, 0, styleRes)");
            return new TypedArrayTypedArrayWrapper(obtainStyledAttributes);
        } else if (this.styleRes == 0) {
            return null;
        } else {
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(this.styleRes, attrs);
            Intrinsics.checkExpressionValueIsNotNull(obtainStyledAttributes2, "context.obtainStyledAttributes(styleRes, attrs)");
            return new TypedArrayTypedArrayWrapper(obtainStyledAttributes2);
        }
    }

    public final boolean hasOption(Option option) {
        Intrinsics.checkParameterIsNotNull(option, "option");
        return this.config != null && this.config.contains(option);
    }
}
