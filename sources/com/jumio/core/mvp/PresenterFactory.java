package com.jumio.core.mvp;

import com.jumio.core.mvp.presenter.Presenter;
import com.jumio.core.mvp.presenter.RequiresPresenter;

public class PresenterFactory {
    public static <T extends Presenter> T createClass(Class<?> viewClass) {
        RequiresPresenter annotation = (RequiresPresenter) viewClass.getAnnotation(RequiresPresenter.class);
        if (annotation == null) {
            throw new RuntimeException("View " + viewClass.getName() + " must be annotated with the @RequiresPresenter annotation!");
        }
        Class<T> presenterClass = annotation.value();
        if (presenterClass == null) {
            return null;
        }
        try {
            return (Presenter) presenterClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
