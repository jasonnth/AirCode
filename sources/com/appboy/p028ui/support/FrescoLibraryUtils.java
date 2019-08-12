package com.appboy.p028ui.support;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import com.appboy.Appboy;
import com.appboy.Constants;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/* renamed from: com.appboy.ui.support.FrescoLibraryUtils */
public class FrescoLibraryUtils {
    private static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, FrescoLibraryUtils.class.getName()});
    private static final String[] USED_FRESCO_CLASSES = {"com.facebook.drawee.backends.pipeline.Fresco", "com.facebook.drawee.interfaces.DraweeController", "com.facebook.drawee.view.SimpleDraweeView", "com.facebook.drawee.backends.pipeline.Fresco", "com.facebook.drawee.controller.BaseControllerListener", "com.facebook.drawee.controller.ControllerListener", "com.facebook.imagepipeline.image.ImageInfo"};
    private static boolean sCanUseFresco = false;
    private static boolean sCanUseFrescoSet = false;

    private static boolean getIsFrescoEnabled(Context context) {
        return new AppboyConfigurationProvider(context).getIsFrescoLibraryUseEnabled();
    }

    public static boolean canUseFresco(Context context) {
        if (sCanUseFrescoSet) {
            return sCanUseFresco;
        }
        if (!getIsFrescoEnabled(context.getApplicationContext())) {
            sCanUseFresco = false;
            sCanUseFrescoSet = true;
            return false;
        }
        try {
            ClassLoader staticClassLoader = FrescoLibraryUtils.class.getClassLoader();
            sCanUseFresco = true;
            String[] strArr = USED_FRESCO_CLASSES;
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (Class.forName(strArr[i], false, staticClassLoader) == null) {
                    sCanUseFresco = false;
                    break;
                } else {
                    i++;
                }
            }
        } catch (Exception e) {
            sCanUseFresco = false;
        } catch (NoClassDefFoundError e2) {
            sCanUseFresco = false;
        } catch (Throwable th) {
            sCanUseFresco = false;
        }
        sCanUseFrescoSet = true;
        return sCanUseFresco;
    }

    public static void setDraweeControllerHelper(SimpleDraweeView simpleDraweeView, String imageUrl, float aspectRatio, boolean respectAspectRatio) {
        setDraweeControllerHelper(simpleDraweeView, imageUrl, aspectRatio, respectAspectRatio, null);
    }

    public static void setDraweeControllerHelper(final SimpleDraweeView simpleDraweeView, String imageUrl, final float aspectRatio, final boolean respectAspectRatio, ControllerListener<ImageInfo> controllerListener) {
        if (StringUtils.isNullOrBlank(imageUrl)) {
            AppboyLogger.m1739w(TAG, "The url set for the Drawee controller was null. Controller not set.");
        } else if (simpleDraweeView == null) {
            AppboyLogger.m1739w(TAG, "The SimpleDraweeView set for the Drawee controller was null. Controller not set.");
        } else {
            RequestLevel requestLevel = Appboy.getOutboundNetworkRequestsOffline() ? RequestLevel.DISK_CACHE : RequestLevel.FULL_FETCH;
            AppboyLogger.m1733d(TAG, "Setting Fresco image request level to: " + requestLevel);
            if (controllerListener == null) {
                controllerListener = new BaseControllerListener<ImageInfo>() {
                    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                        final float imageAspectRatio;
                        if (imageInfo != null) {
                            if (respectAspectRatio) {
                                imageAspectRatio = aspectRatio;
                            } else {
                                imageAspectRatio = (float) (imageInfo.getWidth() / imageInfo.getHeight());
                            }
                            simpleDraweeView.post(new Runnable() {
                                public void run() {
                                    simpleDraweeView.setAspectRatio(imageAspectRatio);
                                }
                            });
                        }
                    }
                };
            }
            try {
                Uri uri = getFrescoUri(imageUrl);
                simpleDraweeView.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(uri).setAutoPlayAnimations(true)).setTapToRetryEnabled(true)).setControllerListener(controllerListener)).setImageRequest(ImageRequestBuilder.newBuilderWithSource(uri).setLowestPermittedRequestLevel(requestLevel).build())).build());
            } catch (NullPointerException e) {
                AppboyLogger.m1736e(TAG, "Fresco controller builder could not be retrieved. Fresco most likely prematurely shutdown.", e);
            } catch (Exception e2) {
                AppboyLogger.m1736e(TAG, "Fresco controller builder could not be retrieved. Fresco most likely prematurely shutdown.", e2);
            }
        }
    }

    static Uri getFrescoUri(String uriString) {
        Uri uri = Uri.parse(uriString);
        if (StringUtils.isNullOrBlank(uri.getScheme())) {
            return Uri.parse("file://" + uriString);
        }
        return uri;
    }
}
