package com.facebook.react.flat;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.infer.annotation.Assertions;

final class DraweeRequestHelper {
    private static AbstractDraweeControllerBuilder sControllerBuilder;
    private static GenericDraweeHierarchyBuilder sHierarchyBuilder;
    private int mAttachCounter;
    private final DraweeController mDraweeController;

    static void setResources(Resources resources) {
        sHierarchyBuilder = new GenericDraweeHierarchyBuilder(resources);
    }

    static void setDraweeControllerBuilder(AbstractDraweeControllerBuilder builder) {
        sControllerBuilder = builder;
    }

    DraweeRequestHelper(ImageRequest imageRequest, ImageRequest cachedImageRequest, ControllerListener listener) {
        AbstractDraweeControllerBuilder controllerBuilder = sControllerBuilder.setImageRequest(imageRequest).setCallerContext(RCTImageView.getCallerContext()).setControllerListener(listener);
        if (cachedImageRequest != null) {
            controllerBuilder.setLowResImageRequest(cachedImageRequest);
        }
        DraweeController controller = controllerBuilder.build();
        controller.setHierarchy(sHierarchyBuilder.build());
        this.mDraweeController = controller;
    }

    /* access modifiers changed from: 0000 */
    public void attach(InvalidateCallback callback) {
        this.mAttachCounter++;
        if (this.mAttachCounter == 1) {
            getDrawable().setCallback((Callback) callback.get());
            this.mDraweeController.onAttach();
        }
    }

    /* access modifiers changed from: 0000 */
    public void detach() {
        this.mAttachCounter--;
        if (this.mAttachCounter == 0) {
            this.mDraweeController.onDetach();
        }
    }

    /* access modifiers changed from: 0000 */
    public GenericDraweeHierarchy getHierarchy() {
        return (GenericDraweeHierarchy) Assertions.assumeNotNull(this.mDraweeController.getHierarchy());
    }

    /* access modifiers changed from: 0000 */
    public Drawable getDrawable() {
        return getHierarchy().getTopLevelDrawable();
    }
}
