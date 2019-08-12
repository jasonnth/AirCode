package com.airbnb.android.core;

import android.support.p000v4.app.Fragment;
import android.support.p002v7.app.AppCompatActivity;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.activities.AutoAirActivity;
import com.airbnb.android.core.fragments.AirFragment;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class ViewBreadcrumbManager {
    private static final Set<Class<?>> EXCLUDED_CLASSES = ImmutableSet.m1300of(AirFragment.class, AirActivity.class, AutoAirActivity.class);
    private static final int MAX_BREADCRUMBS = 10;
    private final Stack<ViewBreadcrumb> breadcrumbStack = new Stack<>();

    class ViewBreadcrumb {
        private final Class<?> klass;
        private final long timestamp;

        public ViewBreadcrumb(Class<?> klass2) {
            if (klass2 == null) {
                throw new IllegalArgumentException("class of view breadcrumb can not be null");
            }
            this.klass = klass2;
            this.timestamp = System.currentTimeMillis();
        }

        public Class<?> getKlass() {
            return this.klass;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            return this.klass.equals(((ViewBreadcrumb) o).klass);
        }

        public int hashCode() {
            if (this.klass != null) {
                return this.klass.hashCode();
            }
            return 0;
        }
    }

    public void addBreadcrumb(Fragment fragment) {
        storeBreadcrumb(fragment);
    }

    public void addBreadcrumb(AppCompatActivity activity) {
        storeBreadcrumb(activity);
    }

    private void storeBreadcrumb(Object object) {
        ViewBreadcrumb viewBreadcrumb = new ViewBreadcrumb(object.getClass());
        if (!shouldIgnoreBreadcrumb(viewBreadcrumb)) {
            this.breadcrumbStack.add(viewBreadcrumb);
            while (this.breadcrumbStack.size() > 10) {
                this.breadcrumbStack.remove(0);
            }
        }
    }

    private boolean shouldIgnoreBreadcrumb(ViewBreadcrumb viewBreadcrumb) {
        return EXCLUDED_CLASSES.contains(viewBreadcrumb.getKlass());
    }

    public ImmutableList<String> getBreadcrumbs() {
        List<ViewBreadcrumb> list = new ArrayList<>(this.breadcrumbStack);
        Collections.reverse(list);
        return FluentIterable.from((Iterable<E>) list).transform(ViewBreadcrumbManager$$Lambda$1.lambdaFactory$(this)).toList();
    }

    /* access modifiers changed from: private */
    public String breadcrumbToString(ViewBreadcrumb viewBreadcrumb) {
        return viewBreadcrumb.getKlass().getCanonicalName();
    }

    public Map<String, String> getBreadcrumbMap() {
        Map<String, String> map = new HashMap<>();
        ImmutableList<String> breadcrumbList = getBreadcrumbs();
        for (int i = 0; i < Math.min(breadcrumbList.size(), 10); i++) {
            map.put("breadcrumb_" + i, breadcrumbList.get(i));
        }
        return map;
    }
}
