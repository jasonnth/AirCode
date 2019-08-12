package com.airbnb.android.photomarkupeditor;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.BaseLogger;
import com.airbnb.jitney.event.logging.ImageAnnotations.p126v1.ImageAnnotationsClickChangeDrawColorEvent;
import com.airbnb.jitney.event.logging.ImageAnnotations.p126v1.ImageAnnotationsClickCropImageEvent;
import com.airbnb.jitney.event.logging.ImageAnnotations.p126v1.ImageAnnotationsClickCropRotateToolIconEvent;
import com.airbnb.jitney.event.logging.ImageAnnotations.p126v1.ImageAnnotationsClickDrawToolIconEvent.Builder;
import com.airbnb.jitney.event.logging.ImageAnnotations.p126v1.ImageAnnotationsClickDrawToolUndoEvent;
import com.airbnb.jitney.event.logging.ImageAnnotations.p126v1.ImageAnnotationsClickRotateImageEvent;
import com.airbnb.jitney.event.logging.ImageAnnotations.p126v1.ImageAnnotationsClickSaveImageEvent;
import com.airbnb.jitney.event.logging.ImageAnnotations.p126v1.ImageAnnotationsSwipeDrawPathEvent;
import com.airbnb.jitney.event.logging.ImageAnnotationsPageType.p016v1.C0951ImageAnnotationsPageType;

public class ImageAnnotationsJitneyLogger extends BaseLogger {
    public ImageAnnotationsJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void logClickDrawToolIconEvent(boolean isHost, C0951ImageAnnotationsPageType pageType) {
        publish(new Builder(context(), Boolean.valueOf(isHost), pageType));
    }

    public void logClickDrawToolUndoEvent(boolean isHost, C0951ImageAnnotationsPageType pageType) {
        publish(new ImageAnnotationsClickDrawToolUndoEvent.Builder(context(), Boolean.valueOf(isHost), pageType));
    }

    public void logClickChangeDrawColorEvent(boolean isHost, C0951ImageAnnotationsPageType pageType) {
        publish(new ImageAnnotationsClickChangeDrawColorEvent.Builder(context(), Boolean.valueOf(isHost), pageType));
    }

    public void logClickCropRotateToolIconEvent(boolean isHost, C0951ImageAnnotationsPageType pageType) {
        publish(new ImageAnnotationsClickCropRotateToolIconEvent.Builder(context(), Boolean.valueOf(isHost), pageType));
    }

    public void logClickCropImageEvent(boolean isHost, C0951ImageAnnotationsPageType pageType) {
        publish(new ImageAnnotationsClickCropImageEvent.Builder(context(), Boolean.valueOf(isHost), pageType));
    }

    public void logClickRotateImageEvent(boolean isHost, C0951ImageAnnotationsPageType pageType) {
        publish(new ImageAnnotationsClickRotateImageEvent.Builder(context(), Boolean.valueOf(isHost), pageType));
    }

    public void logClickSaveImageEvent(boolean isHost, C0951ImageAnnotationsPageType pageType) {
        publish(new ImageAnnotationsClickSaveImageEvent.Builder(context(), Boolean.valueOf(isHost), pageType));
    }

    public void logSwipeDrawPathEvent(boolean isHost, C0951ImageAnnotationsPageType pageType) {
        publish(new ImageAnnotationsSwipeDrawPathEvent.Builder(context(), Boolean.valueOf(isHost), pageType));
    }
}
