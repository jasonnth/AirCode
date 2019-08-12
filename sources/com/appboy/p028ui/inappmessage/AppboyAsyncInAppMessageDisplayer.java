package com.appboy.p028ui.inappmessage;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import com.appboy.Constants;
import com.appboy.models.IInAppMessage;
import com.appboy.models.InAppMessageHtmlBase;
import com.appboy.models.InAppMessageHtmlFull;
import com.appboy.p028ui.support.FrescoLibraryUtils;
import com.appboy.support.AppboyImageUtils;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.appboy.support.WebContentUtils;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.File;

/* renamed from: com.appboy.ui.inappmessage.AppboyAsyncInAppMessageDisplayer */
public class AppboyAsyncInAppMessageDisplayer extends AsyncTask<IInAppMessage, Integer, IInAppMessage> {
    /* access modifiers changed from: private */
    public static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyAsyncInAppMessageDisplayer.class.getName()});

    /* access modifiers changed from: protected */
    public IInAppMessage doInBackground(IInAppMessage... inAppMessages) {
        boolean assetDownloadSucceeded;
        try {
            AppboyLogger.m1733d(TAG, "Starting asynchronous in-app message preparation.");
            IInAppMessage inAppMessage = inAppMessages[0];
            Context applicationContext = AppboyInAppMessageManager.getInstance().getApplicationContext();
            if (inAppMessage instanceof InAppMessageHtmlFull) {
                assetDownloadSucceeded = prepareInAppMessageWithHtml(inAppMessage);
            } else if (FrescoLibraryUtils.canUseFresco(applicationContext)) {
                assetDownloadSucceeded = prepareInAppMessageWithFresco(inAppMessage);
            } else {
                assetDownloadSucceeded = prepareInAppMessageWithBitmapDownload(inAppMessage);
            }
            if (!assetDownloadSucceeded) {
                return null;
            }
            return inAppMessage;
        } catch (Exception e) {
            AppboyLogger.m1736e(TAG, "Error running AsyncInAppMessageDisplayer", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(final IInAppMessage inAppMessage) {
        if (inAppMessage != null) {
            try {
                AppboyLogger.m1733d(TAG, "Finished asynchronous in-app message preparation. Attempting to display in-app message.");
                new Handler(AppboyInAppMessageManager.getInstance().getApplicationContext().getMainLooper()).post(new Runnable() {
                    public void run() {
                        AppboyLogger.m1733d(AppboyAsyncInAppMessageDisplayer.TAG, "Displaying in-app message.");
                        AppboyInAppMessageManager.getInstance().displayInAppMessage(inAppMessage, false);
                    }
                });
            } catch (Exception e) {
                AppboyLogger.m1736e(TAG, "Error running onPostExecute", e);
            }
        } else {
            AppboyLogger.m1735e(TAG, "Cannot display the in-app message because the in-app message was null.");
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean prepareInAppMessageWithHtml(IInAppMessage inAppMessage) {
        InAppMessageHtmlBase inAppMessageHtml = (InAppMessageHtmlBase) inAppMessage;
        String localAssets = inAppMessageHtml.getLocalAssetsDirectoryUrl();
        if (!StringUtils.isNullOrBlank(localAssets) && new File(localAssets).exists()) {
            AppboyLogger.m1737i(TAG, "Local assets for html in-app message are already populated. Not downloading assets.");
            return true;
        } else if (StringUtils.isNullOrBlank(inAppMessageHtml.getAssetsZipRemoteUrl())) {
            AppboyLogger.m1737i(TAG, "Html in-app message has no remote asset zip. Continuing with in-app message preparation.");
            return true;
        } else {
            String localWebContentUrl = WebContentUtils.getLocalHtmlUrlFromRemoteUrl(WebContentUtils.getHtmlInAppMessageAssetCacheDirectory(AppboyInAppMessageManager.getInstance().getApplicationContext()), inAppMessageHtml.getAssetsZipRemoteUrl());
            if (!StringUtils.isNullOrBlank(localWebContentUrl)) {
                AppboyLogger.m1733d(TAG, "Local url for html in-app message assets is " + localWebContentUrl);
                inAppMessageHtml.setLocalAssetsDirectoryUrl(localWebContentUrl);
                return true;
            }
            AppboyLogger.m1739w(TAG, String.format("Download of html content to local directory failed for remote url: %s . Returned local url is: %s", new Object[]{inAppMessageHtml.getAssetsZipRemoteUrl(), localWebContentUrl}));
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean prepareInAppMessageWithFresco(IInAppMessage inAppMessage) {
        String localImageUrl = inAppMessage.getLocalImageUrl();
        if (StringUtils.isNullOrBlank(localImageUrl) || !new File(localImageUrl).exists()) {
            inAppMessage.setLocalImageUrl(null);
            String remoteImageUrl = inAppMessage.getRemoteImageUrl();
            if (StringUtils.isNullOrBlank(remoteImageUrl)) {
                AppboyLogger.m1739w(TAG, "In-app message has no remote image url. Not downloading image.");
                return true;
            }
            DataSource dataSource = Fresco.getImagePipeline().prefetchToDiskCache(ImageRequest.fromUri(remoteImageUrl), new Object());
            do {
            } while (!dataSource.isFinished());
            boolean downloadSucceeded = !dataSource.hasFailed();
            if (downloadSucceeded) {
                inAppMessage.setImageDownloadSuccessful(true);
            } else if (dataSource.getFailureCause() == null) {
                AppboyLogger.m1739w(TAG, "Fresco disk prefetch failed with null cause for remote image url:" + remoteImageUrl);
            } else {
                AppboyLogger.m1739w(TAG, "Fresco disk prefetch failed with cause: " + dataSource.getFailureCause().getMessage() + " with remote image url: " + remoteImageUrl);
            }
            dataSource.close();
            return downloadSucceeded;
        }
        AppboyLogger.m1737i(TAG, "In-app message has local image url for Fresco display. Not downloading image.");
        inAppMessage.setImageDownloadSuccessful(true);
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean prepareInAppMessageWithBitmapDownload(IInAppMessage inAppMessage) {
        if (inAppMessage.getBitmap() != null) {
            AppboyLogger.m1737i(TAG, "In-app message already contains image bitmap. Not downloading image from URL.");
            inAppMessage.setImageDownloadSuccessful(true);
            return true;
        }
        String localImageUrl = inAppMessage.getLocalImageUrl();
        if (!StringUtils.isNullOrBlank(localImageUrl) && new File(localImageUrl).exists()) {
            AppboyLogger.m1737i(TAG, "In-app message has local image url.");
            inAppMessage.setBitmap(AppboyImageUtils.getBitmap(Uri.parse(localImageUrl)));
        }
        if (inAppMessage.getBitmap() == null) {
            String remoteImageUrl = inAppMessage.getRemoteImageUrl();
            if (!StringUtils.isNullOrBlank(remoteImageUrl)) {
                AppboyLogger.m1737i(TAG, "In-app message has remote image url. Downloading.");
                inAppMessage.setBitmap(AppboyImageUtils.getBitmap(Uri.parse(remoteImageUrl)));
            } else {
                AppboyLogger.m1739w(TAG, "In-app message has no remote image url. Not downloading image.");
                return true;
            }
        }
        if (inAppMessage.getBitmap() == null) {
            return false;
        }
        inAppMessage.setImageDownloadSuccessful(true);
        return true;
    }
}
