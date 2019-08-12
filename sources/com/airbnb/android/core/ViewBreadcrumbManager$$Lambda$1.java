package com.airbnb.android.core;

import com.google.common.base.Function;

final /* synthetic */ class ViewBreadcrumbManager$$Lambda$1 implements Function {
    private final ViewBreadcrumbManager arg$1;

    private ViewBreadcrumbManager$$Lambda$1(ViewBreadcrumbManager viewBreadcrumbManager) {
        this.arg$1 = viewBreadcrumbManager;
    }

    public static Function lambdaFactory$(ViewBreadcrumbManager viewBreadcrumbManager) {
        return new ViewBreadcrumbManager$$Lambda$1(viewBreadcrumbManager);
    }

    public Object apply(Object obj) {
        return this.arg$1.breadcrumbToString((ViewBreadcrumb) obj);
    }
}
